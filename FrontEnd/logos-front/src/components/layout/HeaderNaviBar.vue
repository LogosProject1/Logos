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

<<<<<<< HEAD
        <b-nav-form>
          <b-form-input class="" placeholder="Search"></b-form-input>
          <b-button size="sm" class="my-2 my-sm-0" type="submit"
            >Search</b-button
          >
        </b-nav-form>
=======
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
>>>>>>> 73df43c2db60262b7d2b68177f19c4ee02c7d362

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
<<<<<<< HEAD
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
=======
          <b-nav-item right>
            <template #button-content>
              <b-icon icon="people" font-scale="2" variant="dark"></b-icon>
            </template>
            <div v-if="userInfo">
              <b-dropdown-item href="#"
                ><router-link :to="{ name: 'myPage' }" class="link"
                  ><b-icon icon="person-circle"></b-icon> 마이페이지
                </router-link></b-dropdown-item
              >
              <b-dropdown-item href="#" @click.prevent="onClickLogout"
                ><b-icon icon="key"></b-icon> 로그아웃
              </b-dropdown-item>
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
>>>>>>> 73df43c2db60262b7d2b68177f19c4ee02c7d362
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
