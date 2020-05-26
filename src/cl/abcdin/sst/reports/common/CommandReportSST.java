package cl.abcdin.sst.reports.common;

import java.awt.Color;
import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.export.JRCsvExporter;
import net.sf.jasperreports.engine.export.JRCsvExporterParameter;
import net.sf.jasperreports.engine.export.JRXlsExporter;
import net.sf.jasperreports.engine.export.JRXlsExporterParameter;
import ar.com.fdvs.dj.core.DynamicJasperHelper;
import ar.com.fdvs.dj.core.layout.ClassicLayoutManager;
import ar.com.fdvs.dj.domain.ColumnProperty;
import ar.com.fdvs.dj.domain.DynamicReport;
import ar.com.fdvs.dj.domain.Style;
import ar.com.fdvs.dj.domain.builders.ColumnBuilder;
import ar.com.fdvs.dj.domain.builders.FastReportBuilder;
import ar.com.fdvs.dj.domain.constants.Border;
import ar.com.fdvs.dj.domain.constants.Font;
import ar.com.fdvs.dj.domain.constants.HorizontalAlign;
import ar.com.fdvs.dj.domain.constants.Page;
import ar.com.fdvs.dj.domain.constants.VerticalAlign;
import ar.com.fdvs.dj.domain.entities.columns.AbstractColumn;
import cl.abcdin.sst.dao.ColumnaDAO;
import cl.abcdin.sst.dao.UtilDAO;
import cl.abcdin.sst.model.Columna;
import cl.abcdin.sst.model.filters.FilterColumna;
import cl.abcdin.sst.utils.Constants;
import cl.abcdin.sst.utils.Utils;

public abstract class CommandReportSST extends CommandReport{
	private ColumnaDAO columnaDAO;
	private UtilDAO utilDAO;
	
	@SuppressWarnings("rawtypes")
	protected abstract Collection<AbstractColumn> getAbstractColumns(Map map, HttpServletRequest request) throws Exception;

	@SuppressWarnings("rawtypes")
	protected byte[] getBinaryPDF(Map map, HttpServletRequest request)  throws JRException, Exception {
		JRDataSource dataSource = new JRBeanCollectionDataSource(getData(map, request));
		DynamicReport dynamicReport = this.getDynamicReport(map, request, false);
		JasperPrint jasperPrint = DynamicJasperHelper.generateJasperPrint(dynamicReport, new ClassicLayoutManager(), dataSource);
		return JasperExportManager.exportReportToPdf(jasperPrint);
	}

	@SuppressWarnings("rawtypes")
	protected byte[] getBinaryXLS(Map map, HttpServletRequest request)  throws JRException, Exception {
		JRDataSource dataSource = new JRBeanCollectionDataSource(getData(map, request));
		DynamicReport dynamicReport = this.getDynamicReport(map, request, true);
		JasperPrint jasperPrint = DynamicJasperHelper.generateJasperPrint(dynamicReport, new ClassicLayoutManager(), dataSource);
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		JRXlsExporter exporterXLS = new JRXlsExporter();
	    exporterXLS.setParameter(JRXlsExporterParameter.JASPER_PRINT, jasperPrint);
	    exporterXLS.setParameter(JRXlsExporterParameter.OUTPUT_STREAM, out);
	    exporterXLS.setParameter(JRXlsExporterParameter.MAXIMUM_ROWS_PER_SHEET, Integer.decode("1048576")); // Máximo del Excel 2007
	    exporterXLS.setParameter(JRXlsExporterParameter.IS_DETECT_CELL_TYPE, Boolean.TRUE);
	    exporterXLS.setParameter(JRXlsExporterParameter.IS_REMOVE_EMPTY_SPACE_BETWEEN_ROWS, Boolean.TRUE);
	    exporterXLS.exportReport();
		return out.toByteArray();
	}

	@SuppressWarnings("rawtypes")
	protected byte[] getBinaryCSV(Map map, HttpServletRequest request)  throws JRException, Exception {
		JRDataSource dataSource = new JRBeanCollectionDataSource(getData(map, request));
		DynamicReport dynamicReport = this.getDynamicReport(map, request, true);
		JasperPrint jasperPrint = DynamicJasperHelper.generateJasperPrint(dynamicReport, new ClassicLayoutManager(), dataSource);
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		JRCsvExporter exporterCsv = new JRCsvExporter();
		exporterCsv.setParameter(JRCsvExporterParameter.JASPER_PRINT, jasperPrint);
		exporterCsv.setParameter(JRCsvExporterParameter.OUTPUT_STREAM, out);
		exporterCsv.setParameter(JRCsvExporterParameter.FIELD_DELIMITER, ";");
		exporterCsv.exportReport();
		return out.toByteArray();
	}
	
