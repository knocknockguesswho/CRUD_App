package com.faris;

import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {

        Scanner userInputTerminal = new Scanner(System.in);
        String pilihanUser;
        boolean is_continue = true;

        while(is_continue){

            //Reload
            clearScreen();

            System.out.println("Database Menu Makanan\n");
            System.out.println("1.\tLihat Daftar Menu");
            System.out.println("2.\tCari Menu");
            System.out.println("3.\tTambah Menu");
            System.out.println("4.\tUbah Menu");
            System.out.println("5.\tHapus Menu");

            System.out.print("\n\nPilih (1~5): ");
            pilihanUser = userInputTerminal.next();

            //switch with input string
            switch (pilihanUser){
                case "1":
                    System.out.println("DAFTAR MENU");
                    tampilkanData();
                    break;
                case "2":
                    System.out.println("CARI MENU");
                    tampilkanData();
                    break;
                case "3":
                    System.out.println("TAMBAH MENU");
                    tampilkanData();
                    break;
                case "4":
                    System.out.println("UBAH MENU");
                    tampilkanData();
                    break;
                case "5":
                    System.out.println("HAPUS MENU");
                    tampilkanData();
                    break;
                default:
                    System.err.println("INPUT SALAH!\n");
            }
            //Jika user pilih n maka akan keluar dari program.

            is_continue = getYesOrNo("Lanjutkan");

        } //end-while

    }

    private static void tampilkanData() throws IOException{
        System.out.println("*DATA DITAMPILKAN DISINI*\n");
    }

    private static boolean getYesOrNo(String message){
        Scanner userInputTerminal = new Scanner(System.in);
        System.out.print(message + " (y/n)? ");
        String pilihanUser = userInputTerminal.next();

        while(!pilihanUser.equalsIgnoreCase("y") && !pilihanUser.equalsIgnoreCase("n")){
            System.out.println("\npilihan anda salah!");
            System.out.print(message + " (y/n)? ");
            pilihanUser = userInputTerminal.next();
        }

        return pilihanUser.equalsIgnoreCase("y");
    }

    private static void clearScreen(){
        try {
            if (System.getProperty("os.name").contains("Windows")){
                new ProcessBuilder("cmd","/c","cls").inheritIO().start().waitFor();
            } else {
                System.out.print("\033\143");
            }
        } catch (Exception e){
            System.err.println("Tidak bisa reload");
        }
    }

}
