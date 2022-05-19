import dao.fullstackDao;
import model.fullstack;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import static spark.Spark.*;
//import static spark.SparkBase.staticFileLocation;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static spark.Spark.*;


public class App {
    static int getHerokuAssignedPort() {
        ProcessBuilder processBuilder = new ProcessBuilder();
        if (processBuilder.environment().get("PORT") != null) {
            return Integer.parseInt(processBuilder.environment().get("PORT"));
        }
        return 4567;
    }
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
            return new ModelAndView(model, "index.hbs");
        },new HandlebarsTemplateEngine());

        get("/fullstack",(request, response) -> {
            Map<String, Object> model = new HashMap<String, Object>();
            return new ModelAndView(model, "fullstack-form.hbs");
        },new HandlebarsTemplateEngine());

        get("/fullstackCollaborators",(request, response) -> {
            Map<String, Object> model = new HashMap<String, Object>();
            return new ModelAndView(model, "fullstack.hbs");
        },new HandlebarsTemplateEngine());

        get("/fullstack/new",(request, response) -> {
            Map<String, Object> model = new HashMap<String, Object>();
            return new ModelAndView(model, "fullstack.hbs");
        },new HandlebarsTemplateEngine());


//        post("/fullstack", (request, response) -> {
//            Map<String,Object> model=new HashMap<String, Object>();
//            String name = request.queryParams("name");
//            String owner = request.queryParams("owner");
//            String owner_contact = request.queryParams("owner_contact");
//        });

        //process new contact form
        post("/fullstack",(request,response)-> {
            Map<String, Object> model = new HashMap<>();
            String name = request.queryParams("name");
            String owner = request.queryParams("owner");
            String owner_contact = request.queryParams("owner_contact");
            fullstack fullstack = new fullstack(name, owner, owner_contact);
            fullstackDao.addFullstack(fullstack);
            response.redirect("/");
            return null;
        },new HandlebarsTemplateEngine());


//        post("fullstackCollaborators/new",((request, response) -> {
//          Map<String,Object> model=new HashMap<String, Object>();
//          String name=request.queryParams("name");
//          String language=request.queryParams("language");
//          String contact=request.queryParams("contact");
//          String role=request.queryParams("role");
//
//        })

        port(getHerokuAssignedPort());
        staticFileLocation("/public");


        get("/",(request, response) -> {
            Map<String, Object> model = new HashMap<>();
            return new ModelAndView(model,"index.hbs");
        },new HandlebarsTemplateEngine());

        get("/contact",(request, response) -> {
            Map<String, Object> model = new HashMap<>();
            return new ModelAndView(model,"contact.hbs");
        },new HandlebarsTemplateEngine());

    }
}
