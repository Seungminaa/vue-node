<template>
  <div class="container">
    <table class="table">
      <thead>
        <tr>
          <th scope="col">No.</th>
          <th scope="col">제목</th>
          <th scope="col">작성자</th>
          <th scope="col">작성일자</th>
          <th scope="col">댓글 수</th>
        </tr>
      </thead>
      <tbody>
        <tr v-for="(list, idx) in boardList" v-bind:key="idx" v-on:click="showBoardInfo(list.no)">
          <th scope="row">{{ list.no }}</th>
          <td>{{ list.title }}</td>
          <td>{{ list.writer }}</td>
          <td>{{ getDate(list.created_date) }}</td>
          <td>{{ list.count }}</td>
        </tr>
      </tbody>
    </table>
  </div>
</template>

<script>
import axios from 'axios'

export default {
  data() {
    return {
      boardList: []
    }
  },
  created() {
    this.getBoardList();
  },
  methods: {
    async getBoardList() {
      let result = await axios.get('/api/board')
        .catch(err => console.log(err));
      let list = result.data;
      list
      this.boardList = list;
    },
    showBoardInfo(no) {
      this.$router.push({ path: '/boardInfo', query: {'no' : no}});
    },
    getDate(dates) {
      let res = null;
      if (dates != null) {
        let date = new Date(dates);
        let y = date.getFullYear();
        let m = ('0' + (date.getMonth() + 1)).slice(-2);
        let d = ('0' + date.getDate()).slice(-2);

        res = `${y}-${m}-${d}`
      }
      return res
    }
  }
}
</script>
