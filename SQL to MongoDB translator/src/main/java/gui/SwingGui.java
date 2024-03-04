package gui;

import app.AppCore;
import app.Gui;

public class SwingGui implements Gui {

    private MainFrame mainFrame;
    private AppCore appCore;

    public SwingGui(AppCore appCore){
        this.appCore = appCore;
    }
    @Override
    public void start() {
        mainFrame = MainFrame.getInstance();
        mainFrame.setAppCore(appCore);
        mainFrame.setVisible(true);
    }
}
