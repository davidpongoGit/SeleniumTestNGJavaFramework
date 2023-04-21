import org.testng.annotations.Test;
import pageEvents.HomePageEvents;
import pageEvents.LoginPageEvents;
import utils.BaseClass;

public class testAmazonLogin extends BaseClass {
    @Test
     public void signInFlow(){
        HomePageEvents homePageEvents = new HomePageEvents();
        LoginPageEvents loginPageEvents = new LoginPageEvents();

        homePageEvents.clickOnSignInButton();
        loginPageEvents.verifyLoginPageIsOpen();
        loginPageEvents.enterEmailId();
    }
}
