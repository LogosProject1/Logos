<template>
  <div>
    <b-card :img-src="thumbnail" img-alt="Image" img-top class="jb-writer">
      <template #header v-if="thumbnail === null">
        <img src="@/assets/logo.png" height="200" alt="Image" top />
      </template>
      <b-card-text>
        <b-row class="justify-content-center">
          <small class="text-muted mb-3">지식 제공자 : {{ writer }} </small>
        </b-row>
        <b-button @click="clickEnrollButton" variant="primary"
          >신청하기</b-button
        >
      </b-card-text>
      <template #footer>
        <small class="text-muted">신청 포인트 : {{ price }} </small>
      </template>
    </b-card>
    <b-card class="jb-content">
      <b-card-text>
        <viewer ref="toastViewer" :options="editorOptions" />
      </b-card-text>
    </b-card>
  </div>
</template>
<script>
import "@toast-ui/editor/dist/toastui-editor-viewer.css";
import { Viewer } from "@toast-ui/vue-editor";
import { purchaseKnowledge } from "@/api/point";
export default {
  components: {
    viewer: Viewer,
  },
  props: {
    thumbnail: String,
    content: String,
    price: String,
    writer: String,
  },
  data() {
    return {
      editorOptions: {
        customHTMLRenderer: {
          htmlBlock: {
            iframe(node) {
              return [
                {
                  type: "openTag",
                  tagName: "iframe",
                  outerNewLine: true,
                  attributes: node.attrs,
                },
                { type: "html", content: node.childrenHTML },
                {
                  type: "closeTag",
                  tagName: "iframe",
                  outerNewLine: true,
                },
              ];
            },
          },
        },
      },
    };
  },
  updated() {
    this.$refs.toastViewer.invoke("setMarkdown", this.content);
  },
  methods: {
    clickEnrollButton() {
      if (sessionStorage.getItem("access-token") == null) {
        alert("로그인을 해주세요.");
        this.$router.push({ name: "signIn" });
      } else {
        purchaseKnowledge(
          this.$route.params.id,
          (res) => {
            alert(res.data.message);
            if (res.data.result === "TRUE") {
              this.$router.push({
                name: "profile",
              });
            }
          },
          (err) => {
            alert(err);
          }
        );
      }
    },
  },
};
</script>
<style>
.jb-writer {
  position: fixed;
  font-size: 20px;
  color: #ffffff;
  top: 44%;
  left: 75%;
  width: 15%;
}
.jb-content {
  position: absolute;
  font-size: 20px;
  top: 10%;
  right: 35%;
  width: 75%;
  min-height: 700px;
}
</style>
