<template>
  <b-container class="mt-3 d-flex justify-content-center">
    <!-- <head-text-area :msg="msg"></head-text-area> -->
    <b-row>
      <b-col></b-col>
      <b-col cols="12">
        <b-card class="text-center mt-3" style="max-width: 40rem" align="left">
          <img width="150" height="150" src="../../assets/logo.png" />
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
              <!-- <div v-else-if="user.email == null && !isMemberIdDuplicated">
                <b-alert variant="warning" show>
                  이메일를 입력 해주세요.
                </b-alert>
              </div> -->
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

            <b-button type="submit" variant="primary" class="m-1"
              >로그인</b-button
            >
          </b-form>
        </b-card>
      </b-col>
      <b-col></b-col>
    </b-row>
  </b-container>
</template>

<script>
// import { checkId, register } from "@/api/member";
// import HeadTextArea from "@/components/layout/HeadTextArea.vue";

export default {
  name: "MemberLogin",
  data() {
    return {
      user: {
        memberId: null,
        password: null,
        name: null,
        email: null,
        age: null,
      },
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
