import { sessionInstance } from "./index.js";

const api = sessionInstance();

async function joinSession(params, success, fail) {
  api.defaults.headers.post["Authorization"] =
    "Bearer " + sessionStorage.getItem("access-token");
  await api.post(`/session`, params).then(success).catch(fail);
}

async function removeUser(params, success, fail) {
  await api.post(`/remove-user`, params).then(success).catch(fail);
}

export { joinSession, removeUser };
