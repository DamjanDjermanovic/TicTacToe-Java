public class IksOksTabla implements TablaInterfejs {
    private char[][] tabla;

    public IksOksTabla() {
        this.tabla = new char[3][3];
        resetujTablu();
    }
    
    public char getPolje(int red, int kolona)
    {
        return tabla[red][kolona];
    }
    
    @Override
    public void resetujTablu()
    {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                tabla[i][j] = ' ';
            }
        }
    }
    
    @Override
    public boolean potez(int red, int kolona, char znak) {
        if (tabla[red][kolona] == ' ') {
            tabla[red][kolona] = znak;
            return true;
        }
        return false;
    }
    
    public void prepraviPotez(int red, int kolona, char znak) {
        tabla[red][kolona] = znak;
    }
    
    
    @Override
    public void prikaziTablu() {
        System.out.println(" " + tabla[0][0] + " | " + tabla[0][1] + " | " + tabla[0][2] + " ");
        System.out.println("---+---+---");
        System.out.println(" " + tabla[1][0] + " | " + tabla[1][1] + " | " + tabla[1][2] + " ");
        System.out.println("---+---+---");
        System.out.println(" " + tabla[2][0] + " | " + tabla[2][1] + " | " + tabla[2][2] + " ");
    }
    
    @Override
    public boolean proveriPobedu() {
        for (int i = 0; i < 3; i++) {
            if (tabla[i][0] != ' ' && tabla[i][0] == tabla[i][1] && tabla[i][1] == tabla[i][2]) {
                return true;
            }
        }

        for (int i = 0; i < 3; i++) {
            if (tabla[0][i] != ' ' && tabla[0][i] == tabla[1][i] && tabla[1][i] == tabla[2][i]) {
                return true;
            }
        }

        if (tabla[0][0] != ' ' && tabla[0][0] == tabla[1][1] && tabla[1][1] == tabla[2][2]) {
            return true;
        }
        
        if (tabla[0][2] != ' ' && tabla[0][2] == tabla[1][1] && tabla[1][1] == tabla[2][0]) {
            return true;
        }

        return false;
    }
    
    @Override
    public boolean daLiJeTablaPopunjena() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (tabla[i][j] == ' ') {
                    return false;
                }
            }
        }
        return true;
    }
}
