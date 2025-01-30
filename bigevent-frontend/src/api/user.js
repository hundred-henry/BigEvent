import request from "@/utils/request.js";

export const userRegisterService = (registerData) => {
  const params = new URLSearchParams();
  for (let key in registerData) {
    params.append(key, registerData[key]);
  }
  return request.post("/user/register", params);
};

export const userLoginService = (loginData) => {
  const params = new URLSearchParams();
  for (let key in loginData) {
    params.append(key, loginData[key]);
  }
  return request.post("/user/login", params);
};

export const userInfoService = () => {
  return request.get("/user/userInfo");
};

export const userInfoUpdateService = (updateInfoData) => {
  return request.put("/user/update", updateInfoData);
};

export const userAvatarUpdateService = (avatarUrl) => {
  const params = new URLSearchParams();
  params.append("avatarUrl", avatarUrl);
  return request.patch("/user/updateAvatar", params);
};

export const userPwdUpdateService = (pwdData) => {
  return request.patch("/user/updatePwd", pwdData);
};
