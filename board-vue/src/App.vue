<template>
  <nav>
    <h1>{{ getToday }}</h1>
    <router-link to="/">Home</router-link> |
    <router-link to="/about">About</router-link> |
    <router-link to="/board">게시판</router-link> |
    <router-link to="/todo">todo</router-link> |
    <router-link to="/store">store</router-link>
  </nav>
  <router-view/>
</template>

<script>
//npm install vuex@next
//component별 적용
// import mix from '../src/mixins.js'

export default {
  // mixins : [mix],
  data(){
    return{
      rootData:'Hello' // this.rootData
    }
  },
  // 데이터 내보내기(provide,inject / 읽기전용)
  provide(){
    return {
      rootList:'Hello',
      rootStr:'Hi'
    }
  },
  // 값으로 처리 /반드시 return 값이 필요 / 데이터 옵션과 사용 / 변경 안됨 /readonly 상태
  computed : {
    getToday() {
      // mixin이 가지고 있는 숨겨진 데이터
      return this.$getToday('yyyy/MM/dd');
    }
  },
  mounted(){
    console.log("App.vue mounted");
  }
}
</script>

<style>
@import url("../src/assets/style.css");
#app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
}

nav {
  padding: 30px;
}

nav a {
  font-weight: bold;
  color: #2c3e50;
}

nav a.router-link-exact-active {
  color: #42b983;
}
</style>
