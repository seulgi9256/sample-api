package com.example.sampleapi.repository.board.entity;

import java.time.LocalDateTime;

import com.example.sampleapi.model.board.InsertBoard;
import com.example.sampleapi.model.board.UpdateBoard;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Table("board")
public class BoardEntity {
    @Id
    private int num;	
	private String title;
	private String contents;
	private String writeId;
	private String writeName;	
	private LocalDateTime writeDate;
	private String modifyId;
	private String modifyName;	
	private LocalDateTime  modifyDate;


    
    public static BoardEntity of(InsertBoard board){
        return BoardEntity.builder()
            .title(board.getTitle())
            .contents(board.getContents())
            .writeId(board.getWriteId())
            .writeName(board.getWriteName())
            .writeDate(board.getWriteDate())
            .build();
    }
    public static BoardEntity of(UpdateBoard board){
        return BoardEntity.builder()
            .num(board.getNum())
            .title(board.getTitle())
            .contents(board.getContents())
            .modifyId(board.getModifyId())
            .modifyName(board.getModifyName())
            .modifyDate(board.getModifyDate())
            .build();
    }
    
}
