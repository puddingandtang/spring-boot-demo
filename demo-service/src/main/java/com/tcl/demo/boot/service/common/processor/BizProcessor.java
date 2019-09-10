package com.tcl.demo.boot.service.common.processor;


import com.google.common.base.Optional;
import com.tcl.demo.boot.common.base.ErrorCode;
import com.tcl.demo.boot.common.base.ErrorCodes;
import com.tcl.demo.boot.common.exception.BizNormalException;
import com.tcl.demo.boot.common.exception.BizUnNormalException;
import lombok.extern.slf4j.Slf4j;

import java.text.MessageFormat;

@Slf4j
public abstract class BizProcessor<Condition extends BizProcessCondition, Content extends BizProcessContent> {

    public Boolean process(Condition condition, Content content) {

        try {

            // 必要参数校验
            this.checkNecessary(content);

            if (content.isInterrupt()) {
                log.info("追溯编号-[{}],RuleVerifyProcessor-[{}]-不执行,中断标志位True", content.getOutTraceNo(), this.getClass());
                return false;
            }

            boolean verifyResult = this.verifyProcess(condition, content);

            // 设置中断位，中断后续流程
            if (!verifyResult) {
                content.setInterrupt(true);
                log.info("追溯编号-[{}],RuleVerifyProcessor-[{}]-设置中断标志", content.getOutTraceNo(), this.getClass());
                if (null == content.getCode()) {
                    content.setCode(buildErrorMsg(ErrorCodes.RULE_COMMON_ERROR));
                }
            }

            return verifyResult;

        } catch (Exception e) {

            // 设置中断标志
            content.setInterrupt(true);
            // 设置错误
            content.setCode(buildErrorMsg(ErrorCodes.RULE_COMMON_ERROR));

            if (e instanceof BizNormalException) {

                log.info("追溯编号-[{}],RuleVerifyProcessor-[{}]-执行BizNormalException异常:[{}]", content.getOutTraceNo(), this.getClass(), e.getMessage());

            } else if (e instanceof BizUnNormalException) {

                log.error("追溯编号-[" + content.getOutTraceNo() + "],RuleVerifyProcessor-[" + this.getClass() + "]-执行非BizNormalException异常:[{}]", e.getMessage());
            }

            return false;
        }
    }

    /**
     * 必要参数校验
     *
     * @param content
     */
    private void checkNecessary(Content content) {

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

    protected abstract boolean verifyProcess(Condition condition, Content content);

}
