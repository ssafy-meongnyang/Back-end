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
    BoardGetResponse getBoardById(int boardId);
    void updateBoard(BoardUpdateRequest boardUpdateRequest, int boardId);
    Board getBoard(int boardId);
    void deleteBoard(int boardId);
}
