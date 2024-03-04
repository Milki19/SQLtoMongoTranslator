package parser.clause;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Requirement {
    private String column;
    private String operation;
    private String value;
    public Requirement(String column, String operation, String value){
        this.column = column;
        this.operation = operation;
        this.value = value;
    }
}
