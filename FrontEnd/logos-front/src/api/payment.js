import { paymentInstance } from "./index.js";

const api = paymentInstance();

async function getPaymentHistory(page, success, fail) {
  api.defaults.headers["Authorization"] =
    "Bearer " + sessionStorage.getItem("access-token");
  await api
    .get(`/payment/history`, { params: { page: page } })
    .then(success)
    .catch(fail);
}

async function requestPayment(params, success, fail) {
  await api.post(`/payment`, params).then(success).catch(fail);
}

async function verifyPayment(params, success, fail) {
  api.defaults.headers.post["Authorization"] =
    "Bearer " + sessionStorage.getItem("access-token");
  await api.post(`/payment/verify`, params).then(success).catch(fail);
}

export { getPaymentHistory, requestPayment, verifyPayment };
