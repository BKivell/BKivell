import java.util.Observable;
import java.util.Observer;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 *
 * @author Brad Kivell (20115449)
 */
public class MathView extends JFrame implements Observer {

    private JPanel userPanel = new JPanel();
    private JPanel calcPanel = new JPanel();
    private JLabel uName = new JLabel("Username: ");
    private JLabel pWord = new JLabel("Password: ");
    private JTextField unInput = new JTextField(10);
    private JTextField pwInput = new JTextField(10);
    private JLabel wrongName = new JLabel("Wrong username or password!");

    private JLabel firstNumber = new JLabel();
    private JLabel secondNumber = new JLabel();
    private JLabel additionLabel = new JLabel("+");
    private JButton nextButton = new JButton("Next");
    private JButton quitButton = new JButton("Quit");
    private JButton loginButton = new JButton("Log in");

    private JTextField calcSolution = new JTextField(10);

    public MathView() {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(600, 200);
        this.setVisible(true);

        calcPanel.add(firstNumber);
        calcPanel.add(additionLabel);
        calcPanel.add(secondNumber);
        calcPanel.add(calcSolution);
        calcPanel.add(nextButton);

        userPanel.add(uName);
        userPanel.add(unInput);
        userPanel.add(pWord);
        userPanel.add(pwInput);
        userPanel.add(loginButton);
        this.add(userPanel);
    }

    public void startGame() {
        calcPanel.add(quitButton);
        this.getContentPane().removeAll();
        calcPanel.setVisible(true);
        this.add(calcPanel);
        this.revalidate();
        this.repaint();
    }

    // Return login button
    public JButton getLogInButton() {
        return loginButton;
    }

    // Return username input
    public JTextField getUnInput() {
        return unInput;
    }

    // Return password input
    public JTextField getPwInput() {
        return pwInput;
    }

    // Return next button
    public JButton getNextButton() {
        return nextButton;
    }

    // Return quit button
    public JButton getQuitButton() {
        return quitButton;
    }

    // Returns user solution
    public JTextField getCalcSolution() {
        return calcSolution;
    }

    // EDIT THIS
    @Override
    public void update(Observable o, Object arg) {
        if (arg instanceof State) {
            State state;
            state = (State) arg;
            if (state.playing) {
                firstNumber.setText(state.num1 + "");
                secondNumber.setText(state.num2 + "=");
                getCalcSolution().setText("");
                calcPanel.repaint();
            } else {
                JPanel quitPanel = new JPanel();
                JLabel scoreLabel = new JLabel("Your score: " + state.score);
                quitPanel.add(scoreLabel);
                this.getContentPane().removeAll();
                this.add(quitPanel);
                this.revalidate();
                this.repaint();
            }
        } else {
            System.out.println("Invalid Parameter");
        }

    }

    void addController(MathController controller) {
        quitButton.addActionListener(controller);
        nextButton.addActionListener(controller);
        loginButton.addActionListener(controller);
    }
}
