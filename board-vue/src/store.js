// store.js
import { createStore } from "vuex";
import createPersistedState from "vuex-persistedstate";

// createStore 를 통과한 결과물이 나와야함
export default createStore({
    state(){
        return {
            // CRUD는 가능하지만 직접적인 제한은 못함
            cart : [
                {
                    product_id : 1,
                    product_name : '아이폰 거치대',
                    category : 'A'
                }
            ],
            count : 0
        }
    },
    getters : {
        //this를 사용하지 않음, 매개변수로 데이터를 받음
        cartCount : (state) => {
            return state.cart.length;
        }
    },
    mutations : {
        // state 는 무조건 들어가는 대상으로 매개변수로 취급 안함
        increment(state) {
            state.count++;
        },
        addProduct(state,info){
            state.cart.push(info);
        }
    },
    actions : {
        addProduct(context, info){
            context.commit('addProduct',info);
            setTimeout(()=>{
                context.commit('increment');
            },1000);
        }
    },
    //플러그인을 사용해서 새로고침 삭제 안되도록 수정
    //npm install vuex-persistedstate
    plugins : [ createPersistedState() ]
})