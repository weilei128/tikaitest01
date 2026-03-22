import request from '@/utils/request'

// 查询生产井段列表
export function listWellSection(query) {
  return request({
    url: '/basedata/wellSection/list',
    method: 'get',
    params: query
  })
}

// 查询生产井段详细
export function getWellSection(id) {
  return request({
    url: '/basedata/wellSection/' + id,
    method: 'get'
  })
}

// 新增生产井段
export function addWellSection(data) {
  return request({
    url: '/basedata/wellSection',
    method: 'post',
    data: data
  })
}

// 修改生产井段
export function updateWellSection(data) {
  return request({
    url: '/basedata/wellSection',
    method: 'put',
    data: data
  })
}

// 删除生产井段
export function delWellSection(id) {
  return request({
    url: '/basedata/wellSection/' + id,
    method: 'delete'
  })
}
