<!-- eslint-disable vue/no-useless-template-attributes -->
<template>
  <div class="container-fluid">
    <div class="row">
      <nav
        id="sidebarMenu"
        class="col-md-3 col-lg-2 d-md-block bg-light sidebar collapse"
      >
        <div class="position-sticky pt-3 sidebar-sticky">
          <ul class="nav flex-column">
            <b-nav-item
              v-for="item in items"
              :key="item.id"
              v-on:click="selectItem(item.id)"
              v-bind:class="{
                'item-selection': true,
                'active-item': item.id === currentItem,
              }"
              >{{ item.text }}</b-nav-item
            >
          </ul>
        </div>
        <div class="filtering">
          <div class="pb-4">
            <b-icon icon="sliders"></b-icon>
            <b> 추가 필터</b>
          </div>

          <label for="price">금액</label>
          <div class="pt-4 pb-3">
            <Slider
              v-model="priceValue"
              :format="format"
              :max="100000"
              class="slider-blue"
            />
          </div>

          <br />
          <label for="date">날짜</label>
          <div id="date">
            <date-range-picker
              v-model="dateRange"
              :timePicker="true"
              :timePicker24Hour="true"
              opens="right"
            >
              <template v-slot:input="picker">
                {{ picker.startDate | moment("YYYY-MM-DD HH:mm") }} - <br />{{
                  picker.endDate | moment("YYYY-MM-DD HH:mm")
                }}
              </template>
            </date-range-picker>
          </div>

          <div class="mt-5">
            <b-button variant="outline-dark" @click="resetFilter"
              >필터 초기화</b-button
            >
            <b-button
              class="ml-2"
              style="background-color: #764ba2"
              @click="applyFilter"
              >적용</b-button
            >
          </div>
        </div>
      </nav>

      <main class="col-md-9 ms-sm-auto col-lg-10 px-md-4">
        <router-view :key="this.$route.fullPath"></router-view>
      </main>
    </div>
  </div>
</template>

<script>
import DateRangePicker from "vue2-daterange-picker";
import moment from "moment";
import Slider from "@vueform/slider/dist/slider.vue2.js";
import "vue2-daterange-picker/dist/vue2-daterange-picker.css";
import "@vueform/slider/themes/default.css";

export default {
  components: { DateRangePicker, Slider },
  data() {
    return {
      items: [
        {
          id: 1,
          text: "IT",
          href: {
            name: "result",
            query: {
              category: "IT",
              title: undefined,
            },
          },
        },
        {
          id: 2,
          text: "음악",
          href: {
            name: "result",
            query: {
              category: "음악",
              title: undefined,
            },
          },
        },
        {
          id: 3,
          text: "금융",
          href: {
            name: "result",
            query: {
              category: "금융",
              title: undefined,
            },
          },
        },
        {
          id: 4,
          text: "요리",
          href: {
            name: "result",
            query: {
              category: "요리",
              title: undefined,
            },
          },
        },
        {
          id: 5,
          text: "드로잉",
          href: {
            name: "result",
            query: {
              category: "드로잉",
              title: undefined,
            },
          },
        },
        {
          id: 6,
          text: "외국어",
          href: {
            name: "result",
            query: {
              category: "외국어",
              title: undefined,
            },
          },
        },
        {
          id: 7,
          text: "사진/영상",
          href: {
            name: "result",
            query: {
              category: "사진/영상",
              title: undefined,
            },
          },
        },
      ],
      currentItem: 0,
      dateRange: {
        startDate: undefined,
        endDate: undefined,
      },
      priceValue: [0, 100000],
      format: function (value) {
        return `${Math.round(value)}LP`;
      },
    };
  },
  methods: {
    resetFilter(e) {
      e.preventDefault();
      this.dateRange = {
        startDate: undefined,
        endDate: undefined,
      };
      this.priceValue = [0, 100000];
      this.$router.replace({
        path: this.$route.path,
        query: {
          ...this.$route.query,
          startTime: undefined,
          endTime: undefined,
          minPrice: undefined,
          maxPrice: undefined,
        },
      });
    },
    applyFilter(e) {
      e.preventDefault();
      let startTime = undefined;
      let endTime = undefined;
      if (this.dateRange.startDate != null) {
        startTime = moment(this.dateRange.startDate).format("YYYY-MM-DDTHH:mm");
      }
      if (this.dateRange.endDate != null) {
        endTime = moment(this.dateRange.endDate).format("YYYY-MM-DDTHH:mm");
      }
      this.$router.replace({
        path: this.$route.path,
        query: {
          ...this.$route.query,
          startTime: startTime,
          endTime: endTime,
          minPrice: this.priceValue[0],
          maxPrice: this.priceValue[1],
        },
      });
    },
    selectItem(id) {
      this.currentItem = id;
      this.items[id - 1].href.query.title = this.$route.query.title;
      this.$router.push({ query: this.items[id - 1].href.query });
    },
  },
};
</script>

<style>
.form-control {
  height: 100%;
}
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
  height: 100%;
}

@media (max-width: 767.98px) {
  .sidebar {
    top: 5rem;
    height: 100%;
  }
}

.sidebar-sticky {
  /* height: calc(100vh - 48px); */
  overflow-x: hidden;
  overflow-y: auto; /* Scrollable contents if viewport is shorter than content. */
  padding-bottom: 200px;
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
/* .filtering {
  padding: 20px;
  background-color: white;
} */
.slider-blue {
  --slider-connect-bg: #902fba;
  --slider-tooltip-bg: #902fba;
  --slider-handle-ring-color: #3b82f630;
}
</style>
