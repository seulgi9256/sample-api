package com.example.sampleapi.board.query.repository.sql;

public class QueryBoardSqls {

   
	public static final String BOARD_LIST = """
		SELECT
	        num,
            title,
            contents,
            write_id,
            write_name,
            write_date,
            modify_id,
            modify_name,
            modify_date
	    FROM
	        board
	    ORDER BY num DESC
		LIMIT :pageSize OFFSET :offSet
	    """;

	public static final String BOARD_SEARCH= """
		SELECT
            num,
            title,
            contents,
            write_id,
            write_name,
            write_date,
            modify_id,
            modify_name,
            modify_date
        FROM
            board
        WHERE 
            title like :search
            OR contents LIKE :search
        ORDER BY num DESC
	    """;

    public static final String BOARD="""
        SELECT 
            num,
            title,
            contents,
            write_id,
            write_name,
            write_date,
            modify_id,
            modify_name,
            modify_date
        FROM
        	board
        WHERE
        	num = :num
	    """;
    
}
