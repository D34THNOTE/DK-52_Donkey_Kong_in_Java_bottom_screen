package model.player;

public class Position {

    private final int id;

    private Position up;
    private Position down;
    private Position left;
    private Position right;
    private Position jump;
    private Position fall;

    // index of the barrel/platform that can kill u in the
    // specific position
    // -1 if none can kill
    private int tickDangerIndex;
    private int leftDanger;
    private int rightDanger;
    private int upDanger;


    public Position (int id) {
        this.id = id;
        this.up = this;
        this.down = this;
        this.left = this;
        this.right = this;
        this.jump = this;
        this.fall = this;
        this.tickDangerIndex = -1; //default value -1 if nothing can kill
        this.leftDanger = -1;
        this.rightDanger = -1;
        this.upDanger = -1;
    }

    public Position getUp() {
        return up;
    }

    public Position getDown() {
        return down;
    }

    public Position getLeft() {
        return left;
    }

    public Position getRight() {
        return right;
    }

    public Position getJump() {
        return jump;
    }

    public Position getFall() {
        return fall;
    }

    public int getTickDangerIndex() {
        return tickDangerIndex;
    }

    public int getId() {
        return id;
    }

    public int getLeftDanger() {
        return leftDanger;
    }

    public int getRightDanger() {
        return rightDanger;
    }

    public int getUpDanger() {
        return upDanger;
    }

    /////////////////////////////////////////////////

    //returns "this" so that I can do "method chaining" and I think it's really cool actually
    public Position setUp(Position up) {
        this.up = up;
        return this;
    }

    public Position setDown(Position down) {
        this.down = down;
        return this;
    }

    public Position setLeft(Position left) {
        this.left = left;
        return this;
    }

    public Position setRight(Position right) {
        this.right = right;
        return this;
    }

    public Position setJump(Position jump) {
        this.jump = jump;
        return this;
    }

    public Position setFall(Position fall) {
        this.fall = fall;
        return this;
    }

    public Position setTickDangerIndex(int tickDangerIndex) {
        this.tickDangerIndex = tickDangerIndex;
        return this;
    }

    public Position setLeftDanger(int leftDanger) {
        this.leftDanger = leftDanger;
        return this;
    }

    public Position setRightDanger(int rightDanger) {
        this.rightDanger = rightDanger;
        return this;
    }

    public Position setUpDanger(int upDanger) {
        this.upDanger = upDanger;
        return this;
    }
}
