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
    path: "/user",
    name: "user",
    component: () => import("@/views/MemberView.vue"),
    children: [
      {
        path: "signUp",
        name: "signUp",
        component: () => import("@/components/user/MemberRegister.vue"),
      },
      {
        path: "signIn",
        name: "signIn",
        component: () => import("@/components/user/MemberLogin.vue"),
      },
    ],
  },
  {
    path: "/profile",
    name: "profile",
    redirect: "/profile/knowledgeHistory",
    component: () => import("@/views/ProfileView.vue"),
    children: [
      {
        path: "myPage",
        name: "myPage",
        component: () => import("@/components/profile/MemberProfile.vue"),
      },
      {
        path: "pointHistory",
        name: "pointHistory",
        component: () => import("@/components/profile/PointHistory.vue"),
      },
      {
        path: "paymentHistory",
        name: "paymentHistory",
        component: () => import("@/components/profile/PaymentHistory.vue"),
      },
      {
        path: "payment",
        name: "payment",
        component: () => import("@/components/profile/PaymentComponent.vue"),
      },
      {
        path: "knowledgeHistory",
        name: "knowledgeHistory",
        component: () => import("@/components/profile/KnowledgeHistory.vue"),
      },
      {
        path: "memberSeccess",
        name: "memberSeccess",
        component: () => import("@/components/profile/MemberSeccess.vue"),
      },
      {
        path: "rating",
        name: "rating",
        component: () => import("@/components/profile/RatingComponent.vue"),
      },
    ],
  },
  {
    path: "/knowledge/:id",
    name: "knowledge",
    component: () => import("@/views/KnowledgeView.vue"),
  },
  {
    path: "/knowledgeUpdate/:id",
    name: "knowledgeUpdate",
    component: () => import("@/components/knowledge/KnowledgeUpdate.vue"),
  },
  {
    path: "/search",
    name: "search",
    redirect: "/search/result",
    component: () => import("@/views/SearchView.vue"),
    children: [
      {
        path: "result",
        name: "result",
        component: () => import("@/components/knowledge/SearchComponent.vue"),
      },
    ],
  },
  {
    path: "/introduce",
    name: "introduce",
  },
  {
    path: "/createKnowledge",
    name: "createKnowledge",
    component: () => import("@/views/KnowledgeCreateView.vue"),
  },
  {
    path: "/paymentSuccess",
    name: "paymentSuccess",
    component: () => import("@/components/payment/PaymentSuccess.vue"),
  },
  {
    path: "/paymentError",
    name: "paymentError",
    component: () => import("@/components/payment/PaymentError.vue"),
  },
  {
    path: "/session",
    name: "session",
    component: () => import("@/views/Session.vue"),
  },
  {
    path: "/rating",
    name: "rating",
    component: () => import("@/components/rating/RatingDetail.vue"),
  },
];

const router = new VueRouter({
  mode: "history",
  base: process.env.BASE_URL,
  routes,
});

export default router;
