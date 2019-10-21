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
public class McScriptDataObject implements Serializable {

    /*
        CREATE TABLE `mc_script_info` (
          `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键编号',
          `script_no` bigint(20) unsigned NOT NULL COMMENT '脚本编号',
          `script_md5` varchar(1024) NOT NULL COMMENT '脚本MD5',
          `script_type` tinyint(8) unsigned NOT NULL COMMENT '脚本类型',
          `script_context` longtext NOT NULL COMMENT '脚本内容',
          `script_status` tinyint(3) unsigned NOT NULL COMMENT '脚本状态',
          `create_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
          `update_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
          PRIMARY KEY (`id`),
          UNIQUE KEY `uk_script_no` (`script_no`) USING BTREE,
          KEY `idx_create_time` (`create_time`) USING BTREE,
          KEY `idx_update_time` (`update_time`) USING BTREE
        ) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='脚本数据'
     */

    private Long id;

    private Long scriptNo;

    private String scriptMd5;

    private Integer scriptType;

    private String scriptContext;

    private Integer scriptStatus;

    private Date createTime;

    private Date updateTime;


}
