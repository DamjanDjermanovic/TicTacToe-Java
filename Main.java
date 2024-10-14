
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    private static Scanner scanner = new Scanner(System.in);
    private static KorisnikIgrac igrac = null;
    private static KompjuterIgrac kompjuter = new KompjuterIgrac();
    
    public static void main(String[] args) {
        while (true) {
            System.out.println("1. Ulogujte se ili napravite nalog");
            System.out.println("2. Izadjite iz programa");
            System.out.print("Izaberite opciju: ");
            
            int izbor = -1;
            try {
                izbor = scanner.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("Greska: Nevazeci unos. Molimo unesite celobrojnu vrednost.");
                scanner.next();
                continue;
            }
            scanner.nextLine();

            switch (izbor) {
                case 1:
                    prijavljivanje();
                    break;
                case 2:
                    System.out.println("Izlaz iz programa...");
                    return;
                default:
                    System.out.println("Nepoznata opcija. Molimo vas da izaberete ponovo.");
            }
        }
    }
    
    private static void prijavljivanje() {
        System.out.print("Unesite korisnicko ime: ");
        String ime = scanner.nextLine().trim();
        igrac = new KorisnikIgrac(ime);
        System.out.println("Dobro dosli " + ime);
        Runda runda = new Runda(igrac, kompjuter);
        
        while (true) {
            System.out.println("1. Igraj novu rundu");
            System.out.println("2. Promenite korisnika");
            System.out.println("3. Prikazi rezultate dosadasnjih korisnika");
            System.out.println("4. Izadjite iz programa");
            System.out.print("Izaberite opciju: ");
            
            int izbor = -1;
            try {
                izbor = scanner.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("Greska: Nevazeci unos. Molimo unesite celobrojnu vrednost.");
                scanner.next();
                continue;
            }
            scanner.nextLine();
            
            switch (izbor) {
                case 1:
                    runda.zapocni();
                    break;
                case 2:
                    return;
                case 3:
                    igrac.prikaziSveRezultate();
                    break;
                case 4:
                    System.out.println("Izlaz iz programa...");
                    System.exit(0);
                default:
                    System.out.println("Nepoznata opcija. Molimo vas da izaberete ponovo.");
            }
        }
    }
    
}
