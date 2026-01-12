/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package id.ac.unpas.praktikumpemograman2.TugasModul10.model;

import id.ac.unpas.praktikumpemograman2.TugasModul10.util.KoneksiDB;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Flavio
 */
public class Mahasiswa {
    private String nama;
    private String nim;
    private String jurusan;

    public Mahasiswa(String nama, String nim, String jurusan) 
    {
        this.nama = nama;
        this.nim = nim;
        this.jurusan = jurusan;
    }

    public String getNama() 
    { 
        return nama; 
    }
    
    public String getNim() 
    { 
        return nim; 
    }
    
    public String getJurusan() 
    { 
        return jurusan; 
    }

    // Read / Load Data
    public static List<Mahasiswa> ambilData() throws Exception 
    {
        List<Mahasiswa> list = new ArrayList<>();
        Connection conn = KoneksiDB.configDB();
        Statement stm = conn.createStatement();
        ResultSet res = stm.executeQuery("SELECT * FROM mahasiswa");

        while(res.next()) 
        {
            list.add(new Mahasiswa( res.getString("nama"), res.getString("nim"), res.getString("jurusan")));
        }
        return list;
    }

    // Create / Simpan
    public void simpan() throws Exception 
    {
        String sql = "INSERT INTO mahasiswa (nama, nim, jurusan) VALUES (?, ?, ?)";
        Connection conn = KoneksiDB.configDB();
        PreparedStatement pst = conn.prepareStatement(sql);
        pst.setString(1, this.nama);
        pst.setString(2, this.nim);
        pst.setString(3, this.jurusan);
        pst.execute();
    }

    // Update
    public void edit() throws Exception 
    {
        String sql = "UPDATE mahasiswa SET nama = ?, jurusan = ? WHERE nim = ?";
        Connection conn = KoneksiDB.configDB();
        PreparedStatement pst = conn.prepareStatement(sql);
        pst.setString(1, this.nama);
        pst.setString(2, this.jurusan);
        pst.setString(3, this.nim);
        pst.executeUpdate();
    }

    // Delete
    public void hapus() throws Exception 
    {
        String sql = "DELETE FROM mahasiswa WHERE nim = ?";
        Connection conn = KoneksiDB.configDB();
        PreparedStatement pst = conn.prepareStatement(sql);
        pst.setString(1, this.nim);
        pst.execute();
    }

    // Search / Cari
    public static List<Mahasiswa> cari(String keyword) throws Exception 
    {
        List<Mahasiswa> list = new ArrayList<>();
        String sql = "SELECT * FROM mahasiswa WHERE nama LIKE ?";
        Connection conn = KoneksiDB.configDB();
        PreparedStatement pst = conn.prepareStatement(sql);
        pst.setString(1, "%" + keyword + "%");
        ResultSet res = pst.executeQuery();

        while(res.next()) 
        {
            list.add(new Mahasiswa(res.getString("nama"), res.getString("nim"), res.getString("jurusan")));
        }
        return list;
    }

    // Cek NIM
    public static boolean cekNIM(String nim) 
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
            e.printStackTrace();
            return true;
        }
    }
}
