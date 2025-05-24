package com.ssafy.meongnyang.api.board.service;

import com.ssafy.meongnyang.api.board.domain.Board;
import com.ssafy.meongnyang.api.board.dto.request.BoardCreateRequest;
import com.ssafy.meongnyang.api.board.dto.request.BoardUpdateRequest;
import com.ssafy.meongnyang.api.board.dto.response.BoardGetResponse;
import com.ssafy.meongnyang.api.board.dto.response.BoardListGetResponse;
import java.util.List;

public interface BoardService {
    void createBoard(BoardCreateRequest boardCreateRequest, Long userId);
    List<BoardListGetResponse> getBoardList();
    BoardGetResponse getBoardById(Long boardId);
    void updateBoard(BoardUpdateRequest boardUpdateRequest, Long boardId);
    Board getBoard(Long boardId);
    void deleteBoard(Long boardId);
    List<BoardListGetResponse> searchBoard(String writer, String title, String content);
}
