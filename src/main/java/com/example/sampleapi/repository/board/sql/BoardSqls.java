package com.example.sampleapi.repository.board.sql;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import com.example.sampleapi.model.board.Board;

import org.apache.commons.lang3.StringUtils;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class BoardSqls {
	

	public static final String DEFAULT_SELECT= """
		SELECT
			*
		FROM
			board
		   
	""";

		
  
	public static final String count = """
		select count(*) from board
	""";
	public static final String SELECT_LIST = """
		SELECT
	        *
	    FROM
	        board
	    ORDER BY num DESC
		LIMIT :pageSize OFFSET :offSet

	""";

	public static final String SEARCH= """
		SELECT
		        *
		    FROM
		        board
		    WHERE 1=1
	""";

	public static final String SEARCH_WHERE="""
		 AND title like :title
		    or contents like :contents
			ORDER BY num DESC
		    
	""";

	
	public static final String SEARCH_BY_PAGING =  """
		SELECT
			*
		FROM
			board
		where title like :search or contents like :search
		ORDER BY num DESC
		LIMIT :pageSize OFFSET :offSet

    """;

	public static final String SEARCH_BY_TEL =  """
        SELECT
            b.*
        FROM
            board b, member m 
		where b.write_id= m.id
		and m.tel = :search
        ORDER BY b.num DESC
		 LIMIT :pageSize OFFSET :offSet

    """;
	
	  
	public static final String SELECT="""
        select *
        from
        	board
        where
        	num = :num
	""";
	
	public static final String BATCH_UPDATE_INSERT_BOARD="""
        INSERT INTO board
        	(title, contents, write_id, write_name, write_date)
        VALUES
        	(:title, :contents, :writeId, :writeName, now())
    """;




	public static String search(Board board) {
		StringBuilder builder = new StringBuilder();
		/* SEARCH = 
			SELECT
				*
			FROM
				board
			WHERE 1=1
		*/
		builder.append(SEARCH);

		if(board.getTitle() !=null) {
			builder.append("""
				and 
					title like :title
				""");
		}
		if(board.getTitle() !=null) {
			builder.append("""
				and 
					contents like :contents
				""");
		}
		builder.append("""
			order by 
				num DESC 
			""");
		return builder.toString();
	}
	
	public static String searchWhere(Board board) {
		log.info("========searchWhere");
		StringBuilder builder = new StringBuilder();
		builder.append("""
			SELECT
                num, title, contents
            FROM
                board
			
				""");
		whereAnd(
			notEmpty(board.getTitle(), "title like :title"),
			notEmpty(board.getContents(), "contents like :contents")			
		);
		builder.append("""
			order by num DESC 
			""");
		return builder.toString();
	}
	
	
	public static String notEmpty(String param, String condition) {
        return StringUtils.isEmpty(param)? null: condition;
    }

    public static String whereAnd(String ... conditions) {
        List<String> finalCond = Arrays.asList(conditions);         
    
        return "WHERE " + finalCond.stream()
        .filter(arg -> arg != null)
        .collect(Collectors.joining("\nAND "));
        
    }
    public static String whereOr(String ... conditions) {
        List<String> finalCond = Arrays.asList(conditions);         
        
        return "WHERE " + finalCond.stream()
        .filter(arg -> arg != null)
        .collect(Collectors.joining("\nOR "));
        
    }
    
}
