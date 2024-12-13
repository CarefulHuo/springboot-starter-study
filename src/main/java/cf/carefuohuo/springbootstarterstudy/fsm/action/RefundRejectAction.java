package cf.carefuohuo.springbootstarterstudy.fsm.action;

import cf.carefuohuo.springbootstarterstudy.fsm.ReturnAction;
import cf.carefuohuo.springbootstarterstudy.fsm.ReturnFlowState;
import cf.carefuohuo.springbootstarterstudy.fsm.ReturnStateContext;
import cf.carefuohuo.springbootstarterstudy.fsm.params.ReturnOrder;
import cf.carefuohuo.springbootstarterstudy.fsm.params.ReturnStateRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * 拒绝退款
 * Created by jinwei on 2/5/2017.
 */
@Component
@Slf4j
public class RefundRejectAction extends ReturnAction {

    @Override
    protected void doExecute(ReturnOrder returnOrder, ReturnStateRequest returnStateRequest, ReturnStateContext rsc) {
        returnOrder.setReturnFlowState(ReturnFlowState.REJECT_REFUND);
        log.info("");
    }
}
