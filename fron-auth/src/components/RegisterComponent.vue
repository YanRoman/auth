<template>
  <main class="form-signin">
    <h1>REGISTRATION PAGE</h1>
    <form @submit.prevent="submit" action="">
      <h1>Please sign in</h1>

      <div>
        <input v-model="data.username" type="text">
        <label for="">Username</label>
      </div>

      <div>
        <input v-model="data.password" type="password">
        <label for="">Password</label>
      </div>

      <div>
        <input v-model="data.password_confirm" type="password">
        <label for="">Password confirm</label>
      </div>

      <button type="submit"> Register </button>
    </form>
  </main>
</template>

<script>
import {reactive} from "vue";
import axios from 'axios';
import {useRouter} from "vue-router";

export default {
  name: "RegisterComponent",
  setup() {
    const data = reactive({
      username: '',
      password: '',
      password_confirm: ''
    });
    const router = useRouter();

    const submit = async () => {
      await axios.post("http://localhost:8080/register", data);
      await router.push('/login');
    }
    return{
      data,
      submit
    }
  }

}
</script>