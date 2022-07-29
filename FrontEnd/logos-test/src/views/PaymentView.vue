<template>
  <b-container>
    <b-row class="mid-container">
      <b-col cols="6" md="4">
        <p>결제하기 테스트</p>
        <div class="purchase-header">
          <a
            ><span><span>로고스</span> <span>LP</span> 구매 창입니다.</span></a
          >
        </div>
      </b-col>
      <b-col cols="12" md="8"></b-col>
    </b-row>
    <b-row class="mid-container">
      <b-col cols="6" md="4">결제 금액</b-col>
      <b-col cols="12" md="8">
        <b-row>
          <b-col>
            <div class="price-point active">
              <div class="rp">
                <span class="amount">1000</span
                ><span class="name"><span>LP</span></span>
              </div>
              <div class="price"><span>1,000원</span></div>
            </div>
          </b-col>
          <b-col>
            <div class="price-point">
              <div class="rp">
                <span class="amount">5000</span
                ><span class="name"><span>LP</span></span>
              </div>
              <div class="price"><span>5,000원</span></div>
            </div>
          </b-col>
          <b-col>
            <div class="price-point">
              <div class="rp">
                <span class="amount">10000</span
                ><span class="name"><span>LP</span></span>
              </div>
              <div class="price"><span>10,000원</span></div>
            </div>
          </b-col>
          <b-col>
            <div class="price-point">
              <div class="rp">
                <span class="amount">30000</span
                ><span class="name"><span>LP</span></span>
              </div>
              <div class="price"><span>30,000원</span></div>
            </div>
          </b-col>
          <b-col>
            <div class="price-point">
              <div class="rp">
                <span class="amount">50000</span
                ><span class="name"><span>LP</span></span>
              </div>
              <div class="price"><span>50,000원</span></div>
            </div>
          </b-col>
        </b-row>
      </b-col>
    </b-row>
    <b-row class="mid-container">
      <b-col cols="6" md="4">결제 수단</b-col>
      <b-col cols="12" md="8">
        <b-row>
          <b-col>
            <label
              ><input
                type="radio"
                title="카카오페이"
                name="mobile-payment"
                value="kakaopay" /><img
                src="data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAACEAAAAOCAYAAAChHnWMAAACt0lEQVRIS8WVbSzVURjAf9fl0tIiVqx2a9X0odVk2WqsLjNJq1F6G1u0jFVrFCaJEak2mdbaWmqTbEV5mYkrXJPCfLi9UKQINSV96ItV9639na64966tzfJ8Ov/nec5zfud5OX8Z00SpxLW4kEyVPxHA0um2WVqPj47REpdISk0Dg+aYMvOi4ByBCfHUAk6zdODfwhjVGk6G7KNQcpqEyD6F79lU2gH5fwCYOiKvkPi0HK5LEHb6T3yQy/E0W/UGKCmD6jowGCA4AI5EwbzfOSq9D87zQa0Bkwny0sFlIRTfhYeNsEIJGUkwNg73qiD5mLjuxStwIBxWLp/i0AfsxVOWeJSQy1nUmdU/deAbBC9ezcyJchl0P4YFzrDjIDS1QtBWaGiBjd7Q+ABW+4LPetC0gcofYqNgdzQMPxOwSm9Ql0Ow6k/slqfkyzRVVKr8CDOri+5AbKLtoiTEQUGOgOgfgDedcOI0FJXCxDD09EJPH9y4DdpuGNKCuxdkp4JCASlZ8G0AnBxnxB+Qmb6gBbzN6qRMyL9mG2JXCFSXCIi3g9DXAenn4dJVaK0Bv1AIC4WhEXg/DGO9EH4IRj6C0Qheq6DsplXsCSuI3n5Y6y82WUp9GWwLEBCSX1MlbNkJbq5wOBIyLkDXI5Au0tYhILq0sHk7yGTQqRals5AJq3JIDhW1EBEj6ijJIhfIPQPx0eJbglA3g5MTuLtBUwU4KsAnEL7/gA3rYPQzvG4HowHcvMQYfu0He3sriAGrxpRc6pshMg7Kb4kUeiyeudncEz1PQG4HdnYisDRVJiM4OIi1vRwGh2HNJshNg+Tj1tmdbExbI9r/DjyWiEmwJXtiRE88b7Ftn64N2S/642WrgLMQMaKS8l8fK50OpEoprINanaLTi/6SymUp0x+rSducP9tmwrn6gf0CYUX7e8900REAAAAASUVORK5CYII="
                alt="kakaopay"
            /></label>
          </b-col>
          <b-col>
            <label
              ><input
                type="radio"
                title="토스"
                name="mobile-payment"
                value="pay1q-toss" /><img
                src="data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAACEAAAANCAIAAACo6JB1AAAAGXRFWHRTb2Z0d2FyZQBBZG9iZSBJbWFnZVJlYWR5ccllPAAAA3FpVFh0WE1MOmNvbS5hZG9iZS54bXAAAAAAADw/eHBhY2tldCBiZWdpbj0i77u/IiBpZD0iVzVNME1wQ2VoaUh6cmVTek5UY3prYzlkIj8+IDx4OnhtcG1ldGEgeG1sbnM6eD0iYWRvYmU6bnM6bWV0YS8iIHg6eG1wdGs9IkFkb2JlIFhNUCBDb3JlIDUuNi1jMTQyIDc5LjE2MDkyNCwgMjAxNy8wNy8xMy0wMTowNjozOSAgICAgICAgIj4gPHJkZjpSREYgeG1sbnM6cmRmPSJodHRwOi8vd3d3LnczLm9yZy8xOTk5LzAyLzIyLXJkZi1zeW50YXgtbnMjIj4gPHJkZjpEZXNjcmlwdGlvbiByZGY6YWJvdXQ9IiIgeG1sbnM6eG1wTU09Imh0dHA6Ly9ucy5hZG9iZS5jb20veGFwLzEuMC9tbS8iIHhtbG5zOnN0UmVmPSJodHRwOi8vbnMuYWRvYmUuY29tL3hhcC8xLjAvc1R5cGUvUmVzb3VyY2VSZWYjIiB4bWxuczp4bXA9Imh0dHA6Ly9ucy5hZG9iZS5jb20veGFwLzEuMC8iIHhtcE1NOk9yaWdpbmFsRG9jdW1lbnRJRD0ieG1wLmRpZDoxNGM3NTI2ZS1kZTQ3LWIxNDgtOWM5ZS02NzhhYzMwOGI4OWUiIHhtcE1NOkRvY3VtZW50SUQ9InhtcC5kaWQ6MkQwQTc5MEQxNUZEMTFFOEI4QTBCM0FCRjM1NURFQzEiIHhtcE1NOkluc3RhbmNlSUQ9InhtcC5paWQ6MkQwQTc5MEMxNUZEMTFFOEI4QTBCM0FCRjM1NURFQzEiIHhtcDpDcmVhdG9yVG9vbD0iQWRvYmUgUGhvdG9zaG9wIENDIChXaW5kb3dzKSI+IDx4bXBNTTpEZXJpdmVkRnJvbSBzdFJlZjppbnN0YW5jZUlEPSJ4bXAuaWlkOjE0Yzc1MjZlLWRlNDctYjE0OC05YzllLTY3OGFjMzA4Yjg5ZSIgc3RSZWY6ZG9jdW1lbnRJRD0ieG1wLmRpZDoxNGM3NTI2ZS1kZTQ3LWIxNDgtOWM5ZS02NzhhYzMwOGI4OWUiLz4gPC9yZGY6RGVzY3JpcHRpb24+IDwvcmRmOlJERj4gPC94OnhtcG1ldGE+IDw/eHBhY2tldCBlbmQ9InIiPz65hovaAAACtUlEQVR42mLM6b9/6MInBhxgfZuakhQHA2WA6fqD73ikKbcACBgv3fkKsoqJ4dbjHw3zngDZ3pYCUa4i//8zsLEyqstxUm4Hi64yF4Q1f9trCCPSRURHCSq4+/THvWc/vnz3W4iPxc6Az99GEK7zw5e/e8583HHyw9+//9VkOdL9xYFqgOKfvv7ddPT9mRtfGRkYlKXZMwLEGf8DHQwGLgXXX3/4jRwHvuU3Hr38hewibUXOZfWqIAs+/3EuuP7nL7Lk/+MzdN58/APUBQweuKioAAsTVP4/A8QCUQFWiAVAKyEWmGny+FgJ8nMzA9lX739fvPMNkJE/6SHQAkbG/6m+ov42AhrynDv7NLk4mFoWAkObkZOdMT9UwkqXx1CNe12rGgvEjmuwmNdRAkXAvWc/IFZWxUqHOwtDQsY+5yqQceTSp1h3kc/fgF74z8gIirCcYAm4qznYgI7+z8PJoq/CleQtCk1XEOrq/W+w0ADFRPfy5xAuxAIgEOBhhmpgAoVDZ6YckPz373/J1If6CZfyJz4AxgpQREGSHUgC3ZfUfhco3rHkGcKOK/egdugogvzx5Rs0pIEegjBOX/8CYRiqghyhKsNxZJp2cYSktCgbkHvg/Kfle98CGUXhktu6NVJ8xSBhu3zPG2Dkw+y4Dw8rkBHwJDth1Yv95z4t3vE6u+8+xBOx7qAQyOm7v3TXmzgP0SmFChCVv37///Xnf1jtrQu3v+YGS5RESkHE//z9D0pXQE9EN90B8tlYGE/P0YXIAX2KmdIXVCkDo7F02qNdpz6gSV1coIcpzsfNfHCKNijO7z77KSnMxsrCCAkouB5g2i+f/ggSk+3pskAvigmyArkZ/mJ6ylwTVz///ec/MzNjY5KMr7UgRFxLgQPodSAbmMYak2VN1LmB0YfIH7QDAAEGADNbElCBxO/nAAAAAElFTkSuQmCC"
                alt="toss"
            /></label>
          </b-col>
          <b-col>
            <label
              ><input
                type="radio"
                title="페이코"
                name="mobile-payment"
                value="pay1q-toss" /><img
                src="data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAACEAAAANCAIAAACo6JB1AAAAGXRFWHRTb2Z0d2FyZQBBZG9iZSBJbWFnZVJlYWR5ccllPAAAA3FpVFh0WE1MOmNvbS5hZG9iZS54bXAAAAAAADw/eHBhY2tldCBiZWdpbj0i77u/IiBpZD0iVzVNME1wQ2VoaUh6cmVTek5UY3prYzlkIj8+IDx4OnhtcG1ldGEgeG1sbnM6eD0iYWRvYmU6bnM6bWV0YS8iIHg6eG1wdGs9IkFkb2JlIFhNUCBDb3JlIDUuNi1jMTQyIDc5LjE2MDkyNCwgMjAxNy8wNy8xMy0wMTowNjozOSAgICAgICAgIj4gPHJkZjpSREYgeG1sbnM6cmRmPSJodHRwOi8vd3d3LnczLm9yZy8xOTk5LzAyLzIyLXJkZi1zeW50YXgtbnMjIj4gPHJkZjpEZXNjcmlwdGlvbiByZGY6YWJvdXQ9IiIgeG1sbnM6eG1wTU09Imh0dHA6Ly9ucy5hZG9iZS5jb20veGFwLzEuMC9tbS8iIHhtbG5zOnN0UmVmPSJodHRwOi8vbnMuYWRvYmUuY29tL3hhcC8xLjAvc1R5cGUvUmVzb3VyY2VSZWYjIiB4bWxuczp4bXA9Imh0dHA6Ly9ucy5hZG9iZS5jb20veGFwLzEuMC8iIHhtcE1NOk9yaWdpbmFsRG9jdW1lbnRJRD0ieG1wLmRpZDoxNGM3NTI2ZS1kZTQ3LWIxNDgtOWM5ZS02NzhhYzMwOGI4OWUiIHhtcE1NOkRvY3VtZW50SUQ9InhtcC5kaWQ6MkQwQTc5MEQxNUZEMTFFOEI4QTBCM0FCRjM1NURFQzEiIHhtcE1NOkluc3RhbmNlSUQ9InhtcC5paWQ6MkQwQTc5MEMxNUZEMTFFOEI4QTBCM0FCRjM1NURFQzEiIHhtcDpDcmVhdG9yVG9vbD0iQWRvYmUgUGhvdG9zaG9wIENDIChXaW5kb3dzKSI+IDx4bXBNTTpEZXJpdmVkRnJvbSBzdFJlZjppbnN0YW5jZUlEPSJ4bXAuaWlkOjE0Yzc1MjZlLWRlNDctYjE0OC05YzllLTY3OGFjMzA4Yjg5ZSIgc3RSZWY6ZG9jdW1lbnRJRD0ieG1wLmRpZDoxNGM3NTI2ZS1kZTQ3LWIxNDgtOWM5ZS02NzhhYzMwOGI4OWUiLz4gPC9yZGY6RGVzY3JpcHRpb24+IDwvcmRmOlJERj4gPC94OnhtcG1ldGE+IDw/eHBhY2tldCBlbmQ9InIiPz65hovaAAACtUlEQVR42mLM6b9/6MInBhxgfZuakhQHA2WA6fqD73ikKbcACBgv3fkKsoqJ4dbjHw3zngDZ3pYCUa4i//8zsLEyqstxUm4Hi64yF4Q1f9trCCPSRURHCSq4+/THvWc/vnz3W4iPxc6Az99GEK7zw5e/e8583HHyw9+//9VkOdL9xYFqgOKfvv7ddPT9mRtfGRkYlKXZMwLEGf8DHQwGLgXXX3/4jRwHvuU3Hr38hewibUXOZfWqIAs+/3EuuP7nL7Lk/+MzdN58/APUBQweuKioAAsTVP4/A8QCUQFWiAVAKyEWmGny+FgJ8nMzA9lX739fvPMNkJE/6SHQAkbG/6m+ov42AhrynDv7NLk4mFoWAkObkZOdMT9UwkqXx1CNe12rGgvEjmuwmNdRAkXAvWc/IFZWxUqHOwtDQsY+5yqQceTSp1h3kc/fgF74z8gIirCcYAm4qznYgI7+z8PJoq/CleQtCk1XEOrq/W+w0ADFRPfy5xAuxAIgEOBhhmpgAoVDZ6YckPz373/J1If6CZfyJz4AxgpQREGSHUgC3ZfUfhco3rHkGcKOK/egdugogvzx5Rs0pIEegjBOX/8CYRiqghyhKsNxZJp2cYSktCgbkHvg/Kfle98CGUXhktu6NVJ8xSBhu3zPG2Dkw+y4Dw8rkBHwJDth1Yv95z4t3vE6u+8+xBOx7qAQyOm7v3TXmzgP0SmFChCVv37///Xnf1jtrQu3v+YGS5RESkHE//z9D0pXQE9EN90B8tlYGE/P0YXIAX2KmdIXVCkDo7F02qNdpz6gSV1coIcpzsfNfHCKNijO7z77KSnMxsrCCAkouB5g2i+f/ggSk+3pskAvigmyArkZ/mJ6ylwTVz///ec/MzNjY5KMr7UgRFxLgQPodSAbmMYak2VN1LmB0YfIH7QDAAEGADNbElCBxO/nAAAAAElFTkSuQmCC"
                alt="toss"
            /></label>
          </b-col>
        </b-row>
      </b-col>
    </b-row>
    <b-row class="mid-container">
      <b-col cols="6" md="4"
        >로고스 가상 재화 정책 동의 및 결제 전 주의사항</b-col
      >
      <b-col cols="12" md="8">
        <div class="notice-box">
          <ul>
            <li>
              결제를 위해서는 카카오톡 또는 카카오페이 모바일 앱이 필요합니다.
            </li>
            <li>카카오페이 고객센터 : 1644-7405</li>
          </ul>
          <div class="controls">
            <div>
              <a href="#/">상품,가격 및 유효기간</a
              ><span>을 확인하였으며, </span
              ><a
                href="https://legal.kr.riotgames.com/league/payment"
                target="_blank"
                rel="noopener noreferrer"
                >계약 관련 고지 사항</a
              ><span>과 </span
              ><a
                href="https://legal.kr.riotgames.com/league/rp"
                target="_blank"
                rel="noopener noreferrer"
                >리그 오브 레전드 가상 재화 정책</a
              ><span> 및 결제 진행에 동의합니다.</span>
            </div>
            <div class="checkbox">
              <input type="checkbox" id="agreeBtn" /><label
                for="agreeBtn"
              ></label>
            </div>
          </div>
        </div>
      </b-col>
    </b-row>
    <b-row class="mid-container">
      <b-col md="4" offset-md="4">
        <div class="button-row">
          <div class="btn-pay">
            <button class="btn-danger" @click="requestPay">결제하기</button>
          </div>
        </div>
      </b-col>
    </b-row>
  </b-container>
