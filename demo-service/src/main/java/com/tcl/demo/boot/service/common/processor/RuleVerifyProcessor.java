package com.tcl.demo.boot.service.common.processor;


import com.google.common.base.Optional;
import com.tcl.demo.boot.common.base.ErrorCode;
import com.tcl.demo.boot.common.base.ErrorCodes;
import com.tcl.demo.boot.common.preconditions.PreconditionsAssert;
import com.tcl.demo.boot.common.tool.CollectionTool;
import lombok.extern.slf4j.Slf4j;

import java.text.MessageFormat;

@Slf4j
public abstract class RuleVerifyProcessor implements BizProcessor<RuleVerifyCondition, RuleVerifyContent> {

    @Override
    public void process(RuleVerifyCondition condition, RuleVerifyContent content) {

        try {

            // 必要参数校验
            this.checkNecessary(content);

            if (content.isInterrupt()) {
                log.info("RuleVerifyProcessor-[{}]-不执行,中断标志位True", this.getClass());
                return;
            }

            boolean verifyResult = this.verifyProcess(condition, content);

            // 设置中断位，中断后续流程
            if (!verifyResult) {
                content.setInterrupt(true);
                log.info("RuleVerifyProcessor-[{}]-设置中断标志", this.getClass());
                if (null == content.getCode()) {
                    content.setCode(buildErrorMsg(ErrorCodes.RULE_COMMON_ERROR));
                }
            }

            return;

        } catch (Exception e) {

            log.info("RuleVerifyProcessor-[{}]-执行异常:[{}]", this.getClass(), e.getMessage());

            // 设置中断标志
            content.setInterrupt(true);
            // 设置错误
            content.setCode(buildErrorMsg(ErrorCodes.RULE_COMMON_ERROR));

            throw e;
        }
    }

    /**
     * 必要参数校验
     *
     * @param content
     */
    private void checkNecessary(RuleVerifyContent content) {

        PreconditionsAssert.assertNotNull(content, ErrorCodes.RULE_PARAM_CHECK_ERROR, "RuleVerifyContent");
        PreconditionsAssert.assertTrue(!CollectionTool.isEmpty(content.getRules()), ErrorCodes.RULE_PARAM_CHECK_ERROR, "RuleVerifyContent#rules");
    }

    /**
     * 构建错误信息
     *
     * @param errorCode
     * @param objects
     * @return
     */
    protected ErrorCode buildErrorMsg(ErrorCode errorCode, Object... objects) {

        errorCode = Optional.fromNullable(errorCode).or(ErrorCodes.RULE_COMMON_ERROR);

        ErrorCode cur = new ErrorCode(errorCode.getCode(), MessageFormat.format(errorCode.getMsg(), objects));

        return cur;

    }

    protected abstract boolean verifyProcess(RuleVerifyCondition condition, RuleVerifyContent content);

}
