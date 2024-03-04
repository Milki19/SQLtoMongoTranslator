package validator.rules;

import parser.clause.Clause;
import parser.clause.clauses.JoinClause;
import parser.clause.clauses.OnClause;
import parser.clause.clauses.SelectClause;
import parser.clause.clauses.UsingClause;

import java.util.List;

public class FourthRule implements Rule{

    @Override
    public boolean checkRule(List<Clause> clauses) {

        for (int i = 0; i < clauses.size(); i++) {
            if (clauses.get(i) instanceof JoinClause) {
                if (!(clauses.get(i + 1) instanceof UsingClause ||
                        clauses.get(i + 1) instanceof OnClause)) {
                    System.err.println("Join upit nema uslov za spajanje using ili on\nMolim Vas dodajte ga.\n");
                    return false;
                }
            }

        }


        System.out.println("Proslo cetvrto!\n");

        return true;
    }
}
