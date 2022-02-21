package com.example.sampleapi.board.query.dto;

import java.time.LocalDateTime;

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

public class BoardDto {
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
