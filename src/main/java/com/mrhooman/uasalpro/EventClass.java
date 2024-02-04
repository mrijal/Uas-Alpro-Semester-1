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
import java.time.chrono.ChronoLocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.time.LocalDate;
/**
 *
 * @author ACER
 */
public class EventClass {
    
    static Scanner input = new Scanner(System.in);
    
    // Fungsi utama untuk menjalankan menu 1 ( cek dan order )
    public void main(){
        String[] empty = new String[2];
        cekEvent(empty, "");
        order();
    }
    
    // Fungsi untuk menghasilkan string random
    public static String randomString() {
        int length = 5;
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        StringBuilder randomString = new StringBuilder();

        // Menggunakan library Random
        Random random = new Random();
        for (int i = 0; i < length; i++) {
            int index = random.nextInt(characters.length());
            char randomChar = characters.charAt(index);
            randomString.append(randomChar);
        }

        // Mengembalikan nilai String Hasil random
        return randomString.toString();
    }
    
    // Fungsi Menampilkan semua data sekaligus melakukan pencarian jika dibutuhkan
    public static void cekEvent(String[] param, String searchCategory){
        System.out.println();
        String category = null;
        LocalDate categoryTanggal = null;
        
        // Jika paramater pencarian berisi , maka urutkan sesuai kategori pencarian
        if (searchCategory != null || !searchCategory.isEmpty()) {
            category = searchCategory;
            switch(category) {
                case "1":
                    listEvent.sort(Comparator.comparing(Event::nama));
                    break;
                case "2":
                    listEvent.sort(Comparator.comparing(Event::tempat));
                    break;
                case "3":
                    listEvent.sort(Comparator.comparing(Event::tanggal));
                    break;
            }
        }
        
        // Inisiasi Enumeration untuk menampilkan data ( Optional )
        Enumeration<Uasalpro.Event> e = Collections.enumeration(listEvent);
        System.out.println("Event yang akan datang : ");
        int i = 1;
        int nomorContainer = 1;
        container.clear();
        
        //Show List of events
        System.out.println("--------------------------------------------------------------------");
        System.out.printf("%-2s | %-7s | %-20s | %-15s | %-12s %n","NO","KODE","NAMA","LOKASI","TANGGAL");
        System.out.println("--------------------------------------------------------------------");
        while(e.hasMoreElements()){
            Uasalpro.Event currentEvent = e.nextElement();
            // Jika paramater pencarian berisi , maka urutkan sesuai kategori pencarian
            if(param[0] != null && !param[0].isEmpty()){
                param[0].toLowerCase();
                switch(searchCategory){
                case "1" :
                    category = currentEvent.nama();
                    break;
                case "2" :
                    category = currentEvent.tempat();
                    break;
                case "3" :
                    categoryTanggal = currentEvent.tanggal();
                    break;
                default:
                    System.out.println("Kategory pencarian salah");
                    return;
                }
                if (searchCategory.equals("3")) {
                    if(categoryTanggal.isAfter(LocalDate.parse(param[1]).minusDays(i)) && categoryTanggal.isBefore(LocalDate.parse(param[0]).plusDays(1))){
                        container.add(new Uasalpro.Item(Integer.toString(nomorContainer),currentEvent.kode()));
                        System.out.printf("%-2s | %-7s | %-20s | %-15s | %-12s %n",i,currentEvent.kode(),currentEvent.nama(),currentEvent.tempat(),currentEvent.tanggal());
                        nomorContainer++;
                    }
                } else {
                    if(category.toLowerCase().contains(param[0])){
                        container.add(new Uasalpro.Item(Integer.toString(nomorContainer),currentEvent.kode()));
                        System.out.printf("%-2s | %-7s | %-20s | %-15s | %-12s %n",i,currentEvent.kode(),currentEvent.nama(),currentEvent.tempat(),currentEvent.tanggal());
                        nomorContainer++;
                    }
                }
            } else {
                container.add(new Uasalpro.Item(Integer.toString(i),currentEvent.kode()));
                System.out.printf("%-2s | %-7s | %-20s | %-15s | %-12s %n",i,currentEvent.kode(),currentEvent.nama(),currentEvent.tempat(),currentEvent.tanggal());
            }
            i++;
        }
    }
    
