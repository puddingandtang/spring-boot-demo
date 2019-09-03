package com.tcl.demo.boot.service.user.bo;

import com.google.common.collect.Lists;
import com.tcl.demo.boot.common.tool.CollectionTool;
import com.tcl.demo.boot.dal.user.dataobject.McIdentityInfoDataObject;

import java.util.List;
import java.util.stream.Collectors;

public class McIdentityTransform {

    public static final McIdentityInfoDataObject bo2do(McIdentityBO mcIdentityBO){

        if(null == mcIdentityBO){

            return null;
        }

        McIdentityInfoDataObject dataObject = new McIdentityInfoDataObject();
        dataObject.setUserNo(mcIdentityBO.getUserNo());
        dataObject.setUserType(mcIdentityBO.getUserType());
        dataObject.setIdentityNo(mcIdentityBO.getIdentityNo());
        dataObject.setStatus(mcIdentityBO.getStatus());
        return dataObject;

    }

    public static final McIdentityBO do2bo(McIdentityInfoDataObject dataObject) {

        if (null == dataObject) {
            return null;
        }

        McIdentityBO bo = McIdentityBO.builder()
                .identityNo(dataObject.getIdentityNo())
                .userNo(dataObject.getUserNo())
                .userType(dataObject.getUserType())
                .status(dataObject.getStatus())
                .build();

        return bo;
    }

    public static List<McIdentityBO> do2bo(List<McIdentityInfoDataObject> dataObjects) {

        if (CollectionTool.isEmpty(dataObjects)) {

            return Lists.newArrayList();
        }

        return dataObjects.stream().filter(t0 -> null != t0).map(t1 -> do2bo(t1)).collect(Collectors.toList());
    }
}
