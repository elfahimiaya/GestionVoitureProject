package elfahimi.eurekagateway;

import exemple.eurekagateway.EurekaGatewayApplication;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.TestPropertySource;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;


@SpringBootTest(classes = EurekaGatewayApplication.class)
@TestPropertySource(locations = "classpath:application.yml")
class EurekaGatewayApplicationTests {

    @Autowired
    private ApplicationContext applicationContext;


    @Test
    void contextLoads() {

        assertNotNull(applicationContext, "Application context should not be null");
    }

    @Test
    void corsConfigurationIsLoaded() {

        boolean isCorsConfigPresent = applicationContext.containsBean("globalCorsProperties");
        assertTrue(isCorsConfigPresent, "CORS configuration should be present in the application context");
    }



}