<template>
  <div>
    <b-navbar class="header" toggleable="lg" type="dark" variant="white">
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
            ><router-link :to="{ name: 'home' }" class="link span1">
              <span class="span1">카테고리</span>
            </router-link></b-nav-item
          >
          <b-nav-item href="#"
            ><router-link :to="{ name: 'knowledge' }" class="link span1">
              지식</router-link
            ></b-nav-item
          >
          <b-nav-item href="#"
            ><router-link :to="{ name: 'introduce' }" class="link span1">
              서비스 소개</router-link
            ></b-nav-item
          >
        </b-navbar-nav>

        <form
          class="col-md-5 justify-content-center"
          action="/search"
          method="get"
          style="
            float: none;
            position: absolute;
            top: 50%;
            left: 50%;
            transform: translate(-50%, -50%);
          "
        >
          <div class="input-group">
            <input
              name="keyword"
              type="search"
              class="form-control"
              placeholder="원하는 컨텐츠를 검색해보세요."
              aria-label="Search"
            />
            <b-button
              class="btn"
              style="background-color: #764ba2"
              type="submit"
              id="button-addon2"
            >
              검색
            </b-button>
          </div>
        </form>

        <b-navbar-nav class="ml-auto">
          <b-nav-item right v-if="userInfo"
            ><b-avatar
              variant="dark"
              v-text="userInfo ? userInfo.name.charAt(0).toUpperCase() : ''"
            ></b-avatar
            ><span class="span1">
              {{ userInfo.name }}님 환영합니다.
            </span></b-nav-item
          >
          <b-nav-item right>
            <template #button-content>
              <b-icon icon="people" font-scale="2" variant="dark"></b-icon>
            </template>
            <div v-if="userInfo">
              <router-link :to="{ name: 'profile' }" class="link"
                ><b-button pill variant="outline-dark"
                  >마이페이지</b-button
                ></router-link
              >
              <b-button
                class="ml-2"
                pill
                style="background-color: #764ba2"
                @click="onClickLogout"
                >로그아웃</b-button
              >
            </div>
            <div v-else>
              <router-link :to="{ name: 'signUp' }" class="link">
                <b-button pill variant="outline-dark"
                  >회원가입</b-button
                ></router-link
              >
              <router-link :to="{ name: 'signIn' }" class="link">
                <b-button class="ml-2" pill style="background-color: #764ba2"
                  >로그인</b-button
                >
              </router-link>
            </div>
          </b-nav-item>
        </b-navbar-nav>
      </b-collapse>
    </b-navbar>
  </div>
</template>

<script>
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
</script>

<style>
.span1 {
  color: black;
}
.header {
  height: 100px;
}
</style>
