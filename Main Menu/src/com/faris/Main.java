package com.faris;

import java.util.*;
import java.io.*;
import java.lang.*;

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
                    tambahData();
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

            is_continue = getYesOrNo("\nKe Menu Awal");

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

    private static boolean dataSudahAda(String[] keywords) throws IOException{
        FileReader fileInput = new FileReader("db.txt");
        BufferedReader bufferedReader = new BufferedReader(fileInput);
        String data = bufferedReader.readLine();
//        Scanner data_scan;
//        String nama_menu;
//        data_scan = new Scanner(data);
//        data_scan.useDelimiter(",");
//        nama_menu = data_scan.next();
//        nama_menu = data_scan.next();
//        nama_menu = data_scan.next();

        boolean is_exist = false;

        while(data != null){
            is_exist = true;

            for (int i = 0; i < data.length();i++){
                is_exist = is_exist && data.toLowerCase().contains(keywords[1].toLowerCase());
            }
            if(is_exist){
                System.err.println("Data sudah ada di database!");
                break;
            }
            data = bufferedReader.readLine();
        }
        return is_exist;
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

        String a = "\n| No |\tJenis Menu\t|\tNama Menu\t|\tHarga\t|";

        //penutup tabel atas
        System.out.print("+ ---");
        for (int i = 0; i < a.length(); i++){
            System.out.print("-");
        }
        System.out.print("--\t+");
        System.out.println(a);
        System.out.print("+ ---");
        for (int i = 0; i < a.length(); i++){
            System.out.print("-");
        }
        System.out.print("--\t+");


        //mengambil data sesuai dengan isi database.
        int num_autoIncrement = 0;
        while(data != null){
            num_autoIncrement++;
            StringTokenizer stringToken = new StringTokenizer(data,",");

            System.out.println();
            stringToken.nextToken();
            System.out.printf("| %2s ",num_autoIncrement);
            System.out.printf("|\t%-10s\t",stringToken.nextToken());
            System.out.printf("|\t%-11s\t|",stringToken.nextToken());
            System.out.printf("\t%s\t|",stringToken.nextToken());

            data = bufferedInput.readLine();
        }

        //penutup tabel bawah
        System.out.println();
        System.out.print("+ ---");
        for (int i = 0; i < a.length(); i++){
            System.out.print("-");
        }
        System.out.print("--\t+");

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

        String a = "\n| No |\tJenis Menu\t|\tNama Menu\t|\tHarga\t|";
        //penutup tabel atas
        System.out.print("+ ---");
        for (int i = 0; i < a.length(); i++){
            System.out.print("-");
        }
        System.out.print("--\t+");
        System.out.println(a);
        System.out.print("+ ---");
        for (int i = 0; i < a.length(); i++){
            System.out.print("-");
        }
        System.out.print("--\t+");

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
                System.out.printf("|\t%-10s\t",stringToken.nextToken());
                System.out.printf("|\t%-11s\t|",stringToken.nextToken());
                System.out.printf("\t%s\t|",stringToken.nextToken());
            }
            data = bufferedInput.readLine();
        }

        //penutup tabel bawah
        System.out.println();
        System.out.print("+ ---");
        for (int i = 0; i < a.length(); i++){
            System.out.print("-");
        }
        System.out.print("--\t+");

    }

    private static void tambahData() throws IOException{
        FileWriter fileOutput = new FileWriter("db.txt",true);
        BufferedWriter bufferedWriter = new BufferedWriter(fileOutput);




        Scanner adminInputTerminal = new Scanner(System.in);
        String jenisMenu, namaMenu, makanan, minuman;
        int harga = 0;
        boolean is_continue = true;
        String primaryKey;

        //input menu
        System.out.print("Jenis Menu(makanan/minuman): ");
        jenisMenu = adminInputTerminal.nextLine();
        System.out.print("Nama Menu: ");
        namaMenu = adminInputTerminal.nextLine();
        boolean hargaBenar = false;
        while(!hargaBenar){
            try{
                System.out.print("Harga(digit): ");
                harga = adminInputTerminal.nextInt();
                hargaBenar = true;
            } catch (InputMismatchException e){
                adminInputTerminal.next();
                System.out.println("\nMASUKKAN ANGKA!");
            }
        }

//        String[] keywords = {jenisMenu+','+namaMenu+","+harga};
        String hargaString = Integer.toString(harga);
        String[] keyword = {jenisMenu,namaMenu,hargaString};
        boolean dataSudahAda = dataSudahAda(keyword);
        makanan = jenisMenu;
        minuman = jenisMenu;
        int nomorId = urutanPrimaryKey(jenisMenu) + 1;


        //tampilkan data yg akan dimasukkan ke database
        if(!dataSudahAda){
            System.out.println("\nAkan Ditambahkan "+"\n-----------------"+"\nJenis Menu\t: "+ keyword[0] + " "+"\nNama Menu\t: " + keyword[1] + " "+"\nHarga\t\t: " + keyword[2]);

            is_continue = getYesOrNo("\nPastikan data sudah benar.\nINPUT DATA KE DATABASE?");

            if (is_continue){
                switch (jenisMenu){
                    case "makanan":
                        makanan = jenisMenu.replace("makanan","f");
                        primaryKey = String.format("%s_%03d",makanan,nomorId);
                        bufferedWriter.write(primaryKey+","+jenisMenu+","+namaMenu+","+harga);
                        bufferedWriter.newLine();
                        bufferedWriter.flush();
                        System.out.println("INPUT BERHASIL!");
                        break;
                    case "minuman":
                        minuman = jenisMenu.replace("minuman","b");
                        primaryKey = String.format("%s_%03d",minuman,nomorId);
                        System.out.println("INPUT BERHASIL!");
                        bufferedWriter.write(primaryKey+","+jenisMenu+","+namaMenu+","+harga);
                        bufferedWriter.newLine();
                        bufferedWriter.flush();
                        break;
                }
            } else {
                System.err.println("INPUT DATA DIBATALKAN.");
            }
        }

        bufferedWriter.close();

    }

    private static int urutanPrimaryKey(String jenisMenu) throws IOException{
        FileReader fileInput = new FileReader("db.txt");
        BufferedReader bufferedReader = new BufferedReader(fileInput);

        String data = bufferedReader.readLine();
        Scanner dataScan;
        String primaryKey;
        int nomorId = 0;
        String makanan = jenisMenu;
        String minuman = jenisMenu;

        while(data != null){
            dataScan = new Scanner(data);
            dataScan.useDelimiter(",");
            primaryKey = dataScan.next();
            dataScan = new Scanner(primaryKey);
            dataScan.useDelimiter("_");

            switch (jenisMenu){
                case "makanan":
                    makanan = jenisMenu.replace("makanan","f");
                    primaryKey = String.format("%s_%03d",makanan,nomorId);
                    break;
                case "minuman":
                    minuman = jenisMenu.replace("minuman","b");
                    primaryKey = String.format("%s_%03d",minuman,nomorId);
                    break;
            }

            if (makanan.equalsIgnoreCase(dataScan.next()) || minuman.equalsIgnoreCase(dataScan.next())){
                nomorId = dataScan.nextInt();
            }
            data = bufferedReader.readLine();
        }
        return nomorId;
    }

    private static void updateData() throws IOException{

    }

    private static void hapusData() throws IOException{

    }

}
