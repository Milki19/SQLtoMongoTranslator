package validator.rules;

import parser.clause.Clause;

import java.util.List;

public interface Rule {

    public boolean checkRule(List<Clause> clauses);

}
