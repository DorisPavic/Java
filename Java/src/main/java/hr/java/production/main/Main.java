package hr.java.production.main;

import hr.java.production.exception.DuplicateCategoryException;
import hr.java.production.exception.DuplicateItemException;
import hr.java.production.exception.InvalidNameException;
import hr.java.production.model.*;
import hr.java.production.sort.ProductionSorter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import java.math.BigDecimal;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;


/**
 * Služi za učitavanje osnovnih podataka u aplikaciju te njihovo korištenje
 */


public class Main {

    private static Logger log = LoggerFactory.getLogger(Main.class);

    private static final Integer brojKategorijaArtikala = 3;
    private static final Integer brojArtikala = getBrojArtikala();
    private static final Integer brojTvornica = 2;
    private static final Integer brojTrgovina = 2;

    /**
     * Pokreće program
     *
     * @param args argumenti komandne linije
     */

    public static void main(String[] args) {

        log.info("PROGRAM POKRENUT!");

        Scanner skener = new Scanner(System.in);

        Category[] kategorije = new Category[brojKategorijaArtikala];

        System.out.println("\nUnesite " + brojKategorijaArtikala + " kategorije artikala.");
        Boolean ispravanUnosKategorije;
        for (int i = 0; i < brojKategorijaArtikala; i++) {
            do {
                try {
                    ispravanUnosKategorije = true;
                    kategorije[i] = unosKategorija(skener, i, kategorije);
                } catch (DuplicateCategoryException exDC) {
                    System.out.println("\nUnijeli ste kategoriju koja već postoji! Molimo unesite novu kategoriju.");
                    ispravanUnosKategorije = false;
                    log.error("Došlo je do ponovnog unosa iste kategorije.", exDC);

                } catch (InvalidNameException exName1) {
                    System.out.println("Ime kategorije je obavezno!!!");
                    ispravanUnosKategorije = false;
                    log.error("Pogreška prilikom unosa imena kategorije.", exName1);
                }
            } while (!ispravanUnosKategorije);
        }

        log.info("ZAVRŠEN UNOS KATEGORIJA!");


        List<Item> artikli = new ArrayList<>();

        System.out.println("\nUnesite " + brojArtikala + " artikala.");

        for (int i = 0; i < brojArtikala; i++) {
            artikli.add(unosArtikala(skener, i, kategorije));
        }
        log.info("ZAVRŠEN UNOS ARTIKALA!");


        log.info("ISPIS SVIH ARTIKALA!");

        System.out.println("\n Ispis svih artikala prema cijeni s popustom (od najmanje do najveće): ");
        artikli.sort((a1, a2) -> a1.getCijenaSPopustom().compareTo(a2.getCijenaSPopustom()));

        for (Item artikl : artikli) {
            System.out.println("\n Naziv artikla: " + artikl.getName().toUpperCase());
            System.out.println("Kategorija artikla: " + artikl.getCategory().getName());
            if (artikl instanceof Zitarice zitarice) {
                System.out.println("Broj kalorija i cijena po kg: " + zitarice.getKonstantaBrojKalorijaZitarice() + "kcal, " + artikl.getSellingPrice() + "kn");
                System.out.println("Težina proizvoda: " + zitarice.getWeight() + "kg");
                System.out.println("Broj kalorija i cijena po pakiranju: " + zitarice.calculateKilocalories() + "kcal, " + zitarice.calculatePrice() + "kn");
            } else if (artikl instanceof Brasno brasno) {
                System.out.println("Broj kalorija i cijena po kg: " + brasno.getKonstantaBrojKalorijaBrasna() + "kcal, : " + artikl.getSellingPrice() + "kn");
                System.out.println("Težina proizvoda: " + brasno.getWeight() + "kg");
                System.out.println("Broj kalorija i cijena po pakiranju: " + brasno.calculateKilocalories() + "kcal, " + brasno.calculatePrice() + "kn");
            } else if (artikl instanceof Laptop laptop) {
                System.out.println("Prodajna cijena laptopa: " + artikl.getSellingPrice() + "kn");
                System.out.println("Garantni rok: " + laptop.getTrajanjeGarantnogRoka() + "mjeseci");
            } else {
                System.out.println("Prodajna cijena artikla: " + artikl.getSellingPrice() + "kn");
            }
            System.out.println("Cijena s popustom od " + artikl.getDiscount().discountAmount() + "% iznosi " + artikl.getCijenaSPopustom() + "kn");
        }


        System.out.println("\n Ispis svih artikala prema cijeni s popustom (od najveće do najmanje): ");
        artikli.sort((a1, a2) -> a2.getCijenaSPopustom().compareTo(a1.getCijenaSPopustom()));
        for (Item artikl : artikli) {
            System.out.println("\n Naziv artikla: " + artikl.getName().toUpperCase());
            System.out.println("Kategorija artikla: " + artikl.getCategory().getName());
            if (artikl instanceof Zitarice zitarice) {
                System.out.println("Broj kalorija i cijena po kg: " + zitarice.getKonstantaBrojKalorijaZitarice() + "kcal, " + artikl.getSellingPrice() + "kn");
                System.out.println("Težina proizvoda: " + zitarice.getWeight() + "kg");
                System.out.println("Broj kalorija i cijena po pakiranju: " + zitarice.calculateKilocalories() + "kcal, " + zitarice.calculatePrice() + "kn");
            } else if (artikl instanceof Brasno brasno) {
                System.out.println("Broj kalorija i cijena po kg: " + brasno.getKonstantaBrojKalorijaBrasna() + "kcal, : " + artikl.getSellingPrice() + "kn");
                System.out.println("Težina proizvoda: " + brasno.getWeight() + "kg");
                System.out.println("Broj kalorija i cijena po pakiranju: " + brasno.calculateKilocalories() + "kcal, " + brasno.calculatePrice() + "kn");
            } else if (artikl instanceof Laptop laptop) {
                System.out.println("Prodajna cijena laptopa: " + artikl.getSellingPrice() + "kn");
                System.out.println("Garantni rok: " + laptop.getTrajanjeGarantnogRoka() + "mjeseci");
            } else {
                System.out.println("Prodajna cijena artikla: " + artikl.getSellingPrice() + "kn");
            }
            System.out.println("Cijena s popustom od " + artikl.getDiscount().discountAmount() + "% iznosi " + artikl.getCijenaSPopustom() + "kn");
        }


        Factory[] tvornice = new Factory[brojTvornica];

        System.out.println("\nBroj tvornica za unos: " + brojTvornica + ".");

        for (int i = 0; i < brojTvornica; i++) {
            tvornice[i] = unosTvornica(skener, i, artikli);
        }

        log.info("ZAVRŠEN UNOS TVORNICA!");


        System.out.println("Ispis artikala po tvornicama: \n");
        for (int i = 0; i < brojTvornica; i++) {
            System.out.println("Artikli tvornice " + tvornice[i].getName().toUpperCase() + ": ");
            Set<Item> artikliTv = tvornice[i].getItems();
            for (Item artikl : artikliTv) {
                System.out.println(artikl.getName());
            }
        }


        Store[] trgovine = new Store[brojTrgovina];

        System.out.println("\nBroj trgovina za unos: " + brojTrgovina + ".");

        for (int i = 0; i < brojTrgovina; i++) {
            trgovine[i] = unosTrgovina(skener, i, artikli);
        }

        log.info("ZAVRŠEN UNOS TRGOVINA!");


        log.info("POČETAK ISPISA PODATAKA!");

        Map<Category, List<Item>> categoryItemMap = new HashMap<>();

        for (Item artikl : artikli) {
            if (!categoryItemMap.containsKey(artikl.getCategory())) {
                categoryItemMap.put(artikl.getCategory(), new ArrayList<>());
            }
            categoryItemMap.get(artikl.getCategory()).add(artikl);
        }

        System.out.println("__________________________________________________");
        System.out.println("Ispis mape:");
        System.out.println("__________________________________________________");

        for (Map.Entry<Category, List<Item>> ispis : categoryItemMap.entrySet()) {
            System.out.println("\nKategorija " + ispis.getKey().getName().toUpperCase() + " - popis artikala: ");
            List<Item> artikliKategorije = ispis.getValue();
            artikliKategorije.sort(new ProductionSorter());
            for (Item artikl : artikliKategorije) {
                System.out.println(" - " + artikl.getName().toUpperCase() + ", " + artikl.getCijenaSPopustom() + "kn.");
            }
            Item najjeftinijiArtiklKategorije = Collections.min(artikliKategorije);
            System.out.println("Najjeftiniji artikl kategorije " + ispis.getKey().getName().toUpperCase() + " je " + najjeftinijiArtiklKategorije.getName().toUpperCase() + ", cijena: " + najjeftinijiArtiklKategorije.getCijenaSPopustom() + "kn.");
            Item najjskupljiArtiklKategorije = Collections.max(artikliKategorije);
            System.out.println("Najskuplji artikl kategorije " + ispis.getKey().getName().toUpperCase() + " je " + najjskupljiArtiklKategorije.getName().toUpperCase() + ", cijena: " + najjskupljiArtiklKategorije.getCijenaSPopustom() + "kn.");
        }


        List<Item> jestiviProizvodiLista = new ArrayList<>();
        List<Item> laptopLista = new ArrayList<>();

        System.out.println("\n Ispis najskupljeg i najjeftinijeg jestivog proizvoda i laptopa: ");
        for (Item artikl : artikli) {
            if (artikl instanceof Zitarice zitarice || artikl instanceof Brasno brasno) {
                jestiviProizvodiLista.add(artikl);
            } else if (artikl instanceof Laptop laptop) {
                laptopLista.add(artikl);
            }
        }

        System.out.println("Najjeftiniji prehrambeni proizvod je " + Collections.min(jestiviProizvodiLista).getName().toUpperCase() + "(" + Collections.min(jestiviProizvodiLista).getCijenaSPopustom() + "kn).");
        System.out.println("Najskuplji prehrambeni proizvod je " + Collections.max(jestiviProizvodiLista).getName().toUpperCase() + "(" + Collections.max(jestiviProizvodiLista).getCijenaSPopustom() + "kn).");
        System.out.println("Najjeftiniji laptop je " + Collections.min(laptopLista).getName().toUpperCase() + "(" + Collections.min(laptopLista).getCijenaSPopustom() + "kn).");
        System.out.println("Najskuplji laptop je " + Collections.max(laptopLista).getName().toUpperCase() + "(" + Collections.max(laptopLista).getCijenaSPopustom() + "kn).");


        int maxBrojKalorija = 0;
        Item najkaloricnijiProizvod = null;
        Item najskupljaNamirnica = null;
        BigDecimal maxCijenaProizvoda = BigDecimal.valueOf(0);
        BigDecimal kilaza = BigDecimal.valueOf(0);
        Integer brJestivihArtikala = 0;

        for (Item artikl : artikli) {
            if (artikl instanceof Zitarice || artikl instanceof Brasno) {
                brJestivihArtikala++;
                int trenutneKalorije = ((Edible) artikl).calculateKilocalories();
                if (((Edible) artikl).calculateKilocalories() > maxBrojKalorija) {
                    maxBrojKalorija = trenutneKalorije;
                    najkaloricnijiProizvod = artikl;

                }
                BigDecimal trenutnaCijena = ((Edible) artikl).calculatePrice();
                if (((Edible) artikl).calculatePrice().compareTo(maxCijenaProizvoda) > 0) {
                    maxCijenaProizvoda = trenutnaCijena;
                    najskupljaNamirnica = artikl;
                    if (artikl instanceof Zitarice zitarice) {
                        kilaza = zitarice.getWeight();
                    } else if (artikl instanceof Brasno brasno) {
                        kilaza = brasno.getWeight();
                    }

                }
            }
        }

        if (brJestivihArtikala > 0) {
            System.out.println("\nNamirnica s najvećim brojem kalorija (" + maxBrojKalorija + "kcal) je: " + najkaloricnijiProizvod.getName().toUpperCase() + ".");
            System.out.println("Najskuplja namirnica s obzirom na težinu (" + maxCijenaProizvoda + "kn za " + kilaza + " kg) ima " + najskupljaNamirnica.getName().toUpperCase() + ".");
        }


        Item laptopSNajmanjimGRokom = null;
        int minGarantniRok = Integer.MAX_VALUE;
        Integer brLaptopa = 0;
        for (Item artikl : artikli) {
            if (artikl instanceof Laptop) {
                brLaptopa++;
                int trenutniGarantniRok = ((Tehnical) artikl).calculateGaranciju();
                if (((Tehnical) artikl).calculateGaranciju() < minGarantniRok) {
                    minGarantniRok = trenutniGarantniRok;
                    laptopSNajmanjimGRokom = artikl;

                }
            }
        }
        if (brLaptopa > 0) {
            System.out.println("\nLaptop s najmanjim garantnim rokom (" + minGarantniRok + "mjeseci) je " + laptopSNajmanjimGRokom.getName() + ", a njegova cijena s popustom od " + laptopSNajmanjimGRokom.getDiscount().discountAmount() + "% iznosi " + laptopSNajmanjimGRokom.getCijenaSPopustom() + "kn.");
        }


        Item najjeftinijiArtikl = artikli.get(0);
        Store trgovinaSNajjeftinijimArtiklom = trgovine[0];
        BigDecimal najmanjaCijena = artikli.get(0).getSellingPrice();

        for (int i = 0; i < brojTrgovina; i++) {
            Store trgovina = trgovine[i];
            Item[] artikliTrgovine = new Item[trgovina.getItems().size()];
            trgovina.getItems().toArray(artikliTrgovine);
            for (int j = 0; j < artikliTrgovine.length; j++) {
                if (artikliTrgovine[j].getSellingPrice().compareTo(najmanjaCijena) < 0) {
                    najjeftinijiArtikl = artikliTrgovine[j];
                    trgovinaSNajjeftinijimArtiklom = trgovina;
                    najmanjaCijena = artikliTrgovine[j].getSellingPrice();
                }
            }

        }
        System.out.println(" Trgovina s najjeftinijim artiklom je " + trgovinaSNajjeftinijimArtiklom.getName() +
                " koja prodaje " + najjeftinijiArtikl.getName() + " po cijeni: " + najjeftinijiArtikl.getSellingPrice() + " kn.");

        BigDecimal najveciVolumen = ((artikli.get(0).getHeight()).multiply(artikli.get(0).getLength())).multiply(artikli.get(0).getWidth());
        Factory tvornicaSArtiklomNajvecegVolumena = tvornice[0];
        Item najveciArtikl = artikli.get(0);

        for (int i = 0; i < tvornice.length; i++) {
            Factory tvornica = tvornice[i];
            Item[] artikl = new Item[tvornica.getItems().size()];
            tvornice[i].getItems().toArray(artikl);
            for (int j = 0; j < artikl.length; j++) {
                BigDecimal volumenKutije = ((artikl[j].getHeight()).multiply(artikl[j].getLength())).multiply(artikl[j].getWidth());
                if (volumenKutije.compareTo(najveciVolumen) > 0) {
                    najveciVolumen = volumenKutije;
                    tvornicaSArtiklomNajvecegVolumena = tvornica;
                    najveciArtikl = artikl[j];
                }
            }
        }

        System.out.println("\nTvornica s proizvodom koji ima najveći volumen je " + tvornicaSArtiklomNajvecegVolumena.getName() +
                ". Volumen artikla- " + najveciArtikl.getName() + " iznosi " + najveciVolumen + "cm2");


        log.info("KRAJ PROGRAMA!");

        System.out.println("Wuuuhu kraj programa!");
    }


