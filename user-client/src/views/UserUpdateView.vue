<template>
<div class="container">
        <h1>회원 정보 수정</h1>
        <div class="row">
            <table>
                <tr>
                    <th>번호</th>
                    <td class="text-center">
                        <input type="number" v-model="userInfo.user_no" readonly>
                    </td>
                </tr>
                <tr>
                    <th>아이디</th>
                    <td class="text-center">
                        <input type="text" v-model="userInfo.user_id" readonly>
                    </td>
                </tr>
                <tr>
                    <th>비밀번호</th>
                    <td class="text-center">
                        <input type="password" v-model="userInfo.user_pwd">
                    </td>
                </tr>
                <tr>
                    <th>이름</th>
                    <td class="text-center">
                        <input type="text" v-model="userInfo.user_name">
                    </td>
                </tr>
                <tr>
                    <th>성별</th>
                    <td class="text-center">
                        <input type="radio" value="M" v-model="userInfo.user_gender">남
                        <input type="radio" value="F" v-model="userInfo.user_gender">여
                    </td>
                </tr>
                <tr>
                    <th>나이</th>
                    <td class="text-center">
                        <input type="number" v-model="userInfo.user_age" min="0" max="150">
                    </td>
                </tr>
                <tr>
                    <th>생성일</th>
                    <td class="text-center">
                        <input type="date" v-model="userInfo.join_date"><!--yyyy-mm-dd-->
                    </td>
                </tr>
            </table>
        </div>
        <div class="row">
            <button class="btn btn-info" @click="updateInfo()">수정</button>
        </div>
    </div>
</template>

<script>
import axios from 'axios'

export default{ 
    data(){
        return{
            userInfo : {
                user_no : null,
                user_id : "",
                user_pwd : "",
                user_name : "",
                user_gender : null,
                user_age : null,
                join_date : null
            }
        };
    },
    created(){
        let searchNo = this.$route.query.userId;
        this.getUserInfo(searchNo);
    },
    methods : {
        async updateInfo() {
            if(!this.validation()) return ;

            let data = this.getSendData();

            await axios.put('/api/user/' + this.userInfo.user_id, data)
                        .then(res => {
                            console.log(res)
                            if(res.changedRows ==0){
                                alert('수정 되지 않았습니다.');
                                this.$router.push({ name: 'userList' });
                            }else{
                                alert('정상적으로 수정되었습니다.');
                                this.$router.push({ name: 'userInfo',query : {"userId" : this.userInfo.user_id} });
                            }
                        })
                        .catch(err => console.log(err))
            
        },
        validation(){
            if(this.userInfo.user_pwd == ""){
                alert('비밀번호가 입력되지 않았습니다.');
                return false;
            }
            if(this.userInfo.user_name == ""){
                alert('이름이 입력되지 않았습니다.');
                return false;
            }

            return true;
        },
        getSendData(){
            let obj = this.userInfo
            let delData = ["user_no","user_id"];
            let newObj = {};

            let isTargeted = null;
            for(let field in obj){
                isTargeted = false;
                for(let target of delData){
                    if(field == target){
                        isTargeted = true;
                        break;
                    }
                }
                if(!isTargeted){
                    newObj[field] = obj[field];
                }
            }
            let sendData = {
                "param" : newObj
            }
            return sendData;
        },
        async getUserInfo(userId){
            let result = await axios.get('/api/user/' + userId)
                                .catch(err => console.log(err))
            let list = result.data;
            this.userInfo = list;
        }
    }
        
}

</script>