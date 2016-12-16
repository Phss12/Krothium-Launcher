package kml.gui;

import kml.Constants;
import kml.Kernel;
import kml.objects.Browser;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 * Created by darkl on 18/11/2016.
 */
public class Main extends JFrame{
    private JPanel panel1;
    private JButton button1;
    private BackgroundPanel footerPanel;
    private BackgroundPanel headPanel;
    private BackgroundPanel contentPanel;
    private JLabel news;
    private JLabel skins;
    private JLabel settings;
    private JLabel options;
    private JLabel selected;
    private ImageIcon button_normal = new ImageIcon(new ImageIcon(Login.class.getResource("/kml/gui/textures/button_normal.png")).getImage().getScaledInstance(240, 40, Image.SCALE_SMOOTH));
    private ImageIcon button_hover = new ImageIcon(new ImageIcon(Login.class.getResource("/kml/gui/textures/button_hover.png")).getImage().getScaledInstance(240, 40, Image.SCALE_SMOOTH));
    private final Kernel kernel;
    private final Login loginPanel;
    private final Browser browser;
    private boolean componentsDisabled = false;

    public Main(Kernel k){
        this.kernel = k;
        this.loginPanel = new Login(k);
        this.browser = new Browser();
        setSize(600, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Krothium Minecraft Launcher " + Constants.KERNEL_BUILD_NAME);
        setIconImage(new ImageIcon(Login.class.getResource("/kml/gui/textures/icon.png")).getImage());
        contentPanel.setImage(new ImageIcon(Login.class.getResource("/kml/gui/textures/background.png")).getImage());
        contentPanel.setLayout(new BorderLayout());
        footerPanel.setImage(new ImageIcon(Login.class.getResource("/kml/gui/textures/login-background.png")).getImage());
        headPanel.setImage(new ImageIcon(Login.class.getResource("/kml/gui/textures/login-background.png")).getImage());
        setContentPane(panel1);
        news.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                if (news != selected){
                    news.setForeground(Color.GREEN);
                }
                news.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            }
        });
        news.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseExited(MouseEvent e) {
                news.setForeground(Color.WHITE);
            }
        });
        news.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                setSelected(news);
                news.setForeground(Color.WHITE);
            }
        });
        skins.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                if (skins != selected){
                    skins.setForeground(Color.GREEN);
                }
                skins.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            }
        });
        skins.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseExited(MouseEvent e) {
                skins.setForeground(Color.WHITE);
            }
        });
        skins.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                setSelected(skins);
                skins.setForeground(Color.WHITE);
            }
        });
        settings.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                if (settings != selected){
                    settings.setForeground(Color.GREEN);
                }
                settings.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            }
        });
        settings.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseExited(MouseEvent e) {
                settings.setForeground(Color.WHITE);
            }
        });
        settings.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                setSelected(settings);
                settings.setForeground(Color.WHITE);
            }
        });
        options.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                if (options != selected){
                    options.setForeground(Color.GREEN);
                }
                options.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            }
        });
        options.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseExited(MouseEvent e) {
                options.setForeground(Color.WHITE);
            }
        });
        options.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                setSelected(options);
                options.setForeground(Color.WHITE);
            }
        });

        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                kernel.exitSafely();
            }
        });

    }
    @Override
    public void setVisible(boolean b){
        super.setVisible(b);
        setSelected(news);
        if (!kernel.isAuthenticated()){
            this.contentPanel.add(loginPanel.getPanel());
            this.setDisable(true);
        }
    }
    private void setSelected(JLabel l){
        if (!componentsDisabled){
            this.contentPanel.removeAll();
            if (selected == null ){
                selected = l;
                selected.setIcon(new ImageIcon(new ImageIcon(Login.class.getResource("/kml/gui/textures/menu_label.png")).getImage().getScaledInstance(selected.getWidth() + 15, selected.getHeight() + 15, Image.SCALE_SMOOTH)));
            } else if (l != selected) {
                selected.setIcon(null);
                selected = l;
                selected.setIcon(new ImageIcon(new ImageIcon(Login.class.getResource("/kml/gui/textures/menu_label.png")).getImage().getScaledInstance(selected.getWidth() + 15, selected.getHeight() + 15, Image.SCALE_SMOOTH)));
            }
            if (l.equals(news)){
                System.out.println("PASA");
                this.contentPanel.add(this.browser.getPanel());
                this.browser.loadURL("https://google.es");
            }
        }
    }
    public void setDisable(boolean b){
        if (b != componentsDisabled){
            this.news.setEnabled(!b);
            this.settings.setEnabled(!b);
            this.options.setEnabled(!b);
            this.skins.setEnabled(!b);
            componentsDisabled = b;
        }
    }
}