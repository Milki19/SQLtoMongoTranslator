package app;

import database.Database;
import database.MongoDataBase;
import database.settings.Settings;
import database.settings.SettingsImplementation;
import gui.MainFrame;
import gui.table.TableModel;
import lombok.Getter;
import lombok.Setter;

import javax.swing.tree.DefaultTreeModel;
import java.sql.SQLException;
import java.util.List;

@Getter
@Setter

public class AppCore {

    private Database database;
    private Settings settings;
    private TableModel tableModel;
    //private DefaultTreeModel defaultTreeModel;

    protected Gui gui;

    protected MainFrame mainFrame;



    public void run () {
        this.gui.start();
    }

    public void initialise(Gui gui) {
        this.gui = gui;
    }

    public AppCore() throws SQLException {
        tableModel = new TableModel();
        this.settings = initSettings();
        this.database = new MongoDataBase(this.settings);
    }
    private Settings initSettings() {
        Settings settingsImplementation = new SettingsImplementation();
        settingsImplementation.addParameter("mongodb_ip", "134.209.239.154");
        settingsImplementation.addParameter("mongodb_database",  "bp_tim6");
        settingsImplementation.addParameter("mongodb_username", "writer");
        settingsImplementation.addParameter("mongodb_password", "V48nMGJx1mwcRIXB");

        return settingsImplementation;
    }

    public void readDataFromTable(List<String> lista) throws SQLException {
        tableModel.setRows(this.database.getDataFromTable(lista));
    }

}
