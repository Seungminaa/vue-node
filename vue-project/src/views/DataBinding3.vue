<template>
    <label>선택 : <input type="checkbox" v-model="checked" true-value="yes" false-value="no"></label>
    <p>선택값 : {{checked}}</p>

    <!-- v-model : value 속성에 바인딩 됨(양방 작용,데이터 반환됨) / v-bind : 바인딩하는 지시자(일방 작용, 데이터 반환안됨)-->
    <label>text속성 : <input type="text" v-bind:value="ival"></label>
    <p>값 : {{ival}}</p>

    <!-- v-bind : attribute의 값을 바인딩-->
    <img v-bind:src="imgSrc" v-bind:width="imgWidth" alt="">
    <input type="text" v-model="textStr">
    
    <!-- textStr 값이 없으면 비활성화 하겟다-->
    <button v-bind:disabled="textStr == ''">Click</button>

</template>
<script>
export default {
  //데이터를 보여주는 부분(instanse 영역)
    data(){
      return{
        checked : 'yes',
        ival : 'sample',
        checkval : true,
        imgSrc : 'https://kr.vuejs.org/images/logo.png',
        imgWidth : '100px',
        isTrue : true,
        textStr : ''
      }
    }
}
</script>
