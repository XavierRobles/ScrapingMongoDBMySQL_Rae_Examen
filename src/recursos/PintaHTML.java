package recursos;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import entidad.Palabra;

public class PintaHTML {

	public static void CrearHTML(List<Palabra> lista) {

		String html = "<html><head><title>Palabras</title></head><body><table>";
		html += "<h1>Palabras</h1>";
		for (Palabra c : lista) {
			html += "<tr><td><img src='https://" + c.getUrl() + "'></td>";
			html += "<td>" + c.getNombre() + "</td><td>" + c.getDefinicion() + "</td></tr>";
		}
		html += "</table></body></html>";

		crearArchivo(html);
	}

	public static void crearArchivo(String html) {

		try {
			FileWriter fw = new FileWriter("C:\\Users\\xavie\\pruebasmenu\\palabras\\palabra.html");

			fw.write(html);
			fw.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
