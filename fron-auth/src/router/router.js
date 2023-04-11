import {createRouter, createWebHashHistory} from "vue-router";
import MainComponent from "../components/MainComponent.vue";
import LoginComponent from "../components/LoginComponent.vue";
import RegisterComponent from "../components/RegisterComponent.vue";

export default createRouter({
    history: createWebHashHistory(),
    routes: [
        {path : '/', component: MainComponent},
        {path : '/login', component: LoginComponent},
        {path : '/register', component: RegisterComponent}
    ]
})
