/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package id.ac.unpas.praktikumpemograman2.TugasModul10;

import id.ac.unpas.praktikumpemograman2.TugasModul10.view.MahasiswaView;
import id.ac.unpas.praktikumpemograman2.TugasModul10.controller.MahasiswaController;

/**
 *
 * @author Flavio
 */
public class main {
    public static void main(String[] args) {
        MahasiswaView view = new MahasiswaView();
        new MahasiswaController(view);
        view.setVisible(true);
    }
}
