<template>
  <div>
    <HeaderComponent />
    <BoardList v-if="listView" v-bind:list="boardList" @show-write="showWrite" @board-delete="deleteBoard" @show-read="showRead"/>
    <BoardWrite v-if="writeView" v-on:save-board="saveBoard"/>
    <BoardRead v-if="readView" v-bind:board="board" @show-list="showList"/>
    <!-- html 전달-->
    <FooterConponent v-bind:data="htmlData">
      <!--slot을 이용한 html삽입-->
      <template v-slot:footer><p>footer에 들어갈 내용</p></template>
      <template v-slot:header><p>header에 들어갈 내용</p></template>
      <template v-slot:default><p>Hello, World</p></template>
    </FooterConponent> 
  </div>
</template>

<script>
import BoardList from '../components/BoardList.vue'
import HeaderComponent from '../components/HeaderComponent.vue'
import FooterConponent from '../components/FooterConponent.vue'
import BoardWrite from '../components/BoardWrite.vue'
import BoardRead from '../components/BoardRead.vue'

export default {
  data () {
    return {
      listView: true,
      writeView: false,
      readView: false,
      board:{}, //상세화면에 사용할 데이터(파라미터로 받은 데이터)
      boardList: [
        { no: 1, title: 'Vue 노잼', content: 'real no잼', view: 1 },
        { no: 2, title: '노잼 Vue', content: '금요일 너무좋아', view: 2 }
      ],
      htmlData : '<p>Hello</p>'
    }
  },
  components: {
    // import 이름과 동일하지 않을시 지정가능
    BoardList,
    HeaderComponent,
    FooterConponent,
    BoardWrite,
    BoardRead
  },
  methods: {
    showWrite() { //등록화면을 보여주기
      this.listView =false;
      this.writeView = true;
    },
    showList() {
      this.listView =true;
      this.writeView = false;
      this.readView = false;
    },
    saveBoard (title, content){
      console.log(title,content)
      let idx = this.boardList.length -1;
      let no = parseInt(this.boardList[idx].no + 1)
      let board = {no, title, content,view:0}
      this.boardList.splice(this.boardList.length,0,board)
      this.showList();
    },
    showRead(board){
      console.log(board)
      this.listView = false;
      this.writeView = false;
      this.readView = true;
      board.view++;
      this.board = board;
    },
    deleteBoard(no) {
      //반환값이 true인 데이터만 담음
      this.boardList = this.boardList.filter(board => board.no == no ? false : true)
    }
  }
}
</script>
