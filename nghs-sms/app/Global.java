import models.User;
import play.Application;
import play.GlobalSettings;
import views.html.loginPage;

/**
 * Created by sharonramdass on 1/30/17.
 */
public class Global extends GlobalSettings {

    @Override
    public void onStart(Application app) {
        User user = User.find.where().eq("username", "sramdass").and().eq("password", "1234").findUnique();
        if (user == null) {
            user = new User();
            user.username = "sramdass";
            user.password = "1234";
            user.save();
        }
    }
}