<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="井id" prop="wellId">
        <el-input
          v-model="queryParams.wellId"
          placeholder="请输入井id"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="井筒标识" prop="wellboreId">
        <el-input
          v-model="queryParams.wellboreId"
          placeholder="请输入井筒标识"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="井眼尺寸" prop="boreholeSize">
        <el-input
          v-model="queryParams.boreholeSize"
          placeholder="请输入井眼尺寸"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="下入序号" prop="runInNo">
        <el-input
          v-model="queryParams.runInNo"
          placeholder="请输入下入序号"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="组件外径" prop="compRadiusOuter">
        <el-input
          v-model="queryParams.compRadiusOuter"
          placeholder="请输入组件外径"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="组件内径" prop="compRadiusInner">
        <el-input
          v-model="queryParams.compRadiusInner"
          placeholder="请输入组件内径"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="套管顶深" prop="topDepth">
        <el-input
          v-model="queryParams.topDepth"
          placeholder="请输入套管顶深"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="套管底深" prop="bottomDepth">
        <el-input
          v-model="queryParams.bottomDepth"
          placeholder="请输入套管底深"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="最大承压" prop="compPressMax">
        <el-input
          v-model="queryParams.compPressMax"
          placeholder="请输入最大承压"
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
          v-hasPermi="['basedata:boreframe:add']"
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
          v-hasPermi="['basedata:boreframe:edit']"
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
          v-hasPermi="['basedata:boreframe:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['basedata:boreframe:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="boreframeList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="id" align="center" prop="id" />
      <el-table-column label="井id" align="center" prop="wellId" />
      <el-table-column label="井筒标识" align="center" prop="wellboreId" />
      <el-table-column label="井眼尺寸" align="center" prop="boreholeSize" />
      <el-table-column label="下入序号" align="center" prop="runInNo" />
      <el-table-column label="组件外径" align="center" prop="compRadiusOuter" />
      <el-table-column label="组件内径" align="center" prop="compRadiusInner" />
      <el-table-column label="套管类型" align="center" prop="wellboreType" />
      <el-table-column label="套管顶深" align="center" prop="topDepth" />
      <el-table-column label="套管底深" align="center" prop="bottomDepth" />
      <el-table-column label="最大承压" align="center" prop="compPressMax" />
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['basedata:boreframe:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['basedata:boreframe:remove']"
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

    <!-- 添加或修改井身结构设计（套管）对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="井id" prop="wellId">
          <el-input v-model="form.wellId" placeholder="请输入井id" />
        </el-form-item>
        <el-form-item label="井筒标识" prop="wellboreId">
          <el-input v-model="form.wellboreId" placeholder="请输入井筒标识" />
        </el-form-item>
        <el-form-item label="井眼尺寸" prop="boreholeSize">
          <el-input v-model="form.boreholeSize" placeholder="请输入井眼尺寸" />
        </el-form-item>
        <el-form-item label="下入序号" prop="runInNo">
          <el-input v-model="form.runInNo" placeholder="请输入下入序号" />
        </el-form-item>
        <el-form-item label="组件外径" prop="compRadiusOuter">
          <el-input v-model="form.compRadiusOuter" placeholder="请输入组件外径" />
        </el-form-item>
        <el-form-item label="组件内径" prop="compRadiusInner">
          <el-input v-model="form.compRadiusInner" placeholder="请输入组件内径" />
        </el-form-item>
        <el-form-item label="套管顶深" prop="topDepth">
          <el-input v-model="form.topDepth" placeholder="请输入套管顶深" />
        </el-form-item>
        <el-form-item label="套管底深" prop="bottomDepth">
          <el-input v-model="form.bottomDepth" placeholder="请输入套管底深" />
        </el-form-item>
        <el-form-item label="最大承压" prop="compPressMax">
          <el-input v-model="form.compPressMax" placeholder="请输入最大承压" />
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
import { listBoreframe, getBoreframe, delBoreframe, addBoreframe, updateBoreframe } from "@/api/basedata/boreframe";

export default {
  name: "Boreframe",
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
      // 井身结构设计（套管）表格数据
      boreframeList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        wellId: null,
        wellboreId: null,
        boreholeSize: null,
        runInNo: null,
        compRadiusOuter: null,
        compRadiusInner: null,
        wellboreType: null,
        topDepth: null,
        bottomDepth: null,
        compPressMax: null
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        wellId: [
          { required: true, message: "井id不能为空", trigger: "blur" }
        ],
        wellboreId: [
          { required: true, message: "井筒标识不能为空", trigger: "blur" }
        ],
      }
    };
  },
  created() {
    this.getList();
  },
  methods: {
    /** 查询井身结构设计（套管）列表 */
    getList() {
      this.loading = true;
      listBoreframe(this.queryParams).then(response => {
        this.boreframeList = response.rows;
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
        wellboreId: null,
        boreholeSize: null,
        runInNo: null,
        compRadiusOuter: null,
        compRadiusInner: null,
        wellboreType: null,
        topDepth: null,
        bottomDepth: null,
        compPressMax: null
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
      this.title = "添加井身结构设计（套管）";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const id = row.id || this.ids
      getBoreframe(id).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改井身结构设计（套管）";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.id != null) {
            updateBoreframe(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addBoreframe(this.form).then(response => {
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
      this.$modal.confirm('是否确认删除井身结构设计（套管）编号为"' + ids + '"的数据项？').then(function() {
        return delBoreframe(ids);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {});
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('basedata/boreframe/export', {
        ...this.queryParams
      }, `boreframe_${new Date().getTime()}.xlsx`)
    }
  }
};
</script>
