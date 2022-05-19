import dao.fullstackDao;
import model.fullstack;
import dao.Sql2oContact;
import model.Contacts;
import org.sql2o.Sql2o;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;

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


        // String connection = "jdbc:postgresql://ec2-54-172-175-251.compute-1.amazonaws.com:5432/d19tsrp5ts9arv";
//        Sql2o sql2o = new Sql2o(connection,"acutsmyrvfxroj","f6f2568b1bedb19e5723424cd139ea089f13b9effb3756dcc39ca0ba0196a631");


        String connect =  "jdbc:postgresql://localhost/geek_collaborators";
        Sql2o sql2o = new Sql2o(connect,"postgres","okello");

        Sql2oContact contactDao = new Sql2oContact(sql2o);



        get("/",(request, response) -> {
            Map<String, Object> model = new HashMap<>();
            return new ModelAndView(model,"index.hbs");
        },new HandlebarsTemplateEngine());


        //display form receive clients data
        get("/contact/new",(request,response)->{
            Map<String, Object>model = new HashMap<>();
            List<Contacts> contacts = contactDao.getAllContacts();
            model.put("contact",connect);
            return new ModelAndView(model,"contact.hbs");
        },new HandlebarsTemplateEngine());

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


        //process new contact form
        post("/fullstack",(request,response)-> {
            Map<String, Object> model = new HashMap<>();
            String name = request.queryParams("name");
            String owner = request.queryParams("owner");
            String owner_contact = request.queryParams("owner_contact");
            fullstack fullstack = new fullstack(name, owner, owner_contact);
            response.redirect("/");
            return null;
        },new HandlebarsTemplateEngine());


    }
}
