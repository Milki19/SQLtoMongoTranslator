package parser;

import lombok.Getter;
import lombok.Setter;
import parser.clause.Clause;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Getter
@Setter
public class SQLQ {

    private List<Clause> clauses;
    private List<String> andOrClause;
//    private Map<String, String> mapa;
    private int tip;
    private static SQLQ instance = null;

    private SQLQ(){
        clauses = new ArrayList<>();
        andOrClause = new ArrayList<>();
//        mapa = new HashMap<>();
//        maps(mapa);
    }

//    private void maps(Map<String, String> mapa){
//        mapa.put("first_name", "employees");
//        mapa.put("last_name", "employees");
//        mapa.put("department_name", "departments");
//    }

    public static SQLQ getInstance(){
        if(instance == null){
            instance = new SQLQ();
        }
        return instance;
    }
}
