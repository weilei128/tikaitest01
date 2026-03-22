import request from '@/utils/request'

// 查询吸水指数测试列表
export function listWellAbsorp(query) {
  return request({
    url: '/basedata/wellAbsorp/list',
    method: 'get',
    params: query
  })
}

// 查询吸水指数测试详细
export function getWellAbsorp(absorpId) {
  return request({
    url: '/basedata/wellAbsorp/' + absorpId,
    method: 'get'
  })
}

// 新增吸水指数测试
export function addWellAbsorp(data) {
  return request({
    url: '/basedata/wellAbsorp',
    method: 'post',
    data: data
  })
}

// 修改吸水指数测试
export function updateWellAbsorp(data) {
  return request({
    url: '/basedata/wellAbsorp',
    method: 'put',
    data: data
  })
}

// 删除吸水指数测试
export function delWellAbsorp(absorpId) {
  return request({
    url: '/basedata/wellAbsorp/' + absorpId,
    method: 'delete'
  })
}
