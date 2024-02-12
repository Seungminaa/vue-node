<template>
    <div class="container">
        <table class="table">
            <tr>
                <th class="text-center table-primary">번호</th>
                <td class="text-center">{{ boardInfo.no }}</td>
                <th class="text-center table-primary">작성일</th>
                <td class="text-center">{{ createdDate }}</td>
                <th class="text-center table-primary">이름</th>
                <td class="text-center">{{ boardInfo.writer }}</td>
            </tr>
            <tr>
                <th colspan="2" class="text-center table-primary">제목</th>
                <td colspan="4">{{ boardInfo.title }}</td>
            </tr>
            <tr>
                <td colspan="6" class="content">{{ boardInfo.content }}</td>
            </tr>
        </table>
    </div>
</template>
<script>
import axios from 'axios'

export default {
    data() {
        return {
            boardInfo: {}
        }
    },
    components: {
        CommentListView
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
    created() {
        let searchNo = this.$route.query.no;
        this.getBoardInfo(searchNo);
    },
    methods: {
        async getBoardInfo(no) {
            let result = await axios.get('/api/board/' + no)
                .catch(err => console.log(err));
            let info = result.data;
            this.boardInfo = info;
        }
    }
}
</script>

<style></style>