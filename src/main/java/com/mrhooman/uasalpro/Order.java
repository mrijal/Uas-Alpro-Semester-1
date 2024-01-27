/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mrhooman.uasalpro;

import static com.mrhooman.uasalpro.Uasalpro.container;
import static com.mrhooman.uasalpro.Uasalpro.listEvent;
import java.util.Collections;
import java.util.Enumeration;

/**
 *
 * @author ACER
 */
public class Order {
    public void cekEvent(){
        Enumeration<Uasalpro.Event> e = Collections.enumeration(listEvent);
        System.out.println("Event yang akan datang : ");
        int i = 1;
        while(e.hasMoreElements()){
            Uasalpro.Event currentEvent = e.nextElement();
            container.add(new Uasalpro.Item(Integer.toString(i),currentEvent.kode()));
            System.out.println(i + ". " +currentEvent.kode() + " | " + currentEvent.nama() + " | " + currentEvent.tempat() + " | " + currentEvent.tanggal());
            i++;
        }

        System.out.print("Pilih event yang akan di pesan : ");
        String pilihEvent = Uasalpro.input.next();
        System.out.println("kamu memilih nomor : " + pilihEvent);
        System.out.println("-----------------");
        Uasalpro.input.next();
    }
}
