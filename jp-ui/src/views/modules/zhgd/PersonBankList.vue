<template>
    <div class="page">
      <el-form size="small" :inline="true" class="query-form" ref="searchForm" :model="searchForm" @keyup.enter.native="refreshList()" @submit.native.prevent>
            <!-- 搜索框-->
         <el-form-item prop="name">
                <el-input size="small" v-model="searchForm.name" placeholder="人员名称" clearable></el-input>
         </el-form-item>
         <el-form-item prop="identityType">
                  <el-select v-model="searchForm.identityType" placeholder="请选择身份" size="small" style="width: 100%;">
                    <el-option
                      v-for="item in $dictUtils.getDictList('lf_identity_type')"
                      :key="item.value"
                      :label="item.label"
                      :value="item.value">
                    </el-option>
                  </el-select>
         </el-form-item>
         <el-form-item prop="accessStatus">
                  <el-select v-model="searchForm.accessStatus" placeholder="请选择准入状态" size="small" style="width: 100%;">
                    <el-option
                      v-for="item in $dictUtils.getDictList('lf_access_status')"
                      :key="item.value"
                      :label="item.label"
                      :value="item.value">
                    </el-option>
                  </el-select>
         </el-form-item>
          <el-form-item>
            <el-button type="primary" @click="refreshList()" size="small" icon="el-icon-search">查询</el-button>
            <el-button @click="resetSearch()" size="small" icon="el-icon-refresh-right">重置</el-button>
          </el-form-item>
      </el-form>

     <div class="bg-white top">
        <vxe-toolbar :refresh="{query: refreshList}">
          <template #buttons>
            <el-button v-if="hasPermission('zhgd:personBank:add')" type="primary" size="small" icon="el-icon-plus" @click="add()">新建</el-button>
            <el-button v-if="hasPermission('zhgd:personBank:edit')" type="warning" size="small" icon="el-icon-edit-outline" @click="edit()" :disabled="$refs.personBankTable && $refs.personBankTable.getCheckboxRecords().length !== 1" plain>修改</el-button>
            <el-button v-if="hasPermission('zhgd:personBank:del')" type="danger"   size="small" icon="el-icon-delete" @click="del()" :disabled="$refs.personBankTable && $refs.personBankTable.getCheckboxRecords().length === 0" plain>删除</el-button>
            <el-button v-if="hasPermission('zhgd:personBank:import')" type="primary" size="small" icon="el-icon-plus" @click="importFace()">导入人脸</el-button>

          </template>
          <!-- <template #tools>
            <vxe-button
    		  type="default"
    		  title="下载导入模板"
    		  v-if="hasPermission('zhgd:personBank:import')"
    		  class="el-icon-document m-r-12"
    		  @click="downloadTpl()"
    		  circle
            >
            </vxe-button>
          </template> -->
        </vxe-toolbar>
        <div style="height: calc(100% - 80px);">
        <vxe-table
            border
            auto-resize
            resizable
            align="center"
            height="auto"
            :loading="loading"
            size="small"
            ref="personBankTable"
            show-header-overflow
            show-overflow
            highlight-hover-row
            :menu-config="{}"
            :print-config="{}"
            :import-config="{
            importMethod: importMethod,
                types: ['csv', 'xls', 'xlsx'],
                remote: true,
            }"
            :export-config="{
                remote: true,
                filename: `人员库数据${moment(new Date()).format(
            		'YYYY-MM-DD'
                )}`,
                sheetName: '人员库数据',
                exportMethod: exportMethod,
                types: ['xlsx'],
                modes: ['current', 'selected', 'all'],
            }"
            @sort-change="sortChangeHandle"
            :sort-config="{remote:true}"
            :data="dataList"
            :checkbox-config="{}">
            <vxe-column type="seq" width="40"></vxe-column>
            <vxe-column type="checkbox"  width="40px"></vxe-column>
    <vxe-column
        field="name"

        title="人员名称">
            <template slot-scope="scope">
              <el-link  type="primary" :underline="false" v-if="hasPermission('zhgd:personBank:edit')" @click="edit(scope.row.id)">{{scope.row.name}}</el-link>
              <el-link  type="primary" :underline="false" v-else-if="hasPermission('zhgd:personBank:view')"  @click="view(scope.row.id)">{{scope.row.name}}</el-link>
              <span v-else>{{scope.row.name}}</span>
            </template>
      </vxe-column>
    <vxe-column
        field="age"

        title="年龄">
      </vxe-column>
    <vxe-column
        field="gender"

        title="性别">
        <template slot-scope="scope">
              {{ $dictUtils.getDictLabel("lf_gender", scope.row.gender, '-') }}
        </template>
      </vxe-column>
    <vxe-column
        field="jobNo"

        title="工号">
      </vxe-column>
    <vxe-column
        field="idCard"

        title="身份证">
      </vxe-column>
    <vxe-column
        field="identityType"

        title="身份">
        <template slot-scope="scope">
              {{ $dictUtils.getDictLabel("lf_identity_type", scope.row.identityType, '-') }}
        </template>
      </vxe-column>
    <vxe-column
        field="accessStatus"

        title="准入状态">
        <template slot-scope="scope">
              {{ $dictUtils.getDictLabel("lf_access_status", scope.row.accessStatus, '-') }}
        </template>
      </vxe-column>
    <vxe-column
		  field="avatarPath"

		  title="人脸数据">
		  <template  #default="{ row }">
		    <!-- <template v-if="row.avatar">
				  <el-image
				    style="height: 50px;width:50px;margin-right:10px;"
				    :src="src" v-for="(src, index) in row.avatar.split(',')" :key="index"
				    :preview-src-list="row.avatar.split(',')">
				  </el-image>
		     </template> -->
         <el-tag v-if="row.avatarPath" type="success">已上传</el-tag>
         <el-tag v-else >未上传</el-tag>
		  </template>
		</vxe-column>
    <!-- <vxe-column
        field="avatarPath"

        title="头像文件路径">
      </vxe-column> -->
    <vxe-column
        field="company"

        title="所属公司">
      </vxe-column>
    <vxe-column
        field="projectId"

        title="相关项目">
      </vxe-column>
    <vxe-column
        field="examScoreId"

        title="考试成绩">
      </vxe-column>
    <vxe-column
        field="birthday"

        title="出生年月">
      </vxe-column>
    <vxe-column
        field="phoneNumber"

        title="手机号">
      </vxe-column>
      <vxe-column
        fixed="right"
        align="center"
        width="200"
        title="操作">
        <template  slot-scope="scope">
          <el-button v-if="hasPermission('zhgd:personBank:view')" type="text" icon="el-icon-view" size="small" @click="view(scope.row.id)">查看</el-button>
          <el-button v-if="hasPermission('zhgd:personBank:edit')" type="text" icon="el-icon-edit" size="small" @click="edit(scope.row.id)">修改</el-button>
          <el-button v-if="hasPermission('zhgd:personBank:del')" type="text"  icon="el-icon-delete" size="small" @click="del(scope.row.id)">删除</el-button>
        </template>
      </vxe-column>
    </vxe-table>
    <vxe-pager
      background
      size="small"
      :current-page="tablePage.currentPage"
      :page-size="tablePage.pageSize"
      :total="tablePage.total"
      :page-sizes="[10, 20, 100, 1000, {label: '全量数据', value: 1000000}]"
      :layouts="['PrevPage', 'JumpNumber', 'NextPage', 'FullJump', 'Sizes', 'Total']"
      @page-change="currentChangeHandle">
    </vxe-pager>
    </div>
    </div>
        <!-- 弹窗, 新增 / 修改 -->
    <PersonBankForm  ref="personBankForm" @refreshDataList="refreshList"></PersonBankForm>
  </div>
