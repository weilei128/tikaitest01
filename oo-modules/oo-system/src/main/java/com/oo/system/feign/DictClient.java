package com.oo.system.feign;

import com.oo.common.core.domain.R;
import com.oo.common.core.utils.StringUtils;
import com.oo.system.api.domain.SysDictData;
import com.oo.system.api.feign.RemoteDictService;
import com.oo.system.service.ISysDictDataService;
import com.oo.system.service.ISysDictTypeService;
import com.oo.system.service.ISysTranslateService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


/**
 * 字典服务Feign实现类
 *
 * @author
 */
@ApiIgnore
@RestController
@AllArgsConstructor
public class DictClient implements RemoteDictService {

    private final ISysDictTypeService dictTypeService;
    private final ISysDictDataService dictDataService;
    private final ISysTranslateService sysTranslateService;

    @Override
    public R<String> getDictLabel(String dictType, String dictValue) {
        List<SysDictData> data = dictTypeService.selectDictDataByType(dictType);
        if (StringUtils.isNull(data))
        {
            data = new ArrayList<>();
        }
        Optional<String> label = data.stream().filter(a -> a.getDictValue().equalsIgnoreCase(dictValue))
                .map(SysDictData::getDictLabel).findFirst();
        return R.ok(label.orElse(""), "操作成功");
    }

    @Override
    public R<String> getDictValue(String dictType, String dictLabel) {
        List<SysDictData> data = dictTypeService.selectDictDataByType(dictType);
        if (StringUtils.isNull(data))
        {
            data = new ArrayList<>();
        }
        Optional<String> label = data.stream().filter(a -> a.getDictLabel().equalsIgnoreCase(dictLabel))
                .map(SysDictData::getDictValue).findFirst();
        return R.ok(label.orElse(""), "操作成功");
    }

    @Override
    public R<Integer> insertDictData(@RequestBody SysDictData dictData) {
        return R.ok(dictDataService.insertDictData(dictData));
    }

    @Override
    public R loadingDictCache() {
        dictTypeService.loadingDictCache();
        return R.ok();
    }

    @Override
    public R loadingTranslateCache() {
        sysTranslateService.loadingTranslateCache();
        return R.ok();
    }
}
