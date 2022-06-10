package Merkez;

import Bildirler.Bildir;
import Bildirler.ObserverKullanci;
import Kullanci.Kullanci;
import SveSO.*;

import java.util.Scanner;

public class Merkez {
    private static Kullanci kullanci;
    private static ISicaklik sicaklik;
    private static ISogutucu eyleyici;
    private static ObserverKullanci observerKullanci;
    private static Bildir bildir;
    private static Scanner in;
    private static int simdikiDerece = 0, sonrakiDerece = 0;


    public Merkez () {
        kullanci = new Kullanci();
        sicaklik = new Sicaklik();
        eyleyici = new Sogutucu();
        observerKullanci = new ObserverKullanci();
        bildir = new Bildir();
        in = new Scanner(System.in);
    }

    private void menu (){
        System.out.println("---- SECIM MENUSU ----");
        System.out.println("1. Sicaklik Goster");
        System.out.println("2. Sogutucu Ac");
        System.out.println("3. Sogutucu Kapat");
        System.out.println("4. Programdan Cik");
        System.out.println("--------------------------------------");
        System.out.print("Seciniz : ");
    }

    private void kullanciBilgiler(){
        String kullanciAdi = "";
        int sifre = 0, input = 0;
        boolean yeniOlusturdu = false;

        do {
            System.out.println("---- KULLANCI BILGILERI ----");
            if (!yeniOlusturdu)
                System.out.println("1. Yeni Kullanci Olustur");
            System.out.println("2. Sisteme Giris Yap");
            System.out.println("3. Sisteme Cik");
            System.out.println("--------------------------------------");
            System.out.print("Seciniz : ");
            input = in.nextInt();

            if (input == 1 || input == 2) {
                System.out.print("Kullanci Adi : ");
                kullanciAdi = in.next();
                System.out.print("Sifre : ");
                sifre = in.nextInt();
            }

            if (input == 1) {
                if (kullanci.yeniKullanci(kullanciAdi, sifre)) {
                    System.out.println("Basariyla Yeni Kullanci Olusturdu.......");
                    yeniOlusturdu = true;
                }
                else
                    System.out.println("Sifreniz 8 Basamakli Kucuktur Yada Bu Kullanci Adi Kullanmistir........");
            }
            else if (input == 2) {
                if (kullanci.girisYap(kullanciAdi, sifre))
                {
                    System.out.println("Basariyla Sisteme Girdi......");
                    observerKullanci.setObserver(bildir);
                    bildir.add(observerKullanci);
                    break;
                }
                else
                    System.out.println("Kullanci Adi Yada Sifre Yalnis......");
            }
            else if (input == 3)
                System.exit(0);
            else
                System.out.println("Sectiniz Numara Yoktur");
        }while (true);
    }

    private void akilliCihaz(){
        int input = 0;
        boolean birinci = true, sogutucuAckiMi = false;

        while (true)
        {
            menu();
            input = in.nextInt();

            if (input == 1 || (input == 2 && !sogutucuAckiMi))
                sicaklikControl(input);

            switch (input)
            {
                case 1:
                    sicaklik.sicaklikGoster(simdikiDerece);
                    bildir.update();
                    birinci = false;
                    break;
                case 2:
                    if (!sogutucuAckiMi) {
                        eyleyici.sogutucuAc(simdikiDerece, sonrakiDerece);
                        simdikiDerece = sonrakiDerece;
                        bildir.update();
                        sogutucuAckiMi = true;
                    }else System.out.println("Sogutucu Zaten Acik.....");
                    break;
                case 3:
                    if (sogutucuAckiMi) {
                        eyleyici.sogutucuKapat();
                        sogutucuAckiMi = false;
                    }
                    else System.out.println("Sogutucu Zaten Kapali.....");
                    break;
                case 4:
                    System.exit(0);
                    break;
                default:
                    System.out.println("Sectiniz Numara Yoktur");
                    break;
            }
        }
    }

    private void sicaklikControl(int input){

        if (input == 1) {
                simdikiDerece = sicaklik.sicaklikOku();
        }
        else if (input == 2){
            if (simdikiDerece < 20)
                    return;
            sonrakiDerece = simdikiDerece - 15;
        }
    }

    public void basla()
    {
        kullanciBilgiler();
        akilliCihaz();
    }
}
