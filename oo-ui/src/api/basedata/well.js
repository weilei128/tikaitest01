import request from '@/utils/request'

// 查询井基础信息列表
export function listWell(query) {
  return request({
    url: '/basedata/well/list',
    method: 'get',
    params: query
  })
}

// 查询井基础信息详细
export function getWell(wellId) {
  return request({
    url: '/basedata/well/' + wellId,
    method: 'get'
  })
}

// 新增井基础信息
export function addWell(data) {
  return request({
    url: '/basedata/well',
    method: 'post',
    data: data
  })
}

// 修改井基础信息
export function updateWell(data) {
  return request({
    url: '/basedata/well',
    method: 'put',
    data: data
  })
}

// 删除井基础信息
export function delWell(wellId) {
  return request({
    url: '/basedata/well/' + wellId,
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
