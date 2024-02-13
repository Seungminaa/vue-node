<template>
    <div class="container info">
        <table class="table">
            <tr>
                <th>번호</th>
                <td>{{ boardInfo.no }}</td>
                <th>작성일</th>
                <td>{{ createdDate }}</td>
                <th>이름</th>
                <td>{{ boardInfo.writer }}</td>
            </tr>
            <tr>
                <th colspan="2">제목</th>
                <td colspan="4">{{ boardInfo.title }}</td>
            </tr>
            <tr>
                <td colspan="6" class="content">{{ boardInfo.content }}</td>
            </tr>
        </table>
        <div class="btn-center btnUpdate">
            <button class="btn btn-secondary" @click="goToUpdate(boardInfo.no)">수정</button>
            <button class="btn btn-secondary del" @click="deleteInfo(boardInfo.no)">삭제</button>
        </div>
    </div>
    <Comment />
</template>
<script>
import axios from 'axios'
import Comment from './CommentView.vue'

export default {
    data() {
        return {
            boardInfo: {}
        }
    },
    created() {
        let searchNo = this.$route.query.no;
        this.getBoardInfo(searchNo);
    },
    components: {
        Comment
    },
    computed: {
        createdDate() {
            let result = null;
            if (this.boardInfo.created_date != null) {
                let date = new Date(this.boardInfo.created_date);
                let year = date.getFullYear();
                let month = ('0' + (date.getMonth() + 1)).slice(-2);
                let day = ('0' + date.getDate()).slice(-2);

                result = `${year}-${month}-${day}`
            }
            return result
        }
    },
    methods: {
        async getBoardInfo(no) {
            let result = await axios.get('/api/board/' + no)
                .catch(err => console.log(err));
            let info = result.data;
            this.boardInfo = info;
        },
        goToUpdate(no) {
            this.$router.push({ path: '/boardForm', query: { no } });
        },
        deleteInfo(no){
            axios.delete('/api/board/' + no)
            .then(res => {
                if(res.data.affectedRows != 0){
                    alert(`정상적으로 삭제되었습니다.`);
                    this.$router.push({ path: '/board' });
                }else{
                    alert(`삭제되지 않았습니다.\n메세지 확인필요↓\n${res.data.message}`);
                }
            })
            .catch(err => console.log(err))
        }
    }
}
</script>

<style>
.info th,
.info td {
    border: 1px solid #ccc;
}

.info th {
    background-color: rgb(227, 245, 252);
}

.info {
    margin-top: 10px;
}

.first {
    height: 35px;
}

.title {
    text-align: left;
    padding-left: 10px;
    height: 45px;
}

.form-center {
    text-align: center;
}

.content {
    vertical-align: top;
    text-align: left;
    padding-top: 10px;
    padding-left: 10px;
    height: 300px;
}

.btn-center {
    text-align: center;
}
.btnUpdate{
    padding-bottom: 10px;
}
.del{
    margin-left: 1rem;
}
</style>