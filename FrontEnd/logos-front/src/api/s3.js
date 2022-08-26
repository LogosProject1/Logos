import { s3Instance } from "./index.js";

const api = s3Instance();

async function uploadImage(file, success, fail) {
  api.defaults.headers["Authorization"] =
    "Bearer " + sessionStorage.getItem("access-token");
  console.log(file);
  await api.post(`/s3`, { file }).then(success).catch(fail);
}

export { uploadImage };
