package cf.carefuohuo.springbootstarterstudy.fsm;

import cf.carefuohuo.springbootstarterstudy.fsm.event.ReturnEvent;
import cf.carefuohuo.springbootstarterstudy.fsm.params.ReturnOrder;
import cf.carefuohuo.springbootstarterstudy.fsm.params.ReturnStateRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.statemachine.StateMachine;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class ReturnFSMService {

    @Autowired
    private ReturnBuildFactory returnBuildFactory;

    public boolean changeState(ReturnStateRequest request) throws Exception {

        // 1. 获取退单信息
        ReturnOrder returnOrder = new ReturnOrder();
        returnOrder.setReturnFlowState(ReturnFlowState.INIT);

        // 2. 根据订单创建状态机
        StateMachine<ReturnFlowState, ReturnEvent> stateMachine = returnBuildFactory.create(returnOrder);
        if (stateMachine == null) {
            throw new RuntimeException("退单状态不对");
        }
        stateMachine.getExtendedState().getVariables().put(ReturnStateRequest.class, request);

        // 3. 发送当前请求的事件
        boolean isSend = stateMachine.sendEvent(request.getReturnEvent());
        if (!isSend) {
            log.error("退单状态无法从[{}]==》[{}]", returnOrder.getReturnFlowState(), request.getReturnEvent());
            throw new RuntimeException("订单状态已改变，请关闭页面后重试!");
        }

        // 4. 查看执行过程中是否存在异常
        Exception exception = stateMachine.getExtendedState().get(Exception.class, Exception.class);
        if (exception != null) {
            if (exception.getClass().isAssignableFrom(RuntimeException.class)) {
                throw exception;
            } else {
                throw new RuntimeException(exception);
            }
        }
        return isSend;
    }

}
