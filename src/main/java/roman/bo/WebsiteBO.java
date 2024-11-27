package roman.bo;
import roman.po.LoginPO;
import roman.po.RegisterPO;

public class WebsiteBO {
    LoginPO loginPage = new LoginPO();
    RegisterPO registerPage = new RegisterPO();
    public WebsiteBO loginUser(String username, String password) {
        loginPage
                .openLoginPage()
                .enterUsername(username)
                .enterPassword(password)
                .submit();

        return this;
    }

    public WebsiteBO registerUser(String username, String password) {
        registerPage
                .openRegisterPage()
                .enterUsername(username)
                .enterPassword(password)
                .submit();

        return this;
    }

    public boolean isLoginSuccessful() {
        return loginPage.isDashboardVisible();
    }

    public boolean isRegisterSuccessful() {
        return registerPage.isDashboardVisible();
    }


}
