<template>
  <div class="jb-box">
    <video muted autoplay loop>
      <source src="../assets/main_page.mp4" type="video/mp4" />
      <strong>Your browser does not support the video tag.</strong>
    </video>
    <div class="jb-text">
      <p class="main_title">LOGOS</p>
      <div class="jb-description">
        <p class="main_description">
          여러분의 지식을 찾아보세요<br />
          교육, 엔터테이먼트, 라이프스타일까지 각 분야에서 이야기를 통해
          지식들을<br />
          경험하고 여러분의 이야기도 나누어 보세요<br />
        </p>
      </div>
    </div>
    <div class="jb-right">
      <p>최근 등록된 지식</p>
      <div>
        <b-carousel
          id="carousel-1"
          class="jb-carousel"
          :interval="4000"
          controls
          indicators
          background="#ababab"
          style="text-shadow: 1px 1px 2px #333"
        >
          <b-carousel-slide
            class="col-md-6 p-0"
            v-for="knowledge in recentKnowledge"
            :key="knowledge.id"
          >
            <template #img>
              <img
                class="d-block"
                :src="knowledge.thumbnail"
                width="500"
                height="300"
                alt="image slot"
              />
            </template>

            <h3 style="width: 420px">{{ knowledge.title }}</h3>
          </b-carousel-slide>
        </b-carousel>
      </div>
    </div>
    <b-button
      variant="light"
      size="lg"
      class="main_button"
      :to="{ name: 'search' }"
      >지금 찾아보기</b-button
    >
  </div>
</template>

<script>
import { getRecentKnowledge } from "@/api/knowledge";
export default {
  name: "HomeView",
  data() {
    return {
      recentKnowledge: [],
    };
  },
  mounted() {
    getRecentKnowledge(
      (res) => {
        this.recentKnowledge = res.data.knowledge_list;
      },
      () => {}
    );
  },
};
</script>

<style>
body {
  padding: 0px;
  margin: 0px;
}
.jb-box {
  width: 100%;
  height: 100vh;
  overflow: hidden;
  margin: 0px auto;
  position: relative;
}
video {
  width: 100%;
}
.main_button {
  font-family: "Do Hyeon", sans-serif;
  position: absolute;
  top: 45%;
  left: 16%;
}
.jb-text {
  position: absolute;
  top: 22%;
  right: 30%;
  width: 100%;
}
.main_title {
  margin-top: -24px;
  text-align: center;
  font-size: 80px;
  color: #ffffff;
}

.jb-description {
  position: absolute;
  margin-top: 10px;
  left: 43%;
}
.main_description {
  font-family: "Noto Han Sans", sans-serif;
  margin-left: 50px;
  text-align: left;
  color: #ffffff;
}

.jb-right {
  position: absolute;
  top: 25%;
  left: 20%;
  width: 100%;
  margin: auto;
}
.jb-right p {
  margin-top: -24px;
  text-align: center;
  font-size: 30px;
  color: #ffffff;
}
.jb-carousel {
  margin-top: 30px;
  left: 43%;
  width: 500px;
  height: 300px;
}
@import url("https://fonts.googleapis.com/css2?family=Black+Han+Sans&family=Do+Hyeon&family=Noto+Sans+KR&display=swap");
</style>
