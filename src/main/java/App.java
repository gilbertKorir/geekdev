
import dao.Sql2oCollaborationDao;
import dao.Sql2oDevelopersDao;
import dao.Sql2oStudentsDao;
import model.Collaboration;
import model.Developers;
import model.Students;
import org.sql2o.Connection;

import dao.Sql2oContact;
import model.Contacts;
import org.sql2o.Sql2o;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import org.sql2o.Connection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static spark.Spark.*;

public class App {


    public static void main(String[] args) {
        Sql2oStudentsDao sql2oStudentsDao ;
        Sql2oDevelopersDao sql2oDevelopersDao;
        Sql2oCollaborationDao sql2oCollaborationDao;
        Connection conn;


        staticFileLocation("/public");

        String connectionString = "jdbc:postgresql://localhost:5432/geek_collaborators";
        Sql2o sql2o = new Sql2o(connectionString, "postgres", "1234");
        sql2oStudentsDao = new Sql2oStudentsDao(sql2o);
        sql2oDevelopersDao = new Sql2oDevelopersDao(sql2o);
        sql2oCollaborationDao = new Sql2oCollaborationDao(sql2o);
//
//        Students stud = new Students("john", "j@gmail","qwee");
//        sql2oStudentsDao.add(stud);
//        System.out.println("OOOHRAY!");
//        Developers dev = new Developers("jane", "j@gmail.com","o756o56", "trye");
//        sql2oDevelopersDao.add(dev);
//
//    static int getHerokuAssignedPort() {
//        ProcessBuilder processBuilder = new ProcessBuilder();
//        if (processBuilder.environment().get("PORT") != null) {
//            return Integer.parseInt(processBuilder.environment().get("PORT"));
//        }
//        return 4567;

//    public static void main(String[] args) {
//        port(getHerokuAssignedPort());
//        staticFileLocation("/public");

        // creating a db connection:


//        String connection = "jdbc:postgresql://ec2-54-172-175-251.compute-1.amazonaws.com:5432/d19tsrp5ts9arv";
//        Sql2o sql2o = new Sql2o(connection,"acutsmyrvfxroj","f6f2568b1bedb19e5723424cd139ea089f13b9effb3756dcc39ca0ba0196a631");


//        String connect =  "jdbc:postgresql://localhost/geek_collaborators";
//        Sql2o sql2o = new Sql2o(connect,"postgres","okello");

        Sql2oContact contactDao = new Sql2oContact(sql2o);


        get("/",(request, response) -> {
            Map<String, Object> model = new HashMap<>();
            return new ModelAndView(model,"index.hbs");
        },new HandlebarsTemplateEngine());


//        //display form receive clients data
//        get("/contact/new",(request,response)->{
//            Map<String, Object>model = new HashMap<>();
//            List<Contacts> contacts = contactDao.getAllContacts();
//            model.put("contact",connect);
//            return new ModelAndView(model,"contact.hbs");
//        },new HandlebarsTemplateEngine());

        //process new contact form
        post("/contact",(request,response)->{
            Map<String, Object>model = new HashMap<>();
            String name = request.queryParams("name");
            String email = request.queryParams("email");
            String subject = request.queryParams("subject");
            String message = request.queryParams("message");
            Contacts contacts = new Contacts(name, email, subject, message);
            contactDao.addContacts(contacts);
            response.redirect("/");
            return null;
        },new HandlebarsTemplateEngine());

        // display all contacts
        get("/contact",(request, response) -> {
            Map<String, Object> model = new HashMap<>();
            List<Contacts> allContacts = contactDao.getAllContacts();
            model.put("contact",allContacts);
            return new ModelAndView(model,"contact_details.hbs");
        },new HandlebarsTemplateEngine());

        //clear all contacts
        get("/contact/delete",(request, response)->{
            Map<String,Object>model = new HashMap<>();
            contactDao.deleteAllContacts();
            response.redirect("/");
            return null;
        },new HandlebarsTemplateEngine());


        //delete contact by Id
        get("/contact/:id/delete",(request, response)->{
            Map<String, Object>model = new HashMap<>();
            int idOfContactToDelete = Integer.parseInt(request.params("id"));
            contactDao.deleteById(idOfContactToDelete);
            response.redirect("/");
            return null;
        }, new HandlebarsTemplateEngine());

            /*----------------------------Consume API--------------------------------*/
            get("/", (request, response) ->{
                Map<String, Object> model = new HashMap<String, Object>();
                return new ModelAndView(model, "android.hbs");
            }, new HandlebarsTemplateEngine());
            get("/create/student", (request, response) -> {
                Map<String, Object> model = new HashMap<String, Object>();
                return new ModelAndView(model, "student-form.hbs");
            }, new HandlebarsTemplateEngine());
            get("/create/developer", (request, response) -> {
                Map<String, Object> model = new HashMap<String, Object>();
                return new ModelAndView(model, "developer-form.hbs");
            }, new HandlebarsTemplateEngine());
            get("/add/student", (request, response) -> {
                Map<String, Object> model = new HashMap<String, Object>();
                return new ModelAndView(model, "loginStudent.hbs");
            }, new HandlebarsTemplateEngine());
            get("/add/developer", (request, response) -> {
                Map<String, Object> model = new HashMap<String, Object>();
                return new ModelAndView(model, "loginDeveloper.hbs");
            }, new HandlebarsTemplateEngine());
            get("/projects/students", (request, response) -> {
                Map<String, Object> model = new HashMap<String, Object>();
                return new ModelAndView(model, "studentProjects.hbs");
            }, new HandlebarsTemplateEngine());
            get("/projects/developers", (request, response) -> {
                Map<String, Object> model = new HashMap<String, Object>();
                return new ModelAndView(model, "developerProjects.hbs");
            }, new HandlebarsTemplateEngine());

            /*-------------------------------------ADD INTO THE DATABASE----------------------------*/
            post("/create/student/new", (request, response) -> {
                Map<String, Objects> model = new HashMap<String, Objects>();
                String name = request.queryParams("name");
                String email = request.queryParams("email");
                String password = request.queryParams("password");
                Students students = new Students(name, email, password);
                sql2oStudentsDao.add(students);
                return new ModelAndView(model, "student-form.hbs");
            }, new HandlebarsTemplateEngine());

            post("/create/developer/new", (request, response) -> {
                Map<String, Objects> model = new HashMap<String, Objects>();
                String name = request.queryParams("name");
                String email = request.queryParams("email");
                String password = request.queryParams("password");
                Developers developers = new Developers(name, email, password);
                sql2oDevelopersDao.add(developers);
                return new ModelAndView(model, "developer-form.hbs");
            }, new HandlebarsTemplateEngine());

        post("/projects/developers/new", (request, response) -> {
            Map<String, Objects> model = new HashMap<String, Objects>();
            String username = request.queryParams("username");
            String resume = request.queryParams("resume");
            String project_code = request.queryParams("project_code");
            String date = request.queryParams("date");
            Collaboration collaboration = new Collaboration(username,resume, project_code, new Date());
            sql2oCollaborationDao.add(collaboration);
            return new ModelAndView(model, "developerProjects.hbs");
        }, new HandlebarsTemplateEngine());

        get("/view/collaborators",(request, response) -> {
            Map<String,Object> model=new HashMap<String, Object>();
            model.put("collaboration",sql2oCollaborationDao.getAll());
            return new ModelAndView(model,"collaborate-view.hbs");
        },new HandlebarsTemplateEngine());
    }
}

