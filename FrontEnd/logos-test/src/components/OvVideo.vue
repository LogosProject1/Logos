<template>
  <video
    :id="username"
    ref="myWebCam"
    :class="{
      webcam: true,
    }"
    autoplay
    playsinline
    controls="false"
  />
</template>

<script>
export default {
  name: "OvVideo",
  props: {
    streamManager: Object,
    username: String,
  },
  mounted() {
    this.streamManager.addVideoElement(this.$el);
  },
};
</script>
<style scoped>
@import url("OvVideo.css");
</style>
