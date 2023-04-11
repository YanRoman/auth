<template>
  <h1>LOGIN PAGE</h1>
  <main>
    <form @submit.prevent="submit" action="">
      <h1>Please sign in</h1>

      <div>
        <input v-model="data.username" type="text">
        <label  for="">Username</label>
      </div>

      <div>
        <input v-model="data.password" type="password">
        <label for="">Password</label>
      </div>

      <button type="submit"> LOGIN </button>
    </form>
  </main>
</template>

<script>
import {reactive} from "vue";
import axios from "axios";
import {useRouter} from "vue-router";

export default {
  name: "LoginComponent",
  setup(){
    const data = reactive({
      username: '',
      password: ''
    });
    const router = useRouter();
    const submit = async () =>{
      const response = await axios.post("http://localhost:8080/login", data, {withCredentials: true});
      axios.defaults.headers.common['Authorization'] = `Bearer ${response.data.token}`;
      await router.push("/");
    }

    return{
      data,
      submit
    }
  }
}
</script>