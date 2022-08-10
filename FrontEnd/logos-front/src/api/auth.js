import { loginInstance } from "./index.js";

const api = loginInstance();

async function login(user, success, fail) {
  await api.post(`/login`, JSON.stringify(user)).then(success).catch(fail);
}

async function register(user, success, fail) {
  api.defaults.headers["Authorization"] =
    "Bearer " + sessionStorage.getItem("access-token");
  await api.post(`/register`, JSON.stringify(user)).then(success).catch(fail);
}

async function findById(userid, success, fail) {
  api.defaults.headers["Authorization"] =
    "Bearer " + sessionStorage.getItem("access-token");
  await api.get(`/info/${userid}`).then(success).catch(fail);
}

async function checkId(userEmail, success, fail) {
  await api.get(`/check/${userEmail}`).then(success).catch(fail);
}

async function modify(modifyUser, success, fail) {
  api.defaults.headers["Authorization"] =
    "Bearer " + sessionStorage.getItem("access-token");
  await api
    .put(`/update`, JSON.stringify(modifyUser))
    .then(success)
    .catch(fail);
}

export { login, findById, checkId, modify, register };
