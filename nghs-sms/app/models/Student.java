package models;

import com.avaje.ebean.Model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;
import java.io.File;

/**
 * Created by sharonramdass on 1/30/17.
 */
@Entity
public class Student extends Model {

    @Id
    @GeneratedValue
    public Long id;

    public String firstName;
    public String middleName;
    public String lastName;
    public String address;
    public String DOB;

    @Lob
    public File birthCertificate;

    @Lob
    public File profilePic;

    public static Finder<Long, Student> find = new Finder<Long,Student>(Student.class);

}
