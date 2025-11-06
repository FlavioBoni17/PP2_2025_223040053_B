/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package id.ac.unpas.praktikumpemograman2.modul06;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.BorderLayout;

/**
 *
 * @author Flavio Boniperti Oktaviola Zyoffy
 */
public class KalkulatorSederhana {
    public static void main(String[] args) {
        // 1. Inisialisasi Frame (Jendela Utama)
        // Menggunakan BorderLayout sebagai layout utama frame
        JFrame frame = new JFrame("Kalkulator Sederhana");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(300, 400); // Ukuran jendela kalkulator

        // 2. Buat Layar Kalkulator (JTextField)
        JTextField display = new JTextField("0");
        display.setEditable(false); // Layar tidak dapat diedit
        display.setHorizontalAlignment(SwingConstants.RIGHT); // Teks rata kanan
        display.setFont(new Font("Arial", Font.BOLD, 24));

        // 3. Buat Panel untuk Tombol
        // Gunakan GridLayout untuk mengatur 16 tombol dalam 4 baris dan 4 kolom (4x4)
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(4, 4, 10, 10)); // 4x4 dengan jarak 10 piksel

        // 4. Definisikan Teks Tombol
        String[] buttonLabels = {
            "7", "8", "9", "/",
            "4", "5", "6", "*",
            "1", "2", "3", "-",
            "0", "C", "=", "+" // "C" untuk Clear, "=" untuk Hasil
        };

        // 5. Tambahkan Tombol ke Panel
        for (String label : buttonLabels) {
            JButton button = new JButton(label);
            button.setFont(new Font("Arial", Font.PLAIN, 18));
            buttonPanel.add(button);
        }

        // 6. Atur Komponen dalam Frame menggunakan BorderLayout
        // Layar (JTextField) diletakkan di bagian atas (NORTH)
        frame.add(display, BorderLayout.NORTH);

        // Panel Tombol diletakkan di bagian tengah (CENTER)
        frame.add(buttonPanel, BorderLayout.CENTER);

        // 7. Tampilkan Frame
        frame.setVisible(true);
    }
}
