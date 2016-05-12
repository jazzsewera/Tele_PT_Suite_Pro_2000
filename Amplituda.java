//<editor-fold defaultstate="collapsed" desc="Copyright">
/*
* Copyright (C) 2016 jazz
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

/**
 * <blockquote>Labores pariunt honores</blockquote>
 * @author Błażej Sewera (Jazz)
 * @version 0.9 Beta (2016-04-15) - needs testing
 */
public class Amplituda {
	
	// =+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+
	// =======================     POLA I ENUMY:     ================================
	
	// Początek pól wyliczeniowych:
	/**
	 * <h2>Segmenty13Segmentowa (enum)</h2>
	 * <b>Pole wyliczeniowe dla kompresji 13 segmentowej</b> <br>
	 * Wartości: (przy największej amplitudzie) Segment1, do (przy najmniejszej) Segment8. <br>
	 * Jest jeszcze dodana wartość Overshoot, gdy amplituda jest > maxAmplituda.
	 * 
	 */
	static enum Segmenty13Segmentowa {
		Segment1("Segment 1"),
		Segment2("Segment 2"),
		Segment3("Segment 3"),
		Segment4("Segment 4"),
		Segment5("Segment 5"),
		Segment6("Segment 6"),
		Segment7("Segment 7"),
		Segment8("Segment 8"),
		Overshoot("Overshoot");
                
                String s;
                
                Segmenty13Segmentowa(String s) {
                        this.s = s;
                }
                
                @Override
                public String toString() {
                        return s;
                }
	}
	
	/**
	 * <h2>SegmentyCyfrowa (enum)</h2>
	 * <b>Pole wyliczeniowe dla kompresji cyfrowej</b>
	 * Wartości: (przy największej amplitudzie) Segment7, do (przy najmniejszej) Segment1a. <br>
	 * Jest jeszcze dodana wartość Overshoot, gdy amplituda jest > maxAmplituda.
	 * 
	 */
	static enum SegmentyCyfrowa {
		Segment1a("Segment 1a"),
		Segment1b("Segment 1b"),
		Segment2("Segment 2"),
		Segment3("Segment 3"),
		Segment4("Segment 4"),
		Segment5("Segment 5"),
		Segment6("Segment 6"),
		Segment7("Segment 7"),
		Overshoot("Overshoot");
                
                String s;
                
                SegmentyCyfrowa(String s) {
                        this.s = s;
                }
                
                @Override
                public String toString() {
                        return s;
                }
	}
	// Koniec pól wyliczeniowych;
	
	// ==============================================================================
	
	// Początek pól klasy:
	private double amplituda;
	private double amplitudaAbs;
	private double maxAmplituda;
	private int wartoscCyfrowejKwantyzacji = 0;
	// Koniec pól klasy;
	
	// =+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+
	// =======================      KONSTRUKTORY:    ================================
	
	// Początek konstruktorów:
	/**
	 * Konstruktor czteroargumentowy, używany głównie, gdy jednostka jest inna niż Volty [V]. <br>
	 * <b>Użycie:</b><br>
	 * <code>Amplituda ampl = new Amplituda(1.5, 10.1, "V", "V");</code>
	 * @param amplituda (amplituda sygnału; double)
	 * @param maxAmplituda (maksymalna możliwa amplituda; double)
	 * @param jednostka (jednostka amplitudy sygnału; String[] = {"V", "mV"})
	 * @param maxJednostka (jednostka maksymalnej możliwej amplitudy; String[] = {"V", "mV"})
	 */
	public Amplituda(double amplituda, double maxAmplituda, String jednostka, String maxJednostka) {
		
		if (null != jednostka)
			switch (jednostka) {
                case "V":
                    this.amplituda = amplituda;
                    break;
                case "mV":
                    this.amplituda = amplituda / 1000;
                    break;
                default:
                    this.amplituda = amplituda;
                    break;
            }
		
		if (null != maxJednostka)
			switch (maxJednostka) {
                case "V":
                    this.maxAmplituda = maxAmplituda;
                    break;
                case "mV":
                    this.maxAmplituda = maxAmplituda / 1000;
                    break;
                default:
                    this.maxAmplituda = maxAmplituda;
                    break;
            }
		amplitudaAbs = Math.abs(amplituda);
		// Konstruktor z czterema argumentami
	}
	/**
	 * Konstruktor dwuargumentowy, dla jednostki Volt [V]<br>
	 * <b>Użycie:</b><br>
	 * <code>Amplituda ampl = new Amplituda(1.5, 10.1);</code>
	 * @param amplituda (amplituda sygnału; double)
	 * @param maxAmplituda (maksymalna możliwa amplituda; double)
	 */
	public Amplituda(double amplituda, double maxAmplituda) {
		this(amplituda, maxAmplituda, "V", "V");
		// Konstruktor z dwoma argumentami
	}
        
