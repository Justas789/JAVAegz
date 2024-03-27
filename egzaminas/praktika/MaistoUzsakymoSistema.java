import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class MaistoIstaiga {
    private String pavadinimas;
    private String kodas;
    private String adresas;

    public MaistoIstaiga(String pavadinimas, String kodas, String adresas) {
        this.pavadinimas = pavadinimas;
        this.kodas = kodas;
        this.adresas = adresas;
    }

    public String getPavadinimas() {
        return pavadinimas;
    }

    public String getKodas() {
        return kodas;
    }

    public String getAdresas() {
        return adresas;
    }

    public void setPavadinimas(String pavadinimas) {
        this.pavadinimas = pavadinimas;
    }

    public void setKodas(String kodas) {
        this.kodas = kodas;
    }

    public void setAdresas(String adresas) {
        this.adresas = adresas;
    }
}

class Valgiarastis {
    private String pavadinimas;
    private List<String> patiekalai;

    public Valgiarastis(String pavadinimas) {
        this.pavadinimas = pavadinimas;
        this.patiekalai = new ArrayList<>();
    }

    public String getPavadinimas() {
        return pavadinimas;
    }

    public List<String> getPatiekalai() {
        return patiekalai;
    }

    public void setPavadinimas(String pavadinimas) {
        this.pavadinimas = pavadinimas;
    }

    public void pridetiPatiekala(String patiekalas) {
        this.patiekalai.add(patiekalas);
    }

    public void pasalintiPatiekala(String patiekalas) {
        this.patiekalai.remove(patiekalas);
    }
}

class Vartotojas {
    private String vartotojoVardas;
    private String slaptazodis;

    public Vartotojas(String vartotojoVardas, String slaptazodis) {
        this.vartotojoVardas = vartotojoVardas;
        this.slaptazodis = slaptazodis;
    }

    public boolean prisijungtiKaipVartotojas(String ivestasVartotojoVardas, String ivestasSlaptazodis) {
        return vartotojoVardas.equals(ivestasVartotojoVardas) && slaptazodis.equals(ivestasSlaptazodis);
    }

    public boolean yraAdmin() {
        return false;
    }

    public static Vartotojas prisijungti(String vartotojoVardas, String slaptazodis) {
        return prisijungtiVartotoja(vartotojoVardas, slaptazodis);
    }

    public static Vartotojas prisijungtiVartotoja(String vartotojoVardas, String slaptazodis) {
        if ("admin".equals(vartotojoVardas) && "admin123".equals(slaptazodis)) {
            return new Administratorius(vartotojoVardas, slaptazodis);
        } else {
            return new Klientas(vartotojoVardas, slaptazodis);
        }
    }
}

class Klientas extends Vartotojas {
    public Klientas(String vartotojoVardas, String slaptazodis) {
        super(vartotojoVardas, slaptazodis);
    }

    public void ieskotiIstaigos() {
    }

    public void perziuretiValgiarasti(MaistoIstaiga maistoIstaiga) {
    }

    public void pasirinktiPatiekalus(Valgiarastis valgiarastis) {
    }

    public void atliktiUzsakyma() {
    }
}

class Administratorius extends Vartotojas {
    public Administratorius(String vartotojoVardas, String slaptazodis) {
        super(vartotojoVardas, slaptazodis);
    }

    public void pridetiIstaiga(List<MaistoIstaiga> maistoIstaigos, String pavadinimas, String kodas, String adresas) {
        MaistoIstaiga naujaIstaiga = new MaistoIstaiga(pavadinimas, kodas, adresas);
        maistoIstaigos.add(naujaIstaiga);
        System.out.println("Maitinimo įstaiga pridėta sėkmingai.");
    }

    public void pridetiValgiarasti(List<Valgiarastis> valgiarasciai, String pavadinimas) {
        Valgiarastis naujasValgiarastis = new Valgiarastis(pavadinimas);
        valgiarasciai.add(naujasValgiarastis);
        System.out.println("Valgiaraštis pridėtas sėkmingai.");
    }

