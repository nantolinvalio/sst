package cl.abcdin.sst.model.interfaz;

import java.io.Serializable;
import java.util.Date;

import cl.abcdin.sst.utils.Constants;

/**
 * 
 * 
 * <p>
 * Registro de versiones:
 * <ul>
 * <li>1.0 XX/YY/2017, (ACL SPA) - versión inicial
 * </ul>
 * <p>
 * DTO Cabecera de la Interfaz de Orden de LogFire.
 * 
 * <p>
 * <B>Todos los derechos reservados por ACL SPA .</B>
 */
public class CabeceraOrdenDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -9147189843109739531L;

	private String hdrGroupNbr;
	private String facilityCode;
	private String companyCode;
	private String orderNbr;
	private String orderType;
	private String ordDate;
	private String expDate;
	private String reqShipDate;
	private String destFacilityCode;
	private String custName;
	private String custAddr;
	private String custAddr2;
	private String custAddr3;
	private String refNbr;
	private String actionCode;
	private String routeNbr;
	private String custCity;
	private String custState;
	private String custZip;
	private String custCountry;
	private String custPhoneNbr;
	private String custEmail;
	private String custContact;
	private String custNbr;
	private String shiptoFacilityCode;
	private String shiptoName;
	private String shiptoAddr;
	private String shiptoAddr2;
	private String shiptoAddr3;
	private String shiptoCity;
	private String shiptoState;
	private String shiptoZip;
	private String shiptoCountry;
	private String shiptoPhoneNbr;
	private String shiptoEmail;
	private String shiptoContact;
	private String destCompanyCode;
	private String priority;
	private String shipViaCode;
	private String carrierAccountNbr;
	private String paymentMethod;
	private String hostAllocationNbr;
	private String customerPoNbr;
	private String salesOrderNbr;
	private String salesChannel;
	private String destDeptNbr;
	private String startShipDate;
	private String stopShipDate;
	private String splInstr;
	private String vasGroupCode;
	private String currencyCode;
	private String stageLocationBarcode;
	private String custField1;
	private String custField2;
	private String custField3;
	private String custField4;
	private String custField5;
	private String obLpnType;
	private String giftMsg;
	private String schedShipDate;
	private String customerPoType;
	private String customerVendorCode;
	private String custDate1;
	private String custDate2;
	private String custDate3;
	private String custDate4;
	private String custDate5;
	private String custNumerico1;
	private String custNumerico2;
	private String custNumerico3;
	private String custNumerico4;
	private String custNumerico5;
	private String custDecimal1;
	private String custDecimal2;
	private String custDecimal3;
	private String custDecimal4;
	private String custDecimal5;
	private String custShortText1;
	private String custShortText2;
	private String custShortText3;
	private String custShortText4;
	private String custShortText5;
	private String custShortText6;
	private String custShortText7;
	private String custShortText8;
	private String custShortText9;
	private String custShortText10;
	private String custShortText11;
	private String custShortText12;
	private String custLongText1;
	private String custLongText2;
	private String custLongText3;
	private String orderNbrToReplace;

	public String getHdrGroupNbr() {
		return hdrGroupNbr;
	}

	public void setHdrGroupNbr(String hdrGroupNbr) {
		this.hdrGroupNbr = hdrGroupNbr;
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

	public String getOrderNbr() {
		return orderNbr;
	}

	public void setOrderNbr(String orderNbr) {
		this.orderNbr = orderNbr;
	}

	public String getOrderType() {
		return orderType;
	}

	public void setOrderType(String orderType) {
		this.orderType = orderType;
	}

	public String getOrdDate() {
		return ordDate;
	}

	public void setOrdDate(String ordDate) {
		this.ordDate = ordDate;
	}

	public String getExpDate() {
		return expDate;
	}

	public void setExpDate(String expDate) {
		this.expDate = expDate;
	}

	public String getReqShipDate() {
		return reqShipDate;
	}

	public void setReqShipDate(String reqShipDate) {
		this.reqShipDate = reqShipDate;
	}

	public String getDestFacilityCode() {
		return destFacilityCode;
	}

	public void setDestFacilityCode(String destFacilityCode) {
		this.destFacilityCode = destFacilityCode;
	}

	public String getCustName() {
		return custName;
	}

	public void setCustName(String custName) {
		this.custName = custName;
	}

	public String getCustAddr() {
		return custAddr;
	}

	public void setCustAddr(String custAddr) {
		this.custAddr = custAddr;
	}

	public String getCustAddr2() {
		return custAddr2;
	}

	public void setCustAddr2(String custAddr2) {
		this.custAddr2 = custAddr2;
	}

	public String getCustAddr3() {
		return custAddr3;
	}

	public void setCustAddr3(String custAddr3) {
		this.custAddr3 = custAddr3;
	}

	public String getRefNbr() {
		return refNbr;
	}

	public void setRefNbr(String refNbr) {
		this.refNbr = refNbr;
	}

	public String getActionCode() {
		return actionCode;
	}

	public void setActionCode(String actionCode) {
		this.actionCode = actionCode;
	}

	public String getRouteNbr() {
		return routeNbr;
	}

	public void setRouteNbr(String routeNbr) {
		this.routeNbr = routeNbr;
	}

	public String getCustCity() {
		return custCity;
	}

	public void setCustCity(String custCity) {
		this.custCity = custCity;
	}

	public String getCustState() {
		return custState;
	}

	public void setCustState(String custState) {
		this.custState = custState;
	}

	public String getCustZip() {
		return custZip;
	}

	public void setCustZip(String custZip) {
		this.custZip = custZip;
	}

	public String getCustCountry() {
		return custCountry;
	}

	public void setCustCountry(String custCountry) {
		this.custCountry = custCountry;
	}

	public String getCustPhoneNbr() {
		return custPhoneNbr;
	}

	public void setCustPhoneNbr(String custPhoneNbr) {
		this.custPhoneNbr = custPhoneNbr;
	}

	public String getCustEmail() {
		return custEmail;
	}

	public void setCustEmail(String custEmail) {
		this.custEmail = custEmail;
	}

	public String getCustContact() {
		return custContact;
	}

	public void setCustContact(String custContact) {
		this.custContact = custContact;
	}

	public String getCustNbr() {
		return custNbr;
	}

	public void setCustNbr(String custNbr) {
		this.custNbr = custNbr;
	}

	public String getShiptoFacilityCode() {
		return shiptoFacilityCode;
	}

	public void setShiptoFacilityCode(String shiptoFacilityCode) {
		this.shiptoFacilityCode = shiptoFacilityCode;
	}

	public String getShiptoName() {
		return shiptoName;
	}

	public void setShiptoName(String shiptoName) {
		this.shiptoName = shiptoName;
	}

	public String getShiptoAddr() {
		return shiptoAddr;
	}

	public void setShiptoAddr(String shiptoAddr) {
		this.shiptoAddr = shiptoAddr;
	}

	public String getShiptoAddr2() {
		return shiptoAddr2;
	}

	public void setShiptoAddr2(String shiptoAddr2) {
		this.shiptoAddr2 = shiptoAddr2;
	}

	public String getShiptoAddr3() {
		return shiptoAddr3;
	}

	public void setShiptoAddr3(String shiptoAddr3) {
		this.shiptoAddr3 = shiptoAddr3;
	}

	public String getShiptoCity() {
		return shiptoCity;
	}

	public void setShiptoCity(String shiptoCity) {
		this.shiptoCity = shiptoCity;
	}

	public String getShiptoState() {
		return shiptoState;
	}

	public void setShiptoState(String shiptoState) {
		this.shiptoState = shiptoState;
	}

	public String getShiptoZip() {
		return shiptoZip;
	}

	public void setShiptoZip(String shiptoZip) {
		this.shiptoZip = shiptoZip;
	}

	public String getShiptoCountry() {
		return shiptoCountry;
	}

	public void setShiptoCountry(String shiptoCountry) {
		this.shiptoCountry = shiptoCountry;
	}

	public String getShiptoPhoneNbr() {
		return shiptoPhoneNbr;
	}

	public void setShiptoPhoneNbr(String shiptoPhoneNbr) {
		this.shiptoPhoneNbr = shiptoPhoneNbr;
	}

	public String getShiptoEmail() {
		return shiptoEmail;
	}

	public void setShiptoEmail(String shiptoEmail) {
		this.shiptoEmail = shiptoEmail;
	}

	public String getShiptoContact() {
		return shiptoContact;
	}

	public void setShiptoContact(String shiptoContact) {
		this.shiptoContact = shiptoContact;
	}

	public String getDestCompanyCode() {
		return destCompanyCode;
	}

	public void setDestCompanyCode(String destCompanyCode) {
		this.destCompanyCode = destCompanyCode;
	}

	public String getPriority() {
		return priority;
	}

	public void setPriority(String priority) {
		this.priority = priority;
	}

	public String getShipViaCode() {
		return shipViaCode;
	}

	public void setShipViaCode(String shipViaCode) {
		this.shipViaCode = shipViaCode;
	}

	public String getCarrierAccountNbr() {
		return carrierAccountNbr;
	}

	public void setCarrierAccountNbr(String carrierAccountNbr) {
		this.carrierAccountNbr = carrierAccountNbr;
	}

	public String getPaymentMethod() {
		return paymentMethod;
	}

	public void setPaymentMethod(String paymentMethod) {
		this.paymentMethod = paymentMethod;
	}

	public String getHostAllocationNbr() {
		return hostAllocationNbr;
	}

	public void setHostAllocationNbr(String hostAllocationNbr) {
		this.hostAllocationNbr = hostAllocationNbr;
	}

	public String getCustomerPoNbr() {
		return customerPoNbr;
	}

	public void setCustomerPoNbr(String customerPoNbr) {
		this.customerPoNbr = customerPoNbr;
	}

	public String getSalesOrderNbr() {
		return salesOrderNbr;
	}

	public void setSalesOrderNbr(String salesOrderNbr) {
		this.salesOrderNbr = salesOrderNbr;
	}

	public String getSalesChannel() {
		return salesChannel;
	}

	public void setSalesChannel(String salesChannel) {
		this.salesChannel = salesChannel;
	}

	public String getDestDeptNbr() {
		return destDeptNbr;
	}

	public void setDestDeptNbr(String destDeptNbr) {
		this.destDeptNbr = destDeptNbr;
	}

	public String getStartShipDate() {
		return startShipDate;
	}

	public void setStartShipDate(String startShipDate) {
		this.startShipDate = startShipDate;
	}

	public String getStopShipDate() {
		return stopShipDate;
	}

	public void setStopShipDate(String stopShipDate) {
		this.stopShipDate = stopShipDate;
	}

	public String getSplInstr() {
		return splInstr;
	}

	public void setSplInstr(String splInstr) {
		this.splInstr = splInstr;
	}

	public String getVasGroupCode() {
		return vasGroupCode;
	}

	public void setVasGroupCode(String vasGroupCode) {
		this.vasGroupCode = vasGroupCode;
	}

	public String getCurrencyCode() {
		return currencyCode;
	}

	public void setCurrencyCode(String currencyCode) {
		this.currencyCode = currencyCode;
	}

	public String getStageLocationBarcode() {
		return stageLocationBarcode;
	}

	public void setStageLocationBarcode(String stageLocationBarcode) {
		this.stageLocationBarcode = stageLocationBarcode;
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

	public String getObLpnType() {
		return obLpnType;
	}

	public void setObLpnType(String obLpnType) {
		this.obLpnType = obLpnType;
	}

	public String getGiftMsg() {
		return giftMsg;
	}

	public void setGiftMsg(String giftMsg) {
		this.giftMsg = giftMsg;
	}

	public String getSchedShipDate() {
		return schedShipDate;
	}

	public void setSchedShipDate(String schedShipDate) {
		this.schedShipDate = schedShipDate;
	}

	public String getCustomerPoType() {
		return customerPoType;
	}

	public void setCustomerPoType(String customerPoType) {
		this.customerPoType = customerPoType;
	}

	public String getCustomerVendorCode() {
		return customerVendorCode;
	}

	public void setCustomerVendorCode(String customerVendorCode) {
		this.customerVendorCode = customerVendorCode;
	}

	public String getCustDate1() {
		return custDate1;
	}

	public void setCustDate1(String custDate1) {
		this.custDate1 = custDate1;
	}

	public String getCustDate2() {
		return custDate2;
	}

	public void setCustDate2(String custDate2) {
		this.custDate2 = custDate2;
	}

	public String getCustDate3() {
		return custDate3;
	}

	public void setCustDate3(String custDate3) {
		this.custDate3 = custDate3;
	}

	public String getCustDate4() {
		return custDate4;
	}

	public void setCustDate4(String custDate4) {
		this.custDate4 = custDate4;
	}

	public String getCustDate5() {
		return custDate5;
	}

	public void setCustDate5(String custDate5) {
		this.custDate5 = custDate5;
	}

	public String getCustNumerico1() {
		return custNumerico1;
	}

	public void setCustNumerico1(String custNumerico1) {
		this.custNumerico1 = custNumerico1;
	}

	public String getCustNumerico2() {
		return custNumerico2;
	}

	public void setCustNumerico2(String custNumerico2) {
		this.custNumerico2 = custNumerico2;
	}

	public String getCustNumerico3() {
		return custNumerico3;
	}

	public void setCustNumerico3(String custNumerico3) {
		this.custNumerico3 = custNumerico3;
	}

	public String getCustNumerico4() {
		return custNumerico4;
	}

	public void setCustNumerico4(String custNumerico4) {
		this.custNumerico4 = custNumerico4;
	}

	public String getCustNumerico5() {
		return custNumerico5;
	}

	public void setCustNumerico5(String custNumerico5) {
		this.custNumerico5 = custNumerico5;
	}

	public String getCustDecimal1() {
		return custDecimal1;
	}

	public void setCustDecimal1(String custDecimal1) {
		this.custDecimal1 = custDecimal1;
	}

	public String getCustDecimal2() {
		return custDecimal2;
	}

	public void setCustDecimal2(String custDecimal2) {
		this.custDecimal2 = custDecimal2;
	}

	public String getCustDecimal3() {
		return custDecimal3;
	}

	public void setCustDecimal3(String custDecimal3) {
		this.custDecimal3 = custDecimal3;
	}

	public String getCustDecimal4() {
		return custDecimal4;
	}

	public void setCustDecimal4(String custDecimal4) {
		this.custDecimal4 = custDecimal4;
	}

	public String getCustDecimal5() {
		return custDecimal5;
	}

	public void setCustDecimal5(String custDecimal5) {
		this.custDecimal5 = custDecimal5;
	}

	public String getCustShortText1() {
		return custShortText1;
	}

	public void setCustShortText1(String custShortText1) {
		this.custShortText1 = custShortText1;
	}

	public String getCustShortText2() {
		return custShortText2;
	}

	public void setCustShortText2(String custShortText2) {
		this.custShortText2 = custShortText2;
	}

	public String getCustShortText3() {
		return custShortText3;
	}

	public void setCustShortText3(String custShortText3) {
		this.custShortText3 = custShortText3;
	}

	public String getCustShortText4() {
		return custShortText4;
	}

	public void setCustShortText4(String custShortText4) {
		this.custShortText4 = custShortText4;
	}

	public String getCustShortText5() {
		return custShortText5;
	}

	public void setCustShortText5(String custShortText5) {
		this.custShortText5 = custShortText5;
	}

	public String getCustShortText6() {
		return custShortText6;
	}

	public void setCustShortText6(String custShortText6) {
		this.custShortText6 = custShortText6;
	}

	public String getCustShortText7() {
		return custShortText7;
	}

	public void setCustShortText7(String custShortText7) {
		this.custShortText7 = custShortText7;
	}

	public String getCustShortText8() {
		return custShortText8;
	}

	public void setCustShortText8(String custShortText8) {
		this.custShortText8 = custShortText8;
	}

	public String getCustShortText9() {
		return custShortText9;
	}

	public void setCustShortText9(String custShortText9) {
		this.custShortText9 = custShortText9;
	}

	public String getCustShortText10() {
		return custShortText10;
	}

	public void setCustShortText10(String custShortText10) {
		this.custShortText10 = custShortText10;
	}

	public String getCustShortText11() {
		return custShortText11;
	}

	public void setCustShortText11(String custShortText11) {
		this.custShortText11 = custShortText11;
	}

	public String getCustShortText12() {
		return custShortText12;
	}

	public void setCustShortText12(String custShortText12) {
		this.custShortText12 = custShortText12;
	}

	public String getCustLongText1() {
		return custLongText1;
	}

	public void setCustLongText1(String custLongText1) {
		this.custLongText1 = custLongText1;
	}

	public String getCustLongText2() {
		return custLongText2;
	}

	public void setCustLongText2(String custLongText2) {
		this.custLongText2 = custLongText2;
	}

	public String getCustLongText3() {
		return custLongText3;
	}

	public void setCustLongText3(String custLongText3) {
		this.custLongText3 = custLongText3;
	}

	public String getOrderNbrToReplace() {
		return orderNbrToReplace;
	}

	public void setOrderNbrToReplace(String orderNbrToReplace) {
		this.orderNbrToReplace = orderNbrToReplace;
	}

	@Override
	public String toString() {
		return "CabeceraOrdenDTO [hdrGroupNbr=" + hdrGroupNbr
				+ ", facilityCode=" + facilityCode + ", companyCode="
				+ companyCode + ", orderNbr=" + orderNbr + ", orderType="
				+ orderType + ", ordDate=" + ordDate + ", expDate=" + expDate
				+ ", reqShipDate=" + reqShipDate + ", destFacilityCode="
				+ destFacilityCode + ", custName=" + custName + ", custAddr="
				+ custAddr + ", custAddr2=" + custAddr2 + ", custAddr3="
				+ custAddr3 + ", refNbr=" + refNbr + ", actionCode="
				+ actionCode + ", routeNbr=" + routeNbr + ", custCity="
				+ custCity + ", custState=" + custState + ", custZip="
				+ custZip + ", custCountry=" + custCountry + ", custPhoneNbr="
				+ custPhoneNbr + ", custEmail=" + custEmail + ", custContact="
				+ custContact + ", custNbr=" + custNbr
				+ ", shiptoFacilityCode=" + shiptoFacilityCode
				+ ", shiptoName=" + shiptoName + ", shiptoAddr=" + shiptoAddr
				+ ", shiptoAddr2=" + shiptoAddr2 + ", shiptoAddr3="
				+ shiptoAddr3 + ", shiptoCity=" + shiptoCity + ", shiptoState="
				+ shiptoState + ", shiptoZip=" + shiptoZip + ", shiptoCountry="
				+ shiptoCountry + ", shiptoPhoneNbr=" + shiptoPhoneNbr
				+ ", shiptoEmail=" + shiptoEmail + ", shiptoContact="
				+ shiptoContact + ", destCompanyCode=" + destCompanyCode
				+ ", priority=" + priority + ", shipViaCode=" + shipViaCode
				+ ", carrierAccountNbr=" + carrierAccountNbr
				+ ", paymentMethod=" + paymentMethod + ", hostAllocationNbr="
				+ hostAllocationNbr + ", customerPoNbr=" + customerPoNbr
				+ ", salesOrderNbr=" + salesOrderNbr + ", salesChannel="
				+ salesChannel + ", destDeptNbr=" + destDeptNbr
				+ ", startShipDate=" + startShipDate + ", stopShipDate="
				+ stopShipDate + ", splInstr=" + splInstr + ", vasGroupCode="
				+ vasGroupCode + ", currencyCode=" + currencyCode
				+ ", stageLocationBarcode=" + stageLocationBarcode
				+ ", custField1=" + custField1 + ", custField2=" + custField2
				+ ", custField3=" + custField3 + ", custField4=" + custField4
				+ ", custField5=" + custField5 + ", obLpnType=" + obLpnType
				+ ", giftMsg=" + giftMsg + ", schedShipDate=" + schedShipDate
				+ ", customerPoType=" + customerPoType
				+ ", customerVendorCode=" + customerVendorCode + ", custDate1="
				+ custDate1 + ", custDate2=" + custDate2 + ", custDate3="
				+ custDate3 + ", custDate4=" + custDate4 + ", custDate5="
				+ custDate5 + ", custNumerico1=" + custNumerico1
				+ ", custNumerico2=" + custNumerico2 + ", custNumerico3="
				+ custNumerico3 + ", custNumerico4=" + custNumerico4
				+ ", custNumerico5=" + custNumerico5 + ", custDecimal1="
				+ custDecimal1 + ", custDecimal2=" + custDecimal2
				+ ", custDecimal3=" + custDecimal3 + ", custDecimal4="
				+ custDecimal4 + ", custDecimal5=" + custDecimal5
				+ ", custShortText1=" + custShortText1 + ", custShortText2="
				+ custShortText2 + ", custShortText3=" + custShortText3
				+ ", custShortText4=" + custShortText4 + ", custShortText5="
				+ custShortText5 + ", custShortText6=" + custShortText6
				+ ", custShortText7=" + custShortText7 + ", custShortText8="
				+ custShortText8 + ", custShortText9=" + custShortText9
				+ ", custShortText10=" + custShortText10 + ", custShortText11="
				+ custShortText11 + ", custShortText12=" + custShortText12
				+ ", custLongText1=" + custLongText1 + ", custLongText2="
				+ custLongText2 + ", custLongText3=" + custLongText3
				+ ", orderNbrToReplace=" + orderNbrToReplace + "]";
	}
	
	public String getCabeceraOrden() {
		StringBuilder str = new StringBuilder();		
		str.append(hdrGroupNbr)
			.append(facilityCode).append(Constants.PIPE)
			.append(companyCode).append(Constants.PIPE)
			.append(orderNbr).append(Constants.PIPE)
			.append(orderType).append(Constants.PIPE)
			.append(ordDate).append(Constants.PIPE)
			.append(expDate).append(Constants.PIPE)
			.append(reqShipDate).append(Constants.PIPE)
			.append(destFacilityCode).append(Constants.PIPE)
			.append(custName).append(Constants.PIPE)
			.append(custAddr).append(Constants.PIPE)
			.append(custAddr2).append(Constants.PIPE)
			.append(custAddr3).append(Constants.PIPE)
			.append(refNbr).append(Constants.PIPE)
			.append(actionCode).append(Constants.PIPE)
			.append(routeNbr).append(Constants.PIPE)
			.append(custCity).append(Constants.PIPE)
			.append(custState).append(Constants.PIPE)
			.append(custZip).append(Constants.PIPE)
			.append(custCountry).append(Constants.PIPE)
			.append(custPhoneNbr).append(Constants.PIPE)
			.append(custEmail).append(Constants.PIPE)
			.append(custContact).append(Constants.PIPE)
			.append(custNbr).append(Constants.PIPE)
			.append(shiptoFacilityCode).append(Constants.PIPE)
			.append(shiptoName).append(Constants.PIPE)
			.append(shiptoAddr).append(Constants.PIPE)
			.append(shiptoAddr2).append(Constants.PIPE)
			.append(shiptoAddr3).append(Constants.PIPE)
			.append(shiptoCity).append(Constants.PIPE)
			.append(shiptoState).append(Constants.PIPE)
			.append(shiptoZip).append(Constants.PIPE)
			.append(shiptoCountry).append(Constants.PIPE)
			.append(shiptoPhoneNbr).append(Constants.PIPE)
			.append(shiptoEmail).append(Constants.PIPE)
			.append(shiptoContact).append(Constants.PIPE)
			.append(destCompanyCode).append(Constants.PIPE)
			.append(priority).append(Constants.PIPE)
			.append(shipViaCode).append(Constants.PIPE)
			.append(carrierAccountNbr).append(Constants.PIPE)
			.append(paymentMethod).append(Constants.PIPE)
			.append(hostAllocationNbr).append(Constants.PIPE)
			.append(customerPoNbr).append(Constants.PIPE)
			.append(salesOrderNbr == null ? "" : salesOrderNbr).append(Constants.PIPE)
			.append(salesChannel).append(Constants.PIPE)
			.append(destDeptNbr).append(Constants.PIPE)
			.append(startShipDate).append(Constants.PIPE)
			.append(stopShipDate).append(Constants.PIPE)
			.append(splInstr).append(Constants.PIPE)
			.append(vasGroupCode).append(Constants.PIPE)
			.append(currencyCode).append(Constants.PIPE)
			.append(stageLocationBarcode).append(Constants.PIPE)
			.append(custField1).append(Constants.PIPE)
			.append(custField2).append(Constants.PIPE)
			.append(custField3).append(Constants.PIPE)
			.append(custField4).append(Constants.PIPE)
			.append(custField5).append(Constants.PIPE)
			.append(obLpnType).append(Constants.PIPE)
			.append(giftMsg).append(Constants.PIPE)
			.append(schedShipDate).append(Constants.PIPE)
			.append(customerPoType).append(Constants.PIPE)
			.append(customerVendorCode).append(Constants.PIPE)
			.append(custDate1).append(Constants.PIPE)
			.append(custDate2).append(Constants.PIPE)
			.append(custDate3).append(Constants.PIPE)
			.append(custDate4).append(Constants.PIPE)
			.append(custDate5).append(Constants.PIPE)
			.append(custNumerico1).append(Constants.PIPE)
			.append(custNumerico2).append(Constants.PIPE)
			.append(custNumerico3).append(Constants.PIPE)
			.append(custNumerico4).append(Constants.PIPE)
			.append(custNumerico5).append(Constants.PIPE)
			.append(custDecimal1).append(Constants.PIPE)
			.append(custDecimal2).append(Constants.PIPE)
			.append(custDecimal3).append(Constants.PIPE)
			.append(custDecimal4).append(Constants.PIPE)
			.append(custDecimal5).append(Constants.PIPE)
			.append(custShortText1).append(Constants.PIPE)
			.append(custShortText2).append(Constants.PIPE)
			.append(custShortText3).append(Constants.PIPE)
			.append(custShortText4).append(Constants.PIPE)
			.append(custShortText5).append(Constants.PIPE)
			.append(custShortText6).append(Constants.PIPE)
			.append(custShortText7).append(Constants.PIPE)
			.append(custShortText8).append(Constants.PIPE)
			.append(custShortText9).append(Constants.PIPE)
			.append(custShortText10).append(Constants.PIPE)
			.append(custShortText11).append(Constants.PIPE)
			.append(custShortText12).append(Constants.PIPE)
			.append(custLongText1).append(Constants.PIPE)
			.append(custLongText2).append(Constants.PIPE)
			.append(custLongText3).append(Constants.PIPE)
			.append(orderNbrToReplace);
	
		return str.toString();
	}
}
