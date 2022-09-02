import { pointInstance } from "./index.js";

const api = pointInstance();

async function getPointHistory(page, success, fail) {
  api.defaults.headers["Authorization"] =
    "Bearer " + sessionStorage.getItem("access-token");
  await api
    .get(`/point/history`, { params: { page: page } })
    .then(success)
    .catch(fail);
}

async function purchaseKnowledge(id, success, fail) {
  api.defaults.headers["Authorization"] =
    "Bearer " + sessionStorage.getItem("access-token");
  await api
    .post(`/point/` + id)
    .then(success)
    .catch(fail);
}

async function refundKnowledge(id, success, fail) {
  api.defaults.headers["Authorization"] =
    "Bearer " + sessionStorage.getItem("access-token");
  await api
    .delete(`/point/` + id)
    .then(success)
    .catch(fail);
}

export { getPointHistory, purchaseKnowledge, refundKnowledge };
