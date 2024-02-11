<template>
  <div>
    <table id="list">
      <thead>
        <tr>
          <th>글번호</th>
          <th>글제목</th>
          <th>조회수</th>
          <th>삭제</th>
        </tr>
      </thead>
      <tbody>
        <tr v-for="board in list" v-bind:key="board.no">
          <td>{{ board.no }}</td>
          <td @click="showRead(board)">{{ board.title }}</td>
          <td>{{ board.view }}</td>
          <td><button @click="deleteBoard(board.no)">삭제</button></td>
        </tr>
      </tbody>
    </table>
    <button style="float: right;" @click="showWrite">글쓰기</button>
  </div>
</template>

<script>
export default {
  // 부모컨포넌트에서 전달값을 받는 속성
  props: ['list'],
  data() {
    return {};
  },
  methods:{
    // 부모컨포넌트 데이터를 변경
    showWrite(){
      console.log(this.$parent)
      // this.$parent.listView = false;
      // this.$parent.writeView = true;
      this.$emit('show-write')// 부모컨포넌트의 이벤트를 실행하기 위한 호출
    },
    showRead(board){
      //상세화면 보여주기
      this.$emit('show-read',board)
    },
    deleteBoard(no) { //파라미터 전달
      this.$emit('board-delete',no)
    }
  }
}
</script>
