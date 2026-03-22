import request from '@/utils/request'

// 查询单井小层列表
export function listWellLayer(query) {
  return request({
    url: '/basedata/wellLayer/list',
    method: 'get',
    params: query
  })
}

// 查询单井小层详细
export function getWellLayer(id) {
  return request({
    url: '/basedata/wellLayer/' + id,
    method: 'get'
  })
}

// 新增单井小层
export function addWellLayer(data) {
  return request({
    url: '/basedata/wellLayer',
    method: 'post',
    data: data
  })
}

// 修改单井小层
export function updateWellLayer(data) {
  return request({
    url: '/basedata/wellLayer',
    method: 'put',
    data: data
  })
}

// 删除单井小层
export function delWellLayer(id) {
  return request({
    url: '/basedata/wellLayer/' + id,
    method: 'delete'
  })
}
