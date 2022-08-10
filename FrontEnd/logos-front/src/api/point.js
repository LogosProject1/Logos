import { pointInstance } from "./index.js";

const api = pointInstance();

async function getHistory(success, fail) {
  api.defaults.headers["Authorization"] =
    "Bearer " + sessionStorage.getItem("access-token");
  await api.get(`/point/history`).then(success).catch(fail);
}

export { getHistory };
