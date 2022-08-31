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
              <p>시작 시간 : {{ subscribe.startTime | moment("LLL") }}</p>
              <p>종료 시간 : {{ subscribe.endTime | moment("LLL") }}</p>
              <b-button
                class="btn btn-outline-light"
                variant="outlined"
                :to="{ name: 'knowledge', params: { id: subscribe.id } }"
              >
                상세페이지로 이동
              </b-button>
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
              <p>판매 가격 : {{ publish.price }}</p>
              <p>시작 시간 : {{ publish.startTime | moment("LLL") }}</p>
              <p>종료 시간 : {{ publish.endTime | moment("LLL") }}</p>
              <b-button
                class="btn btn-outline-light mr-2"
                variant="outlined"
                :to="{ name: 'knowledge', params: { id: publish.id } }"
              >
                상세페이지로 이동
              </b-button>
              <b-button class="btn btn-outline-light mr-2" variant="primary">
                수정하기
              </b-button>

              <b-button
                v-b-modal.modal-center
                class="btn btn-outline-light"
                variant="danger"
                >삭제하기</b-button
              >

              <b-modal id="modal-center" centered title="지식 삭제 확인 창">
                <p class="my-4">정말로 삭제하시겠습니까?</p>
                <template #modal-footer>
                  <b-button variant="primary" class="float-right">
                    닫기
                  </b-button>
                  <b-button
                    class="btn btn-outline-light"
                    :id="publish.id"
                    variant="danger"
                    @click="onDeleteClick"
                  >
                    삭제하기
                  </b-button>
                </template>
              </b-modal>
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
import { getSubscribed, getPublished, deleteKnowledge } from "@/api/knowledge";
//import { deleteImage } from "@/api/s3";
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
    onDeleteClick(e) {
      e.preventDefault();
      //img 파싱

      //s3 delete request
      //knowledge delete request
      deleteKnowledge(
        e.target.id,
        (res) => {
          if (res.message === "success") {
            this.$bvModal.msgBoxOk("성공적으로 삭제 되었습니다.", {
              title: "삭제 결과",
              size: "sm",
              okVariant: "primary",
              headerClass: "p-2 border-bottom-0",
              footerClass: "p-2 border-top-0",
              centered: true,
            });
          } else {
            this.$bvModal.msgBoxOk(
              "삭제에 실패하였습니다. 다시 시도해주세요.",
              {
                title: "삭제 결과",
                size: "sm",
                okVariant: "primary",
                headerClass: "p-2 border-bottom-0",
                footerClass: "p-2 border-top-0",
                centered: true,
              }
            );
          }
          this.show = false;
        },
        () => {
          alert("지식 삭제 중 오류 발생");
        }
      );
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
