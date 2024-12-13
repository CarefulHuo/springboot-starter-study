package cf.carefuohuo.springbootstarterstudy.fsm.build;

import cf.carefuohuo.springbootstarterstudy.fsm.Builder;
import cf.carefuohuo.springbootstarterstudy.fsm.ReturnFlowState;
import cf.carefuohuo.springbootstarterstudy.fsm.action.AuditReturnAction;
import cf.carefuohuo.springbootstarterstudy.fsm.action.CancelReturnAction;
import cf.carefuohuo.springbootstarterstudy.fsm.action.RemedyReturnAction;
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

/**
 * 退款退货单初始化
 */
@Component
public class InitReturnBuilder implements Builder {

    @Autowired
    private RemedyReturnAction remedyReturnAction;

    @Autowired
    private AuditReturnAction auditReturnAction;

    @Autowired
    private CancelReturnAction cancelReturnAction;

    @Override
    public ReturnFlowState returnFlowState() {
        return ReturnFlowState.INIT;
    }

    @Override
    public StateMachine<ReturnFlowState, ReturnEvent> build(ReturnOrder returnOrder, BeanFactory beanFactory) throws Exception {
        StateMachineBuilder.Builder<ReturnFlowState, ReturnEvent> builder = StateMachineBuilder.builder();

        builder.configureConfiguration()
                .withConfiguration()
                .machineId(ReturnFlowState.INIT.getStateId())
                .listener(listener());

        builder.configureStates()
                .withStates()
                .initial(ReturnFlowState.INIT)
                .states(EnumSet.allOf(ReturnFlowState.class));

        builder.configureTransitions()
                .withExternal()
                // 新建--> 修改退单 --> 新建
                .source(ReturnFlowState.INIT)
                .event(ReturnEvent.REMEDY).action(remedyReturnAction)

                .and()
                .withExternal()
                // 新建 --> 已审核
                .source(ReturnFlowState.INIT).target(ReturnFlowState.AUDIT)
                .event(ReturnEvent.AUDIT).action(auditReturnAction)

                .and()
                .withExternal()
                // 新建 --> 已取消
                .source(ReturnFlowState.INIT).target(ReturnFlowState.VOID)
                .event(ReturnEvent.VOID).action(cancelReturnAction);

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
