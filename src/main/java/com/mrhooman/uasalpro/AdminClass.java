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
    
    public void main(){
        while(isActive){
            System.out.print("Masukkan username : ");
            String username = input.nextLine();
            System.out.print("Masukkan Password : ");
            String password = input.nextLine();
            if (username.equals(USERNAME) && password.equals(PASSWORD)) {
                System.out.println("Berhasil Login!");
                while(isActive){
                    System.out.println("");
                    System.out.println("-----------------------------");
                    System.out.println("--------- MENU ADMIN --------");
                    System.out.println("-----------------------------");
                    System.out.println("1. Tambah Event baru");
                    System.out.println("2. Edit Event");
                    System.out.println("3. Hapus Event");
                    System.out.println("4. Lihat data tiket");
                    System.out.println("00. Logout");
                    System.out.print("Pilih Menu : ");
                    String pilihMenu = input.nextLine();
                    
                    switch(pilihMenu){
                        case "1" :
                            EventClass.tambahEvent();
                            break;
                        case "2" :
                            String[] emptyEdit = new String[2];
                            cekEvent(emptyEdit, "");
                            System.out.print("Pilih Event yang akan diedit : ");
                            int pilihEvent = input.nextInt();
                            input.nextLine();
                            EventClass.editEvent(pilihEvent);
                            break;
                        case "3" :
                            String[] emptyDelete = new String[2];
                            cekEvent(emptyDelete, "");
                            System.out.print("Pilih Event yang akan dihapus : ");
                            String pilihHapusEvent = input.nextLine();
                            EventClass.deleteEvent(pilihHapusEvent);
                            break;
                        case "4" :
                            TiketClass.showTiket();
                            break;
                        case "00":
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
