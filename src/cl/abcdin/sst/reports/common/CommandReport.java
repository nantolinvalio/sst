package cl.abcdin.sst.reports.common;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.util.Collection;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRParameter;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.JasperRunManager;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.export.JRCsvExporter;
import net.sf.jasperreports.engine.export.JRCsvExporterParameter;
import net.sf.jasperreports.engine.export.JRXlsExporter;
import net.sf.jasperreports.engine.export.JRXlsExporterParameter;
import net.sf.jasperreports.engine.util.JRLoader;

@SuppressWarnings({ "unchecked", "rawtypes" })
public abstract class CommandReport {
	private JasperConfigurationGeneral jasperConfigurationGeneral;
	
	protected Boolean ignorePagination = Boolean.FALSE;
	protected abstract Collection getData(Map map, HttpServletRequest request) throws Exception; // Sera implementado por la clase concreta

	public byte[] executePDF(Map map, HttpServletRequest request) throws JRException, Exception {
		return getBinaryPDF(map, request);
	}

	public byte[] executeXLS(Map map, HttpServletRequest request) throws JRException, Exception {
		return getBinaryXLS(map, request);
	}

	public byte[] executeCSV(Map map, HttpServletRequest request) throws JRException, Exception {
		return getBinaryCSV(map, request);
	}

	protected byte[] getBinaryPDF(Map map, HttpServletRequest request)  throws JRException, Exception {
		JRDataSource dataSource = new JRBeanCollectionDataSource(getData(map, request));
		JasperReport jasperReport = this.getJasperReport(map, request);
		Map parameters = jasperConfigurationGeneral.getParameters();
		parameters.put(JRParameter.IS_IGNORE_PAGINATION, ignorePagination);
		return JasperRunManager.runReportToPdf(jasperReport, parameters, dataSource);
	}

	protected byte[] getBinaryXLS(Map map, HttpServletRequest request)  throws JRException, Exception {
		JRDataSource dataSource = new JRBeanCollectionDataSource(getData(map, request));
		JasperReport jasperReport = this.getJasperReport(map, request);
		JasperPrint print = JasperFillManager.fillReport(jasperReport,  jasperConfigurationGeneral.getParameters(), dataSource);
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		JRXlsExporter exporterXLS = new JRXlsExporter();
	    exporterXLS.setParameter(JRXlsExporterParameter.JASPER_PRINT, print);
	    exporterXLS.setParameter(JRXlsExporterParameter.OUTPUT_STREAM, out);
	    exporterXLS.setParameter(JRXlsExporterParameter.MAXIMUM_ROWS_PER_SHEET, Integer.decode("100000")); // Máximo del Excel 2007
	    exporterXLS.setParameter(JRXlsExporterParameter.IS_DETECT_CELL_TYPE, Boolean.TRUE);
	    exporterXLS.setParameter(JRXlsExporterParameter.IS_REMOVE_EMPTY_SPACE_BETWEEN_ROWS, Boolean.TRUE);
		return out.toByteArray();
	}

	protected byte[] getBinaryCSV(Map map, HttpServletRequest request)  throws JRException, Exception {
		JRDataSource dataSource = new JRBeanCollectionDataSource(getData(map, request));
		JasperReport jasperReport = this.getJasperReport(map, request);
		JasperPrint print = JasperFillManager.fillReport(jasperReport,  jasperConfigurationGeneral.getParameters(), dataSource);
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		JRCsvExporter exporterCsv = new JRCsvExporter();
		exporterCsv.setParameter(JRCsvExporterParameter.JASPER_PRINT, print);
		exporterCsv.setParameter(JRCsvExporterParameter.OUTPUT_STREAM, out);
		exporterCsv.setParameter(JRCsvExporterParameter.FIELD_DELIMITER, ";");
		exporterCsv.exportReport();
		return out.toByteArray();
	}

 	public JasperReport getJasperReport(Map map, HttpServletRequest request) throws JRException, Exception {
		InputStream inputStream = JRLoader.getResourceInputStream(jasperConfigurationGeneral.getJasperLocation() + getData("report",map) + ".jasper");
		JasperReport jasperReport = (JasperReport)JRLoader.loadObject(inputStream);
		return jasperReport;
	}
 	 
	protected String getData(String key, Map params) {
		String[] data = (String[])params.get(key);
		if (data == null) return "";
		return data[0];
	}

	protected String[] getArrayData(String key, Map params) {
		String[] data = (String[])params.get(key);
		return data;
	}
	
	public JasperConfigurationGeneral getJasperConfigurationGeneral() {
		return jasperConfigurationGeneral;
	}

	public void setJasperConfigurationGeneral(JasperConfigurationGeneral jasperConfigurationGeneral) {
		this.jasperConfigurationGeneral = jasperConfigurationGeneral;
	}
}
