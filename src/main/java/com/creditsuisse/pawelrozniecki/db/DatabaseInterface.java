package com.creditsuisse.pawelrozniecki.db;

import com.creditsuisse.pawelrozniecki.models.AlertEvent;

import java.util.List;

public interface DatabaseInterface {

    void startServer();

    void select();

    boolean createTable();

    boolean save(List<AlertEvent> list);


}
