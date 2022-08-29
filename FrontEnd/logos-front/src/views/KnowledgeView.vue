<template>
  <div>
    <div class="jb-box" :style="customStyle" style>
      <b-container class="bv-example-row mt-3 text-center">
        <div class="jb-date">
          {{ knowledgeData.updated_at | moment("LLL") }}
        </div>
        <div class="jb-title">{{ knowledgeData.title }}</div>
        <div class="jb-time">
          지식전달 시간 : {{ knowledgeData.startTime | moment("LLL") }} ~
          {{ knowledgeData.endTime | moment("LLL") }}
        </div>
      </b-container>
    </div>
    <b-container class="content">
      <knowledge-main
        :content="knowledgeData.content"
        :price="knowledgeData.price"
        :writer="knowledgeData.writer"
      ></knowledge-main>
    </b-container>
  </div>
</template>

<script>
import KnowledgeMain from "@/components/knowledge/KnowledgeMain.vue";
import { readKnowledge } from "@/api/knowledge";

export default {
  components: { KnowledgeMain },
  name: "KnowledgeView",
  computed: {
    customStyle() {
      return {
        "--jb-color":
          this.backgroundColor[
            Math.floor(Math.random() * (this.backgroundColor.length - 2)) + 1
          ], // testColor 는 예시 변수명
      };
    },
  },
  data() {
    return {
      knowledgeData: {},
      backgroundColor: [
        "linear-gradient(90deg, #355c7d 0%, #6c5b7b 50%, #c06c84 100%)",
        "linear-gradient(90deg, #667eea 0%, #764ba2 100%)",
        "linear-gradient(90deg, #005aa7 0%, #fffde4 100%)",
        "linear-gradient(90deg, #636363 0%, #a2ab58 100%)",
        "linear-gradient(90deg, #11998e 0%, #38ef7d 100%)",
        "linear-gradient(90deg, #ff9a9e 0%, #fecfef 100%)",
        "linear-gradient(90deg, #da4453 0%, #89216b 100%)",
        "linear-gradient(90deg, #23074d 0%, #cc5333 100%)",
      ],
    };
  },
  mounted() {
    readKnowledge(
      this.$route.params.id,
      (res) => {
        this.knowledgeData = res.data.knowledge;
        console.log(this.knowledgeData);
      },
      () => {}
    );
  },
};
</script>

<style>
.jb-box {
  width: 100%;
  height: 70vh;
  overflow: hidden;
  margin: 0px auto;
  position: relative;
  background: var(--jb-color);
}
.jb-date {
  position: absolute;
  font-size: 20px;
  color: #ffffff;
  top: 8%;
  right: 30%;
  width: 100%;
}
.jb-title {
  position: absolute;
  font-size: 30px;
  color: #ffffff;
  top: 15%;
  right: 30%;
  width: 100%;
}
.jb-time {
  position: absolute;
  font-size: 17px;
  color: #ffffff;
  top: 25%;
  right: 22%;
  width: 100%;
}
.content {
  position: absolute;
  top: 45%;
  left: 20%;
  /* margin: -64px 24px 0px 24px; */
}
</style>
