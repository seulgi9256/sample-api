package com.example.sampleapi.repository.board;
import java.util.List;

import javax.sql.DataSource;

import com.example.sampleapi.model.board.Board;
import com.example.sampleapi.repository.board.sql.BoardSqls;

import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSourceUtils;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class BoardNativeImpl implements BoardNative{
    private   JdbcTemplate jdbcTemplate;
    private NamedParameterJdbcTemplate namedTemplate;
 
    private RowMapper<Board> boardRowMapper = BeanPropertyRowMapper.newInstance(Board.class);


    public BoardNativeImpl(final DataSource dataSource){
        this.jdbcTemplate = new JdbcTemplate(dataSource);
        namedTemplate = new NamedParameterJdbcTemplate(dataSource);        

    }

    @Override
    public List<Board> nativeBoardList(PageRequest pageRequest ) {
        String sql = BoardSqls.SELECT_LIST;
        SqlParameterSource paramSource = new MapSqlParameterSource()
            .addValue("pageSize", pageRequest.getPageSize())
            .addValue("offSet", pageRequest.getOffset());
        List<Board> boardList = namedTemplate.query(sql.toString(),paramSource, boardRowMapper);
        return boardList;
    }
    
    @Override
    public List<Board> nativeSearch(Board paramBoard) {
        log.info("paramBoard: {}", paramBoard.toString());

        String sql = BoardSqls.search(paramBoard);

        log.info(sql);

        SqlParameterSource paramSource = new BeanPropertySqlParameterSource(paramBoard);
        List<Board> boardList = namedTemplate.query(sql, paramSource, boardRowMapper);

        return boardList;
    }
    @Override
    public List<Board> nativeSearchWhere(Board paramBoard) {
        StringBuilder sql = getBuilder(BoardSqls.SEARCH);
        String sqlWhere = BoardSqls.SEARCH_WHERE;
        sql.append(sqlWhere);

        SqlParameterSource paramSource = new BeanPropertySqlParameterSource(paramBoard);
        List<Board> boardList = namedTemplate.query(sql.toString(), paramSource, boardRowMapper);

        return boardList;
        
    }

    @Override
    public PageImpl<Board> nativeSearchByPaging(String search, PageRequest pageRequest) {
        log.info("searchByPaging");
        String sql = BoardSqls.SEARCH_BY_PAGING;        
        SqlParameterSource paramSource = new MapSqlParameterSource()
        .addValue("search", search)
        .addValue("pageSize", pageRequest.getPageSize())
        .addValue("offSet", pageRequest.getOffset());
        List<Board> boardList = namedTemplate.query(sql, paramSource, boardRowMapper);

        return new PageImpl<Board>(boardList, pageRequest, boardList.size());
    }

   

    // @Override
    // public int updateBoard(Board board) {
    //     StringBuilder sql = getBuilder(BoardSqls.UPDATE);
    //     log.info("======= updateBoard sql: {} ", sql);

    //     SqlParameterSource namedParameters = new BeanPropertySqlParameterSource(board);
    //     return namedTemplate.update(sql.toString(), namedParameters);
         
    // }

    @Override
    public int[] nativeBatchUpdateInsertBoard(final List<Board> boards) {
        final SqlParameterSource[] batch = SqlParameterSourceUtils.createBatch(boards.toArray());
        StringBuilder sql = getBuilder(BoardSqls.BATCH_UPDATE_INSERT_BOARD);

        final int[] updateCounts = namedTemplate.batchUpdate(sql.toString(), batch);
        return updateCounts;
    }

    private StringBuilder getBuilder(String sql) {
        log.info("sql: {}", sql);
        return new StringBuilder(sql);

    }

   

   

}
