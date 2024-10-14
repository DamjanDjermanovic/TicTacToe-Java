import java.util.Random;

public class KompjuterIgrac extends Igrac {

    public KompjuterIgrac() {
        super("Kompjuter", 'O');
    }

    @Override
    public void odigrajPotez(IksOksTabla tabla) {
        if (PronadjiPobednickiPotez(tabla)) {
            return;
        }

        if (PronadjiBlokadu(tabla)) {
            return;
        }

        SlucajanPotez(tabla);
    }

    private boolean PronadjiPobednickiPotez(IksOksTabla tabla) {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (tabla.getPolje(i, j) == ' ') {
                    tabla.potez(i, j, this.znak);
                    if (tabla.proveriPobedu()) {
                        return true;
                    } else {
                        tabla.prepraviPotez(i, j, ' ');
                    }
                }
            }
        }
        return false;
    }

    private boolean PronadjiBlokadu(IksOksTabla tabla) {
        char protivnik = (this.znak == 'X') ? 'O' : 'X';
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (tabla.getPolje(i, j) == ' ') {
                    tabla.potez(i, j, protivnik);
                    if (tabla.proveriPobedu()) {
                        tabla.prepraviPotez(i, j, this.znak);
                        return true;
                    } else {
                        tabla.prepraviPotez(i, j, ' ');
                    }
                }
            }
        }
        return false;
    }

    private void SlucajanPotez(IksOksTabla tabla) {
        Random rand = new Random();
        int red, kolona;
        do {
            red = rand.nextInt(3);
            kolona = rand.nextInt(3);
        } while (!tabla.potez(red, kolona, this.znak));
    }
}
