<template>
  <b-container class="mt-3 d-flex justify-content-center">
    <!-- <head-text-area :msg="msg"></head-text-area> -->
    <b-row>
      <b-col></b-col>
      <b-col cols="12">
        <b-card class="text-center mt-3" style="max-width: 40rem" align="left">
          <img width="150" height="150" src="../../assets/logo.png" />
          <b-form class="text-left" @submit="onSubmit">
            <b-form-group label="이메일:" label-for="userEmail">
              <b-form-input
                id="userEmail"
                v-model="user.email"
                @blur="userEmailDuplicatedCheck"
                @focus="changeFlag"
                required
                placeholder="이메일 입력"
              ></b-form-input>

              <div v-if="isMemberIdDuplicated && flag">
                <b-alert variant="danger" show>
                  이미 사용중인 이메일 입니다.
                </b-alert>
              </div>
              <div v-else-if="!isMemberIdDuplicated && flag">
                <b-alert variant="success" show>
                  가입 가능한 이메일 입니다.
                </b-alert>
              </div>
              <div v-else-if="!flag">
                <b-alert variant="warning" show>
                  이메일를 입력 해주세요.
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
          </b-form>
        </b-card>
      </b-col>
      <b-col></b-col>
    </b-row>
  </b-container>
</template>

<script>
import { checkId, register } from "@/api/auth";

export default {
  name: "MemberRegister",
  data() {
    return {
      user: {
        email: null,
        password: null,
        name: null,
        age: null,
      },
      isMemberIdDuplicated: false,
      msg: "회원가입",
      flag: false,
    };
  },
  methods: {
    onSubmit(event) {
      event.preventDefault();

      if (!this.isMemberIdDuplicated) {
        register(
          this.user,
          (response) => {
            console.log(response);
            alert("가입이 완료되었습니다.");
            this.$router.push({ name: "home" });
          },
          (error) => {
            console.log(error);
          }
        );
      }
      return;
    },
    onReset(event) {
      event.preventDefault();
      this.user.email = null;
      this.user.password = null;
      this.user.name = null;
      this.user.age = null;
      this.$router.push({ name: "home" });
    },
    userEmailDuplicatedCheck() {
      checkId(
        this.user.email,
        (response) => {
          console.log(response);
          if (response.data.message === "success") {
            this.isMemberIdDuplicated = true;
            console.log("이미 존재하는 이메일입니다.");
          } else {
            this.isMemberIdDuplicated = false;
            console.log("가입 가능한 이메일입니다.");
          }
          this.flag = true;
        },
        (error) => {
          console.log(error);
        }
      );
    },
    changeFlag() {
      this.flag = false;
    },
  },
};
</script>

<style></style>
