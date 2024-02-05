/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mrhooman.uasalpro;

import java.util.ArrayList;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

/**
 *
 * @author Muhammad Rijal
 */
public class Uasalpro {

    // Inisiasi Record
    public record Event(String kode, String nama, String tempat, LocalDate tanggal){}
    public record Item(String nomor, String kode){}
    public record Tiket(String kode, String JenisTiket, String kodeEvent, String namaPemesan, String noHp, String alamat, int jumlah, double totalHarga, String keterangan){}
    public record JenisTiket( String kodeEvent, String jenis, int maksimal, double harga){}
    
    // Inisiasi Array List
    static ArrayList<Event> listEvent = new ArrayList<>();
    static ArrayList<JenisTiket> listJenisTiket = new ArrayList<>();
    static ArrayList<Tiket> listTiket = new ArrayList<>();
    static ArrayList<Item> container = new ArrayList<>();
    
    // Instansiasi Object Scanner
    static Scanner input = new Scanner(System.in);
    
    public static void main(String[] args) {
        
        // Seeding Data Event
        listEvent.add(new Event("BDG01", "bandung fest","Bandung", LocalDate.parse("2024-03-20")));
        listEvent.add(new Event("BDG02", "bandung fest2","Bandung", LocalDate.parse("2024-02-29")));
        listEvent.add(new Event("JKT01", "Jakarta Fest","Jakarta", LocalDate.parse("2024-03-10")));
        listEvent.add(new Event("JKT02", "Jakarta Fest2","Jakarta", LocalDate.parse("2024-03-01")));
        
        // Sedding data Jenis TIket
        listJenisTiket.add(new JenisTiket("BDG01", "REGULER", 100, 100000));
        listJenisTiket.add(new JenisTiket("BDG01", "VIP", 100, 200000));
        listJenisTiket.add(new JenisTiket("BDG02", "REGULER", 100, 75000));
        listJenisTiket.add(new JenisTiket("BDG02", "VIP", 100, 150000));
        listJenisTiket.add(new JenisTiket("JKT01", "REGULER", 100, 300000));
        listJenisTiket.add(new JenisTiket("JKT02", "REGULER", 100, 300000));
        
        // Inisiasi kondisi perulangan
        boolean isActive = true;
        
        while(isActive){
            //Tampilan Menu
            System.out.println("-------------------------");
            System.out.println("Welcome to Tiketing");
            System.out.println("1. Cek Event & Beli Tiket");
            System.out.println("2. Cari Event");
            System.out.println("3. Cek Tiket");
            System.out.println("4. Login Admin");
            System.out.println("5. Close App");
            System.out.print("Pilih Menu : ");

            String pilihan = input.nextLine();
        
            // Instansiasi Object
            EventClass EventClass = new EventClass();
            TiketClass TiketClass = new TiketClass();
            AdminClass AdminClass = new AdminClass();
            
            // Proses Pilihan menu
            switch(pilihan){
                case "1" :
                    // Jalankan method main
                    EventClass.main();
                    break;
                case "2" :
                    // Jalankan Method findEvent
                    EventClass.findEvent();
                    break;
                case "3" :
                    // Proses input tiket
                    System.out.print("Masukkan kode tiket : ");
                    String kodeTiket = input.next();
                    // Pengecekkan tiket dengan method cekTiket yang memerlukan argument kode tiket
                    if (TiketClass.cekTiket(kodeTiket)) {
                        // Tiket Terdaftar
                        boolean state = true;
                        while(state) {
                            System.out.print("Tiket Aktif, Claim Tiket sekarang (Y/N) ? ");
                            String confirm = input.next();
                            switch (confirm.toLowerCase()) {
                                case "y":
                                    TiketClass.claimTiket(kodeTiket);
                                    System.out.println("---------------------------------------------------");
                                    System.out.println("Tiket berhasil di claim, Selamat bersenang senang!!");
                                    System.out.println("---------------------------------------------------");
                                    state = false;
                                    break;
                                case "n":
                                    System.out.println("-------------");
                                    System.out.println("Sampai jumpa!");
                                    System.out.println("-------------");
                                    state = false;
                                    break;
                                default:
                                    System.out.println("-------------------");
                                    System.out.println("Pilihan tidak valid");
                                    System.out.println("-------------------");
                            }
                            System.out.println("");
                        }
                    } else {
                        // Tiket tidak terdaftar
                        System.out.println("-----------------");
                        System.out.println("Tiket Tidak Valid");
                        System.out.println("-----------------");
                    }
                    input.nextLine();
                    break;
                case "4" :
                    // Panggil Method main dari AdminClasss
                    AdminClass.main();
                    break;
                case "5" :
                    // Proses pengehentian perulangan
                    isActive = false;
                    break;
                default :
                    // Jika inputan asal / tidak valid
                    System.out.println("-------------------");
                    System.out.println("Pilihan Tidak Valid");
                    System.out.println("-------------------");
                    break;
            }
        }
        
        
    }
}
