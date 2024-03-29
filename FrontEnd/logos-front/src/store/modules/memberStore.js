import jwt_decode from "jwt-decode";
import { login, findById, deleteMember, modify } from "@/api/auth.js";
import { filterKnowledge } from "@/api/knowledge";

const memberStore = {
  namespaced: true,
  state: {
    isLogin: false,
    isLoginError: false,
    userInfo: null,
    knowledgeList: [],
    title: "",
  },
  getters: {
    checkUserInfo: function (state) {
      return state.userInfo;
    },
    getKnowledgeList: function (state) {
      return state.knowledgeList;
    },
    getTitle: function (state) {
      return state.title;
    },
  },
  mutations: {
    SET_IS_LOGIN: (state, isLogin) => {
      state.isLogin = isLogin;
    },
    SET_IS_LOGIN_ERROR: (state, isLoginError) => {
      state.isLoginError = isLoginError;
    },
    SET_USER_INFO: (state, userInfo) => {
      state.isLogin = true;
      state.userInfo = userInfo;
    },
    SET_KNOWLEDGE_LIST: (state, knowledgeList) => {
      state.knowledgeList = knowledgeList;
    },
    SET_TITLE: (state, title) => {
      state.title = title;
    },
  },
  actions: {
    async userConfirm({ commit }, user) {
      await login(
        user,
        (response) => {
          if (response.data.message === "success") {
            let token = response.data["access-token"];
            commit("SET_IS_LOGIN", true);
            commit("SET_IS_LOGIN_ERROR", false);
            sessionStorage.setItem("access-token", token);
          } else {
            commit("SET_IS_LOGIN", false);
            commit("SET_IS_LOGIN_ERROR", true);
          }
        },
        () => {}
      );
    },
    async memberModify(modifyUser) {
      await modify(
        modifyUser,
        (response) => {
          console.log(response);
          if (response.data.message === "success") {
            alert("회원 정보를 수정 했습니다.");
            this.$router.push({ name: "myPage" });
          } else if (response.data.message === "fail") {
            alert("기존 비밀번호가 다릅니다. 다시 확인 해 주세요.");
          }
        },
        (error) => {
          console.log(error);
        }
      );
    },
    getUserInfo({ commit }, token) {
      let decode_token = jwt_decode(token);
      // console.log(decode_token);
      findById(
        decode_token.email,
        (response) => {
          if (response.data.message === "success") {
            commit("SET_USER_INFO", response.data.userInfo);
          } else {
            console.log("유저 정보 없음!!");
          }
        },
        (error) => {
          console.log(error);
        }
      );
    },
    async userDelete({ commit }) {
      await deleteMember(
        (response) => {
          if (response.data.message === "success") {
            alert("회원이 정상적으로 탈퇴 되었습니다.");
            console.log(response);
            commit("SET_IS_LOGIN", false);
            commit("SET_IS_LOGIN_ERROR", false);
            commit("SET_USER_INFO", null);
          } else if (response.data.message === "fail") {
            alert("회원 탈퇴를 실패 했습니다.");
            return;
          }
        },
        (error) => {
          console.log(error);
        }
      );
    },
    async getKnowledgeList({ commit }, category) {
      await filterKnowledge(
        category,
        (response) => {
          if (response.data.message === "success") {
            commit("SET_KNOWLEDGE_LIST", response.data.knowledge_list);
          } else {
            console.log("유저 정보 없음!!");
          }
        },
        (error) => {
          console.log(error);
        }
      );
    },
    // async userModify()
  },
};

export default memberStore;
