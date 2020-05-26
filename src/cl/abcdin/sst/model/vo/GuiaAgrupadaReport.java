package cl.abcdin.sst.model.vo;

import java.util.List;

import cl.abcdin.sst.model.Guia;

public class GuiaAgrupadaReport {
	private Guia guia;
	private List<ProductoReport> prodcutosReport;
	public Guia getGuia() {
		return guia;
	}
	public void setGuia(Guia guia) {
		this.guia = guia;
	}
	public List<ProductoReport> getProdcutosReport() {
		return prodcutosReport;
	}
	public void setProdcutosReport(List<ProductoReport> prodcutosReport) {
		this.prodcutosReport = prodcutosReport;
	}
}
