package uzay_seyahati;

import java.util.*;

public class Simulasyon {

    public static void baslatSimulasyon(ArrayList<Kisi> kisiler, HashMap<String, Gezegen> gezegenler, ArrayList<UzayAraci> araclar) throws InterruptedException {
        // Kisileri uygun uzay aracına ekle
        for (Kisi k : kisiler) {
            for (UzayAraci u : araclar) {
                if (u.getAdi().equals(k.getUzayAraciAdi())) {
                    u.kisiEkle(k);
                }
            }
        }

        int saat = 0;

        while (true) {
            Thread.sleep(100); // Simülasyon hızı
            saat++;

            // Gezegenlerde saat ilerlet
            for (Gezegen g : gezegenler.values()) {
                g.saatIlerle();
            }

            // Uzay araçlarında saat ilerlet
            for (UzayAraci u : araclar) {
                u.saatGecir(1); // her adımda 1 saat geçiriyoruz
            }

            temizleEkran();
            yazdirDurum(gezegenler, araclar, saat);

            // Eğer bütün araçlar ya vardıysa ya da imha olduysa döngüyü bitir
            boolean bitti = true;
            for (UzayAraci u : araclar) {
                if (!u.isImha() && !u.isVardi()) {
                    bitti = false;
                    break;
                }
            }

            if (bitti) break;
        }

        System.out.println("\nTüm araçlar görevini tamamladı.");
    }

    private static void temizleEkran() {
        try {
            if (System.getProperty("os.name").contains("Windows"))
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            else
                new ProcessBuilder("clear").inheritIO().start().waitFor();
        } catch (Exception e) {
            System.out.println("Ekran temizlenemedi.");
        }
    }


    private static void yazdirDurum(HashMap<String, Gezegen> gezegenler, ArrayList<UzayAraci> araclar, int saat) {
        System.out.println("Simülasyon Saati: " + saat + "\n");

        System.out.println("Uzay Araclari:");
        System.out.println(String.format("%-10s %-10s %-10s %-10s %-20s %-25s",
                "Arac Adı", "Durum", "Çıkış", "Varış", "Hedefe Kalan Saat", "Hedefe Varacağı Tarih"));

        for (UzayAraci u : araclar) {
            String kalanSaat = u.isImha() ? "--" : String.valueOf(u.getKalanSaat());
            String varisTarihi;
            if (u.isImha()) varisTarihi = "--";
            else if (u.isVardi()) varisTarihi = "Varış yapıldı";
            else varisTarihi = "Hesaplanıyor"; // Eğer özel bir tarih hesaplaması yapılmadıysa.

            System.out.println(String.format("%-10s %-10s %-10s %-10s %-20s %-25s",
                    u.getAdi(),
                    u.getDurum(),
                    u.getCikis(),
                    u.getVaris(),
                    kalanSaat,
                    varisTarihi));
        }

        System.out.println("\nGezegen Bilgileri:");
        System.out.print("| Gezegen |");
        for (Gezegen g : gezegenler.values()) {
            System.out.printf(" %-12s |", g.getIsim());
        }
        System.out.println();

        System.out.print("| Tarih   |");
        for (Gezegen g : gezegenler.values()) {
            System.out.printf(" %-12s |", g.getTarih());
        }
        System.out.println();

        System.out.print("| Nüfus   |");
        for (Gezegen g : gezegenler.values()) {
            System.out.printf(" %-12d |", g.getNufus());
        }
        System.out.println();
    }}