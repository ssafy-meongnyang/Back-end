package com.ssafy.meongnyang.api.board.repository;

import com.ssafy.meongnyang.api.board.domain.Board;
import com.ssafy.meongnyang.api.board.dto.response.BoardGetResponse;
import com.ssafy.meongnyang.api.board.dto.response.BoardListGetResponse;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface BoardRepository {
    void insertBoard(Board board);
    List<BoardListGetResponse> getBoardListWithUser();
    BoardGetResponse getBoardById(int boardId);
    void updateBoard(Board board);
    Board getBoard(int boardId);
    void deleteBoard(int boardId);
}
