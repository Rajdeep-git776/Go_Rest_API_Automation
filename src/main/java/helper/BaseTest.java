package helper;

import io.restassured.RestAssured;
import org.testng.annotations.BeforeMethod;
import utils.PropertyReader;

public class BaseTest {
    @BeforeMethod
    public void setUp() {
        RestAssured.baseURI = PropertyReader.propertyReader("baseURI");
    }
}
