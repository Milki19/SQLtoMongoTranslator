package parser.clause.clauses;

import lombok.Getter;
import lombok.Setter;
import parser.SQLQ;
import parser.clause.Clause;

@Getter
@Setter

public class CountClause implements Clause {

    private String keyword;

    private String something;

    public CountClause (String keyword, String something) {
        this.keyword = keyword;
        this.something = something;
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
