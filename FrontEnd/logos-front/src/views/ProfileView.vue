<template>
  <div class="container-fluid">
    <div class="row">
      <nav
        id="sidebarMenu"
        class="col-md-3 col-lg-2 d-md-block bg-light sidebar collapse"
      >
        <div class="position-sticky pt-3 sidebar-sticky">
          <h4
            class="sidebar-heading d-flex justify-content-between align-items-center mt-4 mb-1 text-muted text-uppercase"
          >
            <b-button pill variant="outline-dark">메뉴</b-button>
          </h4>
          <br />
          <ul class="nav flex-column">
            <b-nav-item
              v-for="item in items"
              :key="item.id"
              :to="item.href"
              v-on:click="selectItem(item.id)"
              v-bind:class="{
                'item-selection': true,
                'active-item': item.id === currentItem,
              }"
              >{{ item.text }}</b-nav-item
            >
            <!-- <li class="nav-item">
              <a
                class="nav-link active"
                aria-current="page"
                href="/profile/knowledgeHistory"
              >
                나의 지식
              </a>
            </li>
            <li class="nav-item">
              <a class="nav-link" href="/profile/myPage"> 개인정보확인/수정 </a>
            </li>
            <li class="nav-item">
              <a class="nav-link" href="/profile/pointHistory"> 포인트 이력 </a>
            </li>
            <li class="nav-item">
              <a class="nav-link" href="/profile/paymentHistory"> 결제 내역 </a>
            </li>
            <li class="nav-item">
              <a class="nav-link" href="/profile/payment"> 포인트 결제하기 </a>
            </li>
            <li class="nav-item">
              <a class="nav-link" href="/profile/memberSeccess"> 회원 탈퇴 </a>
            </li> -->
          </ul>
        </div>
      </nav>

      <main class="col-md-9 ms-sm-auto col-lg-10 px-md-4">
        <router-view></router-view>
      </main>
    </div>
  </div>
</template>

<script>
export default {
  data() {
    return {
      items: [
        {
          id: 1,
          text: "나의 지식",
          href: "/profile/knowledgeHistory",
        },
        {
          id: 2,
          text: "개인정보확인/수정",
          href: "/profile/myPage",
        },
        {
          id: 3,
          text: "포인트 이력",
          href: "/profile/pointHistory",
        },
        {
          id: 4,
          text: "결제 내역",
          href: "/profile/paymentHistory",
        },
        {
          id: 5,
          text: "포인트 결제하기",
          href: "/profile/payment",
        },
        {
          id: 6,
          text: "회원 탈퇴",
          href: "/profile/memberSeccess",
        },
        {
          id: 7,
          text: "평가하기",
          href: "/profile/rating",
        },
      ],
      currentItem: 0,
    };
  },
  created() {
    if (sessionStorage.getItem("access-token") === null) {
      alert("로그인을 해주세요.");
      this.$router.push({ name: "signIn" });
    }
  },
  methods: {
    selectItem(id) {
      this.currentItem = id;
    },
  },
};
</script>

<style>
.feather {
  width: 16px;
  height: 16px;
}

/*
 * Sidebar
 */

.sidebar {
  position: relative;
  top: 0;
  /* rtl:raw:
  right: 0;
  */
  bottom: 0;
  /* rtl:remove */
  left: 0;
  z-index: 100; /* Behind the navbar */
  padding: 15px 50px 0; /* Height of navbar */
  box-shadow: inset -1px 0 0 rgba(0, 0, 0, 0.1);
  text-align: left;
  font-size: 1.25rem;
}

@media (max-width: 767.98px) {
  .sidebar {
    top: 5rem;
  }
}

.sidebar-sticky {
  height: calc(100vh - 48px);
  overflow-x: hidden;
  overflow-y: auto; /* Scrollable contents if viewport is shorter than content. */
}

.sidebar .nav-link {
  font-weight: 500;
  color: #333;
}

.sidebar .nav-link .feather {
  margin-right: 4px;
  color: #727272;
}

.sidebar .nav-link.active {
  color: #2470dc;
}

.sidebar .nav-link:hover .feather,
.sidebar .nav-link.active .feather {
  color: inherit;
}

.sidebar-heading {
  font-size: 1.25rem;
}

/*
 * Navbar
 */

.navbar-brand {
  padding-top: 0.75rem;
  padding-bottom: 0.75rem;
  background-color: rgba(0, 0, 0, 0.25);
  box-shadow: inset -1px 0 0 rgba(0, 0, 0, 0.25);
}

.navbar .navbar-toggler {
  top: 0.25rem;
  right: 1rem;
}

.navbar .form-control {
  padding: 0.75rem 1rem;
}

.form-control-dark {
  color: #fff;
  background-color: rgba(255, 255, 255, 0.1);
  border-color: rgba(255, 255, 255, 0.1);
}

.form-control-dark:focus {
  border-color: transparent;
  box-shadow: 0 0 0 3px rgba(255, 255, 255, 0.25);
}

.bd-placeholder-img {
  font-size: 1.125rem;
  text-anchor: middle;
  -webkit-user-select: none;
  -moz-user-select: none;
  user-select: none;
}

@media (min-width: 768px) {
  .bd-placeholder-img-lg {
    font-size: 3.5rem;
  }
}

.b-example-divider {
  height: 3rem;
  background-color: rgba(0, 0, 0, 0.1);
  border: solid rgba(0, 0, 0, 0.15);
  border-width: 1px 0;
  box-shadow: inset 0 0.5em 1.5em rgba(0, 0, 0, 0.1),
    inset 0 0.125em 0.5em rgba(0, 0, 0, 0.15);
}

.b-example-vr {
  flex-shrink: 0;
  width: 1.5rem;
  height: 100vh;
}

.bi {
  vertical-align: -0.125em;
  fill: currentColor;
}

.nav-scroller {
  position: relative;
  z-index: 2;
  height: 2.75rem;
  overflow-y: hidden;
}

.nav-scroller .nav {
  display: flex;
  flex-wrap: nowrap;
  padding-bottom: 1rem;
  margin-top: -1px;
  overflow-x: auto;
  text-align: center;
  white-space: nowrap;
  -webkit-overflow-scrolling: touch;
}
.item-selection:hover {
  color: black;
  cursor: pointer;
  background: #ebebeb;
}

.item-selection {
}

.active-item {
  background-color: #bea8c4;
}
</style>
