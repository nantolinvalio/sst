package cl.abcdin.sst.model.interfaz;

import java.io.Serializable;

import cl.abcdin.sst.utils.Constants;

/**
 * DTO Detalle de la Interfaz de ASN de LogFire.
 * 
 * @author ACL.
 * @date 03-02-2017
 *
 */
public class DetalleAsnDTO implements Serializable {

	private static final long serialVersionUID = -2211509171776629098L;

	private String hdrGroupNbr;
	private Long seqNbr;
	private String actionCode;
	private String lpnNbr;
	private String lpnWeight;
	private String lpnVolume;
	private String itemAlternateCode;
	private String itemPartA;
	private String itemPartB;
	private String itemPartC;
	private String itemPartD;
	private String itemPartE;
	private String itemPartF;
	private String prePackCode;
	private String prePackRatio;
	private String prePackTotalUnits;
	private String invnAttrA;
	private String invnAttrB;
	private String invnAttrC;
	private Long shippedQty;
	private String priorityDate;
	private String poNbr;
	private String palletNbr;
	private String putawayType;
	private String expiryDate;
	private String batchNbr;
	private String recvXdockFacilityCode;
	private String custField1;
	private String custField2;
	private String custField3;
	private String custField4;
	private String custField5;
	private String lpnIsPhysicalPalletFlg;
	private String poSeqNbr;
	private String prePackRatioSeq;
	private String serialNbr;
	private String lpnLockCode;
	private String itemBarcode;
	private String uom;
	private String lpnLength;
	private String lpnWidth;
	private String lpnHeight;
	private String dtlRcvFlg;
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
	public String getActionCode() {
		return actionCode;
	}
	public void setActionCode(String actionCode) {
		this.actionCode = actionCode;
	}
	public String getLpnNbr() {
		return lpnNbr;
	}
	public void setLpnNbr(String lpnNbr) {
		this.lpnNbr = lpnNbr;
	}
	public String getLpnWeight() {
		return lpnWeight;
	}
	public void setLpnWeight(String lpnWeight) {
		this.lpnWeight = lpnWeight;
	}
	public String getLpnVolume() {
		return lpnVolume;
	}
	public void setLpnVolume(String lpnVolume) {
		this.lpnVolume = lpnVolume;
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
	public String getPrePackTotalUnits() {
		return prePackTotalUnits;
	}
	public void setPrePackTotalUnits(String prePackTotalUnits) {
		this.prePackTotalUnits = prePackTotalUnits;
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
	public Long getShippedQty() {
		return shippedQty;
	}
	public void setShippedQty(Long shippedQty) {
		this.shippedQty = shippedQty;
	}
	public String getPoNbr() {
		return poNbr;
	}
	public void setPoNbr(String poNbr) {
		this.poNbr = poNbr;
	}
	public String getPalletNbr() {
		return palletNbr;
	}
	public void setPalletNbr(String palletNbr) {
		this.palletNbr = palletNbr;
	}
	public String getPutawayType() {
		return putawayType;
	}
	public void setPutawayType(String putawayType) {
		this.putawayType = putawayType;
	}
	public String getBatchNbr() {
		return batchNbr;
	}
	public void setBatchNbr(String batchNbr) {
		this.batchNbr = batchNbr;
	}
	public String getRecvXdockFacilityCode() {
		return recvXdockFacilityCode;
	}
	public void setRecvXdockFacilityCode(String recvXdockFacilityCode) {
		this.recvXdockFacilityCode = recvXdockFacilityCode;
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
	public String getPoSeqNbr() {
		return poSeqNbr;
	}
	public void setPoSeqNbr(String poSeqNbr) {
		this.poSeqNbr = poSeqNbr;
	}
	public String getPrePackRatioSeq() {
		return prePackRatioSeq;
	}
	public void setPrePackRatioSeq(String prePackRatioSeq) {
		this.prePackRatioSeq = prePackRatioSeq;
	}
	public String getSerialNbr() {
		return serialNbr;
	}
	public void setSerialNbr(String serialNbr) {
		this.serialNbr = serialNbr;
	}
	public String getLpnLockCode() {
		return lpnLockCode;
	}
	public void setLpnLockCode(String lpnLockCode) {
		this.lpnLockCode = lpnLockCode;
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
	public String getPriorityDate() {
		return priorityDate;
	}
	public void setPriorityDate(String priorityDate) {
		this.priorityDate = priorityDate;
	}
	public String getExpiryDate() {
		return expiryDate;
	}
	public void setExpiryDate(String expiryDate) {
		this.expiryDate = expiryDate;
	}
	public String getLpnIsPhysicalPalletFlg() {
		return lpnIsPhysicalPalletFlg;
	}
	public void setLpnIsPhysicalPalletFlg(String lpnIsPhysicalPalletFlg) {
		this.lpnIsPhysicalPalletFlg = lpnIsPhysicalPalletFlg;
	}
	public String getLpnLength() {
		return lpnLength;
	}
	public void setLpnLength(String lpnLength) {
		this.lpnLength = lpnLength;
	}
	public String getLpnWidth() {
		return lpnWidth;
	}
	public void setLpnWidth(String lpnWidth) {
		this.lpnWidth = lpnWidth;
	}
	public String getLpnHeight() {
		return lpnHeight;
	}
	public void setLpnHeight(String lpnHeight) {
		this.lpnHeight = lpnHeight;
	}
	public String getDtlRcvFlg() {
		return dtlRcvFlg;
	}
	public void setDtlRcvFlg(String dtlRcvFlg) {
		this.dtlRcvFlg = dtlRcvFlg;
	}
	
	@Override
	public String toString() {
		return "DetalleAsnDTO [hdrGroupNbr=" + hdrGroupNbr + ", seqNbr="
				+ seqNbr + ", actionCode=" + actionCode + ", lpnNbr=" + lpnNbr
				+ ", lpnWeight=" + lpnWeight + ", lpnVolume=" + lpnVolume
				+ ", itemAlternateCode=" + itemAlternateCode + ", itemPartA="
				+ itemPartA + ", itemPartB=" + itemPartB + ", itemPartC="
				+ itemPartC + ", itemPartD=" + itemPartD + ", itemPartE="
				+ itemPartE + ", itemPartF=" + itemPartF + ", prePackCode="
				+ prePackCode + ", prePackRatio=" + prePackRatio
				+ ", prePackTotalUnits=" + prePackTotalUnits + ", invnAttrA="
				+ invnAttrA + ", invnAttrB=" + invnAttrB + ", invnAttrC="
				+ invnAttrC + ", shippedQty=" + shippedQty + ", priorityDate="
				+ priorityDate + ", poNbr=" + poNbr + ", palletNbr="
				+ palletNbr + ", putawayType=" + putawayType + ", expiryDate="
				+ expiryDate + ", batchNbr=" + batchNbr
				+ ", recvXdockFacilityCode=" + recvXdockFacilityCode
				+ ", custField1=" + custField1 + ", custField2=" + custField2
				+ ", custField3=" + custField3 + ", custField4=" + custField4
				+ ", custField5=" + custField5 + ", lpnIsPhysicalPalletFlg="
				+ lpnIsPhysicalPalletFlg + ", poSeqNbr=" + poSeqNbr
				+ ", prePackRatioSeq=" + prePackRatioSeq + ", serialNbr="
				+ serialNbr + ", lpnLockCode=" + lpnLockCode + ", itemBarcode="
				+ itemBarcode + ", uom=" + uom + ", lpnLength=" + lpnLength
				+ ", lpnWidth=" + lpnWidth + ", lpnHeight=" + lpnHeight
				+ ", dtlRcvFlg=" + dtlRcvFlg + ", invnAttrD=" + invnAttrD
				+ ", invnAttrE=" + invnAttrE + ", invnAttrF=" + invnAttrF
				+ ", invnAttrG=" + invnAttrG + "]";
	}
	
	public String getDetalleASN() {
		
		StringBuilder str = new StringBuilder();		
		str.append(hdrGroupNbr)
			.append(seqNbr).append(Constants.PIPE)
			.append(actionCode).append(Constants.PIPE)
			.append(lpnNbr).append(Constants.PIPE)
			.append(lpnWeight).append(Constants.PIPE)
			.append(lpnVolume).append(Constants.PIPE)
			.append(itemAlternateCode).append(Constants.PIPE)
			.append(itemPartA).append(Constants.PIPE)
			.append(itemPartB).append(Constants.PIPE)
			.append(itemPartC).append(Constants.PIPE)
			.append(itemPartD).append(Constants.PIPE)
			.append(itemPartE).append(Constants.PIPE)
			.append(itemPartF).append(Constants.PIPE)
			.append(prePackCode).append(Constants.PIPE)
			.append(prePackRatio).append(Constants.PIPE)
			.append(prePackTotalUnits).append(Constants.PIPE)
			.append(invnAttrA).append(Constants.PIPE)
			.append(invnAttrB).append(Constants.PIPE)
			.append(invnAttrC).append(Constants.PIPE)
			.append(shippedQty).append(Constants.PIPE)
			.append(priorityDate).append(Constants.PIPE)
			.append(poNbr).append(Constants.PIPE)
			.append(palletNbr).append(Constants.PIPE)
			.append(putawayType).append(Constants.PIPE)
			.append(expiryDate).append(Constants.PIPE)
			.append(batchNbr).append(Constants.PIPE)
			.append(recvXdockFacilityCode).append(Constants.PIPE)
			.append(custField1).append(Constants.PIPE)
			.append(custField2).append(Constants.PIPE)
			.append(custField3).append(Constants.PIPE)
			.append(custField4).append(Constants.PIPE)
			.append(custField5).append(Constants.PIPE)
			.append(lpnIsPhysicalPalletFlg).append(Constants.PIPE)
			.append(poSeqNbr).append(Constants.PIPE)
			.append(prePackRatioSeq).append(Constants.PIPE)
			.append(serialNbr).append(Constants.PIPE)
			.append(lpnLockCode).append(Constants.PIPE)
			.append(itemBarcode).append(Constants.PIPE)
			.append(uom).append(Constants.PIPE)
			.append(lpnLength).append(Constants.PIPE)
			.append(lpnWidth).append(Constants.PIPE)
			.append(lpnHeight).append(Constants.PIPE)
			.append(dtlRcvFlg).append(Constants.PIPE)
			.append(invnAttrD).append(Constants.PIPE)
			.append(invnAttrE).append(Constants.PIPE)
			.append(invnAttrF).append(Constants.PIPE)
			.append(invnAttrG);
	
		return str.toString();
	}
}
