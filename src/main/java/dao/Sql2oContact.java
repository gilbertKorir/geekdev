package dao;

import model.Contacts;
import org.sql2o.Connection;
import org.sql2o.Sql2o;
import org.sql2o.Sql2oException;

import java.util.List;

public class Sql2oContact implements contactDao{
    // initialize Sql2o
    private final Sql2o sql2o;


    public Sql2oContact(Sql2o sql2o) {
        this.sql2o = sql2o;
    }

    public void getDrivers(){
        try{
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void create() {

    }

    @Override
    public List<Contacts> getAllContacts() {
        getDrivers();
        String sql = "SELECT * FROM sightings";
        try (Connection connection = sql2o.open()){
            return connection.createQuery(sql)
                    .executeAndFetch(Contacts.class);
        }
    }

    @Override
    public void addContacts(Contacts contacts) {
        getDrivers();
        String sql = "INSERT INTO sightings (name,location,animId,endangeredId) VALUES (:name,:location,:animId,:endangeredId)";
        try(Connection conn = sql2o.open()){
            int id = (int) conn.createQuery(sql,true)
                    .bind(contacts)
                    .executeUpdate()
                    .getKey();
            contacts.setId(id);
        }catch(Sql2oException e){
            System.out.println(e);
        }
    }

    @Override
    public Contacts findSightingById(int id) {
        getDrivers();
        try(Connection con = sql2o.open()) {
            return con.createQuery("SELECT * FROM sightings WHERE id = :id")
                    .addParameter("id", id)
                    .executeAndFetchFirst(Contacts.class);
        }
    }

    @Override
    public void deleteById(int id) {
        getDrivers();
        String sql = "DELETE FROM sightings WHERE id = :id";
        try(Connection conn = sql2o.open()){
            conn.createQuery(sql)
                    .addParameter("id",id)
                    .executeUpdate();
        }catch(Sql2oException e){
            System.out.println(e);
        }
    }

    @Override
    public void deleteAllContacts() {
        getDrivers();
        String sql = "DELETE from sightings";
        try (Connection con = sql2o.open()) {
            con.createQuery(sql)
                    .executeUpdate();
        } catch (Sql2oException e) {
            System.out.println(e);
        }
    }

}
