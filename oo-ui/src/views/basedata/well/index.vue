<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="区块" prop="blockId">
        <el-input
          v-model="queryParams.blockId"
          placeholder="请选择区块"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="井号" prop="wellNum">
        <el-input
          v-model="queryParams.wellNum"
          placeholder="请输入井号"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="施工工艺" prop="constructionTech">
        <el-select v-model="queryParams.constructionTech" placeholder="施工工艺" clearable>
          <el-option
            v-for="dict in dict.type.base_well_tech"
            :key="dict.value"
            :label="dict.label"
            :value="dict.value"
          />
        </el-select>
      </el-form-item>
      <el-form-item label="井类型" prop="wellType">
        <el-select v-model="queryParams.wellType" placeholder="井类型" clearable>
          <el-option
            v-for="dict in dict.type.base_well_type"
            :key="dict.value"
            :label="dict.label"
            :value="dict.value"
          />
        </el-select>
      </el-form-item>
      <el-form-item label="当前状态" prop="currentStatus">
        <el-select v-model="queryParams.currentStatus" placeholder="当前状态" clearable>
          <el-option
            v-for="dict in dict.type.base_well_status"
            :key="dict.value"
            :label="dict.label"
            :value="dict.value"
          />
        </el-select>
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
          v-hasPermi="['basedata:well:add']"
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
          v-hasPermi="['basedata:well:edit']"
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
          v-hasPermi="['basedata:well:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['basedata:well:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="wellList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="井名" align="center" prop="wellId" />
      <el-table-column label="区块" align="center" prop="block.projectName" />
      <el-table-column label="井号" align="center" prop="wellNum" />
      <el-table-column label="井类型" align="center" prop="wellType" />
      <el-table-column label="施工工艺" align="center" prop="constructionTech" />
<!--      <el-table-column label="注入方式" align="center" prop="injectionMode" />-->
      <el-table-column label="注入方式" align="center" prop="injectionMode">
        <template slot-scope="scope">
          <dict-tag :options="dict.type.injection_mode" :value="scope.row.injectionMode"/>
        </template>
      </el-table-column>
      <el-table-column label="当前状态" align="center" prop="currentStatus" />
      <el-table-column label="坐标" align="center" prop="coordinateX" />
      <el-table-column label="坐标" align="center" prop="coordinateY" />
      <el-table-column label="垂深海拔" align="center" prop="altitude" />
      <el-table-column label="补心海拔" align="center" prop="bushingElevation" />
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['basedata:well:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['basedata:well:remove']"
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

    <!-- 添加或修改井基础信息对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
<!--        <el-form-item label="区块id" prop="blockId">-->
<!--          <el-input v-model="form.blockId" placeholder="请输入区块id" />-->
<!--        </el-form-item>-->
        <el-form-item label="区块" >
          <treeselect
            v-model="form.blockId"
            :options="blockOptions"
            :normalizer="normalizer"
            :show-count="true"
            placeholder="选择区块"
          />
        </el-form-item>
        <el-form-item label="井号" prop="wellNum">
          <el-input v-model="form.wellNum" placeholder="请输入井号" />
        </el-form-item>
        <el-form-item label="施工工艺" prop="constructionTech">
          <el-input v-model="form.constructionTech" placeholder="请输入施工工艺" />
        </el-form-item>
        <el-form-item label="注入方式" prop="injectionMode">
<!--          <el-input v-model="form.injectionMode" placeholder="请输入注入方式" />-->
          <el-select v-model="form.injectionMode" placeholder="请输入注入方式" clearable>
            <el-option
              v-for="dict in dict.type.base_well_tech"
              :key="dict.value"
              :label="dict.label"
              :value="dict.value"
            />
          </el-select>
        </el-form-item>
        <el-form-item label="坐标" prop="coordinateX">
          <el-input v-model="form.coordinateX" placeholder="请输入坐标" />
        </el-form-item>
        <el-form-item label="坐标" prop="coordinateY">
          <el-input v-model="form.coordinateY" placeholder="请输入坐标" />
        </el-form-item>
        <el-form-item label="垂深海拔" prop="altitude">
          <el-input v-model="form.altitude" placeholder="请输入垂深海拔" />
        </el-form-item>
        <el-form-item label="补心海拔" prop="bushingElevation">
          <el-input v-model="form.bushingElevation" placeholder="请输入补心海拔" />
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
import { listWell, getWell, delWell, addWell, updateWell } from "@/api/basedata/well";
import {listBlock} from "@/api/basedata/block";
import Treeselect from "@riophae/vue-treeselect";
import "@riophae/vue-treeselect/dist/vue-treeselect.css";

export default {
  name: "Well",
  dicts: ['base_well_type','base_well_tech','base_well_status'],
  components:{Treeselect},
  data() {
    return {
      // 遮罩层
      loading: true,
      // // 选中数组
      // ids: [],
      // 区块树选项
      blockOptions: [],
      // 非单个禁用
      single: true,
      // 非多个禁用
      multiple: true,
      // 显示搜索条件
      showSearch: true,
      // 总条数
      total: 0,
      // 井基础信息表格数据
      wellList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        // blockId: null,
        wellNum: null,
        wellType: null,
        constructionTech: null,
        currentStatus: null
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        blockId: [
          { required: true, message: "区块id不能为空", trigger: "blur" }
        ],
        wellNum: [
          { required: true, message: "井号不能为空", trigger: "blur" }
        ],
      }
    };
  },
  created() {
    this.getList();
  },
  methods: {
    /** 查询井基础信息列表 */
    getList() {
      this.loading = true;
      listWell(this.queryParams).then(response => {
        this.wellList = response.rows;
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
        wellId: null,
        blockId: null,
        wellNum: null,
        wellType: null,
        constructionTech: null,
        injectionMode: null,
        currentStatus: 0,
        coordinateX: null,
        coordinateY: null,
        altitude: null,
        bushingElevation: null
      };
      this.resetForm("form");
    },
    /** 转换区块数据结构 */
    normalizer(node) {
      if (node.children && !node.children.length) {
        delete node.children;
      }
      return {
        id: node.blockId,
        label: node.projectName,
        children: node.children
      };
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
      this.ids = selection.map(item => item.wellId)
      this.single = selection.length!==1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加井基础信息";
      listBlock().then(response => {
        this.blockOptions = this.handleTree(response.data, "blockId");
      });
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const wellId = row.wellId || this.ids
      getWell(wellId).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改井基础信息";
      });
      listBlock(row.blockId).then(response => {
        this.blockOptions = this.handleTree(response.data, "blockId");
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.wellId != null) {
            updateWell(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addWell(this.form).then(response => {
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
      const wellIds = row.wellId || this.ids;
      this.$modal.confirm('是否确认删除井基础信息编号为"' + wellIds + '"的数据项？').then(function() {
        return delWell(wellIds);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {});
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('basedata/well/export', {
        ...this.queryParams
      }, `well_${new Date().getTime()}.xlsx`)
    }
  }
};
</script>
