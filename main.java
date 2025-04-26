package uzay_seyahati;

import java.util.*;

public class main {
    public static void main(String[] args) throws Exception {
        ArrayList<Kisi> kisiler = DosyaOkuma.kisileriOku("Kisiler.txt");
        HashMap<String, Gezegen> gezegenler = DosyaOkuma.gezegenleriOku("Gezegenler.txt");
        ArrayList<UzayAraci> araclar = DosyaOkuma.araclariOku("Araclar.txt");

    
        Simulasyon.baslatSimulasyon(kisiler, gezegenler, araclar);
    }
}
