package cf.carefuohuo.springbootstarterstudy.fsm;

import cf.carefuohuo.springbootstarterstudy.fsm.event.ReturnEvent;
import cf.carefuohuo.springbootstarterstudy.fsm.params.ReturnOrder;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.statemachine.StateMachine;

public interface Builder {

    /**
     * 当前类标识
     *
     * @return
     */
    ReturnFlowState returnFlowState();

    /**
     * 构建状态机
     *
     * @return
     */
    StateMachine<ReturnFlowState, ReturnEvent> build(ReturnOrder returnOrder, BeanFactory beanFactory) throws Exception;

}
