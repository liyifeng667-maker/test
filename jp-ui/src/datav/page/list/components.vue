<template>
  <div>
    <avue-crud ref="crud"
               :option="option"
               v-model="form"
               @row-save="rowSave"
               @row-update="rowUpdate"
               @row-del="rowDel"
               :before-open="beforeOpen"
               @selection-change="selectionChange"
               :data="data">
      <template slot-scope="{size,type,row,index}"
                slot="menu">
        <el-button :size="size"
                   icon="el-icon-edit"
                   :type="type"
                   @click="handleView(row,index)">预览</el-button>
      </template>
      <template slot-scope="{}"
                slot="contentForm">
        <monaco-editor v-model="form.content"
                       :language="options.language"
                       :height="options.height"
                       :options="options"></monaco-editor>
        <br />
        <el-button v-if="!view"
                   type="primary"
                   size="small"
                   icon="el-icon-check"
                   @click="handleOption">运行</el-button>
      </template>
      <template slot-scope="{}"
                slot="viewForm">
        <avue-echart-vue :option="vueOption"
                         width="100%"
                         height="100%"></avue-echart-vue>
      </template>
    </avue-crud>
    <el-dialog title="预览组件"
               :visible.sync="box"
               width="60%">
      <avue-echart-vue :option="vueOption"
                       width="100%"
                       height="100%"></avue-echart-vue>
    </el-dialog>

  </div>
</template>

<script>
import AvueEchartVue from '@/datav/echart/packages/vue'
import MonacoEditor from '@/datav/page/components/monaco-editor'
import dataComponentService from '@/api/datav/dataComponentService.js'
export default {
  components: {
    AvueEchartVue,
    MonacoEditor
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
            rules: [{
              required: true,
              message: '请输入组件名称',
              trigger: 'blur'
            }]
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
  created () {
    if (this.menu === false) {
      this.option.header = false
      this.option.menu = false
    }
    this.getList()
  },
  methods: {
    selectionChange (val) {
      this.$emit('change', val[0])
    },
    vaildData (id) {
      return [0, 1, 2].includes(id)
    },
    getList () {
      dataComponentService.list().then(({data}) => {
        this.data = data.records
      })
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