package com.example.sampleapi.controller;
import java.util.List;

import com.example.sampleapi.model.board.Board;
import com.example.sampleapi.model.common.ResultMessage;
import com.example.sampleapi.service.BoardService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/sample-api/v1/boards")
@Slf4j
@RequiredArgsConstructor
public class BoardController {
	private final BoardService boardService;


	@GetMapping(value = "/totalCount")
	public int totalCount() {
		return  (int) boardService.totalCount();
	}

    @GetMapping()
	public  List<Board>  list(@RequestParam(value="size", defaultValue = "10")  int size , @RequestParam(value="page", defaultValue = "1") int page) {
		List<Board> boardList = boardService.list( size, page);
		return boardList;
	}

	@GetMapping("/search")
	public  List<Board>  search(@RequestParam("search") String search) {
		return boardService.search(search+"%");
	}


	@GetMapping("/{num}")
	public Board view(@PathVariable("num") int num) {		
		Board board = boardService.view(num);
		return board;

	}

	@PostMapping()
	public ResponseEntity<ResultMessage> insert( @RequestBody  Board paramBoard) {		
		Board board  = boardService.insert(paramBoard);
		return getResponseEntity(board);
		
	}

	@PutMapping()
	public ResponseEntity<ResultMessage> updateBoard(@RequestBody Board paramBoard) {
		int result = boardService.updateBoard(paramBoard);
		return getResponseEntity(result);
		
	}
	@DeleteMapping("/{num}")
	public ResponseEntity<ResultMessage> delete(@PathVariable("num") int num) {
		int result = boardService.delete(num);
		return getResponseEntity(result);

	}

	private ResponseEntity<ResultMessage> getResponseEntity(int result) {
		if(result > 0) {		
			ResultMessage resultMessage= ResultMessage.builder().successYn("Y").message("정상").build();		 
			return ResponseEntity.ok(resultMessage);
		}	
		ResultMessage resultMessage= ResultMessage.builder().successYn("N").message("오류").build();			
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(resultMessage);	
	}
	private ResponseEntity<ResultMessage> getResponseEntity(Board  board) {
		if(board.getNum() > 0) {	
			ResultMessage resultMessage= ResultMessage.builder().successYn("Y").message("정상").build();			 
			return ResponseEntity.ok(resultMessage);
		}		
		ResultMessage resultMessage= ResultMessage.builder().successYn("N").message("오류").build();		
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(resultMessage);	
	}
    
}
