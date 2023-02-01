package controller;

import model.Model;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.Timer;

public class Controller implements ActionListener, KeyListener {

    private int tickLength = 1000;

    private final Timer timer = new Timer(tickLength,this); // passing "this" as action listener
    private final Model model;


    public Controller(Model model) {
        this.model = model;
        timer.start();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (model.player.currentPosition.getTickDangerIndex() != -1) {
            if (model.dangers.isDanger(model.player.getCurrentPosition().getTickDangerIndex())) { //checking if player is in a position where the movement of dangerous items would cause internal bleeding(probably)
                model.teleportToLimbo();
                model.playerLoseLife();
            }
        }
        model.moveDangersToNextTick();

        if(model.player.currentPosition.getId() == 15) {
            model.teleportToLimbo();
            timer.stop();

            try { Thread.sleep(3000); }
            catch (InterruptedException ignored) { }

            model.teleportToStart();
            timer.start();
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int keyCode = e.getKeyCode();
        switch(keyCode) {
            // STARTING THE GAME/RESPAWNING AFTER DEATH
            case KeyEvent.VK_SPACE -> {
                if(model.player.currentPosition.getId() == 16) model.teleportToStart();
            }

            // MOVING UP/JUMPING
            case KeyEvent.VK_UP -> {

                if(model.movePlayerJump()) {
                    tickLength -= 20;
                    new Thread(() -> {
                        try { Thread.sleep(800); }
                        catch (InterruptedException ignored) { }

                        model.movePlayerFall();
                    }).start();
                }
                else model.movePlayerUp(); // for walking up a ladder
            }

            // MOVING DOWN
            case KeyEvent.VK_DOWN -> model.movePlayerDown();

            //MOVING LEFT
            case KeyEvent.VK_LEFT -> model.movePlayerLeft();

            // MOVING RIGHT
            case KeyEvent.VK_RIGHT -> model.movePlayerRight();
        }
    }
















    //DUNGEON OF FORGOTTEN METHODS
    @Override public void keyTyped(KeyEvent ignored) { }
    @Override public void keyPressed(KeyEvent ignored) { }
}
