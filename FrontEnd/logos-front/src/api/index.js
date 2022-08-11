import axios from "axios";
import {
  API_KNOWLEDGE_URL,
  API_LOGIN_URL,
  API_POINT_URL,
  API_PAYMENT_URL,
} from "@/config";

export function loginInstance() {
  const instance = axios.create({
    baseURL: API_LOGIN_URL,
    headers: {
      "Content-type": "application/json",
    },
  });
  return instance;
}
export function pointInstance() {
  const instance = axios.create({
    baseURL: API_POINT_URL,
    headers: {
      "Content-type": "application/json",
    },
  });
  return instance;
}
export function paymentInstance() {
  const instance = axios.create({
    baseURL: API_PAYMENT_URL,
    headers: {
      "Content-type": "application/json",
    },
  });
  return instance;
}
export function knowledgeInstance() {
  const instance = axios.create({
    baseURL: API_KNOWLEDGE_URL,
    headers: {
      "Content-type": "application/json",
    },
  });
  return instance;
}
