package models;

import com.avaje.ebean.Model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * Created by sharonramdass on 2/2/17.
 */
@Entity
public class Contact extends Model {

    @Id
    @GeneratedValue
    public Long id;

    public String firstName;
    public String lastName;
    public String occupation;
    public String company;
    public String homeNumber;
    public String mobileNumber;
    public String relationship;
    public String status;
    public String comment;

    public static Finder<Long, Contact> find = new Finder<Long,Contact>(Contact.class);
}
