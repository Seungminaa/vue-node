<template>
  <div>
    <input type="text" v-model="todo">
    <button @click="addTodo">추가</button>
  </div>
  <div>
    <ul>
      <li v-for="(d, idx) in todoList" v-bind:key="idx">{{`${d.no} / ${d.text}`}}
        <button @click="delTodo(d.no)">삭제</button>
      </li>
    </ul>
  </div>
</template>
<script>
  export default {
    data(){
      return {
        todo : '', //{no:3,text:'그담할일'},
        todoList : [{no:1, text:'할일1' },{no:2, text:'할일2'}]
      }
    }, 
    methods:{
      addTodo(){
        let no = this.todoList[this.todoList.length -1].no +1;
        let text = this.todo;
        console.log(text)
        this.todoList.push({no, text});
        this.todo = '';

        // let no, text;
        // no = this.todoList.length + 1;
        // text = this.todo;
        // this.todoList.splice(no-1,0,{no,text});
        // this.todo = '';
      },
      delTodo(no){
        console.log('no : ', no);
        this.todoList = this.todoList.filter(todo => todo.no == no ? false : true);
      }
    },

  }
</script>