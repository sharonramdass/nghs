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

        String contact2FirstName = dynamicForm.get("contact2FirstName");
        String contact2LastName = dynamicForm.get("contact2astName");
        String contact2Occupation = dynamicForm.get("contact2Occupation");
        String contact2HomeNumber = dynamicForm.get("contact2HomeNumber");
        String contact2MobileNumber = dynamicForm.get("contact2MobileNumber");
        String contact2Relationship = dynamicForm.get("contact2Relationship");
        String contact2Status = dynamicForm.get("contact2Status");
        String contact2Company = dynamicForm.get("contact2Company");
        String contact2Comment = dynamicForm.get("contact2Comment");

        Contact contact2 = new Contact();
        contact2.firstName = contact2FirstName;
        contact2.lastName = contact2LastName;
        contact2.occupation = contact2Occupation;
        contact2.homeNumber = contact2HomeNumber;
        contact2.mobileNumber = contact2MobileNumber;
        contact2.relationship = contact2Relationship;
        contact2.status = contact2Status;
        contact2.company = contact2Company;
        contact2.comment = contact2Comment;
        contact2.save();

        String contact3FirstName = dynamicForm.get("contact3FirstName");
        String contact3LastName = dynamicForm.get("contact3astName");
        String contact3Occupation = dynamicForm.get("contact3Occupation");
        String contact3HomeNumber = dynamicForm.get("contact3HomeNumber");
        String contact3MobileNumber = dynamicForm.get("contact3MobileNumber");
        String contact3Relationship = dynamicForm.get("contact3Relationship");
        String contact3Status = dynamicForm.get("contact3Status");
        String contact3Company = dynamicForm.get("contact3Company");
        String contact3Comment = dynamicForm.get("contact3Comment");

        Contact contact3 = new Contact();
        contact3.firstName = contact3FirstName;
        contact3.lastName = contact3LastName;
        contact3.occupation = contact3Occupation;
        contact3.homeNumber = contact3HomeNumber;
        contact3.mobileNumber = contact3MobileNumber;
        contact3.relationship = contact3Relationship;
        contact3.status = contact3Status;
        contact3.company = contact3Company;
        contact3.comment = contact3Comment;
        contact3.save();





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
            newStudent.contact2 = contact2;
            newStudent.contact3 = contact3;
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
