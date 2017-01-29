package models;

import com.avaje.ebean.Model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * Created by sharonramdass on 1/29/17.
 */
@Entity
public class User extends Model {

    @Id
    @GeneratedValue
    public Long id;

    public String username;

    public String password;

    public static Finder<Long, User> find = new Finder<Long,User>(User.class);

}
