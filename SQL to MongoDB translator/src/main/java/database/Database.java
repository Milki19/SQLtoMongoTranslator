package database;

import data.Row;

import java.sql.SQLException;
import java.util.List;

public interface Database {

    List<Row> getDataFromTable(List<String> lista) throws SQLException;



}
