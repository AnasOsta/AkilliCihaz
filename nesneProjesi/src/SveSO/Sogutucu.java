package SveSO;

public class Sogutucu implements ISogutucu{
    @Override
    public void sogutucuAc(int once , int sonra) {

        System.out.println("Sogutucu Acildi.......");
        System.out.println("Onceki sicaklik derece = " + once + " Sogutucu acildi sonra yeni sicaklik derecesi oldu = " + sonra);
    }

    @Override
    public void sogutucuKapat() {
        System.out.println("Sogutucu Kapatildi.......");
    }
}
