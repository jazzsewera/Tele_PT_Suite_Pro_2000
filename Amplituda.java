/* * * * * * * * * * * * * * * * * * * * *
 * ##################################### *
 * ------------------------------------- *
 * ===== Created by Błażej Sewera ====== *
 * ------------------------------------- *
 * ##################################### *
 * * * * * * * * * * * * * * * * * * * * */

package dodatki;

/**
 * @author Błażej Sewera (Jazz)
 *
 */
public class Amplituda {
	
	private static enum Segmenty {
		Segment1,
		Segment2,
		Segment3,
		Segment4,
		Segment5,
		Segment6,
		Segment7,
		Segment8,
		PeakOrOvershot,
	}
	
	// Początek pól klasy:
	private double amplituda;
	private double amplitudaAbs;
	private double maxAmplituda;
	// Koniec pól klasy;
	
	// Początek konstruktorów:
	public Amplituda(double amplituda, double maxAmplituda, String jednostka, String maxJednostka) {
		
		if (jednostka == "V")
			this.amplituda = amplituda;
		else if (jednostka == "mV")
			this.amplituda = amplituda / 1000;
		else
			this.amplituda = amplituda;
		
		if (maxJednostka == "V")
			this.maxAmplituda = maxAmplituda;
		else if (maxJednostka == "mV")
			this.maxAmplituda = maxAmplituda / 1000;
		else
			this.maxAmplituda = maxAmplituda;
		amplitudaAbs = Math.abs(amplituda);
		// Konstruktor z trzema argumentami
	}
	
	public Amplituda(double amplituda, double maxAmplituda) {
		this(amplituda, maxAmplituda, "V", "V");
		// Konstruktor z dwoma argumentami
	}
	// Koniec konstruktorów;
	
	// Początek mutatorów:
	public void setAmplituda(double amplituda) {
		this.amplituda = amplituda;
		this.amplitudaAbs = Math.abs(amplituda);
	}
	
	public void setMaxAmplituda(double maxAmplituda) {
		this.maxAmplituda = maxAmplituda;
	}
	// Koniec mutatorów;
	
	// Początek akcesorów:
	public double getAmplituda() {
		return amplituda;
	}
	
	public double getMaxAmplituda() {
		return maxAmplituda;
	}
	
	public double getAmplitudaAbs() {
		return amplitudaAbs;
	}
	// Koniec akcesorów;
	
	// Początek metod klasy:
	public boolean czyDodatniaAmplituda() {
		if (amplituda >= 0) return true;
		else return false;
	}
	
	public Segmenty przypiszSegment() {
		
		if (amplitudaAbs >= maxAmplituda/2 && amplitudaAbs < maxAmplituda) return Segmenty.Segment1;
		else if (amplitudaAbs >= maxAmplituda/4 && amplitudaAbs < maxAmplituda/2) return Segmenty.Segment2;
		else if (amplitudaAbs >= maxAmplituda/8 && amplitudaAbs < maxAmplituda/4) return Segmenty.Segment3;
		else if (amplitudaAbs >= maxAmplituda/16 && amplitudaAbs < maxAmplituda/8) return Segmenty.Segment4;
		else if (amplitudaAbs >= maxAmplituda/32 && amplitudaAbs < maxAmplituda/16) return Segmenty.Segment5;
		else if (amplitudaAbs >= maxAmplituda/64 && amplitudaAbs < maxAmplituda/32) return Segmenty.Segment6;
		else if (amplitudaAbs >= maxAmplituda/128 && amplitudaAbs < maxAmplituda/64) return Segmenty.Segment7;
		else if (amplitudaAbs >= 0 && amplitudaAbs < maxAmplituda/128) return Segmenty.Segment8;
		else return Segmenty.PeakOrOvershot;

	}
	
	public String przypiszSegmentBinarnie() {
		String bin;
		bin = Integer.toBinaryString(7 - przypiszSegment().ordinal());
		if (przypiszSegment() == Segmenty.PeakOrOvershot)
			return " Peak Or Overshot ";
		else {
			if (bin.length() == 1)
				return "00" + bin;
			if (bin.length() == 2)
				return "0" + bin;
			else
				return bin;
		}
	}
	
