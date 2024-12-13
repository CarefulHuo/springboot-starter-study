package cf.carefuohuo.springbootstarterstudy;

import cf.carefuohuo.springbootstarterstudy.fsm.ReturnFSMService;
import cf.carefuohuo.springbootstarterstudy.fsm.event.ReturnEvent;
import cf.carefuohuo.springbootstarterstudy.fsm.params.ReturnStateRequest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class SpringbootStarterStudyApplicationTests {

    @Autowired
    private ReturnFSMService returnFSMService;

    @Test
    void contextLoads() {

        try {
            //修改退单状态
            ReturnStateRequest request = ReturnStateRequest
                    .builder()
                    .rid("R202412091203")
                    .operatorName("operator-001")
                    .returnEvent(ReturnEvent.VOID)
                    .data("退货地址如下所示")
                    .build();
            returnFSMService.changeState(request);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
