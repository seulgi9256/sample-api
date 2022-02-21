package com.example.sampleapi.board.command.repository;

import java.time.LocalDateTime;

import com.example.sampleapi.board.command.domain.Board;

import org.springframework.data.jdbc.repository.query.Modifying;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommandBoardRepository extends CrudRepository<Board, Integer>  {

    @Modifying
    @Query(value="UPDATE board b set b.title = :title , b.contents = :contents , b.modify_id = :modifyId , b.modify_name = :modifyName , b.modify_date = :modifyDate WHERE b.num = :num")
    boolean updateBoard(String title, String contents,String modifyId, String modifyName , LocalDateTime modifyDate,  int num);

}
