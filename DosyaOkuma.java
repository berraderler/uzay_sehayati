package uzay_seyahati;

import java.io.*;
import java.util.*;

public class DosyaOkuma {

	public static ArrayList<Kisi> kisileriOku(String dosyaAdi) throws IOException {
	    ArrayList<Kisi> kisiler = new ArrayList<>();
	    
	    // Dosyanın varlığını kontrol et
	    File file = new File(dosyaAdi);
	    if (!file.exists()) {
	        throw new FileNotFoundException("Dosya bulunamadı: " + file.getAbsolutePath());
	    }

	    // try-with-resources kullanarak otomatik kapatma
	    try (BufferedReader br = new BufferedReader(new FileReader(file))) {
	        String satir;
	        int satirNo = 0;
	        
	        while ((satir = br.readLine()) != null) {
	            satirNo++;
	            try {
	                // Satırı parçalara ayır ve kontrol et
	                String[] parcalar = satir.split("#");
	                if (parcalar.length < 2) {
	                    throw new IOException(satirNo + ". satırda geçersiz format: " + satir);
	                }
	                
	                // Boş değer kontrolü
	                if (parcalar[0].trim().isEmpty() || parcalar[1].trim().isEmpty()) {
	                    throw new IOException(satirNo + ". satırda boş değerler var: " + satir);
	                }
	                
	                kisiler.add(new Kisi(parcalar[0].trim(), parcalar[1].trim()));
	            } catch (Exception e) {
	                System.err.println("Hata: " + e.getMessage());
	                // İsterseniz burada hatalı satırı atlayabilir veya işlemi sonlandırabilirsiniz
	            }
	        }
	    }
	    
	    return kisiler;
	}

    public static HashMap<String, Gezegen> gezegenleriOku(String dosyaAdi) throws IOException {
        HashMap<String, Gezegen> gezegenler = new HashMap<>();
        BufferedReader br = new BufferedReader(new FileReader(dosyaAdi));
        String satir;
        while ((satir = br.readLine()) != null) {
            String[] parcalar = satir.split("#");
            gezegenler.put(parcalar[0], new Gezegen(parcalar[0], Integer.parseInt(parcalar[1]), parcalar[2]));
        }
        br.close();
        return gezegenler;
    }

    public static ArrayList<UzayAraci> araclariOku(String dosyaAdi) throws IOException {
        ArrayList<UzayAraci> araclar = new ArrayList<>();
        BufferedReader br = new BufferedReader(new FileReader(dosyaAdi));
        String satir;
        while ((satir = br.readLine()) != null) {
            String[] parcalar = satir.split("#");
            araclar.add(new UzayAraci(parcalar[0], parcalar[1], parcalar[2], Integer.parseInt(parcalar[4])));
        }
        br.close();
        return araclar;
    }

}