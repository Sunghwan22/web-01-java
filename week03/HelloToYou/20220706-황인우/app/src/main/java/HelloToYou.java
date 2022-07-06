import javax.swing.*;
import java.awt.*;

public class HelloToYou {
  private String name = "world";

  public static void main(String[] args) {
    HelloToYou application = new HelloToYou();
    application.run();
  }

  public void run() {
    JFrame frame = new JFrame("Hello To You");
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setLayout(new GridLayout(0, 1));
    frame.setSize(300, 500);
    frame.setLocation(100, 75);

    JLabel label = new JLabel(greetingMessage());
    frame.add(label);

    JTextField textField = new JTextField(15);
    frame.add(textField);

    JButton button = new JButton("확인");
    button.addActionListener(event -> {
      name = textField.getText();
      label.setText(greetingMessage());
    });
    frame.add(button);

    frame.setVisible(true);
  }

  public String greetingMessage() {
    return "Hello, " + name + "!";
  }
}
