<template>
    <div>
        <form>
            <label>
                제품ID : 
                <input type="text" v-model="productInfo.product_id">
            </label>
            <br>
            <label>
                제품명 : 
                <input type="text" v-model="productInfo.product_name">
            </label>
            <br>
            <label>
                카테고리 : 
                <input type="radio" value="A" v-model="productInfo.category"> A
                <input type="radio" value="B" v-model="productInfo.category"> B
            </label>
            <br>
            <button type="button" @click="addCart">추가</button>
        </form>
        <table>
            <thead>
                <caption> total : {{ total }}</caption>
                <tr>
                    <th>카테고리</th>
                    <th>제품ID</th>
                    <th>제품명</th>
                </tr>
            </thead>
            <tbody>
                <tr :key="idx" v-for="(info,idx) in productList">
                    <td>{{ info.category }}</td>
                    <td>{{ info.product_id }}</td>
                    <td>{{ info.product_name }}</td>
                </tr>
            </tbody>
        </table>
    </div>
</template>
<script>

export default{ 
    data(){
        return{
            productInfo: {
                product_id :'',
                product_name : '',
                category : 'A'
            }
        };
    },
    // 연산된 결과를 가지고 있는 옵션
    computed : {
        productList(){
            return this.$store.state.cart;
        },
        total(){
            return this.$store.getters.cartCount;
        }
    },
    methods : {
        addCart(){ // 서버는 데이터로 저장되지만 자바스크립트 내부에는 메모리형태로 저장되어 주소값이 넘어갈수 있음
            //참조타입 객체는 값을 복사하는 개념이 아님/ 새로 변수를 만들어줘야 복사가 됨
            let obj = {
                product_id : this.productInfo.product_id,
                product_name : this.productInfo.product_name,
                category : this.productInfo.category
            }
            // commit : mutation 내부에서 검색
            // this.$store.commit('addProduct',obj);
            // dispatch : actions 내부에서 검색
            this.$store.dispatch('addProduct',obj);
        }
    }
}
</script>
<style scoped>
    table,th,td{
        border: 1px solid black;
    }
</style>