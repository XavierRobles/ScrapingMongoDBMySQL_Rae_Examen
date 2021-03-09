package recursos;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import entidad.Palabra;

public class Scraping {

	public static List<Palabra> sacarDatosWeb(String palabra) {
		List<Palabra> lista = new ArrayList();
		Document documento;
		try {
			documento = Jsoup.connect("https://dle.rae.es/" + palabra).get();

			Element elemento_palabra = documento.selectFirst("article .f");
			Element elemento_informal = documento.selectFirst(".j");

			String palabraBuscada = elemento_palabra.text();
			String definicion = elemento_informal.text();

			System.out.println("Has buscado: " + palabraBuscada);
			System.out.println("La definicion es: " + definicion + "\n");

			Document documento2;
			documento2 = Jsoup.connect("https://es.wikipedia.org/wiki/" + palabra + "").get();

			Elements direcio_url = documento2.select(".image img");
			String urls = direcio_url.attr("src");
			System.out.println("Direccion de la foto: " + urls);
			String nombre_palabra = "";
			Palabra p = new Palabra(palabraBuscada, definicion, urls);
			lista.add(p);

			AccesoBD.grabarMongoBD(lista);
			return lista;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return lista;
	}

}