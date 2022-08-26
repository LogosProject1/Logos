<template>
  <b-container>
    <h2>지식 등록하기</h2>
    <br />
    <b-input-group prepend="제목" class="mb-2">
      <b-form-input
        v-model="title"
        aria-label="title"
        placeholder="제목을 입력해주세요."
      ></b-form-input>
    </b-input-group>
    <b-input-group prepend="카테고리" class="mb-2">
      <b-form-select v-model="category" :options="categoryOptions"
        ><template #first>
          <b-form-select-option :value="null" disabled>
            카테고리를 선택해주세요.
          </b-form-select-option>
        </template>
      </b-form-select>
    </b-input-group>
    <b-input-group prepend="포인트" class="mb-2">
      <b-form-input
        v-model="point"
        aria-label="point"
        placeholder="받고 싶은 포인트 양을 입력해주세요."
      ></b-form-input>
    </b-input-group>
    <div id="date">
      <b-input-group prepend="시작 날짜" class="mb-2">
        <date-range-picker
          v-model="dateRange"
          :timePicker="true"
          :timePicker24Hour="true"
          opens="right"
        >
          <template v-slot:input="picker">
            {{ picker.startDate | moment("YYYY-MM-DD HH:mm") }} -
            {{ picker.endDate | moment("YYYY-MM-DD HH:mm") }}
          </template>
        </date-range-picker>
      </b-input-group>
    </div>
    <editor
      ref="toastuiEditor"
      :options="editorOptions"
      height="700px"
      initialEditType="wysiwyg"
      previewStyle="vertical"
    />
    <b-button
      class="mt-3 mb-3 float-right"
      variant="primary"
      @click="clickCreateButton"
      >생성하기</b-button
    >
  </b-container>
</template>
<script>
import { createKnowledge } from "@/api/knowledge";
import { uploadImage } from "@/api/s3";
import "@toast-ui/editor/dist/toastui-editor.css";

import moment from "moment";
import "vue2-daterange-picker/dist/vue2-daterange-picker.css";
import "tui-color-picker/dist/tui-color-picker.css";
import "@toast-ui/editor-plugin-color-syntax/dist/toastui-editor-plugin-color-syntax.css";
import colorSyntax from "@toast-ui/editor-plugin-color-syntax";
import DateRangePicker from "vue2-daterange-picker";
import { Editor } from "@toast-ui/vue-editor";

export default {
  components: { editor: Editor, DateRangePicker },
  data() {
    return {
      title: "",
      category: "null",
      point: "",
      uploadImages: [],
      categoryOptions: [
        { value: "IT", text: "IT" },
        { value: "음악", text: "음악" },
        { value: "금융", text: "금융" },
        { value: "요리", text: "요리" },
        { value: "드로잉", text: "드로잉" },
        { value: "외국어", text: "외국어" },
        { value: "사진/영상", text: "사진/영상" },
      ],
      dateRange: {
        startDate: undefined,
        endDate: undefined,
      },
      editorOptions: {
        hideModeSwitch: true,
        plugins: [colorSyntax],
        hooks: {
          addImageBlobHook: this.addImageBlobHook,
        },
      },
    };
  },
  beforeRouteLeave(to, from, next) {
    const answer = window.confirm(
      "저장되지 않은 작업이 있습니다. 정말 나갈까요?"
    );
    if (answer) {
      //이미지 배열 싹다 날리기
      next();
    } else {
      next(false);
    }
  },
  methods: {
    clickCreateButton() {
      let content = this.$refs.toastuiEditor.invoke("getMarkdown");
      let params = {
        title: this.title,
        category: this.category,
        price: this.point,
        content: content,
        startTime: moment(this.dateRange.startDate).format("YYYY-MM-DDTHH:mm"),
        endTime: moment(this.dateRange.endDate).format("YYYY-MM-DDTHH:mm"),
      };

      createKnowledge(
        params,
        () => {
          alert("지식 생성 성공");
          this.$router.push("/");
        },
        () => {
          alert("지식 생성 실패");
        }
      );
    },
    async imageUpload(file) {
      console.log(file);
      //우리 api
      uploadImage(
        file,
        (res) => {
          this.uploadImages.push(res.data.url);
          return res.data.url;
        },
        () => {}
      );
    },

    addImageBlobHook(blob, callback) {
      this.imageUpload(blob)
        .then((url) => {
          callback(url, "img");
        })
        .catch(console.error);
    },
  },
};
</script>
<style></style>