        public Amplituda() {
                this(0.0, 0.0);
        }
	// Koniec konstruktorów;
	
	// =+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+
	// ====================       AKCESORY I MUTATORY:      =========================
	
	// Początek mutatorów:
	/**
	 * Mutator amplitudy używany przy jednostkach innych niż Volty [V]<br>
	 * Ustawia również <b>amplitudaAbs</b>.<br>
	 * <b>Użycie:</b><br>
	 * <code>ampl.setAmplituda(1.5, "V");</code>
	 * @param amplituda
	 * @param jednostka
	 */
	public void setAmplituda(double amplituda, String jednostka) {
		if (null != jednostka)
			switch (jednostka) {
                case "V":
                    this.amplituda = amplituda;
                    break;
                case "mV":
                    this.amplituda = amplituda / 1000;
                    break;
                default:
                    this.amplituda = amplituda;
                    break;
            }
		
		amplitudaAbs = Math.abs(amplituda);
	}
	
	/**
	 * Mutator maksymalnej możliwej amplitudy używany przy jednostkach innych niż Volty [V]<br>
	 * <b>Użycie:</b><br>
	 * <code>ampl.setMaxAmplituda(1.5, "V");</code>
	 * @param maxAmplituda
	 * @param maxJednostka
	 */
	public void setMaxAmplituda(double maxAmplituda, String maxJednostka) {
		if (null != maxJednostka)
			switch (maxJednostka) {
                case "V":
                    this.maxAmplituda = maxAmplituda;
                    break;
                case "mV":
                    this.maxAmplituda = maxAmplituda / 1000;
                    break;
                default:
                    this.maxAmplituda = maxAmplituda;
                    break;
            }
	}
	
	/**
	 * Mutator amplitudy używany przy jednostkach Volty [V]<br>
	 * <b>Użycie:</b><br>
	 * Ustawia również <b>amplitudaAbs</b>.<br>
	 * <code>ampl.setAmplituda(1.5);</code>
	 * @param amplituda
	 */
	public void setAmplituda(double amplituda) {
		setAmplituda(amplituda, "V");
	}
	
	/**
	 * Mutator maksymalnej możliwej amplitudy używany przy jednostkach Volty [V]<br>
	 * <b>Użycie:</b><br>
	 * <code>ampl.setMaxAmplituda(1.5);</code>
	 * @param maxAmplituda
	 */
	public void setMaxAmplituda(double maxAmplituda) {
		setMaxAmplituda(maxAmplituda, "V");
	}
	// Koniec mutatorów;
	
	// ==============================================================================
	
	// Początek akcesorów:
	/**
	 * Akcesor pola amplituda z klasy Amplituda.
	 * @return amplituda (dodatki.Amplituda.amplituda)
	 */
	public double getAmplituda() {
		return amplituda;
	}
	
