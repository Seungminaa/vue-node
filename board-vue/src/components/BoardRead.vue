<template>
    <table id="list">
        <tr>
            <td style="width: 50px;">글제목</td>
            <td>{{board.title}}</td>
        </tr>
        <tr style="height: 200px;">
            <td colspan="2">{{board.content}}</td>
        </tr>
    </table>
    <button style="float: right;" @click="showList">목록</button>
</template>

<script>
export default {
    props: ['board']
    ,
    data() {
        return {
            
        }
    },
    methods : {
        showList(){
      //상세화면 보여주기
      this.$emit('show-list')
    }
    }
}
</script>