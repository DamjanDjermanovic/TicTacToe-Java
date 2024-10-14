import java.util.Random;
import java.util.Scanner;

public class BacanjeNovcica {
    private Random rand;
    private Scanner scanner;

    public BacanjeNovcica() {
        this.rand = new Random();
        this.scanner = new Scanner(System.in);
    }

    public boolean baciNovicic() {
        while (true) {
            try {
                System.out.println("Izaberite (pismo/glava): ");
                String korisnikovIzbor = scanner.nextLine().trim().toLowerCase();

                if (!korisnikovIzbor.equals("pismo") && !korisnikovIzbor.equals("glava")) {
                    throw new NepoznatIzborException("Molimo vas da izaberete 'pismo' ili 'glava'.");
                }

                String rezultatNovcica = (rand.nextBoolean()) ? "pismo" : "glava";
                System.out.println("Novcic je pao na: " + rezultatNovcica);

                return korisnikovIzbor.equals(rezultatNovcica);
            } catch (NepoznatIzborException e) {
                System.out.println(e.getMessage());
            }
        }
    }
}
