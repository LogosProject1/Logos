<template>
  <div>
    <div
      class="d-flex justify-content-between flex-wrap flex-md-nowrap align-items-center pt-3 pb-2 mb-3 border-bottom"
    >
      <h1 class="h2">나의 지식</h1>
    </div>
    <b-tabs content-class="mt-3" justified>
      <b-tab title="내가 구매한 지식" active>
        <div class="row align-items-md-stretch">
          <div class="col-md-6">
            <div class="h-100 p-5 text-white bg-dark rounded-3">
              <div class="row">
                <img
                  src="@/assets/logo.png"
                  class="d-inline-block align-middle"
                  width="100px"
                  alt="logos"
                />
                <h2>지식 타이틀1</h2>
              </div>
              <p>설명, 구매날짜...</p>
              <button class="btn btn-outline-light" type="button">
                상세페이지로 이동
              </button>
            </div>
          </div>
          <div class="col-md-6">
            <div class="h-100 p-5 bg-light border rounded-3">
              <div class="row">
                <img
                  src="@/assets/logo.png"
                  class="d-inline-block align-middle"
                  width="100px"
                  alt="logos"
                />
                <h2>지식 타이틀2</h2>
              </div>
              <p>설명, 구매날짜...</p>
              <button class="btn btn-outline-secondary" type="button">
                상세페이지로 이동
              </button>
            </div>
          </div>
        </div>
      </b-tab>
      <b-tab title="내가 등록한 지식"><p>I'm the second tab</p></b-tab>
    </b-tabs>
  </div>
</template>
<script>
export default {
  name: "KnowledgeHistory",
};
</script>
