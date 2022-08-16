<template>
  <div>
    <div class="jb-box" :style="customStyle" style>
      <b-container class="bv-example-row mt-3 text-center">
        <div class="jb-date">2022/08/16</div>
        <div class="jb-title">지식 제목</div>
        <div></div>
      </b-container>
    </div>
    <b-container>
      <b-card-group columns>
        <b-card
          title="Card title that wraps to a new line"
          img-src="https://placekitten.com/g/400/450"
          img-alt="Image"
          img-top
        >
          <b-card-text>
            This is a wider card with supporting text below as a natural lead-in
            to additional content. This content is a little bit longer.
          </b-card-text>
        </b-card>

        <b-card header="Quote">
          <blockquote class="blockquote mb-0">
            <p>
              Lorem ipsum dolor sit amet, consectetur adipiscing elit. Integer
              posuere erat a ante.
            </p>
            <footer class="blockquote-footer">
              Someone famous in <cite title="Source Title">Source Title</cite>
            </footer>
          </blockquote>
        </b-card>

        <b-card
          title="Title"
          img-src="https://placekitten.com/500/350"
          img-alt="Image"
          img-top
        >
          <b-card-text>
            This card has supporting text below as a natural lead-in to
            additional content.
          </b-card-text>
          <b-card-text class="small text-muted"
            >Last updated 3 mins ago</b-card-text
          >
        </b-card>

        <b-card bg-variant="primary" text-variant="white">
          <blockquote class="card-blockquote">
            <p>
              Lorem ipsum dolor sit amet, consectetur adipiscing elit. Integer
              posuere erat a ante.
            </p>
            <footer>
              <small
                >Someone famous in
                <cite title="Source Title">Source Title</cite></small
              >
            </footer>
          </blockquote>
        </b-card>

        <b-card>
          <b-card-title>Title</b-card-title>
          <b-card-text>
            This card has supporting text below as a natural lead-in to
            additional content.
          </b-card-text>
          <b-card-text class="small text-muted"
            >Last updated 3 mins ago</b-card-text
          >
        </b-card>

        <b-card
          img-src="https://picsum.photos/400/400/?image=41"
          img-alt="Image"
          overlay
        ></b-card>

        <b-card
          img-src="https://picsum.photos/400/200/?image=41"
          img-alt="Image"
          img-top
        >
          <b-card-text>
            This is a wider card with supporting text below as a natural lead-in
            to additional content. This card has even longer content than the
            first.
          </b-card-text>
          <template #footer>
            <small class="text-muted">Footer Text</small>
          </template>
        </b-card>
      </b-card-group>
    </b-container>
  </div>
</template>

<script>
export default {
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
  right: 31%;
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
</style>
