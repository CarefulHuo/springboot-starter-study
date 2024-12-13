package cf.carefuohuo.springbootstarterstudy.fsm;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * 退货流程状态
 * Created by jinwei on 20/4/2017.
 */
public enum ReturnFlowState {

    INIT("INIT", "创建退单"),

    AUDIT("AUDIT", "已审核"),

    DELIVERED("DELIVERED", "已发退货"),

    RECEIVED("RECEIVED", "已收到退货"),

    REFUNDED("REFUNDED", "已退款"),

    COMPLETED("COMPLETED", "已完成"),

    REJECT_REFUND("REJECT_REFUND", "拒绝退款"),

    REJECT_RECEIVE("REJECT_RECEIVE", "拒绝收货"),

    VOID("VOID", "已作废"),

    REFUND_FAILED("REFUND_FAILED", "退款失败");

    private String stateId;

    private String desc;

    ReturnFlowState(String stateId, String desc) {
        this.stateId = stateId;
        this.desc = desc;
    }

    protected static final Map<String, ReturnFlowState> map = new HashMap<>();

    static {
        Arrays.stream(ReturnFlowState.values()).forEach(
            t -> map.put(t.getStateId(), t)
        );
    }

    public static ReturnFlowState forValue(String stateId){
        return map.get(stateId);
    }

    public String getStateId() {
        return stateId;
    }

    public String getDesc() {
        return desc;
    }

    public static Map<String, ReturnFlowState> getMap() {
        return map;
    }
}
