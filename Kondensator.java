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
package obwody.elementy;

/**
 *
 * @author jazz
 */
public class Kondensator {
    
    // Pola:
    private int identyfikator;
    private static int id0;
    private double pojemnosc;
    private double poleOkladzin;
    private double odlegloscMiedzyOkladzinami;
    private double bezwzglednaPrzenikalnoscElektryczna;
    private double wzglednaPrzenikalnoscElektryczna;
    public static final double EPSILON0 = 8.85e-12;
    // Koniec pol;
    
    // Inicjalizacja:
    static {
        id0 = 0;
    }
    {
        identyfikator = id0;
        id0++;
    }
    // Koniec inicjalizacji;
    
    // Konstruktory:
    public Kondensator(double pojemnosc, int id) {
        
        this(pojemnosc);
        identyfikator = id;
    }
    
    public Kondensator(double pojemnosc) {
        
        this.pojemnosc = pojemnosc;
    }
    
    public Kondensator(double poleOkladzin, double odlegloscMiedzyOkladzinami, double przenikalnoscElektryczna, char wzbz) {
        
        this.poleOkladzin = poleOkladzin;
        this.odlegloscMiedzyOkladzinami = odlegloscMiedzyOkladzinami;
        
        if(wzbz == 'w' || wzbz == 'W') {
            this.wzglednaPrzenikalnoscElektryczna = przenikalnoscElektryczna;
            this.bezwzglednaPrzenikalnoscElektryczna = przenikalnoscElektryczna * EPSILON0;
        }
        else {
            this.bezwzglednaPrzenikalnoscElektryczna = przenikalnoscElektryczna;
            this.wzglednaPrzenikalnoscElektryczna = przenikalnoscElektryczna / EPSILON0;
        }
    }
    
    public Kondensator(double poleOkladzin, double odlegloscMiedzyOkladzinami, double bezwzglednaPrzenikalnoscElektryczna) {
        
        this(poleOkladzin, odlegloscMiedzyOkladzinami, bezwzglednaPrzenikalnoscElektryczna, 'B');
    }
    // Koniec konstruktorow;
    
    // Akcesory:
    public double getPojemnosc() {
        return pojemnosc;
    }
    
    public double getPoleOkladzin() {
        return poleOkladzin;
    }
    
    public double getOdlegloscMiedzyOkladzinami() {
        return odlegloscMiedzyOkladzinami;
    }
    
    public double getBezwzglednaPrzenikalnoscElektryczna() {
        return bezwzglednaPrzenikalnoscElektryczna;
    }
    
    public double getWzglednaPrzenikalnoscElektryczna() {
        return wzglednaPrzenikalnoscElektryczna;
    }
    
    public int getIdentyfikator() {
        return identyfikator;
    }
    // Koniec akcesorow;
    
    // Mutatory:
    public void setPojemnosc(double c) {
        pojemnosc = c;
    }
    // Koniec mutatorow;
    
    // Metody klasy:
    
    // Koniec metod klasy?
}
