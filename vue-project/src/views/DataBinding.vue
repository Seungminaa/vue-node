<template>
    <h3>Hello{{msg}}</h3>
    <div>{{htmlStr}}</div>
    <!-- v- : vue 지시자 / v-html : html 형식으로 바꿔줌-->
    <div v-html="htmlStr"></div>
    <!-- v-model : 사용자가 입력한 text로 값을 변경시킬수 있음-->
    <input type="text" v-model="vmodel"/>
    <p>{{vmodel}}</p>
    <textarea cols="30" rows="10" v-model="message"></textarea>
</template>
<script>
export default {
  //데이터를 보여주는 부분
    data(){
      return{
        msg : 'World',
        htmlStr : '<p style="color:red;">Hello</p>',
        vmodel : 'Nice',
        message : '여러라인의 값을 \n 읽습니다.'
      }
    }
}
</script>
