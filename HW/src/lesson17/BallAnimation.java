package lesson17;

import java.awt.*;
import javax.swing.*;

public class BallAnimation {
    public static void main(String[] args) {
        new BallAnimation();
    }

    private BallAnimation() {
        EventQueue.invokeLater(() -> {
            try {
                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex) {
                ex.printStackTrace();
            }
            JFrame frame = new JFrame("Home work from lesson 17");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.add(new BallPane());
            frame.pack();
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
        });
    }
}
