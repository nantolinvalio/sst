package cl.abcdin.sst.model.interfaz;

import java.io.Serializable;

import cl.abcdin.sst.utils.Constants;

/**
 * DTO Principal de la Interfaz de Ajuste de PMM.
 * 
 * @author ACL
 * @date 03-02-2017
 *
 */
public class AjustePmmDTO implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private long techKey;
	private long orgLvlNumber;
	private String prdLvlNumber;
	private String toPrdLvlNbr;
	private String dateCreated;
	private String fromInvType;
	private String toInvType;
	private String invMrptCode;
	private String invDrptCode;
	private String dcQuantity;
	private String jdaQuantity;
	private String actionFlag;
	private String entryDate;
	private String downloadDate1;
	private String downloadDate2;
	private String prdSllUom;
	private String fromInnerPackId;
	private String toInnerPackId;
	private String toMrptCode;
	private String toDrptCode;
	private int boletaNumber; //JADM MOD 1 AGREGA ATRIBUTO REF1
	private Long otNumber; //JADM MOD 2 AGREGA ATRIBUTO REF2
	private long batchNum;
	/**
	 * @return the techKey
	 */
	public long getTechKey() {
		return techKey;
	}
	/**
	 * @param techKey the techKey to set
	 */
	public void setTechKey(final long techKey) {
		this.techKey = techKey;
	}
	/**
	 * @return the orgLvlNumber
	 */
	public long getOrgLvlNumber() {
		return orgLvlNumber;
	}
	/**
	 * @param orgLvlNumber the orgLvlNumber to set
	 */
	public void setOrgLvlNumber(final long orgLvlNumber) {
		this.orgLvlNumber = orgLvlNumber;
	}
	/**
	 * @return the prdLvlNumber
	 */
	public String getPrdLvlNumber() {
		return prdLvlNumber;
	}
	/**
	 * @param prdLvlNumber the prdLvlNumber to set
	 */
	public void setPrdLvlNumber(final String prdLvlNumber) {
		this.prdLvlNumber = prdLvlNumber;
	}
	/**
	 * @return the toPrdLvlNbr
	 */
	public String getToPrdLvlNbr() {
		return toPrdLvlNbr;
	}
	/**
	 * @param toPrdLvlNbr the toPrdLvlNbr to set
	 */
	public void setToPrdLvlNbr(final String toPrdLvlNbr) {
		this.toPrdLvlNbr = toPrdLvlNbr;
	}
	/**
	 * @return the dateCreated
	 */
	public String getDateCreated() {
		return dateCreated;
	}
	/**
	 * @param dateCreated the dateCreated to set
	 */
	public void setDateCreated(final String dateCreated) {
		this.dateCreated = dateCreated;
	}
	/**
	 * @return the fromInvType
	 */
	public String getFromInvType() {
		return fromInvType;
	}
	/**
	 * @param fromInvType the fromInvType to set
	 */
	public void setFromInvType(final String fromInvType) {
		this.fromInvType = fromInvType;
	}
	/**
	 * @return the toInvType
	 */
	public String getToInvType() {
		return toInvType;
	}
	/**
	 * @param toInvType the toInvType to set
	 */
	public void setToInvType(final String toInvType) {
		this.toInvType = toInvType;
	}
	/**
	 * @return the invMrptCode
	 */
	public String getInvMrptCode() {
		return invMrptCode;
	}
	/**
	 * @param invMrptCode the invMrptCode to set
	 */
	public void setInvMrptCode(final String invMrptCode) {
		this.invMrptCode = invMrptCode;
	}
	/**
	 * @return the invDrptCode
	 */
	public String getInvDrptCode() {
		return invDrptCode;
	}
	/**
	 * @param invDrptCode the invDrptCode to set
	 */
	public void setInvDrptCode(final String invDrptCode) {
		this.invDrptCode = invDrptCode;
	}
	/**
	 * @return the dcQuantity
	 */
	public String getDcQuantity() {
		return dcQuantity;
	}
	/**
	 * @param dcQuantity the dcQuantity to set
	 */
	public void setDcQuantity(String dcQuantity) {
		this.dcQuantity = dcQuantity;
	}
	/**
	 * @return the jdaQuantity
	 */
	public String getJdaQuantity() {
		return jdaQuantity;
	}
	/**
	 * @param jdaQuantity the jdaQuantity to set
	 */
	public void setJdaQuantity(String jdaQuantity) {
		this.jdaQuantity = jdaQuantity;
	}
	/**
	 * @return the actionFlag
	 */
	public String getActionFlag() {
		return actionFlag;
	}
	/**
	 * @param actionFlag the actionFlag to set
	 */
	public void setActionFlag(final String actionFlag) {
		this.actionFlag = actionFlag;
	}
	/**
	 * @return the entryDate
	 */
	public String getEntryDate() {
		return entryDate;
	}
	/**
	 * @param entryDate the entryDate to set
	 */
	public void setEntryDate(final String entryDate) {
		this.entryDate = entryDate;
	}
	/**
	 * @return the downloadDate1
	 */
	public String getDownloadDate1() {
		return downloadDate1;
	}
	/**
	 * @param downloadDate1 the downloadDate1 to set
	 */
	public void setDownloadDate1(final String downloadDate1) {
		this.downloadDate1 = downloadDate1;
	}
	/**
	 * @return the downloadDate2
	 */
	public String getDownloadDate2() {
		return downloadDate2;
	}
	/**
	 * @param downloadDate2 the downloadDate2 to set
	 */
	public void setDownloadDate2(final String downloadDate2) {
		this.downloadDate2 = downloadDate2;
	}
	/**
	 * @return the prdSllUom
	 */
	public String getPrdSllUom() {
		return prdSllUom;
	}
	/**
	 * @param prdSllUom the prdSllUom to set
	 */
	public void setPrdSllUom(final String prdSllUom) {
		this.prdSllUom = prdSllUom;
	}
	/**
	 * @return the fromInnerPackId
	 */
	public String getFromInnerPackId() {
		return fromInnerPackId;
	}
	/**
	 * @param fromInnerPackId the fromInnerPackId to set
	 */
	public void setFromInnerPackId(final String fromInnerPackId) {
		this.fromInnerPackId = fromInnerPackId;
	}
	/**
	 * @return the toInnerPackId
	 */
	public String getToInnerPackId() {
		return toInnerPackId;
	}
	/**
	 * @param toInnerPackId the toInnerPackId to set
	 */
	public void setToInnerPackId(final String toInnerPackId) {
		this.toInnerPackId = toInnerPackId;
	}
	/**
	 * @return the toMrptCode
	 */
	public String getToMrptCode() {
		return toMrptCode;
	}
	/**
	 * @param toMrptCode the toMrptCode to set
	 */
	public void setToMrptCode(final String toMrptCode) {
		this.toMrptCode = toMrptCode;
	}
	/**
	 * @return the toDrptCode
	 */
	public String getToDrptCode() {
		return toDrptCode;
	}
	/**
	 * @param toDrptCode the toDrptCode to set
	 */
	public void setToDrptCode(final String toDrptCode) {
		this.toDrptCode = toDrptCode;
	}
	/**
	 * @return the batchNum
	 */
	public long getBatchNum() {
		return batchNum;
	}
	/**
	 * @param batchNum the batchNum to set
	 */
	public void setBatchNum(final long batchNum) {
		this.batchNum = batchNum;
	}
	/**
	 * @return the boletaNumber
	 */
	public int getBoletaNumber() {  //JADM MOD 3 GREGA METODOS G/S INICIO
		return boletaNumber;
	}
	/**
	 * @param boletaNumber the boletaNumber to set
	 */
	public void setBoletaNumber(int boletaNumber) {
		this.boletaNumber = boletaNumber;
	}
	/**
	 * @return the otNumber
	 */
	public Long getOtNumber() {
		return otNumber;
	}
	/**
	 * @param otNumber the otNumber to set
	 */
	public void setOtNumber(Long otNumber) { //JADM MOD 3 AGREGA METODOS G/S FIN
		this.otNumber = otNumber;
	}
	@Override
	public String toString() {
		return "AjustePmmDTO [techKey=" + techKey + ", orgLvlNumber="
				+ orgLvlNumber + ", prdLvlNumber=" + prdLvlNumber
				+ ", toPrdLvlNbr=" + toPrdLvlNbr + ", dateCreated="
				+ dateCreated + ", fromInvType=" + fromInvType + ", toInvType="
				+ toInvType + ", invMrptCode=" + invMrptCode + ", invDrptCode="
				+ invDrptCode + ", dcQuantity=" + dcQuantity + ", jdaQuantity="
				+ jdaQuantity + ", actionFlag=" + actionFlag + ", entryDate="
				+ entryDate + ", downloadDate1=" + downloadDate1
				+ ", downloadDate2=" + downloadDate2 + ", prdSllUom="
				+ prdSllUom + ", fromInnerPackId=" + fromInnerPackId
				+ ", toInnerPackId=" + toInnerPackId + ", toMrptCode="
				+ toMrptCode + ", toDrptCode=" + toDrptCode + ", batchNum="
				+ batchNum + 
				", boletaNumber=" + boletaNumber + ", otnumber=" + otNumber + "]"; //JADM MOD 4 AGREGA ELEMENTOS
	}
	
	public String getLineaAjuste() {
		StringBuilder str = new StringBuilder();
		str.append(techKey)
				.append(Constants.PIPE).append(orgLvlNumber)
				.append(Constants.PIPE).append(prdLvlNumber)
				.append(Constants.PIPE).append(toPrdLvlNbr)
				.append(Constants.PIPE).append(dateCreated)
				.append(Constants.PIPE).append(fromInvType)
				.append(Constants.PIPE).append(toInvType == null ? "" : toInvType)
				.append(Constants.PIPE).append(invMrptCode)
				.append(Constants.PIPE).append(invDrptCode == null ? "" : invDrptCode)
				.append(Constants.PIPE).append(dcQuantity)
				.append(Constants.PIPE).append(jdaQuantity == null ? "" : jdaQuantity)
				.append(Constants.PIPE).append(actionFlag)
				.append(Constants.PIPE).append(entryDate)
				.append(Constants.PIPE).append(downloadDate1)
				.append(Constants.PIPE).append(downloadDate2)
				.append(Constants.PIPE).append(prdSllUom)
				.append(Constants.PIPE).append(fromInnerPackId)
				.append(Constants.PIPE).append(toInnerPackId)
				.append(Constants.PIPE).append(toMrptCode)
				.append(Constants.PIPE).append(toDrptCode)
				.append(Constants.PIPE).append(batchNum)
				.append(Constants.PIPE).append(boletaNumber) //JADM MOD 5 AGREGA ATRIBUTOS
				.append(Constants.PIPE).append(otNumber);    //JADM MOD 6 AGREGA ARTIBUTOS
		return str.toString();
	}
}
