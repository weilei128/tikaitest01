<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="井组名称" prop="wellGroupName">
        <el-input
          v-model="queryParams.wellGroupName"
          placeholder="请输入井组名称"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item>
        <el-button type="primary" icon="el-icon-search" size="mini" @click="handleQuery">搜索</el-button>
        <el-button icon="el-icon-refresh" size="mini" @click="resetQuery">重置</el-button>
      </el-form-item>
    </el-form>

    <el-row :gutter="10" class="mb8">
      <el-col :span="1.5">
        <el-button
          type="primary"
          plain
          icon="el-icon-plus"
          size="mini"
          @click="handleAdd"
          v-hasPermi="['basedata:wellGroup:add']"
        >新增</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="success"
          plain
          icon="el-icon-edit"
          size="mini"
          :disabled="single"
          @click="handleUpdate"
          v-hasPermi="['basedata:wellGroup:edit']"
        >修改</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="danger"
          plain
          icon="el-icon-delete"
          size="mini"
          :disabled="multiple"
          @click="handleDelete"
          v-hasPermi="['basedata:wellGroup:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['basedata:wellGroup:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="wellGroupList"  row-key="wellGroupId" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="井组名称" align="center" prop="wellGroupName" />
<!--      <el-table-column label="水井代码" align="center" prop="waterGroupIds" />-->
<!--      <el-table-column label="油井代码" align="center" prop="oilGroupIds" />-->
      <el-table-column label="水井组" align="center" prop="waterGroupNames" />
      <el-table-column label="油井组" align="center" prop="oilGroupNames" />
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['basedata:wellGroup:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['basedata:wellGroup:remove']"
          >删除</el-button>
        </template>
      </el-table-column>
    </el-table>

    <pagination
      v-show="total>0"
      :total="total"
      :page.sync="queryParams.pageNum"
      :limit.sync="queryParams.pageSize"
      @pagination="getList"
    />

    <!-- 添加或修改井组信息对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="井组名称" prop="wellGroupName">
          <el-input v-model="form.wellGroupName" placeholder="请输入井组名称" />
        </el-form-item>
        <el-form-item label="水井代码" prop="waterGroupIds">
          <el-input v-model="form.waterGroupIds" placeholder="请输入水井代码" />
        </el-form-item>
        <el-form-item label="油井代码" prop="oilGroupIds">
          <el-input v-model="form.oilGroupIds" placeholder="请输入油井代码" />
        </el-form-item>
        <el-form-item label="水井组" prop="waterGroupNames" >
          <el-input v-model="form.waterGroupNames" placeholder="请输入水井组名称" />
        </el-form-item>
        <el-form-item label="油井组" prop="oilGroupNames" >
          <el-input v-model="form.oilGroupNames" placeholder="请输入油井组名称" />
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitForm">确 定</el-button>
        <el-button @click="cancel">取 消</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import { listWellGroup, getWellGroup, delWellGroup, addWellGroup, updateWellGroup } from "@/api/basedata/wellGroup";

export default {
  name: "WellGroup",
  data() {
    return {
      // 遮罩层
      loading: true,
      // 选中数组
      ids: [],
      // 非单个禁用
      single: true,
      // 非多个禁用
      multiple: true,
      // 显示搜索条件
      showSearch: true,
      // 总条数
      total: 0,
      // 井组信息表格数据
      wellGroupList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        wellGroupName: null,
        waterGroupIds: null,
        oilGroupIds: null,
        waterGroupNames: null,
        oilGroupNames: null
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
      }
    };
  },
  created() {
    this.getList();
  },
  methods: {
    /** 查询井组信息列表 */
    getList() {
      this.loading = true;
      listWellGroup(this.queryParams).then(response => {
        this.wellGroupList = response.rows;
        this.total = response.total;
        this.loading = false;
      });
    },
    // 取消按钮
    cancel() {
      this.open = false;
      this.reset();
    },
    // 表单重置
    reset() {
      this.form = {
        wellGroupId: null,
        wellGroupName: null,
        waterGroupIds: null,
        oilGroupIds: null,
        waterGroupNames: null,
        oilGroupNames: null
      };
      this.resetForm("form");
    },
    /** 搜索按钮操作 */
    handleQuery() {
      this.queryParams.pageNum = 1;
      this.getList();
    },
    /** 重置按钮操作 */
    resetQuery() {
      this.resetForm("queryForm");
      this.handleQuery();
    },
    // 多选框选中数据
    handleSelectionChange(selection) {
      this.ids = selection.map(item => item.wellGroupId)
      this.single = selection.length!==1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加井组信息";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const wellGroupId = row.wellGroupId || this.ids
      getWellGroup(wellGroupId).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改井组信息";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.wellGroupId != null) {
            updateWellGroup(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addWellGroup(this.form).then(response => {
              this.$modal.msgSuccess("新增成功");
              this.open = false;
              this.getList();
            });
          }
        }
      });
    },
    /** 删除按钮操作 */
    handleDelete(row) {
      const wellGroupIds = row.wellGroupId || this.ids;
      this.$modal.confirm('是否确认删除井组信息编号为"' + wellGroupIds + '"的数据项？').then(function() {
        return delWellGroup(wellGroupIds);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {});
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('basedata/wellGroup/export', {
        ...this.queryParams
      }, `wellGroup_${new Date().getTime()}.xlsx`)
    }
  }
};
</script>
