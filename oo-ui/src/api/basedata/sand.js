import request from '@/utils/request'

// 查询砂层列表
export function listSand(query) {
  return request({
    url: '/basedata/sand/list',
    method: 'get',
    params: query
  })
}

// 查询砂层详细
export function getSand(id) {
  return request({
    url: '/basedata/sand/' + id,
    method: 'get'
  })
}

// 新增砂层
export function addSand(data) {
  return request({
    url: '/basedata/sand',
    method: 'post',
    data: data
  })
}

// 修改砂层
export function updateSand(data) {
  return request({
    url: '/basedata/sand',
    method: 'put',
    data: data
  })
}

// 删除砂层
export function delSand(id) {
  return request({
    url: '/basedata/sand/' + id,
    method: 'delete'
  })
}
