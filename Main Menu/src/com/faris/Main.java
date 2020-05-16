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
                    cariData();
                    break;
                case "3":
                    System.out.println("TAMBAH MENU");
                    break;
                case "4":
                    System.out.println("UBAH MENU");
                    break;
                case "5":
                    System.out.println("HAPUS MENU");
                    break;
                default:
                    System.err.println("INPUT SALAH!\n");
            }
            //Jika user pilih n maka akan keluar dari program.

            is_continue = getYesOrNo("\nLanjutkan");

        } //end-while

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

    private static void tampilkanData() throws IOException{
        FileReader fileInput;
        BufferedReader bufferedInput;

        try {
            fileInput = new FileReader("db.txt");
            bufferedInput = new BufferedReader(fileInput);
        } catch (Exception e){
            System.out.println("Data tidak ditemukan");
            System.out.println("Silakan tambah data terlebih dahulu");
            return;
        }

        //mengambil data per-baris dr file yg diinput ke BufferedReader
        String data = bufferedInput.readLine();

        String a = "\n| No |\tNama Menu\t|\tHarga\t|";

        //penutup tabel atas
        System.out.println(a);
        System.out.print("+  ");
        for (int i = 0; i < a.length(); i++){
            System.out.print("-");
        }
        System.out.print("\t+");


        //mengambil data sesuai dengan isi database.
        int num_autoIncrement = 0;
        while(data != null){
            num_autoIncrement++;
            StringTokenizer stringToken = new StringTokenizer(data,",");

            System.out.println();
            stringToken.nextToken();
            System.out.printf("| %2s ",num_autoIncrement);
            System.out.printf("|\t%-11s\t|",stringToken.nextToken());
            System.out.printf("\t%s\t|",stringToken.nextToken());

            data = bufferedInput.readLine();
        }

        //penutup tabel bawah
        System.out.println();
        System.out.print("   ");
        for (int i = 0; i < a.length(); i++){
            System.out.print("-");
        }
        System.out.print("\t ");

    }

    private static void cariData() throws IOException{

        try{
            File db_file = new File("db.txt");
        } catch (Exception e){
            System.out.println("File tidak ada!");
            System.out.println("Silakan buat file database.");
            return;
        }

        Scanner userInputTerminal = new Scanner(System.in);
        System.out.print("Masukkan keyword: ");
        String keywordInput = userInputTerminal.nextLine();
        System.out.println(keywordInput);

        //split whitespace
        String[] keywords = keywordInput.split("\\s+");

        FileReader fileInput = new FileReader("db.txt");
        BufferedReader bufferedInput = new BufferedReader(fileInput);

        String data = bufferedInput.readLine();
        boolean is_exist;
        int num_autoIncrement = 0;

        String a = "\n| No |\tNama Menu\t|\tHarga\t|";
        //penutup tabel atas
        System.out.println(a);
        System.out.print("+  ");
        for (int i = 0; i < a.length(); i++){
            System.out.print("-");
        }
        System.out.print("\t+");

        while(data != null){
            is_exist = true;

            for(String keyword:keywords){
                is_exist = data.toLowerCase().contains(keyword.toLowerCase());
            }

            if(is_exist){
                num_autoIncrement++;
                StringTokenizer stringToken = new StringTokenizer(data, ",");

                System.out.println();
                stringToken.nextToken();
                System.out.printf("| %2s ",num_autoIncrement);
                System.out.printf("|\t%-11s\t|",stringToken.nextToken());
                System.out.printf("\t%s\t|",stringToken.nextToken());
            }
            data = bufferedInput.readLine();
        }

        //penutup tabel bawah
        System.out.println();
        System.out.print("   ");
        for (int i = 0; i < a.length(); i++){
            System.out.print("-");
        }
        System.out.print("\t ");

    }

    private static void tambahData() throws IOException{

    }

    private static void updateData() throws IOException{

    }

    private static void hapusData() throws IOException{

    }

}
