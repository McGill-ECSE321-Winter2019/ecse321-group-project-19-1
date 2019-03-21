import Vue from 'vue'
import Router from 'vue-router'
import Hello from '@/components/Hello'
import Cooperator from '@/components/Cooperator'
import Login from '@/components/Login'
import Register from '@/components/Register'
import Logout from '@/components/Logout'
import PmStudent from '@/components/PmStudent'
import ProgramManager from '@/components/ProgramManager'
import Coops from '@/components/Coop'
import Courses from '@/components/Courses'
import TiStudent from '@/components/tiStudent'
import PmSingleStudent from '@/components/PmSingleStudent'
import StudentDocument from '@/components/StudentDocument'

Vue.use(Router)

export default new Router({
  routes: [
    {
      path: '/',
      name: 'Home',
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
      path: '/programManager',
      name: 'ProgramManager',
      component: ProgramManager
    },
    {
      path: '/register',
      name: 'Register',
      component: Register
    },
    {
      path: '/logout',
      name: 'Logout',
      component: Logout
    },
    {
      path: '/Coops',
      name: 'Coops',
      component: Coops
    },
    {
      path: '/CourseRanking',
      name: 'Courses',
      component: Courses
    },
    {
      path: '/TiStudent',
      name: 'TiStudent',
      component: TiStudent
    },
    {
      path: '/PmSingleStudent',
      name: 'PmSingleStudent',
      component: PmSingleStudent
    },
    {
      path: '/StudentDocument/:id',
      name: 'StudentDocument',
      component: PmSingleStudent
    }
    
  ]
})
