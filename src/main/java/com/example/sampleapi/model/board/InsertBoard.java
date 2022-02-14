package com.example.sampleapi.model.board;

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
public class InsertBoard {
   
	private String title;
	private String contents;
    private String writeId;
	private String writeName;	
	private LocalDateTime writeDate;
    
}
