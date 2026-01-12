/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package id.ac.unpas.praktikumpemograman2.TugasModul10.controller;

import id.ac.unpas.praktikumpemograman2.TugasModul10.model.Mahasiswa;
import id.ac.unpas.praktikumpemograman2.TugasModul10.view.MahasiswaView;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;
import javax.swing.JTable;

/**
 *
 * @author Flavio
 */
public class MahasiswaController {
    private MahasiswaView view;

    public MahasiswaController(MahasiswaView view) 
    {
        this.view = view;
        
        view.addSimpanListener(e -> simpanData());
        view.addEditListener(e -> editData());
        view.addHapusListener(e -> hapusData());
        view.addClearListener(e -> view.clearForm());
        view.addCariListener(e -> cariData());
        
        view.addTableMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                JTable table = view.getTable();
                int row = table.getSelectedRow();
                view.setNama(table.getValueAt(row, 1).toString());
                view.setNim(table.getValueAt(row, 2).toString());
                view.setJurusan(table.getValueAt(row, 3).toString());
            }
        });
        
        loadData();
    }

    private void loadData() 
    {
        try 
        {
            List<Mahasiswa> list = Mahasiswa.ambilData();
            view.updateTable(list);
        } 
        catch (Exception e) 
        {
            view.showMessage("Gagal Load Data: " + e.getMessage());
        }
    }

    private void simpanData() 
    {
        if (!validasiInput()) return;
        
        String nama = view.getNama();
        String nim = view.getNim();
        String jurusan = view.getJurusan();

        if (Mahasiswa.cekNIM(nim)) 
        {
            view.showMessage("NIM sudah terdaftar!");
            return;
        }

        try 
        {
            Mahasiswa m = new Mahasiswa(nama, nim, jurusan);
            m.simpan();
            view.showMessage("Data Berhasil Disimpan");
            view.clearForm();
            loadData();
        } 
        catch (Exception e) 
        {
            view.showMessage("Gagal Simpan: " + e.getMessage());
        }
    }

    private void editData() 
    {
        if (!validasiInput()) return;
        
        try 
        {
            Mahasiswa m = new Mahasiswa(view.getNama(), view.getNim(), view.getJurusan());
            m.edit();
            view.showMessage("Data Berhasil Diubah");
            view.clearForm();
            loadData();
        } 
        catch (Exception e) 
        {
            view.showMessage("Gagal Edit: " + e.getMessage());
        }
    }

    private void hapusData() 
    {
        try 
        {
            Mahasiswa m = new Mahasiswa(view.getNama(), view.getNim(), view.getJurusan());
            m.hapus();
            view.showMessage("Data Berhasil Dihapus");
            view.clearForm();
            loadData();
        } 
        catch (Exception e) 
        {
            view.showMessage("Gagal Hapus: " + e.getMessage());
        }
    }

    private void cariData() 
    {
        try 
        {
            String keyword = view.getCariInput();
            List<Mahasiswa> list = Mahasiswa.cari(keyword);
            view.updateTable(list);
        } 
        catch (Exception e) 
        {
            view.showMessage("Gagal Mencari: " + e.getMessage());
        }
    }

    private boolean validasiInput() 
    {
        String nama = view.getNama();
        String nim = view.getNim();

        if (nama.trim().isEmpty() || nim.trim().isEmpty()) 
        {
            view.showMessage("Data tidak boleh kosong!");
            return false;
        }

        if (!nama.matches("[a-zA-Z\\s]+")) 
        {
            view.showMessage("Nama tidak boleh menggunakan angka!");
            return false;
        }

        if (!nim.matches("\\d+")) 
        {
            view.showMessage("NIM hanya boleh diisi angka!");
            return false;
        }
        return true;
    }
}
