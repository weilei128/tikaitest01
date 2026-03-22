<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="井号" prop="wellId">
        <el-input
          v-model="queryParams.wellId"
          placeholder="请输入井号"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="射孔时间" prop="perfTime">
        <el-date-picker clearable
          v-model="queryParams.perfTime"
          type="date"
          value-format="yyyy-MM-dd"
          placeholder="请选择射孔时间">
        </el-date-picker>
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
          v-hasPermi="['basedata:wellPerforation:add']"
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
          v-hasPermi="['basedata:wellPerforation:edit']"
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
          v-hasPermi="['basedata:wellPerforation:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['basedata:wellPerforation:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="wellPerforationList" row-key="id" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="井号" align="center" prop="wellId" />
      <el-table-column label="射孔时间" align="center" prop="perfTime" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.perfTime, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="斜顶深/井段顶" align="center" prop="topDepthTilt" />
      <el-table-column label="斜底深/井段底" align="center" prop="bottomDepthTilt" />
      <el-table-column label="垂顶深" align="center" prop="topDepthVertical" />
      <el-table-column label="垂底深" align="center" prop="bottomDepthVertical" />
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['basedata:wellPerforation:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['basedata:wellPerforation:remove']"
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

    <!-- 添加或修改射孔井段信息对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="井id" prop="wellId">
          <el-input v-model="form.wellId" placeholder="请输入井id" />
        </el-form-item>
        <el-form-item label="射孔时间" prop="perfTime">
          <el-date-picker clearable
            v-model="form.perfTime"
            type="date"
            value-format="yyyy-MM-dd"
            placeholder="请选择射孔时间">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="斜顶深/井段顶" prop="topDepthTilt">
          <el-input v-model="form.topDepthTilt" placeholder="请输入斜顶深/井段顶" />
        </el-form-item>
        <el-form-item label="斜底深/井段底" prop="bottomDepthTilt">
          <el-input v-model="form.bottomDepthTilt" placeholder="请输入斜底深/井段底" />
        </el-form-item>
        <el-form-item label="垂顶深" prop="topDepthVertical">
          <el-input v-model="form.topDepthVertical" placeholder="请输入垂顶深" />
        </el-form-item>
        <el-form-item label="垂底深" prop="bottomDepthVertical">
          <el-input v-model="form.bottomDepthVertical" placeholder="请输入垂底深" />
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
import { listWellPerforation, getWellPerforation, delWellPerforation, addWellPerforation, updateWellPerforation } from "@/api/basedata/wellPerforation";

export default {
  name: "WellPerforation",
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
      // 射孔井段信息表格数据
      wellPerforationList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        wellId: null,
        perfTime: null,
        topDepthTilt: null,
        bottomDepthTilt: null,
        topDepthVertical: null,
        bottomDepthVertical: null
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        wellId: [
          { required: true, message: "井id不能为空", trigger: "blur" }
        ],
        perfTime: [
          { required: true, message: "射孔时间不能为空", trigger: "blur" }
        ],
      }
    };
  },
  created() {
    this.getList();
  },
  methods: {
    /** 查询射孔井段信息列表 */
    getList() {
      this.loading = true;
      listWellPerforation(this.queryParams).then(response => {
        this.wellPerforationList = response.rows;
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
        id: null,
        wellId: null,
        perfTime: null,
        topDepthTilt: null,
        bottomDepthTilt: null,
        topDepthVertical: null,
        bottomDepthVertical: null
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
      this.ids = selection.map(item => item.id)
      this.single = selection.length!==1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加射孔井段信息";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const id = row.id || this.ids
      getWellPerforation(id).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改射孔井段信息";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.id != null) {
            updateWellPerforation(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addWellPerforation(this.form).then(response => {
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
      const ids = row.id || this.ids;
      this.$modal.confirm('是否确认删除射孔井段信息编号为"' + ids + '"的数据项？').then(function() {
        return delWellPerforation(ids);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {});
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('basedata/wellPerforation/export', {
        ...this.queryParams
      }, `wellPerforation_${new Date().getTime()}.xlsx`)
    }
  }
};
</script>
