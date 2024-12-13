package cf.carefuohuo.springbootstarterstudy;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.statemachine.config.EnableWithStateMachine;

@SpringBootApplication
@EnableWithStateMachine
public class SpringbootStarterStudyApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootStarterStudyApplication.class, args);
    }

}
