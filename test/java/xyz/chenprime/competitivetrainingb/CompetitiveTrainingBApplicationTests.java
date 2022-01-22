package xyz.chenprime.competitivetrainingb;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import xyz.chenprime.pojo.User;
import xyz.chenprime.service.UserService;

@SpringBootTest
class CompetitiveTrainingBApplicationTests {

    @Autowired
    UserService service;

    @Test
    void contextLoads() {
    }

}
