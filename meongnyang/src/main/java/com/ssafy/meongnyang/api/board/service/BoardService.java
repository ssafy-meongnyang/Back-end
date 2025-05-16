package com.ssafy.meongnyang.api.board.service;

import com.ssafy.meongnyang.api.board.dto.BoardCreateRequest;

public interface BoardService {
    void createBoard(BoardCreateRequest boardCreateRequest);
}
