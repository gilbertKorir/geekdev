import dao.Sql2oCollaborationDao;
import dao.Sql2oDevelopersDao;
import dao.Sql2oStudentsDao;
import model.Collaboration;
import model.Developers;
import model.Students;
import org.sql2o.Connection;
import org.sql2o.Sql2o;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import org.sql2o.Connection;

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
            String resume = request.queryParams("resume");
            Developers developers = new Developers(name, email, password, resume);
            sql2oDevelopersDao.add(developers);
            return new ModelAndView(model, "developer-form.hbs");
        }, new HandlebarsTemplateEngine());

        post("/projects/developers/new", (request, response) -> {
            Map<String, Objects> model = new HashMap<String, Objects>();
            String username = request.queryParams("username");
            String project_code = request.queryParams("project_code");
            Collaboration collaboration = new Collaboration(username,project_code);
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
