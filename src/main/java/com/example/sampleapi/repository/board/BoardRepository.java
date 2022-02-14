package com.example.sampleapi.repository.board;

import com.example.sampleapi.repository.board.entity.BoardEntity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface BoardRepository extends JpaRepository<BoardEntity, Integer> , BoardNative {

    @Modifying
	@Query(value="UPDATE board b set b.title = :#{#board.title}, b.contents = :#{#board.contents}, b.modify_id = :#{#board.modifyId}, b.modify_name = :#{#board.modifyName}, b.modify_date = now()  WHERE b.num = :#{#board.num}",  nativeQuery= true)
	Integer  updateBoard(@Param("board") BoardEntity board);

 
    
    
}
