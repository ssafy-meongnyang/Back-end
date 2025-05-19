package com.ssafy.meongnyang.api.board.repository;

import com.ssafy.meongnyang.api.board.domain.Board;
import com.ssafy.meongnyang.api.board.dto.BoardListGetResponse;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface BoardRepository {
    void insertBoard(Board board);
    List<BoardListGetResponse> getBoardListWithUser();
}
