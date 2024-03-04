package parser.clause.clauses;

import lombok.Getter;
import lombok.Setter;
import parser.SQLQ;
import parser.clause.Clause;

@Getter
@Setter

public class LikeClause implements Clause {

    private String keyword;
    private String what;
    private String like;

    public LikeClause (String keyword, String what, String like) {
        this.keyword = keyword;
        this.what = what;
        this.like = like;
    }

    @Override
    public void extract() {
        SQLQ.getInstance().getClauses().add(this);
    }

    @Override
    public String write() {
        return what + " " + like;
    }
}
