package uzay_seyahati;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Gezegen {
    private String isim;
    private int nufus;
    private LocalDate tarih;
    private int gununKacSaatOldugu;
    private int saat; // Saat sayacı

    public Gezegen(String isim, int gununKacSaatOldugu, String baslangicTarihi) {
        this.isim = isim;
        this.gununKacSaatOldugu = gununKacSaatOldugu;
        this.tarih = LocalDate.parse(baslangicTarihi, DateTimeFormatter.ofPattern("d.M.yyyy"));
        this.saat = 0; // Başlangıç saati
    }

    // Saat ilerleme fonksiyonu
    public void saatIlerle() {
        saat++;  // Saati bir arttır
        if (saat >= gununKacSaatOldugu) { // Eğer gün sonuna geldiysen
            saat = 0;  // Saati sıfırla
            tarih = tarih.plusDays(1);  // Tarihi bir gün arttır
        }
    }

    // Nüfus artışı
    public void nufusArtir(int artis) {
        nufus += artis;
    }

    // Nüfus azalışı
    public void nufusAzalt(int azalis) {
        nufus -= azalis;
    }

    // Getter'lar
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
}
