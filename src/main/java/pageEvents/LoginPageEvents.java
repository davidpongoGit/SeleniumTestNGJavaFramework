package pageEvents;

import dev.failsafe.internal.util.Assert;
import pageObjects.LoginPageElements;
import utils.BaseClass;
import utils.ElementFetch;

public class LoginPageEvents {
    public void verifyLoginPageIsOpen(){
        ElementFetch elementFetch = new ElementFetch();
        BaseClass.logger.info("Verifying login page has been opened.");
        Assert.isTrue(elementFetch.getListWebElements("XPATH", LoginPageElements.loginTitle).size()>0, "Login Page Not Found");
    }
    public void enterEmailId(){
        ElementFetch elementFetch = new ElementFetch();
        BaseClass.logger.info("Trying to enter email address into email field.");
        elementFetch.getWebElement("XPATH", LoginPageElements.emailAddressField).sendKeys("david.pongo@hotmail.com");
    }
}
