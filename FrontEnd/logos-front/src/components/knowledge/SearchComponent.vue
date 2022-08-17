<template>
  <b-container class="bv-example-row mt-3 text-center main-container">
    <h1>카테고리: {{ this.$route.query.category }}</h1>
    검색결과
    <b-row>
      <b-card-group columns>
        <b-card
          v-for="knowledge in knowledgeList"
          :key="knowledge.id"
          :title="knowledge.title"
          img-src="https://picsum.photos/300/200/?image=40"
          img-alt="Image"
          img-top
        >
          <b-card-text>
            <p>가격 : {{ knowledge.price }} 포인트</p>
            <p>시작 시간 : {{ knowledge.startTime | moment("LLLL") }}</p>
            <p>종료 시간 : {{ knowledge.endTime | moment("LLLL") }}</p>
            <b-row class="mt-3">
              <div class="col text-center">
                <b-button variant="primary" float>상세보기</b-button>
              </div>
            </b-row>
          </b-card-text>
          <template #footer>
            <small class="text-muted"
              >{{ knowledge.updated_at | moment("from", "now", true) }} 전에
              업데이트 됨</small
            >
          </template>
        </b-card>
      </b-card-group>
    </b-row>
  </b-container>
</template>

<script>
import { filterKnowledge } from "@/api/knowledge";
export default {
  data() {
    return {
      knowledgeList: [],
      category: this.$route.query.category,
    };
  },
  updated() {
    this.$nextTick(function () {
      this.category = this.$route.query.category;
      console.log("updated 호출 " + this.category);
    });
  },
  watch: {
    category: function () {
      console.log("watch 호출");
      filterKnowledge(
        { params: { category: this.category } },
        (response) => {
          console.log("카테고리 가져오기 완료");
          this.knowledgeList = response.data.knowledge_list;
        },
        () => {
          console.log("카테고리 가져오기 실패");
        }
      );
    },
  },
  mounted() {
    filterKnowledge(
      { params: { category: this.category } },
      (response) => {
        console.log("카테고리 가져오기 완료");
        this.knowledgeList = response.data.knowledge_list;
      },
      () => {
        console.log("카테고리 가져오기 실패");
      }
    );
  },
};
</script>
