package uzay_seyahati;

import java.util.ArrayList;

public class UzayAraci {
    private String adi;
    private String cikis;
    private String varis;
    private int kalanSaat;
    private boolean vardi;
    private boolean imha;
    private ArrayList<Kisi> yolcular;

    public UzayAraci(String adi, String cikis, String varis, int yolculukSuresi) {
        this.adi = adi;
        this.cikis = cikis;
        this.varis = varis;
        this.kalanSaat = yolculukSuresi;
        this.vardi = false;
        this.imha = false;
        this.yolcular = new ArrayList<>();
    }

    public void kisiEkle(Kisi k) {
        yolcular.add(k);
    }

    public void saatGecir(int saat) {
        if (imha || vardi) return;

        kalanSaat--;

        if (kalanSaat <= 0) {
            vardi = true;
        }
    }

    public String getAdi() {
        return adi;
    }

    public String getCikis() {
        return cikis;
    }

    public String getVaris() {
        return varis;
    }

    public int getKalanSaat() {
        return kalanSaat;
    }

    public boolean isVardi() {
        return vardi;
    }

    public boolean isImha() {
        return imha;
    }

    public String getDurum() {
        if (imha) return "İmha";
        else if (vardi) return "Vardı";
        else return "Yolda";
    }
}