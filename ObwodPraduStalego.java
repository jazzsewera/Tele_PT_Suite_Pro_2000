//<editor-fold defaultstate="collapsed" desc="Copyright">
/*
 * Copyright (C) 2016 Błażej Sewera (Jazz)
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
//</editor-fold>
package obwody;

import java.util.ArrayList;
import obwody.elementy.*;

/**
 *
 * @author jazz
 */
public class ObwodPraduStalego {
    
    // Pola:
    private ArrayList<Rezystor> rezystory;
    private int liczbaRezystorow;
    private ArrayList<Kondensator> kondensatory;
    private int liczbaKondensatorow;
    // private ArrayList<Cewka> cewki;
    // private int ileCewek;
    private double napiecie;
    private double prad;
    private double ladunek;
    private boolean czyZrodloPradu;
    private double rezystancjaZastepcza;
    private double pojemnoscZastepcza;
    
    // Koniec pol;
    
    // Inicjalizacja zmiennych:
    {
        liczbaRezystorow = 0;
        liczbaKondensatorow = 0;
        // ileCewek = 0;
        rezystory = new ArrayList<>();
        kondensatory = new ArrayList<>();
        // cewki = new ArrayList<>();
    }
    // Koniec inicjalizacji;
    
    // Konstruktory:
    public ObwodPraduStalego() {
        czyZrodloPradu = false;
    }
    
    public ObwodPraduStalego(double napLubPrad, boolean czyZrodloPradu) {
        
        if(czyZrodloPradu) {
            this.prad = napLubPrad;
        }
        
        else {
            this.napiecie = napLubPrad;
        }
        
        this.czyZrodloPradu = czyZrodloPradu;
    }
    
    public ObwodPraduStalego(double napiecie) {
        
        this.napiecie = napiecie;
        czyZrodloPradu = false;
    }
    // Koniec konstruktorow;
    
    // Akcesory:
    public double getNapiecie() {
        return napiecie;
    }
    
    public double getPrad() {
        return prad;
    }
    
    public double getLadunek() {
        return ladunek;
    }
    
    public double getRezystancjaZastepcza() {
        return rezystancjaZastepcza;
    }
    
    public double getPojemnoscZastepcza() {
        return pojemnoscZastepcza;
    }
    
    public boolean getCzyZrodloPradu() {
        return czyZrodloPradu;
    }
    
    public int getLiczbaRezystorow() {
        return liczbaRezystorow;
    }
    
    public int getLiczbaKondensatorow() {
        return liczbaKondensatorow;
    }
    // Koniec akcesorow;
    
    // Mutatory:
    public void setNapiecie(double u) {
        napiecie = u;
        czyZrodloPradu = false;
        if(rezystancjaZastepcza == 0) {
            System.out.println("Zwarcie");
            prad = 9999;
        }
        else
            prad = napiecie / rezystancjaZastepcza;
        ladunek = napiecie * pojemnoscZastepcza;
    }
    
    public void setPrad(double i) {
        prad = i;
        czyZrodloPradu = true;
        napiecie = prad * rezystancjaZastepcza;
        ladunek = napiecie * pojemnoscZastepcza;
    }
    // Koniec mutatorow;
    
    // Metody:
    public void dodajRezystorSzeregowo(Rezystor r) {
        rezystory.add(r);
        liczbaRezystorow++;
        rezystancjaZastepcza += r.getRezystancja();
        System.out.println("Dodano rezystor szeregowo. Rezystancja tego rezystora: " + r.getRezystancja() + ";\nIdentyfikator rezystora: R" + r.getIdentyfikator());
    }
    
    public void dodajRezystorRownolegle(Rezystor r) {
        rezystory.add(r);
        liczbaRezystorow++;
        rezystancjaZastepcza = ( rezystancjaZastepcza * r.getRezystancja() ) / ( rezystancjaZastepcza + r.getRezystancja() );
        System.out.println("Dodano rezystor rownolegle. Rezystancja tego rezystora: " + r.getRezystancja() + ";\nIdentyfikator rezystora: R" + r.getIdentyfikator());
    }
    
    public void dodajKondensatorSzeregowo(Kondensator c) {
        kondensatory.add(c);
        liczbaKondensatorow++;
        pojemnoscZastepcza = ( pojemnoscZastepcza * c.getPojemnosc() ) / ( pojemnoscZastepcza + c.getPojemnosc() );
        System.out.println("Dodano kondensator szeregowo. Pojemnosc tego kondensatora: " + c.getPojemnosc() + ";\nIdentyfikator kondensatora: C" + c.getIdentyfikator());
    }
    
    public void dodajKondensatorRownolegle(Kondensator c) {
        kondensatory.add(c);
        liczbaKondensatorow++;
        pojemnoscZastepcza += c.getPojemnosc();
        System.out.println("Dodano kondensator rownolegle. Pojemnosc tego kondensatora: " + c.getPojemnosc() + ";\nIdentyfikator kondensatora: C" + c.getIdentyfikator());
    }
    
    public String wyswietlRezystory() {
        String s = "Liczba rezystorow: ";
        
        s = s.concat( String.valueOf(rezystory.size()) ).concat(".\n");
        
        for(Rezystor r : rezystory)
            s = s.concat("R").concat( String.valueOf(r.getIdentyfikator()) ).concat(": ").concat( String.valueOf(r.getRezystancja()) ).concat(" Ohm\n");
        
        return s;
    }
    
    // Koniec metod;
}
