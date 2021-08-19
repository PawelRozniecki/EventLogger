package com.creditsuisse.pawelrozniecki.interfaces;

import com.creditsuisse.pawelrozniecki.models.AlertEvent;

import java.sql.Connection;
import java.util.List;

public interface DatabaseInterface {

    void startServer();
    void select();
    boolean createTable();
    boolean save(List<AlertEvent> list);
    Connection setupConnection();


}
