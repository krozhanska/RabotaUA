package ua.rabota;

/**
 * Created by k.rozhanska on 20.06.2017.
 */

import org.testng.annotations.*;


public class DataProviderTest {

    @DataProvider(name = "AuthenticationNotValid")
    public static Object[][] emails() {

        return new Object[][] { { "email@"}, { "  @com.ua"}};

    }

    @DataProvider(name = "Authentication")
    public static Object[][] credentials() {

        return new Object[][] { { "emailns@gmail.com", "pass", "Name Surname" }, { "emailsn@gmail.com", "pass2","NameN SurnameS" }};

    }
}


