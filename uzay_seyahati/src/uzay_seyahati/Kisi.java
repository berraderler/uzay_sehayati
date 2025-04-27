package uzay_seyahati;

public class Kisi {
    private String isim;
    private int kalanOmur;
    private String uzayAraciAdi;
    private boolean hayatta;

    public Kisi(String isim, int yas, int kalanOmur, String uzayAraciAdi) {
        this.isim = isim;
        this.kalanOmur = kalanOmur;
        this.uzayAraciAdi = uzayAraciAdi;
        this.hayatta = kalanOmur > 0;
    }

    public String getIsim() {
        return isim;
    }

    public int getKalanOmur() {
        return kalanOmur;
    }

    public String getUzayAraciAdi() {
        return uzayAraciAdi;
    }

    public boolean isHayatta() {
        return hayatta;
    }

    public void saatGecir() {
        if (kalanOmur > 0) {
            kalanOmur--;
            if (kalanOmur == 0) {
                hayatta = false;
                System.out.println(isim + " öldü çünkü kalan ömrü 0.");
            }
        }
    }
}