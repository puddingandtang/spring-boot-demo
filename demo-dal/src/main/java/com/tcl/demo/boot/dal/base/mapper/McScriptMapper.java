package com.tcl.demo.boot.dal.base.mapper;

import com.tcl.demo.boot.dal.base.dataobject.McScriptDataObject;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface McScriptMapper {

    /**
     * 新增
     *
     * @param scriptData
     * @return
     */
    int addScript(McScriptDataObject scriptData);

    /**
     * 更新
     *
     * @param scriptData
     * @return
     */
    int updateScript(McScriptDataObject scriptData);

    /**
     * 查询
     *
     * @param scriptNos
     * @return
     */
    List<McScriptDataObject> queryForScriptNos(@Param("scriptNos") List<Long> scriptNos);

    /**
     * 查询
     *
     * @param scriptNos
     * @param scriptType
     * @return
     */
    List<McScriptDataObject> queryForScriptType(@Param("scriptNos") List<Long> scriptNos, @Param("scriptType") Integer scriptType);

}
