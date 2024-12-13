package cf.carefuohuo.springbootstarterstudy.fsm.build;

import cf.carefuohuo.springbootstarterstudy.fsm.Builder;
import cf.carefuohuo.springbootstarterstudy.fsm.ReturnFlowState;
import cf.carefuohuo.springbootstarterstudy.fsm.action.DeliverReturnAction;
import cf.carefuohuo.springbootstarterstudy.fsm.action.RefundFailedAction;
import cf.carefuohuo.springbootstarterstudy.fsm.action.RefundRejectAction;
import cf.carefuohuo.springbootstarterstudy.fsm.action.RefundReturnAction;
import cf.carefuohuo.springbootstarterstudy.fsm.event.ReturnEvent;
import cf.carefuohuo.springbootstarterstudy.fsm.params.ReturnOrder;
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
public class AuditReturnBuilder implements Builder {

    @Autowired
    private DeliverReturnAction deliverReturnAction;

    @Autowired
    private RefundReturnAction refundReturnAction;

    @Autowired
    private RefundRejectAction refundRejectAction;

    @Autowired
    private RefundFailedAction refundFailedAction;

    @Override
    public ReturnFlowState returnFlowState() {
        return ReturnFlowState.AUDIT;
    }

    @Override
    public StateMachine<ReturnFlowState, ReturnEvent> build(ReturnOrder returnOrder, BeanFactory beanFactory) throws Exception {
        StateMachineBuilder.Builder<ReturnFlowState, ReturnEvent> builder = StateMachineBuilder.builder();

        // 配置构造器配置
        builder.configureConfiguration().withConfiguration()
                .machineId(ReturnFlowState.AUDIT.getStateId())
                .beanFactory(beanFactory)
                .listener(listener());

        // 配置初始状态
        builder.configureStates()
                .withStates()
                .initial(ReturnFlowState.AUDIT)
                .states(EnumSet.allOf(ReturnFlowState.class));

        // 配置
        builder.configureTransitions()
                .withExternal()
                .source(ReturnFlowState.AUDIT).target(ReturnFlowState.DELIVERED)
                .event(ReturnEvent.DELIVER)
                .action(deliverReturnAction)
                .and()

                .withExternal()
                .source(ReturnFlowState.AUDIT).target(ReturnFlowState.COMPLETED)
                .event(ReturnEvent.REFUND)
                .action(refundReturnAction)
                .and()

                .withExternal()
                .source(ReturnFlowState.AUDIT).target(ReturnFlowState.REJECT_REFUND)
                .event(ReturnEvent.REJECT_REFUND)
                .action(refundRejectAction)
                .and()

                .withExternal()
                .source(ReturnFlowState.AUDIT).target(ReturnFlowState.REFUND_FAILED)
                .event(ReturnEvent.REFUND_FAILED)
                .action(refundFailedAction);


        return builder.build();
    }

    private StateMachineListener<ReturnFlowState, ReturnEvent> listener() {
        return new StateMachineListenerAdapter<>() {
            @Override
            public void stateChanged(State<ReturnFlowState, ReturnEvent> from, State<ReturnFlowState, ReturnEvent> to) {
                System.out.println("state change to " + to.getId());
            }
        };
    }
}
