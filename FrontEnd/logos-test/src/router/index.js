import Vue from "vue";
import VueRouter from "vue-router";

Vue.use(VueRouter);

const routes = [
  {
    path: "/",
    name: "home",
    component: () => import("@/views/HomeView.vue"),
  },
  {
    path: "/session",
    name: "session",
    component: () => import("@/views/Session.vue"),
  },
  {
    path: "/payment",
    name: "payment",
    component: () => import("@/views/PaymentView.vue"),
  },
];

const router = new VueRouter({
  mode: "history",
  base: process.env.BASE_URL,
  routes,
});

export default router;
