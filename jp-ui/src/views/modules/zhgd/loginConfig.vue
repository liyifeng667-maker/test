<template>
  <div></div>
</template>
<script>

export default {
  data () {
    return {
      timer: null,
      noOperateTime: 1000 * 60 * 30 // 用户不操作的时间
    }
  },
  created () {},
  mounted () {
    window.addEventListener(
      'click',
      () => {
        // 为了方便，我把点击事件的时间直接存到sessionStorage中去，这样方便获取比较
        sessionStorage.setItem('lastClickTime', new Date().getTime())
      },
      true
    )
    this.isTimeOut()
  },
  beforeDestroy () {
    // 最后一步，离开页面的时候，清除一下定时器，也解绑点击事件
    clearInterval(this.timer)
    window.removeEventListener('click', () => {}, true)
  },
  methods: {
    isTimeOut () {
      clearInterval(this.timer)
      this.timer = setInterval(() => {
        let isLoginHref = window.location.href // url
        // console.log(isLoginHref)
        if (!isLoginHref.includes('/login')) {
          let lastClickTime = Number(sessionStorage.getItem('lastClickTime'))
          let nowTime = new Date().getTime()

          if (nowTime - lastClickTime > this.noOperateTime) {
            this.$message.error('您已经长时间未操作,请重新登录使用。')
            // 退出逻辑
            this.$router.push('/login')
          }
        }
      }, 1000 * 60)
    }
  }
}
</script>
<style>
</style>