	/**
	 * Akcesor pola maxAmplituda z klasy Amplituda.
	 * @return maxAmplituda (dodatki.Amplituda.maxAmplituda)
	 */
	public double getMaxAmplituda() {
		return maxAmplituda;
	}
	
	/**
	 * Akcesor pola amplitudaAbs (czyli moduł pola amplituda) z klasy Amplituda.
	 * @return amplitudaAbs (dodatki.Amplituda.amplitudaAbs)
	 */
	public double getAmplitudaAbs() {
		return amplitudaAbs;
	}
	
	/**
	 * Akcesor pola wartoscCyfrowejKwantyzacji z klasy Amplituda.
	 * @return wartoscCyfrowejKwantyzacji (dodatki.Amplituda.wartoscCyfrowejKwantyzacji)
	 */
	public int getWartoscCyfrowejKwantyzacji() {
		return wartoscCyfrowejKwantyzacji;
	}
	// Koniec akcesorów;
	
	// =+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+
	// =======================       METODY KLASY:      =============================
	
	// Początek metod klasy:
	
	/**
	 * <h2>Czy dodatnia amplituda</h2>
	 * Wspólna metoda dla cyfrowej i 13 segmentowej
	 * @return True, gdy amplituda jest >= 0 lub false, gdy jest < 0.
	 */
	public boolean czyDodatniaAmplituda() {
            return amplituda >= 0;
	}
	
		// Początek metod kompresji 13 segmentowej:
	
