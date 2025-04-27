package uzay_seyahati;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class UzayAraci {
    private String adi;
    private String cikis;
    private String varis;
    private int kalanSaat;
    private int orijinalMesafeSaat; 
    private boolean imha;
    private boolean vardi;
    private boolean hareketEtti = false;
    private ArrayList<Kisi> yolcular;
    private Gezegen cikisGezegeni;
    private Gezegen varisGezegeni;
    private LocalDate cikisTarihi;
    private LocalDate hedefeVarisTarihi;

    public UzayAraci(String adi, String cikis, String varis, int mesafeSaat, HashMap<String, Gezegen> gezegenler) {
        this.adi = adi;
        this.cikis = cikis;
        this.varis = varis;
        this.kalanSaat = mesafeSaat;
        this.orijinalMesafeSaat = mesafeSaat;
        this.imha = false;
        this.vardi = false;
        this.yolcular = new ArrayList<>();

        this.cikisGezegeni = gezegenler.get(cikis);
        this.varisGezegeni = gezegenler.get(varis);
    }

    public void saatGecir(int saat) {
        if (!imha && !vardi) {
            kalanSaat -= saat;
            if (kalanSaat <= 0) {
                kalanSaat = 0;
                vardi = true;

                int canliYolcuSayisi = 0;
                for (Kisi yolcu : yolcular) {
                    if (yolcu.isHayatta()) {
                        canliYolcuSayisi++;
                    }
                }
                varisGezegeni.nufusArtir(canliYolcuSayisi);
            }

            if (yolcular.isEmpty()) {
                imha = true;
            }
        }
    }

    public void kisiEkle(Kisi kisi) {
        yolcular.add(kisi);
    }

    public ArrayList<Kisi> getYolcular() {
        return yolcular;
    }

    public String getAdi() {
        return adi;
    }

    public String getDurum() {
        if (!hareketEtti) return "BEKLIYOR";
        if (imha) return "IMHA";
        else if (vardi) return "VARIS";
        else return "YOLDA";
    }

    public String getCikis() {
        return cikis;
    }

    public boolean isHareketEtti() {
        return hareketEtti;
    }

    public void setHareketEtti(boolean hareketEtti) {
        this.hareketEtti = hareketEtti;
        if (hareketEtti && hedefeVarisTarihi == null) {
            hesaplaHedefeVarisTarihi();
        }
    }

    public String getVaris() {
        return varis;
    }

    public int getKalanSaat() {
        return kalanSaat;
    }

    public boolean isImha() {
        return imha;
    }

    public boolean isVardi() {
        return vardi;
    }

    public LocalDate getCikisTarihi() {
        return cikisTarihi;
    }

    public void setDurum(String durum) {
        if (durum.equals("IMHA")) {
            this.imha = true;
        } else if (durum.equals("VARIS")) {
            this.vardi = true;
        } else {
            this.imha = false;
            this.vardi = false;
        }
    }

    public void setCikisTarihi(String cikisTarihiStr) {
        this.cikisTarihi = LocalDate.parse(cikisTarihiStr, DateTimeFormatter.ofPattern("d.M.yyyy"));
        // Çıkış tarihi verildiğinde hedefeVarisTarihi hesaplanmayacak!
        // Çünkü hareket etmeden hesaplamak istemiyoruz
    }

    private void hesaplaHedefeVarisTarihi() {
        if (cikisTarihi != null && varisGezegeni != null) {
            int toplamSaat = orijinalMesafeSaat;
            int gunlukSaat = varisGezegeni.getGununKacSaatOldugu();
            int kacGun = toplamSaat / gunlukSaat;
            if (toplamSaat % gunlukSaat != 0) kacGun++;
            this.hedefeVarisTarihi = cikisTarihi.plusDays(kacGun);
        }
    }

    public String getHedefeVarisTarihi() {
        if (imha) return "--";
        if (hedefeVarisTarihi == null) return "Hesaplanıyor";
        return hedefeVarisTarihi.format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
    }

    public String tahminiVarisTarihi() {
        return Gezegen.tahminiVarisTarihi(cikisGezegeni, kalanSaat, varisGezegeni);
    }
}
