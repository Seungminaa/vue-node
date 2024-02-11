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


module.exports = {
        boardList
}