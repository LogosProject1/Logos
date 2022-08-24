<template>
  <b-container class="bv-example-row mt-3 text-center main-container">
    <h1 v-if="this.$route.query.category">
      카테고리: {{ this.$route.query.category }}
    </h1>
    <h1 v-else>카테고리: 전체</h1>
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
                <b-button
                  variant="primary"
                  float
                  :to="{ name: 'knowledge', params: { id: knowledge.id } }"
                  >상세보기</b-button
                >
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
      params: {
        category: this.$route.query.category,
        title: this.$route.query.title,
        minPrice: this.$route.query.minPrice,
        maxPrice: this.$route.query.maxPrice,
        startTime: this.$route.query.startTime,
        endTime: this.$route.query.endTime,
      },
    };
  },
  updated() {
    this.$nextTick(function () {
      this.params.category = this.$route.query.category;
      this.params.title = this.$route.query.title;
      this.params.minPrice = this.$route.query.minPrice;
      this.params.maxPrice = this.$route.query.maxPrice;
      this.params.startTime = this.$route.query.startTime;
      this.params.endTime = this.$route.query.endTime;
      console.log("updated 호출 ");
      console.log(this.params);
    });
  },
  watch: {
    params: {
      handler: function () {
        console.log("watch 호출");
        filterKnowledge(
          { params: this.params },
          (response) => {
            console.log("카테고리 가져오기 완료");
            this.knowledgeList = response.data.knowledge_list;
          },
          () => {
            console.log("카테고리 가져오기 실패");
          }
        );
      },
      deep: true,
    },
  },
  mounted() {
    filterKnowledge(
      { params: this.params },
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
