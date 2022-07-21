<template>
  <div v-if="streamManager">
    <div class="user-video">
      <ov-video :username="state.clientData" :stream-manager="streamManager" />
      <div class="nameTag">
        <p>{{ state.clientData }}</p>
      </div>
    </div>
  </div>
</template>

<script>
import OvVideo from "./OvVideo";
import { computed, reactive } from "vue";
export default {
  name: "UserVideo",
  components: {
    OvVideo,
  },
  data() {
    return {
      clientData2: "",
    };
  },
  props: {
    streamManager: Object,
  },

  setup(props) {
    const state = reactive({
      clientData: computed(() => {
        const { connection } = props.streamManager.stream;
        const clientData = JSON.parse(
          connection.data.split("%/%")[0]
        ).clientData;
        console.log(
          "Test: " + JSON.parse(connection.data.split("%/%")[0]).clientData
        );
        return clientData;
      }),
    });
    return { state };
  },
};
</script>
<style scoped>
.user-video {
  position: relative;
}
.nameTag {
  border-radius: 10px;
  padding: 6px 10px;
  background-color: rgba(255, 255, 255, 0.8);
  font-family: "Jua";
  font-size: 1vw;
  position: absolute;
  top: 7%;
  left: 4%;
}

.nameTag > p {
  margin: 0px;
}
</style>
