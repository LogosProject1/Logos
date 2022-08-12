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
            <b-col>결제 금액 : {{ payment.amount }} </b-col>
            <b-col
              >결제 시작 시간 : {{ payment.startTime | moment("LLLL") }}</b-col
            >
            <b-col
              >결제 완료 시간 :{{ payment.endTime | moment("LLLL") }}
            </b-col>
            <b-col>
              <b-badge v-if="payment.result === `SUCCESS`" variant="primary"
                >결제 성공</b-badge
              >
              <b-badge v-if="payment.result === `FAILURE`" variant="danger"
                >결제 실패</b-badge
              >
              <b-badge v-if="payment.result === `PENDING`" variant="success"
                >처리중</b-badge
              >
            </b-col>
            <b-col> 결제 수단 : {{ payment.paymentType }} </b-col>
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
import { getPaymentHistory } from "@/api/payment";

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
    getPaymentHistory(
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
      getPaymentHistory(
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
