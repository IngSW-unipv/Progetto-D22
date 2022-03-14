package it.unipv.po.aioobe.trenissimo.view;

import it.unipv.po.aioobe.trenissimo.model.persistence.service.CachedRoutesService;
import it.unipv.po.aioobe.trenissimo.model.persistence.service.CachedStopTimesService;
import it.unipv.po.aioobe.trenissimo.model.persistence.service.CachedStopsService;
import it.unipv.po.aioobe.trenissimo.model.persistence.service.CachedTripsService;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.net.URI;

public class HiddenJFrame extends JFrame {
    public static final int WIDTH = 300;
    public static final int HEIGHT = 300;
    public HiddenJFrame() {
        setSize(WIDTH,HEIGHT);
        setTitle("Houston Electric Utilities");
        setResizable(false);
        setLayout(new BorderLayout());
        setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("homePage/LogoIcona.png")));

        JPanel listPane = new JPanel();
        listPane.setLayout(new BoxLayout(listPane, BoxLayout.Y_AXIS));

        JPanel titlePane = new JPanel();
        titlePane.setLayout(new BoxLayout(titlePane, BoxLayout.X_AXIS));
        JLabel lblTitle = new JLabel("Trenissimo", SwingConstants.CENTER);
        lblTitle.setBorder(javax.swing.BorderFactory.createEmptyBorder(8, 8, 8, 8));
        titlePane.add(lblTitle);

        JTextArea textArea = new JTextArea(2, 20);
        textArea.setText("Made by: \nVergani Valeria, Zamboni Fabio, Ferrari Davide, Frigatti Andrea, Martini Matteo, Guarrasi Claudio");
        textArea.setBorder(javax.swing.BorderFactory.createEmptyBorder(8, 8, 8, 8));
        textArea.setWrapStyleWord(true);
        textArea.setLineWrap(true);
        textArea.setOpaque(false);
        textArea.setEditable(false);
        textArea.setFocusable(false);

        JPanel bottomPane = new JPanel();
        bottomPane.setLayout(new BoxLayout(bottomPane, BoxLayout.X_AXIS));
        JButton btnClose = new JButton("Reset cache");
        btnClose.addActionListener(e -> {
            CachedStopsService.getInstance().clearCache();
            CachedTripsService.getInstance().clearCache();
            CachedRoutesService.getInstance().clearCache();
            CachedStopTimesService.getInstance().clearCache();
            System.out.println("Cache cleared!");
        });


        bottomPane.add(btnClose);
        bottomPane.setBorder(javax.swing.BorderFactory.createEmptyBorder(8, 8, 8, 8));

        JPanel linkPane = new JPanel();
        linkPane.setLayout(new BoxLayout(linkPane, BoxLayout.X_AXIS));
        JLabel lblLink = new JLabel("Visit project page");
        lblLink.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        lblLink.setForeground(Color.BLUE.darker());
        lblLink.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                try {
                    Desktop.getDesktop().browse(new URI("https://github.com/IngSW-unipv/Progetto-D22"));
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        });

        linkPane.add(lblLink);
        linkPane.setBorder(javax.swing.BorderFactory.createEmptyBorder(8, 8, 8, 8));

        listPane.add(titlePane);
        listPane.add(textArea);
        listPane.add(linkPane);
        listPane.add(bottomPane);

        Container contentPane = getContentPane();
        contentPane.add(listPane, BorderLayout.CENTER);
    }
}


