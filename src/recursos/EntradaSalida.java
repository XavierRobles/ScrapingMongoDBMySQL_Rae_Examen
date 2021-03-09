package recursos;

import java.util.ArrayList;
import java.util.Scanner;

import entidad.Palabra;

public class EntradaSalida {
	static Scanner sc = new Scanner(System.in);
	public final static int BUSCAR = 1;
	public final static int LISTAR = 2;
	public final static int MYSQL = 3;
	public final static int SALIR = 4;

	public static int mostraMenu() {
		System.out.println("Seleccione una opcion del Menu");
		System.out.println("1 - Insertar nueva palabra");
		System.out.println("2 - Mostrar las palabra y crear un HTML");
		System.out.println("3 - Copiar los datos A MSQL");
		System.out.println("4 - Salir");
		int opcion = Integer.parseInt(sc.nextLine());
		return opcion;

	}

	public static String palabra() {
		System.out.println("Introduzca la palabra a buscar");
		String palabra = sc.nextLine();
		return palabra;
	}

	public static void listarPalabras(ArrayList<Palabra> listaContactos) {
		System.out.println("Palabras Registradas:");
		System.out.println("---------------------------------------------------------");
		for (Palabra p : listaContactos) {
			System.out.println(
					"Nombre: " + p.getNombre() + ", Definicion: " + p.getDefinicion() + ", Url: " + p.getUrl());
			System.out.println("---------------------------------------------------------");

		}
	}
}
