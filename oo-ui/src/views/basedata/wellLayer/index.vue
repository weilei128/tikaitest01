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
      <el-form-item label="小层名" prop="layName">
        <el-input
          v-model="queryParams.layName"
          placeholder="请输入小层名"
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
          v-hasPermi="['basedata:wellLayer:add']"
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
          v-hasPermi="['basedata:wellLayer:edit']"
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
          v-hasPermi="['basedata:wellLayer:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['basedata:wellLayer:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="wellLayerList" row-key="id" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="小层名" align="center" prop="id" />
      <el-table-column label="井名" align="center" prop="wellId" />
      <el-table-column label="层段顶深" align="center" prop="intervalTop" />
      <el-table-column label="层段底深" align="center" prop="intervalBottom" />
      <el-table-column label="解释序号" align="center" prop="serialNum" />
      <el-table-column label="解释时间" align="center" prop="serialDate" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.serialDate, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="孔隙度" align="center" prop="porosity" />
      <el-table-column label="渗透率" align="center" prop="permeability" />
      <el-table-column label="是否射孔" align="center" prop="perforationStatus" />
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['basedata:wellLayer:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['basedata:wellLayer:remove']"
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

    <!-- 添加或修改单井小层对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="井id" prop="wellId">
          <el-input v-model="form.wellId" placeholder="请输入井id" />
        </el-form-item>
        <el-form-item label="层段顶深" prop="intervalTop">
          <el-input v-model="form.intervalTop" placeholder="请输入层段顶深" />
        </el-form-item>
        <el-form-item label="层段底深" prop="intervalBottom">
          <el-input v-model="form.intervalBottom" placeholder="请输入层段底深" />
        </el-form-item>
        <el-form-item label="解释序号" prop="serialNum">
          <el-input v-model="form.serialNum" placeholder="请输入解释序号" />
        </el-form-item>
        <el-form-item label="解释时间" prop="serialDate">
          <el-date-picker clearable
            v-model="form.serialDate"
            type="date"
            value-format="yyyy-MM-dd"
            placeholder="请选择解释时间">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="孔隙度" prop="porosity">
          <el-input v-model="form.porosity" placeholder="请输入孔隙度" />
        </el-form-item>
        <el-form-item label="渗透率" prop="permeability">
          <el-input v-model="form.permeability" placeholder="请输入渗透率" />
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
import { listWellLayer, getWellLayer, delWellLayer, addWellLayer, updateWellLayer } from "@/api/basedata/wellLayer";

export default {
  name: "WellLayer",
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
      // 单井小层表格数据
      wellLayerList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        wellId: null,
        intervalTop: null,
        intervalBottom: null,
        serialNum: null,
        serialDate: null,
        porosity: null,
        permeability: null,
        perforationStatus: null
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
    /** 查询单井小层列表 */
    getList() {
      this.loading = true;
      listWellLayer(this.queryParams).then(response => {
        this.wellLayerList = response.rows;
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
        intervalTop: null,
        intervalBottom: null,
        serialNum: null,
        serialDate: null,
        porosity: null,
        permeability: null,
        perforationStatus: 0
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
      this.title = "添加单井小层";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const id = row.id || this.ids
      getWellLayer(id).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改单井小层";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.id != null) {
            updateWellLayer(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addWellLayer(this.form).then(response => {
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
      this.$modal.confirm('是否确认删除单井小层编号为"' + ids + '"的数据项？').then(function() {
        return delWellLayer(ids);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {});
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('basedata/wellLayer/export', {
        ...this.queryParams
      }, `wellLayer_${new Date().getTime()}.xlsx`)
    }
  }
};
</script>
