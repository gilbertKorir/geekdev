package DAO;

import model.jobs;

import java.util.List;


public interface JobsDao {
    //CRUD

    void add(jobs job);

    List<jobs> getAll();

    jobs findById(int id);
    void update(int id, jobs job);

    void deleteById(int id);
}