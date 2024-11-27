package roman.bo;

import io.qameta.allure.Step;
import roman.po.LoginPO;
import roman.po.RegisterPO;

public class WebsiteBO {
    LoginPO loginPage = new LoginPO();
    RegisterPO registerPage = new RegisterPO();

    @Step("Registering a user with email: {0}")
    public WebsiteBO loginUser(String username, String password) {
        loginPage
                .openLoginPage()
                .enterUsername(username)
                .enterPassword(password)
                .submit();

        return this;
    }

    @Step("Registering a user with email: {0}")
    public WebsiteBO registerUser(String username, String password) {
        registerPage
                .openRegisterPage()
                .enterUsername(username)
                .enterPassword(password)
                .submit();

        return this;
    }

    @Step("Verifying that the dashboard is visible")
    public boolean isLoginSuccessful() {
        return loginPage.isDashboardVisible();
    }

    @Step("Verifying that the registration is successful")
    public boolean isRegisterSuccessful() {
        return registerPage.isDashboardVisible();
    }

}
