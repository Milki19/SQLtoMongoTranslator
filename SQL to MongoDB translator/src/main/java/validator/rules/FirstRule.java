package validator.rules;

import parser.clause.Clause;
import parser.clause.clauses.FromClause;
import parser.clause.clauses.SelectClause;
import parser.clause.clauses.WhereClause;

import java.util.List;

public class FirstRule implements Rule{


    @Override
    public boolean checkRule(List<Clause> clauses) {
        int select_flag = 0, from_flag = 0;

        for (Clause c : clauses) {
            if (c instanceof SelectClause)
                select_flag = 1;

            if (c instanceof FromClause)
                from_flag = 1;
        }

        if (select_flag == 1 && from_flag == 1) {
            System.out.println("Proslo prvo!\n");
            return true;
        }
        System.err.println("Upit mora imati sve obavezne delove!!!\nMora imati select i from.\n");


        return false;
    }
}
