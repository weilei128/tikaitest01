import request from '@/utils/request'

// 查询射孔井段信息列表
export function listWellPerforation(query) {
  return request({
    url: '/basedata/wellPerforation/list',
    method: 'get',
    params: query
  })
}

// 查询射孔井段信息详细
export function getWellPerforation(id) {
  return request({
    url: '/basedata/wellPerforation/' + id,
    method: 'get'
  })
}

// 新增射孔井段信息
export function addWellPerforation(data) {
  return request({
    url: '/basedata/wellPerforation',
    method: 'post',
    data: data
  })
}

// 修改射孔井段信息
export function updateWellPerforation(data) {
  return request({
    url: '/basedata/wellPerforation',
    method: 'put',
    data: data
  })
}

// 删除射孔井段信息
export function delWellPerforation(id) {
  return request({
    url: '/basedata/wellPerforation/' + id,
    method: 'delete'
  })
}
