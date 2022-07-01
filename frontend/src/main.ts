import Vue from 'vue'
import App from './App.vue'
import router from './router'
import store from './store'
import VueMoment from "vue-moment";
import { ins as axios } from "@/lib/axios";
import VueAxios from 'vue-axios';
import VueCookies from "vue-cookies";
import { BootstrapVue, IconsPlugin } from 'bootstrap-vue'
import 'bootstrap/dist/css/bootstrap.css'
import 'bootstrap-vue/dist/bootstrap-vue.css'
import VueDaumPostcode from "vue-daum-postcode"
import { Mixin } from "@/mixins/mixin";
import InfiniteLoading from 'vue-infinite-loading';
import PerfectScrollbar from 'vue2-perfect-scrollbar';
import 'vue2-perfect-scrollbar/dist/vue2-perfect-scrollbar.css';

// import { io } from 'socket.io-client';

import '@/assets/styles/index.css';

// const socket = io('http://localhost:9001');
// Vue.prototype.$socket = socket;

Vue.config.productionTip = false

Vue.use(PerfectScrollbar);
Vue.use(BootstrapVue);
Vue.use(IconsPlugin);
Vue.use(VueAxios, axios);
Vue.use(VueCookies);
Vue.use(VueMoment);
Vue.use(VueDaumPostcode);
Vue.use(InfiniteLoading, { /* options */ });

Vue.mixin(Mixin);

new Vue({
  router,
  store,
  render: h => h(App)
}).$mount('#app')
