import request from '@/utils/request'

// 查询压降测试基础列表
export function listWellPressFall(query) {
  return request({
    url: '/basedata/wellPressFall/list',
    method: 'get',
    params: query
  })
}

// 查询压降测试基础详细
export function getWellPressFall(pressFallId) {
  return request({
    url: '/basedata/wellPressFall/' + pressFallId,
    method: 'get'
  })
}

// 新增压降测试基础
export function addWellPressFall(data) {
  return request({
    url: '/basedata/wellPressFall',
    method: 'post',
    data: data
  })
}

// 修改压降测试基础
export function updateWellPressFall(data) {
  return request({
    url: '/basedata/wellPressFall',
    method: 'put',
    data: data
  })
}

// 删除压降测试基础
export function delWellPressFall(pressFallId) {
  return request({
    url: '/basedata/wellPressFall/' + pressFallId,
    method: 'delete'
  })
}
