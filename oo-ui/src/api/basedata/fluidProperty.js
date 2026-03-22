import request from '@/utils/request'

// 查询流体性质（油）列表
export function listFluidProperty(query) {
  return request({
    url: '/basedata/fluidProperty/list',
    method: 'get',
    params: query
  })
}

// 查询流体性质（油）详细
export function getFluidProperty(id) {
  return request({
    url: '/basedata/fluidProperty/' + id,
    method: 'get'
  })
}

// 新增流体性质（油）
export function addFluidProperty(data) {
  return request({
    url: '/basedata/fluidProperty',
    method: 'post',
    data: data
  })
}

// 修改流体性质（油）
export function updateFluidProperty(data) {
  return request({
    url: '/basedata/fluidProperty',
    method: 'put',
    data: data
  })
}

// 删除流体性质（油）
export function delFluidProperty(id) {
  return request({
    url: '/basedata/fluidProperty/' + id,
    method: 'delete'
  })
}
