import { ratingInstance } from "./index.js";

const api = ratingInstance();

async function ratingKnowledge(id, params, success, fail) {
  api.defaults.headers["Authorization"] =
    "Bearer " + sessionStorage.getItem("access-token");
  await api
    .post(`/rating/` + id, params)
    .then(success)
    .catch(fail);
}

export { ratingKnowledge };
