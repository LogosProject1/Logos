<template>
  <div>
    <div
      class="d-flex justify-content-between flex-wrap flex-md-nowrap align-items-center pt-3 pb-2 mb-3 border-bottom"
    >
      <h1 class="h2">결제 내역</h1>
    </div>
    <b-container>
      <b-list-group>
        <b-list-group-item
          v-for="payment in payment_history"
          :key="payment.id"
          button
        >
          <b-row>
            <b-col>증감량 : {{ payment.amount }} </b-col>
            <b-col
              >결제 시작 시간 : {{ payment.startTime | moment("LLLL") }}</b-col
            >
            <b-col
              >결제 완료 시간 :{{ payment.endTime | moment("LLLL") }}
            </b-col>
            <b-col>
              <b-badge v-if="history.type === `INC`" variant="primary"
                >포인트 증가</b-badge
              >
              <b-badge v-if="history.type === `DESC`" variant="danger"
                >포인트 감소</b-badge
              ></b-col
            >
            <b-col> </b-col>
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
import { getPointHistory } from "@/api/point";

export default {
  name: "PointHistory",
  data() {
    return {
      currentPage: 0,
      perPage: 10,
      rows: 0,
      payment_history: [],
    };
  },
  computed: {},
  created() {
    getPointHistory(
      this.currentPage,
      (response) => {
        this.payment_history = response.data.result.paymentHistory;
        this.rows = this.perPage * response.data.result.totalPage;
      },
      (error) => {
        console.log(error);
      }
    );
  },
  methods: {
    pageClicked(bvEvent, page) {
      getPointHistory(
        page - 1,
        (response) => {
          this.payment_history = response.data.result.paymentHistory;
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
