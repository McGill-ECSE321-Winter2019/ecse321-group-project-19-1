import Vue from 'vue'
import Router from 'vue-router'
import Hello from '@/components/Hello'
import Cooperator from '@/components/Cooperator'
import Login from '@/components/Login'
import PmStudent from '@/components/PmStudent.vue'
import ProgramManager from '@/components/ProgramManager.vue'

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
    },
    {
      path: '/PmStudent',
      name: 'PmStudent',
      component: PmStudent
    },
    {
      path: '/ProgramManager',
      name: 'ProgramManager',
      component: ProgramManager
    },
  ]
})
