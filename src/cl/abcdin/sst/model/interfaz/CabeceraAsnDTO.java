package cl.abcdin.sst.model.interfaz;

import java.io.Serializable;

import cl.abcdin.sst.utils.Constants;

/**
 * DTO Cabecera de la Interfaz de ASN de LogFire.
 * 
 * @author ACL.
 * @date 03-02-2017
 *
 */
public class CabeceraAsnDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4607437349514970807L;

	private String hdrGroupNbr;
	private String shipmentNbr;
	private String facilityCode;
	private String companyCode;
	private String trailerNbr;
	private String actionCode;
	private String refNbr;
	private String shipmentType;
	private String loadNbr;
	private String manifestNbr;
	private String trailerType;
	private String vendorInfo;
	private String originInfo;
	private String originCode;
	private String origShippedUnits;
	private String lockCode;
	private String shippedDate;
	private String origShippedLpns;
	private String custField1;
	private String custField2;
	private String custField3;
	private String custField4;
	private String custField5;
	
	public String getHdrGroupNbr() {
		return hdrGroupNbr;
	}
	public void setHdrGroupNbr(String hdrGroupNbr) {
		this.hdrGroupNbr = hdrGroupNbr;
	}
	public String getShipmentNbr() {
		return shipmentNbr;
	}
	public void setShipmentNbr(String shipmentNbr) {
		this.shipmentNbr = shipmentNbr;
	}
	public String getFacilityCode() {
		return facilityCode;
	}
	public void setFacilityCode(String facilityCode) {
		this.facilityCode = facilityCode;
	}
	public String getCompanyCode() {
		return companyCode;
	}
	public void setCompanyCode(String companyCode) {
		this.companyCode = companyCode;
	}
	public String getTrailerNbr() {
		return trailerNbr;
	}
	public void setTrailerNbr(String trailerNbr) {
		this.trailerNbr = trailerNbr;
	}
	public String getActionCode() {
		return actionCode;
	}
	public void setActionCode(String actionCode) {
		this.actionCode = actionCode;
	}
	public String getRefNbr() {
		return refNbr;
	}
	public void setRefNbr(String refNbr) {
		this.refNbr = refNbr;
	}
	public String getShipmentType() {
		return shipmentType;
	}
	public void setShipmentType(String shipmentType) {
		this.shipmentType = shipmentType;
	}
	public String getLoadNbr() {
		return loadNbr;
	}
	public void setLoadNbr(String loadNbr) {
		this.loadNbr = loadNbr;
	}
	public String getManifestNbr() {
		return manifestNbr;
	}
	public void setManifestNbr(String manifestNbr) {
		this.manifestNbr = manifestNbr;
	}
	public String getTrailerType() {
		return trailerType;
	}
	public void setTrailerType(String trailerType) {
		this.trailerType = trailerType;
	}
	public String getVendorInfo() {
		return vendorInfo;
	}
	public void setVendorInfo(String vendorInfo) {
		this.vendorInfo = vendorInfo;
	}
	public String getOriginInfo() {
		return originInfo;
	}
	public void setOriginInfo(String originInfo) {
		this.originInfo = originInfo;
	}
	public String getOriginCode() {
		return originCode;
	}
	public void setOriginCode(String originCode) {
		this.originCode = originCode;
	}
	public String getOrigShippedUnits() {
		return origShippedUnits;
	}
	public void setOrigShippedUnits(String origShippedUnits) {
		this.origShippedUnits = origShippedUnits;
	}
	public String getLockCode() {
		return lockCode;
	}
	public void setLockCode(String lockCode) {
		this.lockCode = lockCode;
	}
	public String getOrigShippedLpns() {
		return origShippedLpns;
	}
	public void setOrigShippedLpns(String origShippedLpns) {
		this.origShippedLpns = origShippedLpns;
	}
	public String getCustField1() {
		return custField1;
	}
	public void setCustField1(String custField1) {
		this.custField1 = custField1;
	}
	public String getCustField2() {
		return custField2;
	}
	public void setCustField2(String custField2) {
		this.custField2 = custField2;
	}
	public String getCustField3() {
		return custField3;
	}
	public void setCustField3(String custField3) {
		this.custField3 = custField3;
	}
	public String getCustField4() {
		return custField4;
	}
	public void setCustField4(String custField4) {
		this.custField4 = custField4;
	}
	public String getCustField5() {
		return custField5;
	}
	public void setCustField5(String custField5) {
		this.custField5 = custField5;
	}
	public String getShippedDate() {
		return shippedDate;
	}
	public void setShippedDate(String shippedDate) {
		this.shippedDate = shippedDate;
	}
	
	@Override
	public String toString() {
		return "CabeceraAsnDTO [hdrGroupNbr=" + hdrGroupNbr + ", shipmentNbr="
				+ shipmentNbr + ", facilityCode=" + facilityCode
				+ ", companyCode=" + companyCode + ", trailerNbr=" + trailerNbr
				+ ", actionCode=" + actionCode + ", refNbr=" + refNbr
				+ ", shipmentType=" + shipmentType + ", loadNbr=" + loadNbr
				+ ", manifestNbr=" + manifestNbr + ", trailerType="
				+ trailerType + ", vendorInfo=" + vendorInfo + ", originInfo="
				+ originInfo + ", originCode=" + originCode
				+ ", origShippedUnits=" + origShippedUnits + ", lockCode="
				+ lockCode + ", shippedDate=" + shippedDate
				+ ", origShippedLpns=" + origShippedLpns + ", custField1="
				+ custField1 + ", custField2=" + custField2 + ", custField3="
				+ custField3 + ", custField4=" + custField4 + ", custField5="
				+ custField5 + "]";
	}
	
	public String getCabeceraASN() {
		
		StringBuilder str = new StringBuilder();		
		str.append(hdrGroupNbr)
			.append(shipmentNbr).append(Constants.PIPE)
			.append(facilityCode).append(Constants.PIPE)
			.append(companyCode).append(Constants.PIPE)
			.append(trailerNbr).append(Constants.PIPE)
			.append(actionCode).append(Constants.PIPE)
			.append(refNbr).append(Constants.PIPE)
			.append(shipmentType).append(Constants.PIPE)
			.append(loadNbr).append(Constants.PIPE)
			.append(manifestNbr).append(Constants.PIPE)
			.append(trailerType).append(Constants.PIPE)
			.append(vendorInfo).append(Constants.PIPE)
			.append(originInfo).append(Constants.PIPE)
			.append(originCode).append(Constants.PIPE)
			.append(origShippedUnits).append(Constants.PIPE)
			.append(lockCode).append(Constants.PIPE)
			.append(shippedDate).append(Constants.PIPE)
			.append(origShippedLpns).append(Constants.PIPE)
			.append(custField1).append(Constants.PIPE)
			.append(custField2).append(Constants.PIPE)
			.append(custField3).append(Constants.PIPE)
			.append(custField4).append(Constants.PIPE)
			.append(custField5);
	
		return str.toString();
	}
}
