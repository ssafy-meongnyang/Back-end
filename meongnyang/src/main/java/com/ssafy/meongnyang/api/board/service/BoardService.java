package com.ssafy.meongnyang.api.board.service;

import com.ssafy.meongnyang.api.board.dto.BoardCreateRequest;
import com.ssafy.meongnyang.api.board.dto.BoardListGetResponse;
import java.util.List;

public interface BoardService {
    void createBoard(BoardCreateRequest boardCreateRequest);
    List<BoardListGetResponse> getBoardList();
}
