/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mrhooman.uasalpro;

import java.util.ArrayList;
import java.time.LocalDate;
import java.util.Scanner;
import java.util.Enumeration;
import java.util.Collections;

/**
 *
 * @author ACER
 */
public class Uasalpro {

    public record Event(String kode, String nama, String tempat, LocalDate tanggal){}
    public record Item(String nomor, String kode){}
    public record Tiket(String kode, String JenisTiket, String kodeEvent, String namaPemesan, String noHp, String alamat, int jumlah, int totalHarga, String keterangan){}
    public record JenisTiket(String kodeEvent, String jenis, int maksimal){}
    
    static ArrayList<Event> listEvent = new ArrayList<>();
    static ArrayList<Item> container = new ArrayList<>();
    
    public static void main(String[] args) {
        
        listEvent.add(new Event("BDG01", "bandung fest","Bandung", LocalDate.now()));
        listEvent.add(new Event("BDG02", "bandung ff","Bandung", LocalDate.now()));
        listEvent.add(new Event("JKT02", "Jakarta Fest","Jakarta", LocalDate.now()));
        listEvent.add(new Event("JKT02", "Jakarta Fest","Jakarta", LocalDate.now()));
        
        Scanner input = new Scanner(System.in);
        boolean isActive = true;
        
        while(isActive){
            //Tampilan Menu
            System.out.println("Welcom to Tiketiing");
            System.out.println("1. Cek Event & Beli Tiket");
            System.out.println("2. Cari Event");
            System.out.println("3. Cek Tiket");
            System.out.println("4. Login Admin");
            System.out.println("5. Close App");
            System.out.print("Pilih Menu : ");

            String pilihan = input.nextLine();
        
            switch(pilihan){
                case "1" :
//                    cekEvent(listEvent);
                    Enumeration<Event> e = Collections.enumeration(listEvent);
                    System.out.println("Event yang akan datang : ");
                    int i = 1;
                    while(e.hasMoreElements()){
                        Event currentEvent = e.nextElement();
                        container.add(new Item(Integer.toString(i),currentEvent.kode));
                        System.out.println(i + ". " +currentEvent.kode + " | " + currentEvent.nama + " | " + currentEvent.tempat + " | " + currentEvent.tanggal);
                        i++;
                    }
                    
                    System.out.print("Pilih event yang akan di pesan : ");
                    String pilihEvent = input.next();
                    System.out.println("kamu memilih nomor : " + pilihEvent);
                    System.out.println("-----------------");
                    
                    break;
                case "2" :
//                    cariEvent();
                    break;
                case "3" :
//                    cekTiket();
                    break;
                case "4" :
//                    loginAdmin();
                    break;
                case "5" :
                    isActive = false;
                    break;
                default :
                    System.out.println("Pilihan Tidak Valid");
                    break;
            }
        }
        
        
    }
}
