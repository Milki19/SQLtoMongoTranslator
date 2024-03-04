package parser.clause.clauses;

import parser.SQLQ;
import parser.clause.Clause;

public class UsingClause implements Clause {

    private String keyword;


    public UsingClause (String keyword) {
        this.keyword = keyword;
    }

    @Override
    public void extract() {
        SQLQ.getInstance().getClauses().add(this);
    }

    @Override
    public String write() {
        return null;
    }
}
