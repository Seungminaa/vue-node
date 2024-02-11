<template>
    <div>
        <button v-on:click="increaseCounter">Add 1</button>
        <p>Counter is : {{counter}}</p>
        <button v-on:click="increaseCounter2(3)">Increase</button>
        <!-- , 로 구분해서 다중호출-->
        <button v-on:click="one(),two()">다중호출</button>
        <select v-model="city" @change="changSelect">
          <option value="1">서울</option>
          <option value="2">부산</option>
          <option value="3">제주</option>
        </select>
    </div>

</template>

<script>
  export default {
      data(){
        return {
          counter : 5,
          city : '1'
        }
      },
      //함수
      methods:{
        increaseCounter(){
          console.log('increaseCounter');
          this.counter = this.counter + parseInt(this.city);
        },
        increaseCounter2(num){
            this.counter = this.counter + num;
        },
        one(){
          alert('one');
        },
        two(){
          alert('two');

        },
        changSelect(){
          alert(this.city);
        }
      },
      //이벤트 변경전 처리
      beforeUpdate(){
        console.log('beforeUpdate');
      },
      //이벤트 변경후 처리
      updated(){
        console.log('updated');

      } 
  }
</script>