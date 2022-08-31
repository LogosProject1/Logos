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

async function deleteKnowledge(params, success, fail) {
  api.defaults.headers["Authorization"] =
    "Bearer " + sessionStorage.getItem("access-token");
  await api
    .delete(`/knowledge/delete`, { data: params })
    .then(success)
    .catch(fail);
}

async function filterKnowledge(params, success, fail) {
  api.defaults.headers["Authorization"] =
    "Bearer " + sessionStorage.getItem("access-token");
  await api.get(`/knowledge/filter`, params).then(success).catch(fail);
}

async function createKnowledge(params, success, fail) {
  api.defaults.headers["Authorization"] =
    "Bearer " + sessionStorage.getItem("access-token");
  await api.post(`/knowledge`, params).then(success).catch(fail);
}

async function readKnowledge(id, success, fail) {
  api.defaults.headers["Authorization"] =
    "Bearer " + sessionStorage.getItem("access-token");
  await api
    .get(`/knowledge/` + id)
    .then(success)
    .catch(fail);
}

async function updateKnowledge(params, success, fail) {
  api.defaults.headers["Authorization"] =
    "Bearer " + sessionStorage.getItem("access-token");
  await api.put(`/knowledge/update`, params).then(success).catch(fail);
}

export {
  getSubscribed,
  getPublished,
  deleteKnowledge,
  filterKnowledge,
  createKnowledge,
  readKnowledge,
  updateKnowledge,
};
