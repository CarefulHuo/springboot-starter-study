package cf.carefuohuo.springbootstarterstudy.fsm.build;

import cf.carefuohuo.springbootstarterstudy.fsm.Builder;
import cf.carefuohuo.springbootstarterstudy.fsm.ReturnFlowState;
import cf.carefuohuo.springbootstarterstudy.fsm.action.AuditReturnAction;
import cf.carefuohuo.springbootstarterstudy.fsm.action.ReceiveReturnAction;
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
public class CompletedReturnOrderBuilder implements Builder {

    @Autowired
    private AuditReturnAction auditReturnAction;

    @Autowired
    private ReceiveReturnAction receiveReturnAction;

    @Override
    public ReturnFlowState returnFlowState() {
        return ReturnFlowState.COMPLETED;
    }

    @Override
    public StateMachine<ReturnFlowState, ReturnEvent> build(ReturnOrder returnOrder, BeanFactory beanFactory) throws Exception {
        StateMachineBuilder.Builder<ReturnFlowState, ReturnEvent> builder = StateMachineBuilder.builder();

        builder.configureConfiguration()
                .withConfiguration()
                .machineId(ReturnFlowState.COMPLETED.getStateId())
                .listener(listener());

        builder.configureStates()
                .withStates()
                .initial(ReturnFlowState.COMPLETED)
                .states(EnumSet.allOf(ReturnFlowState.class));

        builder.configureTransitions()
                .withExternal()
                .source(ReturnFlowState.COMPLETED).target(ReturnFlowState.AUDIT)
                .event(ReturnEvent.REVERSE_REFUND).action(auditReturnAction)

                .and()
                .withExternal()
                .source(ReturnFlowState.COMPLETED).target(ReturnFlowState.RECEIVED)
                .event(ReturnEvent.REVERSE_RETURN).action(receiveReturnAction);

        return builder.build();
    }

    public StateMachineListener<ReturnFlowState, ReturnEvent> listener() {
        return new StateMachineListenerAdapter<>() {
            @Override
            public void stateChanged(State<ReturnFlowState, ReturnEvent> from, State<ReturnFlowState, ReturnEvent> to) {
                System.out.println("state change to " + to.getId());
            }
        };
    }
}
