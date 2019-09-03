package com.tcl.demo.boot.service.user.bo;

import com.google.common.collect.Lists;
import com.tcl.demo.boot.common.tool.CollectionTool;
import com.tcl.demo.boot.dal.user.dataobject.McAccountInfoDataObject;

import java.util.List;
import java.util.stream.Collectors;

public class McAccountTransform {

    public static final McAccountBO do2bo(McAccountInfoDataObject dataObject) {

        if (null == dataObject) {

            return null;
        }

        McAccountBO bo = McAccountBO.builder()
                .identityNo(dataObject.getIdentityNo())
                .accountNo(dataObject.getAccountNo())
                .accountType(dataObject.getAccountType())
                .extendField(dataObject.getExtendField())
                .status(dataObject.getStatus())
                .build();

        return bo;
    }

    public static final List<McAccountBO> do2bo(List<McAccountInfoDataObject> dataObjects) {

        if (CollectionTool.isEmpty(dataObjects)) {

            return Lists.newArrayList();
        }

        return dataObjects.stream().filter(t0 -> null != t0).map(t1 -> do2bo(t1)).collect(Collectors.toList());

    }
}
