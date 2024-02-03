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
 * @author ACER
 */
public class Uasalpro {

    public record Event(String kode, String nama, String tempat, LocalDate tanggal){
    }
    public record Item(String nomor, String kode){}
    public record Tiket(String kode, String JenisTiket, String kodeEvent, String namaPemesan, String noHp, String alamat, int jumlah, double totalHarga, String keterangan){}
    public record JenisTiket( String kodeEvent, String jenis, int maksimal, double harga){}
    
    static ArrayList<Event> listEvent = new ArrayList<>();
    static ArrayList<JenisTiket> listJenisTiket = new ArrayList<>();
    static ArrayList<Tiket> listTiket = new ArrayList<>();
    static ArrayList<Item> container = new ArrayList<>();
    
    static Scanner input = new Scanner(System.in);
    
    public static void main(String[] args) {
        
        DateTimeFormatter formatter = DateTimeFormatter.ISO_DATE;
        
        LocalDate date = LocalDate.parse("2024-01-25", formatter);
       
        listEvent.add(new Event("BDG01", "bandung fest","Bandung", LocalDate.now()));
        listEvent.add(new Event("BDG02", "bandung fest2","Bandung", LocalDate.now()));
        listEvent.add(new Event("JKT01", "Jakarta Fest","Jakarta", LocalDate.now()));
        listEvent.add(new Event("JKT02", "Jakarta Fest2","Jakarta", LocalDate.now()));
        
        listJenisTiket.add(new JenisTiket("BDG01", "REGULER", 100, 100000));
        listJenisTiket.add(new JenisTiket("BDG01", "VIP", 100, 200000));
        listJenisTiket.add(new JenisTiket("BDG02", "REGULER", 100, 75000));
        listJenisTiket.add(new JenisTiket("BDG02", "VIP", 100, 150000));
        listJenisTiket.add(new JenisTiket("JKT01", "REGULER", 100, 300000));
        listJenisTiket.add(new JenisTiket("JKT02", "REGULER", 100, 300000));
        
        boolean isActive = true;
        
        while(isActive == true){
            //Tampilan Menu
            System.out.println("Welcome to Tiket1ing");
            System.out.println("1. Cek Event & Beli Tiket");
            System.out.println("2. Cari Event");
            System.out.println("3. Cek Tiket");
            System.out.println("4. Login Admin");
            System.out.println("5. Close App");
            System.out.print("Pilih Menu : ");

            String pilihan = input.nextLine();
        
            EventClass EventClass = new EventClass();
            TiketClass TiketClass = new TiketClass();
            AdminClass AdminClass = new AdminClass();
            switch(pilihan){
                case "1" :
                    EventClass.main();
                    break;
                case "2" :
                    EventClass.findEvent();
                    break;
                case "3" :
                    System.out.print("Masukkan kode tiket : ");
                    String kodeTiket = input.next();
                    input.nextLine();
                    if (TiketClass.cekTiket(kodeTiket)) {
                        boolean state = true;
                        while(state) {
                            System.out.print("Tiket Aktif, Claim Tiket sekarang (Y/N) ? ");
                            String confirm = input.next();
                            input.nextLine();
                            switch (confirm.toLowerCase()) {
                                case "y":
                                    TiketClass.claimTiket(kodeTiket);
                                    System.out.print("Tiket berhasil di claim, Selamat bersenang senang!!");
                                    state = false;
                                    break;
                                case "n":
                                    System.out.print("Sampai jumpa!");
                                    state = false;
                                    break;
                                default:
                                    System.out.print("Pilihan tidak valid");
                            }
                            System.out.println("");
                        }
                    }
                    break;
                case "4" :
                    AdminClass.main();
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
