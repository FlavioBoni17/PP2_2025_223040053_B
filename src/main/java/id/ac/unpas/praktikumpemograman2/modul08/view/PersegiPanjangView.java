/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package id.ac.unpas.praktikumpemograman2.modul08.view;

import java.awt.GridLayout;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

/**
 *
 * @author Flavio Boniperti Oktaviola Zyoffy
 */
public class PersegiPanjangView extends JFrame {

    // Komponen UI
    private JTextField txtPanjang = new JTextField(10);
    private JTextField txtLebar = new JTextField(10);
    private JLabel lblHasilLuas = new JLabel("-");
    private JLabel lblHasilKeliling = new JLabel("-");

    private JButton btnHitungLuas = new JButton("Hitung Luas");
    private JButton btnHitungKeliling = new JButton("Hitung Keliling");
    private JButton btnReset = new JButton("Reset");

    public PersegiPanjangView() {
        // Pengaturan UI
        this.setTitle("MVC Kalkulator");
        this.setSize(350, 250);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(new GridLayout(6, 2, 6, 6));

        // Tambahkan komponen ke frame
        this.add(new JLabel("Panjang:"));
        this.add(txtPanjang);

        this.add(new JLabel("Lebar:"));
        this.add(txtLebar);

        this.add(new JLabel("Luas:"));
        this.add(lblHasilLuas);

        this.add(new JLabel("Keliling:"));
        this.add(lblHasilKeliling);

        this.add(btnHitungLuas);
        this.add(btnHitungKeliling);

        this.add(new JLabel(""));
        this.add(btnReset);
    }

    // Getter untuk input
    public double getPanjang() {
        return Double.parseDouble(txtPanjang.getText());
    }

    public double getLebar() {
        return Double.parseDouble(txtLebar.getText());
    }

    // Setter output
    public void setHasilLuas(double hasil) {
        lblHasilLuas.setText(String.valueOf(hasil));
    }

    public void setHasilKeliling(double hasil) {
        lblHasilKeliling.setText(String.valueOf(hasil));
    }

    // Reset form
    public void resetForm() {
        txtPanjang.setText("");
        txtLebar.setText("");
        lblHasilLuas.setText("-");
        lblHasilKeliling.setText("-");
    }

    // Error pop-up
    public void tampilkanPesanError(String pesan) {
        JOptionPane.showMessageDialog(this, pesan);
    }

    // Listener methods (untuk Controller)
    public void addHitungLuasListener(ActionListener listener) {
        btnHitungLuas.addActionListener(listener);
    }

    public void addHitungKelilingListener(ActionListener listener) {
        btnHitungKeliling.addActionListener(listener);
    }

    public void addResetListener(ActionListener listener) {
        btnReset.addActionListener(listener);
    }
}