package view;

import model.Model;

import javax.swing.*;
import java.awt.*;

public class ViewPanel extends JPanel {

    private final Model model;

    private ImageIcon background = new ImageIcon();
    private ImageIcon deathScreen = new ImageIcon();
    private final Image[] arrOfPlayers = new Image[15];
    private final Image[] arrOfBarrels = new Image[12];
    private final Image[] arrOfLives = new Image[3];
    private final Image[] arrOfPlatforms = new Image[5];

    public ViewPanel(Model model) {
        super();
        this.model = model;
        initializeImages();

        new Thread( () -> { // I love this style of creating threads, great
            while(!Thread.currentThread().isInterrupted()) {
                synchronized (Model.class) {
                    try { Model.class.wait(); }
                    catch (InterruptedException ignored) { }

                    repaint();
                }
            }
        }).start();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(background.getImage(), 0, 0, null);

        int position = model.player.getCurrentPosition().getId();

        // drawing player
        switch (position) {
            case 0 -> g.drawImage(arrOfPlayers[0], 62, 304, null);
            case 1 -> g.drawImage(arrOfPlayers[1], 66, 228, null);
            case 2 -> g.drawImage(arrOfPlayers[2], 228, 296, null);
            case 3 -> g.drawImage(arrOfPlayers[3], 365, 288, null);
            case 4 -> g.drawImage(arrOfPlayers[4], 488, 277, null);
            case 5 -> g.drawImage(arrOfPlayers[5], 480, 230, null);
            case 6 -> g.drawImage(arrOfPlayers[6], 597, 269, null);
            case 7 -> g.drawImage(arrOfPlayers[7], 540,149, null);
            case 8 -> g.drawImage(arrOfPlayers[8], 424,140, null);
            case 9 -> g.drawImage(arrOfPlayers[9], 417,65, null);
            case 10 -> g.drawImage(arrOfPlayers[10], 300,133, null);
            case 11 -> g.drawImage(arrOfPlayers[11], 299,56, null);
            case 12 -> g.drawImage(arrOfPlayers[12], 184,120, null);
            case 13 -> g.drawImage(arrOfPlayers[13], 47,117, null);
            case 14 -> g.drawImage(arrOfPlayers[14], 44,37, null);
        }

        // drawing barrels
        boolean[] barrels = model.dangers.getIsBarrelPresent();
        if(barrels[11]) g.drawImage(arrOfBarrels[11], 164,341, null);
        if(barrels[10]) g.drawImage(arrOfBarrels[10], 318,332, null);
        if(barrels[9]) g.drawImage(arrOfBarrels[9], 445,320, null);
        if(barrels[8]) g.drawImage(arrOfBarrels[8], 560,314, null);
        if(barrels[7]) g.drawImage(arrOfBarrels[7], 677,306, null);
        if(barrels[6]) g.drawImage(arrOfBarrels[6], 664,199, null);
        if(barrels[5]) g.drawImage(arrOfBarrels[5], 500,186, null);
        if(barrels[4]) g.drawImage(arrOfBarrels[4], 379,178, null);
        if(barrels[3]) g.drawImage(arrOfBarrels[3], 258,168, null);
        if(barrels[2]) g.drawImage(arrOfBarrels[2], 126,160, null);
        if(barrels[1]) g.drawImage(arrOfBarrels[1], 5,151, null);
        if(barrels[0]) g.drawImage(arrOfBarrels[0], 1,23, null);

        //drawing lives
        if(model.player.getLives()[0]) g.drawImage(arrOfLives[0], 314,409,null);
        if(model.player.getLives()[1]) g.drawImage(arrOfLives[1], 372,409,null);
        if(model.player.getLives()[2]) g.drawImage(arrOfLives[2], 430,409,null);

        //drawing moving platforms
        boolean[] platforms = model.dangers.getIsPlatformPresent();
        if(platforms[0]) g.drawImage(arrOfPlatforms[0], 559,36,null);
        if(platforms[1]) g.drawImage(arrOfPlatforms[1], 416,24,null);
        if(platforms[2]) g.drawImage(arrOfPlatforms[2], 290,17,null);
        if(platforms[3]) g.drawImage(arrOfPlatforms[3], 172,10,null);
        if(platforms[4]) g.drawImage(arrOfPlatforms[4], 45,-6,null);

        // RAINY DAY, game over(you died)
        if(model.player.isGameOver()) g.drawImage(deathScreen.getImage(), 0,-30,null);
    }

