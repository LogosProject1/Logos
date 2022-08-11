<template>
  <div>
    <div
      class="d-flex justify-content-between flex-wrap flex-md-nowrap align-items-center pt-3 pb-2 mb-3 border-bottom"
    >
      <h1 class="h2">회원 탈퇴</h1>
    </div>
    <div class="text">
      <div>
        <b>회원님 Logos 서비스를 이용하시는데 불편함이 있으셨나요?</b><br />
        <span
          >이용 불편 및 각종 문의 사항은 고객샌터로 문의 주시면 성심 성의껏 답변
          드리겠습니다.</span
        >
      </div>
      <br />

      <b-card header-tag="header" footer-tag="footer">
        <b-card-text
          ><b
            >1. 회원탈퇴 전, 유의사항을 확인해 주시기 바랍니다.</b
          ></b-card-text
        >
        <b-card-text
          >▪ 회원탈퇴 시 회원전용 웹 서비스 이용이 불가합니다.</b-card-text
        >
        <b-card-text
          >▪ 거래정보가 있는 경우, 전자상거래 등에서의 소비자 보호에 관한 법률에
          따라 계약 또는 청약철회에 관한 기록, 대금결제 및 재화 등의 공급에 관한
          기록은 5년동안 보존됩니다.</b-card-text
        >
        <b-card-text
          >▪ 보유하셨던 LP(Logos Point)는 탈퇴와 함께 삭제되며 환불되지
          않습니다.</b-card-text
        >
        <b-card-text
          >▪ 회원탈퇴 후 Logos 서비스에 입력하신 강의문의 및 후기, 댓글은
          삭제되지 않으며, 회원정보 삭제로 인해 작성자 본인을 확인할 수 없어
          편집 및 삭제처리가 원천적으로 불가능 합니다.</b-card-text
        >
        <b-card-text
          >▪ 강의문의 및 후기, 댓글 삭제를 원하시는 경우에는 먼저 해당 게시물을
          삭제하신 후 탈퇴를 신청하시기 바랍니다.
        </b-card-text>
        <b-card-text
          >▪ 이미 결제가 완료된 건은 탈퇴로 취소되지 않습니다.</b-card-text
        >
        <template #footer>
          <b-form-checkbox
            id="checkbox-1"
            v-model="status"
            name="checkbox-1"
            value="accepted"
            unchecked-value="not_accepted"
          >
            <b
              >상기 Logos 회원탈퇴 시 처리사항 안내를 확인하였음에
              동의합니다.</b
            >
          </b-form-checkbox>
        </template>
      </b-card>
    </div>
    <br />

    <div class="seccessContainer">
      <div>
        <b>보안 위해 회원님의 이름과 계정 이메일를 확인 합니다.</b>
      </div>
      <br />
      <div>
        이름:
        <input type="text" id="username" v-model="user.name" disabled />
        &nbsp; 이메일:
        <input type="text" id="userEmail" v-model="user.email" disabled />
      </div>
      <b-button
        v-on:click="onSubmit"
        variant="outline-danger"
        class="m-5 seccessbtn"
        >회원 탈퇴</b-button
      >
    </div>
  </div>
</template>

<script>
import { mapActions, mapGetters } from "vuex";

const memberStore = "memberStore";

export default {
  name: "MemberSeccess",
  data() {
    return {
      user: {
        email: null,
        name: null,
        password: null,
      },
      status: "not_accepted",
    };
  },
  computed: {
    ...mapGetters(memberStore, ["checkUserInfo"]),
  },
  created() {
    console.log(this.checkUserInfo);
    this.user = this.checkUserInfo;
  },
  methods: {
    ...mapActions(memberStore, ["userDelete"]),
    onSubmit(event) {
      event.preventDefault();

      if (this.status !== "accepted") {
        alert("회원 탈퇴 안내 사항에 동의하셔야 합니다.");
      } else {
        this.userDelete();
        sessionStorage.removeItem("access-token");
        this.$router.push({ name: "home" });
      }
    },
  },
};
</script>

<style>
.text {
  text-align: left;
}
.seccessContainer {
  background-color: #e6e2dd;
  padding-top: 20px;
}
.seccessbtn {
  background-color: white;
}
</style>
