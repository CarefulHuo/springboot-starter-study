package cf.carefuohuo.springbootstarterstudy.fsm.action;

import cf.carefuohuo.springbootstarterstudy.fsm.ReturnAction;
import cf.carefuohuo.springbootstarterstudy.fsm.ReturnFlowState;
import cf.carefuohuo.springbootstarterstudy.fsm.ReturnStateContext;
import cf.carefuohuo.springbootstarterstudy.fsm.params.ReturnOrder;
import cf.carefuohuo.springbootstarterstudy.fsm.params.ReturnStateRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * 商家接受退货
 * Created by jinwei on 22/4/2017.
 */
@Component
@Slf4j
public class ReceiveReturnAction extends ReturnAction {
    @Override
    protected void doExecute(ReturnOrder returnOrder, ReturnStateRequest returnStateRequest, ReturnStateContext rsc) {
        returnOrder.setReturnFlowState(ReturnFlowState.RECEIVED);
        log.info(ReturnFlowState.RECEIVED.getDesc());
    }
}
