package controllers;

import models.Contact;
import models.Student;
import org.apache.commons.io.FilenameUtils;
import org.h2.store.fs.FileUtils;
import play.data.DynamicForm;
import play.data.Form;
import play.mvc.Controller;
import play.mvc.Http;
import play.mvc.Result;
import sun.misc.IOUtils;
import views.html.studentForm;
import views.html.studentInfoPage;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

import static play.mvc.Results.ok;

/**
 * Created by sharonramdass on 1/30/17.
 */
public class StudentController extends Controller {

    public Result displayStudentForm() {
        return ok(studentForm.render());
    }

    public Result createStudent() {

        DynamicForm dynamicForm = Form.form().bindFromRequest();
        String firstName = dynamicForm.get("firstName");
        String middleName = dynamicForm.get("middleName");
        String lastName = dynamicForm.get("lastName");
        String dateOfBirth = dynamicForm.get("dateOfBirth");
        String address = dynamicForm.get("address");

        //Contact1 Information
        String contact1FirstName = dynamicForm.get("contact1FirstName");
        String contact1LastName = dynamicForm.get("contact1astName");
        String contact1Occupation = dynamicForm.get("contact1Occupation");
        String contact1HomeNumber = dynamicForm.get("contact1HomeNumber");
        String contact1MobileNumber = dynamicForm.get("contact1MobileNumber");
        String contact1Relationship = dynamicForm.get("contact1Relationship");
        String contact1Status = dynamicForm.get("contact1Status");
        String contact1Company = dynamicForm.get("contact1Company");
        String contact1Comment = dynamicForm.get("contact1Comment");

        Contact contact1 = new Contact();
        contact1.firstName = contact1FirstName;
        contact1.lastName = contact1LastName;
        contact1.occupation = contact1Occupation;
        contact1.homeNumber = contact1HomeNumber;
        contact1.mobileNumber = contact1MobileNumber;
        contact1.relationship = contact1Relationship;
        contact1.status = contact1Status;
        contact1.company = contact1Company;
        contact1.comment = contact1Comment;
        contact1.save();




        Http.MultipartFormData<File> body = request().body().asMultipartFormData();
        Http.MultipartFormData.FilePart<File> picture1 = body.getFile("profilePicture");
        Http.MultipartFormData.FilePart<File> picture2 = body.getFile("birthCertificate");
        if (picture1 != null) {
            String fileName = picture1.getFilename();
            String contentType = picture1.getContentType();
            File profilePicFile = picture1.getFile();
            String fileName2 = picture2.getFilename();
            String contentType2 = picture2.getContentType();
            File file2 = picture2.getFile();
            Student newStudent = new Student();
            newStudent.firstName = firstName;
            newStudent.middleName = middleName;
            newStudent.lastName = lastName;
            newStudent.DOB = dateOfBirth;
            newStudent.address = address;
            newStudent.profilePic = profilePicFile;
            newStudent.birthCertificate = file2;
            newStudent.contact1 = contact1;
            newStudent.save();
//            File oldFile = new File("public/images/profilePic");
//            oldFile.delete();
//            org.apache.commons.io.FileUtils.moveFile(file, new File("public/images/profilePic"));
            //return ok(new ByteArrayInputStream(Files.readAllBytes(file.toPath()))).as("image/jpeg");
            return ok(studentInfoPage.render(newStudent));
//            return ok("First name: " + firstName + " File uploaded");
        } else {
            flash("error", "Missing file");
            return badRequest();
        }
    }

    public Result getStudentProfilePic(Long studentId) throws IOException {
        Student student = Student.find.byId(studentId);
        File file = student.profilePic;
        return ok(new ByteArrayInputStream(Files.readAllBytes(file.toPath()))).as("image/jpeg");
    }
    public Result getStudentBirthCertificate(Long studentId) throws IOException {
        Student student = Student.find.byId(studentId);
        File file2 = student.birthCertificate;
        return ok(new ByteArrayInputStream(Files.readAllBytes(file2.toPath()))).as("image/jpeg");
    }
}
