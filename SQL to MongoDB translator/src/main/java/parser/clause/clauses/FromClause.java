package parser.clause.clauses;

import lombok.Getter;
import lombok.Setter;
import parser.SQLQ;
import parser.clause.Clause;

import java.util.List;

@Getter
@Setter
public class FromClause implements Clause {

    private String keyword;

    private String db;
    public FromClause (String keyword, String db) {
        this.keyword = keyword;
        this.db = db;
    }

    @Override
    public void extract() {
        SQLQ.getInstance().getClauses().add(this);
    }

    @Override
    public String write() {
        StringBuilder sb = new StringBuilder();
        sb.append(keyword);
        sb.append(" ");
        sb.append(db).append(" ");

        return sb.toString();
    }
}