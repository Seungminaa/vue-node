import { createApp } from 'vue'
import App from './App.vue'
import router from './router'

//id값은 index.html 안에서 찾음
createApp(App).use(router).mount('#app')
