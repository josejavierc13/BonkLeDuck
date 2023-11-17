import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.ImageObserver;
import java.io.File;
import java.io.IOException;
import java.util.Objects;
import java.util.Random;
import java.awt.image.BufferedImage;


public class Main  {
    private final Image grassImage;
    public int CircleX;
    public int CircleY;

    public JFrame mainFrame = new JFrame();



    public Main() {

        mainFrame.setTitle("BONK LE DUCK");
        mainFrame.setSize(800, 800);
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setResizable(false);

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
                ducky(CircleX, CircleY, g);


            }
        };

        mainFrame.setVisible(true);
        mainFrame.add(mainPanel);



    }


    private void drawPond(Graphics g) {
        // Draw grass background
        g.drawImage(grassImage, 0, 0, mainFrame.getWidth(), mainFrame.getHeight(), (ImageObserver) this.mainFrame);

        // Draw water in the middle as a blue circle
        int circleDiameter = 600;
        int circleX = (mainFrame.getWidth() - circleDiameter) / 2;
        int circleY = (mainFrame.getHeight() - circleDiameter) / 2 ;
        System.out.println(circleX);
        System.out.println(circleY);


        g.setColor(new Color(10,  180, 255));
        g.fillOval(circleX, circleY, circleDiameter, circleDiameter);

        this.CircleX = circleX;
        this.CircleY = circleY;
    }
    private void ducky(int circleX, int circleY, Graphics g) {
        Random rand = new Random();

        int randCoordinatesX = rand.nextInt(500);
        int randCoordinatesY = rand.nextInt(500);
        System.out.println(randCoordinatesX);
        // System.out.println(randCoordinatesY);


        ImageIcon icon = new ImageIcon("C:\\Users\\josej\\IdeaProjects\\BonkLeDuck\\IMAGES\\DUCK.png");
        Image image = icon.getImage(); // transform it
        Image newimg = image.getScaledInstance(120, 120,  java.awt.Image.SCALE_SMOOTH);
        icon = new ImageIcon(newimg);
        JButton Button = new JButton(icon);
        mainFrame.add(Button);


        Button.setBounds(randCoordinatesX + 220, randCoordinatesY + 220, 100, 100);
        Button.addActionListener(e -> {
            Container parent = Button.getParent();
            parent.remove(Button);
            parent.revalidate();
            parent.repaint();

        });


    }


    public static void main(String[] args) {
        SwingUtilities.invokeLater(Main::new);

    }
}