    /**
     * Služi za odabir broja artikala koji će se unijeti
     *
     * @return broj artikala za unos
     */

    private static Integer getBrojArtikala() {
        Integer brojArtikalaZaUnos = 0;
        Boolean ispravanUnosBrArtikala;
        do {
            ispravanUnosBrArtikala = true;
            try {
                System.out.print("\nUnesite broj artikala koje želite unijeti: ");
                Scanner skener = new Scanner(System.in);
                brojArtikalaZaUnos = skener.nextInt();

            } catch (InputMismatchException ex1) {
                System.out.println("\nPogriješili ste u unosu broja artikala! Molimo pokušajte ponovo.");
                ispravanUnosBrArtikala = false;
                log.error("Došlo je do pogreške u unosu broja artikala.", ex1);
            }
        } while (!ispravanUnosBrArtikala);

        return brojArtikalaZaUnos;
    }


    /**
     * Služi za unos kategorija
     *
     * @param skener     služi za skeniranje unešenog teksta
     * @param i          predstavlja redni broj pojedine kategorije (od 0 do odabranog broja kategorije)
     * @param kategorije predstavlja polje unesenih kategorija
     * @return vraća ispravno unesenu kategoriju
     * @throws DuplicateCategoryException predstavlja pogrešku koja se javlja u slučaju ponovnog upisa iste kategorije
     */