	/**
	 * Metoda przypisująca segment amplitudzie. <br>
	 * <b>Użycie:</b><br>
	 * <code>ampl.segmentowaPrzypiszSegment();</code>
	 * @return element z enum Segmenty13Segmentowa
	 */
	public Segmenty13Segmentowa segmentowaPrzypiszSegment() {
		
		if (amplitudaAbs > maxAmplituda/2 && amplitudaAbs <= maxAmplituda) return Segmenty13Segmentowa.Segment1;
		else if (amplitudaAbs > maxAmplituda/4 && amplitudaAbs <= maxAmplituda/2) return Segmenty13Segmentowa.Segment2;
		else if (amplitudaAbs > maxAmplituda/8 && amplitudaAbs <= maxAmplituda/4) return Segmenty13Segmentowa.Segment3;
		else if (amplitudaAbs > maxAmplituda/16 && amplitudaAbs <= maxAmplituda/8) return Segmenty13Segmentowa.Segment4;
		else if (amplitudaAbs > maxAmplituda/32 && amplitudaAbs <= maxAmplituda/16) return Segmenty13Segmentowa.Segment5;
		else if (amplitudaAbs > maxAmplituda/64 && amplitudaAbs <= maxAmplituda/32) return Segmenty13Segmentowa.Segment6;
		else if (amplitudaAbs > maxAmplituda/128 && amplitudaAbs <= maxAmplituda/64) return Segmenty13Segmentowa.Segment7;
		else if (amplitudaAbs >= 0 && amplitudaAbs <= maxAmplituda/128) return Segmenty13Segmentowa.Segment8;
		else return Segmenty13Segmentowa.Overshoot;

	}
	
	
	/**
	 * Metoda zwracająca 3 bity oznaczające dany segment (przypisany przez metodę segmentowaPrzypiszSegment() )<br>
	 * <b>Użycie:</b>
	 * <code>ampl.segmentowaPrzypiszSegmentBinarnie();</code>
	 * @return String "***", gdzie * oznacza 0 lub 1
	 */
	public String segmentowaPrzypiszSegmentBinarnie() {
		if (segmentowaPrzypiszSegment() == Segmenty13Segmentowa.Overshoot) return " Overshoot ";
		String bin;
		bin = Integer.toBinaryString(7 - segmentowaPrzypiszSegment().ordinal());
			if (bin.length() == 1)
				return "00" + bin;
			if (bin.length() == 2)
				return "0" + bin;
			else
				return bin;
	}
	
	
	/**
	 * Metoda zwracająca pozostałe 4 bity stworzone wg zasad kompresji 13 segmentowej.<br>
	 * <b>Użycie:</b>
	 * <code>ampl.segmentowaGenerujPozostale4Bity();</code>
	 * @return String "****", gdzie * oznacza 0 lub 1
	 */
	public String segmentowaGenerujPozostale4Bity() {
		
		int nrSegmentu = segmentowaPrzypiszSegment().ordinal() + 1;
		double amplitudaWzglKwantyzacji = 0.0;
		int wartoscKwantyzacji = 0;
		double amplitudaWzgl;
		double przedzialKwantyzacji;
		
		switch( nrSegmentu ) {
			case 1:
				amplitudaWzgl = amplitudaAbs-(maxAmplituda/2);
				przedzialKwantyzacji = (maxAmplituda/2) / 16;
				while (amplitudaWzglKwantyzacji < amplitudaWzgl) {
					amplitudaWzglKwantyzacji += przedzialKwantyzacji;
					wartoscKwantyzacji++;
				}
				break;
			case 2: {
				amplitudaWzgl = amplitudaAbs-(maxAmplituda/4);
				przedzialKwantyzacji = (maxAmplituda/4) / 16;
				while (amplitudaWzglKwantyzacji < amplitudaWzgl) {
					amplitudaWzglKwantyzacji += przedzialKwantyzacji;
					wartoscKwantyzacji++;
				}
				break;
			}
			case 3: {
				amplitudaWzgl = amplitudaAbs-(maxAmplituda/8);
				przedzialKwantyzacji = (maxAmplituda/8) / 16;
				while (amplitudaWzglKwantyzacji < amplitudaWzgl) {
					amplitudaWzglKwantyzacji += przedzialKwantyzacji;
					wartoscKwantyzacji++;
				}
				break;
			}
			case 4: {
				amplitudaWzgl = amplitudaAbs-(maxAmplituda/16);
				przedzialKwantyzacji = (maxAmplituda/16) / 16;
				while (amplitudaWzglKwantyzacji < amplitudaWzgl) {
					amplitudaWzglKwantyzacji += przedzialKwantyzacji;
					wartoscKwantyzacji++;
				}
				break;
			}
			case 5: {
				amplitudaWzgl = amplitudaAbs-(maxAmplituda/32);
				przedzialKwantyzacji = (maxAmplituda/32) / 16;
				while (amplitudaWzglKwantyzacji < amplitudaWzgl) {
					amplitudaWzglKwantyzacji += przedzialKwantyzacji;
					wartoscKwantyzacji++;
				}
				break;
			}
			case 6: {
				amplitudaWzgl = amplitudaAbs-(maxAmplituda/64);
				przedzialKwantyzacji = (maxAmplituda/64) / 16;
				while (amplitudaWzglKwantyzacji < amplitudaWzgl) {
					amplitudaWzglKwantyzacji += przedzialKwantyzacji;
					wartoscKwantyzacji++;
				}
				break;
			}
			case 7: {
				amplitudaWzgl = amplitudaAbs-(maxAmplituda/128);
				przedzialKwantyzacji = (maxAmplituda/128) / 16;
				while (amplitudaWzglKwantyzacji < amplitudaWzgl) {
					amplitudaWzglKwantyzacji += przedzialKwantyzacji;
					wartoscKwantyzacji++;
				}
				break;
			}
			case 8: {
				przedzialKwantyzacji = (maxAmplituda/128) / 16;
				while (amplitudaWzglKwantyzacji < amplitudaAbs) {
					amplitudaWzglKwantyzacji += przedzialKwantyzacji;
					wartoscKwantyzacji++;
				}
				break;
			}
			default:
				return " Overshoot ";
		}
            switch (Integer.toBinaryString(wartoscKwantyzacji-1).length()) {
                case 3:
                    return "0"+Integer.toBinaryString(wartoscKwantyzacji-1);
                case 2:
                    return "00"+Integer.toBinaryString(wartoscKwantyzacji-1);
                case 1:
                    return "000"+Integer.toBinaryString(wartoscKwantyzacji-1);
                default:
                    return Integer.toBinaryString(wartoscKwantyzacji-1);
            }
			
	}
	
	
	/**
	 * <h2>Kompresja 13 Segmentowa</h2>
	 * Krzywa kompresji typu A CCiTT G 703 <br>
	 * <b>Kroki kompresji:</b>
	 * <ol>
	 * <li>Generacja pierwszego bitu (znaku): dla amplitudy dodatniej - 1, a dla ujemnej - 0. Realizowane przez czyDodatniaAmplituda() </li>
	 * <li>Zakwalifikowanie amplitudy do segmentu. Realizowane przez segmentowaPrzypiszSegment() </li>
	 * <li>Wygenerowanie trzech bitów segmentu. Realizowane przez segmentowaPrzypiszSegmentBinarnie() </li>
	 * <li>Wygenerowanie 4 kolejnych bitów przez podzielenie segmentu na 16 równych części i porównanie amplitudy z tymi segmentami. Realizowane przez segmentowaGenerujPozostale4Bity() </li>
	 * </ol>
	 * <b>Na samym końcu skleca się to wszystko w kupę przez metodę segmentowaWyswietlKod()</b>
	 * 
	 */
	public String segmentowaWyswietlKod() {
		if (czyDodatniaAmplituda())
			return "1" + segmentowaPrzypiszSegmentBinarnie() + segmentowaGenerujPozostale4Bity();
		else
			return "0" + segmentowaPrzypiszSegmentBinarnie() + segmentowaGenerujPozostale4Bity();
	}
	
