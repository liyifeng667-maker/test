<template>
	<div>
		<avue-echart-vue
			:option="vueOption"
			width="100%"
			height="100%"
      v-if="box"
		></avue-echart-vue>
	</div>
</template>

<script>
import AvueEchartVue from '@/datav/echart/packages/vue';
import dataComponentService from '@/api/datav/dataComponentService.js'
export default {
  components: {
    AvueEchartVue
  },
  props: {
    menu: {
      type: Boolean,
      default: true
    }
  },
  data () {
    return {
      box: false,
      options: {
        height: 300,
        language: 'javascript',
        fontSize: 12
      },
      content: '',
      index: -1,
      form: {},
      vueOption: {},
      data: [],
      option: {
        tip: false,
        header: true,
        menu: true,
        selection: true,
        dialogWidth: '70%',
        labelWidth: 100,
        index: true,
        align: 'center',
        headerAlign: 'center',
        column: [
          {
            label: '组件名称',
            prop: 'name',
            row: true,
            rules: [
              {
                required: true,
                message: '请输入组件名称',
                trigger: 'blur'
              }
            ]
          },
          {
            label: '组件数据',
            prop: 'content',
            span: 24,
            hide: true
          },
          {
            label: '组件预览',
            prop: 'view',
            span: 24,
            hide: true
          }
        ]
      }
    }
  },
  watch: {
    '$route.query.id': {
      handler (id) {
        dataComponentService.queryById(id).then(({data}) => {
          this.vueOption = this.deepClone({
            content: data.content
          })
          this.box = true
        })
      },
      immediate: true,
      deep: false
    }
  }
}
</script>

<style lang="scss" scoped>
.components {
	&_content {
		display: flex;
		align-items: flex-end;
		flex-direction: column;
	}
	&_left,
	&_right {
		width: 100%;
	}
}
</style>
