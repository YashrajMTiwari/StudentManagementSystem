package org.example;

import jdk.dynalink.linker.GuardedInvocation;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SearchPanel extends JFrame {

    DataBaseFunctions dbf = new DataBaseFunctions();
    public SearchPanel() {

        JPanel panel = new JPanel();

        panel.add(new Label("Search by ID:"));
        JTextField searchField = new JTextField(20);
        panel.add(searchField);

        JButton searchBtn = new JButton("Search");
        panel.add(searchBtn);

        JPanel resultPanel = new JPanel(new GridLayout(2, 1));
        panel.add(resultPanel);
        searchBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int id = Integer.parseInt(searchField.getText());
                resultPanel.removeAll();
                JPanel result = dbf.SearchStudent(id);
                resultPanel.add(result);
                resultPanel.revalidate();
                resultPanel.repaint();
            }
        });

        add(panel);
        setTitle("Search Student");
        setSize(480, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setResizable(false);
        setLocationRelativeTo(null);
        setVisible(true);
    }
}
