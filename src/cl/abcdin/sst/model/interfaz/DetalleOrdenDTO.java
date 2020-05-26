package cl.abcdin.sst.model.interfaz;

import java.io.Serializable;
import java.util.Date;

import cl.abcdin.sst.utils.Constants;

/**
 * DTO Detalle de la Interfaz de Orden de LogFire.
 * 
 * @author ACL.
 * @date 03-02-2017
 *
 */
public class DetalleOrdenDTO implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 4513503041468765530L;

	private String hdrGroupNbr;
	private Long seqNbr;
	private String itemAlternateCode;
	private String itemPartA;
	private String itemPartB;
	private String itemPartC;
	private String itemPartD;
	private String itemPartE;
	private String itemPartF;
	private String prePackCode;
	private String prePackRatio;
	private String prePackRatioSeq;
	private String prePackTotalUnits;
	private Long ordQty;
	private String reqCntrNbr;
	private String actionCode;
	private String batchNbr;
	private String invnAttrA;
	private String invnAttrB;
	private String invnAttrC;
	private Long cost;
	private Long salePrice;
	private String poNbr;
	private String shipmentNbr;
	private String destFacilityAttrA;
	private String destFacilityAttrB;
	private String destFacilityAttrC;
	private String refNbr1;
	private String hostObLpnNbr;
	private String splInstr;
	private String vasActivityCode;
	private String custField1;
	private String custField2;
	private String custField3;
	private String custField4;
	private String custField5;
	private String voucherNbr;
	private String voucherAmount;
	private String voucherExpDate;
	private String reqPalletNbr;
	private String lockCode;
	private String serialNbr;
	private String itemBarcode;
	private String uom;
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
	private String invnAttrD;
	private String invnAttrE;
	private String invnAttrF;
	private String invnAttrG;
	
	public String getHdrGroupNbr() {
		return hdrGroupNbr;
	}
	public void setHdrGroupNbr(String hdrGroupNbr) {
		this.hdrGroupNbr = hdrGroupNbr;
	}
	public Long getSeqNbr() {
		return seqNbr;
	}
	public void setSeqNbr(Long seqNbr) {
		this.seqNbr = seqNbr;
	}
	public String getItemAlternateCode() {
		return itemAlternateCode;
	}
	public void setItemAlternateCode(String itemAlternateCode) {
		this.itemAlternateCode = itemAlternateCode;
	}
	public String getItemPartA() {
		return itemPartA;
	}
	public void setItemPartA(String itemPartA) {
		this.itemPartA = itemPartA;
	}
	public String getItemPartB() {
		return itemPartB;
	}
	public void setItemPartB(String itemPartB) {
		this.itemPartB = itemPartB;
	}
	public String getItemPartC() {
		return itemPartC;
	}
	public void setItemPartC(String itemPartC) {
		this.itemPartC = itemPartC;
	}
	public String getItemPartD() {
		return itemPartD;
	}
	public void setItemPartD(String itemPartD) {
		this.itemPartD = itemPartD;
	}
	public String getItemPartE() {
		return itemPartE;
	}
	public void setItemPartE(String itemPartE) {
		this.itemPartE = itemPartE;
	}
	public String getItemPartF() {
		return itemPartF;
	}
	public void setItemPartF(String itemPartF) {
		this.itemPartF = itemPartF;
	}
	public String getPrePackCode() {
		return prePackCode;
	}
	public void setPrePackCode(String prePackCode) {
		this.prePackCode = prePackCode;
	}
	public String getPrePackRatio() {
		return prePackRatio;
	}
	public void setPrePackRatio(String prePackRatio) {
		this.prePackRatio = prePackRatio;
	}
	public String getPrePackRatioSeq() {
		return prePackRatioSeq;
	}
	public void setPrePackRatioSeq(String prePackRatioSeq) {
		this.prePackRatioSeq = prePackRatioSeq;
	}
	public String getPrePackTotalUnits() {
		return prePackTotalUnits;
	}
	public void setPrePackTotalUnits(String prePackTotalUnits) {
		this.prePackTotalUnits = prePackTotalUnits;
	}
	public Long getOrdQty() {
		return ordQty;
	}
	public void setOrdQty(Long ordQty) {
		this.ordQty = ordQty;
	}
	public String getReqCntrNbr() {
		return reqCntrNbr;
	}
	public void setReqCntrNbr(String reqCntrNbr) {
		this.reqCntrNbr = reqCntrNbr;
	}
	public String getActionCode() {
		return actionCode;
	}
	public void setActionCode(String actionCode) {
		this.actionCode = actionCode;
	}
	public String getBatchNbr() {
		return batchNbr;
	}
	public void setBatchNbr(String batchNbr) {
		this.batchNbr = batchNbr;
	}
	public String getInvnAttrA() {
		return invnAttrA;
	}
	public void setInvnAttrA(String invnAttrA) {
		this.invnAttrA = invnAttrA;
	}
	public String getInvnAttrB() {
		return invnAttrB;
	}
	public void setInvnAttrB(String invnAttrB) {
		this.invnAttrB = invnAttrB;
	}
	public String getInvnAttrC() {
		return invnAttrC;
	}
	public void setInvnAttrC(String invnAttrC) {
		this.invnAttrC = invnAttrC;
	}
	public Long getCost() {
		return cost;
	}
	public void setCost(Long cost) {
		this.cost = cost;
	}
	public Long getSalePrice() {
		return salePrice;
	}
	public void setSalePrice(Long salePrice) {
		this.salePrice = salePrice;
	}
	public String getPoNbr() {
		return poNbr;
	}
	public void setPoNbr(String poNbr) {
		this.poNbr = poNbr;
	}
	public String getShipmentNbr() {
		return shipmentNbr;
	}
	public void setShipmentNbr(String shipmentNbr) {
		this.shipmentNbr = shipmentNbr;
	}
	public String getDestFacilityAttrA() {
		return destFacilityAttrA;
	}
	public void setDestFacilityAttrA(String destFacilityAttrA) {
		this.destFacilityAttrA = destFacilityAttrA;
	}
	public String getDestFacilityAttrB() {
		return destFacilityAttrB;
	}
	public void setDestFacilityAttrB(String destFacilityAttrB) {
		this.destFacilityAttrB = destFacilityAttrB;
	}
	public String getDestFacilityAttrC() {
		return destFacilityAttrC;
	}
	public void setDestFacilityAttrC(String destFacilityAttrC) {
		this.destFacilityAttrC = destFacilityAttrC;
	}
	public String getRefNbr1() {
		return refNbr1;
	}
	public void setRefNbr1(String refNbr1) {
		this.refNbr1 = refNbr1;
	}
	public String getHostObLpnNbr() {
		return hostObLpnNbr;
	}
	public void setHostObLpnNbr(String hostObLpnNbr) {
		this.hostObLpnNbr = hostObLpnNbr;
	}
	public String getSplInstr() {
		return splInstr;
	}
	public void setSplInstr(String splInstr) {
		this.splInstr = splInstr;
	}
	public String getVasActivityCode() {
		return vasActivityCode;
	}
	public void setVasActivityCode(String vasActivityCode) {
		this.vasActivityCode = vasActivityCode;
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
	public String getVoucherNbr() {
		return voucherNbr;
	}
	public void setVoucherNbr(String voucherNbr) {
		this.voucherNbr = voucherNbr;
	}
	public String getVoucherAmount() {
		return voucherAmount;
	}
	public void setVoucherAmount(String voucherAmount) {
		this.voucherAmount = voucherAmount;
	}
	public String getVoucherExpDate() {
		return voucherExpDate;
	}
	public void setVoucherExpDate(String voucherExpDate) {
		this.voucherExpDate = voucherExpDate;
	}
	public String getReqPalletNbr() {
		return reqPalletNbr;
	}
	public void setReqPalletNbr(String reqPalletNbr) {
		this.reqPalletNbr = reqPalletNbr;
	}
	public String getLockCode() {
		return lockCode;
	}
	public void setLockCode(String lockCode) {
		this.lockCode = lockCode;
	}
	public String getSerialNbr() {
		return serialNbr;
	}
	public void setSerialNbr(String serialNbr) {
		this.serialNbr = serialNbr;
	}
	public String getItemBarcode() {
		return itemBarcode;
	}
	public void setItemBarcode(String itemBarcode) {
		this.itemBarcode = itemBarcode;
	}
	public String getUom() {
		return uom;
	}
	public void setUom(String uom) {
		this.uom = uom;
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
	public String getInvnAttrD() {
		return invnAttrD;
	}
	public void setInvnAttrD(String invnAttrD) {
		this.invnAttrD = invnAttrD;
	}
	public String getInvnAttrE() {
		return invnAttrE;
	}
	public void setInvnAttrE(String invnAttrE) {
		this.invnAttrE = invnAttrE;
	}
	public String getInvnAttrF() {
		return invnAttrF;
	}
	public void setInvnAttrF(String invnAttrF) {
		this.invnAttrF = invnAttrF;
	}
	public String getInvnAttrG() {
		return invnAttrG;
	}
	public void setInvnAttrG(String invnAttrG) {
		this.invnAttrG = invnAttrG;
	}
	
	@Override
	public String toString() {
		return "DetalleOrdenDTO [hdrGroupNbr=" + hdrGroupNbr + ", seqNbr="
				+ seqNbr + ", itemAlternateCode=" + itemAlternateCode
				+ ", itemPartA=" + itemPartA + ", itemPartB=" + itemPartB
				+ ", itemPartC=" + itemPartC + ", itemPartD=" + itemPartD
				+ ", itemPartE=" + itemPartE + ", itemPartF=" + itemPartF
				+ ", prePackCode=" + prePackCode + ", prePackRatio="
				+ prePackRatio + ", prePackRatioSeq=" + prePackRatioSeq
				+ ", prePackTotalUnits=" + prePackTotalUnits + ", ordQty="
				+ ordQty + ", reqCntrNbr=" + reqCntrNbr + ", actionCode="
				+ actionCode + ", batchNbr=" + batchNbr + ", invnAttrA="
				+ invnAttrA + ", invnAttrB=" + invnAttrB + ", invnAttrC="
				+ invnAttrC + ", cost=" + cost + ", salePrice=" + salePrice
				+ ", poNbr=" + poNbr + ", shipmentNbr=" + shipmentNbr
				+ ", destFacilityAttrA=" + destFacilityAttrA
				+ ", destFacilityAttrB=" + destFacilityAttrB
				+ ", destFacilityAttrC=" + destFacilityAttrC + ", refNbr1="
				+ refNbr1 + ", hostObLpnNbr=" + hostObLpnNbr + ", splInstr="
				+ splInstr + ", vasActivityCode=" + vasActivityCode
				+ ", custField1=" + custField1 + ", custField2=" + custField2
				+ ", custField3=" + custField3 + ", custField4=" + custField4
				+ ", custField5=" + custField5 + ", voucherNbr=" + voucherNbr
				+ ", voucherAmount=" + voucherAmount + ", voucherExpDate="
				+ voucherExpDate + ", reqPalletNbr=" + reqPalletNbr
				+ ", lockCode=" + lockCode + ", serialNbr=" + serialNbr
				+ ", itemBarcode=" + itemBarcode + ", uom=" + uom
				+ ", custDate1=" + custDate1 + ", custDate2=" + custDate2
				+ ", custDate3=" + custDate3 + ", custDate4=" + custDate4
				+ ", custDate5=" + custDate5 + ", custNumerico1="
				+ custNumerico1 + ", custNumerico2=" + custNumerico2
				+ ", custNumerico3=" + custNumerico3 + ", custNumerico4="
				+ custNumerico4 + ", custNumerico5=" + custNumerico5
				+ ", custDecimal1=" + custDecimal1 + ", custDecimal2="
				+ custDecimal2 + ", custDecimal3=" + custDecimal3
				+ ", custDecimal4=" + custDecimal4 + ", custDecimal5="
				+ custDecimal5 + ", custShortText1=" + custShortText1
				+ ", custShortText2=" + custShortText2 + ", custShortText3="
				+ custShortText3 + ", custShortText4=" + custShortText4
				+ ", custShortText5=" + custShortText5 + ", custShortText6="
				+ custShortText6 + ", custShortText7=" + custShortText7
				+ ", custShortText8=" + custShortText8 + ", custShortText9="
				+ custShortText9 + ", custShortText10=" + custShortText10
				+ ", custShortText11=" + custShortText11 + ", custShortText12="
				+ custShortText12 + ", custLongText1=" + custLongText1
				+ ", custLongText2=" + custLongText2 + ", custLongText3="
				+ custLongText3 + ", invnAttrD=" + invnAttrD + ", invnAttrE="
				+ invnAttrE + ", invnAttrF=" + invnAttrF + ", invnAttrG="
				+ invnAttrG + "]";
	}

	public String getDetalleOrden() {
		StringBuilder str = new StringBuilder();
		
		str.append(hdrGroupNbr)
			.append(seqNbr).append(Constants.PIPE)
			.append(itemAlternateCode).append(Constants.PIPE)
			.append(itemPartA).append(Constants.PIPE)
			.append(itemPartB).append(Constants.PIPE)
			.append(itemPartC).append(Constants.PIPE)
			.append(itemPartD).append(Constants.PIPE)
			.append(itemPartE).append(Constants.PIPE)
			.append(itemPartF).append(Constants.PIPE)
			.append(prePackCode).append(Constants.PIPE)
			.append(prePackRatio).append(Constants.PIPE)
			.append(prePackRatioSeq).append(Constants.PIPE)
			.append(prePackTotalUnits).append(Constants.PIPE)
			.append(ordQty).append(Constants.PIPE)
			.append(reqCntrNbr).append(Constants.PIPE)
			.append(actionCode).append(Constants.PIPE)
			.append(batchNbr).append(Constants.PIPE)
			.append(invnAttrA).append(Constants.PIPE)
			.append(invnAttrB).append(Constants.PIPE)
			.append(invnAttrC).append(Constants.PIPE)
			.append(cost).append(Constants.PIPE)
			.append(salePrice).append(Constants.PIPE)
			.append(poNbr).append(Constants.PIPE)
			.append(shipmentNbr).append(Constants.PIPE)
			.append(destFacilityAttrA).append(Constants.PIPE)
			.append(destFacilityAttrB).append(Constants.PIPE)
			.append(destFacilityAttrC).append(Constants.PIPE)
			.append(refNbr1).append(Constants.PIPE)
			.append(hostObLpnNbr).append(Constants.PIPE)
			.append(splInstr).append(Constants.PIPE)
			.append(vasActivityCode).append(Constants.PIPE)
			.append(custField1).append(Constants.PIPE)
			.append(custField2).append(Constants.PIPE)
			.append(custField3).append(Constants.PIPE)
			.append(custField4).append(Constants.PIPE)
			.append(custField5).append(Constants.PIPE)
			.append(voucherNbr).append(Constants.PIPE)
			.append(voucherAmount).append(Constants.PIPE)
			.append(voucherExpDate).append(Constants.PIPE)
			.append(reqPalletNbr).append(Constants.PIPE)
			.append(lockCode).append(Constants.PIPE)
			.append(serialNbr).append(Constants.PIPE)
			.append(itemBarcode).append(Constants.PIPE)
			.append(uom).append(Constants.PIPE)
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
			.append(custLongText3 == null ? "" : custLongText3).append(Constants.PIPE)
			.append(invnAttrD == null ? "" : invnAttrD).append(Constants.PIPE)
			.append(invnAttrE == null ? "" : invnAttrE).append(Constants.PIPE)
			.append(invnAttrF).append(Constants.PIPE)
			.append(invnAttrG);
		return str.toString();
	}
}
