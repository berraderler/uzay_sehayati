package uzay_seyahati;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Gezegen {
    private String isim;
    private int nufus;
    private LocalDate tarih;
    private int gununKacSaatOldugu;
    private int saat;

    public Gezegen(String isim, int gununKacSaatOldugu, String baslangicTarihi) {
        this.isim = isim;
        this.gununKacSaatOldugu = gununKacSaatOldugu;
        this.tarih = LocalDate.parse(baslangicTarihi, DateTimeFormatter.ofPattern("d.M.yyyy"));
        this.saat = 0;
        this.nufus = 0;
    }

    public void saatIlerle() {
        saat++;
        if (saat >= gununKacSaatOldugu) {
            saat = 0;
            tarih = tarih.plusDays(1);
        }
    }

    public void nufusArtir(int artis) {
        nufus += artis;
    }

    public void nufusAzalt(int azalis) {
        nufus -= azalis;
        if (nufus < 0) {
            nufus = 0; 
        }
    }

    public String getIsim() {
        return isim;
    }

    public int getNufus() {
        return nufus;
    }

    public String getTarih() {
        return tarih.format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
    }

    public int getSaat() {
        return saat;
    }

    public int getGununKacSaatOldugu() {
        return gununKacSaatOldugu;
    }

    public LocalDate getLocalDate() {
        return tarih;
    }

    public static String tahminiVarisTarihi(Gezegen cikisGezegen, int kalanSaat, Gezegen varisGezegeni) {
        int gunlukSaatCikis = cikisGezegen.getGununKacSaatOldugu();
        
        int kacGun = kalanSaat / gunlukSaatCikis;
        if (kalanSaat % gunlukSaatCikis != 0) {
            kacGun++;
        }

        LocalDate tahminiTarih = cikisGezegen.getLocalDate().plusDays(kacGun);
        
        return tahminiTarih.format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
    }


}