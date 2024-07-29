import org.opencv.core.Core;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Main extends JFrame {

    JPanel panel;
    Register register ;
    Main() {
        window();

    }

    public void window() {
        // Create the buttons
        JButton button1 = new JButton("Register");
        JButton button2 = new JButton("Attendance");

        // Create a panel with FlowLayout for horizontal arrangement
        panel = new JPanel();
        panel.setLayout(new FlowLayout(FlowLayout.CENTER)); // Align to the right

        // Add buttons to the panel
        panel.add(button1);
        panel.add(button2);

        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                register = new Register();
            }
        });

        // Set up the main frame
        setLayout(new BorderLayout());
        add(panel, BorderLayout.SOUTH); // Add the panel to the SOUTH position

        // Frame settings
        setTitle("Smart_Attendance");
        setSize(600, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Ensure the application closes on exit
        setVisible(true);
    }

    public static void main(String[] args) {
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
        new Main();
    }
}
