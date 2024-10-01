package org.example;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class DataBaseFunctions {

    String url = "jdbc:mysql://localhost:3306/studentdata?user=root&password=root123";

    String[] genders = {"Male", "Female", "Others"};
    String[] courses = {"Computer Applications", "CSE", "Pharma", "MBA"};

    public void AddStudent(String name, int age, String gender, String contactNo, String email, String course) {

        try {
            Connection con = DriverManager.getConnection(url);
            String query = "INSERT INTO student_info (name, age, gender, contactNo, email, course) VALUES(?,?,?,?,?,?)";

            PreparedStatement ps = con.prepareStatement(query);
            ps.setString(1, name);
            ps.setInt(2, age);
            ps.setString(3, gender);
            ps.setString(4, contactNo);
            ps.setString(5, email);
            ps.setString(6, course);

            int rowsaffected = ps.executeUpdate();

            if (rowsaffected > 0) {
                JOptionPane.showMessageDialog(null, "Student Data Inserted.");
            }
        } catch (SQLException sqle) {
            sqle.printStackTrace();
        }
    }

    public JPanel SearchStudent(int id) {

        JPanel resultPanel = new JPanel(new GridLayout(0, 1, 5, 5));

        try {
            Connection con = DriverManager.getConnection(url);
            String query = "SELECT * FROM student_info WHERE id = ?";

            PreparedStatement ps = con.prepareStatement(query);
            ps.setInt(1, id);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                resultPanel.add(new JLabel("ID: " + String.valueOf(rs.getInt(1))));
                resultPanel.add(new JLabel("Name: " + rs.getString(2)));
                resultPanel.add(new JLabel("Age: " + String.valueOf(rs.getInt(3))));
                resultPanel.add(new JLabel("Gender: " + rs.getString(4)));
                resultPanel.add(new JLabel("Contact No: " + rs.getString(5)));
                resultPanel.add(new JLabel("Email: " + rs.getString(6)));
                resultPanel.add(new JLabel("Course: " + rs.getString(7)));

                JPanel buttonPanel = new JPanel(new GridLayout(2, 1));

                JButton updateBtn = new JButton("Update");
                buttonPanel.add(updateBtn);
                updateBtn.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        resultPanel.removeAll();
                        JPanel result = UpdateStudent(id);
                        resultPanel.add(result);
                        resultPanel.revalidate();
                        resultPanel.repaint();
                    }
                });

                JButton deleteBtn = new JButton("Delete");
                buttonPanel.add(deleteBtn);
                deleteBtn.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        DeleteStudent(id);
                    }
                });

                resultPanel.add(updateBtn);
                resultPanel.add(deleteBtn);

            } else {
                //resultPanel.add(new JLabel("No Results"));
                JOptionPane.showMessageDialog(null, "No Results Found");
            }
        } catch (SQLException sqle) {
            sqle.printStackTrace();
        }
        return resultPanel;
    }

    public JPanel UpdateStudent(int id) {
        JPanel resultPanel = new JPanel(new GridLayout(0, 1, 5, 5));

        JComboBox<String> genderOptions = new JComboBox<>(genders);
        JComboBox<String> courseOption = new JComboBox<>(courses);

        try {
            Connection con = DriverManager.getConnection(url);
            String query = "SELECT * FROM student_info WHERE id = ?";

            PreparedStatement ps = con.prepareStatement(query);
            ps.setInt(1, id);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                int studentId = rs.getInt(1);
                resultPanel.add(new JLabel("Student Id: " + studentId));

                JTextField studentName = new JTextField(rs.getString(2));
                resultPanel.add(studentName);

                JTextField studentAge = new JTextField(rs.getString(3));
                resultPanel.add(studentAge);

                String currentgender = rs.getString(4);
                genderOptions.setSelectedItem(currentgender);
                resultPanel.add(genderOptions);

                JTextField studentContact = new JTextField(rs.getString(5));
                resultPanel.add(studentContact);

                JTextField studentEmail = new JTextField(rs.getString(6));
                resultPanel.add(studentEmail);

                String currentcourse = rs.getString(7);
                courseOption.setSelectedItem(currentcourse);
                resultPanel.add(courseOption);

                JButton SaveUpdates = new JButton("Save");
                resultPanel.add(SaveUpdates);


                SaveUpdates.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        try {
                            Connection con = DriverManager.getConnection(url);
                            String query = "UPDATE student_info SET name = ?, age = ?, gender = ?, contactNo = ?, email = ?, course = ? WHERE id = ?";

                            PreparedStatement ps = con.prepareStatement(query);

                            String name = studentName.getText();
                            ps.setString(1, name);

                            String age = studentAge.getText();
                            ps.setInt(2,Integer.parseInt(age));

                            String gender = (String) genderOptions.getSelectedItem();
                            ps.setString(3, gender);

                            String contact = studentContact.getText();
                            ps.setString(4, contact);

                            String email = studentEmail.getText();
                            ps.setString(5, email);

                            String course = (String) courseOption.getSelectedItem();
                            ps.setString(6, course);

                            // Improve this
                            ps.setInt(7, studentId);

                            int rowsAffected = ps.executeUpdate();

                            if (rowsAffected > 0) {
                                JOptionPane.showMessageDialog(null, "Data Updated");
                            }
                        } catch(SQLException sqle) {
                            sqle.printStackTrace();
                        }
                    }
                });

                /*
                JButton cancel = new JButton("cancel");
                resultPanel.add(cancel);

                cancel.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        SearchPanel search = new SearchPanel();
                        search.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                    }
                });
                 */

            } else {
                JOptionPane.showMessageDialog(null, "Unable to update Data.");
            }
        } catch (SQLException sqle) {
            sqle.printStackTrace();
        }
        return resultPanel;
    }

    public void DeleteStudent(int id) {
        try {
            Connection con = DriverManager.getConnection(url);

            String query = "DELETE FROM student_info WHERE id = ?";
            PreparedStatement ps = con.prepareStatement(query);
            ps.setInt(1, id);
            int rowAffected = ps.executeUpdate();

            if (rowAffected > 0) {
                JOptionPane.showMessageDialog(null,"Deleted Student");
            }
        } catch (SQLException sqle) {
            sqle.printStackTrace();
        }
    }
}
