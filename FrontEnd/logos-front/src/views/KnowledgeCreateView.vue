<template>
  <b-container>
    <h2>지식 등록하기</h2>
    <br />
    <b-input-group prepend="제목" class="mb-2">
      <b-form-input aria-label="title"></b-form-input>
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
            {{ picker.startDate | moment("YYYY-MM-DD HH:mm") }} - <br />{{
              picker.endDate | moment("YYYY-MM-DD HH:mm")
            }}
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
      @click="createAction"
      >생성하기</b-button
    >
  </b-container>
</template>
<script>
import "@toast-ui/editor/dist/toastui-editor.css";
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
      dateRange: {
        startDate: undefined,
        endDate: undefined,
      },
      editorOptions: {
        hideModeSwitch: true,
        plugins: [colorSyntax],
      },
    };
  },
  methods: {
    createAction() {
      var content = this.$refs.toastuiEditor.invoke("getMarkdown");
      // content를 저장하는 액션 처리
      console.log(content);
    },
  },
};
</script>
<style></style>