    private static Category unosKategorija(Scanner skener, int i, Category[] kategorije) throws DuplicateCategoryException {
        boolean duplicate;
        boolean ispravanNaziv;
        Category trenutnaKategorija;
        String imeKategorije = "";

        do {
            ispravanNaziv = true;
            System.out.print("\n Unesite ime " + (i + 1) + ". kategorije: ");
            imeKategorije = skener.nextLine();

            if (imeKategorije.isEmpty() || imeKategorije.isBlank()) {
                ispravanNaziv = false;
                throw new InvalidNameException("\nIme kategorije je prazno!");
            }

            duplicate = false;
            trenutnaKategorija = new Category(imeKategorije, null);
            for (Category kategorija : kategorije) {
                if (trenutnaKategorija.equals(kategorija)) {
                    duplicate = true;
                    System.out.println("\nIme " + imeKategorije.toUpperCase() + " već postoji!");
                    throw new DuplicateCategoryException("Unesena kategorija koja već postoji!!!");
                }
            }
        } while (duplicate || !ispravanNaziv);

        System.out.print("Unesite opis " + (i + 1) + ". kategorije: ");
        String opisKategorije = skener.nextLine();

        trenutnaKategorija.setDescription(opisKategorije);


        return trenutnaKategorija;
    }

    /**
     * Služi za unos artikala
     *
     * @param skener     služi za skeniranje unesenog teksta
     * @param i          predstavlja redni broj pojedinog artikla (od 0 do ukupnog broja artikla za unos)
     * @param kategorije predstavlja polje unesenih kategorija
     * @return vraća ispravno unesen artikl
     */


