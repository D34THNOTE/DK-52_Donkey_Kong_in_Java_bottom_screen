package model.dangers;

public class Dangers {

    boolean[] isBarrelPresent = {true, false, false, false, false, false, false, false, false, false, false, false}; // length 12
    boolean[] isPlatformPresent = new boolean[5]; //{true, false, false, true, false};
    private final double probabilityOfBarrelSpawn = 0.3;
    private final double probabilityOfPlatformSpawn = 0.1;

    public boolean[] getIsBarrelPresent() {
        return isBarrelPresent;
    }

    public boolean[] getIsPlatformPresent() {
        return isPlatformPresent;
    }

    public void moveToNextTick() {
        moveBarrelsToNextTick();
        movePlatformsToNextTick();
    }

    private void moveBarrelsToNextTick() {
        for(int i = isBarrelPresent.length-1; i > 0; i--) {
            isBarrelPresent[i] = isBarrelPresent[i-1];
        }

        if(!isBarrelPresent[1]) isBarrelPresent[0] = Math.random() < probabilityOfBarrelSpawn;
        else isBarrelPresent[0] = false;    // 2 barrels in a row made impossible
    }

    private void movePlatformsToNextTick() {
        for(int i = isPlatformPresent.length-1; i > 0; i--) {
            isPlatformPresent[i] = isPlatformPresent[i-1];
        }

        isPlatformPresent[0] = Math.random() < probabilityOfPlatformSpawn;
    }

    public boolean isDanger(int dangerIndex) {
        // asking if there's danger in a position
        if(dangerIndex < 0) return false;
        if(dangerIndex < 12) return isBarrelPresent[dangerIndex];
        if(dangerIndex < 17) return isPlatformPresent[dangerIndex-12];
        if(dangerIndex == 100) return true; // for killing by jumping into a platform

        return false;
    }
}
