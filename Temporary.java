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

import obwody.elementy.Rezystor;

/**
 *
 * @author jazz
 */
public class Temporary {
    public static void main(String[] args) {
        ObwodPraduStalego obw = new ObwodPraduStalego();
        Rezystor r1 = new Rezystor(12);
        Rezystor r2 = new Rezystor(120);
        obw.dodajRezystorSzeregowo(r1);
        obw.dodajRezystorSzeregowo(r2);
        obw.dodajRezystorRownolegle(r1);
        obw.dodajRezystorSzeregowo(new Rezystor(2500));
        obw.setNapiecie(25);
        System.out.println(obw.getRezystancjaZastepcza());
        System.out.println(obw.getPrad());
        System.out.println(obw.wyswietlRezystory());
    }

}
