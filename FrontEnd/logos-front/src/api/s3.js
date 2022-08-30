import { s3Instance } from "./index.js";

const api = s3Instance();

async function uploadImage(file, success, fail) {
  api.defaults.headers["Authorization"] =
    "Bearer " + sessionStorage.getItem("access-token");
  api.defaults.headers["Content-type"] = "multipart/form-data";

  await api.post(`/s3`, { file }).then(success).catch(fail);
}

async function deleteImage(param, success, fail) {
  api.defaults.headers["Authorization"] =
    "Bearer " + sessionStorage.getItem("access-token");
  api.defaults.headers["Content-type"] = "application/json";
  await api.delete(`/s3`, { data: param }).then(success).catch(fail);
}

export { uploadImage, deleteImage };
