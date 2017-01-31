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

    public Result createStudent() throws IOException {

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

//            Student retrievedStudent = Student.find.byId(newStudent.id);
//            file = retrievedStudent.profilePic;

            new File("assets/images/profilePic").delete();
            org.apache.commons.io.FileUtils.moveFile(file, new File("assets/images/profilePic"));
            //return ok(new ByteArrayInputStream(Files.readAllBytes(file.toPath()))).as("image/jpeg");
            return ok(studentInfoPage.render(newStudent));
            //return ok("First name: " + firstName + " File uploaded");
        } else {
            flash("error", "Missing file");
            return badRequest();
        }


    }
}
