package id.ac.unpas.praktikumpemograman2.modul07;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

/**
 *
 * @author Flavio Boniperti Oktaviola Zyoffy
 */
public class TugasManajemenNilaiSiswaApp extends JFrame {
    
    private JTextField txtNama;
    private JTextField txtNilai;
    private JComboBox<String> cmbMakul;
    private JTable tableData;
    private DefaultTableModel tableModel;
    private JTabbedPane tabbedPane;
    
    public TugasManajemenNilaiSiswaApp() {

        setTitle("Aplikasi Manajemen Nilai Siswa");
        setSize(500, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        tabbedPane = new JTabbedPane();

        JPanel panelInput = createInputPanel();
        JPanel panelTable = createTablePanel();

        tabbedPane.addTab("Input Data", panelInput);
        tabbedPane.addTab("Daftar Nilai", panelTable);

        add(tabbedPane);
    }

    private JPanel createInputPanel() {
        JPanel panel = new JPanel(new GridLayout(5, 2, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        panel.add(new JLabel("Nama Siswa:"));
        txtNama = new JTextField();
        panel.add(txtNama);

        panel.add(new JLabel("Mata Pelajaran:"));
        String[] matkul = {
                "Matematika Dasar",
                "Bahasa Indonesia",
                "Algoritma dan Pemrograman I",
                "Praktikum Pemrograman II"
        };
        cmbMakul = new JComboBox<>(matkul);
        panel.add(cmbMakul);

        panel.add(new JLabel("Nilai (0-100):"));
        txtNilai = new JTextField();
        panel.add(txtNilai);

        JButton btnReset = new JButton("Reset");
        btnReset.addActionListener(e -> {
            txtNama.setText("");
            txtNilai.setText("");
            cmbMakul.setSelectedIndex(0);
        });

        JButton btnSimpan = new JButton("Simpan Data");
        btnSimpan.addActionListener(e -> prosesSimpan());

        panel.add(btnReset);
        panel.add(btnSimpan);

        return panel;
    }

    private JPanel createTablePanel() {
        JPanel panel = new JPanel(new BorderLayout());

        String[] kolom = {"Nama Siswa", "Mata Pelajaran", "Nilai", "Grade"};
        tableModel = new DefaultTableModel(kolom, 0);

        tableData = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(tableData);

        JButton btnHapus = new JButton("Hapus Data");
        btnHapus.addActionListener(e -> {
            int row = tableData.getSelectedRow();
            if (row > -1) {
                tableModel.removeRow(row);
                JOptionPane.showMessageDialog(this, "Data berhasil dihapus!");
            } else {
                JOptionPane.showMessageDialog(this, "Pilih baris yang ingin dihapus!");
            }
        });

        panel.add(scrollPane, BorderLayout.CENTER);
        panel.add(btnHapus, BorderLayout.SOUTH);

        return panel;
    }

    private void prosesSimpan() {
        String nama = txtNama.getText();
        String matkul = (String) cmbMakul.getSelectedItem();
        String strNilai = txtNilai.getText();

        if (nama.trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Nama tidak boleh kosong!", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // validasi minimal 3 karakter
        if (nama.trim().length() < 3) {
            JOptionPane.showMessageDialog(this, "Nama minimal 3 karakter!", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        int nilai;
        try {
            nilai = Integer.parseInt(strNilai);

            if (nilai < 0 || nilai > 100) {
                JOptionPane.showMessageDialog(this, "Nilai harus 0 - 100!", "Error", JOptionPane.WARNING_MESSAGE);
                return;
            }

        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Nilai harus berupa angka!", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // grade versi switch-case
        String grade;
        switch (nilai / 10) {
            case 10, 9 -> grade = "A";
            case 8 -> grade = "AB";
            case 7 -> grade = "B";
            case 6 -> grade = "BC";
            case 5 -> grade = "C";
            case 4 -> grade = "D";
            default -> grade = "E";
        }

        tableModel.addRow(new Object[]{nama, matkul, nilai, grade});

        JOptionPane.showMessageDialog(this, "Data berhasil disimpan!");

        txtNama.setText("");
        txtNilai.setText("");

        tabbedPane.setSelectedIndex(1);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new TugasManajemenNilaiSiswaApp().setVisible(true);
        });
    }
    
}
