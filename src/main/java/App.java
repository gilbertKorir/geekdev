
import dao.Sql2oCollaborationDao;
import dao.Sql2oDevelopersDao;
import dao.Sql2oStudentsDao;
import model.Collaboration;
import model.Developers;
import model.Students;

import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import static spark.Spark.*;

public class App {


    public static void main(String[] args) {
        Sql2oStudentsDao sql2oStudentsDao ;
        Sql2oDevelopersDao sql2oDevelopersDao;
        Sql2oCollaborationDao sql2oCollaborationDao;

        sql2oStudentsDao = new Sql2oStudentsDao(sql2o);
        sql2oDevelopersDao = new Sql2oDevelopersDao(sql2o);
        sql2oCollaborationDao = new Sql2oCollaborationDao(sql2o);


            /*----------------------------Consume API--------------------------------*/
            get("/android", (request, response) ->{
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

