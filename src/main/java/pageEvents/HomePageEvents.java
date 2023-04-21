package pageEvents;

import pageObjects.HomePageElements;
import utils.BaseClass;
import utils.ElementFetch;

public class HomePageEvents {
    public void clickOnSignInButton(){
        ElementFetch elementFetch =  new ElementFetch();
        BaseClass.logger.info("Trying to find and click on sign in button!");
        elementFetch.getWebElement("XPATH", HomePageElements.signInButton).click();
    }
}
