package model;

import model.dangers.Dangers;
import model.player.Player;
import model.player.Position;

public class Model {

    public Player player;
    public Dangers dangers;
    private Position startPosition;
    private Position limbo;

    public Model() {
        initializePositions();
        this.player = new Player(limbo);
        this.dangers = new Dangers();
    }

    public void initializePositions() {
        Position[] posArray = new Position[17];

        for(int i=0; i < posArray.length; i++) {
            posArray[i] = new Position(i);
        }
        // mapping where you can go from a position
        posArray[0].setJump(posArray[1]).setRight(posArray[2]);
        posArray[1].setFall(posArray[0]);
        posArray[2].setRight(posArray[3]).setLeft(posArray[0]);
        posArray[3].setRight(posArray[4]).setLeft(posArray[2]);
        posArray[4].setRight(posArray[6]).setLeft(posArray[3]).setJump(posArray[5]);
        posArray[5].setFall(posArray[4]);
        posArray[6].setLeft(posArray[4]).setUp(posArray[7]);
        posArray[7].setLeft(posArray[8]).setDown(posArray[6]);
        posArray[8].setLeft(posArray[10]).setRight(posArray[7]).setJump(posArray[9]);
        posArray[9].setFall(posArray[8]);
        posArray[10].setLeft(posArray[12]).setRight(posArray[8]).setJump(posArray[11]);
        posArray[11].setFall(posArray[10]);
        posArray[12].setLeft(posArray[13]).setRight(posArray[10]);
        posArray[13].setRight(posArray[12]).setUp(posArray[14]);
        posArray[14].setDown(posArray[13]).setUp(posArray[15]);

        // mapping which barrel can kill you at the moment of tick
        posArray[0].setTickDangerIndex(11);
        posArray[2].setTickDangerIndex(10);
        posArray[3].setTickDangerIndex(9);
        posArray[4].setTickDangerIndex(8);
        posArray[6].setTickDangerIndex(7);
        posArray[7].setTickDangerIndex(5);
        posArray[8].setTickDangerIndex(4);
        posArray[10].setTickDangerIndex(3);
        posArray[12].setTickDangerIndex(2);
        posArray[13].setTickDangerIndex(1); //redoing this

        // mapping which platform can kill you from what position
        // needed because there's constant jump time
        posArray[9].setTickDangerIndex(12);
        posArray[11].setTickDangerIndex(13);

        // setting up connections so that moving left through a barrel will kill the player
        posArray[2].setLeftDanger(11);
        posArray[3].setLeftDanger(10);
        posArray[4].setLeftDanger(9);
        posArray[6].setLeftDanger(8);
        posArray[7].setLeftDanger(5);
        posArray[8].setLeftDanger(4);
        posArray[10].setLeftDanger(3);
        posArray[12].setLeftDanger(2);
        //posArray[13].setLeftDanger(1); //unnecessary but commented it just in case.. dunno what case, but case

        // setting up connections so that moving right through a barrel will kill the player
        posArray[0].setRightDanger(11);
        posArray[2].setRightDanger(10);
        posArray[3].setRightDanger(9);
        posArray[4].setRightDanger(8);
        posArray[8].setRightDanger(5);
        posArray[10].setRightDanger(4);
        posArray[12].setRightDanger(3);
        posArray[13].setRightDanger(2);

        // setting up connections so that jumping up into a platform/steal beam will actually kill the player
        posArray[8].setUpDanger(13);
        posArray[10].setUpDanger(14);
        posArray[14].setUpDanger(16);

        // killing by jumping into construction
        posArray[2].setUpDanger(100);
        posArray[3].setUpDanger(100);
        posArray[7].setUpDanger(100);
        posArray[12].setUpDanger(100);

        startPosition = posArray[0]; // I'm going to let you guess that one
        limbo = posArray[16]; // where player goes to when they start the game/die
    }

    public boolean movePlayerJump() {
        int dangerId = player.currentPosition.getUpDanger();
        boolean jumpSuccessful  = player.moveJump();
        if(!jumpSuccessful) return false;

        if (dangers.isDanger(dangerId)) {
            teleportToLimbo();
            playerLoseLife();
            return false;
        }

        synchronized (Model.class) {
            Model.class.notify();
        }
        return true;
    }

    public void movePlayerUp() {
        int dangerId = player.currentPosition.getUpDanger();

        if (dangers.isDanger(dangerId)) {
            teleportToLimbo();
            playerLoseLife();
        } else {
            player.moveUp();
            synchronized (Model.class) {
                Model.class.notify();
            }
        }
    }

    public void movePlayerDown() { // only tick can kill the player hence why no checks for danger
        player.moveDown();
        synchronized (Model.class) {
            Model.class.notify();
        }
    }

    public void movePlayerLeft() {
        int dangerId = player.currentPosition.getLeftDanger();

        if(dangers.isDanger(dangerId)) {
            teleportToLimbo();
            playerLoseLife();
        } else {
            player.moveLeft();
            synchronized (Model.class) {
                Model.class.notify();
            }
        }
    }

    public void movePlayerRight() {
        int dangerId = player.currentPosition.getRightDanger();

        if(dangers.isDanger(dangerId)) {
            teleportToLimbo();
            playerLoseLife();
        } else {
            player.moveRight();
            synchronized (Model.class) {
                Model.class.notify();
            }
        }
    }

    public void movePlayerFall() {
        player.moveFall();
        synchronized (Model.class) {
            Model.class.notify();
        }
    }

    public void teleportToStart() {
        player.currentPosition = startPosition;

        synchronized (Model.class) {
            Model.class.notify();
        }
    }

    public void teleportToLimbo() {
        player.currentPosition = limbo;

        synchronized (Model.class) {
            Model.class.notify();
        }
    }

    public void playerLoseLife() {
        player.loseLife();

        synchronized (Model.class) {
            Model.class.notify();
        }
    }

    public void moveDangersToNextTick() {
        dangers.moveToNextTick();

        synchronized (Model.class) {
            Model.class.notify();
        }
    }
}
