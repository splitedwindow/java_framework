package roman.bo;
import roman.po.LoginPO;
import roman.po.RegisterPO;

public class WebsiteBO {
    LoginPO loginPage = new LoginPO();
    RegisterPO registerPage = new RegisterPO();
    public WebsiteBO LoginUser(String username, String password) {
        loginPage
                .openLoginPage()
                .enterUsername(username)
                .enterPassword(password)
                .submit();

        return this;
    }

    public WebsiteBO RegisterUser(String username, String password) {
        registerPage
                .openRegisterPage()
                .enterUsername(username)
                .enterPassword(password)
                .submit();

        return this;
    }


}