    // Fungsi untuk proses pemesanan tiket
    public static void order(){
        System.out.print("Pilih event yang akan di pesan (00 untuk kembali) : ");
        String pilihEvent = input.next();
        
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
            System.out.println();
            
            System.out.printf("%-2s | %-10s | %-5s | %-10s |%n", "NO", "JENIS TIKET", "KUOTA", "HARGA");
            while(index < listJenisTiket.size()){
                Uasalpro.JenisTiket currentTiket = listJenisTiket.get(index);
                if(currentTiket.kodeEvent().equals(kode)){
                    System.out.printf("%-3s | %-10s | %-5s | %,.2f |%n", nomor, currentTiket.jenis(), currentTiket.maksimal(), currentTiket.harga());
                nomor++;
                }
                index++;
            }
            
            //Proses pemilihan dan store data Jenis Tiket
            System.out.print("Pilih Jenis Tiket (cth : REGULER) : ");
            String pilihanTiket = input.next().toLowerCase();
            
            index = 0;
            Uasalpro.JenisTiket tiketTerpilih = null;
            while(index < listJenisTiket.size()){
                Uasalpro.JenisTiket currentTiket = listJenisTiket.get(index);
                if(currentTiket.jenis().toLowerCase().contains(pilihanTiket) && currentTiket.kodeEvent().equals(kode)){
                    tiketTerpilih = currentTiket;
                }
                index++;
            }
            
            // Proses Input data tiket
            System.out.println("");
            System.out.println("------------------------------");
            System.out.println("------ LENGKAPI BIODATA ------");
            System.out.println("------------------------------");
            System.out.print("Masukkan Nama : ");
            String namaPemesan = input.next();
            System.out.print("Masukkan Nomor Hp : ");
            String noHpPemesan = input.next();
            System.out.print("Masukkan Alamat : ");
            String alamatPemesan = input.next();
            System.out.print("Masukkan Jumlah Tiket : ");
            int jumlahTiket = input.nextInt();
            input.nextLine();
            double totalHarga = jumlahTiket * tiketTerpilih.harga();
            
            System.out.println("");
            System.out.println("-----------------------------");
            System.out.println("------ KONFIRMASI DATA ------");
            System.out.println("-----------------------------");
            System.out.printf("%-15s : %-10s %n","Event", eventTerpilih.nama());
            System.out.printf("%-15s : %-10s %n","Jenis Tiket",tiketTerpilih.jenis());
            System.out.printf("%-15s : %-10s %n","Pemesan", namaPemesan);
            System.out.printf("%-15s : %-10s %n","No Hp", noHpPemesan);
            System.out.printf("%-15s : %-10s %n","Alamat", alamatPemesan);
            System.out.printf("%-15s : %-10s %n","Jumlah", jumlahTiket);
            System.out.printf("%-15s : %,.2f %n","Total", totalHarga);
            
            System.out.print("Data sudah Benar (Y/N) ?");
            String confirm = input.next();
            
            boolean statusPilihan = true;
            while(statusPilihan){
                if(confirm.toLowerCase().equals("y")){
                    String id = randomString();
                    listTiket.add(new Uasalpro.Tiket(id, tiketTerpilih.jenis(),kode,namaPemesan,noHpPemesan,alamatPemesan,jumlahTiket,totalHarga,"aktif"));
                    statusPilihan = false;
                    index = 0;
                    System.out.println("Terima kasih telah memesan!");
                            System.out.printf("%-24s %-25s %-10s %n","---------","TIKET EVENT","----------");
                            System.out.printf("%-12s%-20s%-10s%-5s%-11s %n","------------","----------------------","----------","-----","------------");
                            System.out.printf("%-7s %-20s | %-10s | %-10s %-7s %n","-------","Nama Pemesan","Kode Event","Kode Tiket","------");
                    while(index < listTiket.size()){
                        Uasalpro.Tiket currentTiket = listTiket.get(index);
                        if (currentTiket.kode().equals(id)) {
                            System.out.printf("%-12s%-20s%-10s%-5s%-11s %n","------------","----------------------","----------","-----","------------");
                            System.out.printf("%-7s %-20s | %-10s | %-10s %-7s %n","-------",currentTiket.namaPemesan(),currentTiket.kodeEvent(),currentTiket.kode(),"------");
                        }
                            System.out.printf("%-12s%-20s%-10s%-5s%-11s %n","------------","----------------------","----------","-----","------------");
                        index++;
                    }
                } else if (confirm.toLowerCase().equals("n")){
                    System.out.println("Pesanan Dibatalkan");
                    statusPilihan = false;
                } else {
                    System.out.println("Pilihan Tidak Valid");
                }
            }
        }
    }   
    
    // Fungsi pencarian event
    public void findEvent(){
        System.out.println("--------------------------------------");
        System.out.println("Mau cari berdasarkan apa ? ");
        System.out.println("1. Nama");
        System.out.println("2. Tempat");
        System.out.println("3. Tanggal");
        System.out.print("Pilih kategori cari : ");
        String searchCategory = input.next();
        String[] search = new String[2];
        
        if (searchCategory.equals("3")) {
            System.out.print("Masukkan Tanggal Tanggal Awal (YYYY-MM-DD) : "); 
            String search2 = input.next();
            System.out.print("Masukkan Tanggal Akhir (YYYY-MM-DD) : "); 
            search[1] = search2;
        } else {
            System.out.print("Masukkan Pencarian : "); 
        }
       
        String kata = input.next();
        search[0] = kata;
        
        
        cekEvent(search, searchCategory);
        if (container.size() < 1) {
            System.out.println("Data Tidak ditemukan");
            System.out.println("");
        } else {
            order();
        }
    }
    
    // Fungsi nuntuk menghapus data event
    public static void deleteEvent(String indexEvent){
        // Konfirmasi hapus
        boolean isActive = false;
        System.out.print("Yakin Hapus Event ? ");
        String confirm = input.nextLine();
        do {            
            switch (confirm.toLowerCase()) {
                case "y" :
                    // Hapus data Event berdasarkan indexnya
                    Uasalpro.listEvent.remove(Integer.parseInt(indexEvent));
                    System.out.println("Berhasil Hapus Event !");
                    break;
                case "n" :
                    System.out.println("Hapus event dibatalkan");
                    break;
                default :
                    System.out.println("Pilihan tidak valid !");
                    isActive = true;
                    break;
            }
        } while (isActive);
    }
    
    public static void editEvent(int indexEvent){
        String kode = null;
        Event selectedEvent = null;
        int index = 0;
        while(index < Uasalpro.container.size()){
            if (index == indexEvent) {
                Uasalpro.Item currentItem = container.get(index);
                kode = currentItem.kode();
            }
            index++;
        }
        
        index = 0;
        while(index < listEvent.size()){
            Event currentEvent = listEvent.get(index);
            if (currentEvent.kode().equals(kode)) {
                selectedEvent = currentEvent;
            }
            index++;
        }
        
        System.out.println("Masukkan Data baru ( ketik - untuk tidak merubah ) : ");
        System.out.print("Nama Event : ");
        String namaEvent = input.nextLine();
        if (namaEvent.equals("-")) {
            namaEvent = selectedEvent.nama();
        }
        System.out.print("Lokasi Event : ");
        String lokasiEvent = input.nextLine();
        if (lokasiEvent.equals("-")) {
            lokasiEvent = selectedEvent.tempat();
        }
        System.out.print("Tanggal Event (YYYY-MM-DD) : ");
        String tanggalEvent = input.nextLine();
        if (tanggalEvent.equals("-")) {
            tanggalEvent = selectedEvent.tanggal().toString();
        }
        
        Event updatedEvent = new Event(
                selectedEvent.kode(),
                namaEvent,
                lokasiEvent,
                LocalDate.parse(tanggalEvent)
        );
        
        listEvent.set(indexEvent-1, updatedEvent);
        System.out.print("Edit pilihan tiketnya (Y/N) ? ");
        String confirmEditTIket = input.next();
        switch(confirmEditTIket.toLowerCase()) {
            case "y":
                // Edit pilihan tiket
                break;
            case "n":
                // Batal pilihan tiket
                break;
            default :
                System.out.println("Pilihan tidak valid");
                break;
        }
        System.out.println("Edit Event Berhasil !!");
    }
    
    public static void tambahEvent(){
        int sizeAwal = listEvent.size();
        System.out.println("Masukkan Data Event Baru : ");
        System.out.print("Kode Event ( Tidak dapat diubah lagi ) : ");
        String kodeEvent = input.next();
        input.nextLine();
        System.out.print("Nama Event : ");
        String namaEvent = input.nextLine();
        System.out.print("Lokasi Event : ");
        String lokasiEvent = input.nextLine();
        System.out.print("Tanggal Event (YYYY-MM-DD) : ");
        String tanggalEvent = input.next();
        
        Event eventBaru = new Event(
                kodeEvent,
                namaEvent,
                lokasiEvent,
                LocalDate.parse(tanggalEvent)
        );
        
        listEvent.add(eventBaru);
        int sizeAkhir = listEvent.size();
        if (sizeAkhir - sizeAwal > 0) {
            System.out.println("Tambah data tiket untuk event " + namaEvent);
            boolean tambahTiket = true;
            while(tambahTiket){
                System.out.print("Masukkan Jenis Tiket (Cth REGULER) : ");
                String jenisTiket = input.next();
                System.out.println("Masukkan Harga untuk tiket " + jenisTiket + " (Cth 100000) : ");
                double  hargaTiket = input.nextDouble();
                System.out.println("Masukkan Jumlah Maksimal untuk tiket " + jenisTiket + " : ");
                int maksimal = input.nextInt();
                Uasalpro.JenisTiket newJenisTiket = new Uasalpro.JenisTiket(
                        kodeEvent,
                        jenisTiket,
                        maksimal,
                        hargaTiket
                );
                Uasalpro.listJenisTiket.add(newJenisTiket);
                System.out.println("Tambah Jenis Tiket [" +jenisTiket+ "] untuk [" +namaEvent+"] Berhasil !");
                System.out.print("Tambahkah Jenis Tiket lagi (Y/N) ? ");
                String tambahLagi = input.next();
                if (tambahLagi.toLowerCase().equals("n")) {
                    tambahTiket = false;
                }
            }
            System.out.println("Data Event berhasil ditambahkan!");
        } else {
            System.out.println("Data gagal ditambahkan, coba lagi nanti");
        }
    }
}
