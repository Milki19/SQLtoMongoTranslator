package parser.clause.clauses;

import parser.SQLQ;
import parser.clause.Clause;

public class InClause implements Clause {

    private String keyword;

    private String domain;

    public InClause (String keyword, String domain) {
        this.keyword = keyword;
        this.domain = domain;
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
