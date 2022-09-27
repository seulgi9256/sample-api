package com.example.sampleapi.board.command.controller;

import com.example.sampleapi.board.command.domain.Board;
import com.example.sampleapi.board.command.service.CommandBoardService;
import com.example.sampleapi.common.dto.ResultMessage;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/sample-api/v1/boards")
@RequiredArgsConstructor
@Slf4j
public class CommandBoardController {
    private final CommandBoardService commandService;

	@PostMapping()
	public ResponseEntity<ResultMessage> insert( @RequestBody  Board paramBoard) {		
		Board board  = commandService.insert(paramBoard);
        if(board.getNum() > 0){
            return getResponseEntity(1);
        }
		return getResponseEntity(0);
		
	}

	@PutMapping()
	public ResponseEntity<ResultMessage> updateBoard(@RequestBody Board paramBoard) {
		int result = commandService.updateBoard(paramBoard);
		return getResponseEntity(result);
		
	}
	@DeleteMapping("/{num}")
	public ResponseEntity<ResultMessage> delete(@PathVariable("num") int num) {
		int result = commandService.delete(num);
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
	
    
}
