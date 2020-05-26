package cl.abcdin.sst.reports.beans.dummy;

import java.util.ArrayList;
import java.util.Collection;

public class CodigoBarraReport {
	@SuppressWarnings("rawtypes")
	public static Collection getData(){
		
		Collection<String> codigosBarras = new ArrayList<String>();
		
		String codigoBarra="356847";
		codigosBarras.add(codigoBarra);
		return codigosBarras;
	}
	
//	public static void main(String[] args) {
//		@SuppressWarnings("unchecked")
//		Collection<Integer> codigosBarras = getData();
//		for(Integer codigoBarra: codigosBarras){
//			System.out.println(codigoBarra);
//		}
//	}
}
