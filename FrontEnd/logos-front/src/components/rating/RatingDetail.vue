<template>
  <div class="container">
    <star-rating v-model="rating" :increment="0.5"></star-rating>
    {{ rating }}
    <div class="textarea">
      <b-form-textarea
        id="textarea-rows"
        placeholder="Tall textarea"
        rows="8"
      ></b-form-textarea>
    </div>
  </div>
</template>
<script>
import StarRating from "vue-star-rating";

export default {
  components: {
    StarRating,
  },
  data() {
    return {
      rating: 0,
    };
  },
  methods: {
    setRating: function (rating) {
      this.rating = rating;
      console.log(this.rating);
    },
  },
};
</script>
<style>
.container {
  text-align: center;
}
.textarea {
  width: 500px;
}
</style>