    private static Item unosArtikala(Scanner skener, int i, Category[] kategorije) {

        int odabirPodvrsteArtikla = 0;
        int odabirVrsteArtikla = 0;
        int brojVrsteArtikla = 3;
        int brojPodvrstaArtikla = 2;

        Boolean ispravanUnosBrVrsteArtikala;

        do {
            skener = new Scanner(System.in);
            try {
                do {
                    ispravanUnosBrVrsteArtikala = true;
                    System.out.println("\nOdaberite vrstu " + (i + 1) + ". artikla kojeg želite unijeti:");

                    System.out.println("1. Jestivi proizvodi");
                    System.out.println("2. Laptop");
                    System.out.println("3. Ostali artikli");

                    System.out.print("Odabir >>> ");
                    odabirVrsteArtikla = skener.nextInt();
                    skener.nextLine();
                    if (odabirVrsteArtikla < 0 || odabirVrsteArtikla > brojVrsteArtikla) {
                        System.out.println("\nUnijeli ste pogrešan redni broj! Ne postoji opcija pod tim brojem. Molimo pokušajte ponovo!");
                        ispravanUnosBrVrsteArtikala = false;
                    }
                } while (odabirVrsteArtikla < 0 || odabirVrsteArtikla > brojVrsteArtikla);

            } catch (InputMismatchException ex2) {
                System.out.println("\nPogriješili ste u odabiru vrste artikala! Molimo unesite BROJ koji se odnosi na vrstu atikla!");
                ispravanUnosBrVrsteArtikala = false;
                log.error("Pogrešno odabran artikl.", ex2);
            }

        } while (!ispravanUnosBrVrsteArtikala);


        List<Item> zitarice = new ArrayList<>();
        List<Item> brasno = new ArrayList<>();
        List<Item> laptop = new ArrayList<>();
        Item noviArtikl;

        BigDecimal weight = BigDecimal.valueOf(0);
        int garantniRok = 0;


        Boolean ispravanUnosBrPodvrsteArtikala;

        if (odabirVrsteArtikla == 1) {
            do {
                ispravanUnosBrPodvrsteArtikala = true;
                skener = new Scanner(System.in);
                try {
                    do {
                        System.out.println("\nOdaberite podvrstu artikla kojeg želite unijeti:");
                        System.out.println("1. Žitarice");
                        System.out.println("2. Brašno");
                        System.out.print("Odabir >>> ");
                        odabirPodvrsteArtikla = skener.nextInt();
                        skener.nextLine();
                        if (odabirPodvrsteArtikla == 1) {
                            System.out.println("Odabrali ste unos žitarica.");

                        } else if (odabirPodvrsteArtikla == 2) {
                            System.out.println("Odabrali ste unos brašna.");
                        }
                        if (odabirPodvrsteArtikla < 0 || odabirPodvrsteArtikla > brojPodvrstaArtikla) {
                            System.out.println("\nUnijeli ste pogrešan redni broj! Ne postoji opcija pod tim brojem. Molimo pokušajte ponovo!");
                        }
                    } while (odabirPodvrsteArtikla < 0 || odabirPodvrsteArtikla > brojPodvrstaArtikla);

                } catch (InputMismatchException ex3) {
                    System.out.println("\nPogriješili ste u odabiru podvrste artikala! Molimo unesite BROJ koji se odnosi na podvrstu atikla!");
                    ispravanUnosBrPodvrsteArtikala = false;
                    log.error("Pogrešan odabir podvrste artikala.", ex3);
                }
            } while (!ispravanUnosBrPodvrsteArtikala);

        } else if (odabirVrsteArtikla == 2) {
            System.out.println("Odabrali ste unos laptopa.");
        } else {
            System.out.println("Odabrali ste unos ostalih artikala.");
        }

        System.out.print("\n Unesite ime " + (i + 1) + ". artikla: ");
        String imeArtikla = skener.nextLine();

        Matcher matcher;
        String sifraArtikla = "";

        do {
            System.out.print("\n Unesite šifru " + (i + 1) + ". artikla: ");

            sifraArtikla = skener.nextLine();
            Pattern pattern = Pattern.compile("^[a-zA-Z0-9]{5}");
            matcher = pattern.matcher(sifraArtikla);
            if (!matcher.matches()) {
                System.out.println("Krivo ste unijeli šifru artikla! Šifra treba imati 5 znakova!");
            }
        } while (!matcher.matches());

        Integer redniBrojKategorije = 0;

        Boolean ispravanUnosKategorijeArtikala;
        do {
            ispravanUnosKategorijeArtikala = true;
            skener = new Scanner(System.in);
            try {
                do {
                    System.out.println("\nOdaberite kategoriju " + (i + 1) + ". artikla: ");
                    for (int j = 0; j < brojKategorijaArtikala; j++) {
                        System.out.println((j + 1) + ". " + kategorije[j].getName() + " - " + kategorije[j].getDescription());
                    }
                    System.out.print("Unesite redni broj kategorije >>> ");
                    redniBrojKategorije = skener.nextInt();
                    skener.nextLine();

                    if (redniBrojKategorije < 1 || redniBrojKategorije > brojKategorijaArtikala) {
                        System.out.println("\nOdabrali ste pogrešan redni broj kategorije. Odabrani broj mora između 1 i " + brojKategorijaArtikala + ". \nMolimo pokušajte ponovo.");
                    }
                } while (redniBrojKategorije < 1 || redniBrojKategorije > brojKategorijaArtikala);

            } catch (InputMismatchException ex4) {
                System.out.println("\nPogriješili ste u odabiru kategorije artikala! Molimo unesite BROJ koji se odnosi na kategoriju atikla!");
                ispravanUnosKategorijeArtikala = false;
                log.error("Pogrešno odabrana kategorija artikla.", ex4);
            }
        } while (!ispravanUnosKategorijeArtikala);

        Category odabranaKategorijaArtikla = kategorije[redniBrojKategorije - 1];

        Boolean ispravanUnosPodatakaAtributa;
        BigDecimal sirinaArtikla = BigDecimal.valueOf(0), visinaArtikla = BigDecimal.valueOf(0), duzinaArtikla = BigDecimal.valueOf(0), cijenaProizvodnjeArtikla = BigDecimal.valueOf(0), cijenaProdajeArtikla = BigDecimal.valueOf(0), popust = BigDecimal.valueOf(0);

        do {
            ispravanUnosPodatakaAtributa = true;
            skener = new Scanner(System.in);
            try {
                System.out.print("\nUnesite širinu artikla (u cm)- " + imeArtikla + ": ");
                sirinaArtikla = skener.nextBigDecimal();

                System.out.print("Unesite visinu artikla (u cm)- " + imeArtikla + ": ");
                visinaArtikla = skener.nextBigDecimal();

                System.out.print("Unesite dužinu artikla (u cm)- " + imeArtikla + ": ");
                duzinaArtikla = skener.nextBigDecimal();

                System.out.print("Unesite cijenu proizvodnje artikla (u HRK)- " + imeArtikla + ": ");
                cijenaProizvodnjeArtikla = skener.nextBigDecimal();

                System.out.print("Unesite prodajnu cijenu artikla (u HRK)- " + imeArtikla + ": ");
                cijenaProdajeArtikla = skener.nextBigDecimal();
                skener.nextLine();

                System.out.print("Unesite popust artikla (izražen u %)- " + imeArtikla + ": ");
                popust = skener.nextBigDecimal();
                skener.nextLine();

            } catch (InputMismatchException ex5) {
                System.out.println("\nPogriješili ste u unosu podataka artikla! Potrebno je unijeti brojčane vrijednosti (za decimalne brojeve koristite zarez!). \nMolimo pokušajte ponovo.");
                ispravanUnosPodatakaAtributa = false;
                log.error("Pogrešno uneseni podaci artikla.", ex5);
            }
        } while (!ispravanUnosPodatakaAtributa);


        BigDecimal cijenaSPopustom = BigDecimal.valueOf(0);
        BigDecimal izracunPopusta;

        if (odabirPodvrsteArtikla == 1) {

            Boolean ispravanUnosTezineZitarice;
            do {
                ispravanUnosTezineZitarice = true;
                skener = new Scanner(System.in);
                try {
                    System.out.print("Unesite težinu proizvoda (u kg!): ");
                    weight = skener.nextBigDecimal();
                    skener.nextLine();
                } catch (InputMismatchException ex6) {
                    System.out.println("\nPogriješili ste u unosu težine artikla! Potrebno je unijeti brojčanu vrijednost (za decimalne brojeve koristite zarez!). \nMolimo pokušajte ponovo.");
                    ispravanUnosTezineZitarice = false;
                    log.error("Pogrešno unesena težina žitarice.", ex6);
                }
            } while (!ispravanUnosTezineZitarice);

            noviArtikl = new Zitarice(imeArtikla, odabranaKategorijaArtikla, sirinaArtikla, visinaArtikla,
                    duzinaArtikla, cijenaProizvodnjeArtikla, cijenaProdajeArtikla, weight, popust, cijenaSPopustom, sifraArtikla);
            zitarice.add(noviArtikl);


        } else if (odabirPodvrsteArtikla == 2) {

            Boolean ispravanUnosTezineBrasna;
            do {
                ispravanUnosTezineBrasna = true;
                skener = new Scanner(System.in);
                try {
                    System.out.print("Unesite težinu proizvoda (u kg!): ");
                    weight = skener.nextBigDecimal();
                    skener.nextLine();
                } catch (InputMismatchException ex6) {
                    System.out.println("\nPogriješili ste u unosu težine artikla! Potrebno je unijeti brojčanu vrijednost (za decimalne brojeve koristite zarez!). \nMolimo pokušajte ponovo.");
                    ispravanUnosTezineBrasna = false;
                    log.error("Pogrešno unesena težina brašna.", ex6);
                }
            } while (!ispravanUnosTezineBrasna);


            noviArtikl = new Brasno(imeArtikla, odabranaKategorijaArtikla, sirinaArtikla, visinaArtikla, duzinaArtikla,
                    cijenaProizvodnjeArtikla, cijenaProdajeArtikla, weight, popust, cijenaSPopustom, sifraArtikla);
            brasno.add(noviArtikl);


        } else if (odabirVrsteArtikla == 2) {

            Boolean ispravanUnosGarancijeLaptopa;
            do {
                ispravanUnosGarancijeLaptopa = true;
                skener = new Scanner(System.in);
                try {
                    System.out.print("Unesite trajanje garantnog roka izražen u mjesecima: ");
                    garantniRok = skener.nextInt();
                    skener.nextLine();
                } catch (InputMismatchException ex7) {
                    System.out.println("\nPogriješili ste u unosu garantnog roka! Potrebno je unijeti cijelobrojnu brojčanu vrijednost. \nMolimo pokušajte ponovo.");
                    ispravanUnosGarancijeLaptopa = false;
                    log.error("Pogrešno unesen garantni rok laptopa.", ex7);
                }
            } while (!ispravanUnosGarancijeLaptopa);

            noviArtikl = new Laptop(imeArtikla, odabranaKategorijaArtikla, sirinaArtikla, visinaArtikla, duzinaArtikla,
                    cijenaProizvodnjeArtikla, cijenaProdajeArtikla, popust, cijenaSPopustom, garantniRok, sifraArtikla);
            laptop.add(noviArtikl);


        } else {
            noviArtikl = new Item(imeArtikla, odabranaKategorijaArtikla, sirinaArtikla, visinaArtikla, duzinaArtikla,
                    cijenaProizvodnjeArtikla, cijenaProdajeArtikla, popust, cijenaSPopustom, sifraArtikla);

        }


        if (noviArtikl instanceof Zitarice || noviArtikl instanceof Brasno) {
            izracunPopusta = weight.multiply(noviArtikl.getSellingPrice()).multiply(popust.divide(BigDecimal.valueOf(100)));
            noviArtikl.setCijenaSPopustom(weight.multiply(noviArtikl.getSellingPrice()).subtract(izracunPopusta));
        } else {
            izracunPopusta = noviArtikl.getSellingPrice().multiply(popust.divide(BigDecimal.valueOf(100)));
            noviArtikl.setCijenaSPopustom(noviArtikl.getSellingPrice().subtract(izracunPopusta));
        }


        return noviArtikl;
    }


