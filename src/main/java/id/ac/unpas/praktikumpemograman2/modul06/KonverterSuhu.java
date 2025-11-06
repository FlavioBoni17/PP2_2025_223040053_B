/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package id.ac.unpas.praktikumpemograman2.modul06;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane; // Untuk menampilkan pesan error
import javax.swing.JTextField;

/**
 *
 * @author Flavio Boniperti Oktaviola Zyoffy
 */
public class KonverterSuhu {

    public static void main(String[] args) {
        // 1. Buat Frame dan atur layout (GridLayout: 3 baris, 2 kolom)
        JFrame frame = new JFrame("Konverter Suhu C-F");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(300, 150);
        frame.setLayout(new GridLayout(3, 2, 5, 5)); // 3 baris, 2 kolom, jarak 5px antar sel

        // 2. Buat Komponen
        JLabel celciusLabel = new JLabel("Celcius:");
        JTextField celciusInput = new JTextField(10);
        JButton konversiButton = new JButton("Konversi");
        JLabel fahrenheitLabel = new JLabel("Fahrenheit:");
        JLabel hasilLabel = new JLabel(""); // Label untuk menampilkan hasil konversi

        // 3. Tambahkan Komponen ke Frame
        frame.add(celciusLabel);
        frame.add(celciusInput);
        frame.add(konversiButton);
        frame.add(new JLabel("")); // Kosongkan sel agar layout tetap seimbang
        frame.add(fahrenheitLabel);
        frame.add(hasilLabel);

        // 4. Tambahkan Event Handling untuk Tombol
        konversiButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    // Ambil input dari JTextField
                    String inputCelciusText = celciusInput.getText();

                    // Ubah ke tipe data double
                    double celcius = Double.parseDouble(inputCelciusText);

                    // Rumus konversi: F = (C × 9/5) + 32
                    double fahrenheit = (celcius * 9.0 / 5.0) + 32.0;

                    // Tampilkan hasil ke JLabel
                    hasilLabel.setText(String.format("%.2f °F", fahrenheit));

                } catch (NumberFormatException ex) {
                    // Jika input bukan angka, tampilkan pesan error
                    JOptionPane.showMessageDialog(
                        frame,
                        "Input tidak valid! Masukkan angka untuk Celcius.",
                        "Error Input",
                        JOptionPane.ERROR_MESSAGE
                    );
                    hasilLabel.setText("Error");
                }
            }
        });

        // 5. Tampilkan Frame
        frame.setVisible(true);
    }
}