package database;

import com.mongodb.MongoClient;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import data.Row;
import database.settings.Settings;
import org.bson.Document;
import parser.SQLQ;

import javax.print.Doc;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MongoDataBase implements Database {

    private Settings settings;
    private MongoClient connection;
    private static String ip;
    private static String database;
    private static String username;
    private static String password;


    public MongoDataBase(Settings settings) throws SQLException {
        this.settings = settings;
        getParameters();
    }

    private void getParameters() throws SQLException {
        ip = settings.getParameter("mongodb_ip");
        database = settings.getParameter("mongodb_database");
        username = settings.getParameter("mongodb_username");
        password = settings.getParameter("mongodb_password");
    }

    public MongoClient getConnection() {
        MongoCredential credential = MongoCredential.createCredential(username, database, password.toCharArray());
        connection = new MongoClient(new ServerAddress(ip, 27017), Arrays.asList(credential));

        return connection;
    }

    private void closeConnection() {
        try {
            connection.close();
        } finally {
            connection = null;
        }
    }


    @Override
    public List<Row> getDataFromTable(List<String> lista) throws SQLException {

        getConnection();
        List<Row> rows = new ArrayList<>();

        MongoDatabase database = connection.getDatabase("bp_tim6");
        MongoCursor<Document> cursor = null;
        int tip = SQLQ.getInstance().getTip();

        List<String> stvari = new ArrayList<>();

        if (tip == 1) {
            cursor = database.getCollection(lista.get(0)).find(Document.parse(lista.get(1))).projection(Document.parse(lista.get(2))).sort(Document.parse(lista.get(3))).iterator();
            //from where select order
        }else if (tip == 2) {
            stvari.add(lista.get(1));
            stvari.add("{$match: " + lista.get(2) + "}");
//            stvari.add("{$project: " + lista.get(3) + "}");

//            if(stvari.contains(lista.get(4))){
//                stvari.add("{%sort: " + lista.get(4) + "}");
//            }
//            System.out.println(lista.get(0));
//            System.out.println(lista.get(2));
//            System.out.println(lista.get(3));

            cursor = database.getCollection(lista.get(0)).find(Document.parse(lista.get(2))).projection(Document.parse(lista.get(3))).iterator();

        }else if (tip == 3) {

        }else if (tip == 4) {
            StringBuilder sb = new StringBuilder();
//            sb.append("db.");
//            sb.append(lista.get(0));
//            sb.append("aggregate([\n{");
//            sb.append("$group: {");
//            sb.append("agr: { ");
            sb.append(lista.get(1));
//            sb.append(",\n_id: {\n");
            lista.add(sb.toString());
        }else if (tip == 5) {
//            cursor = database.getCollection(lista.get(0)).find(Document.parse(lista.get(1))).projection(Document.parse(lista.get(2))).sort(Document.parse(lista.get(3))).iterator();

//            for (String s : lista) {
//                cursor = database.getCollection(lista.get(0)).find(Document.parse(s)).iterator();
//            }
        }



        List<Document> dokumenti = new ArrayList<>();

        while (cursor.hasNext()) {
            Document d = cursor.next();
            dokumenti.add(d);
            System.out.println(d.toJson());
        }

        for(Document d : dokumenti){
            Row row = new Row();
            row.setName(lista.get(0));

            DocumentResult resultSetMetaData = new DocumentResult(d);

            for (int i = 1; i<=resultSetMetaData.getColumnCount(); i++){
                row.addField(resultSetMetaData.getColumnLabel(i), resultSetMetaData.getString(i));
            }
            rows.add(row);
        }

        connection.close();
        return rows;
    }

}

