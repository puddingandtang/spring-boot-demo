package com.tcl.demo.boot.service.user.bo;

import com.google.common.collect.Lists;
import com.tcl.demo.boot.common.tool.CollectionTool;
import com.tcl.demo.boot.dal.user.dataobject.McIdentityAccountConfigDataObject;

import java.util.List;
import java.util.stream.Collectors;

public class McIdentityAccountConfigTransform {


    public static McIdentityAccountConfigBO do2bo(McIdentityAccountConfigDataObject dataObject) {

        if (null == dataObject) {

            return null;
        }

        McIdentityAccountConfigBO configBO = McIdentityAccountConfigBO.builder()
                .userType(dataObject.getUserType())
                .accountType(dataObject.getAccountType())
                .defaultInit(dataObject.getDefaultInit())
                .status(dataObject.getStatus())
                .build();

        return configBO;

    }

    public static List<McIdentityAccountConfigBO> do2bo(List<McIdentityAccountConfigDataObject> dataObjects) {

        if (CollectionTool.isEmpty(dataObjects)) {

            return Lists.newArrayList();
        }

        return dataObjects.stream()
                .filter(t0 -> null != t0)
                .map(t1 -> do2bo(t1))
                .collect(Collectors.toList());

    }
}
