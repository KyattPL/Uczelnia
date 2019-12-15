public abstract class Osoba{
    protected String imie;
    protected String nazwisko;
    protected String PESEL;
    protected int wiek;
    protected Plec plec;

    Osoba(String imie, String nazwisko, String PESEL, int wiek, Plec plec){
        this.imie = imie;
        this.nazwisko = nazwisko;
        this.PESEL = PESEL;
        this.wiek = wiek;
        this.plec = plec;
    }

    public enum Plec{
        M,
        K
    }

    public String getImie(){
        return this.imie;
    }    

    public String getNazwisko(){
        return this.nazwisko;
    }

    public String getPESEL(){
        return this.PESEL;
    }

    public int getWiek(){
        return this.wiek;
    }

    public Plec getPlec(){
        return this.plec;
    }

    public void setImie(String imie){
        this.imie = imie;
    }

    public void setNazwisko(String nazwisko){
        this.nazwisko = nazwisko;
    }

    public void setPESEL(String PESEL){
        this.PESEL = PESEL;
    }

    public void setWiek(int wiek){
        this.wiek = wiek;
    }

    public void setPlec(Plec plec){
        this.plec = plec;
    }
}