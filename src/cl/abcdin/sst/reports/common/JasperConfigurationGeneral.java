package cl.abcdin.sst.reports.common;

import java.util.Map;

@SuppressWarnings("rawtypes")
public class JasperConfigurationGeneral {
	private String jasperLocation;
	private Map parameters;
	
	public String getJasperLocation() {
		return jasperLocation;
	}
	public void setJasperLocation(String jrxmlLocation) {
		this.jasperLocation = jrxmlLocation;
	}
	public Map getParameters() {
		return parameters;
	}
	public void setParameters(Map parameters) {
		this.parameters = parameters;
	}
	
	
}
