import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.Random;
import java.awt.image.BufferedImage;


public class Main extends JFrame {
    private Image grassImage;
    public Image Ducky;
    public int circlex;
    public int circley;

    public Main() {
        setTitle("BONK LE DUCK");
        setSize(800, 800);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        try {
            grassImage = ImageIO.read(new File("C:\\Users\\josej\\IdeaProjects\\BonkLeDuck\\IMAGES\\Grass_image.jpg"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        JPanel mainPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                drawPond(g);
                ducky(circlex,circley,g);

            }
        };

        getContentPane().add(mainPanel);

        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void drawPond(Graphics g) {
        // Draw grass background
        g.drawImage(grassImage, 0, 0, getWidth(), getHeight(), this);

        // Draw water in the middle as a blue circle
        int circleDiameter = 600;
        int circleX = (getWidth() - circleDiameter) / 2;
        int circleY = (getHeight() - circleDiameter) / 2 ;
        System.out.println(circleX);
        System.out.println(circleY);


        g.setColor(new Color(10,  180, 255));
        g.fillOval(circleX, circleY, circleDiameter, circleDiameter);

        this.circlex = circleX;
        this.circley = circleY;
    }
    private void ducky(int circleX, int circleY, Graphics g){
        Random rand = new Random();

        int randCoordinatesX = rand.nextInt(2000);
        int randCoordinatesY = rand.nextInt(2000);
        System.out.println(randCoordinatesX);


        try {
            BufferedImage bufferedImage = ImageIO.read(new File("C:\\Users\\josej\\IdeaProjects\\BonkLeDuck\\IMAGES\\DUCK.png"));
            Image image = bufferedImage.getScaledInstance(20, 20, Image.SCALE_DEFAULT);
            g.drawImage(image, randCoordinatesX + 600, randCoordinatesY + 600, 100, 100, this);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(Main::new);
    }
}