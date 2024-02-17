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
 * @author Muhammad Rijal
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
        System.out.println("");
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
            // Jika paramater pencarian berisi , maka urutkan sesuai kategori pencarian dan isi container hanya dengan event yang memenuhi kriteria
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
                // Isi container dengan Event yang memenuhi kriteria
                if (searchCategory.equals("3")) {
                    if(categoryTanggal.isAfter(LocalDate.parse(param[1]).minusDays(i)) && categoryTanggal.isBefore(LocalDate.parse(param[0]).plusDays(1))){
                        container.add(new Uasalpro.Item(Integer.toString(nomorContainer),currentEvent.kode()));
                        System.out.printf("%-2s | %-7s | %-20s | %-15s | %-12s %n",nomorContainer,currentEvent.kode(),currentEvent.nama(),currentEvent.tempat(),currentEvent.tanggal());
                        nomorContainer++;
                    }
                } else {
                    if(category.toLowerCase().contains(param[0].toLowerCase())){
                        container.add(new Uasalpro.Item(Integer.toString(nomorContainer),currentEvent.kode()));
                        System.out.printf("%-2s | %-7s | %-20s | %-15s | %-12s %n",nomorContainer,currentEvent.kode(),currentEvent.nama(),currentEvent.tempat(),currentEvent.tanggal());
                        nomorContainer++;
                    }
                }
            } else {
                // Isi container dengan semua Event
                container.add(new Uasalpro.Item(Integer.toString(i),currentEvent.kode()));
                System.out.printf("%-2s | %-7s | %-20s | %-15s | %-12s %n",i,currentEvent.kode(),currentEvent.nama(),currentEvent.tempat(),currentEvent.tanggal());
            }
            i++;
        }
    }
    
    // Fungsi untuk proses pemesanan tiket
    public static void order(){
        System.out.print("Pilih event yang akan di pesan (00 untuk kembali) : ");
        String pilihEvent = input.nextLine();
        
        // Pemesanan batal dan kembali ke menu awal
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
            //Menampilkan Tiket tiket yang tersedia untuk event yang dipilih
            while(index < listJenisTiket.size()){
                Uasalpro.JenisTiket currentTiket = listJenisTiket.get(index);
                if(currentTiket.kodeEvent().equals(kode)){
                    System.out.printf("%-3s | %-10s | %-5s | %,.2f |%n", nomor, currentTiket.jenis(), currentTiket.maksimal(), currentTiket.harga());
                nomor++;
                }
                index++;
            }
            
            //Proses pemilihan dan store data Jenis Tiket untuk event yang dipesan
            System.out.print("Pilih Jenis Tiket (cth : REGULER) : ");
            String pilihanTiket = input.nextLine().toLowerCase();
            
            index = 0;
            Uasalpro.JenisTiket tiketTerpilih = null;
            while(index < listJenisTiket.size()){
                Uasalpro.JenisTiket currentTiket = listJenisTiket.get(index);
                if(currentTiket.jenis().toLowerCase().contains(pilihanTiket) && currentTiket.kodeEvent().equals(kode)){
                    tiketTerpilih = currentTiket;
                }
                index++;
            }
            
            if (tiketTerpilih == null) {
                System.out.println("Tiket tidak ada");
            } else {
                // Proses Input biodata untuk tiket
                System.out.println("");
                System.out.println("------------------------------");
                System.out.println("------ LENGKAPI BIODATA ------");
                System.out.println("------------------------------");
                System.out.print("Masukkan Nama : ");
                String namaPemesan = input.nextLine();
                System.out.print("Masukkan Nomor Hp : ");
                String noHpPemesan = input.nextLine();
                System.out.print("Masukkan Alamat : ");
                String alamatPemesan = input.nextLine();
                System.out.print("Masukkan Jumlah Tiket : ");
                int jumlahTiket = Integer.parseInt(input.nextLine());
                
                double totalHarga = jumlahTiket * tiketTerpilih.harga();

                // Konfirmasi biodata
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
                String confirm = input.nextLine();

                boolean statusPilihan = true;
                // Pengecekkan konfirmasi , jika benar maka masukkan data ke listTiket , jika tidak maka kembali ke menu awal
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
                        // Menampilkan kode tiket yang digenerate otommatis
                        while(index < listTiket.size()){
                            Uasalpro.Tiket currentTiket = listTiket.get(index);
                            if (currentTiket.kode().equals(id)) {
                                System.out.printf("%-12s%-20s%-10s%-5s%-11s %n","------------","----------------------","----------","-----","------------");
                                System.out.printf("%-7s %-20s | %-10s | %-10s %-7s %n","-------",currentTiket.namaPemesan(),currentTiket.kodeEvent(),currentTiket.kode(),"------");
                                System.out.printf("%-12s%-20s%-10s%-5s%-11s %n","------------","----------------------","----------","-----","------------");
                            }
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
    }   
    
    // Fungsi pencarian event
    public void findEvent(){
        System.out.println("--------------------------------------");
        System.out.println("Mau cari berdasarkan apa ? ");
        System.out.println("1. Nama");
        System.out.println("2. Tempat");
        System.out.println("3. Tanggal");
        System.out.print("Pilih kategori cari : ");
        String searchCategory = input.nextLine();
        String[] search = new String[2];
        
        // Jika kategori dipilih adalah tanggal maka pencarian diharuskan mengisi 2 variable ( tanggal awal dan akhir )
        if (searchCategory.equals("3")) {
            System.out.print("Masukkan Tanggal Tanggal Awal (YYYY-MM-DD) : "); 
            String search2 = input.nextLine();
            System.out.print("Masukkan Tanggal Akhir (YYYY-MM-DD) : "); 
            search[1] = search2;
        } else {
            System.out.print("Masukkan Pencarian : "); 
        }
       
        String kata = input.nextLine();
        search[0] = kata;
        
        // Memanggil fungsi cekEvent untuk mencari sekaligus menampilkan data Event
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
        System.out.print("Yakin Hapus Event (Y/N) ? ");
        String confirm = input.nextLine();
        do {            
            switch (confirm.toLowerCase()) {
                case "y" :
                    // Hapus data Event berdasarkan indexnya
                    Uasalpro.listEvent.remove(Integer.parseInt(indexEvent));
                    System.out.println("Berhasil Hapus Event !");
                    break;
                case "n" :
                    // Batal hapus data
                    System.out.println("Hapus event dibatalkan");
                    break;
                default :
                    System.out.println("Pilihan tidak valid !");
                    isActive = true;
                    break;
            }
        } while (isActive);
    }
    
    // Fungsi untuk edit Event dengan mengambil parameter index event yang akan diedit
    public static void editEvent(int indexEvent){
        // Inisiasi variable untuk pencarian event
        String kode = null;
        Event selectedEvent = null;
        int index = 0;
        while(index < Uasalpro.container.size()){
            if (index+1 == indexEvent) {
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
        
        // Proses input data baru jika diperlukan , jika tidak maka tinggal mengisi strip ( - )
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
        
        // Pembuatan record event untuk update data event yang dipilih
        Event updatedEvent = new Event(
                selectedEvent.kode(),
                namaEvent,
                lokasiEvent,
                LocalDate.parse(tanggalEvent)
        );
        // Update Event yang dipilih dengan data data yang baru
        listEvent.set(indexEvent-1, updatedEvent);
        
        // konfirmasi edit beserta pilihan tiket
        System.out.print("Edit pilihan tiketnya (Y/N) ? ");
        String confirmEditTIket = input.nextLine();
        boolean kondisiEditPilTiket = true;
        while(kondisiEditPilTiket){
            switch(confirmEditTIket.toLowerCase()) {
                case "y":
                    TiketClass.editJenisTiket(selectedEvent.kode());
                    kondisiEditPilTiket = false;
                    break;
                case "n":
                    System.out.println("Edit Jenis Tiket dibatalkan");
                    kondisiEditPilTiket = false;
                    break;
                default :
                    System.out.println("Pilihan tidak valid");
                    break;
            }
        }
        System.out.println("Edit Event Berhasil !!");
    }
    
    // Fungsi untuk tambah event
    public static void tambahEvent(){
        int sizeAwal = listEvent.size();
        System.out.println("Masukkan Data Event Baru : ");
        System.out.print("Kode Event ( Tidak dapat diubah lagi ) : ");
        String kodeEvent = input.nextLine();
        System.out.print("Nama Event : ");
        String namaEvent = input.nextLine();
        System.out.print("Lokasi Event : ");
        String lokasiEvent = input.nextLine();
        System.out.print("Tanggal Event (YYYY-MM-DD) : ");
        String tanggalEvent = input.nextLine();
        
        // Inisiasi Record Event untuk ditambahkan ke listEvent
        Event eventBaru = new Event(
                kodeEvent,
                namaEvent,
                lokasiEvent,
                LocalDate.parse(tanggalEvent)
        );
        
        // Tambahkan Event baru ke listEvent
        listEvent.add(eventBaru);
        
        // Jika penambahan berhasil maka proses penambahan jenis tiket
        int sizeAkhir = listEvent.size();
        if (sizeAkhir - sizeAwal > 0) {
            System.out.println("Tambah data tiket untuk event " + namaEvent);
            boolean tambahTiket = true;
            while(tambahTiket){
                System.out.print("Masukkan Jenis Tiket (Cth REGULER) : ");
                String jenisTiket = input.nextLine();
                System.out.print("Masukkan Harga untuk tiket " + jenisTiket + " (Cth 100000) : ");
                double  hargaTiket = Double.parseDouble(input.nextLine());
                System.out.print("Masukkan Jumlah Maksimal untuk tiket " + jenisTiket + " : ");
                int maksimal = Integer.parseInt(input.nextLine());
                Uasalpro.JenisTiket newJenisTiket = new Uasalpro.JenisTiket(
                        kodeEvent,
                        jenisTiket,
                        maksimal,
                        hargaTiket
                );
                Uasalpro.listJenisTiket.add(newJenisTiket);
                System.out.println("Tambah Jenis Tiket [" +jenisTiket+ "] untuk [" +namaEvent+"] Berhasil !");
                System.out.println("");
                System.out.print("Tambahkah Jenis Tiket lagi (Y/N) ? ");
                String tambahLagi = input.nextLine();
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
