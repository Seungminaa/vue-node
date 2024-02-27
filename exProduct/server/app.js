require('dotenv').config({ path : './db/dbSetting.env'})
const express = require('express');
const app = express();
const mysql = require('./db.js');


app.use(express.json()).use(express.urlencoded({extended : false}));

app.listen(3000, () => {
    console.log('Server Start, http://localhost:3000');
});

// 목록
app.get('/board', async (req, res) => {
    let list = await mysql.executeQuery('boardList');
    res.json(list);
});

// // 단건목록
// app.get('/board/:no', async (req, res) => {
//     let boardNo = req.params.no;
//     let info = (await mysql.executeQuery('boardInfo', boardNo))[0];
//     res.json(info);
// });

// // 등록
// app.post('/board', async (req, res) => {
//     let data = req.body.param;
//     let result = await mysql.executeQuery('boardInsert', data);
//     res.json(result);
// });

// // 수정
// app.put('/board/:no', async (req, res) => {
//     let result = await updateJson(req);
//     res.json(result);
// });

// // Json 수정
// async function updateJson(request){
//     let data = [ selectedJson(request.body.param), request.params.no];
//     let result = await mysql.executeQuery('boardUpdate', data);
//     return result;
// }

// // 등록할 건수만 확인
// function selectedJson(obj){
//     let delData = ["no"];
//     let newObj = {};
//     let isTargeted = null;
//     for(let field in obj){
//         isTargeted = false;
//         for(let target of delData){
//             if(field == target){
//                 isTargeted = true;
//                 break;
//             }
//         }
//         if(!isTargeted){
//             newObj[field] = obj[field];
//         }
//     }
//     return newObj;
// };

// // 삭제
// app.delete('/board/:no', async (req, res) => {
//     let boardNo = req.params.no;
//     let result = await mysql.executeQuery('boardDelete', boardNo);
//     res.json(result);
// });

// // 댓글 조회
// app.get('/comment/:no', async (req, res) => {
//     let no = req.params.no;
//     let list = await mysql.executeQuery('commentList', no);
//     res.json(list);
// });

//결제정보확인
app.post('/pay', async (req, res) => {
    console.log(req);
    let imp_uid = req.body.imp_uid;
    let merchant_uid = req.body.merchant_uid;
    let s = {imp_uid:imp_uid, merchant_uid:merchant_uid};
    res.json(s);
});
