<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="项目名称" prop="projectName">
        <el-input
          v-model="queryParams.projectName"
          placeholder="请输入项目名称"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
<!--      <el-form-item label="父id" prop="parentId">-->
<!--        <el-input-->
<!--          v-model="queryParams.parentId"-->
<!--          placeholder="请输入父id"-->
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
          v-hasPermi="['basedata:block:add']"
        >新增</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['basedata:block:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList" :columns="columns"></right-toolbar>
    </el-row>

    <el-table
      v-if="refreshTable"
      v-loading="loading"
      :data="blockList"
      row-key="blockId"
      :default-expand-all="isExpandAll"
      :tree-props="{children: 'children', hasChildren: 'hasChildren'}"
    >
      <el-table-column prop="projectName" label="项目名称" width="260"></el-table-column>
      <el-table-column prop="projectType" label="项目类型" width="100">
        <template slot-scope="scope">
          <dict-tag :options="dict.type.base_block" :value="scope.row.projectType"/>
        </template>
      </el-table-column>
<!--      <el-table-column label="创建时间" align="center" prop="createTime" width="200">-->
<!--        <template slot-scope="scope">-->
<!--          <span>{{ parseTime(scope.row.createTime) }}</span>-->
<!--        </template>-->
<!--      </el-table-column>-->
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-plus"
            @click="handleAdd(scope.row)"
            v-hasPermi="['basedata:block:add']"
          >新增</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['basedata:block:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['basedata:block:remove']"
          >删除</el-button>
        </template>
      </el-table-column>
    </el-table>

<!--    <pagination-->
<!--      v-show="total>0"-->
<!--      :total="total"-->
<!--      :page.sync="queryParams.pageNum"-->
<!--      :limit.sync="queryParams.pageSize"-->
<!--      @pagination="getList"-->
<!--    />-->

    <!-- 添加或修改区块对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="父级项目" prop="parentId"  v-if="form.parentId !== 0">
          <treeselect
            v-model="form.parentId"
            :options="blockOptions"
            :normalizer="normalizer"
            :show-count="true"
            placeholder="选择父级项目"
          />
        </el-form-item>
        <el-form-item label="项目名称" prop="projectName">
          <el-input v-model="form.projectName" placeholder="请输入项目名称" />
        </el-form-item>
        <el-form-item label="项目类型" prop="projectName">
          <el-select v-model="form.projectType" placeholder="项目类型" clearable>
            <el-option
              v-for="dict in dict.type.base_block"
              :key="dict.value"
              :label="dict.label"
              :value="dict.value"
            />
          </el-select>
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
import { listBlock, getBlock, delBlock, addBlock, updateBlock } from "@/api/basedata/block";
import Treeselect from "@riophae/vue-treeselect";
import "@riophae/vue-treeselect/dist/vue-treeselect.css";
import {getDept, listDept, listDeptExcludeChild} from "@/api/system/dept";



export default {
  name: "Block",
  dicts: ['base_block'],
  components:{Treeselect},
  data() {
    return {
      // 遮罩层
      loading: true,
      // 选中数组
      // ids: [],
      // 区块树选项
      blockOptions: [],
      // 非单个禁用
      single: true,
      // 非多个禁用
      multiple: true,
      // 是否展开，默认全部展开
      isExpandAll: true,
      // 显示搜索条件
      showSearch: true,
      // 总条数
      total: 0,
      // 区块表格数据
      blockList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 重新渲染表格状态
      refreshTable: true,
      // 查询参数
      queryParams: {
        projectName: null,
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        parentId: [
          { required: true, message: "上级项目不能为空" }
        ],
      },
      // // 列信息
      // columns: [
      //   { key: 0, label: `区块id`, visible: false },
      //   { key: 1, label: `项目类型`, visible: true },
      //   { key: 2, label: `项目名称`, visible: true },
      //   { key: 3, label: `父级名称`, visible: true },
      // ],
    };
  },
  created() {
    this.getList();
  },
  methods: {
    /** 查询区块列表 */
    getList() {
      this.loading = true;
      listBlock(this.queryParams).then(response => {
        this.blockList=  this.handleTree(response.data, "blockId");//response.rows;
       // this.total = response.total;
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
        blockId: null,
        projectType: null,
        projectName: null,
        parentId: null
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
    // /** 查询区块下拉树结构 */
    // getTreeselect() {
    //   listBlock().then(response => {
    //     this.blockOptions = [];
    //     const block = { blockId: 0, projectName: '主项目', children: [] };
    //     block.children = this.handleTree(response.data, "blockId");
    //     this.blockOptions.push(block);
    //   });
    // },
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
    // // 多选框选中数据
    // handleSelectionChange(selection) {
    //   this.ids = selection.map(item => item.blockId)
    //   this.single = selection.length!==1
    //   this.multiple = !selection.length
    // },
    /** 新增按钮操作 */
    handleAdd(row) {
      this.reset();
      if (row != undefined) {
        this.form.parentId = row.blockId;
      }
      this.open = true;
      this.title = "添加区块";
      listBlock().then(response => {
        this.blockOptions = this.handleTree(response.data, "blockId");
      });
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      if (row != null && row.blockId) {
        this.form.parentId = row.blockId;
      } else {
        this.form.parentId = 0;
      }
      getBlock(row.blockId).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改区块";
      });
      listBlock(row.blockId).then(response => {
        this.blockOptions = this.handleTree(response.data, "blockId");
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.blockId != null) {
            updateBlock(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addBlock(this.form).then(response => {
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
      const blockIds = row.blockId; //|| this.ids
      this.$modal.confirm('是否确认删除该数据项？').then(function() {
        return delBlock(blockIds);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {});
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('basedata/block/export', {
        ...this.queryParams
      }, `block_${new Date().getTime()}.xlsx`)
    }
  }
};
</script>
