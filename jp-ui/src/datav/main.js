import Vue from 'vue'
import ElementUI from 'element-ui'
import 'element-ui/lib/theme-chalk/index.css'
import dataV from '@jiaminghi/data-view'
import router from './router.js'
import { website } from '@/datav/config.js'
import VueCookie from 'vue-cookie'
import draggable from '@/datav/page/components/draggable'
import App from './App.vue'
import './styles/common.scss'
import '@/datav/utils/es6'
// import '@/mock/'
// 导入主题文件
import '@/datav/theme/index.js'
import httpRequest from '@/utils/httpRequest'
Vue.prototype.$http = httpRequest // ajax请求方法
Vue.config.productionTip = false
document.title = website.title
Vue.use(VueCookie)
Vue.use(ElementUI)
Vue.use(window.AVUE)
Vue.component('avue-draggable', draggable)
Vue.prototype.$website = website
Vue.use(dataV)
new Vue({
  router,
  render: h => h(App)
}).$mount('#app')
