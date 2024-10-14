public class Runda {

    private IksOksTabla tabla;
    private KorisnikIgrac igrac1;
    private KompjuterIgrac igrac2;
    private Igrac trenutniIgrac;
    private BacanjeNovcica bacanjeNovcica;

    public Runda(KorisnikIgrac igrac1, KompjuterIgrac igrac2) {
        this.tabla = new IksOksTabla();
        this.igrac1 = igrac1;
        this.igrac2 = igrac2;
        this.trenutniIgrac = igrac1;
        this.bacanjeNovcica = new BacanjeNovcica();
    }

    public void zapocni() {
        if (bacanjeNovcica.baciNovicic()) {
            this.trenutniIgrac = igrac1;
            System.out.println(igrac1.getIme() + " igra prvi!");
        } else {
            this.trenutniIgrac = igrac2;
            System.out.println(igrac2.getIme() + " igra prvi!");
        }
        
        while (true) {
            tabla.prikaziTablu();

            trenutniIgrac.odigrajPotez(tabla);
            
            System.out.println("Potez: " + trenutniIgrac.getIme());
            
            if (tabla.proveriPobedu()) {
                tabla.prikaziTablu();
                System.out.println("Pobednik je: " + trenutniIgrac.getIme());
                
                if (trenutniIgrac == igrac1) {
                    igrac1.dodajPobedu();
                } else {
                    igrac1.povecajPoraze();
                }
                
                prikaziRezultat();
                break;
            }

            if (tabla.daLiJeTablaPopunjena()) {
                tabla.prikaziTablu();
                System.out.println("Igra je zavrsena - Nereseno!");
                break;
            }

            trenutniIgrac = (trenutniIgrac == igrac1) ? igrac2 : igrac1;
        }
        
        tabla.resetujTablu();
    }

    private void prikaziRezultat() {
        System.out.println("Rezultati:");
        System.out.println(igrac1.getIme() + " - Pobede: " + igrac1.getPobede() + ", Porazi: " + igrac1.getPorazi());
    }
}
