import Vue from "vue";
import App from "./App.vue";
import router from "./router";
import store from "./store";

import { BootstrapVue, IconsPlugin } from "bootstrap-vue";
import "bootstrap/dist/css/bootstrap.css";
import "bootstrap-vue/dist/bootstrap-vue.css";
// import "remixicon/fonts/remixicon.css";

Vue.use(BootstrapVue);
Vue.use(IconsPlugin);
const moment = require("moment");
require("moment/locale/ko");

Vue.use(require("vue-moment"), {
  moment,
});

Vue.config.productionTip = false;

new Vue({
  router,
  store,
  render: (h) => h(App),
}).$mount("#app");
