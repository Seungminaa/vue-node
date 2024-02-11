
require('dotenv').config({ path : './db/dbSetting.env'})
const express = require('express');
const app = express();
const mysql = require('./db.js');


app.use(express.json()).use(express.urlencoded({extended : false}));

app.listen(3000, () => {
    console.log('Server Start, http://localhost:3000');
});

// board 목록
app.get('/board', async (req, res) => {
    let list = await mysql.executeQuery('boardList');
    res.json(list);
});

