package com.example.sampleapi.board.query.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.sampleapi.board.query.dto.BoardDto;
import com.example.sampleapi.board.query.service.QueryBoardService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/sample-api/v1/boards")
@RequiredArgsConstructor
@Tag(name = "게시판", description = "게시판")
@Slf4j
public class QueryBoardController {
    private final QueryBoardService queryService;

	@Operation(summary = "게시믈 전체건수 조회", description = "게시믈 전체건수를 조회합니다.")
    @GetMapping("/totalCount")
	public int totalCount() {
		return  (int) queryService.totalCount();
	}

	@Operation(summary = "게시판 목록조회", description = "게시판 목록을 페이지로 조회합니다.")
    @GetMapping("/list")
	public  List<BoardDto>  boardList(@RequestParam(value="size", defaultValue = "10")  int size , @RequestParam(value="page", defaultValue = "1") int page) {
		log.info("boardList:size-{},page-{}",size,page);
		List<BoardDto> boardList = queryService.boardList(size, page);
		return boardList;
	}

	@Operation(summary = "게시판 검색", description = "게시판을 검색합니다.")
	@GetMapping("/search")
	public  List<BoardDto>  boardSearch(@RequestParam("search") String search) {
		return queryService.boardSearch(search+"%");
	}


	@Operation(summary = "게시판 조회", description = "해당 게시물을 조회합니다.")
	@GetMapping("/{num}")
	public BoardDto board(@Parameter(description="게시물 번호") @PathVariable("num") int num) {		
		BoardDto boardDto = queryService.board(num);
		return boardDto;
	}

    
}
