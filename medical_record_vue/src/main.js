// The Vue build version to load with the `import` command
// (runtime-only or standalone) has been set in webpack.base.conf with an alias.
import Vue from 'vue'
import App from './App'
import router from './router'
/*引入如下组件*/
import ElementUI from 'element-ui';
import 'element-ui/lib/theme-chalk/index.css';
import common from '../commonFun/common.js'
Vue.use(ElementUI);

Vue.config.productionTip = false
Vue.prototype.host = 'http://127.0.0.1:8080'
Vue.prototype.common = common

router.beforeEach((to, from, next) => {
  console.log(to.meta)
  // 判断该路由是否需要登录权限
  if (to.meta.requireAuth) {
    // 该路由需要登录权限
    if (sessionStorage.getItem('token')) {
      // 已登录
      next()
    } else {
      next('/login')
    }
  } else {
    next()
  }
})

/* eslint-disable no-new */
new Vue({
  el: '#app',
  router,
  components: { App },
  template: '<App/>'
})