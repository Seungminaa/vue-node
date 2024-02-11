let boardList = 
`SELECT no
        , title
        , writer
        , content
        , created_date
        , updated_date
FROM t_board_board
ORDER BY 1`;


module.exports = {
        boardList
}