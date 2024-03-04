package gui;

import adapter.Adapter;
import adapter.AdapterImplementation;
import app.AppCore;
import gui.table.TableModel;
import lombok.Getter;
import lombok.Setter;
import parser.Parser;
import parser.SQLQ;
import validator.Validator;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

@Getter
@Setter
public class MainFrame extends JFrame {

    private AppCore appCore;
    private static MainFrame instance = null;
    private TextArea textArea;
    private JButton jbRun;
    private JTable jTable;
    private String text;

    public static MainFrame getInstance () {

        if (instance == null) {
            instance = new MainFrame();
            instance.initialise();
        }

        return instance;
    }

    public void initialise () {

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setPreferredSize(new Dimension(1280, 720));


        jbRun = new JButton("RUN");
        jbRun.setPreferredSize(new Dimension(50, 50));

        textArea = new TextArea();
        this.add(textArea, BorderLayout.CENTER);

        jbRun.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (textArea == null || textArea.getSelectedText().isEmpty()) {
                    return;
                }

                SQLQ.getInstance().getClauses().clear();
                SQLQ.getInstance().getAndOrClause().clear();

                Validator validator = new Validator();
                Parser parser = new Parser(textArea.getSelectedText());
                Adapter adapter = new AdapterImplementation();
                SQLQ query = parser.extract();


                //parser.extract();


                if (validator.validate(query)) {
                    try {

                        appCore.readDataFromTable(adapter.getMongo(query));
                    } catch (SQLException ex) {
                        throw new RuntimeException(ex);
                    }
                }
            }
        });

        this.add(jbRun, BorderLayout.NORTH);

        jTable = new JTable();
        jTable.setPreferredScrollableViewportSize(new Dimension(500, 400));
        jTable.setFillsViewportHeight(true);
        this.add(new JScrollPane(jTable), BorderLayout.SOUTH);

        this.pack();
        this.setLocationRelativeTo(null);

    }
    public TextArea getTextArea () {
        return textArea;
    }

    public void setAppCore(AppCore appCore) {
        this.appCore = appCore;
        this.jTable.setModel(appCore.getTableModel());
    }

}
