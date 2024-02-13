import { createRouter, createWebHistory } from 'vue-router'
import HomeView from '../views/HomeView.vue'
import BoardView from '../views/BoardView.vue'
import BoardInfoView from '../views/BoardInfoView.vue'
import BoardFormView from '../views/BoardFormView.vue'

const routes = [
  {
    path: '/',
    name: 'home',
    component: HomeView
  },
  {
    path: '/board',
    name: 'board',
    component: BoardView
  },
  {
    path: '/boardInfo',
    name: 'boardInfo',
    component: BoardInfoView
  },
  {
    path: '/boardForm',
    name: 'boardForm',
    component: BoardFormView
  }
]

const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes
})

export default router
