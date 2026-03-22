<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="井名" prop="wellId">
        <el-input
          v-model="queryParams.wellId"
          placeholder="请输入井名"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="小层名" prop="layerId">
        <el-input
          v-model="queryParams.layerId"
          placeholder="请输入小层名"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="取样时间" prop="sampleTime">
        <el-date-picker clearable
          v-model="queryParams.sampleTime"
          type="date"
          value-format="yyyy-MM-dd"
          placeholder="开始时间">
        </el-date-picker>
        <spa> —— </spa>
        <el-date-picker clearable
                        v-model="queryParams.sampleTime"
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
          v-hasPermi="['basedata:fluidProperty:add']"
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
          v-hasPermi="['basedata:fluidProperty:edit']"
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
          v-hasPermi="['basedata:fluidProperty:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['basedata:fluidProperty:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="fluidPropertyList" row-key="id" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
<!--      <el-table-column label="流体id" align="center" prop="id" />-->
      <el-table-column label="井名" align="center" prop="wellId" />
      <el-table-column label="小层名" align="center" prop="layerId" />
      <el-table-column label="取样井段顶" align="center" prop="wellSectionTop" width="85" />
      <el-table-column label="取样井段底" align="center" prop="wellSectionBottom" width="85"/>
      <el-table-column label="地层温度" align="center" prop="formationTemperature" />
      <el-table-column label="饱和压力" align="center" prop="saturationPressure" />
      <el-table-column label="气油比" align="center" prop="gasOilRatio" />
      <el-table-column label="体积系数" align="center" prop="volumeFactor" />
      <el-table-column label="压缩系数" align="center" prop="zipRatio" />
      <el-table-column label="原油密度(地面)" align="center" prop="oilDensityOn" />
      <el-table-column label="原油密度" align="center" prop="oilDensityDown" />
      <el-table-column label="原油粘度" align="center" prop="oilViscosityOn" />
      <el-table-column label="原油粘度" align="center" prop="oilViscosityDown" />
      <el-table-column label="取样时间" align="center" prop="sampleTime" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.sampleTime, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['basedata:fluidProperty:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['basedata:fluidProperty:remove']"
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

    <!-- 添加或修改流体性质（油）对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="井id" prop="wellId">
          <el-input v-model="form.wellId" placeholder="请输入井id" />
        </el-form-item>
        <el-form-item label="小层id" prop="layerId">
          <el-input v-model="form.layerId" placeholder="请输入小层id" />
        </el-form-item>
        <el-form-item label="取样井段顶" prop="wellSectionTop">
          <el-input v-model="form.wellSectionTop" placeholder="请输入取样井段顶" />
        </el-form-item>
        <el-form-item label="取样井段底" prop="wellSectionBottom">
          <el-input v-model="form.wellSectionBottom" placeholder="请输入取样井段底" />
        </el-form-item>
        <el-form-item label="地层温度" prop="formationTemperature">
          <el-input v-model="form.formationTemperature" placeholder="请输入地层温度" />
        </el-form-item>
        <el-form-item label="饱和压力" prop="saturationPressure">
          <el-input v-model="form.saturationPressure" placeholder="请输入饱和压力" />
        </el-form-item>
        <el-form-item label="气油比" prop="gasOilRatio">
          <el-input v-model="form.gasOilRatio" placeholder="请输入溶解气油比" />
        </el-form-item>
        <el-form-item label="体积系数" prop="volumeFactor">
          <el-input v-model="form.volumeFactor" placeholder="请输入体积系数" />
        </el-form-item>
        <el-form-item label="压缩系数" prop="zipRatio">
          <el-input v-model="form.zipRatio" placeholder="请输入压缩系数" />
        </el-form-item>
        <el-form-item label="原油密度(地面)" prop="oilDensityOn">
          <el-input v-model="form.oilDensityOn" placeholder="请输入原油密度(地面)" />
        </el-form-item>
        <el-form-item label="原油密度" prop="oilDensityDown">
          <el-input v-model="form.oilDensityDown" placeholder="请输入原油密度" />
        </el-form-item>
        <el-form-item label="原油粘度" prop="oilViscosityOn">
          <el-input v-model="form.oilViscosityOn" placeholder="请输入原油粘度" />
        </el-form-item>
        <el-form-item label="原油粘度" prop="oilViscosityDown">
          <el-input v-model="form.oilViscosityDown" placeholder="请输入原油粘度" />
        </el-form-item>
        <el-form-item label="取样时间" prop="sampleTime">
          <el-date-picker clearable
            v-model="form.sampleTime"
            type="date"
            value-format="yyyy-MM-dd"
            placeholder="请选择取样时间">
          </el-date-picker>
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
import { listFluidProperty, getFluidProperty, delFluidProperty, addFluidProperty, updateFluidProperty } from "@/api/basedata/fluidProperty";

export default {
  name: "FluidProperty",
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
      // 流体性质（油）表格数据
      fluidPropertyList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        wellId: null,
        layerId: null,
        wellSectionTop: null,
        wellSectionBottom: null,
        formationTemperature: null,
        saturationPressure: null,
        gasOilRatio: null,
        volumeFactor: null,
        zipRatio: null,
        oilDensityOn: null,
        oilDensityDown: null,
        oilViscosityOn: null,
        oilViscosityDown: null,
        sampleTime: null
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        wellId: [
          { required: true, message: "井id不能为空", trigger: "blur" }
        ],
      }
    };
  },
  created() {
    this.getList();
  },
  methods: {
    /** 查询流体性质（油）列表 */
    getList() {
      this.loading = true;
      listFluidProperty(this.queryParams).then(response => {
        this.fluidPropertyList = response.rows;
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
        layerId: null,
        wellSectionTop: null,
        wellSectionBottom: null,
        formationTemperature: null,
        saturationPressure: null,
        gasOilRatio: null,
        volumeFactor: null,
        zipRatio: null,
        oilDensityOn: null,
        oilDensityDown: null,
        oilViscosityOn: null,
        oilViscosityDown: null,
        sampleTime: null
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
      this.title = "添加流体性质（油）";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const id = row.id || this.ids
      getFluidProperty(id).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改流体性质（油）";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.id != null) {
            updateFluidProperty(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addFluidProperty(this.form).then(response => {
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
      this.$modal.confirm('是否确认删除流体性质（油）编号为"' + ids + '"的数据项？').then(function() {
        return delFluidProperty(ids);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {});
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('basedata/fluidProperty/export', {
        ...this.queryParams
      }, `fluidProperty_${new Date().getTime()}.xlsx`)
    }
  }
};
</script>
