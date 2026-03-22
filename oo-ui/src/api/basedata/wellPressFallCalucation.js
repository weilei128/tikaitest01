import request from '@/utils/request'

// 查询压降测试计算列表
export function listWellPressFallCalucation(query) {
  return request({
    url: '/basedata/wellPressFallCalucation/list',
    method: 'get',
    params: query
  })
}

// 查询压降测试计算详细
export function getWellPressFallCalucation(id) {
  return request({
    url: '/basedata/wellPressFallCalucation/' + id,
    method: 'get'
  })
}

// 新增压降测试计算
export function addWellPressFallCalucation(data) {
  return request({
    url: '/basedata/wellPressFallCalucation',
    method: 'post',
    data: data
  })
}

// 修改压降测试计算
export function updateWellPressFallCalucation(data) {
  return request({
    url: '/basedata/wellPressFallCalucation',
    method: 'put',
    data: data
  })
}

// 删除压降测试计算
export function delWellPressFallCalucation(id) {
  return request({
    url: '/basedata/wellPressFallCalucation/' + id,
    method: 'delete'
  })
}
