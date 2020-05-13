package com.faris;

import java.util.*;
import java.io.IOException;

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

            System.out.print("\n\nPilihan Anda (1~5): ");
            pilihanUser = userInputTerminal.next();

            switch (pilihanUser){
                case "1":
                    System.out.println("DAFTAR MENU\n");
                    break;
                case "2":
                    System.out.println("CARI MENU\n");
                    break;
                case "3":
                    System.out.println("TAMBAH MENU\n");
                    break;
                case "4":
                    System.out.println("UBAH MENU\n");
                    break;
                case "5":
                    System.out.println("HAPUS MENU\n");
                    break;
                default:
                    System.err.println("INPUT SALAH!\n");
            }
            //Jika user pilih n maka akan keluar dari program.
            System.out.print("Lanjutkan (y/n)?");
            pilihanUser = userInputTerminal.next();

            is_continue = pilihanUser.equalsIgnoreCase("y");

        }

    }

    private static void tampilkanData() throws IOException{

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
