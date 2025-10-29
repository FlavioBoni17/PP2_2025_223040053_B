/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package id.ac.unpas.praktikumpemograman2.modul05;

import java.awt.BorderLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingUtilities;

/**
 *
 * @author ASUS
 */
public class Tugas {
     public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                JFrame frame = new JFrame("Contoh BorderLayout dengan Aksi Tombol");
                frame.setSize(400, 300);
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setLayout(new BorderLayout());

                // 1. Komponen utama
                JLabel label = new JLabel("Silakan klik salah satu tombol.", JLabel.CENTER);

                // 2. Membuat tombol
                JButton btnNorth = new JButton("NORTH");
                JButton btnSouth = new JButton("SOUTH");
                JButton btnWest = new JButton("WEST");
                JButton btnEast = new JButton("EAST");
                JButton btnCenter = new JButton("CENTER");

                // 3. Menambahkan aksi untuk setiap tombol
                btnNorth.addActionListener(e -> label.setText("Tombol NORTH diklik!"));
                btnSouth.addActionListener(e -> label.setText("Tombol SOUTH diklik!"));
                btnWest.addActionListener(e -> label.setText("Tombol WEST diklik!"));
                btnEast.addActionListener(e -> label.setText("Tombol EAST diklik!"));
                btnCenter.addActionListener(e -> label.setText("Tombol CENTER diklik!"));

                // 4. Menambahkan komponen ke frame dengan posisi BorderLayout
                frame.add(label, BorderLayout.NORTH);
                frame.add(btnSouth, BorderLayout.SOUTH);
                frame.add(btnWest, BorderLayout.WEST);
                frame.add(btnEast, BorderLayout.EAST);
                frame.add(btnCenter, BorderLayout.CENTER);

                // 5. Tampilkan jendela
                frame.setVisible(true);
            }
        });
    }
}
