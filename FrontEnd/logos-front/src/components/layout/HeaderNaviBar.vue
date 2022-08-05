<template>
  <div>
    <b-navbar toggleable="lg" type="dark" variant="white">
      <b-navbar-brand href="#">
        <router-link to="/">
          <img
            src="@/assets/logo.png"
            class="d-inline-block align-middle"
            width="100px"
            alt="logos"
        /></router-link>
      </b-navbar-brand>

      <b-navbar-toggle target="nav-collapse"></b-navbar-toggle>

      <b-collapse id="nav-collapse" is-nav>
        <b-navbar-nav>
          <b-nav-item href="#"
            ><router-link :to="{ name: 'home' }" class="link"
              >Home
            </router-link></b-nav-item
          >
          <b-nav-item href="#"
            ><router-link :to="{ name: 'introduce' }" class="link">
              서비스 소개</router-link
            ></b-nav-item
          >
          <b-nav-item href="#"
            ><router-link :to="{ name: 'knowledge' }" class="link">
              지식</router-link
            ></b-nav-item
          >
        </b-navbar-nav>

        <b-nav-form>
          <b-form-input class="" placeholder="Search"></b-form-input>
          <b-button size="sm" class="my-2 my-sm-0" type="submit"
            >Search</b-button
          >
        </b-nav-form>

        <b-navbar-nav class="ml-auto">
          <b-nav-item right v-if="userInfo"
            ><b-avatar
              variant="dark"
              v-text="userInfo ? userInfo.memberId.charAt(0).toUpperCase() : ''"
            ></b-avatar
            ><span class="span1">
              {{ userInfo.memberId }}님 환영합니다.
            </span></b-nav-item
          >
          <div v-if="userInfo">
            <b-nav-item href="#"
              ><router-link :to="{ name: 'myPage' }" class="link">
                마이페이지
              </router-link></b-nav-item
            >
            <b-nav-item href="#" @click.prevent="onClickLogout">
              로그아웃
            </b-nav-item>
          </div>
          <div v-else>
            <b-nav-item href="#"
              ><b-button
                :to="{ name: 'signUp' }"
                class="link"
                pill
                variant="dark"
              >
                회원가입</b-button
              ></b-nav-item
            >
            <b-nav-item href="#"
              ><b-button
                :to="{ name: 'signIn' }"
                class="link"
                pill
                variant="light"
              >
                로그인
              </b-button></b-nav-item
            >
          </div>
        </b-navbar-nav>
      </b-collapse>
    </b-navbar>
  </div>
</template>

<!-- <script>
import { mapState, mapMutations } from "vuex";
// import ms from "@/store/modules/memberStore";

const memberStore = "memberStore";

export default {
  name: "HeaderNaviBar",

  computed: {
    ...mapState(memberStore, ["isLogin", "userInfo"]),
  },
  methods: {
    ...mapMutations(memberStore, ["SET_IS_LOGIN", "SET_USER_INFO"]),
    onClickLogout() {
      // console.log("memberStore : ", ms);
      this.SET_IS_LOGIN(false);
      this.SET_USER_INFO(null);
      sessionStorage.removeItem("access-token");
      if (this.$route.path != "/") this.$router.push({ name: "home" });
    },
  },
};
</script> -->

<style>
.span1 {
  color: black;
}
</style>
