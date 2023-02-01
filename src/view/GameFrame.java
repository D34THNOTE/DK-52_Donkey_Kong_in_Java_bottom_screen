package view;

import model.Model;

import javax.swing.*;
import java.awt.*;

public class GameFrame extends JFrame {


    public GameFrame(Model model) {
        super();

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Dimension dim = new Dimension(800, 530);
        this.setSize(dim);
        this.setPreferredSize(dim);

        this.add(new ViewPanel(model));
        this.setTitle("CLANG"); // important
    }


}
