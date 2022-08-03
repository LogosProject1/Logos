<template>
  <b-container>
    <b-card bg-variant="light">
      <b-form-group
        label-cols-lg="5"
        label="로그인"
        label-size="lg"
        label-class="font-weight-bold pt-0"
        class="mb-5"
      >
      </b-form-group>
      <b-form-group
        label="이메일 :"
        label-for="nested-email"
        label-cols-sm="3"
        label-align-sm="right"
        content-cols-sm
        content-cols-lg="7"
      >
        <b-form-input id="nested-email"></b-form-input>
      </b-form-group>

      <b-form-group
        label="비밀번호 :"
        label-for="nested-password"
        label-cols-sm="3"
        label-align-sm="right"
        content-cols-sm
        content-cols-lg="7"
      >
        <b-form-input col="2" id="nested-password"></b-form-input>
      </b-form-group>

      <b-form-group
        label-for="nested-button"
        label-cols-sm="3"
        label-align-sm="right"
        content-cols-sm
        content-cols-lg="7"
      >
        <button class="btn btn-primary mt-4 float-right">로그인하기</button>
      </b-form-group>
    </b-card>
  </b-container>
</template>

<script>
export default {
  name: "SignIn",

  data: () => ({
    name: "",
    email: "",
    checkbox: false,
  }),

  computed: {
    checkboxErrors() {
      const errors = [];
      if (!this.$v.checkbox.$dirty) return errors;
      !this.$v.checkbox.checked && errors.push("You must agree to continue!");
      return errors;
    },
    nameErrors() {
      const errors = [];
      if (!this.$v.name.$dirty) return errors;
      !this.$v.name.maxLength &&
        errors.push("Name must be at most 10 characters long");
      !this.$v.name.required && errors.push("Name is required.");
      return errors;
    },
    emailErrors() {
      const errors = [];
      if (!this.$v.email.$dirty) return errors;
      !this.$v.email.email && errors.push("Must be valid e-mail");
      !this.$v.email.required && errors.push("E-mail is required");
      return errors;
    },
  },

  methods: {
    submit() {
      this.$v.$touch();
    },
  },
};
</script>
