package cl.abcdin.sst.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Configuraciones {
	
	
        private static Properties props2;
		private String prueba="";
        public static String getString(String key) {
        	
        	System.out.println("metodo configuacionesgetString");
			
                InputStream input = null;
                String resp=null;
                try {
                        if(props2==null){
                        	//Cargar url del dominio 
                        	// RUTA DESARROLLO /root/glassfish3/glassfish/domains/domain1/properties/SST.properties
                        	//  RUTA QA /opt/oracle/app/glassfish/glassfish/domains/domain1/properties/SST.properties
                        	File file = new File("/opt/oracle/app/glassfish/glassfish/domains/domain1/properties/SST.properties");
                                System.out.println(file.getAbsolutePath());
                                props2 = new Properties();
                                props2.load(new FileInputStream(file));
                        }
                        resp=props2.getProperty(key).trim();
                } catch (Exception ex) {
                        ex.printStackTrace();
                        resp='!' + key + '!';
                } finally {
                        if (input != null) {
                                try {
                                        input.close();
                                } catch (IOException e) {
                                        e.printStackTrace();
                                        resp='!' + key + '!';
                                }
                        }
                }
                return resp;

        }
}