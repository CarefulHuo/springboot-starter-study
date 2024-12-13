package cf.carefuohuo.springbootstarterstudy.fsm.action;

import cf.carefuohuo.springbootstarterstudy.fsm.ReturnAction;
import cf.carefuohuo.springbootstarterstudy.fsm.ReturnFlowState;
import cf.carefuohuo.springbootstarterstudy.fsm.ReturnStateContext;
import cf.carefuohuo.springbootstarterstudy.fsm.params.ReturnOrder;
import cf.carefuohuo.springbootstarterstudy.fsm.params.ReturnStateRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

/**
 * 审核退货单
 */
@Component
public class AuditReturnAction extends ReturnAction {

    private static final Logger logger = LoggerFactory.getLogger(AuditReturnAction.class);

    @Override
    protected void doExecute(ReturnOrder returnOrder, ReturnStateRequest returnStateRequest, ReturnStateContext rsc) {
        if (returnOrder.getReturnFlowState() == ReturnFlowState.INIT
                || returnOrder.getReturnFlowState() == ReturnFlowState.COMPLETED){
            returnOrder.setReturnFlowState(ReturnFlowState.AUDIT);
            returnOrder.setAuditTime(LocalDateTime.now());
            logger.info(ReturnFlowState.AUDIT.getDesc());
        }else{
            throw new IllegalArgumentException("ReturnOrder state is illegal");
        }
    }
}
