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

/**
 *
 * @author Ganja & jazz
 * @version 0.01
 */
package obwody.elementy;

import static java.lang.Math.PI;

public class Cewka {
    
    //<editor-fold defaultstate="collapsed" desc="Pola">
    private int identyfikator;
    private static int id0;
    private double dlugoscCewki; // [m]
    private double poleRamki; // S [m^2]
    private double przenikalnoscMagnetyczna; // mi
    private double przenikalnoscWzglednaMagnetyczna; // mir
    private double indukcyjnoscWlasnaCewki; // L [H]
    
    public static final double MI0 = PI * 4e-7; // [H/m] Stała magnetyczna
    
    private int liczbaZwojow; // z
    //</editor-fold>
    
    // Inicjalizacja:
    static {
        id0 = 0;
    }
    {
        identyfikator = id0;
        id0++;
    }
    // Koniec inicjalizacji;
    
    //<editor-fold defaultstate="collapsed" desc="Konstruktory">
    public Cewka(double indukcyjnoscWlasnaCewki, int id) {
        this.indukcyjnoscWlasnaCewki = indukcyjnoscWlasnaCewki;
        identyfikator = id;
    }
    
    public Cewka(double indukcyjnoscWlasnaCewki) {
        this.indukcyjnoscWlasnaCewki = indukcyjnoscWlasnaCewki;
    }
    
    public Cewka(double dlugoscCewki, int liczbaZwojow, double poleRamki, double przenikalnoscWzglednaMagnetyczna) {
        this.dlugoscCewki = dlugoscCewki;
        this.liczbaZwojow = liczbaZwojow;
        this.poleRamki = poleRamki;
        this.przenikalnoscWzglednaMagnetyczna = przenikalnoscWzglednaMagnetyczna;
        
        this.przenikalnoscMagnetyczna = this.MI0 * przenikalnoscWzglednaMagnetyczna;
        this.indukcyjnoscWlasnaCewki = ( (liczbaZwojow*liczbaZwojow) *  this.przenikalnoscMagnetyczna * poleRamki) / dlugoscCewki;
    }
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Akcesory">
    public double getDlugoscCewki() {
        return dlugoscCewki;
    }
    
    public double getPoleRamki() {
        return poleRamki;
    }
    
    public double getPrzenikalnoscMagnetyczna() {
        return przenikalnoscMagnetyczna;
    }
    
    public double getPrzenikalnoscWzglednaMagnetyczna() {
        return przenikalnoscWzglednaMagnetyczna;
    }
    
    public double getIndukcyjnoscWlasnaCewki() {
        return indukcyjnoscWlasnaCewki;
    }

    public int getLiczbaZwojow() {
        return liczbaZwojow;
    }
    
    public int getIdentyfikator() {
        return identyfikator;
    }
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Mutatory">
    public void setIndukcyjnosc(double indukcyjnoscWlasnaCewki) {
        this.indukcyjnoscWlasnaCewki = indukcyjnoscWlasnaCewki;
    }
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Metody klasy">
    
    //</editor-fold>
}