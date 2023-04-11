<template>
  <h1>Main page</h1>
  <h2>{{ message }}</h2>
  <button>ВЫЙТИ</button>
</template>

<script>
import {onMounted, ref} from "vue";
import axios from "axios";

export default {
  name: "MainComponent",
  setup(){
    const message = ref("You are not logged in");

    onMounted( async () => {
      const {data} = await axios.get("http://localhost:8080/user");
      message.value = `Hi ${data.username}`;
    });


    return{
      message
    }
  }
}
</script>