package com.example.sampleapi.board.command.domain;

import java.time.LocalDateTime;

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

}
