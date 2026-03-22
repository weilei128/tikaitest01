import request from '@/utils/request'

// 查询生产井与小层关系列表
export function listWellSectionLayer(query) {
  return request({
    url: '/basedata/wellSectionLayer/list',
    method: 'get',
    params: query
  })
}

// 查询生产井与小层关系详细
export function getWellSectionLayer(id) {
  return request({
    url: '/basedata/wellSectionLayer/' + id,
    method: 'get'
  })
}

// 新增生产井与小层关系
export function addWellSectionLayer(data) {
  return request({
    url: '/basedata/wellSectionLayer',
    method: 'post',
    data: data
  })
}

// 修改生产井与小层关系
export function updateWellSectionLayer(data) {
  return request({
    url: '/basedata/wellSectionLayer',
    method: 'put',
    data: data
  })
}

// 删除生产井与小层关系
export function delWellSectionLayer(id) {
  return request({
    url: '/basedata/wellSectionLayer/' + id,
    method: 'delete'
  })
}
