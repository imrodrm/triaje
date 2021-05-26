package com.spring.pruebas;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import com.spring.modelo.entidades.Enfermedad;
import com.spring.modelo.entidades.Evaluacion;
import com.spring.modelo.entidades.Paciente;
import com.spring.modelo.entidades.PacienteEnfermedad;
import com.spring.modelo.entidades.PersonalUrgencias;

public class ProbandoMapeos {

	public static void main(String[] args) {
		SessionFactory factoria = new Configuration()
				.configure("hibernate.cfg.xml")
				.addAnnotatedClass(Paciente.class)
				.addAnnotatedClass(Enfermedad.class)
				.addAnnotatedClass(PacienteEnfermedad.class)
				.addAnnotatedClass(Evaluacion.class)
				.addAnnotatedClass(PersonalUrgencias.class)
				.buildSessionFactory();
		
		Session sesion = factoria.getCurrentSession();
		
		try {

			// Comenzamos la transacción
			System.out.println("Comenzamos la transacción");
			sesion.beginTransaction();
/*
			Scanner sc = new Scanner(System.in);

			String user = sc.next();
			System.out.println("Usuario: " + user);

			String pwd = sc.next();
			System.out.println("Contrasenia: " + pwd);

			MessageDigest md5 = MessageDigest.getInstance("MD5");
			byte[] hashInOne = md5.digest(pwd.getBytes("UTF-8"));

			String hashMD5String = getString(hashInOne);

			System.out.println(hashMD5String);

			Query<PersonalUrgencias> query2 = sesion.createQuery(
					"FROM PersonalUrgencias WHERE contrasenia=: pwd AND usuario =:user", PersonalUrgencias.class);
			query2.setParameter("user", user);
			query2.setParameter("pwd", hashMD5String);
			List<PersonalUrgencias> haLogueado = query2.getResultList();
			System.out.println();
			System.out.println();
			System.out.println("Lista: ");
			for(PersonalUrgencias pu: haLogueado) {
				System.out.println(pu.getNombre());
			}
			System.out.println(!haLogueado.isEmpty());
			System.out.println();
			System.out.println();
			
			if (haLogueado.isEmpty()) {
				System.out.println("MALACONTRASENIA");
			} else {
				System.out.println("OK");
			}

			sc.close();
		*/	
	/*		
			Query<PersonalUrgencias> query = sesion.createQuery("FROM PersonalUrgencias ORDER BY id", PersonalUrgencias.class);
			
			List<PersonalUrgencias> personal = query.getResultList();
			for(PersonalUrgencias pu : personal) {
				System.out.println(pu.getId());
			}
	*/		
			//ZoneId zoneid = ZoneId.of("Europe/Spain");
			LocalDateTime localDateTime = LocalDateTime.ofInstant(new Date().toInstant(), ZoneId.systemDefault());
			LocalDateTime startofDay = localDateTime.with(LocalTime.MIN);
			Date date = Date.from(startofDay.atZone(ZoneId.systemDefault()).toInstant());
			System.out.println(date);
		
			
			Query<Evaluacion> query = sesion.createQuery("FROM Evaluacion WHERE fecha >= :date ORDER BY prioridad",
					Evaluacion.class);
			query.setParameter("date", date);

			List<Evaluacion> evaluaciones = query.getResultList();
			for(Evaluacion ev: evaluaciones){
				System.out.println(ev.getId());
			}
			
			
			
	/*
			Date d = new GregorianCalendar(2021, Calendar.APRIL, 1).getTime();
			Query<Evaluacion> query = sesion.createQuery("FROM Evaluacion WHERE fecha >= :date ORDER BY prioridad", Evaluacion.class);
			query.setParameter("date", d);
			
			
			List<Evaluacion> evaluaciones = query.getResultList();
			
			System.out.println("\r\n\r\nEvaluaciones a partir de " + d.toString());
			for(Evaluacion e: evaluaciones) {
				System.out.println(e.getPrioridad());
			}
		*/
		/*
			String dni = "asdasda";
			Query<Paciente> query = sesion.createQuery(
					"FROM Paciente WHERE dni LIKE :denei ORDER BY nombre",
					Paciente.class);
			;
			query.setParameter("denei", dni);

			List<Paciente> paciente = query.getResultList();
			System.out.println(paciente.size());
		*/
		/*
			String nombre = "perez";
			System.out.println(nombre);
	
					
			Query<Paciente> query = sesion.createQuery("FROM Paciente WHERE (lower(nombre) LIKE lower(:name)) ORDER BY nombre", Paciente.class);;
			query.setParameter("name", "%" + nombre + "%");
			
			List<Paciente> pacientes = query.getResultList();
			DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
			for(Paciente p : pacientes) {
				System.out.println(p);
				System.out.println(df.format(p.getF_nac()));
			}
			
	*/
	/*
			String nombre = "Pepa Pérez Rodríguez";
			Calendar c = Calendar.getInstance();
			c.set(1976, 6, 6, 0, 0, 0);
			Date f_nac = c.getTime();
			Query<Paciente> query = sesion.createQuery(
					"FROM Paciente WHERE nombre = :name AND f_nac = :fecha ORDER BY nombre",
					Paciente.class);
			;
			query.setParameter("name", nombre);
			query.setParameter("fecha", f_nac);

			List<Paciente> pacientes = query.getResultList();
			
			for(Paciente pp: pacientes) {
				System.out.println(pp);
			}
	*/
	/*
			String nss = "ABd1235";
			
			Paciente p = sesion.get(Paciente.class, nss);
			
			System.out.println(p);
	*/
			/*
			
			Query<Evaluacion> query = sesion.createQuery("FROM Evaluacion WHERE nssPaciente = :nss", Evaluacion.class);
			query.setParameter("nss", p.getNSS());
			
			List<Evaluacion> evaluaciones = query.getResultList();
			
			for(Evaluacion e : evaluaciones) {
				System.out.println(e.getDolencia());
			}
	*/
			
	/*
			PersonalUrgencias pu = sesion.get(PersonalUrgencias.class, 1);
			
			Query<Evaluacion> query = sesion.createQuery("FROM Evaluacion WHERE idPersonal_Urgencias = :idUrgencias", Evaluacion.class);
			query.setParameter("idUrgencias", pu.getId());
			
			List<Evaluacion> evaluaciones = query.getResultList();
			
			for(Evaluacion e : evaluaciones) {
				System.out.println(e.getDolencia());
			}
			
	*/
			/*
			Query<Object[]> query = sesion.createQuery("SELECT pe.enfermedad, pe.temporalidad FROM Enfermedad AS e INNER JOIN e.pacientes pe"
					+ " WHERE pe.paciente = :name ORDER BY e.nombre");
			query.setParameter("name", p);
			List<Object[]> lista = query.list();
			for(Object[] objetos : lista) {
				Enfermedad e = (Enfermedad) objetos[0];
				String temporalidad = (String) objetos[1];
				System.out.println(nombre + " tiene la enfermedad " + e.getNombre() + " con una temporalidad de " + temporalidad);
			}
	
			System.out.println("--------------------------------------------------------------\r\n\r\n");
			for(PacienteEnfermedad petardo: p.getEnfermedades()) {
				System.out.println(petardo.getEnfermedad().getNombre());
			}*/
			
			
			//Commiteamos la transacción
			System.out.println("Commiteamos la transacción");
			sesion.getTransaction().commit();
			
			System.out.println("FIN");
			
		/*} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		*/}
		finally{
			
			sesion.close();
			
			factoria.close();
		}

	}
	
	
	private static String getString( byte[] bytes ) 
	{
	  StringBuffer sb = new StringBuffer();
	  for( int i=0; i<bytes.length; i++ )     
	  {
	     byte b = bytes[ i ];
	     String hex = Integer.toHexString((int) 0x00FF & b);
	     if (hex.length() == 1) 
	     {
	        sb.append("0");
	     }
	     sb.append( hex );
	  }
	  return sb.toString();
	}

}
