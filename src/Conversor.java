
public class Conversor {
	char desde = 'C';
	char grado = 'F';
	//convierte de celsius
	double multi(double C) {
		switch (grado) {
		case 'F': return (C * (9.0/5.0)) + 32.0;
		case 'K': return C + 273.15;
		case 'R': return C * (9.0/5.0) + 491.67;
		case 'C': return C;
		default: return 0.0;
		}
	}
	//convierte a celsius
	double celsius(double g){
		switch (desde) {
		case 'C': return g;
		case 'F': return (g - 32) * (5.0/9.0);
		case 'K': return g - 273.15;
		case 'R': return (g - 491.67) * (5.0/9.0);
		default: return 0.0;
		}
	}
	//setear los dos chars y realizar la conversion
	double res(double G) {
		G = celsius(G);
		return multi(G);
	}
	
}
