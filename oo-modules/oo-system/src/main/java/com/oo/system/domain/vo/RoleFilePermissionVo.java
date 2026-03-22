package com.oo.system.domain.vo;

import com.oo.system.api.domain.TreeSelect;
import lombok.Data;

import java.util.List;

@Data
public class RoleFilePermissionVo {
    /**
     */
    private List<TreeSelect> list;

    /**
     */
    private List<Integer> defaultSelectKeys;
}
