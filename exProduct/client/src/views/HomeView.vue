<template>
  <div class="container">
    <p>커뮤니티 게시판 입니다</p>
    <p>* 본 게시판은 따뜻한 소통과 자유롭게 정보 공유를 위해 운영되는 페이지 입니다.</p>
    <p>* 욕설, 비방, 광고 등 타인에게 불쾌감을 줄 수 있는 내용은 삼가주시기 바랍니다.</p>
  </div>
  <section class="test">
    <input type="number" placeholder="금액 입력" v-model="price" >
    <div class="btn" @click="PaymentBtn">결제</div>
  </section>
</template>

<script>
import axios from 'axios'

const { IMP } = window;

export default {
  data(){
    return{
      price: 0
    }
  },
  created(){
    document.cookie = "safeCookie1=foo; SameSite=Lax"; //쿠키인데 그닥 필요없는듯?
    document.cookie = "safeCookie2=foo"; //쿠키인데 그닥 필요없는듯?
    document.cookie = "crossCookie=bar; SameSite=None; Secure"; //쿠키인데 그닥 필요없는듯?
  },
  methods: {
    PaymentBtn(){

      IMP.init("imp42374167");

      const data = {
        pg: 'html5_inicis',                           // PG사
        pay_method: 'card',                           // 결제수단
        merchant_uid: `odr_${new Date().getTime()}`,   // 주문번호 
        amount: this.price,                                 // 결제금액
        name: '아임포트 결제 데이터 분석',                  // 주문명
        buyer_name: '홍길동',                           // 구매자 이름
        buyer_tel: '010-8832-4280',                     // 구매자 전화번호
        buyer_email: 'dmg04144@naver.com',               // 구매자 이메일
        buyer_addr: '서울특별시 영등포구 당산동',                    // 구매자 주소
        buyer_postcode: '06018'                      // 구매자 우편번호
      };

      IMP.request_pay(data, rsp => { // callback
        console.log(rsp);
        if (rsp.success) {
          console.log("결제 성공");
        axios({
        url: '/pay',                                   //본인웹서버 route
        method: "post",
        headers: { "Content-Type": "application/json" },
        data: {
          imp_uid: rsp.imp_uid,
          merchant_uid: rsp.merchant_uid
        }
      }).then((data) => {
        // 서버 결제 API 성공시 로직
        console.log(data);
      })
        } else {
          console.log("결제 실패");
        }
      });

    }
  }
}
</script>


<style>
  .test{ display:flex; justify-content: center; align-items: center; height:100px; }
  .btn{ width: 200px; height:40px; background-color:#ffffff; border:1px #a8a8a8 solid; color:black; display:flex; align-items: center; justify-content: center; cursor:pointer; }
</style>
