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
package mainPackage;

//import java.util.Scanner; // Do debugowania
import javax.swing.JFrame;

/**
 * @author Błażej Sewera (Jazz)
 * @version 0.9 (2016-05-28) - added Cyfrowa functionality
 */
public class MainApplicationClass {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        
        System.out.println("Starting application...");
        ApplicationMainGUI gui = new ApplicationMainGUI();
        
        gui.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        gui.setVisible(true);
        System.out.println("Started application.");
        
        //<editor-fold defaultstate="collapsed" desc="Śmietnik do debugowania">
        /*        System.out.println("Strt.");
        Scanner s = new Scanner(System.in);
        System.out.println("1");
        Amplituda amplituda = new Amplituda();
        System.out.println("2");
        amplituda.setAmplituda(5.6);
        System.out.println("3");
        amplituda.setMaxAmplituda(10);
        System.out.println("4");
        double ampl, maxAmpl;
        System.out.println("5");
        System.out.println("Start:");
        for(int i = 0; i<3; i++) {
        System.out.println("Podaj amplitude:");
        ampl = s.nextDouble();
        maxAmpl = s.nextDouble();
        amplituda.setAmplituda(ampl);
        amplituda.setMaxAmplituda(maxAmpl);
        System.out.println(amplituda.getAmplitudaAbs());
        System.out.println(amplituda.getWartoscCyfrowejKwantyzacji());
        }*/
        //</editor-fold>
    }
    
}