    /**
     * Služi za unos tvornica
     *
     * @param skener  služi za skeniranje unesenog teksta
     * @param i       predstavlja redni broj pojedine tvornice (od 0 do ukupnog broja tvornica za unos)
     * @param artikli predstavlja polje unesenih artikala
     * @return vraća ispravno unesenu tvornicu
     */

    private static Factory unosTvornica(Scanner skener, int i, List<Item> artikli) {
        System.out.print("\n Unesite ime " + (i + 1) + ". tvornice: ");
        String imeTvornice = skener.nextLine();

        System.out.println("Unesite ulicu, kućni broj, grad i poštanski broj tvornice - " + imeTvornice + ":");

        System.out.print("Unesite ulicu: ");
        String ulica = skener.nextLine();
        System.out.print("Unesite kućni broj: ");
        String kucniBroj = skener.nextLine();
        System.out.println("Odaberite grad: ");

        for (int g = 0; g < City.values().length; g++) {
            System.out.println((g + 1) + ". " + City.values()[g].getPostalCode() + " " + City.values()[g].getCityName());
        }
        Integer odabraniRedniBrojGrada = 0;
        Boolean ispravanRedniBrojGradaTvornice = true;

        do {
            ispravanRedniBrojGradaTvornice = true;
            try {
                do {
                    System.out.print("Odabir >>> ");
                    odabraniRedniBrojGrada = skener.nextInt();
                    skener.nextLine();
                    if (odabraniRedniBrojGrada < 0 || odabraniRedniBrojGrada > City.values().length) {
                        System.out.println("\nUpisali ste pogrešan redni broj odabranog grada. Molimo pokušajte ponovo!");
                    }
                } while (odabraniRedniBrojGrada <= 0 || odabraniRedniBrojGrada > City.values().length);
            } catch (InputMismatchException exG1) {
                System.out.println("\nPogriješili ste u unosu broja artikla! Potrebno je unijeti cijelobrojnu vrijednost. \nMolimo pokušajte ponovo.");
                ispravanRedniBrojGradaTvornice = false;
                log.error("Pogrešno unesen redni broj grada tvornice.", exG1);
            }
        } while (!ispravanRedniBrojGradaTvornice);

        City gradTvornice = City.values()[odabraniRedniBrojGrada - 1];

        Address adresaTvornice = new Address.Builder(gradTvornice)
                .street(ulica)
                .houseNumber(kucniBroj)
                .build();

        Integer brojArtikalaTvornice = null;
        Boolean ispravanBrArtikalaTvornice;

        do {
            ispravanBrArtikalaTvornice = true;
            skener = new Scanner(System.in);
            try {
                do {
                    System.out.print("\nUnesite ukupan broj artikala koji se proizvodi u tvornici: ");
                    brojArtikalaTvornice = skener.nextInt();
                    skener.nextLine();

                    if (brojArtikalaTvornice < 0 || brojArtikalaTvornice > brojArtikala) {
                        System.out.println("\nUpisali ste pogrešan broj artikla. Broj artikala mora biti između 0 i " + brojArtikala + "!");
                    }
                } while (brojArtikalaTvornice < 0 || brojArtikalaTvornice > brojArtikala);
            } catch (InputMismatchException ex8) {
                System.out.println("\nPogriješili ste u unosu broja artikla! Potrebno je unijeti cijelobrojnu brojčanu vrijednost. \nMolimo pokušajte ponovo.");
                ispravanBrArtikalaTvornice = false;
                log.error("Pogrešno unesen broj artikala koji se proizvodi u tvornici.", ex8);
            }
        } while (!ispravanBrArtikalaTvornice);

        Integer odabraniBrojArtiklaTvornice;
        Set<Item> artikliTvornice = new HashSet<>();

        Boolean duplicate;
        Item uneseniArtiklTvornice = null;

        Boolean ispravanRedniBrArtikalaTvornice;

        if (brojArtikalaTvornice > 0) {

            for (int k = 0; k < brojArtikalaTvornice; k++) {

                do {
                    ispravanRedniBrArtikalaTvornice = true;
                    skener = new Scanner(System.in);
                    duplicate = false;
                    try {
                        do {
                            System.out.println("Odaberite redni broj  " + (k + 1) + ". artikla sa popisa koji se proizvodi u tvornici:");
                            for (int j = 0; j < brojArtikala; j++) {
                                System.out.println((j + 1) + " . " + artikli.get(j).getName());
                            }
                            System.out.print("Odabir >>> ");
                            odabraniBrojArtiklaTvornice = skener.nextInt();
                            skener.nextLine();
                            if (odabraniBrojArtiklaTvornice < 0 || odabraniBrojArtiklaTvornice > brojArtikala) {
                                System.out.println("\nOdabrali ste pogrešan redni broj artikla. Broj mora biti između 0 i " + brojArtikala + "!");
                            } else {
                                uneseniArtiklTvornice = artikli.get(odabraniBrojArtiklaTvornice - 1);
                                for (Item artikl : artikliTvornice) {
                                    if (uneseniArtiklTvornice.equals(artikl) && brojArtikalaTvornice > 1) {
                                        duplicate = true;
                                        throw new DuplicateItemException();
                                    }
                                }
                            }
                        } while (odabraniBrojArtiklaTvornice < 0 || odabraniBrojArtiklaTvornice > brojArtikala || duplicate);

                        artikliTvornice.add(uneseniArtiklTvornice);

                    } catch (InputMismatchException ex9) {
                        System.out.println("\nPogriješili ste u unosu broja artikla! Potrebno je unijeti cijelobrojnu brojčanu vrijednost. \nMolimo pokušajte ponovo.");
                        ispravanRedniBrArtikalaTvornice = false;
                        log.error("Pogreška u unosu broja artikala tvornice.", ex9);
                    } catch (DuplicateItemException exDp1) {
                        System.out.println("\nPonovo ste odabrali ste isti artikl. Molimo izaberite ponovo!");
                        log.error("Ponovo odabran isti artikl tvornice", exDp1);
                    }
                } while (!ispravanRedniBrArtikalaTvornice || duplicate);
            }
        }

        return new Factory(imeTvornice, adresaTvornice, artikliTvornice, gradTvornice);
    }


