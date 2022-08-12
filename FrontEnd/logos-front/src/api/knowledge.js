import { knowledgeInstance } from "./index.js";

const api = knowledgeInstance();

async function getSubscribed(page, success, fail) {
  api.defaults.headers["Authorization"] =
    "Bearer " + sessionStorage.getItem("access-token");
  await api
    .get(`/knowledge/subscribed`, { params: { page: page } })
    .then(success)
    .catch(fail);
}

async function getPublished(page, success, fail) {
  api.defaults.headers["Authorization"] =
    "Bearer " + sessionStorage.getItem("access-token");
  await api
    .get(`/knowledge/published`, { params: { page: page } })
    .then(success)
    .catch(fail);
}

export { getSubscribed, getPublished };