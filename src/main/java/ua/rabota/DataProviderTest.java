package ua.rabota;


import org.testng.annotations.*;


public class DataProviderTest {

    @DataProvider(name = "AuthenticationNotValid")
    public static Object[][] emails() {

        return new Object[][] { { "email@"}, { "  @com.ua"}};

    }

    @DataProvider(name = "Authentication")
    public static Object[][] credentials() {

        return new Object[][] { { "emailns@gmail.com", "pass", "Name Surname","0" }, { "emailsn@gmail.com", "pass2","NameN SurnameS" ,"1"}};

    }
}


