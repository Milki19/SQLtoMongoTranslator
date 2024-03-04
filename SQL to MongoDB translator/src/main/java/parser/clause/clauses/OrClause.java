package parser.clause.clauses;

import parser.SQLQ;
import parser.clause.Clause;

public class OrClause implements Clause {

    private String keyword;

    public OrClause (String keyword) {
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
