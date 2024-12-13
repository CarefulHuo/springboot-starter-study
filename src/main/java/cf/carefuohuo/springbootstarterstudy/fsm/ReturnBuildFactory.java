package cf.carefuohuo.springbootstarterstudy.fsm;

import cf.carefuohuo.springbootstarterstudy.fsm.event.ReturnEvent;
import cf.carefuohuo.springbootstarterstudy.fsm.params.ReturnOrder;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.statemachine.StateMachine;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.stream.Collectors;

@Component
public class ReturnBuildFactory implements InitializingBean {

    private Map<ReturnFlowState, Builder> stateBuilderMap = new ConcurrentHashMap<>(4);


    @Autowired
    private List<Builder> builders;

    @Autowired
    private BeanFactory beanFactory;

    public StateMachine<ReturnFlowState, ReturnEvent> create(ReturnOrder returnOrder) {
        ReturnFlowState returnFlowState = returnOrder.getReturnFlowState();
        Builder builder = builders.stream().filter(builderElement -> builderElement.returnFlowState() == returnFlowState).findAny().orElseThrow(() -> new RuntimeException(String.format("[退货]创建状态[%s]失败 => [未知状态]", returnFlowState)));

        StateMachine<ReturnFlowState, ReturnEvent> stateMachineBuildr;
        try {
            stateMachineBuildr = builder.build(returnOrder, beanFactory);
            stateMachineBuildr.start();
        } catch (Exception e) {
            throw new RuntimeException(String.format("[退货]创建状态[%s]失败 => [%s]", returnFlowState, e.getMessage()));
        }

        stateMachineBuildr.getExtendedState().getVariables().put(ReturnOrder.class, returnOrder);
        return stateMachineBuildr;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        stateBuilderMap = builders.stream().collect(Collectors.toMap(Builder::returnFlowState, Function.identity()));
    }
}
