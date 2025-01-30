import request from "@/utils/request";
import { useTokenStore } from "@/stores/token";

export const articleCategoryListService = () => {
  return request.get("/category");
};

export const articleCategoryAddService = (categoryData) => {
  return request.post("/category", categoryData);
};

export const articleCategoryUpdateService = (categoryData) => {
  return request.put(`/category`, categoryData);
}

export const articleCategoryDeleteService = (id) => {
  return request.delete(`/category/${id}`);
}