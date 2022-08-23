<template>
  <b-container>
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
      :options="editorOptions"
      height="700px"
      initialEditType="wysiwyg"
      previewStyle="vertical"
    />
    <b-button class="mt-3 mb-3 float-right" variant="primary"
      >생성하기</b-button
    >
  </b-container>
</template>
<script>
import "@toast-ui/editor/dist/toastui-editor.css";
import "vue2-daterange-picker/dist/vue2-daterange-picker.css";
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
      },
    };
  },
};
</script>
<style></style>
