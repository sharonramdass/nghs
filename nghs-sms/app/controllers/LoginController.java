package controllers;

import models.User;
import play.Logger;
import play.data.DynamicForm;
import play.data.Form;
import play.mvc.Controller;
import play.mvc.Result;
import views.html.index;
import views.html.loginPage;

/**
 * Created by sharonramdass on 1/28/17.
 */
public class LoginController extends Controller {

    public Result displayLogin() {
        return ok(loginPage.render(""));
    }

    public Result verify() {
        DynamicForm dynamicForm = Form.form().bindFromRequest();
        String userName = dynamicForm.get("userName");
        String userPassword = dynamicForm.get("userPassword");
        //Logger.info("Username is: " + userName);
        //Logger.info("Password is: " + userPassword);
        User user = User.find.where().eq("username", userName).and().eq("password", userPassword).findUnique();
        if (user == null) {
            return forbidden(loginPage.render("Username and Password incorrect"));
        } else {
            return ok(index.render("Login successful"));
        }
    }
}
