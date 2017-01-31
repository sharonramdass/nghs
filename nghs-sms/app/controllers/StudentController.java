package controllers;

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

        Http.MultipartFormData<File> body = request().body().asMultipartFormData();
        Http.MultipartFormData.FilePart<File> picture = body.getFile("profilePicture");
        if (picture != null) {
            String fileName = picture.getFilename();
            String contentType = picture.getContentType();
            File file = picture.getFile();
            Student newStudent = new Student();
            newStudent.firstName = firstName;
            newStudent.profilePic = file;
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
}