</template>
<script>
export default {
  methods: {
    requestPay: function () {
      var IMP = window.IMP; // 생략 가능
      IMP.init("imp66365401"); // 예: imp00000000
      // IMP.request_pay(param, callback) 결제창 호출
      // axios로 백엔드에 주문 테이블에 주문 레코드 만들고 ID 받아오기
      // 보낼 정보 amount
      IMP.request_pay(
        {
          // param
          pg: "kakaopay",
          pay_method: "card",
          quota: "일시불",
          merchant_uid: "12223",
          name: "Logos LP Point 1000",
          amount: "1000",
          buyer_email: "test@test.com",
          buyer_name: "몰루",
          buyer_tel: "123-123-1231",
          buyer_addr: "",
          buyer_postcode: "",
        },
        (rsp) => {
          // callback
          if (rsp.success) {
            // 결제 성공 시 로직,
            // 백엔드에서 받았던 주문 ID 다시 백엔드로 보내서 위변조 검증
            // 결제번호(imp_uid)와 주문번호(merchant_uid) 보내기
            console.log(IMP);
            console.log(rsp);
            console.log("결제 성공");
          } else {
            console.log(IMP);
            console.log(rsp);
            // 결제 실패 시 로직,
            // 안내 후 다시 이전 페이지로 돌려보냄
            console.log("결제 실패");
          }
        }
      );
    },
  },
};
</script>
<style>
.mid-container {
  margin-bottom: 20px;
}
</style>
