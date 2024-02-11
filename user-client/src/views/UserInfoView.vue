<!--UserInfoView.vue-->
<template>
    <div class="container">
        <h1>회원 정보 조회</h1>
        <div class="row">
            <table>
                <tr>
                    <th>번호</th>
                    <td>{{ userInfo.user_no }}</td>
                </tr>
                <tr>
                    <th>아이디</th>
                    <td>{{ userInfo.user_id }}</td>
                </tr>
                <tr>
                    <th>비밀번호</th>
                    <td>{{ userInfo.user_pwd }}</td>
                </tr>
                <tr>
                    <th>이름</th>
                    <td>{{ userInfo.user_name }}</td>
                </tr>
                <tr>
                    <th>성별</th>
                    <td>{{ gen }}</td>
                </tr>
                <tr>
                    <th>나이</th>
                    <td>{{ userInfo.user_age }}</td>
                </tr>
                <tr>
                    <th>생성일</th>
                    <td>{{ joinDate }}</td>
                </tr>
            </table>
        </div>
        <div class="row">
            <button class="btn btn-success col-4" @click="goToUpdate(userInfo.user_id)">수정</button>
            <router-link to="/" class="btn btn-success col-4">목록</router-link>
            <button class="btn btn-success col-4" @click="deleteInfo(userInfo.user_id)">삭제</button>
        </div>
    </div>
</template>

<script>
import axios from 'axios'

export default{
    data(){
        return {
            userInfo : {}
        }
    },
    created(){
        let searchNo = this.$route.query.userId;
        this.getUserInfo(searchNo);
    },
    computed : {
        //성별
        gen() {
            let result = null;

            if(this.userInfo.user_gender=="M"){
                result = "남";
            }else if(this.userInfo.user_gender=="F"){
                result = "여";
            }
            return result;
        },
        //날짜
        joinDate(){
            let result = null;
            if(this.userInfo.join_date != null){
                let date = new Date(this.userInfo.join_date);
                let year = date.getFullYear();
                let month = ('0'+date.getMonth()).slice(-2);
                let day = ('0'+date.getDate()).slice(-2);
                result = `${year}년 ${month}월 ${day}일`
            }
            return result;
        }
    },
    methods : {
        async getUserInfo(userId){
            let result = await axios.get('/api/user/' + userId)
                                .catch(err => console.log(err))
            let list = result.data;
            let newDate = this.dateFormat(list.join_date);
            list.join_date = newDate;
            this.userInfo = list;
        },
        dateFormat(val){
            let result = null;
            if(val != null){
                let date = new Date(val);
                let year = date.getFullYear();
                let month = ('0'+date.getMonth()+1).slice(-2);
                let day = ('0'+date.getDate()).slice(-2);
                result = `${year}-${month}-${day}`;
            }
            return result;
        },
        //수정폼 컨포넌트 호출
        goToUpdate(user_id){
            console.log(user_id)
            //변수명 == 필드명, 변수가 가지고 있는 값이 필드의 값
            // this.$router.push({ name: 'userUpdate', query : {"userId" : user_id}});
            this.$router.push({ name: 'userForm', query : {"userId" : user_id}});
        },
        //서버에 해당 데이터를 삭제
        deleteInfo(user_id){
            console.log(user_id)
            axios.delete('/api/user/' + user_id)
            .then(res => {
                //삭제를 배열의 첫번째 값으로 넘기니까 data 속성의 값이 나오지 않음
                console.log(res)
                if(res.data.affectedRows != 0){
                    alert(`정상적으로 삭제되었습니다.`);
                    // 보내는 것
                    this.$router.push({ name: 'userList' });
                }else{
                    alert(`삭제되지 않았습니다.\n메세지 확인필요↓\n${res.data.message}`);
                }
            })
            .catch(err => console.log(err))

        }
    }
}

</script>