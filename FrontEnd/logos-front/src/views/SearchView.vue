<template>
  <b-container class="bv-example-row mt-3 text-center main-container">
    검색결과
    <b-row>
      <b-card-group columns>
        <b-card
          title="제목"
          img-src="https://picsum.photos/300/200/?image=40"
          img-alt="Image"
          img-top
        >
          <b-card-text>
            애옹이
            <b-row class="mt-3">
              <div class="col text-center">
                <b-button variant="primary" float>상세보기</b-button>
              </div>
            </b-row>
          </b-card-text>
          <template #footer>
            <small class="text-muted">Last updated 3 mins ago</small>
          </template>
        </b-card>

        <b-card
          title="제목"
          img-src="https://picsum.photos/300/200/?image=41"
          img-alt="Image"
          img-top
        >
          <b-card-text>
            빗방울빗방울빗방울빗방울빗방울빗방울빗방울빗방울빗방울빗방울빗방울빗방울빗방울빗방울빗방울빗방울
            <b-row class="mt-3">
              <div class="col text-center">
                <b-button variant="primary" float>상세보기</b-button>
              </div>
            </b-row>
          </b-card-text>
          <template #footer>
            <small class="text-muted">Last updated 3 mins ago</small>
          </template>
        </b-card>

        <b-card
          title="Title"
          img-src="https://picsum.photos/300/200/?image=42"
          img-alt="Image"
          img-top
        >
          <b-card-text>
            100자 제한
            <b-row class="mt-3">
              <div class="col text-center">
                <b-button variant="primary" float>상세보기</b-button>
              </div>
            </b-row>
          </b-card-text>
          <template #footer>
            <small class="text-muted">Last updated 3 mins ago</small>
          </template>
        </b-card>
        <b-card
          title="Title"
          img-src="https://picsum.photos/300/200/?image=42"
          img-alt="Image"
          img-top
        >
          <b-card-text>
            가장 긴 텍스트에 높이가 맞춰짐
            <b-row class="mt-3">
              <div class="col text-center">
                <b-button variant="primary" float>상세보기</b-button>
              </div>
            </b-row>
          </b-card-text>
          <template #footer>
            <small class="text-muted">Last updated 3 mins ago</small>
          </template>
        </b-card>
        <b-card
          title="Title"
          img-src="https://picsum.photos/300/200/?image=42"
          img-alt="Image"
          img-top
        >
          <b-card-text>
            가장 긴 텍스트에 높이가 맞춰짐
            <b-row class="mt-3">
              <div class="col text-center">
                <b-button variant="primary" float>상세보기</b-button>
              </div>
            </b-row>
          </b-card-text>
          <template #footer>
            <small class="text-muted">Last updated 3 mins ago</small>
          </template>
        </b-card>
        <b-card
          title="Title"
          img-src="https://picsum.photos/300/200/?image=42"
          img-alt="Image"
          img-top
        >
          <b-card-text>
            가장 긴 텍스트에 높이가 맞춰짐
            <b-row class="mt-3">
              <div class="col text-center">
                <b-button variant="primary" float>상세보기</b-button>
              </div>
            </b-row>
          </b-card-text>
          <template #footer>
            <small class="text-muted">Last updated 3 mins ago</small>
          </template>
        </b-card>
      </b-card-group>
    </b-row>
  </b-container>
</template>

<script>
export default {
  name: "SearchView",
};
</script>

<style>
b-card-group {
}
</style>
