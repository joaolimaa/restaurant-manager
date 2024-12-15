package fiap.restaurant_manager.cucumber;

import io.cucumber.spring.CucumberContextConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberContextConfiguration
@SpringBootTest
(
    webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT
)
@CucumberOptions
(
    features = "src/test/resources/features",
    glue = "fiap.restaurant_manager.cucumber.steps",
    plugin = { "pretty", "html:target/cucumber-report.html" },
    monochrome = true
)
public class CucumberTest { }