    /**
     * Služi za unos trgovina
     *
     * @param skener  služi za skeniranje unesenog teksta
     * @param i       predstavlja redni broj pojedine trgovine (od 0 do ukupnog broja trgovina za unos)
     * @param artikli predstavlja polje unesenih artikala
     * @return vraća ispravno unesenu trgovinu
     */

    private static Store unosTrgovina(Scanner skener, int i, List<Item> artikli) {
        System.out.print(" \n Unesite ime " + (i + 1) + ". trgovine: ");
        String imeTrgovine = skener.nextLine();

        String webAdresaTrgovine = "";
        Boolean ispravnaWebAdresa;

        do {
            ispravnaWebAdresa = true;
            try {
                System.out.print("Unesite web adresu " + (i + 1) + ". trgovine: ");
                webAdresaTrgovine = skener.nextLine();
                URL url = new URL(webAdresaTrgovine);
               /*
                URLConnection urlConnection= url.openConnection();
                urlConnection.connect();
                */
            } catch (MalformedURLException exURL1) {
                System.out.println("\nWeb adresa unesena u krivom formatu! Unesite u slijedećem formatu : http://google.com.");
                ispravnaWebAdresa = false;
                log.error("Pogrešno unesen format web adresa trgovine.", exURL1);
            }
            /*
            catch (IOException exURL2) {
                System.out.println("\nNije moguće povezivanje na web stranicu! Molimo pokušajte ponovo!");
                ispravnaWebAdresa = false;
                }
            */

        } while (!ispravnaWebAdresa);

        Integer brojArtikalaTrgovine = 0;
        Boolean ispravanBrArtikalaTrgovine;

        do {
            ispravanBrArtikalaTrgovine = true;
            skener = new Scanner(System.in);
            try {
                do {
                    System.out.print("Unesite ukupan broj artikala sa popisa koji se prodaje u trgovini: ");
                    brojArtikalaTrgovine = skener.nextInt();
                    skener.nextLine();

                    if (brojArtikalaTrgovine < 0 || brojArtikalaTrgovine > brojArtikala) {
                        System.out.println("\nUpisali ste pogrešan broj artikla. Broj mora biti između 0 i " + brojArtikala + "!");
                    }
                } while (brojArtikalaTrgovine < 0 || brojArtikalaTrgovine > brojArtikala);

            } catch (InputMismatchException ex10) {
                System.out.println("\nPogriješili ste u unosu broja artikla! Potrebno je unijeti cijelobrojnu brojčanu vrijednost. \nMolimo pokušajte ponovo.");
                ispravanBrArtikalaTrgovine = false;
                log.error("Pogrešno unesen broj artikala koji se prodaje u trgovini.", ex10);
            }

        } while (!ispravanBrArtikalaTrgovine);


        Integer odabraniBrojArtiklaTrgovine;
        Set<Item> artikliTrgovine = new HashSet<>();
        Item uneseniArtiklTrgovine = null;
        Boolean duplicate;
        Boolean ispravanRedniBrArtikalaTrgovine;

        if (brojArtikalaTrgovine > 0) {
            for (int k = 0; k < brojArtikalaTrgovine; k++) {
                do {
                    ispravanRedniBrArtikalaTrgovine = true;
                    skener = new Scanner(System.in);
                    duplicate = false;
                    try {
                        do {
                            System.out.println("Odaberite redni broj " + (k + 1) + ". artikla sa popisa koji se prodaje u trgovini " + imeTrgovine + ":");
                            for (int j = 0; j < brojArtikala; j++) {
                                System.out.println((j + 1) + " . " + artikli.get(j).getName());
                            }
                            System.out.print("Odabir >>> ");
                            odabraniBrojArtiklaTrgovine = skener.nextInt();
                            skener.nextLine();
                            if (odabraniBrojArtiklaTrgovine < 0 || odabraniBrojArtiklaTrgovine > brojArtikala) {
                                System.out.println("\nOdabrali ste pogrešan redni broj artikla. Broj mora biti između 0 i " + brojArtikala + "!");
                            } else {
                                uneseniArtiklTrgovine = artikli.get(odabraniBrojArtiklaTrgovine - 1);
                                for (Item artikl : artikliTrgovine) {
                                    if (uneseniArtiklTrgovine.equals(artikl) && brojArtikalaTrgovine > 1) {
                                        duplicate = true;
                                        throw new DuplicateItemException("Odabran isti artikl!!!");
                                    }
                                }
                            }
                        } while (odabraniBrojArtiklaTrgovine < 0 || odabraniBrojArtiklaTrgovine > brojArtikala || duplicate);

                        artikliTrgovine.add(uneseniArtiklTrgovine);

                    } catch (InputMismatchException ex10) {
                        System.out.println("\nPogriješili ste u unosu broja artikla! Potrebno je unijeti cijelobrojnu brojčanu vrijednost. \nMolimo pokušajte ponovo.");
                        ispravanRedniBrArtikalaTrgovine = false;
                        log.error("Pogrešan odabir artikla koji se prodaje u trgovini.", ex10);
                    } catch (DuplicateItemException exDp2) {
                        System.out.println("\nPonovo ste odabrali ste isti artikl. Molimo izaberite ponovo!");
                        log.error("Ponovo izabran isti artikl trgovine.", exDp2);
                    }
                } while (!ispravanRedniBrArtikalaTrgovine || duplicate);
            }
        }
        return new Store(imeTrgovine, webAdresaTrgovine, artikliTrgovine);
    }
}
