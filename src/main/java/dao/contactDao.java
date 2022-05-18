package dao;

import model.Contacts;

import java.util.List;

public interface contactDao {

    // create new contact
    void create();
    // list all contacts
    List<Contacts> getAllContacts();

    // create a new contacts
    void addContacts(Contacts contacts);


    // get a specific contact
    Contacts findSightingById(int id);

    // delete by id
    void deleteById(int id);

    // delete all contacts.
    void deleteAllContacts();

    // more methods will added as the application grows.

}
