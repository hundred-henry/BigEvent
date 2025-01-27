import axios from "axios";
import { ElMessage } from "element-plus";
// import {useTokenStore} from "@/stores/token";
// import router from "@/router";

const baseURL = "/api";
const instance = axios.create({ baseURL });

// instance.interceptors.request.use(
//     config => {
//       const tokenStore = useTokenStore();
//       if (tokenStore.token) {
//         config.headers.Authorization = tokenStore.token
//       }
//       return config
//     },
//     error => {
//       return Promise.reject(error)
//     }
// )

//添加响应拦截器
instance.interceptors.response.use(
  (result) => {
    // success
    if (result.data.code === 0) {
      return result.data;
    }

    // error
    ElMessage.error(result.data.msg ? result.data.msg : "服务异常");
    return Promise.reject(result.data);
  },
  (err) => {
    alert("服务异常");
    return Promise.reject(err); //异步的状态转化成失败的状态
  }
);

export default instance;
