package cl.abcdin.sst.applets;

import java.net.MalformedURLException;
import java.net.URL;

import javax.print.Doc;
import javax.print.DocFlavor;
import javax.print.DocPrintJob;
import javax.print.PrintException;
import javax.print.PrintService;
import javax.print.PrintServiceLookup;
import javax.print.SimpleDoc;
import javax.print.attribute.PrintServiceAttribute;
import javax.print.attribute.standard.PrinterName;
import javax.swing.JApplet;

public class CodigoBarrasApplet extends JApplet {

	private static final long serialVersionUID = 1L;
	
	public void print(String codigoBarras) {
		try {
			PrintService[] printServices = PrintServiceLookup.lookupPrintServices(null, null);
			PrintService psLabel = null;
			
			System.out.println("*** Impresoras encontradas => " + printServices.length);
			for (PrintService printService : printServices) {
				PrintServiceAttribute attr = printService.getAttribute(PrinterName.class);
				System.out.println("*** Impresora => " + ((PrinterName)attr).getValue());			
				if (((PrinterName)attr).getValue().toLowerCase().indexOf("accumax") != -1) {
					psLabel = printService;
					System.out.println("*** Impresora Seleccionada => " + ((PrinterName)attr).getValue());
					break;
				}
			}
			
			if (psLabel == null) {
				System.err.println("*** ERROR => Impresora inexistente");
				return;
			}

			System.out.println("*** Abriendo trabajo de impresora con =>" + psLabel.getName());
			DocPrintJob job = psLabel.createPrintJob();
			System.out.println("*** Trabajo Abierto =>" + job.getClass());
			
			String s =	"" +
						"^@20,3\n"+
						"^W30\n"+
						"^H10\n"+
						"^P1\n"+
						"^S4\n"+
						"^AT\n"+
						"^C1\n"+
						"^R1\n"+
						"~Q+1\n"+
						"^O0\n"+
						"^D0\n"+
						"^E12\n"+
						"~R200\n"+
						"^%\n"+
						"Dy2-me-dd\n"+
						"Th:m:s\n"+
						"BA,42,36,1,2,60,0,0,"+codigoBarras+"\n"+
						"AT,71,100,25,25,0,0,0,0,"+codigoBarras+"\n"+
						"$\n";

			System.out.println("*** Codigo => " + s);
			
			byte[] by = s.getBytes();
			System.out.println("*** Bytes =>" + by);
			
			DocFlavor flavor = DocFlavor.BYTE_ARRAY.AUTOSENSE;
			Doc doc = new SimpleDoc(by, flavor, null);

			System.out.println("*** Enviando a Impresora");
			System.out.println("*** Documento a Imprimir =>" + doc.getClass());
			job.print(doc, null);
			System.out.println("*** Enviado");
		} catch (PrintException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void init() {
		String codigoBarras = getParameter("codigoBarras");
		System.out.println("*** Barcode => " + codigoBarras);
		print(codigoBarras);
//		JSObject win = (JSObject) JSObject.getWindow(this);
//		win.eval("window.close();");
		try {
			getAppletContext().showDocument(new URL("javascript:exit()"));
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
	}
}
