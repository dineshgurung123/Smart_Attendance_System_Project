import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import  java.sql.*;
import java.util.Scanner;
import java.util.Scanner.*;

public class RegisterInfo {

    private JFrame frame;
    private JTextField nameField;
    private JTextField idField;
    private JTextField phoneField;
    private JTextField addressField;
    private JButton submitButton;
    private JButton clearButton;

    public RegisterInfo() {
        // Create and set up the window
        frame = new JFrame("Registration Page");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 500);

        // Load the background image
        ImageIcon backgroundIcon = new ImageIcon("image/register.jpg");
        JLabel background = new JLabel(backgroundIcon);
        background.setSize(800, 800);
        frame.setContentPane(background);
        background.setLayout(null);

        // Set the frame location to the center of the screen
        frame.setLocationRelativeTo(null);

        // Create and add components
        JLabel nameLabel = new JLabel("Name:");
        nameLabel.setBounds(50, 50, 100, 30);
        nameField = new JTextField();
        nameField.setBounds(200, 50, 300, 30);

        JLabel idLabel = new JLabel("ID:");
        idLabel.setBounds(50, 100, 100, 30);
        idField = new JTextField();
        idField.setBounds(200, 100, 300, 30);

        JLabel phoneLabel = new JLabel("Phone no:");
        phoneLabel.setBounds(50, 150, 100, 30);
        phoneField = new JTextField();
        phoneField.setBounds(200, 150, 300, 30);

        JLabel addressLabel = new JLabel("Address:");
        addressLabel.setBounds(50, 200, 100, 30);
        addressField = new JTextField();
        addressField.setBounds(200, 200, 300, 30);

        submitButton = new JButton("Submit");
        submitButton.setBounds(200, 300, 100, 30);
        clearButton = new JButton("Clear");
        clearButton.setBounds(400, 300, 100, 30);

        // Add components to the background
        background.add(nameLabel);
        background.add(nameField);
        background.add(idLabel);
        background.add(idField);
        background.add(phoneLabel);
        background.add(phoneField);
        background.add(addressLabel);
        background.add(addressField);
        background.add(submitButton);
        background.add(clearButton);

        // Set button action listeners
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handleSubmit();
            }
        });

        clearButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handleClear();
            }
        });

        // Display the window
        frame.setVisible(true);
    }

    private void handleSubmit() {
        String Name = nameField.getText();
        String Id = idField.getText();
        String Address = addressField.getText();
        String Phone_no = phoneField.getText();

        // Handle form submission logic here



           try{

               Connection con ;

               // Load the driver
               Class.forName("com.mysql.cj.jdbc.Driver");

               // Connection to the database
               String url = "jdbc:mysql://localhost:3306/smart_attendance";
               String username = "root";
               String password = "sspear54321";


                con = DriverManager.getConnection(url, username, password)   ;

            PreparedStatement ps = con.prepareStatement("insert into student(Name, Id, Address, Phone_no) values(?,?,?,?)");
            ps.setString(1, Name);
            ps.setString(2, Id);
            ps.setString(3, Address);
            ps.setString(4, Phone_no);
            ps.executeUpdate();



           }catch (Exception e){
               System.out.println(e.getStackTrace());
           }

    }

    private void handleClear() {
        nameField.setText("");
        idField.setText("");
        addressField.setText("");
        phoneField.setText("");
    }

    public static void main(String[] args) {
        new RegisterInfo();
    }
}
