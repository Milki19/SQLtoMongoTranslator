package validator.rules;

import parser.clause.Clause;
import parser.clause.Requirement;
import parser.clause.clauses.SelectClause;
import parser.clause.clauses.WhereClause;

import java.util.List;

public class ThirdRule implements Rule{

    private String[] aggregation = {"min, max, avg"};
    @Override
    public boolean checkRule(List<Clause> clauses) {

        for (Clause c : clauses) {
            if (c instanceof WhereClause) {
                WhereClause whereClause = (WhereClause) c;
                for (String s : aggregation) {
                    for(Requirement r : whereClause.getRequirements()){
                        if(r.getValue().equalsIgnoreCase(s)){
                            System.err.println("U where iskazu pronadjena je funkcija agregacije!\nMolimo izbacite je!\n");
                            return false;
                        }
                    }
                }


            }
        }


        System.out.println("Proslo trece\n");

        return true;
    }
}
