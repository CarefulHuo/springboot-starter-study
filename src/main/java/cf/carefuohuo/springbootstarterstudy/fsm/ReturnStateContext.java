package cf.carefuohuo.springbootstarterstudy.fsm;

import cf.carefuohuo.springbootstarterstudy.fsm.event.ReturnEvent;
import cf.carefuohuo.springbootstarterstudy.fsm.params.ReturnOrder;
import cf.carefuohuo.springbootstarterstudy.fsm.params.ReturnStateRequest;
import org.springframework.statemachine.StateContext;

/**
 * 退货状态管理上下文
 */
public class ReturnStateContext {

    private StateContext<ReturnFlowState, ReturnEvent> context;

    public ReturnStateContext(StateContext<ReturnFlowState, ReturnEvent> context) {
        this.context = context;
    }

    /**
     * 传递参数
     *
     * @param key
     * @param value
     * @return
     */
    public ReturnStateContext put(Object key, Object value) {
        context.getExtendedState().getVariables().put(key, value);
        return this;
    }

    public ReturnStateRequest findReturnRequest() {
        return context.getExtendedState().get(ReturnStateRequest.class, ReturnStateRequest.class);
    }

    public ReturnOrder findReturnOrder() {
        return context.getExtendedState().get(ReturnOrder.class, ReturnOrder.class);
    }
}
