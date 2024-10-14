public abstract class Igrac {
    protected String ime;
    protected char znak;

    public Igrac(String ime, char znak) {
        this.ime = ime;
        this.znak = znak;
    }

    public String getIme() {
        return ime;
    }

    public char getZnak() {
        return znak;
    }

    public abstract void odigrajPotez(IksOksTabla tabla);
}

