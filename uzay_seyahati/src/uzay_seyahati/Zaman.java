package uzay_seyahati;

public class Zaman {
    private int gun;
    private int ay;
    private int yil;
    private int saat;
    private int gununKacSaatOldugu;

    public Zaman(int gun, int ay, int yil, int gununKacSaatOldugu) {
        this.gun = gun;
        this.ay = ay;
        this.yil = yil;
        this.saat = 0;
        this.gununKacSaatOldugu = gununKacSaatOldugu;
    }

    public void ilerleBirSaat() {
        saat++;
        if (saat >= gununKacSaatOldugu) {
            saat = 0;
            gun++;
            if (gun > 30) {
                gun = 1;
                ay++;
                if (ay > 12) {
                    ay = 1;
                    yil++;
                }
            }
        }
    }

    public String getTarih() {
        return gun + "." + ay + "." + yil;
    }

    public String getTarihSaat() {
        return String.format("%02d.%02d.%04d %02d:00", gun, ay, yil, saat);
    }

    public boolean esitMi(int gun, int ay, int yil) {
        return this.gun == gun && this.ay == ay && this.yil == yil;
    }

    public int getGun() {
        return gun;
    }

    public int getAy() {
        return ay;
    }

    public int getYil() {
        return yil;
    }

    public int getSaat() {
        return saat;
    }
}