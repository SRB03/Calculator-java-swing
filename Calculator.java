import javax.swing.*;
import java.awt.*;

public class Calculator {
    public static void calculation(String str, JTextField T){
        if (str == null || str.trim().isEmpty()) {
            T.setText("");
            return;
        }

        char[] operators = new char[32];
        int[] operands = new int[32];
        int opt_counter = 0;
        int opd_counter = 0;

        for (int i = 0; i < str.length(); i++) {
            char ch = str.charAt(i);
                operands[opd_counter] = operands[opd_counter] * 10 + (ch - '0');
            } else if (ch == '+' || ch == '-' || ch == '*' || ch == '/') {
                operators[opt_counter] = ch;
                opd_counter++;
                opt_counter++;
            } else if (ch == ' ') {
                // skip spaces
            } else {
                // ignore other characters
            }
        }
        float result = (float) operands[0];
        for (int n = 0; n < opt_counter; n++) {
            char op = operators[n];
            int rhs = operands[n + 1];
            switch (op) {
                case '+':
                    result = result + rhs;
                    break;
                case '-':
                    result = result - rhs;
                    break;
                case '*':
                    result = result * rhs;
                    break;
                case '/':
                    if (rhs == 0) {
                        T.setText("Err: Div0");
                        return;
                    }
                    result = result / rhs;
                    break;
                default:
                    // ignore
            }
        }

        T.setText(Float.toString(result));
    }


    public static void addActionNum(JButton b , String txt, JTextField t){
        b.addActionListener(e -> t.setText(t.getText() + txt));
    }


    public static void addActionOpr(JButton b, String txt, JTextField t) {
        b.addActionListener(e -> {
            String s = t.getText();
            if (s == null) s = "";
            
            if (s.isEmpty()) {
                if ("-".equals(txt)) {
                    t.setText("-");
                }
                return;
            }

            int n = s.length();
            char last = s.charAt(n - 1);

            if (last == '+' || last == '-' || last == '*' || last == '/') {
                t.setText(s.substring(0, n - 1) + txt);
                return;
            }
            t.setText(s + txt);
        });
    }


    public static void addActionClear(JButton b, JTextField t){
        b.addActionListener(e -> t.setText(""));
    }


    public static void addActionEqual(JButton b, JTextField t){
        b.addActionListener(e -> calculation(t.getText(), t));
    }


    public static void main(String[] args) {
        JFrame jframe = new JFrame("Calculator");

//        Button Declaration..
    JButton[] digits = new JButton[10];
    for (int i = 0; i <= 9; i++) digits[i] = new JButton(String.valueOf(i));
        JButton bplus = new JButton("+");
        JButton bminus = new JButton("-");
        JButton bmult = new JButton("*");
        JButton bdivid = new JButton("/");
        JButton bequal = new JButton("=");
        JButton bclr = new JButton("C");
        JTextField textF = new JTextField(20);

//        Panel declaration...
        JPanel bpanel = new JPanel();
        JPanel tpanel = new JPanel();
        bpanel.setLayout(new GridBagLayout());
        tpanel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

//        Adding elements to the respective panels...
        {
            gbc.insets = new Insets(10, 10, 10, 10);
            gbc.anchor = GridBagConstraints.WEST;

            gbc.gridx = 0;
            gbc.gridy = 0;
            bpanel.add(digits[1], gbc);
            gbc.gridx = 1;
            gbc.gridy = 0;
            bpanel.add(digits[2], gbc);
            gbc.gridx = 2;
            gbc.gridy = 0;
            bpanel.add(digits[3], gbc);
            gbc.gridx = 0;
            gbc.gridy = 1;
            bpanel.add(digits[4], gbc);
            gbc.gridx = 1;
            gbc.gridy = 1;
            bpanel.add(digits[5], gbc);
            gbc.gridx = 2;
            gbc.gridy = 1;
            bpanel.add(digits[6], gbc);
            gbc.gridx = 0;
            gbc.gridy = 2;
            bpanel.add(digits[7], gbc);
            gbc.gridx = 1;
            gbc.gridy = 2;
            bpanel.add(digits[8], gbc);
            gbc.gridx = 2;
            gbc.gridy = 2;
            bpanel.add(digits[9], gbc);

            gbc.gridx = 0;
            gbc.gridy = 3;
            bpanel.add(bclr, gbc);
            gbc.gridx = 1;
            gbc.gridy = 3;
            bpanel.add(digits[0], gbc);
            gbc.gridx = 2;
            gbc.gridy = 3;
            bpanel.add(bequal, gbc);

            gbc.gridx = 3;
            gbc.gridy = 0;
            bpanel.add(bplus, gbc);
            gbc.gridx = 3;
            gbc.gridy = 1;
            bpanel.add(bminus, gbc);
            gbc.gridx = 3;
            gbc.gridy = 2;
            bpanel.add(bmult, gbc);
            gbc.gridx = 3;
            gbc.gridy = 3;
            bpanel.add(bdivid, gbc);
            bpanel.setSize(290, 200);
            tpanel.add(textF);
            textF.setEditable(false);
            textF.setHorizontalAlignment(JTextField.RIGHT);
            textF.setFont(new Font("SansSerif", Font.PLAIN, 18));
        }


//        Adding ActionListener to num buttons...
        {
            for (int i = 0; i <= 9; i++) {
                final int val = i;
                digits[i].addActionListener(e -> textF.setText(textF.getText() + val));
            }
        }


//        Adding ActionListener to operator buttons, clear and equals button...
        {
            addActionOpr(bplus, "+", textF );
            addActionOpr(bminus, "-", textF );
            addActionOpr(bmult, "*", textF );
            addActionOpr(bdivid, "/", textF );

            addActionClear(bclr, textF);
            addActionEqual(bequal, textF);
        }


//        Framing...
        {
            jframe.getContentPane().add(bpanel, BorderLayout.CENTER);
            jframe.getContentPane().add(tpanel, BorderLayout.NORTH);
            jframe.setSize(300, 250);
            jframe.setVisible(true);
            jframe.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        }

    }
}