    public void pridetiPatiekala(List<Valgiarastis> valgiarasciai, String valgiarascioPavadinimas, String patiekaloPavadinimas) {
        for (Valgiarastis valgiarastis : valgiarasciai) {
            if (valgiarastis.getPavadinimas().equals(valgiarascioPavadinimas)) {
                valgiarastis.pridetiPatiekala(patiekaloPavadinimas);
                System.out.println("Patiekalas pridėtas sėkmingai.");
                return;
            }
        }
        System.out.println("Nepavyko pridėti patiekalo: valgiaraščio pavadinimas nerastas.");
    }

    public void valdytiUzsakymus() {
    }
}

public class MaistoUzsakymoSistema {
    private List<MaistoIstaiga> maistoIstaigos;
    private List<Valgiarastis> valgiarasciai;
    private Vartotojas prisijungesVartotojas;

    public MaistoUzsakymoSistema() {
        this.maistoIstaigos = new ArrayList<>();
        this.valgiarasciai = new ArrayList<>();
    }

    public static void main(String[] args) {
        MaistoUzsakymoSistema sistema = new MaistoUzsakymoSistema();
        sistema.pradeti();
    }
    

    private void pradeti() {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\nPasirinkite veiksmą:");
            System.out.println("1. Prisijungungti");
            System.out.println("2. Registruotis");
            System.out.println("3. Baigti programą");
            int veiksmas = scanner.nextInt();

            switch (veiksmas) {
                case 1:
                    prisijungti(scanner);
                    break;
                case 2:
                    registruotis(scanner);
                    break;
                case 3:
                    System.out.println("Programa baigta.");
                    System.exit(0);
                default:
                    System.out.println("Neteisingas pasirinkimas. Bandykite dar kartą.");
            }
        }
    }
    
    private void prisijungti(Scanner scanner) {
        System.out.println("Įveskite vartotojo vardą:");
        String ivestasVartotojoVardas = scanner.next();
        System.out.println("Įveskite slaptažodį:");
        String ivestasSlaptazodis = scanner.next();
    
        prisijungesVartotojas = Vartotojas.prisijungti(ivestasVartotojoVardas, ivestasSlaptazodis);
    
        if (prisijungesVartotojas != null) {
            System.out.println("Prisijungta sėkmingai.");
            if (prisijungesVartotojas.yraAdmin()) {
                adminMeniu(scanner);
            } else {
                klientoMeniu(scanner);
            }
        } else {
            System.out.println("Neteisingas vartotojo vardas arba slaptažodis. Bandykite dar kartą.");
            pradeti();
        }
    }
    
    private void registruotis(Scanner scanner) {
        System.out.println("Registracija");
        System.out.println("Įveskite vartotojo vardą:");
        String ivestasVartotojoVardas = scanner.next();
        System.out.println("Įveskite slaptažodį:");
        String ivestasSlaptazodis = scanner.next();
    
        prisijungesVartotojas = new Vartotojas(ivestasVartotojoVardas, ivestasSlaptazodis);
    
        System.out.println("Registracija sėkminga.");
    
        if ("admin".equals(ivestasVartotojoVardas) && "admin123".equals(ivestasSlaptazodis)) {
            prisijungesVartotojas = new Administratorius(ivestasVartotojoVardas, ivestasSlaptazodis);
            adminMeniu(scanner); 
        } else {
            if (prisijungesVartotojas != null) {
                klientoMeniu(scanner); 
            } else {
                System.out.println("Registracija nepavyko. Bandykite dar kartą.");
            }
        }
    }
    
    private void klientoMeniu(Scanner scanner) {
        if (prisijungesVartotojas instanceof Klientas) {
            Klientas klientas = (Klientas) prisijungesVartotojas;
    
            while (true) {
                System.out.println("\nKliento Meniu:");
                System.out.println("1. Ieškoti maitinimo įstaigos");
                System.out.println("2. Peržiūrėti valgiaraštį");
                System.out.println("3. Pasirinkti patiekalus");
                System.out.println("4. Atlikti užsakymą");
                System.out.println("5. Atsijungti");
    
                int veiksmas = scanner.nextInt();
    
                switch (veiksmas) {
                    case 1:
                        klientas.ieskotiIstaigos();
                        break;
                    case 2:
                        System.out.println("Pasirinkite maitinimo įstaigą, kurios valgiaraštį norite peržiūrėti:");
                        MaistoIstaiga pasirinktaIstaiga = pasirinktiIstaiga(scanner);
                        klientas.perziuretiValgiarasti(pasirinktaIstaiga);
                        break;
                    case 3:
                        System.out.println("Pasirinkite patiekalus iš valgiaraščio:");
                        Valgiarastis pasirinktasValgiarastis = pasirinktiValgiarasti(scanner);
                        klientas.pasirinktiPatiekalus(pasirinktasValgiarastis);
                        break;
                    case 4:
                        klientas.atliktiUzsakyma();
                        break;
                    case 5:
                        System.out.println("Atsijungta sėkmingai.");
                        prisijungesVartotojas = null;
                        pradeti();
                        break;
                    default:
                        System.out.println("Neteisingas pasirinkimas. Bandykite dar kartą.");
                }
            }
        }
    }
    
    private MaistoIstaiga pasirinktiIstaiga(Scanner scanner) {
        return null;
    }
    
    private Valgiarastis pasirinktiValgiarasti(Scanner scanner) {
        return null;
    }
    
    private void adminMeniu(Scanner scanner) {
        Administratorius administratorius = (Administratorius) prisijungesVartotojas;
    
        while (true) {
            System.out.println("\nAdministratoriaus Meniu:");
            System.out.println("1. Pridėti maitinimo istaiga");
            System.out.println("2. Pridėti valgiarasti");
            System.out.println("3. Pridėti patiekalą");
            System.out.println("4. Valdyti užsakymus");
            System.out.println("5. Atsijungti");
    
            int veiksmas = scanner.nextInt();
    
            switch (veiksmas) {
                case 1:
                    System.out.println("Įveskite maitinimo įstaigos pavadinimą:");
                    String istaigosPavadinimas = scanner.next();
                    System.out.println("Įveskite maitinimo įstaigos kodą:");
                    String istaigosKodas = scanner.next();
                    System.out.println("Įveskite maitinimo įstaigos adresą:");
                    String istaigosAdresas = scanner.next();
                    administratorius.pridetiIstaiga(maistoIstaigos, istaigosPavadinimas, istaigosKodas, istaigosAdresas);
                    break;
                case 2:
                    System.out.println("Įveskite valgiaraščio pavadinimą:");
                    String valgiarascioPavadinimas  = scanner.next();
                    administratorius.pridetiValgiarasti(valgiarasciai, valgiarascioPavadinimas);
                    break;
                case 3:
                    System.out.println("Įveskite valgiaraščio pavadinimą, į kurį norite pridėti patiekalą:");
                    String pavadinimas = scanner.next();
                    System.out.println("Įveskite patiekalo pavadinimą:");
                    String patiekaloPavadinimas = scanner.next();
                    administratorius.pridetiPatiekala(valgiarasciai, pavadinimas, patiekaloPavadinimas);
                    break;
                    case 4:
                    administratorius.valdytiUzsakymus();
                    break;
                    case 5:
                    System.out.println("Atsijungta sėkmingai.");
                    prisijungesVartotojas = null;
                    pradeti();
                    break;
                    default:
                    System.out.println("Neteisingas pasirinkimas. Bandykite dar karta.");
                    }
                    }
                    }
                    }
                    
                    
    
            
            