	public String generujPozostale4Bity() {
		
		int nrSegmentu = przypiszSegment().ordinal() + 1;
		double amplitudaWzglKwantyzacji = 0.0;
		int wartoscKwantyzacji = 0;
		double amplitudaWzgl;
		double przedzialKwantyzacji;
		
		switch( nrSegmentu ) {
			case 1:
				amplitudaWzgl = amplitudaAbs-(maxAmplituda/2);
				przedzialKwantyzacji = (maxAmplituda/2) / 16;
				while (amplitudaWzglKwantyzacji <= amplitudaWzgl) {
					amplitudaWzglKwantyzacji += przedzialKwantyzacji;
					wartoscKwantyzacji++;
				}
				break;
			case 2: {
				amplitudaWzgl = amplitudaAbs-(maxAmplituda/4);
				przedzialKwantyzacji = (maxAmplituda/4) / 16;
				while (amplitudaWzglKwantyzacji <= amplitudaWzgl) {
					amplitudaWzglKwantyzacji += przedzialKwantyzacji;
					wartoscKwantyzacji++;
				}
				break;
			}
			case 3: {
				amplitudaWzgl = amplitudaAbs-(maxAmplituda/8);
				przedzialKwantyzacji = (maxAmplituda/8) / 16;
				while (amplitudaWzglKwantyzacji <= amplitudaWzgl) {
					amplitudaWzglKwantyzacji += przedzialKwantyzacji;
					wartoscKwantyzacji++;
				}
				break;
			}
			case 4: {
				amplitudaWzgl = amplitudaAbs-(maxAmplituda/16);
				przedzialKwantyzacji = (maxAmplituda/16) / 16;
				while (amplitudaWzglKwantyzacji <= amplitudaWzgl) {
					amplitudaWzglKwantyzacji += przedzialKwantyzacji;
					wartoscKwantyzacji++;
				}
				break;
			}
			case 5: {
				amplitudaWzgl = amplitudaAbs-(maxAmplituda/32);
				przedzialKwantyzacji = (maxAmplituda/32) / 16;
				while (amplitudaWzglKwantyzacji <= amplitudaWzgl) {
					amplitudaWzglKwantyzacji += przedzialKwantyzacji;
					wartoscKwantyzacji++;
				}
				break;
			}
			case 6: {
				amplitudaWzgl = amplitudaAbs-(maxAmplituda/64);
				przedzialKwantyzacji = (maxAmplituda/64) / 16;
				while (amplitudaWzglKwantyzacji <= amplitudaWzgl) {
					amplitudaWzglKwantyzacji += przedzialKwantyzacji;
					wartoscKwantyzacji++;
				}
				break;
			}
			case 7: {
				amplitudaWzgl = amplitudaAbs-(maxAmplituda/128);
				przedzialKwantyzacji = (maxAmplituda/128) / 16;
				while (amplitudaWzglKwantyzacji <= amplitudaWzgl) {
					amplitudaWzglKwantyzacji += przedzialKwantyzacji;
					wartoscKwantyzacji++;
				}
				break;
			}
			case 8: {;
				przedzialKwantyzacji = (maxAmplituda/128) / 16;
				while (amplitudaWzglKwantyzacji <= amplitudaAbs) {
					amplitudaWzglKwantyzacji += przedzialKwantyzacji;
					wartoscKwantyzacji++;
				}
				break;
			}
			default:
				return " Peak Or Overshot ";
		}
		if (Integer.toBinaryString(wartoscKwantyzacji-1).length() == 3)
			return "0"+Integer.toBinaryString(wartoscKwantyzacji-1);
		else if (Integer.toBinaryString(wartoscKwantyzacji-1).length() == 2)
			return "00"+Integer.toBinaryString(wartoscKwantyzacji-1);
		else if (Integer.toBinaryString(wartoscKwantyzacji-1).length() == 1)
			return "000"+Integer.toBinaryString(wartoscKwantyzacji-1);
		else
			return Integer.toBinaryString(wartoscKwantyzacji-1);
			
	}
	
	public String wyswietlKod13Segmentowej() {
		if (czyDodatniaAmplituda())
			return "1" + przypiszSegmentBinarnie() + generujPozostale4Bity();
		else
			return "0" + przypiszSegmentBinarnie() + generujPozostale4Bity();
	}
	// Koniec metod klasy;

}

// ŚMIETNIK:

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
