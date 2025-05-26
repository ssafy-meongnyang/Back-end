package com.ssafy.meongnyang.api.board.service;

import com.ssafy.meongnyang.api.board.domain.Board;
import com.ssafy.meongnyang.api.board.dto.request.BoardCreateRequest;
import com.ssafy.meongnyang.api.board.dto.request.BoardUpdateRequest;
import com.ssafy.meongnyang.api.board.dto.response.BoardGetResponse;
import com.ssafy.meongnyang.api.board.dto.response.BoardListGetResponse;
import com.ssafy.meongnyang.api.board.dto.response.PageResponse;
import com.ssafy.meongnyang.api.board.repository.BoardRepository;
import com.ssafy.meongnyang.global.exception.CustomException;
import com.ssafy.meongnyang.global.external.S3Service;
import com.ssafy.meongnyang.global.response.enums.ErrorCode;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BoardServiceImpl implements BoardService {

    private final BoardRepository boardRepository;
    private final S3Service s3Service;

    private static final String BOARD_FILE_PATH = "board_file/";
    private static final String BUCKET_PATH = "https://meongnyang-ssafy.s3.ap-northeast-2.amazonaws.com/";

    public void createBoard(BoardCreateRequest boardCreateRequest, Long userId) {
        try {
            if (boardCreateRequest.image() != null) {
                Board board = Board.builder()
                        .category(boardCreateRequest.category())
                        .userId(userId)
                        .title(boardCreateRequest.title())
                        .content(boardCreateRequest.content())
                        .imageUrl(BUCKET_PATH + s3Service.uploadImage(BOARD_FILE_PATH, boardCreateRequest.image()))
                        .build();
                boardRepository.insertBoard(board);
            } else {
                Board board = Board.builder()
                        .category(boardCreateRequest.category())
                        .userId(userId)
                        .title(boardCreateRequest.title())
                        .content(boardCreateRequest.content())
                        .build();
                boardRepository.insertBoard(board);
            }
        } catch (IOException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    public PageResponse<BoardListGetResponse> getBoardList(int page, int size, String category) {
        int offset = (page - 1) * size;
        List<BoardListGetResponse> boards = boardRepository.getBoardListWithUser(offset, size, category);
        int total = boardRepository.getBoardCount();
        List<Map<String, Object>> rawCounts = boardRepository.getBoardCategoryCount();
        Map<String, Integer> categoryCounts = new HashMap<>();
        int totalCount = 0;

        for (Map<String, Object> row : rawCounts) {
            String cat = (String) row.get("category");
            int count = ((Number) row.get("count")).intValue();
            categoryCounts.put(cat, count);
            totalCount += count;
        }

        categoryCounts.put("전체", totalCount); // "전체" = 전체 합

        return new PageResponse<>(boards, total, size, total, categoryCounts);
    }

    public int getBoardCount() {
        return boardRepository.getBoardCount();
    }

    public BoardGetResponse getBoardById(Long boardId) {
        return boardRepository.getBoardById(boardId);
    }

    public void updateBoard(BoardUpdateRequest boardUpdateRequest, Long boardId, Long userId) {
        Board board = boardRepository.getBoard(boardId);
        if(board.getUserId() != userId) {
            throw new CustomException(ErrorCode.NOT_AUTHOR);
        }
        board.setCategory(boardUpdateRequest.category());
        board.setTitle(boardUpdateRequest.title());
        board.setContent(boardUpdateRequest.content());
        if (boardUpdateRequest.image() != null && !boardUpdateRequest.image().isEmpty()) {
            try {
                String uploadedUrl = BUCKET_PATH + s3Service.uploadImage(BOARD_FILE_PATH, boardUpdateRequest.image());
                board.setImageUrl(uploadedUrl);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        } else if (boardUpdateRequest.isDeleted() && boardUpdateRequest.image() == null) {
            // 이미지가 전달되지 않았을 때 기존 이미지 URL 유지
            board.setImageUrl(null);
        } else {
            board.setImageUrl(board.getImageUrl());
        }
        boardRepository.updateBoard(board);
    }

    public Board getBoard(Long boardId) {
        return boardRepository.getBoard(boardId);
    }

    public void deleteBoard(Long boardId, Long userId) {
        if(boardRepository.getBoard(boardId).getUserId() != userId){
            throw new CustomException(ErrorCode.NOT_AUTHOR);
        }
        boardRepository.deleteBoard(boardId);
    }

    public List<BoardListGetResponse> searchBoard(String writer, String title, String content) {
        return boardRepository.searchBoard(writer, title, content);
    }
}
