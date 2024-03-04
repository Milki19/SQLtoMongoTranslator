package parser.clause.clauses;

import gui.MainFrame;
import lombok.Getter;
import lombok.Setter;
import parser.Parser;
import parser.SQLQ;
import parser.clause.Clause;
import parser.clause.Operation;
import parser.clause.Requirement;
import java.util.List;

@Getter
@Setter
public class WhereClause implements Clause {

    private List<Requirement> requirements;
    private Operation operation;
    private String keyword;
    private LikeClause likeClause;
    public WhereClause(String keyword, List<Requirement> requirements, LikeClause likeClause) {
        this.keyword = keyword;
        this.requirements = requirements;
        this.likeClause = likeClause;
    }

    @Override
    public void extract() {
        SQLQ.getInstance().getClauses().add(this);
    }

    @Override
    public String write() {
        StringBuilder sb = new StringBuilder();
        sb.append(keyword).append(" ");


        for(Requirement r : requirements){
            sb.append(r.getColumn()).append(" ");
            sb.append(r.getOperation()).append(" ");
            sb.append(r.getValue()).append(" ");
        }

        return sb.toString();
    }
}
