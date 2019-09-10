package com.tcl.demo.boot.service.common.command;

import com.tcl.demo.boot.common.base.ErrorCodes;
import com.tcl.demo.boot.common.base.ErrorLv;
import com.tcl.demo.boot.common.exception.BizRuntimeException;
import com.tcl.demo.boot.common.exception.BizUnNormalException;
import com.tcl.demo.boot.common.preconditions.PreconditionsAssert;
import com.tcl.demo.boot.service.common.processor.BizProcessCondition;
import com.tcl.demo.boot.service.common.processor.BizProcessContent;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public abstract class BaseCommand<Condition extends BizProcessCondition, Content extends BizProcessContent> {

    /**
     * 核心
     *
     * @param condition
     * @param content
     */
    public void command(Condition condition, Content content) throws BizRuntimeException {

        PreconditionsAssert.assertNotNull(condition, ErrorCodes.COMMAND_PARAM_ILLEGALITY, "Condition");
        PreconditionsAssert.assertNotNull(condition, ErrorCodes.COMMAND_PARAM_ILLEGALITY, "Content");
        PreconditionsAssert.assertNotNull(condition, ErrorCodes.COMMAND_PARAM_ILLEGALITY, "CommandData");

        boolean interrupt;
        try {

            // 执行前置处理，可以初始化一些数据等操作
            processCommandBefore(condition, content);

            interrupt = content.isInterrupt();
            if (interrupt) {

                log.info("执行器-Before-{},检测到中断标志，后续不进行处理", this.getClass());
                return;
            }

            // 执行核心
            processCommandDoing(condition, content);
            log.info("执行器-Doing-{},执行处理完成", this.getClass());

        } catch (Exception e) {

            log.info("执行器-" + this.getClass().toString() + "-执行流程异常:", e);

            if (e instanceof BizRuntimeException) {

                BizRuntimeException bizRuntimeException = (BizRuntimeException) e;
                throw bizRuntimeException;

            } else {
                throw new BizUnNormalException(ErrorCodes.CODE_ERROR, ErrorLv.ERROR);
            }

        } finally {
            try {
                // 执行后置
                processCommandFinally(condition, content);
            } catch (Exception e) {
                log.error("执行器-Finally-" + this.getClass().toString() + ",执行异常:", e);
            }

        }


    }


    /**
     * 子类实现：具体命令格式
     *
     * @param condition
     * @param content
     */
    protected abstract void processCommandBefore(Condition condition, Content content);


    /**
     * 子类实现：具体命令格式
     *
     * @param condition
     * @param content
     */
    protected abstract void processCommandDoing(Condition condition, Content content);


    /**
     * 子类实现：具体命令格式
     *
     * @param condition
     * @param content
     */
    protected abstract void processCommandFinally(Condition condition, Content content);

}
