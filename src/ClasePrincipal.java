import java.util.ArrayList;
import java.util.List;

import entidad.Palabra;
import recursos.AccesoBD;
import recursos.EntradaSalida;
import recursos.Scraping;

public class ClasePrincipal {
	private static List<Palabra> lista;

	public static void main(String[] args) {

		int opcion = EntradaSalida.mostraMenu();

		while (opcion != EntradaSalida.SALIR) {
			switch (opcion) {
			case EntradaSalida.BUSCAR:

				String palabra = EntradaSalida.palabra();
				lista = recursos.Scraping.sacarDatosWeb(palabra);

				break;
			case EntradaSalida.LISTAR:

				ArrayList<Palabra> listaPalabras = AccesoBD.listarPalabras();

				EntradaSalida.listarPalabras(listaPalabras);

				break;

			case EntradaSalida.MYSQL:

				ArrayList<Palabra> listaPalabras2 = AccesoBD.listarPalabras();

				AccesoBD.inicializarConexion();
				AccesoBD.insertarPalabrasMySQL(listaPalabras2);

				break;
			case EntradaSalida.SALIR:

				break;

			default:
				break;
			}
			opcion = EntradaSalida.mostraMenu();

		}
		System.out.println("Cerrando Aplicacion...");
	}
}
