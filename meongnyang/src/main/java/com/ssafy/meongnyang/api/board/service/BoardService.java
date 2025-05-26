package com.ssafy.meongnyang.api.board.service;

import com.ssafy.meongnyang.api.board.domain.Board;
import com.ssafy.meongnyang.api.board.dto.request.BoardCreateRequest;
import com.ssafy.meongnyang.api.board.dto.request.BoardUpdateRequest;
import com.ssafy.meongnyang.api.board.dto.response.BoardGetResponse;
import com.ssafy.meongnyang.api.board.dto.response.BoardListGetResponse;
import com.ssafy.meongnyang.api.board.dto.response.PageResponse;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface BoardService {
    void createBoard(BoardCreateRequest boardCreateRequest, Long userId);
    PageResponse<BoardListGetResponse> getBoardList(int page, int size, String category);
    int getBoardCount();
    BoardGetResponse getBoardById(Long boardId);
    void updateBoard(BoardUpdateRequest boardUpdateRequest, Long boardId);
    Board getBoard(Long boardId);
    void deleteBoard(Long boardId);
    List<BoardListGetResponse> searchBoard(String writer, String title, String content);

}
