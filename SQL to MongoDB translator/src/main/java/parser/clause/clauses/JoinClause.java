package parser.clause.clauses;

import lombok.Getter;
import lombok.Setter;
import parser.SQLQ;
import parser.clause.Clause;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class JoinClause implements Clause {

    private String keyword;
    private String what;

    private String usingWhat;

    //private List<String> whatsJoinin;


    public JoinClause (String keyword, String what, String usingWhat) {
        this.keyword = keyword;
        this.what = what;
        this.usingWhat = usingWhat;
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
