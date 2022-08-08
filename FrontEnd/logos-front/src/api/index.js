import axios from "axios";
import { API_LOGIN_URL } from "@/config";

export function loginInstance() {
  const instance = axios.create({
    baseURL: API_LOGIN_URL,
    headers: {
      "Content-type": "application/json",
    },
  });
  return instance;
}