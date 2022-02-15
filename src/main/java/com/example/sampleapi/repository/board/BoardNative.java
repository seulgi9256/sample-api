package com.example.sampleapi.repository.board;

import java.util.List;

import com.example.sampleapi.model.board.Board;

import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

public interface BoardNative {
    
    public List<Board> nativeBoardList(PageRequest pageRequest);
  
    public List<Board> nativeSearch(Board paramBoard);

    public List<Board> nativeSearchWhere(Board paramBoard);
    public Board nativeBoard(int num);

    public PageImpl<Board> nativeSearchByPaging(String search, PageRequest pageRequest);
    public int[] nativeBatchUpdateInsertBoard(final List<Board> boards);
}
