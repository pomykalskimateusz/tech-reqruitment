package pl.pomykalskimateusz.evolution.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import pl.pomykalskimateusz.evolution.service.user.UserService;

import java.math.BigDecimal;

import static org.junit.Assert.assertEquals;

@SpringBootTest
@RunWith(SpringRunner.class)
public class UserServiceTest {
    @Autowired
    private UserService userService;

    @Test
    public void should_initialize_user_with_appropriate_balance() {
        assertEquals(userService.createUser().getBalance(), BigDecimal.valueOf(5000.00));
    }
}
