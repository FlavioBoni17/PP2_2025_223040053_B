/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package id.ac.unpas.praktikumpemograman2.modul10;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.table.DefaultTableModel;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

/**
 *
 * @author Flavio Boniperti Oktaviola Zyoffy
 */
public class MahasiswaApp extends JFrame {
    JTextField txtNama, txtNIM, txtJurusan;
    JButton btnSimpan, btnEdit, btnHapus, btnClear;
    JTable tableMahasiswa;
    DefaultTableModel model;
    
    //Latihan 3
    JTextField txtCari;
    JButton btnCari;
    
    public MahasiswaApp() 
    {
        setTitle("Aplikasi CRUD Mahasiswa JDBC");
        setSize(600,500);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());
        
        JPanel panelForm = new JPanel(new GridLayout(4,2,10,10));
        panelForm.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
        
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
        
        //Latihan 3
        JPanel panelCari = new JPanel(new FlowLayout(FlowLayout.LEFT));
        panelCari.add(new JLabel("Cari Nama:"));
        
        txtCari = new JTextField(20);
        panelCari.add(txtCari);

        btnCari = new JButton("Cari");
        panelCari.add(btnCari);

        panelAtas.add(panelCari, BorderLayout.NORTH);
        
        tableMahasiswa.addMouseListener(new MouseAdapter() 
        {
            @Override
            public void mouseClicked(MouseEvent e) 
            {
                int row = tableMahasiswa.getSelectedRow();
                txtNama.setText(model.getValueAt(row, 1).toString());
                txtNIM.setText(model.getValueAt(row, 2).toString());
                txtJurusan.setText(model.getValueAt(row, 3).toString());
            }
        });
        
        btnSimpan.addActionListener(e -> tambahData());
        btnEdit.addActionListener(e -> ubahData());
        btnHapus.addActionListener(e -> hapusData());
        btnClear.addActionListener(e -> kosongkanForm());
        
        //Latihan 3
        btnCari.addActionListener(e -> cariData());
        
        loadData();
    }
    
    private void loadData() 
    {
        model.setRowCount(0);
        try
        {
            Connection conn = KoneksiDB.configDB();
            Statement stm = conn.createStatement();
            ResultSet res = stm.executeQuery("SELECT * FROM mahasiswa");
            
            int no = 1;
            while(res.next())
            {
                model.addRow(new Object[] 
                {
                    no++,
                    res.getString("nama"),
                    res.getString("nim"),
                    res.getString("jurusan")
                });
            }
        } 
        catch(Exception e)
        {
            JOptionPane.showMessageDialog(this, "Gagal Load Data: " + e.getMessage());
        }
    }
    
    private void tambahData()
    {
        //Latihan 2
        if (!validasiInput()) {
            return;
        }
        
        //Latihan 4
        if (cekNimSudahAda(txtNIM.getText())) 
        {
            JOptionPane.showMessageDialog(this, "NIM sudah terdaftar!");
            return;
        }
        
        try
        {
            String sql = "INSERT INTO mahasiswa (nama, nim, jurusan) VALUES (?, ?, ?)";
            Connection conn = KoneksiDB.configDB();
            PreparedStatement pst = conn.prepareStatement(sql);
            
            pst.setString(1, txtNama.getText());
            pst.setString(2, txtNIM.getText());
            pst.setString(3, txtJurusan.getText());
            
            pst.execute();
            JOptionPane.showMessageDialog(this, "Data Berhasil Disimpan");
            loadData();
            kosongkanForm();
        }
        catch(Exception e)
        {
            JOptionPane.showMessageDialog(this, "Gagal Simpan: "+ e.getMessage());
        }
    }
    
    private void ubahData()
    {
        //Latihan 2
        if (!validasiInput()) {
            return;
        }
        
        try
        {
            String sql = "UPDATE mahasiswa SET nama = ?, jurusan = ? WHERE nim = ?";
            Connection conn = KoneksiDB.configDB();
            PreparedStatement pst = conn.prepareStatement(sql);
            
            pst.setString(1, txtNama.getText());
            pst.setString(2, txtJurusan.getText());
            pst.setString(3, txtNIM.getText());
            
            pst.executeUpdate();
            JOptionPane.showMessageDialog(this, "Data Berhasil Diubah");
            loadData();
            kosongkanForm();
        }
        catch(Exception e)
        {
            JOptionPane.showMessageDialog(this, "Gagal Edit: " + e.getMessage());
        }
    }
    
    private void hapusData()
    {
        try
        {
            String sql = "DELETE FROM mahasiswa WHERE nim = ?";
            Connection conn = KoneksiDB.configDB();
            PreparedStatement pst = conn.prepareStatement(sql);

            pst.setString(1, txtNIM.getText());

            pst.execute();
            JOptionPane.showMessageDialog(this, "Data Berhasil Dihapus");
            loadData();
            kosongkanForm();  
        }
        catch(Exception e)
        {
            JOptionPane.showMessageDialog(this, "Gagal Hapus" + e.getMessage());
        }
    }
    
    private void kosongkanForm()
    {
        txtNama.setText(null);
        txtNIM.setText(null);
        txtJurusan.setText(null);
    }
    
    //Latihan 2
    private boolean validasiInput() 
    {
        // Cek kosong
        if (txtNama.getText().trim().isEmpty() || txtNIM.getText().trim().isEmpty()) 
        {
            JOptionPane.showMessageDialog(this, "Data tidak boleh kosong!");
            return false;
        }

        // Validasi Nama (tidak boleh angka)
        if (!txtNama.getText().matches("[a-zA-Z\\s]+")) 
        {
            JOptionPane.showMessageDialog(this, "Nama tidak boleh menggunakan angka!");
            return false;
        }

        // Validasi NIM
        if (!txtNIM.getText().matches("\\d+")) 
        {
            JOptionPane.showMessageDialog(this, "NIM hanya boleh diisi angka!");
            return false;
        }
        return true;
    }
    
    //Latihan 3
    private void cariData()
    {
        model.setRowCount(0);
        try 
        {
            String sql = "SELECT * FROM mahasiswa WHERE nama LIKE ?";
            Connection conn = KoneksiDB.configDB();
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setString(1, "%" + txtCari.getText() + "%");

            ResultSet rs = pst.executeQuery();
            int no = 1;

            while (rs.next()) 
            {
                model.addRow(new Object[]
                {
                    no++,
                    rs.getString("nama"),
                    rs.getString("nim"),
                    rs.getString("jurusan")
                });
            }
        } 
        catch (Exception e) 
        {
            JOptionPane.showMessageDialog(this, "Gagal Mencari: " + e.getMessage());
        }
    }
    
    //Latihan 4
    private boolean cekNimSudahAda(String nim) 
    {
        try 
        {
            String sql = "SELECT nim FROM mahasiswa WHERE nim = ?";
            Connection conn = KoneksiDB.configDB();
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setString(1, nim);

            ResultSet rs = pst.executeQuery();
            return rs.next();
        } 
        catch (Exception e) 
        {
            JOptionPane.showMessageDialog(this, "Gagal cek NIM: " + e.getMessage());
            return true;
        }
    }
    
    public static void main(String[] args){
        SwingUtilities.invokeLater(() -> new MahasiswaApp().setVisible(true));
    }
}
