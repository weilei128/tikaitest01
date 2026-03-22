<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
<!--      <el-form-item label="试井项目" prop="wellTestProjectId">-->
<!--        <el-input-->
<!--          v-model="queryParams.wellTestProjectId"-->
<!--          placeholder="请输入试井项目"-->
<!--          clearable-->
<!--          @keyup.enter.native="handleQuery"-->
<!--        />-->
<!--      </el-form-item>-->
      <el-form-item label="井号" prop="borepipeId">
        <el-input
          v-model="queryParams.borepipeId"
          placeholder="请输入井号"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="层位" prop="productionIntervalId">
        <el-input
          v-model="queryParams.productionIntervalId"
          placeholder="请输入层位"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="测试时间" prop="measureTime">
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
          v-hasPermi="['basedata:wellPressFallCalucation:add']"
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
          v-hasPermi="['basedata:wellPressFallCalucation:edit']"
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
          v-hasPermi="['basedata:wellPressFallCalucation:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['basedata:wellPressFallCalucation:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="wellPressFallCalucationList" row-key="id" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
<!--      <el-table-column label="主键" align="center" prop="id" />-->
      <el-table-column label="试井项目" align="center" prop="wellTestProjectId" />
      <el-table-column label="井号" align="center" prop="borepipeId" />
      <el-table-column label="层位" align="center" prop="productionIntervalId" />
      <el-table-column label="测试时间" align="center" prop="measureTime" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.measureTime, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="小层原渗透率" align="center" prop="permeability" width="98px" />
      <el-table-column label="注入量" align="center" prop="injvolume" />
      <el-table-column label="注入强度" align="center" prop="intensity" />
      <el-table-column label="注入厚度" align="center" prop="thickness" />
      <el-table-column label="PI值" align="center" prop="pi" />
      <el-table-column label="修正PI值" align="center" prop="correctpi" />
      <el-table-column label="无因此PI值" align="center" prop="dimensionlesspi" />
      <el-table-column label="FD值" align="center" prop="fd" />
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['basedata:wellPressFallCalucation:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['basedata:wellPressFallCalucation:remove']"
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

    <!-- 添加或修改压降测试计算对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="试井项目标识" prop="wellTestProjectId">
          <el-input v-model="form.wellTestProjectId" placeholder="请输入试井项目标识" />
        </el-form-item>
        <el-form-item label="井号代码" prop="borepipeId">
          <el-input v-model="form.borepipeId" placeholder="请输入井号代码" />
        </el-form-item>
        <el-form-item label="层位代码" prop="productionIntervalId">
          <el-input v-model="form.productionIntervalId" placeholder="请输入层位代码" />
        </el-form-item>
        <el-form-item label="测试时间" prop="measureTime">
          <el-date-picker clearable
            v-model="form.measureTime"
            type="date"
            value-format="yyyy-MM-dd"
            placeholder="请选择测试时间">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="小层原始渗透率" prop="permeability">
          <el-input v-model="form.permeability" placeholder="请输入小层原始渗透率" />
        </el-form-item>
        <el-form-item label="注入量" prop="injvolume">
          <el-input v-model="form.injvolume" placeholder="请输入注入量" />
        </el-form-item>
        <el-form-item label="注入强度" prop="intensity">
          <el-input v-model="form.intensity" placeholder="请输入注入强度" />
        </el-form-item>
        <el-form-item label="注入厚度" prop="thickness">
          <el-input v-model="form.thickness" placeholder="请输入注入厚度" />
        </el-form-item>
        <el-form-item label="PI值" prop="pi">
          <el-input v-model="form.pi" placeholder="请输入PI值" />
        </el-form-item>
        <el-form-item label="修正PI值" prop="correctpi">
          <el-input v-model="form.correctpi" placeholder="请输入修正PI值" />
        </el-form-item>
        <el-form-item label="无因此PI值" prop="dimensionlesspi">
          <el-input v-model="form.dimensionlesspi" placeholder="请输入无因此PI值" />
        </el-form-item>
        <el-form-item label="FD值" prop="fd">
          <el-input v-model="form.fd" placeholder="请输入FD值" />
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
import { listWellPressFallCalucation, getWellPressFallCalucation, delWellPressFallCalucation, addWellPressFallCalucation, updateWellPressFallCalucation } from "@/api/basedata/wellPressFallCalucation";

export default {
  name: "WellPressFallCalucation",
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
      // 压降测试计算表格数据
      wellPressFallCalucationList: [],
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
        permeability: null,
        injvolume: null,
        intensity: null,
        thickness: null,
        pi: null,
        correctpi: null,
        dimensionlesspi: null,
        fd: null
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
    /** 查询压降测试计算列表 */
    getList() {
      this.loading = true;
      listWellPressFallCalucation(this.queryParams).then(response => {
        this.wellPressFallCalucationList = response.rows;
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
        wellTestProjectId: null,
        borepipeId: null,
        productionIntervalId: null,
        measureTime: null,
        permeability: null,
        injvolume: null,
        intensity: null,
        thickness: null,
        pi: null,
        correctpi: null,
        dimensionlesspi: null,
        fd: null
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
      this.title = "添加压降测试计算";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const id = row.id || this.ids
      getWellPressFallCalucation(id).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改压降测试计算";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.id != null) {
            updateWellPressFallCalucation(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addWellPressFallCalucation(this.form).then(response => {
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
      this.$modal.confirm('是否确认删除压降测试计算编号为"' + ids + '"的数据项？').then(function() {
        return delWellPressFallCalucation(ids);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {});
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('basedata/wellPressFallCalucation/export', {
        ...this.queryParams
      }, `wellPressFallCalucation_${new Date().getTime()}.xlsx`)
    }
  }
};
</script>
