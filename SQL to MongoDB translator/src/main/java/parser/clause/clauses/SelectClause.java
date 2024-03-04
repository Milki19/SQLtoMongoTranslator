package parser.clause.clauses;

import lombok.Getter;
import lombok.Setter;
import parser.SQLQ;
import parser.clause.Clause;

import java.util.List;

@Getter
@Setter
public class SelectClause implements Clause {

    private String keyword;
    private List<String> elements;

    public SelectClause (String keyword, List<String> elements) {
        this.keyword = keyword;
        this.elements = elements;
    }

    @Override
    public void extract() {
        SQLQ.getInstance().getClauses().add(this);
    }

    @Override
    public String write() {
        StringBuilder sb = new StringBuilder();
        sb.append(keyword).append(" ");

        for(String s : elements){
            sb.append(s);
            sb.append(",");
        }
        return sb.toString();
    }
}
