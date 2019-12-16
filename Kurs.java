public class Kurs {
    private String nazwa;
    private String imieProwadzacego;
    private String nazwiskoProwadzacego;
    private int punktyECTS;

    Kurs(String nazwa, String imieProwadzacego, String nazwiskoProwadzacego, int punktyECTS) {
        this.nazwa = nazwa;
        this.imieProwadzacego = imieProwadzacego;
        this.nazwiskoProwadzacego = nazwiskoProwadzacego;
        this.punktyECTS = punktyECTS;
    }

    @Override
    public String toString() {
        return (nazwa + " " + imieProwadzacego + " " + nazwiskoProwadzacego + " ECTS: " + punktyECTS);
    }

    public String getNazwa() {
        return this.nazwa;
    }

    public String getImieProwadzacego() {
        return this.imieProwadzacego;
    }

    public String getNazwiskoProwadzacego() {
        return this.nazwiskoProwadzacego;
    }

    public int getPunktyECTS() {
        return this.punktyECTS;
    }

    public void setNazwa(String nazwa) {
        this.nazwa = nazwa;
    }

    public void setImieProwadzacego(String imieProwadzacego) {
        this.imieProwadzacego = imieProwadzacego;
    }

    public void setNazwiskoProwadzacego(String nazwiskoProwadzacego) {
        this.nazwiskoProwadzacego = nazwiskoProwadzacego;
    }

    public void setPunktyECTS(int punktyECTS) {
        this.punktyECTS = punktyECTS;
    }
}