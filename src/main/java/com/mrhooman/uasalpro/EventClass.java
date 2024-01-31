/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mrhooman.uasalpro;

import com.mrhooman.uasalpro.Uasalpro.Event;
import static com.mrhooman.uasalpro.Uasalpro.container;
import static com.mrhooman.uasalpro.Uasalpro.listEvent;
import static com.mrhooman.uasalpro.Uasalpro.listJenisTiket;
import static com.mrhooman.uasalpro.Uasalpro.listTiket;
import java.util.Collections;
import java.util.Enumeration;
import java.util.Scanner;
import java.util.Random;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
/**
 *
 * @author ACER
 */
public class EventClass {
    
    Scanner input = new Scanner(System.in);
    
    public void main(){
        String[] empty = new String[2];
        cekEvent(empty, "");
        order();
    }
    
    public static String randomString() {
        int length = 5;
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        StringBuilder randomString = new StringBuilder();

        Random random = new Random();
        for (int i = 0; i < length; i++) {
            int index = random.nextInt(characters.length());
            char randomChar = characters.charAt(index);
            randomString.append(randomChar);
        }

        return randomString.toString();
    }
    
    public void cekEvent(String[] param, String searchCategory){
        System.out.println();
        Enumeration<Uasalpro.Event> e = Collections.enumeration(listEvent);
        listEvent.sort(Comparator.comparing(Event::nama));
        System.out.println("Event yang akan datang : ");
        int i = 1;
        String category = null;
        container.clear();
        
        //Show List of events
        while(e.hasMoreElements()){
            Uasalpro.Event currentEvent = e.nextElement();
            if(param[0] != null && !param[0].isEmpty()){
                param[0].toLowerCase();
                switch(searchCategory){
                case "1" :
                    category = currentEvent.nama();
                    break;
                case "2" :
                    category = currentEvent.tempat();
                    break;
                default:
                    System.out.println("Kategory pencarian salah");
                    return;
                }
                
                if(category.toLowerCase().contains(param[0])){
                    container.add(new Uasalpro.Item(Integer.toString(i),currentEvent.kode()));
                    System.out.println(i + ". " +currentEvent.kode() + " | " + currentEvent.nama() + " | " + currentEvent.tempat() + " | " + currentEvent.tanggal());
                }
            } else {
                container.add(new Uasalpro.Item(Integer.toString(i),currentEvent.kode()));
                System.out.println(i + ". " +currentEvent.kode() + " | " + currentEvent.nama() + " | " + currentEvent.tempat() + " | " + currentEvent.tanggal());
            }
            i++;
        }
    }
    public void order(){
        System.out.print("Pilih event yang akan di pesan (00 untuk kembali) : ");
        String pilihEvent = input.nextLine();
        
        if(pilihEvent.equals("00")){
            System.out.println("Ditunggu Pemesanan kedepannya");
        } else{
            
            //Pengambilan kode Event dari event yang dipilih
            int index = 0;
            String kode = "";
            while(index < container.size()){
                Uasalpro.Item currentItem = container.get(index);
                if(currentItem.nomor().equals(pilihEvent)){
                    kode = currentItem.kode();
                }
                index++;
            }
            
            //Pencarian Detail Event dari event yang dipilih
            index = 0;
            Uasalpro.Event eventTerpilih = null;
            while(index < listEvent.size()){
                Uasalpro.Event currentEvent = listEvent.get(index);
                if(currentEvent.kode().equals(kode)){
                    eventTerpilih = new Uasalpro.Event(currentEvent.kode(),currentEvent.nama(),currentEvent.tempat(),currentEvent.tanggal());
                }
                index++;
            }
            
            //Proses Order Tiket
            index = 0;
            int nomor = 1;
            System.out.println("Pilih Jenis Tiket");
            
            System.out.printf("%-2s | %-10s | %-5s | %-10s |%n", "NO", "JENIS TIKET", "KUOTA", "HARGA");
            while(index < listJenisTiket.size()){
                Uasalpro.JenisTiket currentTiket = listJenisTiket.get(index);
                if(currentTiket.kodeEvent().equals(kode)){
                    System.out.printf("%-3s | %-10s | %-5s | %,.2f |%n", nomor, currentTiket.jenis(), currentTiket.maksimal(), currentTiket.harga());
                }
                index++;
                nomor++;
            }
            
            //Proses pemilihan dan store data Jenis Tiket
            System.out.println("Pilih Jenis Tiket (cth : REGULER) : ");
            String pilihanTiket = input.nextLine();
            
            index = 0;
            Uasalpro.JenisTiket tiketTerpilih = null;
            while(index < listJenisTiket.size()){
                Uasalpro.JenisTiket currentTiket = listJenisTiket.get(index);
                if(currentTiket.jenis().contains(pilihanTiket) && currentTiket.kodeEvent().equals(kode)){
                    tiketTerpilih = currentTiket;
                }
                index++;
            }
            
            //Proses Input data tiket
            System.out.print("Masukkan Nama : ");
            String namaPemesan = input.nextLine();
            System.out.print("Masukkan Nomor Hp : ");
            String noHpPemesan = input.nextLine();
            System.out.print("Masukkan Alamat : ");
            String alamatPemesan = input.nextLine();
            System.out.print("Masukkan Jumlah Tiket : ");
            int jumlahTiket = input.nextInt();
            input.nextLine();
            double totalHarga = jumlahTiket * tiketTerpilih.harga();
            
            System.out.printf("--------------------------------%n");
            System.out.println("Konfirmasi Data : ");
            System.out.printf("--------------------------------%n");
            System.out.printf("%-15s : %-10s %n","Event", eventTerpilih.nama());
            System.out.printf("%-15s : %-10s %n","Jenis Tiket",tiketTerpilih.jenis());
            System.out.printf("%-15s : %-10s %n","Pemesan", namaPemesan);
            System.out.printf("%-15s : %-10s %n","No Hp", noHpPemesan);
            System.out.printf("%-15s : %-10s %n","Alamat", alamatPemesan);
            System.out.printf("%-15s : %-10s %n","Jumlah", jumlahTiket);
            System.out.printf("%-15s : %,.2f %n","Total", totalHarga);
            
            System.out.print("Data sudah Benar (Y/N) ?");
            String confirm = input.nextLine();
            
            boolean statusPilihan = true;
            while(statusPilihan){
                if(confirm.toLowerCase().equals("y")){
                    System.out.println("Terima kasih telah memesan!");
                    listTiket.add(new Uasalpro.Tiket(randomString(), tiketTerpilih.jenis(),kode,namaPemesan,noHpPemesan,alamatPemesan,jumlahTiket,totalHarga,"aktif"));
                    statusPilihan = false;
                } else if (confirm.toLowerCase().equals("n")){
                    System.out.println("Pesanan Dibatalkan");
                    statusPilihan = false;
                } else {
                    System.out.println("Pilihan Tidak Valid");
                }
            }
            
//            System.out.printf("--------------------------------%n");
//            System.out.printf("| %-10s | %-8s | %4s |%n", "CATEGORY", "NAME", "BITS");
//            System.out.printf("--------------------------------%n");
//
//            System.out.printf("| %-10s | %-8s | %04d |%n", "Floating", "double",  64);
//            System.out.printf("| %-10s | %-8s | %04d |%n", "Floating", "float",   32);
//            System.out.printf("| %-10s | %-8s | %04d |%n", "Integral", "long",    64);
//            System.out.printf("| %-10s | %-8s | %04d |%n", "Integral", "int",     32);
//            System.out.printf("| %-10s | %-8s | %04d |%n", "Integral", "char",    16);
//            System.out.printf("| %-10s | %-8s | %04d |%n", "Integral", "short",   16);
//            System.out.printf("| %-10s | %-8s | %04d |%n", "Integral", "byte",    8);
//            System.out.printf("| %-10s | %-8s | %04d |%n", "Boolean",  "boolean", 1);
//
//            System.out.printf("--------------------------------%n");
        }
    }   
    
    public void findEvent(){
        System.out.println("Mau cari berdasarkan apa ? ");
        System.out.println("1. Nama");
        System.out.println("2. Tempat");
        System.out.println("3. Tanggal");
        String searchCategory = input.next();
        
        System.out.print("Masukkan Pencarian : ");
        String kata = input.next();
        String[] search = new String[2];
        search[0] = kata;
        
        if (searchCategory.equals("3")) {
            String search2 = input.next();
            search[1] = search2;
        }
        
        cekEvent(search, searchCategory);
    }
}
