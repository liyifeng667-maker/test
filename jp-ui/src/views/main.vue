<template>
  <div id="app"
    class="jp-wrapper"
    :class="{ 'jp-sidebar--fold': sidebarFold }">
    <template>
      <main-navbar ref="navbar" @showRight="showRight" />
      <main-sidebar/>
      <div class="jp-content__wrapper">
        <main-content/>
      </div>
      <main-right ref="mainRight"/>
      <!-- <loginConfig/> -->

    </template>
  </div>
</template>

<script>
  import MainNavbar from './layout/_common_top'
  import MainSidebar from './layout/_common_left'
  import MainContent from './layout/_common_center'
  import MainRight from './layout/_common_right'
  import configService from '@/api/sys/configService'
  import userService from '@/api/sys/userService'
  import loginConfig from './modules/zhgd/loginConfig.vue'

  export default {
    data () {
      return {
        isRightVisible: false
      }
    },
    components: {
      MainNavbar,
      MainSidebar,
      MainContent,
      MainRight,
      loginConfig
    },
    computed: {
      sidebarFold: {
        get () {
          return this.$store.state.common.sidebarFold
        }
      }
    },
    mounted () {
      this.getUserInfo()
      this.getConfig()
      this.resetDocumentClientHeight()
    },
    methods: {
      // 重置窗口可视高度
      resetDocumentClientHeight () {
        window.onresize = () => {
          if (this.$refs.navbar) {
            let _defaultLayout = this.$refs.navbar.defaultLayout
            if (_defaultLayout === 'top') {
              this.$refs.navbar.fixTopMenu()
            }
          }
        }
      },
      showRight (flag) {
        this.$refs.mainRight.showRight()
        this.isRightVisible = flag
      },
      // 获取当前登录用户信息
      getUserInfo () {
        userService.info().then(({data}) => {
          this.$store.commit('user/updateUser', data.user)
        })
      },
      // 获取产品name 和 logo
      getConfig () {
        configService.getConfig().then(({data}) => {
          this.$store.commit('config/updateProductName', data.productName)
          this.$store.commit('config/updateLogo', data.logo)
          if (!localStorage.getItem('defaultLayout')) {
            this.$store.commit('config/updateDefaultLayout', data.defaultLayout)
          }
          if (!localStorage.getItem('defaultTheme')) {
            this.$store.commit('config/updateDefaultTheme', data.defaultTheme)
          }
        })
      }
    }
  }
</script>
