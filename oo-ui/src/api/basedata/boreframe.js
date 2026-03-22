import request from '@/utils/request'

// 查询井身结构设计（套管）列表
export function listBoreframe(query) {
  return request({
    url: '/basedata/boreframe/list',
    method: 'get',
    params: query
  })
}

// 查询井身结构设计（套管）详细
export function getBoreframe(id) {
  return request({
    url: '/basedata/boreframe/' + id,
    method: 'get'
  })
}

// 新增井身结构设计（套管）
export function addBoreframe(data) {
  return request({
    url: '/basedata/boreframe',
    method: 'post',
    data: data
  })
}

// 修改井身结构设计（套管）
export function updateBoreframe(data) {
  return request({
    url: '/basedata/boreframe',
    method: 'put',
    data: data
  })
}

// 删除井身结构设计（套管）
export function delBoreframe(id) {
  return request({
    url: '/basedata/boreframe/' + id,
    method: 'delete'
  })
}
