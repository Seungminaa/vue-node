<!--UserInsertView.vue-->
<template>
<div class="container">
        <h1>회원 정보 등록</h1>
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
                        <input type="text" v-model="userInfo.user_id">
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
                        <!-- true-value , false-value , 데이터를 참고할땐 id,name 속성은 거의 안써야함-->
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
            <button class="btn btn-info" @click="insertInfo()">저장</button>
        </div>
    </div>
</template>

<script>
import axios from 'axios'

export default{ 
   data(){
    return {
        userInfo : {
            user_no : null,
            user_id : "",
            user_pwd : "",
            user_name : "",
            user_gender : null,
            user_age : null,
            join_date : null
        }
    }
   },
   created (){
    this.userInfo.join_date = this.dateFormat()
   },
   methods :{
    insertInfo(){
        // 1) 유효성 체크
        if(!this.validation()) return ;
        // 2) ajax
        // 2-1) 실제 보낼 데이터 선별(객체나 배열로 보냄)
        let data = this.getSendData();
        
        // 2-2) axios를 이용해 ajax(json타입)
        axios
        .post('/api/user',data)
        .then(result => {
            // 3) 결과처리
            let user_no = result.data.insertId;
            if(user_no == 0){
                alert(`등록되지 않았습니다.\n메세지를 확인해주세요\n${result.data.message}`)
            }else{
                alert('정상적으로 등록되었습니다.')
                this.userInfo.user_no = user_no;
            }
        })
        .catch(err => console.log(err))

    },
    validation(){
        if(this.userInfo.user_id == ""){
            alert('아이디가 입력되지 않았습니다.');
            return false;
        }
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
        let delData = ["user_no"];
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
    dateFormat(){
        let date = new Date();
        let year = date.getFullYear();
        let month = ('0'+date.getMonth()+1).slice(-2);
        let day = ('0'+date.getDate()).slice(-2);
        return `${year}-${month}-${day}`;
    }
   }
}
</script>