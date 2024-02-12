// 목록
let boardList = 
`select b.no
        , b.title
        , b.writer
        , b.content
        , b.created_date
        , b.updated_date
        , count(c.bno) count
from t_board_board b left join t_comment_board c
on b.no = c.bno
group by c.bno,b.no
order by 1`;

// 단건목록
let boardInfo = 
`select no
        , title
        , writer
        , content
        , created_date
        , updated_date
from t_board_board
where no = ?`;

// 등록
let boardInsert = 
`insert into t_board_board
set ?`;

// 수정
let boardUpdate = 
`update t_board_board
set ?
where no = ?`;

// 삭제
let boardDelete = 
`delete from t_board_board
where no = ?;`

// 댓글 목록
let commentList = 
`select   no
        , writer
        , content
        , created_date
        , updated_date
        , bno
from t_comment_board
where bno = ?
order by 1`;


module.exports = {
    boardList,
    boardInfo,
    boardInsert,
    boardUpdate,
    boardDelete,
    commentList
}