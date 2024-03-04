package parser;

import lombok.Getter;
import lombok.Setter;
import parser.clause.Clause;
import parser.clause.Requirement;
import parser.clause.clauses.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Getter
@Setter
public class Parser {

    private List<String> comparables;
    private String string;
    private String[] keywords = {"count", "from", "group", "in", "join", "like", "on", "order", "select", "using", "where"};


    public Parser(String string) {
        comparables = new ArrayList<>(Arrays.asList("<", ">", "=", "<=", ">=", "in"));
        this.string = string;
    }

    public SQLQ extract () {


        /// 0 1 2 3 (4)
        String[] niz = string.split("[, ]");
        int n = niz.length;
        for (int i = 0; i < n; i++) {
            if(niz[i].equalsIgnoreCase("select")){
                List<String> elements = new ArrayList<>();
                for(int j = i + 1; j < n; j++){
                    if (isKeyword(niz[j])) {
                        break;
                    }
                    if(!(niz[j].equals(""))){
                        elements.add(niz[j]);
                    }

                }
                SelectClause selectClause = new SelectClause("select", elements);
                selectClause.extract();
//                System.out.println(selectClause.write());
            }else if(niz[i].equalsIgnoreCase("where")){
                List<Requirement> requirements = new ArrayList<>();
                Requirement requirement1 = new Requirement(niz[i+1], niz[i+2], niz[i+3]);
                requirements.add(requirement1);
                LikeClause likeClause = null;

                if(niz[i+2].equalsIgnoreCase("like")){
                    likeClause = new LikeClause("like", niz[i+1], niz[i+3]);
                    likeClause.extract();
                    likeClause.write();
                }

                if(i+4 != n){
                    if (niz[i + 4].equalsIgnoreCase("and") || niz[i + 4].equalsIgnoreCase("or")) {
                        Requirement requirement2 = new Requirement(niz[i + 5], niz[i + 6], niz[i + 7]);
                        requirements.add(requirement2);

                        SQLQ.getInstance().getAndOrClause().add(niz[i+4]);
                    }
                }

                if(i+8 != n && i+4 != n){
                    if (niz[i + 8].equalsIgnoreCase("and") || niz[i + 8].equalsIgnoreCase("or")) {
                        Requirement requirement3 = new Requirement(niz[i + 9], niz[i + 10], niz[i + 11]);
                        requirements.add(requirement3);

                        SQLQ.getInstance().getAndOrClause().add(niz[i+8]);
                    }
                }

                WhereClause whereClause = new WhereClause("where", requirements, likeClause);
                whereClause.extract();
            }else if(niz[i].equalsIgnoreCase("group") && niz[i+1].equalsIgnoreCase("by")){
                List<String> column = new ArrayList<>();
//                i += 2;
                while (!(isKeyword(niz[i]))){
                    column.add(niz[i]);
                    i++;
                }
                GroupByClause groupByClause = new GroupByClause("group", column);
                groupByClause.extract();
            }else if(niz[i].equalsIgnoreCase("from")){
                String db = "";
                db = niz[i+1];
                FromClause fromClause = new FromClause("from", db);
                fromClause.extract();
            }else if(niz[i].equalsIgnoreCase("join")){
                JoinClause joinClause = new JoinClause("join", niz[i + 1], niz[i + 3]);
                joinClause.extract();
                    if (niz[i+2].equalsIgnoreCase("using")) {
                        UsingClause usingClause = new UsingClause("using");
                        usingClause.extract();
                    }else if (niz[i+2].equalsIgnoreCase("on")) {
                        OnClause onClause = new OnClause("on");
                        onClause.extract();
                    }

            }else if(niz[i].contains("count")){
                String[] smth = niz[i].split("[(,)]");

                String something = niz[1];

                CountClause countClause = new CountClause("count", something);
                countClause.extract();
            }else if(niz[i].equalsIgnoreCase("in")){
                String something = "";
                if(niz[i+1].contains("(")){
                    int cnt = 2;
                    if(!(niz[i+2].contains(")"))){
                        i++;
                        cnt++;
                    }

                }
                InClause inClause = new InClause("in", "string");
                inClause.extract();
            }else if(niz[i].equalsIgnoreCase("on")) {
                OnClause onClause = new OnClause("on");
                onClause.extract();
            }else if(niz[i].equalsIgnoreCase("order") && niz[i+1].equalsIgnoreCase("by")) {
                List<String> columns = new ArrayList<>();
                List<String> orders = new ArrayList<>();
                i+=2;

                while (i < n && !isKeyword(niz[i])) {
                    columns.add(niz[i++]);
                    orders.add(niz[i++]);
                    i++;
                }

                OrderByClause orderByClause = new OrderByClause("order by", columns, orders);
                orderByClause.extract();
            }
        }

        //System.out.println(SQLQ.getInstance().getClauses());
        return SQLQ.getInstance();

    }

    private boolean isKeyword(String word) {
        for (String keyword : keywords) {
            if (keyword.equalsIgnoreCase(word)) {
                return true;
            }
        }
        return false;
    }

}