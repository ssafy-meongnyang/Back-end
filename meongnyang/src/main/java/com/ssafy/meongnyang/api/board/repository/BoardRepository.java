package com.ssafy.meongnyang.api.board.repository;

import com.ssafy.meongnyang.api.board.domain.Board;
import com.ssafy.meongnyang.api.board.dto.response.BoardGetResponse;
import com.ssafy.meongnyang.api.board.dto.response.BoardListGetResponse;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface BoardRepository {
    void insertBoard(Board board);
    List<BoardListGetResponse> getBoardListWithUser(@Param("offset") int offset, @Param("size") int size, @Param("category") String category);
    BoardGetResponse getBoardById(Long boardId);
    void updateBoard(Board board);
    Board getBoard(Long boardId);
    void deleteBoard(Long boardId);
    List<BoardListGetResponse> searchBoard(String writer, String title, String content);
    int getBoardCount();
}
