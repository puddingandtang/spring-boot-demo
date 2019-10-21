package com.tcl.demo.boot.dal.base.dataobject;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class McFlowDataObject implements Serializable {

    /*
    CREATE TABLE `mc_flow_info` (
      `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键编号',
      `flow_no` bigint(20) unsigned NOT NULL COMMENT '流程编号',
      `biz_class_one` tinyint(8) unsigned NOT NULL COMMENT '业务-一级类型',
      `biz_class_two` tinyint(8) unsigned NOT NULL COMMENT '业务-二级类型',
      `biz_title` varchar(128) NOT NULL COMMENT '业务标题',
      `biz_desc` longtext NOT NULL COMMENT '业务描述',
      `flow_status` tinyint(8) unsigned NOT NULL COMMENT '流程状态',
      `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
      `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
      PRIMARY KEY (`id`),
      UNIQUE KEY `uk_flow_no` (`flow_no`) USING BTREE,
      KEY `idx_biz` (`biz_class_one`,`biz_class_two`,`flow_no`) USING BTREE,
      KEY `idx_create_time` (`create_time`) USING BTREE,
      KEY `idx_update_time` (`update_time`) USING BTREE
    ) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='脚本流程-组装脚本'
     */


    private Long id;

    private Long flowNo;

    private Integer bizClassOne;

    private Integer bizClassTwo;

    private String bizDesc;

    private Integer flowStatus;

    private Date createTime;

    private Date updateTime;
}
