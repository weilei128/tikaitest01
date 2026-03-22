import request from '@/utils/request'

// 查询套管库列表
export function listCasingpipe(query) {
  return request({
    url: '/basedata/casingpipe/list',
    method: 'get',
    params: query
  })
}

// 查询套管库详细
export function getCasingpipe(id) {
  return request({
    url: '/basedata/casingpipe/' + id,
    method: 'get'
  })
}

// 新增套管库
export function addCasingpipe(data) {
  return request({
    url: '/basedata/casingpipe',
    method: 'post',
    data: data
  })
}

// 修改套管库
export function updateCasingpipe(data) {
  return request({
    url: '/basedata/casingpipe',
    method: 'put',
    data: data
  })
}

// 删除套管库
export function delCasingpipe(id) {
  return request({
    url: '/basedata/casingpipe/' + id,
    method: 'delete'
  })
}
