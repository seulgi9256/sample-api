package com.example.sampleapi.board.command.service;
import com.example.sampleapi.board.command.domain.Board;
import com.example.sampleapi.board.command.repository.CommandBoardRepository;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CommandBoardService {
    private final CommandBoardRepository commandBoardRepository;
	

	public Board insert( Board paramBoard){			 
		Board board  = commandBoardRepository.save(paramBoard);
		return board;

		 
	}

	@Transactional
	public int updateBoard( Board paramBoard) {
		
		boolean result = commandBoardRepository.updateBoard(paramBoard.getTitle(), paramBoard.getContents(), paramBoard.getModifyId(), paramBoard.getModifyName(), paramBoard.getModifyDate(), paramBoard.getNum());
		if(result) return 1;
		return 0;
		
	}
 
	public int delete(int num)  {
		commandBoardRepository.deleteById(num);
		return 1;

	}

    
}
