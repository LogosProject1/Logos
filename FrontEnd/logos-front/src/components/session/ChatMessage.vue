<template>
  <div class="message right ng-star-inserted">
    <div class="msg-detail">
      <div class="nickname-container">
        <p class="ng-star-inserted">{{ nickname }}</p>
      </div>
      <div class="msg-content">
        <pre>{{ chatData.data }}</pre>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: "ChatMessage",
  props: {
    chatData: Object,
  },
  computed: {
    nickname() {
      const nickname = this.getNickname();
      return nickname;
    },
  },
  methods: {
    getNickname() {
      return (
        JSON.parse(this.chatData.from.data.split("%/%")[0]).clientData +
        "/" +
        JSON.parse(this.chatData.from.data.split("%/%")[1]).serverData
      );
    },
  },
};
</script>
