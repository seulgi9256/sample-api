package com.example.sampleapi.model.board;

import java.time.LocalDateTime;

import com.example.sampleapi.repository.board.entity.BoardEntity;

import org.springframework.data.annotation.Id;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * Board
 */
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Board {
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

	public static Board of(BoardEntity entity){
		return Board.builder()
			.num(entity.getNum())
			.title(entity.getTitle())
			.contents(entity.getContents())
			.writeId(entity.getWriteId())
			.writeName(entity.getWriteName())
			.writeDate(entity.getWriteDate())
			.modifyId(entity.getModifyId())
			.modifyName(entity.getModifyName())
			.modifyDate(entity.getModifyDate())
			.build();


	}
}
