import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;

import java.util.HashMap;
import java.util.Map;

import static spark.Spark.get;
import static spark.Spark.staticFileLocation;

public class App {
    public static void main(String[] args) {
        staticFileLocation("/public");

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

    }
}
