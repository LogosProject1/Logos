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
                class="btn btn-outline-light"
                variant="outlined"
                :to="{ name: 'knowledge', params: { id: subscribe.id } }"
              >
                상세페이지로 이동
              </b-button>
              <b-button
                class="btn btn-outline-light"
                variant="danger"
                :id="subscribe.id"
                @click="clickRefundButton"
                >환불받기</b-button
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
      </b-tab>
      <b-tab title="내가 등록한 지식">
        <div class="row align-items-md-stretch">
          <div class="col-md-6" v-for="publish in publish_list" :key="publish">
            <div class="h-100 p-5 text-white bg-dark rounded-3">
              <div class="row">
                <b-img
                  thumbnail
                  fluid
                  :src="publish.thumbnail"
                  class="d-inline-block align-middle"
                  width="150"
                  height="100"
                  alt="Image 1"
                ></b-img>
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
              <b-button
                class="btn btn-outline-light mr-2"
                variant="primary"
                :to="{ name: 'knowledgeUpdate', params: { id: publish.id } }"
              >
                수정하기
              </b-button>

              <b-button
                class="btn btn-outline-light"
                variant="danger"
                :id="publish.id"
                @click="onDeleteClick"
                >삭제하기</b-button
              >
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
import {
  getSubscribed,
  getPublished,
  deleteKnowledge,
  readKnowledge,
} from "@/api/knowledge";
import { deleteImage } from "@/api/s3";
import { refundKnowledge } from "@/api/point";
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
    clickRefundButton(e) {
      e.preventDefault();
      this.$bvModal
        .msgBoxConfirm("정말로 지식을 환불받으시겠습니까?", {
          title: "다시 확인해주세요.",
          size: "sm",
          okVariant: "danger",
          okTitle: "환불받기",
          cancelTitle: "아니요",
          footerClass: "p-2",
          hideHeaderClose: false,
          centered: true,
        })
        .then(async (value) => {
          if (value) {
            await refundKnowledge(
              e.target.id,
              (res) => {
                if (res.data.result) {
                  this.$bvModal
                    .msgBoxOk(res.data.message, {
                      title: "환불 결과",
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
                  this.$bvModal.msgBoxOk(res.data.message, {
                    title: "환불 결과",
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
                  "환불에 실패하였습니다. 다시 시도해주세요.",
                  {
                    title: "환불 결과",
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
    onDeleteClick(e) {
      e.preventDefault();
      this.$bvModal
        .msgBoxConfirm("정말로 지식을 삭제하시겠습니까?", {
          title: "다시 확인해주세요.",
          size: "sm",
          okVariant: "danger",
          okTitle: "삭제하기",
          cancelTitle: "아니요",
          footerClass: "p-2",
          hideHeaderClose: false,
          centered: true,
        })
        .then(async (value) => {
          if (value) {
            //knowledge read request & img tag parsing
            let deleteParams = [];

            await readKnowledge(
              e.target.id,
              (res) => {
                const regex =
                  // eslint-disable-next-line no-useless-escape
                  /(<img[^>]*src\s*=\s*[\"']?([^>\"']+)[\"']?[^>]*>)/g;
                while (regex.test(res.data.knowledge.content)) {
                  const splited = RegExp.$2.trim().split("/");
                  deleteParams.push(splited[4] + "/" + splited[5]);
                }
                const thumbnailSplited =
                  res.data.knowledge.thumbnail.split("/");
                deleteParams.push(
                  thumbnailSplited[4] + "/" + thumbnailSplited[5]
                );
              },
              (err) => {
                console.log("지식 가져오기 중 오류 발생", err);
              }
            );

            //s3 delete request
            if (deleteParams.length > 0) {
              await deleteImage(
                deleteParams,
                () => {
                  console.log("image delete success");
                },
                (error) => {
                  console.log("image delete error", error);
                }
              );
            }

            //knowledge delete request
            await deleteKnowledge(
              e.target.id,
              (res) => {
                if (res.data.message === "success") {
                  this.$bvModal
                    .msgBoxOk("성공적으로 삭제 되었습니다.", {
                      title: "삭제 결과",
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
              },
              () => {
                alert("지식 삭제 중 오류 발생");
              }
            );
          }
        });
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
