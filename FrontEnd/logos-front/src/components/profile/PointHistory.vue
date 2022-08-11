<template>
  <div>
    <div
      class="d-flex justify-content-between flex-wrap flex-md-nowrap align-items-center pt-3 pb-2 mb-3 border-bottom"
    >
      <h1 class="h2">포인트 이력</h1>
    </div>
    <b-container>
      <b-list-group>
        <b-list-group-item
          v-for="history in history_list"
          :key="history.id"
          button
        >
          <b-row>
            <b-col>증감량 : {{ history.amount }} </b-col>
            <b-col>남은 포인트 : {{ history.remain }}</b-col>
            <b-col> 날짜 :{{ history.modifyTime | moment("LLLL") }} </b-col>
            <b-col>
              <b-badge v-if="history.type === `INC`" variant="primary"
                >포인트 증가</b-badge
              >
              <b-badge v-if="history.type === `DESC`" variant="danger"
                >포인트 감소</b-badge
              ></b-col
            >
          </b-row>
        </b-list-group-item>
      </b-list-group>
    </b-container>

    <div class="mt-3">
      <b-pagination
        v-model="currentPage"
        pills
        align="center"
        :total-rows="rows"
        :per-page="perPage"
        @page-click="pageClicked"
      ></b-pagination>
    </div>
  </div>
</template>

<script>
import { getHistory } from "@/api/point";

export default {
  name: "PointHistory",
  data() {
    return {
      currentPage: 0,
      perPage: 10,
      rows: 0,
      history_list: [],
    };
  },
  computed: {},
  created() {
    getHistory(
      this.currentPage,
      (response) => {
        this.history_list = response.data.result.pointHistory;
        this.rows = this.perPage * response.data.result.totalPage;
      },
      (error) => {
        console.log(error);
      }
    );
  },
  methods: {
    pageClicked(bvEvent, page) {
      getHistory(
        page - 1,
        (response) => {
          this.history_list = response.data.result.pointHistory;
          this.rows = this.perPage * response.data.result.totalPage;
        },
        (error) => {
          console.log(error);
        }
      );
    },
  },
};
</script>
