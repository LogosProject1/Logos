<template>
  <div>
    <div
      class="d-flex justify-content-between flex-wrap flex-md-nowrap align-items-center pt-3 pb-2 mb-3 border-bottom"
    >
      <h1 class="h2">나의 지식</h1>
    </div>
    <b-tabs content-class="mt-3" justified>
      <b-tab title="내가 구매한 지식" active>
        <div class="row align-items-md-stretch">
          <div
            class="col-md-6"
            v-for="subscribe in subscribe_list"
            :key="subscribe"
          >
            <div class="h-100 p-5 text-white bg-dark rounded-3">
              <div class="row">
                <img
                  src="@/assets/logo.png"
                  class="d-inline-block align-middle"
                  width="100px"
                  alt="logos"
                />
                <h2>{{ subscribe.title }}</h2>
              </div>
              <p>구매 가격 : {{ subscribe.price }}</p>
              <p>시작 시간 : {{ subscribe.startTime }}</p>
              <p>종료 시간 : {{ subscribe.endTime }}</p>
              <button class="btn btn-outline-light" type="button">
                상세페이지로 이동
              </button>
            </div>
          </div>
        </div>
        <div class="mt-3">
          <b-pagination
            v-model="subscribeCurrentPage"
            pills
            align="center"
            :total-rows="subscribeRows"
            :per-page="perPage"
            @page-click="subscribePageClicked"
          ></b-pagination>
        </div>
      </b-tab>
      <b-tab title="내가 등록한 지식">
        <div class="row align-items-md-stretch">
          <div class="col-md-6" v-for="publish in publish_list" :key="publish">
            <div class="h-100 p-5 text-white bg-dark rounded-3">
              <div class="row">
                <img
                  src="@/assets/logo.png"
                  class="d-inline-block align-middle"
                  width="100px"
                  alt="logos"
                />
                <h2>{{ publish.title }}</h2>
              </div>
              <p>가격 : {{ publish.price }}</p>
              <p>시작 시간 : {{ publish.startTime }}</p>
              <p>종료 시간 : {{ publish.endTime }}</p>
              <button class="btn btn-outline-light" type="button">
                상세페이지로 이동
              </button>
            </div>
          </div>
        </div>
        <div class="mt-3">
          <b-pagination
            v-model="publishCurrentPage"
            pills
            align="center"
            :total-rows="publishRows"
            :per-page="perPage"
            @page-click="publishPageClicked"
          ></b-pagination>
        </div>
      </b-tab>
    </b-tabs>
  </div>
</template>
<script>
import { getSubscribed, getPublished } from "@/api/knowledge";
export default {
  name: "KnowledgeHistory",
  data() {
    return {
      subscribeCurrentPage: 0,
      publishCurrentPage: 0,
      perPage: 10,
      subscribeRows: 0,
      publishRows: 0,
      subscribe_list: [],
      publish_list: [],
    };
  },
  computed: {},
  created() {
    getSubscribed(
      this.currentPage,
      (response) => {
        this.subscribe_list = response.data.result.subscribeList;
        this.subscribeRows = this.perPage * response.data.result.totalPage;
        console.log(this.subscribe_list);
      },
      (error) => {
        console.log(error);
      }
    );
    getPublished(
      this.currentPage,
      (response) => {
        this.publish_list = response.data.result.publishList;
        this.publishRows = this.perPage * response.data.result.totalPage;
      },
      (error) => {
        console.log(error);
      }
    );
  },
  methods: {
    subscribePageClicked(bvEvent, page) {
      getSubscribed(
        page - 1,
        (response) => {
          this.subscribe_list = response.data.result.subscribeList;
          this.rows = this.perPage * response.data.result.totalPage;
        },
        (error) => {
          console.log(error);
        }
      );
    },
    publishPageClicked(bvEvent, page) {
      getPublished(
        page - 1,
        (response) => {
          this.publish_list = response.data.result.publishList;
          this.publishRows = this.perPage * response.data.result.totalPage;
        },
        (error) => {
          console.log(error);
        }
      );
    },
  },
};
</script>