	@SuppressWarnings("rawtypes")
	public DynamicReport getDynamicReport(Map map, HttpServletRequest request, Boolean ignorePagination) throws Exception {
		FastReportBuilder frb = new FastReportBuilder();
		Integer pageWidth = 80;
		for (Object column : getAbstractColumns(map, request)) {
			if (column instanceof AbstractColumn) {	
				frb.addColumn((AbstractColumn)column);
				pageWidth += ((AbstractColumn)column).getWidth();
			} else if (column instanceof ColumnProperty) {
				frb.addField((ColumnProperty)column);
			}
		}
		frb.setPageSizeAndOrientation(new Page(Page.Page_Legal_Landscape().getHeight(), pageWidth));
		frb.setHeaderHeight(40);
		frb.setTitle("");
		frb.setSubtitle(Utils.formatDate(utilDAO.getDate(), "dd/MM/yyyy HH:mm:ss"));
		frb.setWhenNoDataAllSectionNoDetail();
		frb.setIgnorePagination(ignorePagination);
		DynamicReport dr = frb.build();
		return dr;
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	protected <T> Collection parseAbstractColumns(List<Columna> columnas, Class<T> clazz) throws Exception {
		List abstractColumns = new ArrayList();
		for (Columna columna : columnas) {
			if (columna.getOrden() != null && columna.getPropiedad() != null)
				abstractColumns.add(createAbstractColumn(columna, clazz));
			else
				abstractColumns.add(new ColumnProperty(columna.getPropiedad(), clazz));
		}
		return abstractColumns;
	}
	
	@SuppressWarnings("rawtypes")
	private Class getType(Class clazz, String name) throws Exception {
		//TODO DAR RECURSIVIDAD A LA BUSQUEDA DE CAMPOS EN LA CLASE CUANDO SON CLASES HEREDADAS
		try {
			if (name.indexOf(".") == -1) {
				try {
					return clazz.getDeclaredField(name).getType();
				} catch (Exception e) {
					if(clazz.getSuperclass() != Object.class) {
						return getType(clazz.getSuperclass(), name);						
					} else {
						throw e;
					}
				}
			} else {
				String className = name.substring(0, name.indexOf("."));
				String propName = name.substring(name.indexOf(".") + 1);
				try {
					return getType(clazz.getDeclaredField(className).getType(), propName);
				} catch(Exception e) {
					if(clazz.getSuperclass() != Object.class) {
						return getType(clazz.getSuperclass().getDeclaredField(className).getType(), propName);						
					} else {
						throw e;
					}
				}
			}
		} catch (Exception e) {
			throw e;
		}
	}
	
	private Style getCellStyle(Columna columna) {
		Style style = new Style();
		style.setBorder(new Border(0.5F, Border.BORDER_STYLE_SOLID, Color.BLACK));
		if (columna.getAlinear() == null) {
			style.setHorizontalAlign(HorizontalAlign.CENTER);
		} else if (columna.getAlinear().equals(Constants.COLUMNA_ALIGN_CENTER)) {
			style.setHorizontalAlign(HorizontalAlign.CENTER);
		} else if (columna.getAlinear().equals(Constants.COLUMNA_ALIGN_LEFT)) {
			style.setHorizontalAlign(HorizontalAlign.LEFT);
		} else if (columna.getAlinear().equals(Constants.COLUMNA_ALIGN_RIGHT)) {
			style.setHorizontalAlign(HorizontalAlign.RIGHT);
		} 
		style.setBlankWhenNull(true);
		style.setTransparent(false);
		style.setBackgroundColor(Color.WHITE);
		style.setFont(new Font(9,"Arial",false));
		return style;
	}
	
	private Style getHeaderStyle(Columna columna) {
		Style style = new Style();
		style.setBorder(new Border(0.5F, Border.BORDER_STYLE_SOLID, Color.BLACK));
		style.setHorizontalAlign(HorizontalAlign.CENTER);
		style.setFont(new Font(10,"Arial",true));
		style.setBackgroundColor(Color.gray);
		style.setTransparent(false);
		style.setVerticalAlign(VerticalAlign.MIDDLE);
		return style;
	}
	
	@SuppressWarnings("rawtypes")
	protected <T> AbstractColumn createAbstractColumn(Columna columna, Class<T> clazz) throws Exception {
		Class clz = getType(clazz, columna.getPropiedad());
		Style style = getCellStyle(columna);
		Style styleheader = getHeaderStyle(columna);
		
		AbstractColumn ac = ColumnBuilder.getNew()
				.setColumnProperty(columna.getPropiedad(), clz)
				.setTitle(columna.getNombre())
				.setWidth(columna.getAncho())
				.setStyle(style)
				.setHeaderStyle(styleheader)
				.setCustomExpression(columna.getCustomExpression())
				.setPattern(columna.getFormato())
				.build();
		return ac;
	}
	
	@SuppressWarnings("unchecked")
	protected <T> Collection<AbstractColumn> getAbstractColumns(FilterColumna filterColumna, Class<T> clazz) throws Exception {
		List<Columna> columnas = columnaDAO.listColumnasByFilter(filterColumna);
		return parseAbstractColumns(columnas, clazz);
	}
	
	public ColumnaDAO getColumnaDAO() {
		return columnaDAO;
	}
	
	public void setColumnaDAO(ColumnaDAO columnaDAO) {
		this.columnaDAO = columnaDAO;
	}

	public void setUtilDAO(UtilDAO utilDAO) {
		this.utilDAO = utilDAO;
	}
}