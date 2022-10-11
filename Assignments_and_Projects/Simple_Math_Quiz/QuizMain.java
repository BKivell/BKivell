public class QuizMain {

    public static void main(String[] args) {
        MathModel model = new MathModel();
        MathView view = new MathView();
        model.addObserver(view);
        view.setVisible(true);

        MathController controller = new MathController();
        controller.addModel(model);
        controller.addView(view);
        view.addController(controller);
    }
}
