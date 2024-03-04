package adapter;

import parser.SQLQ;

import java.util.List;

public interface Adapter {

    public List<String> getMongo(SQLQ instance);

}
