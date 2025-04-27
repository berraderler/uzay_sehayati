package uzay_seyahati;

import java.io.*;
import java.util.*;

public class DosyaOkuma {

    public static ArrayList<Kisi> kisileriOku(String dosyaAdi) throws IOException {
        ArrayList<Kisi> kisiler = new ArrayList<>();
        File file = new File(dosyaAdi);
        if (!file.exists()) {
            throw new FileNotFoundException("Dosya bulunamadı: " + file.getAbsolutePath());
        }

        BufferedReader br = new BufferedReader(new FileReader(file));
        String satir;
        while ((satir = br.readLine()) != null) {
            String[] parcalar = satir.split("#");
            String isim = parcalar[0];
            int yas = Integer.parseInt(parcalar[1]);
            int kalanOmur = Integer.parseInt(parcalar[2]);
            String uzayAraciAdi = parcalar[3];

            kisiler.add(new Kisi(isim, yas, kalanOmur, uzayAraciAdi));
        }
        br.close();
        return kisiler;
    }

    public static HashMap<String, Gezegen> gezegenleriOku(String dosyaAdi) throws IOException {
        HashMap<String, Gezegen> gezegenler = new HashMap<>();
        BufferedReader br = new BufferedReader(new FileReader(dosyaAdi));
        String satir;
        while ((satir = br.readLine()) != null) {
            String[] parcalar = satir.split("#");
            String gezegenIsmi = parcalar[0];
            int mesafe = Integer.parseInt(parcalar[1]);
            String tarih = parcalar[2];

            gezegenler.put(gezegenIsmi, new Gezegen(gezegenIsmi, mesafe, tarih));
        }
        br.close();
        return gezegenler;
    }

    public static ArrayList<UzayAraci> araclariOku(String dosyaAdi, HashMap<String, Gezegen> gezegenler) throws IOException {
        ArrayList<UzayAraci> araclar = new ArrayList<>();
        BufferedReader br = new BufferedReader(new FileReader(dosyaAdi));
        String satir;
        while ((satir = br.readLine()) != null) {
            String[] parcalar = satir.split("#");

            String aracAdi = parcalar[0];
            String cikisGezegeni = parcalar[1];
            String varisGezegeni = parcalar[2];
            String cikisTarihi = parcalar[3];
            int mesafeSaat = Integer.parseInt(parcalar[4]);

            UzayAraci arac = new UzayAraci(aracAdi, cikisGezegeni, varisGezegeni, mesafeSaat, gezegenler);
            arac.setCikisTarihi(cikisTarihi);
            araclar.add(arac);
        }
        br.close();
        return araclar;
    }
}
