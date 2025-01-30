import "./assets/main.scss";

import { createApp } from "vue";
import ElementPlus from "element-plus";
import "element-plus/dist/index.css";
import router from "@/router/index";
import { createPersistedState } from "pinia-persistedstate-plugin";
import locale from "element-plus/dist/locale/zh-cn";

import App from "./App.vue";
import { createPinia } from "pinia";

const app = createApp(App);
const pinia = createPinia();
const persist = createPersistedState();
pinia.use(persist);
app.use(pinia);
app.use(router);
app.use(ElementPlus, {locale});
// app.use(ElementPlus);
app.mount("#app");
