/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mrhooman.uasalpro;

/**
 *
 * @author Muhammad Rijal
 */
public class TiketClass {
    
    // Fungsi untuk show semua tiket
    public static void showTiket(){
        int index = 0;
            System.out.printf("%-2s | %-15s | %-10s | %-10s | %-4s | %-10s | %-10s |%n","No", "Pemesan", "Kode Tiket", "Kode Event", "Jmlah", "Total", "Status");
        while (index < Uasalpro.listTiket.size()){
            Uasalpro.Tiket currentTiket = Uasalpro.listTiket.get(index);
            System.out.printf("%-2s | %-15s | %-10s | %-10s | %-4s | %-10s | %-10s |%n",index+1, currentTiket.namaPemesan(), currentTiket.kode(), currentTiket.kodeEvent(), currentTiket.jumlah(), currentTiket.totalHarga(), currentTiket.keterangan());
            index++;
        }
    }
    
    // Fungsi Pengecekan tiket aktif tidaknya
    public boolean cekTiket(String kode){
        int index = 0;
        // Perulangan pengecekan tiket berdasarkan kode dan status
        while(index < Uasalpro.listTiket.size()){
            Uasalpro.Tiket currentTiket = Uasalpro.listTiket.get(index);
            if(currentTiket.kode().equals(kode) && currentTiket.keterangan().equals("aktif")){
                // Kembalikan nilai True jika ditemukan
                return true;
            }
        }
        // Jika tidak ada maka return false
        return false;
    }

    // Fungsi claim tiket dengan merubah statusnya
    public void claimTiket(String kode){
        int index = 0;
        while(index < Uasalpro.listTiket.size()){
            Uasalpro.Tiket currentTiket = Uasalpro.listTiket.get(index);
            if(currentTiket.kode().equals(kode)){
                // Membuat tiket baru dengan status baru
                Uasalpro.Tiket updatedTiket = new Uasalpro.Tiket(
                    currentTiket.kode(),
                    currentTiket.JenisTiket(),
                    currentTiket.kodeEvent(),
                    currentTiket.namaPemesan(),
                    currentTiket.noHp(),
                    currentTiket.alamat(),
                    currentTiket.jumlah(),
                    currentTiket.totalHarga(),
                    "claimed"
                );

                // Replace Tiket lama dengan yang baru dibuat
                Uasalpro.listTiket.set(index, updatedTiket);
                break;
            }
            index++;
        }
    }
}
