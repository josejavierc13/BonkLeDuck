import javax.imageio.ImageIO;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.*;
import java.awt.*;
import java.awt.image.ImageObserver;
import java.io.File;
import java.io.IOException;
import java.util.Random;

public class Main {
    private final Image grassImage;
    public int CircleX;
    public int CircleY;
    public JFrame mainFrame = new JFrame();

    public int Score1 = 0;
    public int Score2 = 0;
    public JLabel ScoreLabel;
    public JLabel ScoreLabel2;


    public Main() {
        mainFrame.setTitle("BONK LE DUCK");
        mainFrame.setSize(800, 800);
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setResizable(false);
        mainFrame.setBounds(500, 1, 850, 800);
        try {
            grassImage = ImageIO.read(new File("C:\\Users\\josej\\IdeaProjects\\BonkLeDuck\\IMAGES\\Grass_image.jpg"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        JPanel mainPanel = new JPanel(null) {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                drawPond(g);
                ducky(CircleX, CircleY, g);
            }
        };

        /**
         * Score Board 1
         */
        ImageIcon icon = new ImageIcon("C:\\Users\\josej\\IdeaProjects\\BonkLeDuck\\IMAGES\\ScoreBoard.png");
        Image image = icon.getImage();
        Image newImg = image.getScaledInstance(200, 150, java.awt.Image.SCALE_SMOOTH);
        icon = new ImageIcon(newImg);
        JLabel ScoreBoardLabel = new JLabel(icon);

        ScoreLabel = new JLabel(String.valueOf(Score1));
        Font labelFont = ScoreLabel.getFont();
        ScoreLabel.setFont(new Font(labelFont.getName(), Font.PLAIN, 50));

        mainPanel.add(ScoreLabel);
        mainPanel.add(ScoreBoardLabel);

        ScoreBoardLabel.setBounds(0, 600, 200, 150);
        ScoreLabel.setBounds(85, 650, 100, 50);

        /**
         * Score Board 2
         */

        ImageIcon icon2 = new ImageIcon("C:\\Users\\josej\\IdeaProjects\\BonkLeDuck\\IMAGES\\ScoreBoard.png");
        Image image2 = icon2.getImage();
        Image newImg2 = image2.getScaledInstance(200, 150, java.awt.Image.SCALE_SMOOTH);
        icon2 = new ImageIcon(newImg2);
        JLabel ScoreBoardLabel2 = new JLabel(icon2);

        ScoreLabel2 = new JLabel(String.valueOf(Score2));
        Font labelFont2 = ScoreLabel2.getFont();
        ScoreLabel2.setFont(new Font(labelFont2.getName(), Font.PLAIN, 50));


        ScoreBoardLabel2.setBounds(630, 600, 200, 150);
        ScoreLabel2.setBounds(715, 650, 100, 50);

        mainPanel.add(ScoreLabel);
        mainPanel.add(ScoreBoardLabel);
        mainPanel.add(ScoreLabel2);
        mainPanel.add(ScoreBoardLabel2);

        mainFrame.setVisible(true);
        mainFrame.add(mainPanel);
        PlayMusic();
    }

    // The rest of your code remains unchanged...

    private void drawPond(Graphics g) {
        // Draw grass background
        g.drawImage(grassImage, 0, 0, mainFrame.getWidth(), mainFrame.getHeight(), (ImageObserver) this.mainFrame);

        // Draw water in the middle as a blue circle
        int circleDiameter = 600;
        int circleX = (mainFrame.getWidth() - circleDiameter) / 2;
        int circleY = (mainFrame.getHeight() - circleDiameter) / 2;

        g.setColor(new Color(10, 180, 255));
        g.fillOval(circleX, circleY, circleDiameter, circleDiameter);

        this.CircleX = circleX;
        this.CircleY = circleY;
    }






    private void ducky(int circleX, int circleY, Graphics g) {
        Random rand = new Random();

        int randCoordinatesX = rand.nextInt(300);
        int randCoordinatesY = rand.nextInt(300);



        ImageIcon icon = new ImageIcon("C:\\Users\\josej\\IdeaProjects\\BonkLeDuck\\IMAGES\\DUCK.png");
        Image image = icon.getImage(); // transform it
        Image newImg = image.getScaledInstance(80, 80,  java.awt.Image.SCALE_SMOOTH);
        icon = new ImageIcon(newImg);
        JButton Button = new JButton(icon);
        Button.setBackground(new Color(10,  180, 255));
        Button.setBorderPainted(false);
        mainFrame.add(Button);



        Button.setBounds(randCoordinatesX + 220, randCoordinatesY + 220, 60, 80);
        Button.addActionListener(e -> {
            updateScore1(ScoreLabel);  // Call the method to update the score label
            Container parent = Button.getParent();
            parent.remove(Button);
            parent.revalidate();
            parent.repaint();
            PlayBonk();


        });


    }

    private void updateScore1(JLabel ScoreLabel) {
        Score1 += 1;
        ScoreLabel.setText(String.valueOf(Score1));
    }

    private void updateScore2(JLabel ScoreLabel2) {
        Score1 += 1;
        ScoreLabel.setText(String.valueOf(Score1));
    }

    public void PlayBonk() {
        try {
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File("C:\\Users\\josej\\IdeaProjects\\BonkLeDuck\\Sounds\\BONK.wav").getAbsoluteFile());
            Clip clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            clip.start();
        } catch(Exception ex) {
            System.out.println("Error with playing sound.");
            ex.printStackTrace();
        }
    }

    public static void PlayMusic() {
        try {
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File("C:\\Users\\josej\\IdeaProjects\\BonkLeDuck\\Sounds\\Music.wav").getAbsoluteFile());
            Clip clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            clip.loop(Clip.LOOP_CONTINUOUSLY);
            clip.start();
        } catch(Exception ex) {
            System.out.println("Error with playing sound.");
            ex.printStackTrace();
        }
    }


    public static void main(String[] args) {
        SwingUtilities.invokeLater(Main::new);




    }
}