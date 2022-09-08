<template>
  <div>
    <div
      class="d-flex justify-content-between flex-wrap flex-md-nowrap align-items-center pt-3 pb-2 mb-3 border-bottom"
    >
      <h1 class="h2">평가하기</h1>
    </div>
    <div class="row align-items-md-stretch">
      <div
        v-if="subscribe_list === [] || subscribe_list.length === 0"
        style="margin: auto"
      >
        <div>
          <svg
            xmlns="http://www.w3.org/2000/svg"
            width="50"
            height="50"
            fill="currentColor"
            class="bi bi-x-circle"
            viewBox="0 0 16 16"
          >
            <path
              d="M8 15A7 7 0 1 1 8 1a7 7 0 0 1 0 14zm0 1A8 8 0 1 0 8 0a8 8 0 0 0 0 16z"
            />
            <path
              d="M4.646 4.646a.5.5 0 0 1 .708 0L8 7.293l2.646-2.647a.5.5 0 0 1 .708.708L8.707 8l2.647 2.646a.5.5 0 0 1-.708.708L8 8.707l-2.646 2.647a.5.5 0 0 1-.708-.708L7.293 8 4.646 5.354a.5.5 0 0 1 0-.708z"
            />
          </svg>
          <div class="m-3">구매하신 지식이 없습니다.</div>
        </div>
      </div>
      <div
        class="col-md-6"
        v-for="subscribe in subscribe_list"
        :key="subscribe.id"
      >
        <div class="h-100 p-5 text-white bg-dark rounded-3">
          <div class="row">
            <img
              :src="subscribe.thumbnail"
              class="d-inline-block align-middle"
              width="100px"
              alt="logos"
            />
            <h2>{{ subscribe.title }}</h2>
          </div>
          <p>구매 가격 : {{ subscribe.price }}</p>
          <p>시작 시간 : {{ subscribe.startTime | moment("LLL") }}</p>
          <p>종료 시간 : {{ subscribe.endTime | moment("LLL") }}</p>
          <b-button
            class="btn btn-outline-light mr-2"
            variant="outlined"
            :to="{ name: 'knowledge', params: { id: subscribe.id } }"
          >
            상세페이지로 이동
          </b-button>
          <b-button
            class="btn btn-outline-light mr-2"
            variant="success"
            :id="subscribe.id"
            @click="clickRatingButton"
            >평가하기</b-button
          >
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
  </div>
</template>
<script>
import { getSubscribed } from "@/api/knowledge";
import { ratingKnowledge } from "@/api/rating";
import StarRating from "vue-star-rating";
export default {
  name: "RatingComponent",
  component: {
    StarRating,
  },
  data() {
    return {
      subscribeCurrentPage: 0,
      perPage: 10,
      subscribeRows: 0,
      publishRows: 0,
      subscribe_list: [],
      rating: 0,
      content: null,
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
  },
  methods: {
    clickRatingButton(e) {
      e.preventDefault();
      const h = this.$createElement;
      const messageVNode = h("div", { class: ["container"] }, [
        h(StarRating, {
          props: {
            increment: 0.5,
            "show-rating": false,
          },
          on: {
            "rating-selected": this.starClick,
          },
        }),
        h("div", { class: ["textarea"] }, [
          h("b-form-textarea", {
            props: {
              id: "textarea-rows",
              placeholder: "Tall textarea",
              rows: "8",
            },
            on: {
              change: this.textAreaChange,
            },
          }),
        ]),
      ]);

      this.$bvModal
        .msgBoxConfirm([messageVNode], {
          title: "별점과 평가 내용을 입력해주세요",
          size: "md",
          okVariant: "danger",
          okTitle: "평가하기",
          cancelTitle: "아직 안할래요",
          footerClass: "p-2",
          hideHeaderClose: false,
          centered: true,
        })
        .then(async (value) => {
          if (value) {
            await ratingKnowledge(
              e.target.id,
              { rate: this.rating, content: this.content },
              (res) => {
                if (res.data.ratingResult.result) {
                  this.$bvModal
                    .msgBoxOk(res.data.ratingResult.message, {
                      title: "평가 결과",
                      size: "sm",
                      okVariant: "primary",
                      headerClass: "p-2 border-bottom-0",
                      footerClass: "p-2 border-top-0",
                      centered: true,
                    })
                    .then((value) => {
                      if (value) {
                        window.location.reload(true);
                      }
                    });
                } else {
                  this.$bvModal.msgBoxOk(res.data.ratingResult.message, {
                    title: "평가 결과",
                    size: "sm",
                    okVariant: "primary",
                    headerClass: "p-2 border-bottom-0",
                    footerClass: "p-2 border-top-0",
                    centered: true,
                  });
                }
              },
              () => {
                this.$bvModal.msgBoxOk(
                  "평가에 실패하였습니다. 다시 시도해주세요.",
                  {
                    title: "평가 결과",
                    size: "sm",
                    okVariant: "primary",
                    headerClass: "p-2 border-bottom-0",
                    footerClass: "p-2 border-top-0",
                    centered: true,
                  }
                );
              }
            );
          }
        });
    },
    starClick(value) {
      this.rating = 50 + value * 10;
      console.log(this.rating);
      console.log(this.content);
    },
    textAreaChange(value) {
      this.content = value;
    },
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
  },
};
</script>
