import { pointInstance } from "./index.js";

const api = pointInstance();

async function getHistory(page, success, fail) {
  api.defaults.headers["Authorization"] =
    "Bearer " + sessionStorage.getItem("access-token");
  await api
    .get(`/point/history`, { params: { page: page } })
    .then(success)
    .catch(fail);
}

export { getHistory };