		// Koniec metod kompresji 13 segmentowej;
	
		// ========================================================================
	
		// Początek metod kompresji cyfrowej:
	
	/**
	 * Metoda zwracająca 12 bitów wygenerowanych według kodowania liniowego PCM. Potrzebne do kompresji cyfrowej.<br>
	 * <b>Użycie:</b>
	 * <code>ampl.cyfrowaGeneruj12Bitowy();</code>
	 * @return String "************" gdzie * oznacza 0 lub 1
	 */
	public String cyfrowaGeneruj12Bitowy() {
		
		double przedzialKwantyzacji = maxAmplituda / 2047;
		
		while (wartoscCyfrowejKwantyzacji * przedzialKwantyzacji < amplitudaAbs) wartoscCyfrowejKwantyzacji++;
		String bin12 = Integer.toBinaryString(wartoscCyfrowejKwantyzacji);
		if (bin12.length() < 11) {
			if (czyDodatniaAmplituda())
				return "1" + new String(new char[11-bin12.length()]).replace("\0", "0") + bin12;
			else
				return "0" + new String(new char[11-bin12.length()]).replace("\0", "0") + bin12;
		}
		else if (bin12.length() == 11) {
			if (czyDodatniaAmplituda())
				return "1" + bin12;
			else
				return "0" + bin12;
		}
		else
			return "Overshoot";
		
	}
	
	
	/**
	 * Metoda przypisująca segment z kompresji cyfrowej.<br>
	 * <b>Użycie:</b>
	 * <code>ampl.cyfrowaPrzypiszSegment();</code>
	 * @return element z enum SegmentyCyfrowa
	 */
	public SegmentyCyfrowa cyfrowaPrzypiszSegment() {
		if (wartoscCyfrowejKwantyzacji > 1024 && wartoscCyfrowejKwantyzacji <= 2048) return SegmentyCyfrowa.Segment7;
		else if (wartoscCyfrowejKwantyzacji > 512 && wartoscCyfrowejKwantyzacji <= 1024) return SegmentyCyfrowa.Segment6;
		else if (wartoscCyfrowejKwantyzacji > 256 && wartoscCyfrowejKwantyzacji <= 512) return SegmentyCyfrowa.Segment5;
		else if (wartoscCyfrowejKwantyzacji > 128 && wartoscCyfrowejKwantyzacji <= 256) return SegmentyCyfrowa.Segment4;
		else if (wartoscCyfrowejKwantyzacji > 64 && wartoscCyfrowejKwantyzacji <= 128) return SegmentyCyfrowa.Segment3;
		else if (wartoscCyfrowejKwantyzacji > 32 && wartoscCyfrowejKwantyzacji <= 64) return SegmentyCyfrowa.Segment2;
		else if (wartoscCyfrowejKwantyzacji > 16 && wartoscCyfrowejKwantyzacji <= 32) return SegmentyCyfrowa.Segment1b;
		else if (wartoscCyfrowejKwantyzacji >= 0 && wartoscCyfrowejKwantyzacji <= 16) return SegmentyCyfrowa.Segment1a;
		else return SegmentyCyfrowa.Overshoot;
	}
	
	
	/**
	 * Metoda zwracająca 3 bity segmentu z kompresji cyfrowej.<br>
	 * <b>Użycie:</b>
	 * <code>ampl.cyfrowaPrzypiszSegmentBinarnie();</code>
	 * @return String "***" gdzie * oznacza 0 lub 1
	 */
	public String cyfrowaPrzypiszSegmentBinarnie() {
		if (cyfrowaPrzypiszSegment() == SegmentyCyfrowa.Overshoot) return " Overshoot ";
		String bin;
		bin = Integer.toBinaryString(cyfrowaPrzypiszSegment().ordinal());
			if (bin.length() == 1)
				return "00" + bin;
			if (bin.length() == 2)
				return "0" + bin;
			else
				return bin;
	}
	
	
	/**
	 * Metoda zwracająca String z oznaczeniem, które bity są brane pod uwagę przy kompresji do 8 bitów.<br>
	 * <b>Użycie:</b>
	 * <code>ampl.cyfrowaPodajWXYZ(8);</code>
	 * lub <br>
	 * <code>ampl.cyfrowaPodajWXYZ(12);</code>
	 * @return String o długości 12, np. "S001WXYZ****" lub 8, np. "S001WXYZ"
	 */
	public String cyfrowaPodajWXYZ(short b) {
		if (b == 8)
			return "S" + cyfrowaPrzypiszSegmentBinarnie() + "WXYZ";
		else {
			switch (cyfrowaPrzypiszSegment().ordinal()) {
			case 0:
				return "S0000000WXYZ";
			case 1:
				return "S0000001WXYZ";
			case 2:
				return "S000001WXYZ*";
			case 3:
				return "S00001WXYZ**";
			case 4:
				return "S0001WXYZ***";
			case 5:
				return "S001WXYZ****";
			case 6:
				return "S01WXYZ*****";
			case 7:
				return "S1WXYZ******";
			}
			return " Overshoot ";
		}
	}
	
	
	/**
	 * Metoda zwracająca pozostałe 4 bity wg kompresji cyfrowej.<br>
	 * Bity są wybierane ze względu na numer segmentu. Są to bity WXYZ.<br>
	 * <b>Użycie:</b>
	 * <code>ampl.cyfrowaGenerujPozostale4Bity();</code>
	 * @return String "****" gdzie * oznacza 0 lub 1
	 */
	public String cyfrowaGenerujPozostale4Bity() {
		char[] bitArray = cyfrowaGeneruj12Bitowy().toCharArray();
		if (bitArray.length == 12) {
			switch (cyfrowaPrzypiszSegment().ordinal()) {
			case 0:
				return String.valueOf(bitArray, 8, 4);
			case 1:
				return String.valueOf(bitArray, 8, 4);
			case 2:
				return String.valueOf(bitArray, 7, 4);
			case 3:
				return String.valueOf(bitArray, 6, 4);
			case 4:
				return String.valueOf(bitArray, 5, 4);
			case 5:
				return String.valueOf(bitArray, 4, 4);
			case 6:
				return String.valueOf(bitArray, 3, 4);
			case 7:
				return String.valueOf(bitArray, 2, 4);
			}
		}
		return " Overshoot ";
	}
	
	
	/**
	 * <h2>Kompresja cyfrowa</h2>
	 * Kompresja cyfrowa jest c. interpretacją krzywej kompresji typu A CCiTT G 703 <br>
	 * <b>Kroki kompresji:</b>
	 * <ol>
	 * <li>Generacja pierwszego bitu (znaku): dla amplitudy dodatniej - 1, a dla ujemnej - 0. Realizowane przez czyDodatniaAmplituda() </li>
	 * <li>Zakodowanie amplitudy na 12 bitach liniowo </li>
	 * <li>Zakwalifikowanie amplitudy do segmentu. Realizowane przez cyfrowaPrzypiszSegment() </li>
	 * <li>Wygenerowanie trzech bitów segmentu. Realizowane przez cyfrowaPrzypiszSegmentBinarnie() </li>
	 * <li>Wygenerowanie 4 kolejnych bitów przez wybranie bitów WXYZ z kodowania 12 bitowego. Realizowane przez cyfrowaGenerujPozostale4Bity() </li>
	 * </ol>
	 * <b>Na samym końcu skleca się to wszystko w kupę przez metodę cyfrowaaWyswietlKod()</b>
	 * 
	 */
	public String cyfrowaWyswietlKod() {
		if (czyDodatniaAmplituda())
			return "1" + cyfrowaPrzypiszSegmentBinarnie() + cyfrowaGenerujPozostale4Bity();
		else return "0" + cyfrowaPrzypiszSegmentBinarnie() + cyfrowaGenerujPozostale4Bity();
	}
		// Koniec metod kompresji cyfrowej;
	
