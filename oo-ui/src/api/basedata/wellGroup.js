import request from '@/utils/request'

// 查询井组信息列表
export function listWellGroup(query) {
  return request({
    url: '/basedata/wellGroup/list',
    method: 'get',
    params: query
  })
}

// 查询井组信息详细
export function getWellGroup(wellGroupId) {
  return request({
    url: '/basedata/wellGroup/' + wellGroupId,
    method: 'get'
  })
}

// 新增井组信息
export function addWellGroup(data) {
  return request({
    url: '/basedata/wellGroup',
    method: 'post',
    data: data
  })
}

// 修改井组信息
export function updateWellGroup(data) {
  return request({
    url: '/basedata/wellGroup',
    method: 'put',
    data: data
  })
}

// 删除井组信息
export function delWellGroup(wellGroupId) {
  return request({
    url: '/basedata/wellGroup/' + wellGroupId,
    method: 'delete'
  })
}
