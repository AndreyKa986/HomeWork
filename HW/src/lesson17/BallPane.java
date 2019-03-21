package lesson17;

import javax.swing.*;
import java.awt.*;

public class BallPane extends JPanel {
    private int xRedBall = ((int) (Math.random() * 93)) * 10 + 10;
    private int yRedBall = ((int) (Math.random() * 51)) * 10 + 10;
    private boolean isXRedBall = true;
    private boolean isYRedBall = true;
    private int xBlueBall = ((int) (Math.random() * 93)) * 10 + 10;
    private int yBlueBall = ((int) (Math.random() * 51)) * 10 + 10;
    private boolean isXBlueBall = true;
    private boolean isYBlueBall = true;
    private int xGreenBall = ((int) (Math.random() * 93)) * 10 + 10;
    private int yGreenBall = ((int) (Math.random() * 51)) * 10 + 10;
    private boolean isXGreenBall = true;
    private boolean isYGreenBall = true;

    BallPane() {
        Timer timer = new Timer(20, e -> {
            moveRedBall();
            moveBlueBall();
            moveGreenBall();
            repaint();
        });
        timer.start();
    }

    private void moveRedBall() {
        if (xRedBall == 0 || xRedBall == 940) {
            isXRedBall = !isXRedBall;
            xRedBall = isXRedBall ? xRedBall + 10 : xRedBall - 10;
        } else {
            xRedBall = isXRedBall ? xRedBall + 10 : xRedBall - 10;
        }
        if (yRedBall == 0 || yRedBall == 520) {
            isYRedBall = !isYRedBall;
            yRedBall = isYRedBall ? yRedBall + 10 : yRedBall - 10;
        } else {
            yRedBall = isYRedBall ? yRedBall + 10 : yRedBall - 10;
        }
    }

    private void moveBlueBall() {
        if (xBlueBall == 0 || xBlueBall == 940) {
            isXBlueBall = !isXBlueBall;
            xBlueBall = isXBlueBall ? xBlueBall + 5 : xBlueBall - 5;
        } else {
            xBlueBall = isXBlueBall ? xBlueBall + 5 : xBlueBall - 5;
        }
        if (yBlueBall == 0 || yBlueBall == 520) {
            isYBlueBall = !isYBlueBall;
            yBlueBall = isYBlueBall ? yBlueBall + 5 : yBlueBall - 5;
        } else {
            yBlueBall = isYBlueBall ? yBlueBall + 5 : yBlueBall - 5;
        }
    }

    private void moveGreenBall() {
        if (xGreenBall == 0 || xGreenBall == 940) {
            isXGreenBall = !isXGreenBall;
            xGreenBall = isXGreenBall ? xGreenBall + 2 : xGreenBall - 2;
        } else {
            xGreenBall = isXGreenBall ? xGreenBall + 2 : xGreenBall - 2;
        }
        if (yGreenBall == 0 || yGreenBall == 520) {
            isYGreenBall = !isYGreenBall;
            yGreenBall = isYGreenBall ? yGreenBall + 2 : yGreenBall - 2;
        } else {
            yGreenBall = isYGreenBall ? yGreenBall + 2 : yGreenBall - 2;
        }
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(960, 540);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D redBall = (Graphics2D) g.create();
        redBall.setColor(Color.RED);
        redBall.fillOval(xRedBall, yRedBall, 20, 20);
        redBall.dispose();
        Graphics2D blueBall = (Graphics2D) g.create();
        blueBall.setColor(Color.BLUE);
        blueBall.fillOval(xBlueBall, yBlueBall, 20, 20);
        blueBall.dispose();
        Graphics2D greenBall = (Graphics2D) g.create();
        greenBall.setColor(Color.GREEN);
        greenBall.fillOval(xGreenBall, yGreenBall, 20, 20);
        greenBall.dispose();
    }
}
