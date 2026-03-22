<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="井筒名" prop="wellboreId">
        <el-input
          v-model="queryParams.wellboreId"
          placeholder="请输入井筒名"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="来源活动" prop="sourceActivityId">
        <el-input
          v-model="queryParams.sourceActivityId"
          placeholder="请输入来源活动"
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
          v-hasPermi="['basedata:wellSurvey:add']"
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
          v-hasPermi="['basedata:wellSurvey:edit']"
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
          v-hasPermi="['basedata:wellSurvey:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['basedata:wellSurvey:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="wellSurveyList" row-key="measureDataId" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
<!--      <el-table-column label="测斜数据记录标识" align="center" prop="measureDataId" />-->
<!--      <el-table-column label="数据集编码" align="center" prop="datasetClass" />-->
      <el-table-column label="井筒名" align="center" prop="wellboreId" />
      <el-table-column label="来源活动" align="center" prop="sourceActivityId" />
      <el-table-column label="测点斜深" align="center" prop="measurePointMd" />
      <el-table-column label="测点垂深" align="center" prop="measurePointTvd" />
      <el-table-column label="井斜角" align="center" prop="inclination" />
      <el-table-column label="方位角" align="center" prop="azimuth" />
      <el-table-column label="东西位移" align="center" prop="displacementEw" />
      <el-table-column label="南北位移" align="center" prop="displacementNs" />
      <el-table-column label="狗腿度" align="center" prop="dogleg" />
      <el-table-column label="水平位移" align="center" prop="closureHorizon" />
      <el-table-column label="闭合方位" align="center" prop="closureAzimuth" />
      <el-table-column label="闭合距" align="center" prop="closureDispl" />
      <el-table-column label="备注" align="center" prop="remark" />
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['basedata:wellSurvey:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['basedata:wellSurvey:remove']"
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

    <!-- 添加或修改井眼轨迹信息对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="数据集编码" prop="datasetClass">
          <el-input v-model="form.datasetClass" placeholder="请输入数据集编码" />
        </el-form-item>
        <el-form-item label="井筒标识" prop="wellboreId">
          <el-input v-model="form.wellboreId" placeholder="请输入井筒标识" />
        </el-form-item>
        <el-form-item label="来源活动标识" prop="sourceActivityId">
          <el-input v-model="form.sourceActivityId" placeholder="请输入来源活动标识" />
        </el-form-item>
        <el-form-item label="测点斜深" prop="measurePointMd">
          <el-input v-model="form.measurePointMd" placeholder="请输入测点斜深" />
        </el-form-item>
        <el-form-item label="测点垂深" prop="measurePointTvd">
          <el-input v-model="form.measurePointTvd" placeholder="请输入测点垂深" />
        </el-form-item>
        <el-form-item label="井斜角" prop="inclination">
          <el-input v-model="form.inclination" placeholder="请输入井斜角" />
        </el-form-item>
        <el-form-item label="方位角" prop="azimuth">
          <el-input v-model="form.azimuth" placeholder="请输入方位角" />
        </el-form-item>
        <el-form-item label="东西位移" prop="displacementEw">
          <el-input v-model="form.displacementEw" placeholder="请输入东西位移" />
        </el-form-item>
        <el-form-item label="南北位移" prop="displacementNs">
          <el-input v-model="form.displacementNs" placeholder="请输入南北位移" />
        </el-form-item>
        <el-form-item label="狗腿度" prop="dogleg">
          <el-input v-model="form.dogleg" placeholder="请输入狗腿度" />
        </el-form-item>
        <el-form-item label="水平位移" prop="closureHorizon">
          <el-input v-model="form.closureHorizon" placeholder="请输入水平位移" />
        </el-form-item>
        <el-form-item label="闭合方位" prop="closureAzimuth">
          <el-input v-model="form.closureAzimuth" placeholder="请输入闭合方位" />
        </el-form-item>
        <el-form-item label="闭合距" prop="closureDispl">
          <el-input v-model="form.closureDispl" placeholder="请输入闭合距" />
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
import { listWellSurvey, getWellSurvey, delWellSurvey, addWellSurvey, updateWellSurvey } from "@/api/basedata/wellSurvey";

export default {
  name: "WellSurvey",
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
      // 井眼轨迹信息表格数据
      wellSurveyList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        datasetClass: null,
        wellboreId: null,
        sourceActivityId: null,
        measurePointMd: null,
        measurePointTvd: null,
        inclination: null,
        azimuth: null,
        displacementEw: null,
        displacementNs: null,
        dogleg: null,
        closureHorizon: null,
        closureAzimuth: null,
        closureDispl: null,
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
    /** 查询井眼轨迹信息列表 */
    getList() {
      this.loading = true;
      listWellSurvey(this.queryParams).then(response => {
        this.wellSurveyList = response.rows;
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
        measureDataId: null,
        datasetClass: null,
        wellboreId: null,
        sourceActivityId: null,
        measurePointMd: null,
        measurePointTvd: null,
        inclination: null,
        azimuth: null,
        displacementEw: null,
        displacementNs: null,
        dogleg: null,
        closureHorizon: null,
        closureAzimuth: null,
        closureDispl: null,
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
      this.ids = selection.map(item => item.measureDataId)
      this.single = selection.length!==1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加井眼轨迹信息";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const measureDataId = row.measureDataId || this.ids
      getWellSurvey(measureDataId).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改井眼轨迹信息";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.measureDataId != null) {
            updateWellSurvey(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addWellSurvey(this.form).then(response => {
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
      const measureDataIds = row.measureDataId || this.ids;
      this.$modal.confirm('是否确认删除井眼轨迹信息编号为"' + measureDataIds + '"的数据项？').then(function() {
        return delWellSurvey(measureDataIds);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {});
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('basedata/wellSurvey/export', {
        ...this.queryParams
      }, `wellSurvey_${new Date().getTime()}.xlsx`)
    }
  }
};
</script>
