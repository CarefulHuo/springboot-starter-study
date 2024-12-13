package cf.carefuohuo.springbootstarterstudy.fsm.build;

import cf.carefuohuo.springbootstarterstudy.fsm.Builder;
import cf.carefuohuo.springbootstarterstudy.fsm.ReturnFlowState;
import cf.carefuohuo.springbootstarterstudy.fsm.action.CloseReturnAction;
import cf.carefuohuo.springbootstarterstudy.fsm.action.RefundReturnAction;
import cf.carefuohuo.springbootstarterstudy.fsm.event.ReturnEvent;
import cf.carefuohuo.springbootstarterstudy.fsm.params.ReturnOrder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.statemachine.StateMachine;
import org.springframework.statemachine.config.StateMachineBuilder;
import org.springframework.statemachine.listener.StateMachineListener;
import org.springframework.statemachine.listener.StateMachineListenerAdapter;
import org.springframework.statemachine.state.State;
import org.springframework.stereotype.Component;

import java.util.EnumSet;

@Component
@Slf4j
public class CloseReturnBuilder implements Builder {

    @Autowired
    private CloseReturnAction closeReturnAction;

    @Autowired
    private RefundReturnAction refundReturnAction;

    @Override
    public ReturnFlowState returnFlowState() {
        return ReturnFlowState.REFUND_FAILED;
    }

    @Override
    public StateMachine<ReturnFlowState, ReturnEvent> build(ReturnOrder returnOrder, BeanFactory beanFactory) throws Exception {
        StateMachineBuilder.Builder<ReturnFlowState, ReturnEvent> builder = StateMachineBuilder.builder();

        // 配置构造器属性
        builder.configureConfiguration()
                .withConfiguration()
                .machineId(ReturnFlowState.REFUND_FAILED.getStateId())
                .beanFactory(beanFactory)
                .listener(listener());

        // 配置状态
        builder.configureStates()
                .withStates()
                .initial(ReturnFlowState.REFUND_FAILED)
                .states(EnumSet.allOf(ReturnFlowState.class));

        // 配置流转
        builder.configureTransitions()
                .withExternal()
                // 退款失败--》已完成
                .source(ReturnFlowState.REFUND_FAILED).target(ReturnFlowState.COMPLETED)
                // 执行事件类型为关闭退款的事件
                .event(ReturnEvent.CLOSE_REFUND).action(closeReturnAction)

                .and()
                .withExternal()
                // 退款失败--》已完成
                .source(ReturnFlowState.REFUND_FAILED).target(ReturnFlowState.COMPLETED)
                // 执行事件类型为退款的事件
                .event(ReturnEvent.REFUND).action(refundReturnAction);


        return builder.build();
    }

    public StateMachineListener<ReturnFlowState, ReturnEvent> listener(){
        return new StateMachineListenerAdapter<>() {
            @Override
            public void stateChanged(State<ReturnFlowState, ReturnEvent> from, State<ReturnFlowState, ReturnEvent> to) {
                System.out.println("state change to " + to.getId());
            }
        };
    }
}
