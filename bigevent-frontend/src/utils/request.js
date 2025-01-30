import axios from "axios";
import { ElMessage } from "element-plus";
import { useTokenStore } from "@/stores/token";

const baseURL = "/api";
const instance = axios.create({ baseURL });

//添加请求拦截器
instance.interceptors.request.use(
  (config) => {
    const tokenStore = useTokenStore();
    config.headers.Authorization = tokenStore.token;
    return config;
  },
  (err) => {
    return Promise.reject(err);
  }
);

import router from "@/router";
//添加响应拦截器
instance.interceptors.response.use(
  (result) => {
    // success
    if (result.data.code === 0) {
      return result.data;
    }

    // error
    ElMessage.error(result.data.msg ? result.data.msg : "Server Error");
    return Promise.reject(result.data);
  },
  (err) => {
    // determine the responese status code
    // if 401, redirect to login page
    if (err.response.status === 401) {
      ElMessage.error("Please login first");
      router.push("/login");
    } else {
      ElMessage.error(err.message);
    }
    return Promise.reject(err); //异步的状态转化成失败的状态
  }
);

export default instance;
