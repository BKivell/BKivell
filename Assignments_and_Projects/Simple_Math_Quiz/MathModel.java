import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Observable;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author Brad Kivell (20115449)
 */
public class MathModel extends Observable {

    public String username = null;
    public String password = null;
    public State modelState;
    int answer = 0;

    private DBManager dBManager;

    public MathModel() {
        dBManager = new DBManager();
        dBManager.dbsetup();
        modelState = new State();
    }

    // Holds data to be passed to view
    class State {
        public int num1;
        public int num2;
        public boolean playing;
        public int score = 0;
    }

    // Verifies username and password, returns true of match or when generating a new user
    public boolean checkName() {
        boolean userCheck = false;
        try {
            ResultSet rs = dBManager.dBQuery("SELECT userid, password, score FROM UserInfo "
                    + "WHERE userid = '" + username + "'");
            if (rs.next()) {
                String pass = rs.getString("password");
                System.out.println("***" + pass);
                System.out.println("found user");

                if (password.equals(pass)) {
                    modelState.score = rs.getInt("score");
                    userCheck = true;
                    System.out.println("Password Verified");
                } else {
                    userCheck = false;
                    System.out.println("Invalid Password");
                    return userCheck;
                }
            } else {
                System.out.println("no such user");
                dBManager.dBUpdate("INSERT INTO UserInfo "
                        + "VALUES('" + username + "', '" + password + "', 0)");
                userCheck = true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(MathQuiz.class.getName()).log(Level.SEVERE, null, ex);
        }
        return userCheck;
    }

    // Generates a new question 
    public void newQuestion() {
        modelState.playing = true;
        modelState.num1 = getNumber();
        modelState.num2 = getNumber();
        answer = modelState.num1 + modelState.num2;
        setChanged();
        notifyObservers(modelState);
    }

    // Returns random integer
    public int getNumber() {
        Random generator = new Random();
        int i = generator.nextInt(100);
        return i;
    }

    // Updates database and view to match
    void quitGame() {
        modelState.playing = false;
        dBManager.dBUpdate("UPDATE UserInfo SET score=" + modelState.score + " WHERE userid='" + username + "'");
        System.out.println(username + modelState.score);
        setChanged();
        notifyObservers(modelState);
    }

}
