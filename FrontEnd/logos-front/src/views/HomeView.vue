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
      <div class="jb-right-row">
        <b-carousel
          id="carousel-1"
          class="jb-carousel"
          v-model="slide"
          :interval="4000"
          controls
          indicators
          background="#ababab"
          img-width="1024"
          img-height="480"
          style="text-shadow: 1px 1px 2px #333"
          @sliding-start="onSlideStart"
          @sliding-end="onSlideEnd"
        >
          <!-- Text slides with image -->
          <b-carousel-slide
            caption="First slide"
            text="Nulla vitae elit libero, a pharetra augue mollis interdum."
            img-src="https://picsum.photos/1024/480/?image=52"
          ></b-carousel-slide>

          <!-- Slides with custom text -->
          <b-carousel-slide img-src="https://picsum.photos/1024/480/?image=54">
            <h1>Hello world!</h1>
          </b-carousel-slide>

          <!-- Slides with image only -->
          <b-carousel-slide
            img-src="https://picsum.photos/1024/480/?image=58"
          ></b-carousel-slide>

          <!-- Slides with img slot -->
          <!-- Note the classes .d-block and .img-fluid to prevent browser default image alignment -->
          <b-carousel-slide>
            <template #img>
              <img
                class="d-block img-fluid w-100"
                width="1024"
                height="480"
                src="https://picsum.photos/1024/480/?image=55"
                alt="image slot"
              />
            </template>
          </b-carousel-slide>

          <!-- Slide with blank fluid image to maintain slide aspect ratio -->
          <b-carousel-slide
            caption="Blank Image"
            img-blank
            img-alt="Blank image"
          >
            <p>
              Lorem ipsum dolor sit amet, consectetur adipiscing elit.
              Suspendisse eros felis, tincidunt a tincidunt eget, convallis vel
              est. Ut pellentesque ut lacus vel interdum.
            </p>
          </b-carousel-slide>
        </b-carousel>
      </div>
    </div>
    <b-button variant="light" size="lg" class="main_button"
      >지금 찾아보기</b-button
    >
  </div>
</template>

<script>
export default {
  name: "HomeView",
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
}
.jb-right p {
  margin-top: -24px;
  text-align: center;
  font-size: 30px;
  color: #ffffff;
}
.jb-carousel {
  margin-top: 30px;
  width: 30%;
  left: 43%;
}
@import url("https://fonts.googleapis.com/css2?family=Black+Han+Sans&family=Do+Hyeon&family=Noto+Sans+KR&display=swap");
</style>
