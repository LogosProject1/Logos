<template>
  <video
    :id="username"
    ref="myWebCam"
    @mouseover="showVideoMenu"
    :class="{
      webcam: true,
      'selected-border': isSelected,
      'died-user': isDead,
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
