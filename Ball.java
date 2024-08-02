import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;

public class Ball {
    private int x, y, size;
    private int dx, dy;

    public Ball(int x, int y) {
        this.x = x;
        this.y = y;
        this.size = 20;
        this.dx = 3;
        this.dy = 3;
    }

    public void move() {
        x += dx;
        y += dy;
    }

    public void reverseXDirection() {
        dx = -dx;
    }

    public void reverseYDirection() {
        dy = -dy;
    }

    public void draw(Graphics2D g) {
        g.setColor(Color.WHITE);
        g.fillOval(x, y, size, size);
    }

    public Rectangle getBounds() {
        return new Rectangle(x, y, size, size);
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getSize() {
        return size;
    }
}
