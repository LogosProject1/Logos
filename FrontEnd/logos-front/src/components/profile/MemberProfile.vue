<template>
  <b-container class="bv-example-row mt-3">
    <b-row>
      <b-col></b-col>
      <b-col cols="8">
        <b-card class="text-center mt-3" style="max-width: 40rem" align="left">
          <b-form class="text-left" @submit="onSubmit" @reset="onReset">
            <b-form-group label="이메일:" label-for="userEmail">
              <b-form-input
                id="userEmail"
                v-model="user.email"
                disabled
              ></b-form-input>
            </b-form-group>
            <b-form-group label="이름:" label-for="username">
              <b-form-input
                id="username"
                v-model="user.name"
                required
              ></b-form-input>
            </b-form-group>
            <b-form-group label="휴대폰 번호:" label-for="userPhone">
              <b-form-input
                id="userPhone"
                v-model="user.phone"
                required
              ></b-form-input>
            </b-form-group>
            <b-form-group label="기존 비밀번호:" label-for="userpwd">
              <b-form-input
                type="password"
                id="userpwd"
                v-model="modifyUser.originPassword"
                required
              ></b-form-input>
            </b-form-group>
            <b-form-group label="새 비밀번호:" label-for="usernewpwd">
              <b-form-input
                type="password"
                id="usernewpwd"
                v-model="modifyUser.newPassword"
                required
              ></b-form-input>
            </b-form-group>
            <b-form-group
              label="새 비밀번호 확인:"
              label-for="usernewpwdconfirm"
            >
              <b-form-input
                type="password"
                id="usernewpwdconfirm"
                v-model="inputNewPasswordConfirm"
                @blur="passwordCheckValid"
                required
              ></b-form-input>
              <div v-if="!passwordCheckFlag">
                <b-alert variant="danger" show
                  >비밀번호가 동일하지 않습니다.</b-alert
                >
              </div>
              <div v-if="passwordCheckFlag">
                <b-alert variant="success" show>비밀번호가 동일합니다.</b-alert>
              </div>
            </b-form-group>

            <b-button type="submit" variant="primary" class="m-5"
              >수정</b-button
            >
          </b-form>
        </b-card>
      </b-col>
      <b-col></b-col>
    </b-row>
  </b-container>
</template>

<script>
import { mapGetters } from "vuex";
import { modify } from "@/api/auth";

const memberStore = "memberStore";

export default {
  name: "MemberModify",
  data() {
    return {
      user: {
        name: null,
        email: null,
        phone: null,
      },
      isUserId: true,
      inputNewPasswordConfirm: "",
      passwordCheckFlag: false,
      modifyUser: {
        originPassword: "",
        newPassword: "",
        email: "",
        name: "",
        phone: "",
      },
      msg: "회원 정보 수정",
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
    onSubmit(event) {
      event.preventDefault();

      if (!this.passwordCheckFlag) {
        alert("변경할 비밀 번호를 확인 해주세요.");
        return;
      }

      this.modifyUser.email = this.user.email;
      this.modifyUser.name = this.user.name;
      this.modifyUser.phone = this.user.phone;

      modify(
        this.modifyUser,
        (response) => {
          if (response.data.message === "success") {
            alert("회원 정보를 수정 했습니다.");
            this.$router.push({ name: "myPage" });
          } else if (response.data.message === "fail") {
            alert("기존 비밀번호가 다릅니다. 다시 확인 해 주세요.");
            return;
          }
        },
        (error) => {
          console.log(error);
        }
      );
    },
    onReset(event) {
      event.preventDefault();
      this.user.name = null;
      this.user.email = null;
      this.user.phone = null;
      this.$router.push({ name: "home" });
    },
    passwordCheckValid() {
      if (this.modifyUser.newPassword === this.inputNewPasswordConfirm) {
        this.passwordCheckFlag = true;
      } else {
        this.passwordCheckFlag = false;
      }
    },
  },
};
</script>

<style></style>
