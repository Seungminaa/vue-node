<!-- UserListView.vue-->
<template>
    <div class="container">
        <h1>전체 회원 조회</h1>
        <table class="table">
            <caption>Total : {{ count }}</caption>
            <thead>
                <tr>
                    <th>No</th>
                    <th>ID</th>
                    <th>password</th>
                    <th>이름</th>
                    <th>성별</th>
                    <th>나이</th>
                    <th>가입날짜</th>
                </tr>
            </thead>
            <tbody>
                <!-- template : v-for 사용시 v-if 사용가능 / 표준태그이자 랜더링 되지 않는 태그-->
                <tr v-for="(list,idx) in userList" :key="idx" @click="goToUserInfo(list.user_id)">
                    <td>{{list.user_no }}</td>
                    <td>{{list.user_id }}</td>
                    <td>{{list.user_pwd }}</td>
                    <td>{{list.user_name }}</td>
                    <td>{{list.user_gender }}</td>
                    <td>{{list.user_age }}</td>
                    <!-- text 디렉티브로 사용가능-->
                    <td v-text="list.join_date" />
                </tr>
            </tbody>
        </table>
    </div>

</template>

<script>
import axios from 'axios'

export default {
    data() {
        return{
            userList : []
        }
    },
    //값 / 기존의 있는 값으로 새로운 값을 생성
    computed : {
        count(){
            return this.userList.length;
        }
    },
    //와챠, 이미 존재하는 데이터를 감시, return이 없음, 프로세스 : 감시를 해서 값을 다루는것이 아닌 동작순서를 나타냄
    watch : {
        userList(newQuestion,oldQuestion){
            console.log(oldQuestion)
            console.log('데이터가 변경되었습니다.')
            console.log(newQuestion)
        }
    },
    // 데이터가 생성되기 전에 처리되어야할 부분
    created(){
        this.getUserList(); // 비동기작업
    },
    // v-on 수식어있음, 확인필요
    methods : {
        async getUserList(){
            //동기작업
            let result = await axios.get('/api/user')
                                .catch(err => console.log(err))
            let list = result.data;
            this.userList = list;
        },
        //v-on 이벤트는 methods에서 찾음
        goToUserInfo(user_id){
            //등록된 라우터에 대해 요청 / 무조건 get방식
            //path나 name형태로 router에서 찾을수 있음
            this.$router.push({ name: 'userInfo', query : {"userId" : user_id}});
        }
    }
}
</script>
