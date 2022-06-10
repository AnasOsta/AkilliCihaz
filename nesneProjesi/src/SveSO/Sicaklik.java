package SveSO;

import java.util.Random;

public class Sicaklik implements ISicaklik{
    @Override
    public int sicaklikOku() {
        Random random = new Random();
        return random.nextInt(21) + 20;
    }

    @Override
    public int sicaklikGoster(int derece) {
        System.out.println("Sicaklik degeri " + derece + " derecedir.");
        return sicaklikOku();
    }
}
