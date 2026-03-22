<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="生产段" prop="sectionName">
        <el-input
          v-model="queryParams.sectionName"
          placeholder="请输入生产段名称"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="生效时间" prop="effectiveDate">
        <el-date-picker clearable
          v-model="queryParams.effectiveDate"
          type="date"
          value-format="yyyy-MM-dd"
          placeholder="请选择生效时间">
        </el-date-picker>
      </el-form-item>
      <el-form-item label="失效时间" prop="expiryDate">
        <el-date-picker clearable
          v-model="queryParams.expiryDate"
          type="date"
          value-format="yyyy-MM-dd"
          placeholder="请选择失效时间">
        </el-date-picker>
      </el-form-item>
<!--      <el-form-item label="井筒id" prop="wellboreId">-->
<!--        <el-input-->
<!--          v-model="queryParams.wellboreId"-->
<!--          placeholder="请输入井筒id"-->
<!--          clearable-->
<!--          @keyup.enter.native="handleQuery"-->
<!--        />-->
<!--      </el-form-item>-->
<!--      <el-form-item label="井管id" prop="borepipeId">-->
<!--        <el-input-->
<!--          v-model="queryParams.borepipeId"-->
<!--          placeholder="请输入井管id"-->
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
          v-hasPermi="['basedata:wellSection:add']"
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
          v-hasPermi="['basedata:wellSection:edit']"
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
          v-hasPermi="['basedata:wellSection:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['basedata:wellSection:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="wellSectionList" row-key="id" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="生产段名称" align="center" prop="sectionName" />
      <el-table-column label="生产段顶深" align="center" prop="topDepth" />
      <el-table-column label="生产段底深" align="center" prop="bottomDepth" />
      <el-table-column label="生效时间" align="center" prop="effectiveDate" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.effectiveDate, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="失效时间" align="center" prop="expiryDate" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.expiryDate, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="井筒名" align="center" prop="wellboreId" />
<!--      <el-table-column label="井管id" align="center" prop="borepipeId" />-->
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['basedata:wellSection:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['basedata:wellSection:remove']"
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

    <!-- 添加或修改生产井段对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="生产段名称" prop="sectionName">
          <el-input v-model="form.sectionName" placeholder="请输入生产段名称" />
        </el-form-item>
        <el-form-item label="生产段顶深" prop="topDepth">
          <el-input v-model="form.topDepth" placeholder="请输入生产段顶深" />
        </el-form-item>
        <el-form-item label="生产段底深" prop="bottomDepth">
          <el-input v-model="form.bottomDepth" placeholder="请输入生产段底深" />
        </el-form-item>
        <el-form-item label="生效时间" prop="effectiveDate">
          <el-date-picker clearable
            v-model="form.effectiveDate"
            type="date"
            value-format="yyyy-MM-dd"
            placeholder="请选择生效时间">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="失效时间" prop="expiryDate">
          <el-date-picker clearable
            v-model="form.expiryDate"
            type="date"
            value-format="yyyy-MM-dd"
            placeholder="请选择失效时间">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="井筒id" prop="wellboreId">
          <el-input v-model="form.wellboreId" placeholder="请输入井筒id" />
        </el-form-item>
        <el-form-item label="井管id" prop="borepipeId">
          <el-input v-model="form.borepipeId" placeholder="请输入井管id" />
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
import { listWellSection, getWellSection, delWellSection, addWellSection, updateWellSection } from "@/api/basedata/wellSection";

export default {
  name: "WellSection",
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
      // 生产井段表格数据
      wellSectionList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        sectionName: null,
        topDepth: null,
        bottomDepth: null,
        effectiveDate: null,
        expiryDate: null,
        wellboreId: null,
        borepipeId: null
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
    /** 查询生产井段列表 */
    getList() {
      this.loading = true;
      listWellSection(this.queryParams).then(response => {
        this.wellSectionList = response.rows;
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
        sectionName: null,
        topDepth: null,
        bottomDepth: null,
        effectiveDate: null,
        expiryDate: null,
        wellboreId: null,
        borepipeId: null
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
      this.title = "添加生产井段";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const id = row.id || this.ids
      getWellSection(id).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改生产井段";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.id != null) {
            updateWellSection(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addWellSection(this.form).then(response => {
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
      this.$modal.confirm('是否确认删除生产井段编号为"' + ids + '"的数据项？').then(function() {
        return delWellSection(ids);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {});
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('basedata/wellSection/export', {
        ...this.queryParams
      }, `wellSection_${new Date().getTime()}.xlsx`)
    }
  }
};
</script>
