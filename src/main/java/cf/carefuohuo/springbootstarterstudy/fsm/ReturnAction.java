package cf.carefuohuo.springbootstarterstudy.fsm;

import cf.carefuohuo.springbootstarterstudy.fsm.event.ReturnEvent;
import cf.carefuohuo.springbootstarterstudy.fsm.params.ReturnOrder;
import cf.carefuohuo.springbootstarterstudy.fsm.params.ReturnStateRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.statemachine.StateContext;
import org.springframework.statemachine.action.Action;

/**
 * 事件的抽象父类
 */
public abstract class ReturnAction implements Action<ReturnFlowState, ReturnEvent> {

    private static final Logger logger = LoggerFactory.getLogger(ReturnAction.class);

    @Override
    public void execute(StateContext<ReturnFlowState, ReturnEvent> context) {
        ReturnStateContext returnStateContext = new ReturnStateContext(context);
        try {
            doExecute(returnStateContext.findReturnOrder(), returnStateContext.findReturnRequest(), returnStateContext);
        } catch (Exception e) {
            returnStateContext.put(Exception.class, e);
            logger.error("[退货处理]，从状态:{}，经过事件:{}，到状态:{}，出现异常:{}", context.getSource().getId(), context.getEvent(), context.getTarget().getId(), e.getMessage());
        }
    }

    protected abstract void doExecute(ReturnOrder returnOrder, ReturnStateRequest returnStateRequest, ReturnStateContext rsc);
}
