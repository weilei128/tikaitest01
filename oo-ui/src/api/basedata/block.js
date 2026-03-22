import request from '@/utils/request'

// 查询区块列表
export function listBlock(query) {
  return request({
    url: '/basedata/block/list',
    method: 'get',
    params: query
  })
}

// 查询区块详细
export function getBlock(blockId) {
  return request({
    url: '/basedata/block/' + blockId,
    method: 'get'
  })
}

// 新增区块
export function addBlock(data) {
  return request({
    url: '/basedata/block',
    method: 'post',
    data: data
  })
}

// 修改区块
export function updateBlock(data) {
  return request({
    url: '/basedata/block',
    method: 'put',
    data: data
  })
}

// 删除区块
export function delBlock(blockId) {
  return request({
    url: '/basedata/block/' + blockId,
    method: 'delete'
  })
}

//获取区块下拉树列表
export function treeSelect(data) {
  return request({
    url: '/basedata/block/treeselect',
    method: 'get',
    data: data
  })

}
