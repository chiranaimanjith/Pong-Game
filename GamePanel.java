import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JPanel;
import javax.swing.Timer;

public class GamePanel extends JPanel implements Runnable {

    private Ball ball;
    private Paddle player1;
    private Paddle player2;
    private int player1Score;
    private int player2Score;
    private boolean gameRunning;

    public GamePanel() {
        setBackground(Color.BLACK);
        setFocusable(true);
        setPreferredSize(new Dimension(800, 600));
        addKeyListener(new KeyInputHandler());

        ball = new Ball(400, 300);
        player1 = new Paddle(10, 250, KeyEvent.VK_W, KeyEvent.VK_S);
        player2 = new Paddle(770, 250, KeyEvent.VK_UP, KeyEvent.VK_DOWN);

        gameRunning = true;

        Timer timer = new Timer(5, e -> gameUpdate());
        timer.start();

        new Thread(this).start();
    }

    private void gameUpdate() {
        if (gameRunning) {
            ball.move();
            player1.move();
            player2.move();
            checkCollision();
            repaint();
        }
    }

    private void checkCollision() {
        // Check collision with walls
        if (ball.getY() <= 0 || ball.getY() >= getHeight() - ball.getSize()) {
            ball.reverseYDirection();
        }

        // Check collision with paddles
        if (ball.getBounds().intersects(player1.getBounds()) || ball.getBounds().intersects(player2.getBounds())) {
            ball.reverseXDirection();
        }

        // Check if the ball goes out of bounds
        if (ball.getX() <= 0) {
            player2Score++;
            resetBall();
        } else if (ball.getX() >= getWidth() - ball.getSize()) {
            player1Score++;
            resetBall();
        }
    }

    private void resetBall() {
        ball = new Ball(400, 300);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        g2d.setColor(Color.WHITE);
        g2d.setFont(new Font("Arial", Font.BOLD, 30));
        g2d.drawString(String.valueOf(player1Score), getWidth() / 2 - 50, 50);
        g2d.drawString(String.valueOf(player2Score), getWidth() / 2 + 30, 50);

        ball.draw(g2d);
        player1.draw(g2d);
        player2.draw(g2d);
    }

    @Override
    public void run() {
        while (gameRunning) {
            gameUpdate();
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private class KeyInputHandler extends KeyAdapter {
        @Override
        public void keyPressed(KeyEvent e) {
            player1.keyPressed(e);
            player2.keyPressed(e);
        }

        @Override
        public void keyReleased(KeyEvent e) {
            player1.keyReleased(e);
            player2.keyReleased(e);
        }
    }
}

