package com.spring.modelo.dao;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.spring.modelo.entidades.PersonalUrgencias;

@Repository
public class PersonalUrgenciasLoginDAOImplementacion implements PersonalUrgenciasLoginDAO{

	/**
	 * La factoría de la que cogemos las sesiones La anotación de @Autowired sirve
	 * para inyectar dependencias
	 */
	@Autowired
	private SessionFactory factoria;
	
	@Override
	public List<PersonalUrgencias> login(String username, String pwd) throws NoSuchAlgorithmException, UnsupportedEncodingException {
		Session sesion = factoria.getCurrentSession();

		
		MessageDigest md5 = MessageDigest.getInstance("MD5");
		byte[] hashInOne = md5.digest(pwd.getBytes("UTF-8"));
		String hashMD5String = getString(hashInOne);
		
		
		Query<PersonalUrgencias> query2 = sesion.createQuery(
				"FROM PersonalUrgencias WHERE contrasenia=: pwd AND usuario =:user", PersonalUrgencias.class);
		query2.setParameter("user", username);
		query2.setParameter("pwd", hashMD5String);
		List<PersonalUrgencias> log = query2.getResultList();
		return log;
		//return !haLogueado.isEmpty();
	}

	@Override
	public void cambiarContrasenia(String username, String newpwd) {
		// TODO Auto-generated method stub
		
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
