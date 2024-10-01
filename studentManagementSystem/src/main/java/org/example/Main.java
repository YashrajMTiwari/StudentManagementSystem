package org.example;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Main {

    static DataBaseFunctions dbf = new DataBaseFunctions();
    public static void main(String[] args) {

        String[] genders = {"Male", "Female", "Others"};
        String[] courses = {"Computer Applications", "CSE", "Pharma", "MBA"};

        JFrame frame = new JFrame();

        JPanel mainPanel = new JPanel(new GridLayout(14, 1, 10, 10));

        mainPanel.add(new JLabel("Student Name"));
        JTextField studentNameField = new JTextField();
        mainPanel.add(studentNameField);

        mainPanel.add(new JLabel("Student Age"));
        JTextField studentAgeField = new JTextField();
        mainPanel.add(studentAgeField);

        mainPanel.add(new JLabel("Gender"));
        JComboBox<String> genderDropdown = new JComboBox<>(genders);
        mainPanel.add(genderDropdown);

        mainPanel.add(new JLabel("Contact Number"));
        JTextField studentContactNoField = new JTextField();
        mainPanel.add(studentContactNoField);

        mainPanel.add(new JLabel("Student Email"));
        JTextField studentEmailField = new JTextField();
        mainPanel.add(studentEmailField);

        mainPanel.add(new JLabel("Student Course"));
        JComboBox<String> studentCourseField = new JComboBox<>(courses);
        mainPanel.add(studentCourseField);

        JButton addButton = new JButton("Add Student");
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = studentNameField.getText();
                int age = Integer.parseInt(studentAgeField.getText());
                String gender = (String) genderDropdown.getSelectedItem();
                String contactNo = studentContactNoField.getText();
                String email = studentEmailField.getText();
                String course = (String) studentCourseField.getSelectedItem();
                dbf.AddStudent(name, age, gender, contactNo, email, course);

                studentAgeField.setText("");
                studentEmailField.setText("");
                studentNameField.setText("");
                studentAgeField.setText("");
                studentContactNoField.setText("");
            }
        });
        mainPanel.add(addButton);

        JButton searchButton = new JButton("Search Student");
        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SearchPanel search = new SearchPanel();
            }
        });
        mainPanel.add(searchButton);

        frame.add(mainPanel);
        frame.setSize(480, 720);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setTitle("Student Management System");
        frame.setVisible(true);
    }
}