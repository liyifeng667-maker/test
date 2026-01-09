import Router from 'vue-router'
import Vue from 'vue'
Vue.use(Router)
const vueRouter = new Router({
  routes: [
    {
      path: '/componentsPreview',
      component: () => import(
/* webpackChunkName: "page" */ '@/datav/page/list/componentsPreview.vue')
    },
    {
      path: '/build/:id',
      name: 'build',
      component: () => import(/* webpackChunkName: "page" */ '@/datav/page/build.vue')
    },
    {
      path: '/view/:id',
      name: 'view',
      component: () => import(/* webpackChunkName: "page" */ '@/datav/page/view.vue')
    }
  ]
})
export default vueRouter
