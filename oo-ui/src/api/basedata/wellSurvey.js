import request from '@/utils/request'

// 查询井眼轨迹信息列表
export function listWellSurvey(query) {
  return request({
    url: '/basedata/wellSurvey/list',
    method: 'get',
    params: query
  })
}

// 查询井眼轨迹信息详细
export function getWellSurvey(measureDataId) {
  return request({
    url: '/basedata/wellSurvey/' + measureDataId,
    method: 'get'
  })
}

// 新增井眼轨迹信息
export function addWellSurvey(data) {
  return request({
    url: '/basedata/wellSurvey',
    method: 'post',
    data: data
  })
}

// 修改井眼轨迹信息
export function updateWellSurvey(data) {
  return request({
    url: '/basedata/wellSurvey',
    method: 'put',
    data: data
  })
}

// 删除井眼轨迹信息
export function delWellSurvey(measureDataId) {
  return request({
    url: '/basedata/wellSurvey/' + measureDataId,
    method: 'delete'
  })
}
