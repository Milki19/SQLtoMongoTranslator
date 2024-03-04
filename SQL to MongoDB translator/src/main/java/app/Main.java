package app;

import gui.MainFrame;
import gui.SwingGui;
import parser.Parser;

import java.sql.SQLException;

public class Main {


    public static void main(String[] args) throws SQLException {
        AppCore appCore = new AppCore();
        Gui gui = new SwingGui(appCore);

        appCore.initialise(gui);

        //System.out.println(Parser.getInstance().getString());

        appCore.run();
    }

}
