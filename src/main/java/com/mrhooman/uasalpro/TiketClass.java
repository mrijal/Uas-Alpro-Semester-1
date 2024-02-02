/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mrhooman.uasalpro;

/**
 *
 * @author ACER
 */
public class TiketClass {
    public void showTiket(){
        int index = 0;
            System.out.printf("%-2s | %-15s | %-10s | %-10s | %-4s | %-10s | %-10s |%n","No", "Pemesan", "Kode Tiket", "Kode Event", "Jmlah", "Total", "Status");
        while (index < Uasalpro.listTiket.size()){
            Uasalpro.Tiket currentTiket = Uasalpro.listTiket.get(index);
            System.out.printf("%-2s | %-15s | %-10s | %-10s | %-4s | %-10s | %-10s |%n",index+1, currentTiket.namaPemesan(), currentTiket.kode(), currentTiket.kodeEvent(), currentTiket.jumlah(), currentTiket.totalHarga(), currentTiket.keterangan());
            index++;
        }
    }
}
