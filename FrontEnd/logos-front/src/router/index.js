import Vue from "vue";
import VueRouter from "vue-router";

Vue.use(VueRouter);

const routes = [
  {
    path: "/signup",
    name: "signup",
    component: () => import("@/views/SignUp.vue"),
  },
  {
    path: "/signin",
    name: "signin",
    component: () => import("@/views/SignIn.vue"),
  },
];

const router = new VueRouter({
  mode: "history",
  base: process.env.BASE_URL,
  routes,
});

export default router;
