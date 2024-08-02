import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;

public class Paddle {
    private int x, y, width, height;
    private int dy;
    private int upKey, downKey;
    private static final int SPEED = 5;

    public Paddle(int x, int y, int upKey, int downKey) {
        this.x = x;
        this.y = y;
        this.width = 20;
        this.height = 100;
        this.upKey = upKey;
        this.downKey = downKey;
    }

    public void move() {
        y += dy;
        if (y < 0) y = 0;
        if (y > 500) y = 500; // Panel height - Paddle height
    }

    public void draw(Graphics2D g) {
        g.setColor(Color.WHITE);
        g.fillRect(x, y, width, height);
    }

    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == upKey) {
            dy = -SPEED;
        } else if (e.getKeyCode() == downKey) {
            dy = SPEED;
        }
    }

    public void keyReleased(KeyEvent e) {
        if (e.getKeyCode() == upKey || e.getKeyCode() == downKey) {
            dy = 0;
        }
    }

    public Rectangle getBounds() {
        return new Rectangle(x, y, width, height);
    }
}
