package cf.carefuohuo.springbootstarterstudy.fsm.params;

import cf.carefuohuo.springbootstarterstudy.fsm.event.ReturnEvent;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 状态机请求参数
 */
@Getter
@Setter
@ToString
@Builder
public class ReturnStateRequest {

    /**
     * 退单号
     */
    private String rid;

    /**
     * 操作人
     */
    private String operatorName;

    /**
     * 逆向事件
     */
    private ReturnEvent returnEvent;

    /**
     * 参数
     */
    private Object data;
}
