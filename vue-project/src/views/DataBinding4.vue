<template>
<!-- bind를 이용한 css입히기-->
  <div class="container" v-bind:class="simpleClass">
    Hello
  </div>


<!-- bind class를 boolean 타입으로 css적용-->
  <div class="container" v-bind:class="{active: activeValue, 'text-red':redValue}">
    Hello
  </div>
  <div class="container" v-bind:class="[activeClass, redClass]">
    Hello
  </div>

  <div style="color:red; background:yellow;">
    Style1
  </div>
  <div v-bind:style="{color: att1, background:att2}">
    Style2
  </div>
  <div v-bind:style="colorStyle2">
    Style3
  </div>

</template>

<script>
export default {
  //데이터를 보여주는 부분(instanse 영역)
    data(){
      return{
        simpleClass : 'active text-red',
        activeValue : true,
        redValue : false,
        activeClass : 'active',
        redClass : 'text-red',
        att1 : 'red',
        att2 : 'yellow',
        colorStyle : 'color:red; background:yellow;',
        colorStyle2:{
          color : 'red',
          background : 'yellow'
        }
      }
      //hook이라는 곳에 저장
    },mounted(){ //DOM 조작에 대한 목적
        console.log(this);
      },methods : { // 이벤트 실행함수

      }
}
</script>

<style>
  .container{
    width: 100%;
    height: 50px;
  }
  .active{
    background: yellow;
    font-weight: bold;
  }
  .text-red{
    color: red;
  }
</style>