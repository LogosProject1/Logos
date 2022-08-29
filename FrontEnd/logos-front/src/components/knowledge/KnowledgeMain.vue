<template>
  <div>
    <b-card
      img-src="https://picsum.photos/400/200/?image=41"
      img-alt="Image"
      img-top
      class="jb-writer"
    >
      <b-card-text>
        <b-row class="justify-content-center">
          <small class="text-muted mb-3">지식 제공자 : {{ writer }} </small>
        </b-row>
        <b-button variant="primary">신청하기</b-button>
      </b-card-text>
      <template #footer>
        <small class="text-muted">신청 포인트 : {{ price }} </small>
      </template>
    </b-card>
    <b-card class="jb-content">
      <b-card-text>
        <viewer />
      </b-card-text>
    </b-card>
  </div>
</template>
<script>
import "@toast-ui/editor/dist/toastui-editor-viewer.css";
import { Viewer } from "@toast-ui/vue-editor";
export default {
  components: {
    viewer: Viewer,
  },
  props: {
    content: String,
    price: String,
    writer: String,
  },
};
</script>
<style>
.jb-writer {
  position: absolute;
  font-size: 20px;
  color: #ffffff;
  top: 10%;
  left: 90%;
  width: 30%;
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
