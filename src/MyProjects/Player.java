package MyProjects;

import javax.sound.sampled.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

public class Player extends JFrame implements ActionListener {
    String[] musics = new String[50];
    int count=0;
    JButton select;
    JButton play;
    JButton stop;
    JButton restart;
    String music_name = "";
    JTextField input;

    Player() throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("C:\\Music player\\musics.txt"));
        try {
            String line = br.readLine();

            while (line != null) {
                musics[count] = line;
                line = br.readLine();
                count++;
            }
        } finally {
            br.close();
        }

        ImageIcon icon = new ImageIcon("C:\\Music player\\icon.jpg");
        JFrame frame = new JFrame("Music Player");
        JPanel upper = new JPanel();
        JPanel lower = new JPanel();

        input = new JTextField();
        input.setPreferredSize(new Dimension(225,37));
        input.setCaretColor(Color.BLACK);
        input.setBackground(Color.WHITE);
        input.setForeground(Color.BLACK);
        input.setFont(new Font("Consolas",Font.PLAIN,17));

        select = new JButton("Select");
        select.setFocusable(false);
        select.setBackground(Color.WHITE);
        select.setForeground(Color.BLACK);
        select.addActionListener(this);

        play = new JButton("Play");
        play.setFocusable(false);
        play.setBackground(Color.WHITE);
        play.setForeground(Color.BLACK);
        play.addActionListener(this);

        stop = new JButton("Stop");
        stop.setFocusable(false);
        stop.setBackground(Color.WHITE);
        stop.setForeground(Color.BLACK);
        stop.addActionListener(this);

        restart = new JButton("Restart");
        restart.setFocusable(false);
        restart.setBackground(Color.WHITE);
        restart.setForeground(Color.BLACK);
        restart.addActionListener(this);

        lower.setBounds(0,300,350,200);
        lower.setBackground(Color.BLACK);
        lower.setLayout(new FlowLayout());
        lower.add(select);
        lower.add(input);
        lower.add(play);
        lower.add(stop);
        lower.add(restart);

        upper.setBounds(0,0,350,300);
        upper.setBackground(Color.BLACK);
        upper.setLayout(new GridLayout(count,1,0,-100));

        JLabel[] labels = new JLabel[50];

        for(int i=0; i<count; i++){
            labels[i]=new JLabel();
            labels[i].setForeground(Color.WHITE);
            labels[i].setText(musics[i]);
            upper.add(labels[i]);
        }

        frame.setLayout(null);
        frame.add(upper);
        frame.add(lower);
        frame.setIconImage(icon.getImage());
        frame.setSize(350,500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);

    }
    int flag=0;
    @Override
    public void actionPerformed(ActionEvent e) {

        if(e.getSource()==select){

            music_name = input.getText();
            try {
                if(flag==1)
                {
                    try {
                        play(3," ");
                    } catch (UnsupportedAudioFileException  | LineUnavailableException | IOException ex) {
                        ex.printStackTrace();
                    }
                }
                play(1,music_name);
                flag = 1;
            } catch (UnsupportedAudioFileException  | LineUnavailableException | IOException ex) {
                ex.printStackTrace();
            }
        }
        if(e.getSource()==play) {
            try {
                play(2,"");
            } catch (UnsupportedAudioFileException  | LineUnavailableException | IOException ex) {
                ex.printStackTrace();
            }
        }
        if(e.getSource()==stop)
        {
            try {
                play(3," ");
            } catch (UnsupportedAudioFileException  | LineUnavailableException | IOException ex) {
                ex.printStackTrace();
            }
        }
        if(e.getSource()==restart)
        {
            try {
                play(4,"");
            } catch (UnsupportedAudioFileException  | LineUnavailableException | IOException ex) {
                ex.printStackTrace();
            }
        }
    }
    Clip clip = null;

    void play(int number,String name) throws UnsupportedAudioFileException, IOException, LineUnavailableException {

        if(number==1)
        {
            File file = new File("C:\\Music player\\"+name+".wav");
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(file);
            clip = AudioSystem.getClip();
            clip.open(audioStream);
        }
        if(number==2)
            clip.start();

        if(number==3)
            clip.stop();

        if(number==4)
            clip.setMicrosecondPosition(0);
    }
}

