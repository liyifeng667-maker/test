<template>
  <transition v-bind:css="false" name="fade">
    <router-view></router-view>
  </transition>
</template>

<script>
  // import LoginConfig from './views/modules/zhgd/loginConfig.vue'
  export default {
    // components: {
    //   LoginConfig
    // }
}
</script>
<style scoped>
.fade-enter-active,
.fade-leave-active {
  transition: opacity 0s;
}
.fade-enter,
.fade-leave-to {
  opacity: 0;
}


</style>
