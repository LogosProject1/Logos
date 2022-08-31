<template>
  <b-container>
    <h2>지식 수정하기</h2>
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
import { uploadImage, deleteImage } from "@/api/s3";
import "@toast-ui/editor/dist/toastui-editor.css";

import moment from "moment";
import "vue2-daterange-picker/dist/vue2-daterange-picker.css";
import "tui-color-picker/dist/tui-color-picker.css";
import "@toast-ui/editor-plugin-color-syntax/dist/toastui-editor-plugin-color-syntax.css";
import colorSyntax from "@toast-ui/editor-plugin-color-syntax";
import DateRangePicker from "vue2-daterange-picker";
import { Editor } from "@toast-ui/vue-editor";
import { readKnowledge } from "@/api/knowledge";

export default {
  components: { editor: Editor, DateRangePicker },
  data() {
    return {
      created: false,
      title: "",
      category: "null",
      point: "",
      url: "",
      youtubeUrl: "",
      youtubeUrls: [],
      uploadImages: [],
      knowledgeData: [],
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
        plugins: [[colorSyntax], [this.youtubePlugin]],
        customHTMLRenderer: {
          htmlBlock: {
            iframe(node) {
              console.log(node.attrs);
              return [
                {
                  type: "openTag",
                  tagName: "iframe",
                  outerNewLine: true,
                  attributes: node.attrs,
                },
                { type: "html", content: node.childrenHTML },
                { type: "closeTag", tagName: "iframe", outerNewLine: true },
              ];
            },
          },
        },
        hooks: {
          addImageBlobHook: async (blob, callback) => {
            await this.imageUpload(blob);
            callback(this.url, "img");
            console.log(this.uploadImages);
            console.log(this.$refs.toastuiEditor.invoke("getHTML"));
            return false;
          },
        },
      },
    };
  },
  async beforeRouteLeave(to, from, next) {
    if (!this.created) {
      const answer = window.confirm(
        "저장되지 않은 작업이 있습니다. 정말 나갈까요?"
      );
      if (answer) {
        //이미지 배열 S3 Delete
        let deleteParams = [];
        for (let i = 0; i < this.uploadImages.length; i++) {
          const splited = this.uploadImages[i].split("/");
          deleteParams.push(splited[4] + "/" + splited[5]);
        }
        await deleteImage(
          deleteParams,
          () => {
            console.log("image delete success");
          },
          (error) => {
            console.log("image delete error", error);
          }
        );
        next();
      } else {
        next(false);
      }
    }
    next();
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
  methods: {
    youtubePlugin() {
      const container = document.createElement("div");
      container.className = "youtube-div";
      container.id = "youtube-div";

      const youtubeInput = document.createElement("input");
      youtubeInput.className = "youtube-input";
      youtubeInput.id = "youtube-input";
      youtubeInput.placeholder = "유튜브 링크";

      const youtubeButton = document.createElement("button");
      youtubeButton.className = "youtube-button";
      youtubeButton.id = "youtube-button";

      let btnText = document.createTextNode("확인");

      container.appendChild(youtubeInput);
      container.appendChild(youtubeButton);

      youtubeButton.appendChild(btnText);
      youtubeButton.onclick = () => {
        const value = document.getElementById("youtube-input").value;

        if (!value) {
          youtubeInput.value = "";
          return;
        }
        if (!value.includes("https://youtu.be/")) {
          alert("'https://youtu.be/' 를 포함한 링크만 가능합니다.");
          youtubeInput.value = "";
          return;
        }

        const youtubeUrl = value.split("/")[3];
        this.youtubeUrl = youtubeUrl;
        this.youtubeUrls = [...this.youtubeUrls, this.youtubeUrl];

        const getHTML = this.$refs.toastuiEditor.invoke("getHTML");
        this.$refs.toastuiEditor.invoke(
          "setHTML",
          getHTML +
            `<iframe width="560" height="315" src='https://www.youtube.com/embed/${this.youtubeUrl}' title="YouTube video player" frameborder="0" allow="accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture"></iframe>`
        );

        youtubeInput.value = "";
      };

      return {
        toolbarItems: [
          {
            groupIndex: 3,
            itemIndex: 3,
            item: {
              name: "youtube",
              tooltip: "유튜브 링크",
              className: "youtube",
              popup: {
                className: "toastui-editor-popup-add-link-youtube",
                body: container,
                style: { width: "400" },
              },
            },
          },
        ],
      };
    },

    async clickCreateButton() {
      let content = this.$refs.toastuiEditor.invoke("getHTML");
      let params = {
        title: this.title,
        category: this.category,
        price: this.point,
        content: content,
        startTime: moment(this.dateRange.startDate).format("YYYY-MM-DDTHH:mm"),
        endTime: moment(this.dateRange.endDate).format("YYYY-MM-DDTHH:mm"),
      };

      //1.먼저 uploadImages배열이랑 content를 비교
      let deleteParams = [];
      for (let i = 0; i < this.uploadImages.length; i++) {
        if (!content.includes(this.uploadImages[i])) {
          const splited = this.uploadImages[i].split("/");
          deleteParams.push(splited[4] + "/" + splited[5]);
        }
      }
      //없는 uploadImages 요소를 s3 delete
      await deleteImage(
        deleteParams,
        () => {
          console.log("image delete success");
        },
        (error) => {
          console.log("image delete error", error);
        }
      );

      await createKnowledge(
        params,
        () => {
          //2. knowledge에 post 요청
          alert("지식 생성 성공");
          this.created = true;
          this.$router.push("/");
        },
        (err) => {
          console.log(err);
          alert("지식 생성 실패");
        }
      );
    },
    async imageUpload(file) {
      await uploadImage(
        file,
        (res) => {
          this.uploadImages.push(res.data.url);
          this.url = res.data.url;
        },
        () => {}
      );
    },
  },
};
</script>
<style></style>
