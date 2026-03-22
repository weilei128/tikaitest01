<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="试井项目" prop="wellTestProjectId">
        <el-input
          v-model="queryParams.wellTestProjectId"
          placeholder="请输入试井项目"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
<!--      <el-form-item label="井管代码" prop="borepipeId">-->
<!--        <el-input-->
<!--          v-model="queryParams.borepipeId"-->
<!--          placeholder="请输入井管代码"-->
<!--          clearable-->
<!--          @keyup.enter.native="handleQuery"-->
<!--        />-->
<!--      </el-form-item>-->
      <el-form-item label="分注层位" prop="productionIntervalId">
        <el-input
          v-model="queryParams.productionIntervalId"
          placeholder="请输入分注层位"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="测量时间" prop="measureTime">
        <el-date-picker clearable
          v-model="queryParams.measureTime"
          type="date"
          value-format="yyyy-MM-dd"
          placeholder="开始时间">
        </el-date-picker>
        <span> —— </span>
        <el-date-picker clearable
                        v-model="queryParams.measureTime"
                        type="date"
                        value-format="yyyy-MM-dd"
                        placeholder="结束时间">
        </el-date-picker>
      </el-form-item>
<!--      <el-form-item label="累计时长" prop="cumDuration">-->
<!--        <el-input-->
<!--          v-model="queryParams.cumDuration"-->
<!--          placeholder="请输入累计时长"-->
<!--          clearable-->
<!--          @keyup.enter.native="handleQuery"-->
<!--        />-->
<!--      </el-form-item>-->
<!--      <el-form-item label="注入井口压力" prop="intakeWellHeadPress">-->
<!--        <el-input-->
<!--          v-model="queryParams.intakeWellHeadPress"-->
<!--          placeholder="请输入注入井口压力"-->
<!--          clearable-->
<!--          @keyup.enter.native="handleQuery"-->
<!--        />-->
<!--      </el-form-item>-->
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
          v-hasPermi="['basedata:wellPressFall:add']"
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
          v-hasPermi="['basedata:wellPressFall:edit']"
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
          v-hasPermi="['basedata:wellPressFall:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['basedata:wellPressFall:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="wellPressFallList" row-key="pressFallId" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
<!--      <el-table-column label="压力降落试井数据记录标识" align="center" prop="pressFallId" />-->
      <el-table-column label="试井项目" align="center" prop="wellTestProjectId" />
      <el-table-column label="井管" align="center" prop="borepipeId" />
      <el-table-column label="分注层位" align="center" prop="productionIntervalId" />
      <el-table-column label="测量时间" align="center" prop="measureTime" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.measureTime, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="累计时长" align="center" prop="cumDuration" />
      <el-table-column label="注入井口压力" align="center" prop="intakeWellHeadPress" />
      <el-table-column label="备注" align="center" prop="remark" />
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['basedata:wellPressFall:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['basedata:wellPressFall:remove']"
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

    <!-- 添加或修改压降测试基础对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="试井项目标识" prop="wellTestProjectId">
          <el-input v-model="form.wellTestProjectId" placeholder="请输入试井项目标识" />
        </el-form-item>
        <el-form-item label="井管代码" prop="borepipeId">
          <el-input v-model="form.borepipeId" placeholder="请输入井管代码" />
        </el-form-item>
        <el-form-item label="分注层位" prop="productionIntervalId">
          <el-input v-model="form.productionIntervalId" placeholder="请输入分注层位" />
        </el-form-item>
        <el-form-item label="测量时间" prop="measureTime">
          <el-date-picker clearable
            v-model="form.measureTime"
            type="date"
            value-format="yyyy-MM-dd"
            placeholder="请选择测量时间">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="累计时长" prop="cumDuration">
          <el-input v-model="form.cumDuration" placeholder="请输入累计时长" />
        </el-form-item>
        <el-form-item label="注入井口压力" prop="intakeWellHeadPress">
          <el-input v-model="form.intakeWellHeadPress" placeholder="请输入注入井口压力" />
        </el-form-item>
        <el-form-item label="备注" prop="remark">
          <el-input v-model="form.remark" type="textarea" placeholder="请输入内容" />
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
import { listWellPressFall, getWellPressFall, delWellPressFall, addWellPressFall, updateWellPressFall } from "@/api/basedata/wellPressFall";

export default {
  name: "WellPressFall",
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
      // 压降测试基础表格数据
      wellPressFallList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        wellTestProjectId: null,
        borepipeId: null,
        productionIntervalId: null,
        measureTime: null,
        cumDuration: null,
        intakeWellHeadPress: null,
        remark: null
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
    /** 查询压降测试基础列表 */
    getList() {
      this.loading = true;
      listWellPressFall(this.queryParams).then(response => {
        this.wellPressFallList = response.rows;
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
        pressFallId: null,
        wellTestProjectId: null,
        borepipeId: null,
        productionIntervalId: null,
        measureTime: null,
        cumDuration: null,
        intakeWellHeadPress: null,
        remark: null
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
      this.ids = selection.map(item => item.pressFallId)
      this.single = selection.length!==1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加压降测试基础";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const pressFallId = row.pressFallId || this.ids
      getWellPressFall(pressFallId).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改压降测试基础";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.pressFallId != null) {
            updateWellPressFall(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addWellPressFall(this.form).then(response => {
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
      const pressFallIds = row.pressFallId || this.ids;
      this.$modal.confirm('是否确认删除压降测试基础编号为"' + pressFallIds + '"的数据项？').then(function() {
        return delWellPressFall(pressFallIds);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {});
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('basedata/wellPressFall/export', {
        ...this.queryParams
      }, `wellPressFall_${new Date().getTime()}.xlsx`)
    }
  }
};
</script>
