<script setup>
import { User, Lock } from "@element-plus/icons-vue";
import { ref } from "vue";
import { ElMessage } from "element-plus";
// Control the display of registration and login forms, default is registration
const isRegister = ref(false);
// Define data model
const registerData = ref({
  username: "",
  password: "",
  rePassword: "",
});

// Function to validate password
const checkRePassword = (rule, value, callback) => {
  if (value === "") {
    callback(new Error("Please confirm the password again"));
  } else if (value !== registerData.value.password) {
    callback(new Error("Please ensure the two passwords match"));
  } else {
    callback();
  }
};

// Define form validation rules
const rules = {
  username: [
    { required: true, message: "Please enter a username", trigger: "blur" },
    {
      min: 5,
      max: 16,
      message: "Length should be 5-16 characters",
      trigger: "blur",
    },
  ],
  password: [
    { required: true, message: "Please enter a password", trigger: "blur" },
    {
      min: 5,
      max: 16,
      message: "Length should be 5-16 characters",
      trigger: "blur",
    },
  ],
  rePassword: [{ validator: checkRePassword, trigger: "blur" }],
};

// Call backend API to complete registration
import { userRegisterService } from "@/api/user.js";
const register = async () => {
  //registerData是一个响应式对象,如果要获取值,需要.value
  let result = await userRegisterService(registerData.value);
  ElMessage.success(result.msg ? result.msg : "注册成功");
};

//绑定数据,复用注册表单的数据模型
//表单数据校验
//登录函数
import { userLoginService } from "@/api/user.js";
// import {useTokenStore} from '@/stores/token.js'
import {useRouter} from 'vue-router'
const router = useRouter()
// const tokenStore = useTokenStore();
const login = async () => {
  //调用接口,完成登录
  let result = await userLoginService(registerData.value);

  ElMessage.success(result.msg ? result.msg : "登录成功");
  //   //把得到的token存储到pinia中
  //   tokenStore.setToken(result.data);
  //   //跳转到首页 路由完成跳转
    router.push("/");
};

// Define function to clear data model
const clearRegisterData = () => {
  registerData.value = {
    username: "",
    password: "",
    rePassword: "",
  };
};
</script>

<template>
  <el-row class="login-page">
    <el-col :span="12" class="bg"></el-col>
    <el-col :span="6" :offset="3" class="form">
      <!-- Registration Form -->
      <el-form
        ref="form"
        size="large"
        autocomplete="off"
        v-if="isRegister"
        :model="registerData"
        :rules="rules"
      >
        <el-form-item>
          <h1>Register</h1>
        </el-form-item>
        <el-form-item prop="username">
          <el-input
            :prefix-icon="User"
            placeholder="Please enter a username"
            v-model="registerData.username"
          ></el-input>
        </el-form-item>
        <el-form-item prop="password">
          <el-input
            :prefix-icon="Lock"
            type="password"
            placeholder="Please enter a password"
            v-model="registerData.password"
          ></el-input>
        </el-form-item>
        <el-form-item prop="rePassword">
          <el-input
            :prefix-icon="Lock"
            type="password"
            placeholder="Please enter the password again"
            v-model="registerData.rePassword"
          ></el-input>
        </el-form-item>
        <!-- Register Button -->
        <el-form-item>
          <el-button
            class="button"
            type="primary"
            auto-insert-space
            @click="register"
          >
            Register
          </el-button>
        </el-form-item>
        <el-form-item class="flex">
          <el-link
            type="info"
            :underline="false"
            @click="
              isRegister = false;
              clearRegisterData();
            "
          >
            ← Back
          </el-link>
        </el-form-item>
      </el-form>
      <!-- Login Form -->
      <el-form
        ref="form"
        size="large"
        autocomplete="off"
        v-else
        :model="registerData"
        :rules="rules"
      >
        <el-form-item>
          <h1>Login</h1>
        </el-form-item>
        <el-form-item prop="username">
          <el-input
            :prefix-icon="User"
            placeholder="Please enter a username"
            v-model="registerData.username"
          ></el-input>
        </el-form-item>
        <el-form-item prop="password">
          <el-input
            name="password"
            :prefix-icon="Lock"
            type="password"
            placeholder="Please enter a password"
            v-model="registerData.password"
          ></el-input>
        </el-form-item>
        <el-form-item class="flex">
          <div class="flex">
            <el-checkbox>Remember me</el-checkbox>
            <el-link type="primary" :underline="false"
              >Forgot password?</el-link
            >
          </div>
        </el-form-item>
        <!-- Login Button -->
        <el-form-item>
          <el-button
            class="button"
            type="primary"
            auto-insert-space
            @click="login"
            >Login</el-button
          >
        </el-form-item>
        <el-form-item class="flex">
          <el-link
            type="info"
            :underline="false"
            @click="
              isRegister = true;
              clearRegisterData();
            "
          >
            Register →
          </el-link>
        </el-form-item>
      </el-form>
    </el-col>
  </el-row>
</template>

<style lang="scss" scoped>
/* Styles */
.login-page {
  height: 100vh;
  background-color: #fff;

  .bg {
    background: url("@/assets/logo2.png") no-repeat 60% center / 240px auto,
      url("@/assets/login_bg.jpg") no-repeat center / cover;
    border-radius: 0 20px 20px 0;
  }

  .form {
    display: flex;
    flex-direction: column;
    justify-content: center;
    user-select: none;

    .title {
      margin: 0 auto;
    }

    .button {
      width: 100%;
    }

    .flex {
      width: 100%;
      display: flex;
      justify-content: space-between;
    }
  }
}
</style>