    // Basement
    private void initializeImages() {
        background = new ImageIcon("images/GUImap/DonkeyKongMapThatIMadeByMyselfAndAmSoProudOfThatIGaveItARidiculouslyLongName.png");
        deathScreen = new ImageIcon("images/GUImap/YOUNOOB.png");

        arrOfPlayers[0] = new ImageIcon("images/GUIplayer/1(62,304).png").getImage();
        arrOfPlayers[1] = new ImageIcon("images/GUIplayer/1jump(66,228).png").getImage();
        arrOfPlayers[2] = new ImageIcon("images/GUIplayer/2(228,296).png").getImage();
        arrOfPlayers[3] = new ImageIcon("images/GUIplayer/3(365,288).png").getImage();
        arrOfPlayers[4] = new ImageIcon("images/GUIplayer/4(488,277).png").getImage();
        arrOfPlayers[5] = new ImageIcon("images/GUIplayer/4jump(488,230).png").getImage();
        arrOfPlayers[6] = new ImageIcon("images/GUIplayer/5(597,269).png").getImage();
        arrOfPlayers[7] = new ImageIcon("images/GUIplayer/6(540,149).png").getImage();
        arrOfPlayers[8] = new ImageIcon("images/GUIplayer/7(424,140).png").getImage();
        arrOfPlayers[9] = new ImageIcon("images/GUIplayer/7jump(417,65).png").getImage();
        arrOfPlayers[10] = new ImageIcon("images/GUIplayer/8(300,133).png").getImage();
        arrOfPlayers[11] = new ImageIcon("images/GUIplayer/8jump(299,56).png").getImage();
        arrOfPlayers[12] = new ImageIcon("images/GUIplayer/9(184,120).png").getImage();
        arrOfPlayers[13] = new ImageIcon("images/GUIplayer/10(47,117).png").getImage();
        arrOfPlayers[14] = new ImageIcon("images/GUIplayer/10jump(44,37).png").getImage();

        arrOfBarrels[11] = new ImageIcon("images/GUIbarrels/b1(164,341).png").getImage();
        arrOfBarrels[10] = new ImageIcon("images/GUIbarrels/b2(318,332).png").getImage();
        arrOfBarrels[9] = new ImageIcon("images/GUIbarrels/b3(445,320).png").getImage();
        arrOfBarrels[8] = new ImageIcon("images/GUIbarrels/b4(560,314).png").getImage();
        arrOfBarrels[7] = new ImageIcon("images/GUIbarrels/b5(677,306).png").getImage();
        arrOfBarrels[6] = new ImageIcon("images/GUIbarrels/b6(664,199).png").getImage();
        arrOfBarrels[5] = new ImageIcon("images/GUIbarrels/b7(500,186).png").getImage();
        arrOfBarrels[4] = new ImageIcon("images/GUIbarrels/b8(379,178).png").getImage();
        arrOfBarrels[3] = new ImageIcon("images/GUIbarrels/b9(258,168).png").getImage();
        arrOfBarrels[2] = new ImageIcon("images/GUIbarrels/b10(126,160).png").getImage();
        arrOfBarrels[1] = new ImageIcon("images/GUIbarrels/b11(5,151).png").getImage();
        arrOfBarrels[0] = new ImageIcon("images/GUIbarrels/b12(1,23).png").getImage();

        arrOfLives[0] = new ImageIcon("images/GUIlives/live1(314,409).png").getImage();
        arrOfLives[1] = new ImageIcon("images/GUIlives/live2(372,409).png").getImage();
        arrOfLives[2] = new ImageIcon("images/GUIlives/live3(430,409).png").getImage();

        arrOfPlatforms[0] = new ImageIcon("images/GUIplatforms/platform1(559,36).png").getImage();
        arrOfPlatforms[1] = new ImageIcon("images/GUIplatforms/platform2(416,24).png").getImage();
        arrOfPlatforms[2] = new ImageIcon("images/GUIplatforms/platform3(290,17).png").getImage();
        arrOfPlatforms[3] = new ImageIcon("images/GUIplatforms/platform4(172,10).png").getImage();
        arrOfPlatforms[4] = new ImageIcon("images/GUIplatforms/platform5(45,-6).png").getImage();

    }

}
