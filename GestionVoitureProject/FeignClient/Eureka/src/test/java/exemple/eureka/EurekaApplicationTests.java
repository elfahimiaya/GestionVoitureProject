package exemple.eureka;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest(classes = EurekaApplication.class)
class EurekaApplicationTests {

    @Autowired
    private ApplicationContext applicationContext;

    @Test
    void contextLoads() {
        assertNotNull(applicationContext, "Application context should not be null");
    }

    @Test
    void eurekaServerIsEnabled() {
        boolean isEurekaServerEnabled = applicationContext.containsBean("eurekaAutoServiceRegistration");
        assertTrue(isEurekaServerEnabled, "Eureka server should be enabled and properly configured");
    }
}
