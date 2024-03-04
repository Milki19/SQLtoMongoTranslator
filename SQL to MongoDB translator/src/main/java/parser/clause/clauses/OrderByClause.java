package parser.clause.clauses;

import lombok.Getter;
import lombok.Setter;
import parser.SQLQ;
import parser.clause.Clause;

import java.util.List;

@Getter
@Setter

public class OrderByClause implements Clause {
    private List<String> column;
    private List<String> order;
    private String keyword;

    public OrderByClause(String keyword, List<String> column, List<String> order){
        this.keyword = keyword;
        this.column = column;
        this.order = order;
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
