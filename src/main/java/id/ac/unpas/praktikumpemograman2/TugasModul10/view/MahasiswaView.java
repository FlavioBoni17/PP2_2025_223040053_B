/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package id.ac.unpas.praktikumpemograman2.TugasModul10.view;

import id.ac.unpas.praktikumpemograman2.TugasModul10.model.Mahasiswa;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.util.List;

/**
 *
 * @author Flavio
 */
public class MahasiswaView extends JFrame {
    private JTextField txtNama, txtNIM, txtJurusan;
    private JButton btnSimpan, btnEdit, btnHapus, btnClear;
    private JTable tableMahasiswa;
    private DefaultTableModel model;
    private JTextField txtCari;
    private JButton btnCari;

    public MahasiswaView() {
        setTitle("Aplikasi CRUD Mahasiswa MVC");
        setSize(600, 500);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        JPanel panelForm = new JPanel(new GridLayout(4, 2, 10, 10));
        panelForm.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        panelForm.add(new JLabel("Nama:"));
        txtNama = new JTextField();
        panelForm.add(txtNama);

        panelForm.add(new JLabel("NIM:"));
        txtNIM = new JTextField();
        panelForm.add(txtNIM);

        panelForm.add(new JLabel("Jurusan:"));
        txtJurusan = new JTextField();
        panelForm.add(txtJurusan);

        JPanel panelTombol = new JPanel(new FlowLayout());
        btnSimpan = new JButton("Simpan");
        btnEdit = new JButton("Edit");
        btnHapus = new JButton("Hapus");
        btnClear = new JButton("Clear");

        panelTombol.add(btnSimpan);
        panelTombol.add(btnEdit);
        panelTombol.add(btnHapus);
        panelTombol.add(btnClear);

        JPanel panelAtas = new JPanel(new BorderLayout());
        panelAtas.add(panelForm, BorderLayout.CENTER);
        panelAtas.add(panelTombol, BorderLayout.SOUTH);
        add(panelAtas, BorderLayout.NORTH);

        model = new DefaultTableModel();
        model.addColumn("No");
        model.addColumn("Nama");
        model.addColumn("NIM");
        model.addColumn("Jurusan");

        tableMahasiswa = new JTable(model);
        JScrollPane scrollPane = new JScrollPane(tableMahasiswa);
        add(scrollPane, BorderLayout.CENTER);

        JPanel panelCari = new JPanel(new FlowLayout(FlowLayout.LEFT));
        panelCari.add(new JLabel("Cari Nama:"));

        txtCari = new JTextField(20);
        panelCari.add(txtCari);

        btnCari = new JButton("Cari");
        panelCari.add(btnCari);

        panelAtas.add(panelCari, BorderLayout.NORTH);
    }

    public String getNama() 
    { 
        return txtNama.getText(); 
    }
    
    public String getNim() 
    { 
        return txtNIM.getText(); 
    }
    
    public String getJurusan() 
    { 
        return txtJurusan.getText(); 
    }
    
    public String getCariInput() 
    { 
        return txtCari.getText(); 
    }

    public void setNama(String nama) 
    { 
        txtNama.setText(nama); 
    }
    
    public void setNim(String nim) 
    { 
        txtNIM.setText(nim); 
    }
    
    public void setJurusan(String jurusan) 
    { 
        txtJurusan.setText(jurusan); 
    }
    
    public void addSimpanListener(ActionListener listener) 
    { 
        btnSimpan.addActionListener(listener); 
    }
    
    public void addEditListener(ActionListener listener) 
    { 
        btnEdit.addActionListener(listener); 
    }
    
    public void addHapusListener(ActionListener listener) 
    { 
        btnHapus.addActionListener(listener); 
    }
    
    public void addClearListener(ActionListener listener) 
    { 
        btnClear.addActionListener(listener); 
    }
    
    public void addCariListener(ActionListener listener) 
    { 
        btnCari.addActionListener(listener); 
    }
    
    public void addTableMouseListener(MouseAdapter adapter) 
    { 
        tableMahasiswa.addMouseListener(adapter); 
    }

    public JTable getTable() 
    { 
        return tableMahasiswa; 
    }
    
    public void updateTable(List<Mahasiswa> data) 
    {
        model.setRowCount(0);
        int no = 1;
        for (Mahasiswa m : data) 
        {
            model.addRow(new Object[]{ no++, m.getNama(), m.getNim(), m.getJurusan() });
        }
    }

    public void clearForm() 
    {
        txtNama.setText("");
        txtNIM.setText("");
        txtJurusan.setText("");
    }

    public void showMessage(String message) 
    {
        JOptionPane.showMessageDialog(this, message);
    }
}
