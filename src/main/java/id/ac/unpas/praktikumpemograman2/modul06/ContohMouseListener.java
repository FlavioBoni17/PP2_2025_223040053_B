/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package id.ac.unpas.praktikumpemograman2.modul06;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 *
 * @author Flavio Boniperti Oktaviola Zyoffy
 */
public class ContohMouseListener {
    public static void main(String[] args) {
        // 1. Inisialisasi Frame (Jendela)
        JFrame frame = new JFrame("Contoh MouseListener");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(300, 300);

        // 2. Buat komponen (event source)
        JPanel panel = new JPanel();
        panel.setBackground(Color.LIGHT_GRAY);
        panel.setPreferredSize(new Dimension(200, 200));

        // 3. Buat event listener menggunakan MouseAdapter
        MouseAdapter adapter = new MouseAdapter() {

            // Metode dieksekusi saat kursor mouse masuk ke area panel
            @Override
            public void mouseEntered(MouseEvent e) {
                // Saat mouse masuk, ubah warna jadi biru
                panel.setBackground(Color.CYAN);
            }

            // Metode dieksekusi saat kursor mouse keluar dari area panel
            @Override
            public void mouseExited(MouseEvent e) {
                // Saat mouse keluar, ubah warna kembali ke abu-abu muda
                panel.setBackground(Color.LIGHT_GRAY);
            }

            // Metode dieksekusi saat tombol mouse diklik
            @Override
            public void mouseClicked(MouseEvent e) {
                // Tampilkan koordinat x dan y saat mouse diklik
                System.out.println("Mouse diklik di: x=" + e.getX() + ", y=" + e.getY());
            }
        };

        // 4. Daftarkan listener ke source
        panel.addMouseListener(adapter);

        // 5. Tambahkan komponen ke frame dan tampilkan
        frame.add(panel);
        frame.setVisible(true);
    }
}
