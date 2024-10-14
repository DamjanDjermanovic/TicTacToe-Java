import java.util.InputMismatchException;
import java.util.Scanner;
import java.io.*;

public class KorisnikIgrac extends Igrac {

    private Scanner scanner;
    private int pobede;
    private int porazi;

    public KorisnikIgrac(String ime) {
        super(ime, 'X');
        this.scanner = new Scanner(System.in);
        this.pobede = 0;
        this.porazi = 0;
        citaj();
    }

    @Override
    public void odigrajPotez(IksOksTabla tabla) {
        int red = -1, kolona = -1;

        while (true) {
            try {
                System.out.print("Unesite red (0, 1, 2): ");
                red = scanner.nextInt();
                System.out.print("Unesite kolonu (0, 1, 2): ");
                kolona = scanner.nextInt();

                if (red < 0 || red >= 3 || kolona < 0 || kolona >= 3) {
                    throw new NevazeciPotezException("Nevazeci unos. Unesite red i kolonu izmedju 0 i 2.");
                }

                if (!tabla.potez(red, kolona, znak)) {
                    throw new NevazeciPotezException("Pozicija je vec zauzeta. Pokusajte ponovo.");
                }

                break;

            } catch (InputMismatchException e) {
                System.out.println("Greska: Nevazeci unos. Molimo unesite celobrojne vrednosti.");
                scanner.next();
            } catch (NevazeciPotezException e) {
                System.out.println(e.getMessage());
            }
        }
    }
    
    public int getPobede() {
        return pobede;
    }

    public void dodajPobedu() {
        this.pobede++;
        upisi();
    }

    public int getPorazi() {
        return porazi;
    }

    public void povecajPoraze() {
        this.porazi++;
        upisi();
    }

    private void upisi() {
        try {
            File file = new File("korisnici.txt");
            if (!file.exists()) {
                file.createNewFile();
            }

            File tempFile = new File("korisnici_temp.txt");

            try (BufferedReader reader = new BufferedReader(new FileReader(file)); PrintWriter writer = new PrintWriter(new FileWriter(tempFile))){
                String line;
                boolean found = false;
                while ((line = reader.readLine()) != null) {
                    String[] parts = line.split(",");
                    if (parts[0].equals(getIme())) {
                        writer.println(getIme() + "," + pobede + "," + porazi);
                        found = true;
                    } else {
                        writer.println(line);
                    }
                }

                if (!found) {
                    writer.println(getIme() + "," + pobede + "," + porazi);
                }
            }

            if (!file.delete()) {
                System.out.println("Ne mogu obrisati originalni fajl.");
                return;
            }

            if (!tempFile.renameTo(file)) {
                System.out.println("Ne mogu preimenovati temp fajl.");
            }

        } catch (IOException e) {
            System.out.println(e);
        }
    }

    private void citaj() {
        File file = new File("korisnici.txt");
        if (!file.exists()) {
            return;
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts[0].equals(getIme())) {
                    this.pobede = Integer.parseInt(parts[1]);
                    this.porazi = Integer.parseInt(parts[2]);
                    return;
                }
            }
        } catch (IOException e) {
            System.out.println(e);
        }
    }
    
    public void prikaziSveRezultate() {
        File file = new File("korisnici.txt");
        if (!file.exists()) {
            System.out.println("Nema dostupnih rezultata. Datoteka ne postoji.");
            return;
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;

            System.out.printf("%-20s%-10s%-10s%n", "Korisnicko Ime", "Pobede", "Porazi");
            System.out.println("------------------  --------  --------");

            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 3) {
                    String ime = parts[0];
                    int pobede = Integer.parseInt(parts[1]);
                    int porazi = Integer.parseInt(parts[2]);

                    System.out.printf("%-20s%-10d%-10d%n", ime, pobede, porazi);
                }
            }
        } catch (IOException e) {
            System.out.println("Greska pri citanju rezultata: " + e.getMessage());
        }
        System.out.println("------------------  --------  --------");
    }

}
