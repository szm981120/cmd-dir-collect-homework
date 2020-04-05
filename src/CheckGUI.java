import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.List;

public class CheckGUI {
    private JPanel rootPanel;
    private JButton check_btn;
    private JTextArea show_txta;
    private JLabel show_lbl;

    public CheckGUI() {
        show_txta.setFocusable(false);
        show_lbl.setText("");
        check_btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Check check = new Check();
                    List<String> uncommitted = check.check();
                    StringBuilder stringBuilder = new StringBuilder();
                    if (uncommitted == null || uncommitted.size() == 0) {
                        show_lbl.setText("完活");
                        stringBuilder.append("全部上交");
                    } else {
                        show_lbl.setText("处刑名单");
                        for (int i = 1; i <= uncommitted.size(); i++) {
                            stringBuilder.append(i + ": " + uncommitted.get(i - 1) + "\n");
                        }
                        show_txta.setText(stringBuilder.toString());
                    }
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        });
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("CheckGUI");
        frame.setContentPane(new CheckGUI().rootPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
        frame.setBounds(250, 100, 500, 500);
    }
}
