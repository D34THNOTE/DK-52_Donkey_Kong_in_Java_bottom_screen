package model.player;

public class Player {

    public Position currentPosition; //changed to public
    private boolean[] lives = {true, true, true};
    private boolean gameOver = false;


    public Player(Position currentPosition) {
        this.currentPosition = currentPosition;
    }

    public Position getCurrentPosition() {
        return currentPosition;
    }

    public void moveUp() {
        currentPosition = currentPosition.getUp();
    }

    public void moveDown() {
        currentPosition = currentPosition.getDown();
    }

    public void moveLeft() {
        currentPosition = currentPosition.getLeft();
    }

    public void moveRight() {
        currentPosition = currentPosition.getRight();
    }

    public boolean moveJump() {
        boolean possible = currentPosition != currentPosition.getJump();
        currentPosition = currentPosition.getJump();
        return possible;
    }

    public void moveFall() {
        currentPosition = currentPosition.getFall();
    }

    public boolean[] getLives() {
        return lives;
    }

    public boolean isGameOver() {
        return gameOver;
    }

    public void loseLife() {
        boolean done = false;

        for(int i=0; i< lives.length && !done; i++) { // modified this a bit so that it actually works
            if(lives[i]) {
                lives[i] = false;
                done = true;
            }
        }

        if(!lives[2]) done = true;
        else done = false;

        if(done) { // GAME OVER
            gameOver = true;
            currentPosition = new Position(18);
        }
    }
}
