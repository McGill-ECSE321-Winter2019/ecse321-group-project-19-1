import Vue from 'vue'
import Router from 'vue-router'
import Hello from '@/components/Hello'
import Cooperator from '@/components/Cooperator'
import Login from '@/components/Login'

Vue.use(Router)

export default new Router({
  routes: [
    {
      path: '/',
      name: 'Hello',
      component: Hello
    },
    {
      path: '/app',
      name: 'Cooperator',
      component: Cooperator
    },
    {
      path: '/login',
      name: 'Login',
      component: Login
    }
  ]
})