</template>

<script>
  import PersonBankForm from './PersonBankForm'
  import personBankService from '@/api/zhgd/personBankService'
  export default {
    data () {
      return {
        searchForm: {
          name: '',
          identityType: '',
          accessStatus: ''
        },
        dataList: [],
        tablePage: {
          total: 0,
          currentPage: 1,
          pageSize: 10,
          orders: [{ column: 'create_date', asc: false }]
        },
        loading: false
      }
    },
    components: {
      PersonBankForm
    },
    activated () {
      this.refreshList()
    },
    methods: {
      // 获取数据列表
      refreshList () {
        this.loading = true
        personBankService.list({
          'current': this.tablePage.currentPage,
          'size': this.tablePage.pageSize,
          'orders': this.tablePage.orders,
          ...this.searchForm
        }).then(({data}) => {
          this.dataList = data.records
          this.tablePage.total = data.total
          this.loading = false
        })
      },
      // 当前页
      currentChangeHandle ({ currentPage, pageSize }) {
        this.tablePage.currentPage = currentPage
        this.tablePage.pageSize = pageSize
        this.refreshList()
      },
      // 排序
      sortChangeHandle (obj) {
        this.tablePage.orders = []
        if (obj.order != null) {
          this.tablePage.orders = [{ column: obj.column.sortBy || this.$utils.toLine(obj.property), asc: obj.order === 'asc' }]
        } else {
          this.tablePage.orders = [{ column: 'create_date', asc: false }]
        }
        this.refreshList()
      },
      // 新增
      add () {
        this.$refs.personBankForm.init('add', '')
      },
      // 修改
      edit (id) {
        id = id || this.$refs.personBankTable.getCheckboxRecords().map(item => {
          return item.id
        })[0]
        this.$refs.personBankForm.init('edit', id)
      },
      // 查看
      view (id) {
        this.$refs.personBankForm.init('view', id)
      },
      // 删除
      del (id) {
        let ids = id || this.$refs.personBankTable.getCheckboxRecords().map(item => {
          return item.id
        }).join(',')
        this.$confirm(`确定删除所选项吗?`, '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(() => {
          this.loading = true
          personBankService.delete(ids).then(({data}) => {
            this.$message.success(data)
            this.refreshList()
            this.loading = false
          })
        })
      },
      // 下载模板
      downloadTpl () {
        this.loading = true
        personBankService.exportTemplate().then(({data}) => {
    // 将二进制流文件写入excel表，以下为重要步骤
          this.$utils.downloadExcel(data, '请假表单导入模板')
          this.loading = false
        }).catch(function (err) {
          this.loading = false
          if (err.response) {
            console.log(err.response)
          }
        })
      },
      // 自定义服务端导入
      importMethod ({ file }) {
      // 处理表单
        const formBody = new FormData()
        formBody.append('file', file)
        this.loading = true
        personBankService.importExcel(formBody).then(({data}) => {
          this.$message.success({
            dangerouslyUseHTMLString: true,
            message: data
          })
          this.refreshList()
        })
      },
      // 自定义服务端导出
      exportMethod ({ options }) {
      // 传给服务端的参数
        const params = {
          current: this.tablePage.currentPage,
          size: this.tablePage.pageSize,
          orders: this.tablePage.orders,
          ...this.searchForm,
          filename: options.filename,
          sheetName: options.sheetName,
          isHeader: options.isHeader,
          original: options.original,
          mode: options.mode,
          selectIds: options.mode === 'selected' ? options.data.map((item) => item.id) : [],
          exportFields: options.columns.map((column) => column.property && column.property.split('.')[0])
        }
        this.loading = true
        return personBankService.exportExcel(params).then(({data}) => {
      // 将二进制流文件写入excel表，以下为重要步骤
          this.$utils.downloadExcel(data, options.filename)
          this.loading = false
        }).catch(function (err) {
          if (err.response) {
            console.log(err.response)
          }
        })
      },
      resetSearch () {
        this.$refs.searchForm.resetFields()
        this.refreshList()
      }
    }
  }
</script>

