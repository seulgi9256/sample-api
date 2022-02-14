package com.example.sampleapi.service;
import java.util.List;

import com.example.sampleapi.model.board.Board;
import com.example.sampleapi.repository.board.BoardRepository;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BoardService {
    private final BoardRepository boardRepository;
	public int totalCount()  {
		return  (int) boardRepository.count();
	}

	public  List<Board>  list(  int size , int page) {
		
		PageRequest pageRequest = PageRequest.of((page - 1), size, Sort.Direction.DESC, "num");
		List<Board>  boardList = boardRepository.nativeBoardList(pageRequest);	 
		return boardList;
	}

	public  List<Board>  search(String search)  {
		Board searchBoard= Board.builder()
			.title(search)
			.contents(search)
			.build();
		return boardRepository.nativeSearch(searchBoard);
	}


	 
	public Board view( int num)  {		
		Board board = boardRepository.findById(num).orElse(Board.builder().build());
		return board;

	}

	public Board insert( Board paramBoard){		
		Board board  = boardRepository.save(paramBoard);
		return board;		 
	}

	@Transactional
	public int updateBoard( Board paramBoard) {
		int result = boardRepository.updateBoard(paramBoard);
		return result;
		
	}
 
	public int delete(int num)  {
		boardRepository.deleteById(num);
		return 1;

	}
    
}
