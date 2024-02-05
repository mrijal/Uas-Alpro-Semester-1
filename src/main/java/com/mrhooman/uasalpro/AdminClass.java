/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mrhooman.uasalpro;

import static com.mrhooman.uasalpro.EventClass.cekEvent;
import java.util.Scanner;

/**
 *
 * @author Muhammad Rijal
 */
public class AdminClass {
    boolean isActive = true;
    Scanner input = new Scanner(System.in);
    String USERNAME = "admin";
    String PASSWORD = "password";
    
    // Fungsi Utama Admin
    public void main(){
        while(isActive){
            System.out.print("Masukkan username : ");
            String username = input.nextLine();
            System.out.print("Masukkan Password : ");
            String password = input.nextLine();
            if (username.equals(USERNAME) && password.equals(PASSWORD)) {
                System.out.println("Berhasil Login!");
                while(isActive){
                    // Tampilan Menu
                    System.out.println("");
                    System.out.println("-----------------------------");
                    System.out.println("--------- MENU ADMIN --------");
                    System.out.println("-----------------------------");
                    System.out.println("1. Tambah Event baru");
                    System.out.println("2. Edit Event");
                    System.out.println("3. Hapus Event");
                    System.out.println("4. Lihat data Jenis Tiket");
                    System.out.println("5. Lihat data Tiket");
                    System.out.println("00. Logout");
                    System.out.print("Pilih Menu : ");
                    String pilihMenu = input.nextLine();
                    
                    switch(pilihMenu){
                        case "1" :
                            // Memanggil Method TambahEvent
                            EventClass.tambahEvent();
                            break;
                        case "2" :
                            String[] emptyEdit = new String[2];
                            // Memanggil Method cekEvent dengan argument empty untuk menampilkan semua data event
                            cekEvent(emptyEdit, "");
                            System.out.print("Pilih Event yang akan diedit : ");
                            String pilihEvent = input.next();
                            // Memanggil Method EditEvent
                            EventClass.editEvent(Integer.parseInt(pilihEvent));
                            break;
                        case "3" :
                            String[] emptyDelete = new String[2];
                            // Memanggil Method cekEvent dengan argument empty untuk menampilkan semua data event
                            cekEvent(emptyDelete, "");
                            System.out.print("Pilih Event yang akan dihapus : ");
                            String pilihHapusEvent = input.nextLine();
                            // Memanggil method deleteEvent dengan argument pilihHapusEvent
                            EventClass.deleteEvent(pilihHapusEvent);
                            break;
                        case "4" :
                            // Memanggil Method showJenisTiket
                            TiketClass.showJenisTiket();
                            break;
                        case "5" :
                            // Memanggil Method showTiket
                            TiketClass.showTiket();
                            break;
                        case "00":
                            // Proses logout sederhana
                            System.out.println("Logout success!!");
                            isActive = false;
                            break;
                        default :
                            System.out.println("Pilihan tidak valid !");
                            break;
                    }
                    System.out.println("");
                }
            } else {
                System.out.println("-------------------------------");
                System.out.println("Username atau Password Salah !!");
                System.out.println("-------------------------------");
            }
        }
    }
}
