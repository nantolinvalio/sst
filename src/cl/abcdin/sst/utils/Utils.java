package cl.abcdin.sst.utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Utils {
	
	public static String getIndicadorColor(Integer i) {
		if (null ==  i) {
			return "";
		}
		switch (i){
			case 1:
				return "1- Verde";
			case 2:
				return "2- Verde Claro";
			case 3:
				return "3- Amarillo";
			case 4:
				return "4- Rojo";
			default: 
				return "0";
		}
	}
	
	public static String getIndicadorColor(String i) {
		return getIndicadorColor(Integer.parseInt(i));
	}
	
	public static Long getIdFromRun(String rut) throws Exception {
		if (rut == null || rut.equals(""))
			return null;
		Pattern p = Pattern.compile("^([\\d]+)-([\\d|K|k]){1}$");
		Matcher m = p.matcher(rut);
		if (!m.find())
			return null;
		return Long.parseLong(rut.split("\\-")[0]);
	}
	
	public static Long getIdFromRunNoValidate(String rut) throws Exception {
		if (rut == null || rut.equals("")) {
			return null;
		}
		return Long.parseLong(rut.split("\\-")[0]);
	}
	
	public static String getRunFromId(Long id) throws Exception {
		if (id == null)
			return null;
		if (id < 0)
			return id.toString();
		String run = Long.toString(id);
		Integer resp = 0;
		Integer factor = 2;
		for (int i = 1; i <= run.length(); i++) {
			resp += factor * Integer.parseInt(Character.toString(run.charAt(run.length() - i)));
			factor = factor == 7 ? 2 : factor + 1 ; 
		}
		resp = 11 - (resp % 11);
		return run + "-" + (resp == 10 ? "K": resp == 11 ? "0" : resp);
	}
	
	public static Date addDaysToDate(Date fecha, Integer days) throws Exception {
		Calendar cal = Calendar.getInstance();
		cal.setTime(fecha);
		cal.add(Calendar.DAY_OF_MONTH, days);
		return cal.getTime();
	}
	
	public static Date subtractDaysToDate(Date fecha, Integer days) throws Exception {
		Calendar cal = Calendar.getInstance();
		cal.setTime(fecha);
		cal.add(Calendar.DAY_OF_MONTH, days*-1);
		return cal.getTime();
	}
	
	public static String formatFechaXperts(Date fecha) throws Exception {
		SimpleDateFormat sdf = new SimpleDateFormat(Constants.FORMATO_FECHA_XPERTS);
		if (fecha == null) return "";
		return sdf.format(fecha);
	}
	
	public static String formatDate(Date fecha, String format) throws Exception {
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		return sdf.format(fecha);
	}
	
	public static String getMD5(String md5) throws Exception {
		try {
			java.security.MessageDigest md = java.security.MessageDigest.getInstance("MD5");
			byte[] array = md.digest(md5.getBytes());
			StringBuffer sb = new StringBuffer();
			for (int i = 0; i < array.length; ++i) {
				sb.append(Integer.toHexString((array[i] & 0xFF) | 0x100).substring(1,3));
			}
			return sb.toString();
		} catch (java.security.NoSuchAlgorithmException e) {
			throw e;
		}
	}
	
}
