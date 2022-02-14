package com.example.sampleapi.model.board;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

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
@Entity
@Table(name = "board")
public class Board {
    @Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int num;	
	private String title;
	private String contents;
	private String writeName;	
	private LocalDateTime writeDate;
	private String modifyName;
	private String writeId;
	private String modifyId;
	private LocalDateTime  modifyDate;
}
