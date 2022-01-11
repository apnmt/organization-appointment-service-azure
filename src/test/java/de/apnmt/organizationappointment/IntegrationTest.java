package de.apnmt.organizationappointment;

import de.apnmt.organizationappointment.OrganizationappointmentserviceApp;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * Base composite annotation for integration tests.
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@SpringBootTest(classes = OrganizationappointmentserviceApp.class)
public @interface IntegrationTest {
}
