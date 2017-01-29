package controllers;

import play.mvc.Controller;
import play.mvc.Result;
import views.html.loginPage;

/**
 * Created by sharonramdass on 1/28/17.
 */
public class LoginController extends Controller {

    public Result displayLogin() {
        return ok(loginPage.render("Please enter username and password"));
    }
}
