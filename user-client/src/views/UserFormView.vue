<!--UserFormView.vue-->
<template>
    <div class="container">
            <h1 class="text-center">{{title}}</h1>
            <div class="row">
                <table class="table">
                    <tr>
                        <th class="text-right table-primary">번호</th>
                        <td class="text-center">
                            <input class="form-control" type="number" v-model="userInfo.user_no" readonly>
                        </td>
                    </tr>
                    <tr>
                        <th>아이디</th>
                        <td class="text-center">
                            <input type="text" v-model="userInfo.user_id" v-bind:readonly="isUpdated">
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
                <!--첫번째-->
                <!-- <button class="btn btn-info" @click="saveInfo(searchNo)">저장</button> -->
                <!--두번째-->
                <button class="btn btn-info" @click="isUpdated ? updateInfo() : insertInfo()">저장</button>
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
            },
            searchNo : null, // 등록 or 수정
            isUpdated : false
        }
    },
    computed : {
        title(){
            return this.isUpdated ? '회원 정보 수정' : '회원 정보 등록';
        }
    },
    created() {
        // this.searchNo = this.$route.query.id;
        this.searchNo = this.$route.query.userId;
        if(this.searchNo != null && this.searchNo != undefined){
            this.getUserInfo();
        }else{
            this.userInfo.join_date = this.getDate('');
        }
    },
    methods : {
        async getUserInfo(){
            let result = await axios.get(`/api/user/${this.searchNo}`)
                                .catch(err => console.log(err))
            let list = result.data;
            // 날짜처리 필요
            
            let newDate = this.getDate(list.join_date);
            list.join_date = newDate;
            
            this.userInfo = list;
            this.isUpdated = true;
        },
        saveInfo(id){ //첫번째
            // 1) 보낼 데이터 산출
            let info = this.getSendInfo(id);

            // 2) ajax
            axios(info)
            .then(result => {
                let count = result.data.affectedRows;
                if(count ==0){
                    alert('저장되지 않았습니다. 내용을 확인해주세요')
                }else{
                    alert('저장되었습니다.')
                    if(result.data.insertId > 0){ //등록일 경우 추가작업
                        this.userInfo.user_no  = result.data.insertId;
                    }
                }
                console.log(result);
            })
            .catch(err => console.log(err))

        },
        getSendInfo(id){
            // method, url, data
            let method = '';
            let url = '';
            let data = null;

            if(id == null || id == undefined){ //등록
                method = 'post';
                url = `/api/user`;
                data = {
                    "param" : {
                        user_id : this.userInfo.user_id,
                        user_pwd : this.userInfo.user_pwd,
                        user_name : this.userInfo.user_name,
                        user_gender : this.userInfo.user_gender,
                        user_age : this.userInfo.user_age,
                        join_date : this.userInfo.join_date
                    }
                };
            }else{ //수정
                method = 'put';
                url = `/api/user/${id}`;
                data = {
                    "param" : {
                        user_pwd : this.userInfo.user_pwd,
                        user_name : this.userInfo.user_name,
                        user_gender : this.userInfo.user_gender,
                        user_age : this.userInfo.user_age,
                        join_date : this.userInfo.join_date
                    }
                };
            }
            return {
                method,
                url,
                data
            }

        },
        insertInfo(){
            if(!this.validation()) return;

            let data = {
                "param" :  {
                        user_id : this.userInfo.user_id,
                        user_pwd : this.userInfo.user_pwd,
                        user_name : this.userInfo.user_name,
                        user_gender : this.userInfo.user_gender,
                        user_age : this.userInfo.user_age,
                        join_date : this.userInfo.join_date
                    }
            }

            axios
            .post('/api/user', data)
            .then(result => {
                let user_no = result.data.insertId;
                if(user_no == 0){
                    alert(`등록되지 않았습니다.\n내용을 확인해주세요`)
                }else{
                    alert(`정상적으로 등록되었습니다.`);
                    this.userInfo.user_no = user_no;
                    this.$router.push({ name: 'userInfo',query : {"userId" : this.userInfo.user_id} });
                }
            })
            .catch(err => console.log(err));        

        },
        updateInfo(){
            if(!this.validation()) return;

            let data = {
                "param" : {
                        user_pwd : this.userInfo.user_pwd,
                        user_name : this.userInfo.user_name,
                        user_gender : this.userInfo.user_gender,
                        user_age : this.userInfo.user_age,
                        join_date : this.userInfo.join_date
                    }
            };

            axios
            .put(`/api/user/${this.userInfo.user_id}`, data)
            .then(result => {
                let count = result.data.changedRows;
                if(count == 0){
                    alert(`수정되지 않았습니다.\n내용를 확인해주세요`)
                }else{
                    alert(`정상적으로 수정되었습니다.`);
                }                   
            })
            .catch(err => console.log(err));        

        },
        validation(){
            if(this.userInfo.user_id == "" && !this.isUpdated){
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
        getDate(value){
            if(value == null) return null;
        
            let date = value == ''? new Date() : new Date(value);
            let year = date.getFullYear();
            let month = ('0'+(date.getMonth()+1)).slice(-2);
            let day = ('0'+date.getDate()).slice(-2);
            return `${year}-${month}-${day}`;
        }
    }
}
</script>