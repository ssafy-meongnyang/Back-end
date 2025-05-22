package com.ssafy.meongnyang.api.board.service;

import com.ssafy.meongnyang.api.board.domain.Board;
import com.ssafy.meongnyang.api.board.dto.request.BoardCreateRequest;
import com.ssafy.meongnyang.api.board.dto.request.BoardUpdateRequest;
import com.ssafy.meongnyang.api.board.dto.response.BoardGetResponse;
import com.ssafy.meongnyang.api.board.dto.response.BoardListGetResponse;
import com.ssafy.meongnyang.api.board.repository.BoardRepository;
import com.ssafy.meongnyang.global.external.S3Service;
import java.io.IOException;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BoardServiceImpl implements BoardService {

    private final BoardRepository boardRepository;
    private final S3Service s3Service;

    private static final String BOARD_FILE_PATH = "board_file/";
    private static final String BUCKET_PATH = "https://meongnyang-ssafy.s3.ap-northeast-2.amazonaws.com/";
    public void createBoard(BoardCreateRequest boardCreateRequest) {
        try {
            Board board = Board.builder()
                    .category(boardCreateRequest.category())
                    .userId(boardCreateRequest.userId()) //아직 토큰 구현이 안된 것 같아서 일단 임의로 넣게 만들었습니다
                    .title(boardCreateRequest.title())
                    .content(boardCreateRequest.content())
                    .imageUrl(BUCKET_PATH + s3Service.uploadImage(BOARD_FILE_PATH, boardCreateRequest.image()))
                    .build();
            boardRepository.insertBoard(board);
        } catch (IOException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    public List<BoardListGetResponse> getBoardList() {
        return boardRepository.getBoardListWithUser();
    }

    public BoardGetResponse getBoardById(int boardId) {
        return boardRepository.getBoardById(boardId);
    }

    public void updateBoard(BoardUpdateRequest boardUpdateRequest, int boardId){
        Board board = boardRepository.getBoard(boardId);
        board.setCategory(boardUpdateRequest.category());
        board.setTitle(boardUpdateRequest.title());
        board.setContent(boardUpdateRequest.content());
        try {
            board.setImageUrl(BUCKET_PATH+ s3Service.uploadImage(BOARD_FILE_PATH, boardUpdateRequest.image()));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        boardRepository.updateBoard(board);
    }

    public Board getBoard(int boardId){
        return boardRepository.getBoard(boardId);
    }
}
