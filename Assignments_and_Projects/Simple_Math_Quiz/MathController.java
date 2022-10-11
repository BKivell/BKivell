import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
/**
 *
 * @author Brad Kivell (20115449)
 */
public class MathController implements ActionListener {

    public MathView view; // Holds reference to the view (Updates user interface)
    public MathModel model; // Holds reference to the model (Stores & manages data)

    public MathController() {
    }

    // Action listener, performs function based on source
    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        if (source == view.getQuitButton()) {
            eventHandlerQuit();
        }
        if (source == view.getLogInButton()) {
            eventHandlerLogin();
        }
        if (source == view.getNextButton()) {
            eventHandlerNext();
        }
    }

    // Generates a new question & updates view
    private void createNewQuestion() {
        this.model.newQuestion();
    }

    // Starts quiz & updates view
    public void startQuiz() {
        createNewQuestion();
        view.startGame();
    }

    // Verifies password before starting quiz
    private void eventHandlerLogin() {
        System.out.println("You clicked the login button");
        model.username = view.getUnInput().getText();
        model.password = view.getPwInput().getText();
        if (model.username != null && model.password != null) {
            if (model.checkName() == true) {
                startQuiz();
            }
        }
    }

    // Generates a new question and updates the players score
    private void eventHandlerNext() {
        System.out.println("You clicked the next button");
        String userAnswer = view.getCalcSolution().getText();
        if (userAnswer.compareTo(model.answer + "") == 0) {
            model.modelState.score += 10;
            createNewQuestion();
        } else {
            model.modelState.score -= 10;
            createNewQuestion();
        }
        System.out.println(model.modelState.score);
    }

    // Updates database to hold score and updates view
    private void eventHandlerQuit() {
        System.out.println("You clicked the quit button");
        String userAnswer = view.getCalcSolution().getText();
        model.quitGame();
    }

    public void addView(MathView view) {
        this.view = view;
    }

    public void addModel(MathModel model) {
        this.model = model;
    }
}
