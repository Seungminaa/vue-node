<template>
    <!--select value값-->
    <select v-model="city">
      <option value="02">서울</option>
      <option value="03">부산</option>
      <option value="04">제주</option>
    </select>
    <p>{{city}}</p>
    <!--선택값 false-value : value 값 지정-->
    <label>선택 : <input type="checkbox" v-model="checked" true-value="yes" false-value="no"></label>
    <p>선택값 : {{checked}}</p>

    <label><input type="checkbox" v-model="checkItems" value="01">서울</label>
    <label><input type="checkbox" v-model="checkItems" value="02">부산</label>
    <label><input type="checkbox" v-model="checkItems" value="03">제주</label>
    <label><input type="checkbox" v-model="checkItems" value="04">대전</label>
    <p>선택값 : {{checkItems}}</p>
</template>
<script>
export default {
  //데이터를 보여주는 부분
    data(){
      return{
        city : '04',
        checked : 'yes',
        //배열이 아니면 true, false 로 판단되어 전부 체크됨
        checkItems : []
      }
    }
    // ,
    //   computed : {
    //     orderItems() {
    //       return this.checkItems.sort();
    //     }
    //   }
}
</script>
