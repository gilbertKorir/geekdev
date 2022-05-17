import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import static spark.Spark.*;
//import static spark.SparkBase.staticFileLocation;

public class App {
    public static void main(String[] args) {
        staticFileLocation("/public");
        String layout = "templates/layout.hbs";

        ProcessBuilder process = new ProcessBuilder();
        Integer port;

        if (process.environment().get("PORT") != null) {
            port = Integer.parseInt(process.environment().get("PORT"));
        } else {
            port = 4567;
        }
        port(port);

        get("/",(request, response) -> {
            Map<String, Object> model = new HashMap<String, Object>();
            return new ModelAndView(model, "fullstack-form.hbs");
        },new HandlebarsTemplateEngine());

        get("/fullstackCollaborators",(request, response) -> {
            Map<String, Object> model = new HashMap<String, Object>();
            return new ModelAndView(model, "fullstack-form.hbs");
        },new HandlebarsTemplateEngine());

//        post("fullstackCollaborators/new",((request, response) -> {
//          Map<String,Object> model=new HashMap<String, Object>();
//          String name=request.queryParams("name");
//          String language=request.queryParams("language");
//          String contact=request.queryParams("contact");
//          String role=request.queryParams("role");
//
//        })
    }
}
