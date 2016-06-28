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
public class Rezystor {
    
    // Pola:
    private int identyfikator;
    private static int id0;
    private double rezystancja;
    private double rezystywnosc;
    private double dlugoscPrzewodnika;
    private double polePrzekrojuPoprzecznego;
    private double konduktywnosc;
    public static final double ALFA = 0.004;
    private double temperatura;
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
    public Rezystor(double rezystancja, int id) {
        
        this(rezystancja);
        identyfikator = id;
    }
    
    public Rezystor(double rezystancja) {
        
        this.rezystancja = rezystancja;
        this.temperatura = 20;
    }
    
    public Rezystor(double rezystancja, double temperatura) {
        
        this.rezystancja = rezystancja;
        this.temperatura = temperatura;
    }
    
    public Rezystor(double rezystywnosc, double dlugosc, double pole) {
        
        this.rezystywnosc = rezystywnosc;
        this.konduktywnosc = 1/rezystywnosc;
        this.dlugoscPrzewodnika = dlugosc;
        this.polePrzekrojuPoprzecznego = pole;
        this.temperatura = 20;
        this.rezystancja = (rezystywnosc * dlugosc) / pole;
    }
    // Koniec konstruktorow;
    
    // Akcesory:
    public double getRezystancja() {
        return rezystancja;
    }
    
    public double getRezystywnosc() {
        return rezystywnosc;
    }
        
    public double getDlugoscPrzewodnika() {
        return dlugoscPrzewodnika;
    }
        
    public double getPolePrzekrojuPoprzecznego() {
        return polePrzekrojuPoprzecznego;
    }
        
    public double getKonduktywnosc() {
        return konduktywnosc;
    }
        
    public double getTemperatura() {
        return temperatura;
    }
    
    public int getIdentyfikator() {
        return identyfikator;
    }
    // Koniec akcesorów;
    
    // Mutatory:
    public void setRezystancja(double r) {
        rezystancja = r;
    }
        
    public void setTemperatura(double t) {
        double temp = temperatura;
        temperatura = t;
        rezystancja = rezystancja * (1 + ALFA * (t - temp));
    }
    // Koniec mutatorów;
    
    // Metody klasy:
    public double temperaturowaZmianaRezystancji(double temp) {
        
        // O ile zmieni sie rezystancja w stosunku do obecnie ustawionej temperatury.
        return rezystancja * (1 + ALFA * (temp - temperatura));
    }
    // Koniec metod klasy?
}
