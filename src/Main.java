import controller.Controller;
import model.Model;
import view.GameFrame;

import javax.swing.*;

public class Main {

    private static Controller controller;
    private static Model model;
    private static GameFrame gameFrame;

    public static void main(String[] args) {
        model = new Model();
        controller = new Controller(model);
        gameFrame = new GameFrame(model);

        //listens to the JFrame for player input
        gameFrame.addKeyListener(controller);

        SwingUtilities.invokeLater( () -> {
            gameFrame.pack();
            gameFrame.setLocationRelativeTo(null);
            gameFrame.setVisible(true);
        });


    }

}
