package adapter;

import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import parser.SQLQ;
import parser.clause.Clause;
import parser.clause.Requirement;
import parser.clause.clauses.*;

import org.bson.Document;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class AdapterImplementation implements Adapter {

    private int tip = 1;

    private String count = "";
    private String from = "";
    private String groupBy = "";
    private String in = "";
    private String join = "";
    private String like = "";
    private String on = "";
    private String or = "";

    private String select;

    private String orderBy = "";
    private WhereClause wc;

    private List<Requirement> requirements = new ArrayList<>();

    public void parameterConverter() {
        List<Clause> clauses = SQLQ.getInstance().getClauses();
//        Map<String, String> mapa = SQLQ.getInstance().getMapa();

        for (Clause clause : clauses) {

            if (clause instanceof WhereClause) {
                wc = (WhereClause) clause;
                requirements.addAll(wc.getRequirements());
            } else if (clause instanceof CountClause) {
                count = ((CountClause) clause).getSomething();
            } else if (clause instanceof FromClause) {
                from = ((FromClause) clause).getDb();
            } else if (clause instanceof GroupByClause) {
                tip = 4;

                GroupByClause gb = (GroupByClause) clause;
                List<String> columns = gb.getColumns();
                StringBuilder sb = new StringBuilder();
                sb.append("{");

                for (int i = 0; i < columns.size(); i++) {
                    sb.append("\"").append(columns.get(i)).append("\": \"$_id.").append(columns.get(i)).append("\"");

                    if (i < columns.size() - 1) {
                        sb.append(", ");
                    }
                }

                sb.append("}");
                groupBy = sb.toString();

            } else if (clause instanceof InClause) {
            } else if (clause instanceof JoinClause) {

                tip = 2;

                JoinClause jc = (JoinClause) clause;
                StringBuilder sb = new StringBuilder();
                String usingWhat = jc.getUsingWhat().replace("(", "");
                usingWhat = jc.getUsingWhat().replace(")", "");

                String s1 = usingWhat.replace("(", "");
                String s2 = s1.replace(")", "");

                sb.append("{\n" +
                        "$lookup: {\n" +
                        "from: \""+s1+"\", " +
                        "foreignField:\""+ s2 + "\", " +
                        "localField: \"" + s2 + "\", " +
                        ",as:\"a\"" +
                        "}, {$unwind: \"$a\"}");

                join = sb.toString();

//                System.out.println(join);

            } else if (clause instanceof LikeClause) {

            } else if (clause instanceof OnClause) {
            } else if (clause instanceof OrClause) {
            } else if (clause instanceof OrderByClause) {
                OrderByClause orderByClause = (OrderByClause) clause;
                List<String> columns = orderByClause.getColumn();
                List<String> orders = orderByClause.getOrder();

                StringBuilder sb = new StringBuilder();
                sb.append("{");

                for (int i = 0; i < columns.size(); i++) {
                    sb.append("\"").append(columns.get(i)).append("\"");
                    if (orders.get(i).equalsIgnoreCase("asc")) {
                        sb.append(": 1");
                    } else {
                        sb.append(": -1");
                    }

                    if (i < columns.size() - 1) {
                        sb.append(", ");
                    }
                }

                sb.append("}");

                orderBy = sb.toString();
            } else if (clause instanceof SelectClause) {
                SelectClause selectClause = (SelectClause) clause;
                List<String> elements = selectClause.getElements();

                StringBuilder sb = new StringBuilder();
                sb.append("{");

                for (int i = 0; i < elements.size(); i++) {
                    String[] niz;
                    String novo;
                    String element = elements.get(i);
                    //first_name    last_name   department_name     Key
                    //employees     employees   departments         Value

//                    System.out.println(mapa.get(element) + "," + from + "\n");

                    //join upit nije radio zbog from-a ispod

//                    if(mapa.get(element).equalsIgnoreCase(from)){
//                        novo = "\"a." + element + "\": 1, ";
//                        System.out.println(mapa.get(element) + "\n");
//                        System.out.println(from + "\n");
//                        sb.append(novo);
//                        continue;
//
//                    }

//                    if (element.toLowerCase().startsWith("min(")) {
//                        sb.append("\"").append(element.substring(4, element.length() - 1)).append("\": {\"$min\": \"$").append(element.substring(4, element.length() - 1)).append("\"}");
//                        tip = 4;
//                    } else if (element.toLowerCase().startsWith("max(")) {
//                        sb.append("\"").append(element.substring(4, element.length() - 1)).append("\": {\"$max\": \"$").append(element.substring(4, element.length() - 1)).append("\"}");
//                        tip = 4;
//                    } else if (element.toLowerCase().startsWith("avg(")) {
//                        sb.append("\"").append(element.substring(4, element.length() - 1)).append("\": {\"$avg\": \"$").append(element.substring(4, element.length() - 1)).append("\"}");
//                        tip = 4;
//                    }

                    sb.append("\"");

                    if(element.contains(".")){
                        niz = element.split("\\.");
                        novo = "a." + niz[1];
                        sb.append(novo).append("\": 1");
                        continue;
                    }

                    sb.append(elements.get(i)).append("\": 1");

                    if (i < elements.size() - 1) {
                        sb.append(", ");
                    }
                }

                sb.append("\"_id\": 0}");
                sb.append("}");

//                System.out.println(sb.toString());

                select = sb.toString();
            }
        }
    }

    public List<String> mapper() {
        List<String> list = new ArrayList<>();
        SQLQ.getInstance().setTip(tip);

        if (tip == 1) {

            // from where select order
            list.add(from);
            System.out.println(from);
            list.add(getWhereCondition());
            System.out.println(getWhereCondition());
            list.add(select);
            System.out.println(select);
            list.add(orderBy);
            System.out.println(orderBy);
        } else if (tip == 2) {

            list.add(from);
            System.out.println(from);
            list.add(join);
            System.out.println(join);
            list.add(getWhereCondition());
            System.out.println(getWhereCondition());
            list.add(select);
//            System.out.println(select);

            if(!(orderBy.isEmpty())){
                list.add(orderBy);
            }
        } else if (tip == 3) {


        } else if (tip == 4) {

            list.add(from);
            list.add(select);


        }else if(tip == 5){
            // from where select order
            list.add(from);
            list.add(getWhereCondition());
            list.add(select);

        }

        return list;
    }

    private String getWhereCondition() {

        List<String> andOrClause = SQLQ.getInstance().getAndOrClause();

        StringBuilder sb = new StringBuilder();
        sb.append("{");

        String what = "";
        String like = "";

//        if (!(wc.getLikeClause() == null)) {
//            what = wc.getLikeClause().getWhat();
//            like = wc.getLikeClause().getLike();
//            tip = 5;
//        }
        for (int i = 0; i < requirements.size(); i++) {

//                if (like.startsWith("%")) {
//                    //{ last_name: {$regex:/King$/}
//                    sb.append(" ").append(what).append(": { $regex:/").append(like).append("$/");
//                }else if(like.endsWith("%")){
//                    sb.append(" ").append(what).append(": { $regex/^").append(like).append("/");
//                } else {
//                    sb.append(" ").append(what).append(": { $regex/").append(like).append("/");
//                }

            if (andOrClause.isEmpty()) {
                    Requirement requirement = requirements.get(i);
                    sb.append("\"").append(requirement.getColumn()).append("\": ")
                            .append("\"").append(requirement.getValue()).append("\"");

                    if (i < requirements.size() - 1) {
                        sb.append(", ");
                    }
                }

            }

        sb.append("}");

        return sb.toString();
    }

    @Override
    public List<String> getMongo(SQLQ instance) {
        parameterConverter();
        return mapper();
    }
}
