package recursos;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.query.Query;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;

import entidad.Palabra;

public class AccesoBD {

	public static void grabarMongoBD(List<Palabra> lista) {

		MongoClient mongoClient = new MongoClient(new MongoClientURI("mongodb://localhost:27017"));
		DB database = mongoClient.getDB("Palabras");
		DBCollection tablapalabras = database.getCollection("Palabras");
		for (Palabra c : lista) {
			DBObject palabras = new BasicDBObject().append("Nombre", c.getNombre())
					.append("Definicion", c.getDefinicion()).append("URL", c.getUrl());
			tablapalabras.insert(palabras);
		}

		System.out.println("####################################");
		System.out.println("Insertando datos en la BBDD.");
		System.out.println("####################################\n");

	}

	public static ArrayList<Palabra> listarPalabras() {

		MongoClient mongoClient = new MongoClient(new MongoClientURI("mongodb://localhost:27017"));
		DB database = mongoClient.getDB("Palabras");
		DBCollection tablapalabras = database.getCollection("Palabras");

		ArrayList<Palabra> listapalabras = new ArrayList();
		Iterable<DBObject> lista_palabras = tablapalabras.find();
		for (DBObject dbo : lista_palabras) {

			Palabra c = new Palabra();
			c.setNombre((String) dbo.get("Nombre"));
			c.setDefinicion((String) dbo.get("Definicion"));
			c.setUrl((String) dbo.get("URL"));
			listapalabras.add(c);
			PintaHTML.CrearHTML(listapalabras);
		}
		return listapalabras;
	}

	static StandardServiceRegistry s = null;
	static SessionFactory sf = null;
	static Session sesion = null;

	public static void inicializarConexion() {
		s = new StandardServiceRegistryBuilder().configure().build();
		sf = new MetadataSources(s).buildMetadata().buildSessionFactory();
		sesion = sf.openSession();
	}

	public static void insertarPalabrasMySQL(List<Palabra> listaPalabras) {
		Transaction t = sesion.beginTransaction();
		for (Palabra p : listaPalabras) {
			if (p.getDefinicion().length() > 255) {
				p.setDefinicion(p.getDefinicion().substring(250));
			}
			sesion.save(p);

		}
		t.commit();
	}
}
