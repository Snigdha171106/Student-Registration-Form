import javax.swing.*; 
import java.awt.*; 
import java.awt.event.*; 
import java.util.HashSet;

public class microproject extends JFrame 
{ 
    JTextField nameField, emailField, phoneField; 
    JPasswordField passwordField, confirmPasswordField; 
    JRadioButton maleButton, femaleButton; 
    ButtonGroup genderGroup; 
    static int userCount = 0; //limitation for registration
    static final int MAX_USERS = 2;  
    static final HashSet<String> registeredEmails = new HashSet<>();

public microproject() 
{
    setTitle("Student Registration Form");
    setSize(900,600);
    setLayout(new GridBagLayout());
    setLocationRelativeTo(null); //places the window at the center
    GridBagConstraints gbc = new GridBagConstraints();
    gbc.insets = new Insets(5, 5, 5, 5);  //sapce between neighbour components
    getContentPane().setBackground(new Color(240, 248, 255));
    Font boldFont = new Font("Arial", Font.BOLD, 15);

    gbc.gridx = 0; 
    gbc.gridy = 0; gbc.gridwidth = 2; 
    gbc.anchor = GridBagConstraints.CENTER;
    JLabel titleLabel = new JLabel("Student Registration Form"); 
    titleLabel.setFont(new Font("Arial", Font.BOLD, 18));
    titleLabel.setForeground(new Color(0, 102, 204));   // form title text color
    add(titleLabel, gbc);

    gbc.gridwidth = 1; 
    gbc.anchor = GridBagConstraints.WEST;

    // Name Field
    gbc.gridx = 0; gbc.gridy = 1;
    JLabel nameLabel = new JLabel("Name:");
    nameLabel.setFont(boldFont);
    add(nameLabel, gbc);
    
    gbc.gridx = 1;
    nameField = new JTextField();
    nameField.setPreferredSize(new Dimension(300, 30));
    add(nameField, gbc);
    
    gbc.gridy++;

    // Email Field
    gbc.gridx = 0; gbc.gridy++;
    JLabel emailLabel = new JLabel("Email:");
    emailLabel.setFont(boldFont);
    add(emailLabel, gbc);
    
    gbc.gridx = 1;
    emailField = new JTextField();
    emailField.setPreferredSize(new Dimension(300, 30));
    add(emailField, gbc);
    
    gbc.gridy++;
   

    // Phone Field
    gbc.gridx = 0; gbc.gridy++;
    JLabel phoneLabel = new JLabel("Phone:");
    phoneLabel.setFont(boldFont);
    add(phoneLabel, gbc);
    
    gbc.gridx = 1;
    phoneField = new JTextField();
    phoneField.setPreferredSize(new Dimension(300, 30));
    add(phoneField, gbc);
    
    gbc.gridy++;

    // Password Field
    gbc.gridx = 0; gbc.gridy++;
    JLabel passwordLabel = new JLabel("Password:");
    passwordLabel.setFont(boldFont);
    add(passwordLabel, gbc);
    
    gbc.gridx = 1;
    passwordField = new JPasswordField();
    passwordField.setPreferredSize(new Dimension(300, 30));
    add(passwordField, gbc);
    
    gbc.gridy++;
    JLabel passwordError = new JLabel("*Password should be at least 6 characters");
    passwordError.setForeground(Color.RED);
    add(passwordError, gbc);

    // Confirm Password Field
    gbc.gridx = 0; gbc.gridy++;
    JLabel confirmPasswordLabel = new JLabel("Confirm Password:");
    confirmPasswordLabel.setFont(boldFont);
    add(confirmPasswordLabel, gbc);
    
    gbc.gridx = 1;
    confirmPasswordField = new JPasswordField();
    confirmPasswordField.setPreferredSize(new Dimension(300, 30));
    add(confirmPasswordField, gbc);
    
    gbc.gridy++;
   
    // Gender 
    gbc.gridx = 0; gbc.gridy++;
    JLabel genderLabel = new JLabel("Gender:");
    genderLabel.setFont(boldFont);
    add(genderLabel, gbc);
    
    gbc.gridx = 1;
    JPanel genderPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 0));
    maleButton = new JRadioButton("Male");
    femaleButton = new JRadioButton("Female");
    genderGroup = new ButtonGroup();
    genderGroup.add(maleButton);
    genderGroup.add(femaleButton);
    genderPanel.add(maleButton);
    genderPanel.add(femaleButton);
    genderPanel.setOpaque(false);
    maleButton.setOpaque(false);  //setopaque(false) is basically used to remove the bg of radiobuttons
    femaleButton.setOpaque(false);
    add(genderPanel, gbc);
    
    gbc.gridy++;

    // Submit Button
    gbc.gridx = 0; 
    gbc.gridy++; 
    gbc.gridwidth = 2; 
    gbc.anchor = GridBagConstraints.CENTER;
    JButton submitButton = new JButton("Submit");
    submitButton.setPreferredSize(new Dimension(200,30));
    add(submitButton, gbc);

    // Action Listener
    submitButton.addActionListener(e -> 
    {
        if (userCount >= MAX_USERS) {
            showPopup("Registration limit reached! Only 2 users allowed.", Color.BLACK);
            return;
        }

        String name = nameField.getText();
        String email = emailField.getText();
        String phone = phoneField.getText();
        String password = new String(passwordField.getPassword());
        String confirmPassword = new String(confirmPasswordField.getPassword());

        if (name.isEmpty() || !name.matches("[A-Za-z ]+")) {
            showPopup("Invalid Name! Only letters and spaces allowed.", Color.BLACK);
            return;
        }

        if (!email.contains("@") || !email.contains(".")) {
            showPopup("Invalid Email! Please enter a valid email address.", Color.BLACK);
            return;
        }

        if (registeredEmails.contains(email)) {
            showPopup("Email already registered! Use a different email.", Color.BLACK);
            return;
        }
        registeredEmails.add(email);

        if (phone.length() != 10 || !phone.matches("\\d+")) {
            showPopup("Invalid Phone! Must be 10 digits.", Color.BLACK);
            return;
        }

        if (password.length() < 6) {
            showPopup("Password too short! Minimum 6 characters.", Color.BLACK);
            return;
        }

        if (!password.equals(confirmPassword)) {
            showPopup("Password and Confirm Password do not match.", Color.BLACK);
            return;
        }

        if (!maleButton.isSelected() && !femaleButton.isSelected()) {
            showPopup("Please select Gender!", Color.BLACK);
            return;
        }

        userCount++;
        showPopup("Registration Successful!", Color.BLACK);
        
        //clear fields
        nameField.setText("");
        emailField.setText("");
        phoneField.setText("");
        passwordField.setText("");
        confirmPasswordField.setText("");
        genderGroup.clearSelection();
    }); //ActionListener close
    
    setDefaultCloseOperation(EXIT_ON_CLOSE);
    setVisible(true);
}

private void showPopup(String message, Color color) {
    UIManager.put("OptionPane.messageForeground", color);
    JOptionPane.showMessageDialog(this, message, "Message", JOptionPane.INFORMATION_MESSAGE);
}

public static void main(String[] args) 
{
    microproject m = new microproject();
}
}