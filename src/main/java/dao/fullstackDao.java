package dao;

import model.fullstack;

import java.util.List;

public interface fullstackDao {
        void create();
        List<fullstack> getAllfullstack();


        fullstack findSightingById(int id);
}