	// Koniec metod klasy;

}


// =+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+
// +=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=
// =+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+



/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 * Poniżej są elementy, które się nie przydały, ale mogą się *
 *   przydać w przyszłości. Nie ulegają one, jak na razie    *
 *   sprawdzeniu.                                            *
 * Można tam też wprowadzić swoje komentarze i podpowiedzi,  *
 *   jednak prosiłbym o podanie daty, imienia lub nicku i    *
 *   samej treści komentarza. Można też edytować sam kod :)  *
 *                                                           *
 *   Pozdrawiam z całego serca,                              *
 *   Błażej Sewera                                           *
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */


//<editor-fold defaultstate="collapsed" desc="Śmietnik">
// =+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+
// =========================       ŚMIETNIK:      ===============================

/*
	public static int poteguj(int liczba, int wykladnik) {
	if (wykladnik == 0) return 0;
	
	for (int i=0; i < wykladnik; i++) {
		wykladnik *= wykladnik;
		
	}
	return wykladnik;
}
		
		if (przypiszSegment().ordinal() == 0) {
			// Dla 1 segmentu
			double amplitudaWzgl = amplitudaAbs-(maxAmplituda/2);
		}
		else if (przypiszSegment().ordinal() == 1) {
			// Dla 2 segmentu
			
		}
		else if (przypiszSegment().ordinal() == 2) {
			// Dla 3 segmentu
			
		}
		else if (przypiszSegment().ordinal() == 3) {
			// Dla 4 segmentu
			
		}
		else if (przypiszSegment().ordinal() == 4) {
			// Dla 5 segmentu
			
		}
		else if (przypiszSegment().ordinal() == 5) {
			// Dla 6 segmentu
			
		}
		else if (przypiszSegment().ordinal() == 6) {
			// Dla 7 segmentu
			
		}
		else if (przypiszSegment().ordinal() == 7) {
			// Dla 8 segmentu
			
		}
*/
//</editor-fold>