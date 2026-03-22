<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="井号" prop="borepipeId">
        <el-input
          v-model="queryParams.borepipeId"
          placeholder="请输入井号"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="开发层位" prop="productionIntervalId">
        <el-input
          v-model="queryParams.productionIntervalId"
          placeholder="请输入开发层位"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="日期" prop="absorpDate">
        <el-date-picker clearable
          v-model="queryParams.absorpDate"
          type="date"
          value-format="yyyy-MM-dd"
          placeholder="请选择日期">
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
          v-hasPermi="['basedata:wellAbsorp:add']"
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
          v-hasPermi="['basedata:wellAbsorp:edit']"
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
          v-hasPermi="['basedata:wellAbsorp:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['basedata:wellAbsorp:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="wellAbsorpList" row-key="absorpId" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="试井项目" align="center" prop="wellTestProjectId" />
      <el-table-column label="井号" align="center" prop="borepipeId" />
      <el-table-column label="开发层位" align="center" prop="productionIntervalId" />
      <el-table-column label="日期" align="center" prop="absorpDate" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.absorpDate, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="注入量" align="center" prop="waterInj" />
      <el-table-column label="流压" align="center" prop="flowPress" />
      <el-table-column label="静压" align="center" prop="staticPress" />
      <el-table-column label="注入井口压力" align="center" prop="intakeWellHeadPress" />
      <el-table-column label="水嘴直径" align="center" prop="waterNozzleDiameter" />
      <el-table-column label="备注" align="center" prop="remark" />
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['basedata:wellAbsorp:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['basedata:wellAbsorp:remove']"
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

    <!-- 添加或修改吸水指数测试对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="试井项目标识" prop="wellTestProjectId">
          <el-input v-model="form.wellTestProjectId" placeholder="请输入试井项目标识" />
        </el-form-item>
        <el-form-item label="井号代码" prop="borepipeId">
          <el-input v-model="form.borepipeId" placeholder="请输入井号代码" />
        </el-form-item>
        <el-form-item label="开发层位唯一ID 为空时代表全井测试" prop="productionIntervalId">
          <el-input v-model="form.productionIntervalId" placeholder="请输入开发层位唯一ID 为空时代表全井测试" />
        </el-form-item>
        <el-form-item label="日期" prop="absorpDate">
          <el-date-picker clearable
            v-model="form.absorpDate"
            type="date"
            value-format="yyyy-MM-dd"
            placeholder="请选择日期">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="注入量" prop="waterInj">
          <el-input v-model="form.waterInj" placeholder="请输入注入量" />
        </el-form-item>
        <el-form-item label="流压" prop="flowPress">
          <el-input v-model="form.flowPress" placeholder="请输入流压" />
        </el-form-item>
        <el-form-item label="静压" prop="staticPress">
          <el-input v-model="form.staticPress" placeholder="请输入静压" />
        </el-form-item>
        <el-form-item label="注入井口压力" prop="intakeWellHeadPress">
          <el-input v-model="form.intakeWellHeadPress" placeholder="请输入注入井口压力" />
        </el-form-item>
        <el-form-item label="水嘴直径" prop="waterNozzleDiameter">
          <el-input v-model="form.waterNozzleDiameter" placeholder="请输入水嘴直径" />
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
import { listWellAbsorp, getWellAbsorp, delWellAbsorp, addWellAbsorp, updateWellAbsorp } from "@/api/basedata/wellAbsorp";

export default {
  name: "WellAbsorp",
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
      // 吸水指数测试表格数据
      wellAbsorpList: [],
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
        absorpDate: null,
        waterInj: null,
        flowPress: null,
        staticPress: null,
        intakeWellHeadPress: null,
        waterNozzleDiameter: null,
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
    /** 查询吸水指数测试列表 */
    getList() {
      this.loading = true;
      listWellAbsorp(this.queryParams).then(response => {
        this.wellAbsorpList = response.rows;
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
        absorpId: null,
        wellTestProjectId: null,
        borepipeId: null,
        productionIntervalId: null,
        absorpDate: null,
        waterInj: null,
        flowPress: null,
        staticPress: null,
        intakeWellHeadPress: null,
        waterNozzleDiameter: null,
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
      this.ids = selection.map(item => item.absorpId)
      this.single = selection.length!==1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加吸水指数测试";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const absorpId = row.absorpId || this.ids
      getWellAbsorp(absorpId).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改吸水指数测试";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.absorpId != null) {
            updateWellAbsorp(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addWellAbsorp(this.form).then(response => {
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
      const absorpIds = row.absorpId || this.ids;
      this.$modal.confirm('是否确认删除吸水指数测试编号为"' + absorpIds + '"的数据项？').then(function() {
        return delWellAbsorp(absorpIds);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {});
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('basedata/wellAbsorp/export', {
        ...this.queryParams
      }, `wellAbsorp_${new Date().getTime()}.xlsx`)
    }
  }
};
</script>
