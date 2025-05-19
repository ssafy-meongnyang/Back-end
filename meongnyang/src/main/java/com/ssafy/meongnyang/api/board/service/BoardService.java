package com.ssafy.meongnyang.api.board.service;

import com.ssafy.meongnyang.api.board.dto.request.BoardCreateRequest;
import com.ssafy.meongnyang.api.board.dto.response.BoardGetResponse;
import com.ssafy.meongnyang.api.board.dto.response.BoardListGetResponse;
import java.util.List;

public interface BoardService {
    void createBoard(BoardCreateRequest boardCreateRequest);
    List<BoardListGetResponse> getBoardList();
    BoardGetResponse getBoardById(int boardId);
}
