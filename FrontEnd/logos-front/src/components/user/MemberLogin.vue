<template>
  <b-container class="mt-3 d-flex justify-content-center">
    <!-- <head-text-area :msg="msg"></head-text-area> -->
    <b-row>
      <b-col></b-col>
      <b-col cols="12">
        <b-card class="text-center mt-3" style="max-width: 40rem" align="left">
          <img width="150" height="150" src="../../assets/logo.png" />
          <b-form class="text-left" @submit="confirm">
            <b-alert show variant="danger" v-if="isLoginError"
              >아이디 또는 비밀번호를 확인하세요.</b-alert
            >
            <b-form-group label="이메일:" label-for="userEmail">
              <b-form-input
                id="userEmail"
                v-model="user.email"
                required
                placeholder="이메일 입력"
              ></b-form-input>
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

            <router-link
              :to="{ name: 'signUp' }"
              id="movepage"
              class="float-right"
              >아이디가 없으신가요?</router-link
            >
            <b-row>
              <b-button type="submit" variant="primary" class="m-1"
                >로그인</b-button
              >
            </b-row>
          </b-form>
        </b-card>
      </b-col>
      <b-col></b-col>
    </b-row>
  </b-container>
</template>

<script>
import { mapState, mapActions } from "vuex";

const memberStore = "memberStore";

export default {
  name: "MemberLogin",
  data() {
    return {
      user: {
        email: null,
        password: null,
      },
      msg: "로그인",
    };
  },
  computed: {
    ...mapState(memberStore, ["isLogin", "isLoginError"]),
  },
  methods: {
    ...mapActions(memberStore, ["userConfirm", "getUserInfo"]),
    async confirm() {
      event.preventDefault();
      await this.userConfirm(this.user);
      let token = "Bearer " + sessionStorage.getItem("access-token");
      if (this.isLogin) {
        await this.getUserInfo(token);
        this.$router.push({ name: "home" });
      }
    },
  },
};
</script>

<style></style>
