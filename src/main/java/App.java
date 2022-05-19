import DAO.JobsDao;
import model.Contacts;
import model.jobs;
import org.sql2o.Sql2o;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;
import DAO.*;
import dao.*;

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




        port(getHerokuAssignedPort());
        staticFileLocation("/public");

        String connect =  "jdbc:postgresql://localhost/geek_collaborators";
        Sql2o sql2o = new Sql2o(connect,"chechesylvia","0718500898");

        Sql2oJobsDao JobsDao = new Sql2oJobsDao(sql2o);




        get("/",(request, response) -> {
            Map<String, Object> model = new HashMap<>();
            return new ModelAndView(model,"index.hbs");
        },new HandlebarsTemplateEngine());


        get("/contact",(request, response) -> {
            Map<String, Object> model = new HashMap<>();
            return new ModelAndView(model,"contact.hbs");
        },new HandlebarsTemplateEngine());


//        get("/jobs", (request, response) -> {
//            Map<String, Object> model = new HashMap<>();
//            return new ModelAndView(model, "jobs.hbs");
//        }, new HandlebarsTemplateEngine());

        //display form receive clients data
        get("/jobs/new",(request,response)->{
            Map<String, Object>model = new HashMap<>();
            List<jobs> jobs = JobsDao.getAll();
            model.put("jobs", jobs);
            return new ModelAndView(model,"jobs.hbs");
        },new HandlebarsTemplateEngine());

//process new contact form
        post("/jobs",(request,response)->{
            Map<String, Object>model = new HashMap<>();
            String company = request.queryParams("company");
            String title = request.queryParams("title");
            String description = request.queryParams("description");
            String duration = request.queryParams("duration");
            String languages = request.queryParams("languages");
            jobs jobs = new jobs(company, title, description, duration, languages);
            JobsDao.add(jobs);
            response.redirect("/");
            return null;
        },new HandlebarsTemplateEngine());

// display all contacts
        get("/jobs",(request, response) -> {
            Map<String, Object> model = new HashMap<>();
            List<jobs> allJobs = JobsDao.getAll();
            model.put("jobs",allJobs);
            return new ModelAndView(model,"job-form.hbs");
        },new HandlebarsTemplateEngine());

//clear all endangered
        get("/jobs/delete",(request, response)->{
            Map<String,Object>model = new HashMap<>();
            JobsDao.deleteAll();
            response.redirect("/");
            return null;
        },new HandlebarsTemplateEngine());


//delete job by Id
        get("/jobs/:id/delete",(request, response)->{
            Map<String, Object>model = new HashMap<>();
            int idOfJobToDelete = Integer.parseInt(request.params("id"));
            JobsDao.deleteById(idOfJobToDelete);
            response.redirect("/");
            return null;
        }, new HandlebarsTemplateEngine());

    }

}
