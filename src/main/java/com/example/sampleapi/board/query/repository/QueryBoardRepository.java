package com.example.sampleapi.board.query.repository;

import java.util.List;

import com.example.sampleapi.board.query.dto.BoardDto;
import com.example.sampleapi.board.query.repository.sql.QueryBoardSqls;

import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QueryBoardRepository extends CrudRepository<BoardDto, Integer> {

    @Query(QueryBoardSqls.BOARD_LIST)
    List<BoardDto> boardList(int pageSize, long offSet );

    @Query(QueryBoardSqls.BOARD_SEARCH)
    List<BoardDto> boardSearch(String search);

    @Query(QueryBoardSqls.BOARD)
    BoardDto  board(int num);

}
