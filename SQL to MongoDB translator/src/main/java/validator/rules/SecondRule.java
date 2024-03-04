package validator.rules;

import parser.clause.Clause;
import parser.clause.clauses.SelectClause;
import parser.clause.clauses.WhereClause;

import java.util.List;

public class SecondRule implements Rule{

    private String[] aggregation = {"min, max, avg"};


    //Pogledaj ponovo
    @Override
    public boolean checkRule(List<Clause> clauses) {

        for (Clause c : clauses) {
            if (c instanceof SelectClause) {
                SelectClause selectClause = (SelectClause) c;
                for (String s : aggregation) {
                    if (!(selectClause.getElements().contains(s))) {
                        System.out.println("Proslo drugo!\n");
                        return true;
                    }
                }

            }
        }

        System.err.println("Sve selektovane stvari nisu usle u group by.\nMolim Vas da ispravite gresku.\n");

        return false;
    }
}
