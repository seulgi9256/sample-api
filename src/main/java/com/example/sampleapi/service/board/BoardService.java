package com.example.sampleapi.service.board;
import java.util.List;

import com.example.sampleapi.model.board.Board;
import com.example.sampleapi.model.board.InsertBoard;
import com.example.sampleapi.model.board.UpdateBoard;
import com.example.sampleapi.repository.board.BoardRepository;
import com.example.sampleapi.repository.board.entity.BoardEntity;

import org.springframework.beans.BeanUtils;
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
		BoardEntity boardEntity = boardRepository.findById(num).orElse(BoardEntity.builder().build());
		return convertBoard(boardEntity);

	}

	public Board insert( InsertBoard insertBoard){		

		// BoardEntity boardEntity= new BoardEntity();
		// BeanUtils.copyProperties(insertBoard, boardEntity);
		
		BoardEntity boardEntity = BoardEntity.of(insertBoard);
		BoardEntity result  = boardRepository.save(boardEntity);
		return convertBoard(result);
	}

	@Transactional
	public int updateBoard( UpdateBoard updateBoard) {
		BoardEntity boardEntity=  BoardEntity.of(updateBoard);		 
		int result = boardRepository.updateBoard(boardEntity);
		return result;
		
	}
 
	public int delete(int num)  {
		boardRepository.deleteById(num);
		return 1;

	}

	private Board convertBoard(BoardEntity boardEntity){
		Board board= new Board();
		BeanUtils.copyProperties(boardEntity, board);
		return board;

	}
    
}
