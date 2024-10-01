package org.example;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UpdatePanel extends JFrame {
    DataBaseFunctions dbf = new DataBaseFunctions();
    public UpdatePanel(int id) {
        JPanel result = dbf.UpdateStudent(id);
    }
}
