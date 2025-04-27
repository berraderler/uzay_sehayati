package uzay_seyahati;

import java.util.*;

public class Simulasyon {

    public static void baslatSimulasyon(ArrayList<Kisi> kisiler, HashMap<String, Gezegen> gezegenler, ArrayList<UzayAraci> araclar) throws InterruptedException {
        for (Kisi k : kisiler) {
            for (UzayAraci u : araclar) {
                if (u.getAdi().equals(k.getUzayAraciAdi())) {
                    u.kisiEkle(k);
                }
            }
        }

        int saat = 0;

        while (true) {
            Thread.sleep(1);
            saat++;

            for (Gezegen g : gezegenler.values()) {
                g.saatIlerle();
            }

            for (UzayAraci u : araclar) {
                if (!u.isHareketEtti()) {
                    // Gezegenin tarihine bak, çıkış tarihi gelmiş mi?
                    Gezegen cikisGezegen = gezegenler.get(u.getCikis());
                    if (cikisGezegen.getLocalDate().isAfter(u.getCikisTarihi()) || cikisGezegen.getLocalDate().isEqual(u.getCikisTarihi())) {
                        u.setHareketEtti(true); // artık hareket etmeye başlıyor
                    }
                }
                if (u.isHareketEtti()) {
                    u.saatGecir(1); // sadece hareket etmeye başladıysa saat geçiriyoruz
                }
            }


            for (UzayAraci u : araclar) {
                Iterator<Kisi> iterator = u.getYolcular().iterator();
                while (iterator.hasNext()) {
                    Kisi k = iterator.next();
                    k.saatGecir();
                    if (k.getKalanOmur() <= 0) {
                        u.getYolcular().remove(k);
                        Gezegen gezegen = gezegenler.get(u.getVaris());
                        gezegen.nufusAzalt(1);
                    }
                }
            }

            temizleEkran();
            yazdirDurum(gezegenler, araclar, saat);

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
        System.out.println("Uzay Araclari:");
        System.out.println(String.format("%-10s %-10s %-10s %-10s %-20s %-25s",
                "Arac Adı", "Durum", "Çıkış", "Varış", "Hedefe Kalan Saat", "Hedefe Varacağı Tarih"));

        for (UzayAraci u : araclar) {
            String kalanSaat = u.isImha() ? "--" : String.valueOf(u.getKalanSaat());
            String varisTarihi;
            if (u.isImha()) varisTarihi = "--";
            else if (u.isVardi()) varisTarihi = "Varış yapıldı";
            else varisTarihi = u.getHedefeVarisTarihi();

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
    }
}
