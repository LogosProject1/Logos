<template>
  <b-container class="mt-3">
    <!-- <head-text-area :msg="msg"></head-text-area> -->
    <b-card class="text-center mt-3" style="max-width: 40rem" align="left">
      <b-form class="text-left" @submit="onSubmit" @reset="onReset">
        <b-form-group label="이메일:" label-for="userEmail">
          <b-form-input
            id="userEmail"
            v-model="user.email"
            @blur="memberIdDuplicatedCheck"
            required
            placeholder="이메일 입력"
          ></b-form-input>
          <div v-if="isMemberIdDuplicated">
            <b-alert variant="success" show>
              가입 가능한 이메일 입니다.
            </b-alert>
          </div>
          <div v-else-if="user.email == null && !isMemberIdDuplicated">
            <b-alert variant="warning" show> 이메일를 입력 해주세요. </b-alert>
          </div>
          <div v-else>
            <b-alert variant="danger" show>
              이미 사용중인 이메일 입니다.
            </b-alert>
          </div>
        </b-form-group>
        <b-form-group label="비밀번호:" label-for="userpwd">
          <b-form-input
            type="password"
            id="userpwd"
            v-model="user.password"
            required
            placeholder="비밀번호 입력"
          ></b-form-input>
        </b-form-group>
        <b-form-group label="이름:" label-for="username">
          <b-form-input
            id="username"
            v-model="user.name"
            required
            placeholder="이름 입력"
          ></b-form-input>
        </b-form-group>
        <b-form-group label="휴대폰 번호:" label-for="userPhone">
          <b-form-input
            id="userPhone"
            v-model="user.phone"
            required
            placeholder="휴대폰 번호 입력"
          ></b-form-input>
        </b-form-group>
        <b-button type="submit" variant="primary" class="m-1"
          >회원가입 등록</b-button
        >
        <b-button type="reset" variant="danger" class="m-1">초기화</b-button>
      </b-form>
    </b-card>
  </b-container>
</template>

<script>
// import { checkId, register } from "@/api/member";
// import HeadTextArea from "@/components/layout/HeadTextArea.vue";

export default {
  name: "MemberRegister",
  data() {
    return {
      user: {
        memberId: null,
        password: null,
        name: null,
        email: null,
        age: null,
      },
      isMemberIdDuplicated: false,
      msg: "회원가입",
    };
  },
  //   components: { HeadTextArea },
  //   methods: {
  //     onSubmit(event) {
  //       event.preventDefault();

  //       if (this.isMemberIdDuplicated) {
  //         register(
  //           this.user,
  //           (response) => {
  //             console.log(response);
  //             alert("가입이 완료되었습니다.");
  //             this.$router.push({ name: "home" });
  //           },
  //           (error) => {
  //             console.log(error);
  //           }
  //         );
  //       }
  //       return;
  //     },
  //     onReset(event) {
  //       event.preventDefault();
  //       this.user.memberId = null;
  //       this.user.password = null;
  //       this.user.name = null;
  //       this.user.email = null;
  //       this.user.age = null;
  //       this.$router.push({ name: "home" });
  //     },
  //     memberIdDuplicatedCheck() {
  //       checkId(
  //         this.user.memberId,
  //         (response) => {
  //           console.log(response);
  //           if (response.data.message === "success") {
  //             this.isMemberIdDuplicated = true;
  //           } else {
  //             this.isMemberIdDuplicated = false;
  //           }
  //         },
  //         (error) => {
  //           console.log(error);
  //         }
  //       );
  //     },
  //   },
};
</script>

<style></style>
