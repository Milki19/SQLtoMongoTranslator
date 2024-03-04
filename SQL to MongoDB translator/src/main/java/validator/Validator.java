package validator;

import parser.SQLQ;
import parser.clause.Clause;
import validator.rules.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Validator {

    private FirstRule firstRule;
    private SecondRule secondRule;
    private ThirdRule thirdRule;
    private FourthRule fourthRule;
    private Rule rule;
    private List<Clause> klauzuli;
    private List<Rule> ruleList;

    public Validator () {
        klauzuli = SQLQ.getInstance().getClauses();
        ruleList = new ArrayList<>(Arrays.asList(new FirstRule(), new SecondRule(), new ThirdRule(), new FourthRule()));

    }

    public boolean validate (SQLQ instance) {

        for (Rule rule : ruleList) {
            if (rule.checkRule(klauzuli) == false) {
                return false;
            }

        }

        return true;
    }

}
