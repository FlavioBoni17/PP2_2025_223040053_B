/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package id.ac.unpas.praktikumpemograman2.modul08.controller;

import id.ac.unpas.praktikumpemograman2.modul08.model.PersegiPanjangModel;
import id.ac.unpas.praktikumpemograman2.modul08.view.PersegiPanjangView;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * @author Flavio Boniperti Oktaviola Zyoffy
 */
public class PersegiPanjangController {
    // Model dan view sebagai atribut kelas
    private PersegiPanjangModel model;
    private PersegiPanjangView view;
    
    public PersegiPanjangController(PersegiPanjangModel model, PersegiPanjangView view) {
        this.model = model;
        this.view = view;

        // Menghubungkan tombol di View dengan logic di Controller
        this.view.addHitungLuasListener(new HitungLuasListener());
        this.view.addHitungKelilingListener(new HitungKelilingListener());
        this.view.addResetListener(new ResetFormListener());
    }
    
    // Inner class untuk menangani event klik tombol
    class HitungLuasListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                // 1. Ambil data dari View
                double p = view.getPanjang();
                double l = view.getLebar();

                // 2. Kirim data ke Model
                model.setPanjang(p);
                model.setLebar(l);

                // 3. Jalankan logika bisnis di Model
                model.hitungLuas();
                view.setHasilLuas(model.getLuas());
                
            } catch (NumberFormatException ex) {
                view.tampilkanPesanError("Input harus berupa angka!");
            }
        }
    }
    
    class HitungKelilingListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                double p = view.getPanjang();
                double l = view.getLebar();
                model.setPanjang(p);
                model.setLebar(l);

                model.hitungKeliling();
                view.setHasilKeliling(model.getKeliling());

            } catch (NumberFormatException ex) {
                view.tampilkanPesanError("Input harus berupa angka!");
            }
        }
    }
    
    class ResetFormListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            view.resetForm();
        }
    }
}

