package parser.clause.clauses;

import lombok.Getter;
import lombok.Setter;
import parser.SQLQ;
import parser.clause.Clause;

import java.util.List;

@Getter
@Setter

public class GroupByClause implements Clause {

    private String keyword;
    private List<String> columns;

    public GroupByClause (String keyword, List<String> columns) {
        this.keyword = keyword;
        this.columns = columns;
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
