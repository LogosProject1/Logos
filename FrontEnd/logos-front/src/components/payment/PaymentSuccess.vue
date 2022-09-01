<template>
  <div>
    <br />
    <h1>결제 완료</h1>
    <br />
    <hr class="hr-1" />
    <br />
    <br />
    <br />
    <br />
    <br />
    <br />
    <b-icon
      icon="check-circle-fill"
      variant="primary"
      animation="throb"
      font-scale="7.5"
    ></b-icon>
    <br />
    <br />
    <br />
    <br />
    <br />
    <span class="price">
      <b>{{ this.amount | price | strUnit }}</b>
    </span>
    <h3>결제가 완료되었습니다.</h3>
    <br />
    <br />
    <br />
    <br />
    <br />
    <hr class="hr-1" />
    <br />
    <br />
    <b-container class="bv-example-row">
      <b-row>
        <b-col class="title1"><h4>결제항목</h4></b-col>
        <b-col
          ><h4>{{ $route.params.amount }} LP</h4></b-col
        >
        <div class="w-100"></div>
        <b-col class="title1"><h4>결제수단</h4></b-col>
        <b-col
          ><h4>{{ $route.params.method }}</h4></b-col
        >
      </b-row>
    </b-container>
    <br />
    <br />
    <hr class="hr-1" />

    <br />
    <br />
    <b-button variant="primary" size="lg" class="button1" :to="{ name: 'home' }"
      >확인</b-button
    >
  </div>
</template>
<script>
export default {
  data() {
    return {
      amount: 0,
      method: "",
    };
  },
  mounted() {
    this.amount = this.$route.params.amount;
    this.method = this.$route.params.method;
  },
  filters: {
    // 3자리마다 콤마 찍기
    price(value) {
      if (!value) return value;
      return value.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",");
    },
    strUnit(value) {
      return `${value} 원`;
    },
  },
};
</script>
<style scoped>
hr.hr-1 {
  border: 0;
  height: 1px;
  background-image: linear-gradient(
    to right,
    rgba(143, 132, 132, 0),
    rgba(134, 130, 130, 0.75),
    rgba(126, 122, 122, 0)
  );
}
.price {
  font-size: 55px;
}
.title1 {
  color: rgb(156, 146, 146);
}
.button1 {
  width: 150px;
}

/* .icon {
  animation-iteration-count: 1;
} */
</style>
