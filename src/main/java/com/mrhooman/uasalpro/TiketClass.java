/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mrhooman.uasalpro;

import java.util.Scanner;

/**
 *
 * @author Muhammad Rijal
 */
public class TiketClass {
    
    static Scanner input = new Scanner(System.in);
    
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
    
    // Fungsi menampilkan Jenis Tiket
    public static void showJenisTiket(){
        int index = 0;
            System.out.printf("%-2s | %-15s | %-10s | %-10s | %-10s |%n","No", "Kode Event", "Jenis Tiket", "Harga", "Jumlah Maksimal");
        while (index < Uasalpro.listJenisTiket.size()){
            Uasalpro.JenisTiket currentTiket = Uasalpro.listJenisTiket.get(index);
            System.out.printf("%-2s | %-15s | %-10s | %-10s | %-10s |%n",index+1, currentTiket.kodeEvent(), currentTiket.jenis(), currentTiket.harga(), currentTiket.maksimal());
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
    
    // Edit jenis tiket berdasarkan kodeEvent
    public static void editJenisTiket(String kodeEvent){
        int index = 0;
        System.out.println("Masukkan Data Jenis tiket baru : ");
        // Looping pengambilan data
        while(index < Uasalpro.listJenisTiket.size()){
            // Inisiasi Record JenisTiket sesuai index
            Uasalpro.JenisTiket currentTiket = Uasalpro.listJenisTiket.get(index);
            if(currentTiket.kodeEvent().equals(kodeEvent)){
                System.out.print("Masukkan Nama Jenis Tiket ( Before : "+currentTiket.jenis()+" | ketik - untuk tetap ) : ");
                String namaJenis = input.next();
                // Kondisi value akan diedit atau tidaknya
                if (namaJenis.equals("-")) {
                    namaJenis = currentTiket.jenis();
                }
                System.out.print("Masukkan Harga Tiket ( Before : "+currentTiket.harga()+" | ketik 0 untuk tetap ) : ");
                double hargaTiket = input.nextDouble();
                // Kondisi value akan diedit atau tidaknya
                if (hargaTiket == 0) {
                    hargaTiket = currentTiket.harga();
                }
                System.out.print("Masukkan maksimal tiket ( Before : "+currentTiket.maksimal()+" | ketik 0 untuk tetap ) : ");
                int maksimal = input.nextInt();
                // Kondisi value akan diedit atau tidaknya
                if (maksimal == 0) {
                    maksimal = currentTiket.maksimal();
                }

                // Inisiasi JenisTiket baru untuk mengganti JenisTiket yang dipilih dengan property yang telah diinput
                Uasalpro.JenisTiket newJenisTiket = new Uasalpro.JenisTiket(
                        kodeEvent,
                        namaJenis,
                        maksimal,
                        hargaTiket
                );
                
                // Timpa JenisTiket yang lama dengan yang baru setelah diedit
                Uasalpro.listJenisTiket.set(index, newJenisTiket);
            }
            index++;
        }
        
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
