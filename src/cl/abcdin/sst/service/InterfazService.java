package cl.abcdin.sst.service;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Calendar;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import cl.abcdin.sst.dao.BitacoraDAO;
import cl.abcdin.sst.dao.OtTrackingDAO;
import cl.abcdin.sst.dao.UtilDAO;
import cl.abcdin.sst.model.Cambio;
import cl.abcdin.sst.model.OrdenTrabajo;
import cl.abcdin.sst.model.OtTracking;
import cl.abcdin.sst.model.Prts;
import cl.abcdin.sst.model.Ubicacion;
import cl.abcdin.sst.model.interfaz.AjustePmmDTO;
import cl.abcdin.sst.model.interfaz.AsnDTO;
import cl.abcdin.sst.model.interfaz.CabeceraAsnDTO;
import cl.abcdin.sst.model.interfaz.CabeceraOrdenDTO;
import cl.abcdin.sst.model.interfaz.DetalleAsnDTO;
import cl.abcdin.sst.model.interfaz.DetalleOrdenDTO;
import cl.abcdin.sst.model.interfaz.OrdenDTO;
import cl.abcdin.sst.utils.Constants;
import cl.abcdin.sst.utils.SSTTParametros;
import cl.abcdin.sst.utils.Utils;

/**
 * Clase de encargada de las llamadas a los metodos de interfaz PMM y LogFire.
 * 
 * @author ACL
 *
 */
public class InterfazService {
	private static final Log log = LogFactory.getLog(InterfazService.class);
	
	private OtTrackingDAO otTrackingDAO;
	private UtilDAO utilDAO;
	private BitacoraDAO bitacoraDAO;

	public void createInterfazPMM(Ubicacion ubicacion, OrdenTrabajo oT,
			String dcQuantity) throws Exception {
		
		PrintWriter salida = null;
		String nombreArchivo = null;
		AjustePmmDTO registro = null;
		String nombre = null;
		
		try {
			// ADJ+ST+PMM+ORG_LVL_NUMBER+yyyymmddhhss+batch_number.TXT
			if(ubicacion == null){
				Long idUbicacion = bitacoraDAO.getUbicacionInicialOT(oT.getId());
				ubicacion = new Ubicacion();
				ubicacion.setId(idUbicacion);
			}
			
			nombreArchivo = getNombreArchivoPMM(ubicacion);
			
			//TODO descomentar la primera salida y comentar la segunda para pruebas local (plarrain)
			nombre = SSTTParametros.getInstancia()
					.getParametroPorCodigo(Constants.PATH_ADJ).getValor()
					.concat("temp_" + nombreArchivo);
			
			salida = new PrintWriter(nombre);
			
			//------------------------------------------------------------------------
			 // salida = new PrintWriter("C:/borrarDataTestABCDIN/".concat(nombre));
			//------------------------------------------------------------------------
			
			registro = getAdjRegistro(oT, ubicacion, dcQuantity);

			salida.print(registro.getLineaAjuste() + "\n");
			salida.flush();
			
		} catch (FileNotFoundException e) {
			log.error(e.getMessage(), e);
			throw e;
		} finally {
			try {
				if (null != salida)
					salida.close();
				
					String[] nombre2 = nombre.split("temp_");
					boolean renombro = renombrarArchivo(nombre, SSTTParametros.getInstancia().getParametroPorCodigo(Constants.PATH_ADJ).getValor().concat(nombre2[1]));
					//TODO ruta local (plarrain)
					//boolean renombro = renombrarArchivo("C:/borrarDataTestABCDIN".concat(nombre), "C:/borrarDataTestABCDIN".concat(SSTTParametros.getInstancia().getParametroPorCodigo(Constants.PATH_ADJ).getValor().concat(nombre2[1])));
					if (!renombro) {
						System.out.println("-----> Error intentando cambiar el nombre de fichero.");
				    }
					
			} catch (Exception e2) {
				log.error(e2.getMessage(), e2);
				throw e2;
			}
		}
	}
	
	private boolean renombrarArchivo(String archivoAntiguo, String archivoNuevo){
		File fichero = new File(archivoAntiguo);
		if (!fichero.exists()) {
		    System.out.println("-----> Error. No existe un fichero: "+archivoAntiguo);
		    return false;
		}
		
		File fichero2 = new File(archivoNuevo);
		if(fichero2.exists()) {
		    System.out.println("-----> Error. Ya existe un fichero: "+archivoNuevo);
		    return false;
		}
		
		boolean correcto = fichero.renameTo(fichero2);
		return correcto;
	}
	
	private AjustePmmDTO getAdjRegistro(OrdenTrabajo oT, Ubicacion ubicacion, String dcQuantity) {
		
		AjustePmmDTO ajustePmmDTO = new AjustePmmDTO();
		Long seqPMM = new Long(1); //utilDAO.getSeqRegInterfazPMM();
		
		ajustePmmDTO.setTechKey(seqPMM);
		ajustePmmDTO.setOrgLvlNumber(ubicacion.getId());
		ajustePmmDTO.setPrdLvlNumber(oT.getProducto().getId().toString());
		ajustePmmDTO.setToPrdLvlNbr(Constants.VACIO);
		
		try {
			ajustePmmDTO.setDateCreated(Utils.formatDate(Calendar.getInstance().getTime(),
								Constants.FORMATO_FECHA_YYYYMMDD));
			ajustePmmDTO.setEntryDate(Utils.formatDate(Calendar.getInstance().getTime(),
					Constants.FORMATO_FECHA_YYYYMMDD));
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}
		ajustePmmDTO.setFromInvType(Constants.FROM_INV_TYPE);
		
		//-----------------------------------------------------------------------
		String quanty = "";
		if(oT.getBanderaOrigenOT().equals(Constants.CASO_USO_1)){
		
			String[] contiene = SSTTParametros.getInstancia().getParametroPorCodigo(Constants.INV_MRPT_DRPT_CODE_CU_01).getValor().split("/");
			ajustePmmDTO.setInvMrptCode(contiene[0]);
			ajustePmmDTO.setInvDrptCode(contiene[1]);
			if("+".equals(contiene[2])){
				quanty = Constants.DC_QUANTITY_POSITIVO;
			}
			if("-".equals(contiene[2])){
				quanty = Constants.DC_QUANTITY_NEGATIVO;
			}
			
		}else if(oT.getBanderaOrigenOT().equals(Constants.CASO_USO_2)){
		
			String[] contiene = SSTTParametros.getInstancia().getParametroPorCodigo(Constants.INV_MRPT_DRPT_CODE_CU_02).getValor().split("/");
			ajustePmmDTO.setInvMrptCode(contiene[0]);
			ajustePmmDTO.setInvDrptCode(contiene[1]);
			if("+".equals(contiene[2])){
				quanty = Constants.DC_QUANTITY_POSITIVO;
			}
			if("-".equals(contiene[2])){
				quanty = Constants.DC_QUANTITY_NEGATIVO;
			}
			
		}else if(oT.getBanderaOrigenOT().equals(Constants.CASO_USO_4)){
					
			if(dcQuantity.equals(Constants.DC_QUANTITY_NEGATIVO)){
				String[] contiene = SSTTParametros.getInstancia().getParametroPorCodigo(Constants.INV_MRPT_DRPT_CODE_CU_04_2).getValor().split("/");
				ajustePmmDTO.setInvMrptCode(contiene[0]);
				ajustePmmDTO.setInvDrptCode(contiene[1]);
				if("+".equals(contiene[2])){
					quanty = Constants.DC_QUANTITY_POSITIVO;
				}
				if("-".equals(contiene[2])){
					quanty = Constants.DC_QUANTITY_NEGATIVO;
				}
			}
			if(dcQuantity.equals(Constants.DC_QUANTITY_POSITIVO)){		
				String[] contiene = SSTTParametros.getInstancia().getParametroPorCodigo(Constants.INV_MRPT_DRPT_CODE_CU_04_3).getValor().split("/");
				ajustePmmDTO.setInvMrptCode(contiene[0]);
				ajustePmmDTO.setInvDrptCode(contiene[1]);
				if("+".equals(contiene[2])){
					quanty = Constants.DC_QUANTITY_POSITIVO;
				}
				if("-".equals(contiene[2])){
					quanty = Constants.DC_QUANTITY_NEGATIVO;
				}
			}
						
		}else if(oT.getBanderaOrigenOT().equals(Constants.CASO_USO_5)){		
			String[] contiene = SSTTParametros.getInstancia().getParametroPorCodigo(Constants.INV_MRPT_DRPT_CODE_CU_05).getValor().split("/");
			ajustePmmDTO.setInvMrptCode(contiene[0]);
			ajustePmmDTO.setInvDrptCode(contiene[1]);
			if("+".equals(contiene[2])){
				quanty = Constants.DC_QUANTITY_POSITIVO;
			}
			if("-".equals(contiene[2])){
				quanty = Constants.DC_QUANTITY_NEGATIVO;
			}
			
		}else if(oT.getBanderaOrigenOT().equals(Constants.CASO_USO_6)){
			String[] contiene = SSTTParametros.getInstancia().getParametroPorCodigo(Constants.INV_MRPT_DRPT_CODE_CU_06).getValor().split("/");
			ajustePmmDTO.setInvMrptCode(contiene[0]);
			ajustePmmDTO.setInvDrptCode(contiene[1]);
			if("+".equals(contiene[2])){
				quanty = Constants.DC_QUANTITY_POSITIVO;
			}
			if("-".equals(contiene[2])){
				quanty = Constants.DC_QUANTITY_NEGATIVO;
			}
			
		}else if(oT.getBanderaOrigenOT().equals(Constants.CASO_USO_7)){
			String[] contiene = SSTTParametros.getInstancia().getParametroPorCodigo(Constants.INV_MRPT_DRPT_CODE_CU_07).getValor().split("/");
			ajustePmmDTO.setInvMrptCode(contiene[0]);
			ajustePmmDTO.setInvDrptCode(contiene[1]);
			if("+".equals(contiene[2])){
				quanty = Constants.DC_QUANTITY_POSITIVO;
			}
			if("-".equals(contiene[2])){
				quanty = Constants.DC_QUANTITY_NEGATIVO;
			}
			
		}else if(oT.getBanderaOrigenOT().equals(Constants.CASO_USO_8)){
			String[] contiene = SSTTParametros.getInstancia().getParametroPorCodigo(Constants.INV_MRPT_DRPT_CODE_CU_08).getValor().split("/");
			ajustePmmDTO.setInvMrptCode(contiene[0]);
			ajustePmmDTO.setInvDrptCode(contiene[1]);
			if("+".equals(contiene[2])){
				quanty = Constants.DC_QUANTITY_POSITIVO;
			}
			if("-".equals(contiene[2])){
				quanty = Constants.DC_QUANTITY_NEGATIVO;
			}
			
		}else if(oT.getBanderaOrigenOT().equals(Constants.CASO_USO_GENERICO_1)){
			String[] contiene = SSTTParametros.getInstancia().getParametroPorCodigo(Constants.INV_MRPT_DRPT_CODE_CU_04_1).getValor().split("/");
			ajustePmmDTO.setInvMrptCode(contiene[0]);
			ajustePmmDTO.setInvDrptCode(contiene[1]);
			if("+".equals(contiene[2])){
				quanty = Constants.DC_QUANTITY_POSITIVO;
			}
			if("-".equals(contiene[2])){
				quanty = Constants.DC_QUANTITY_NEGATIVO;
			}
			
		}else{
			String[] contiene = SSTTParametros.getInstancia().getParametroPorCodigo(Constants.INV_MRPT_DRPT_CODE_CU_09).getValor().split("/");
			ajustePmmDTO.setInvMrptCode(contiene[0]);
			ajustePmmDTO.setInvDrptCode(contiene[1]);
			if("+".equals(contiene[2])){
				quanty = Constants.DC_QUANTITY_POSITIVO;
			}
			if("-".equals(contiene[2])){
				quanty = Constants.DC_QUANTITY_NEGATIVO;
			}
		}
		//-----------------------------------------------------------------------
		if(!"".equals(quanty)){
			ajustePmmDTO.setDcQuantity(quanty);
		}else{
			ajustePmmDTO.setDcQuantity(dcQuantity);
		}
		ajustePmmDTO.setJdaQuantity(Constants.VACIO);
		ajustePmmDTO.setActionFlag(Constants.ACTION_FLAG_ADJ);
		ajustePmmDTO.setDownloadDate1(Constants.VACIO);
		ajustePmmDTO.setDownloadDate2(Constants.VACIO);
		ajustePmmDTO.setPrdSllUom(Constants.PRD_SLL_UOM);
		ajustePmmDTO.setFromInnerPackId(Constants.VACIO);
		ajustePmmDTO.setToInnerPackId(Constants.VACIO);
		ajustePmmDTO.setToMrptCode(Constants.VACIO);
		ajustePmmDTO.setToDrptCode(Constants.VACIO);
		ajustePmmDTO.setBatchNum(seqPMM);
		ajustePmmDTO.setBoletaNumber(oT.getIdDocumento()); //JADM MOD 7 AGREGA IdBoleta
		ajustePmmDTO.setOtNumber(oT.getId()); //JADM MOD 8 AGREGA idOT
		return ajustePmmDTO;
	}
	
	private String getNombreArchivoPMM(Ubicacion ubicacion) {
		Prts comienzaCon = SSTTParametros.getInstancia().getParametroPorCodigo(Constants.STARWITH_ADJ);
		Prts sistemaOrg =SSTTParametros.getInstancia().getParametroPorCodigo(Constants.SYSTEM_ORIGEN_ADJ);
		Prts sistemaDest =SSTTParametros.getInstancia().getParametroPorCodigo(Constants.SYSTEM_DESTINO_ADJ);
		Prts terminaCon = SSTTParametros.getInstancia().getParametroPorCodigo(Constants.ENDSWITH_ADJ);
		Long seqPMM = utilDAO.getSeqInterfazPMM();
		String fechaArchivo = null;
		try {
			fechaArchivo = Utils.formatDate(Calendar.getInstance().getTime(),
					Constants.FORMATO_FECHA_YYYYMMDDHHMMSS);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}
		StringBuilder nombreArchivo = new StringBuilder();
		nombreArchivo.append(comienzaCon.getValor() != null ? comienzaCon.getValor() : "")
				.append(sistemaOrg.getValor() != null ? sistemaOrg.getValor() : "").append(sistemaDest.getValor() != null ? sistemaDest.getValor() : "")
				.append(ubicacion.getId().toString()).append(fechaArchivo)
				.append(seqPMM.toString()).append(terminaCon.getValor() != null ? terminaCon.getValor() : "");
		return nombreArchivo.toString();
	}
	
	public void createInterfazOrden(Ubicacion ubicacion, OrdenTrabajo oT)
			throws Exception {
		
		PrintWriter salida = null;
		String nombreArchivo = null;
		OrdenDTO dto = null;
		String nombre = null;
		try {
			// ORR+ORIGEN+COMPAÑÍA DESTINO + YYYYMMDD + HHMMSS + SEQ.extensión
			nombreArchivo = getNombreArchivoOrden();
			//----------------------------------------------------------------
			//TODO descomentar la primera salida y comentar la segunda para pruebas local (plarrain)
			nombre = SSTTParametros.getInstancia()
					.getParametroPorCodigo(Constants.PATH_ORDEN).getValor()
					.concat("temp_" + nombreArchivo);
			
			salida = new PrintWriter(nombre);
			
			//----------------------------------------------------------------
			//salida = new PrintWriter("C:/borrarDataTestABCDIN/".concat(nombre));
			//----------------------------------------------------------------
			
			dto = getOrrRegistro(oT, ubicacion);
			salida.print(dto.getCabeceraDTO().getCabeceraOrden() + "\r\n");
			salida.print(dto.getDetalleDTO().getDetalleOrden() + "\r\n");
			salida.flush();
		} catch (FileNotFoundException e) {
			log.error(e.getMessage(), e);
			throw e;
		} finally {
			try {
				if (null != salida)
					salida.close();
					
				String[] nombre2 = nombre.split("temp_");
				boolean renombro = renombrarArchivo(nombre, SSTTParametros.getInstancia().getParametroPorCodigo(Constants.PATH_ORDEN).getValor().concat(nombre2[1]));
				//TODO ruta local (plarrain)
				//boolean renombro = renombrarArchivo("C:/borrarDataTestABCDIN".concat(nombre), "C:/borrarDataTestABCDIN".concat(SSTTParametros.getInstancia().getParametroPorCodigo(Constants.PATH_ORDEN).getValor().concat(nombre2[1])));
				if (!renombro) {
					System.out.println("-----> Error intentando cambiar el nombre de fichero.");
			    }
				
			} catch (Exception e2) {
				log.error(e2.getMessage(), e2);
				throw e2;
			}
		}
		
	}

	private OrdenDTO getOrrRegistro(OrdenTrabajo oT, Ubicacion ubicacion) {
		OrdenDTO ordenDTO = new OrdenDTO();
		CabeceraOrdenDTO cabecera = getCabeceraOrr(oT, ubicacion);
		DetalleOrdenDTO detalle = getDetalleOrr(oT);
		ordenDTO.setCabeceraDTO(cabecera);
		ordenDTO.setDetalleDTO(detalle);
		return ordenDTO;
	}


	private DetalleOrdenDTO getDetalleOrr(OrdenTrabajo oT) {
		
		DetalleOrdenDTO detalle = new DetalleOrdenDTO();
		Long seqOrr = new Long(1); //utilDAO.getSeqDetInterfazORR();
		detalle.setHdrGroupNbr(Constants.HDR_GROUP_NBR_DET_ORR);
		detalle.setSeqNbr(seqOrr);
		detalle.setItemAlternateCode(Constants.VACIO);
		detalle.setItemPartA(Constants.ITEM_PART_A.concat(oT.getProducto()
				.getId().toString()));
		detalle.setItemPartB(Constants.VACIO);
		detalle.setItemPartC(Constants.VACIO);
		detalle.setItemPartD(Constants.VACIO);
		detalle.setItemPartE(Constants.VACIO);
		detalle.setItemPartF(Constants.VACIO);
		detalle.setPrePackCode(Constants.VACIO);
		detalle.setPrePackRatio(Constants.VACIO);
		detalle.setPrePackRatioSeq(Constants.VACIO);
		detalle.setPrePackTotalUnits(Constants.VACIO);
		detalle.setOrdQty(1L);
		detalle.setReqCntrNbr(Constants.VACIO);
		detalle.setActionCode(Constants.ACTION_CODE_C);
		detalle.setBatchNbr(Constants.VACIO);
		detalle.setInvnAttrA(Constants.VACIO);
		detalle.setInvnAttrB(Constants.VACIO);
		detalle.setInvnAttrC(Constants.VACIO);
		detalle.setCost(0L);
		detalle.setSalePrice(0L);
		detalle.setPoNbr(Constants.VACIO);
		detalle.setShipmentNbr(Constants.VACIO);
		detalle.setDestFacilityAttrA(Constants.VACIO);
		detalle.setDestFacilityAttrB(Constants.VACIO);
		detalle.setDestFacilityAttrC(Constants.VACIO);
		detalle.setRefNbr1(Constants.VACIO);
		detalle.setHostObLpnNbr(Constants.VACIO);
		detalle.setSplInstr(Constants.VACIO);
		detalle.setVasActivityCode(Constants.VACIO);
		detalle.setCustField1(Constants.VACIO);
		detalle.setCustField2(Constants.VACIO);
		detalle.setCustField3(Constants.VACIO);
		detalle.setCustField4(Constants.VACIO);
		detalle.setCustField5(Constants.VACIO);
		detalle.setVoucherNbr(Constants.VACIO);
		detalle.setVoucherAmount(Constants.VACIO);
		detalle.setVoucherExpDate(Constants.VACIO);
		detalle.setReqPalletNbr(Constants.VACIO);
		detalle.setLockCode(Constants.VACIO);
		detalle.setSerialNbr(Constants.VACIO);
		detalle.setItemBarcode(Constants.VACIO);
		detalle.setUom(Constants.VACIO);
		detalle.setCustDate1(Constants.VACIO);
		detalle.setCustDate2(Constants.VACIO);
		detalle.setCustDate3(Constants.VACIO);
		detalle.setCustDate4(Constants.VACIO);
		detalle.setCustDate5(Constants.VACIO);
		detalle.setCustNumerico1(Constants.VACIO);
		detalle.setCustNumerico2(Constants.VACIO);
		detalle.setCustNumerico3(Constants.VACIO);
		detalle.setCustNumerico4(Constants.VACIO);
		detalle.setCustNumerico5(Constants.VACIO);
		detalle.setCustDecimal1(Constants.VACIO);
		detalle.setCustDecimal2(Constants.VACIO);
		detalle.setCustDecimal3(Constants.VACIO);
		detalle.setCustDecimal4(Constants.VACIO);
		detalle.setCustDecimal5(Constants.VACIO);
		detalle.setCustShortText1(Constants.VACIO);
		detalle.setCustShortText2(Constants.VACIO);
		detalle.setCustShortText3(Constants.VACIO);
		detalle.setCustShortText4(Constants.VACIO);
		detalle.setCustShortText5(Constants.VACIO);
		detalle.setCustShortText6(Constants.VACIO);
		detalle.setCustShortText7(Constants.VACIO);
		detalle.setCustShortText8(Constants.VACIO);
		detalle.setCustShortText9(Constants.VACIO);
		detalle.setCustShortText10(Constants.VACIO);
		detalle.setCustShortText11(Constants.VACIO);
		detalle.setCustShortText12(Constants.VACIO);
		detalle.setCustLongText1(Constants.VACIO);
		detalle.setCustLongText2(Constants.VACIO);
		detalle.setCustLongText3(Constants.VACIO);
		detalle.setInvnAttrE(Constants.VACIO);
		detalle.setInvnAttrF(Constants.VACIO);
		detalle.setInvnAttrG(Constants.VACIO);
		return detalle;
	}


	private CabeceraOrdenDTO getCabeceraOrr(final OrdenTrabajo oT,
			final Ubicacion ubicacion) {
		CabeceraOrdenDTO cabecera = new CabeceraOrdenDTO();
		String fechaOrden = null;
		try {
			fechaOrden = Utils.formatDate(Calendar.getInstance().getTime(),
					Constants.FORMATO_FECHA_YYYYMMDD);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}
		cabecera.setHdrGroupNbr(Constants.HDR_GROUP_NBR_CAB_ORR);
		cabecera.setFacilityCode(Constants.FACILITY_CODE_CAB_ORR.concat(ubicacion.getId().toString()));
		cabecera.setCompanyCode(Constants.COMPANY_CODE_CAB_ORR);
		cabecera.setOrderNbr(Constants.ORDER_NBR_CAB_ORR.concat(oT.getId().toString()));
		cabecera.setOrderType(Constants.ORDER_TYPE_CAB_ORR);
		cabecera.setOrdDate(fechaOrden);
		cabecera.setExpDate(Constants.VACIO);
		cabecera.setReqShipDate(Constants.VACIO);
		cabecera.setDestFacilityCode(SSTTParametros.getInstancia().getParametroPorCodigo(Constants.DEST_FACILITY_CODE_HDR_ORR).getValor());
		cabecera.setCustName(Constants.VACIO);
		cabecera.setCustAddr(Constants.VACIO);
		cabecera.setCustAddr2(Constants.VACIO);
		cabecera.setCustAddr3(Constants.VACIO);
		cabecera.setRefNbr(Constants.VACIO);
		cabecera.setActionCode(Constants.ACTION_CODE_C);
		cabecera.setRouteNbr(Constants.VACIO);
		cabecera.setCustCity(Constants.VACIO);
		cabecera.setCustState(Constants.VACIO);
		cabecera.setCustZip(Constants.VACIO);
		cabecera.setCustCountry(Constants.VACIO);
		cabecera.setCustPhoneNbr(Constants.VACIO);
		cabecera.setCustEmail(Constants.VACIO);
		cabecera.setCustContact(Constants.VACIO);
		cabecera.setCustNbr(Constants.VACIO);
		cabecera.setShiptoFacilityCode(Constants.VACIO);
		cabecera.setShiptoName(Constants.VACIO);
		cabecera.setShiptoAddr(Constants.VACIO);
		cabecera.setShiptoAddr2(Constants.VACIO);
		cabecera.setShiptoAddr3(Constants.VACIO);
		cabecera.setShiptoCity(Constants.VACIO);
		cabecera.setShiptoState(Constants.VACIO);
		cabecera.setShiptoZip(Constants.VACIO);
		cabecera.setShiptoCountry(Constants.VACIO);
		cabecera.setShiptoPhoneNbr(Constants.VACIO);
		cabecera.setShiptoEmail(Constants.VACIO);
		cabecera.setShiptoContact(Constants.VACIO);
		cabecera.setDestCompanyCode(Constants.VACIO);
		cabecera.setPriority(Constants.VACIO);
		cabecera.setShipViaCode(Constants.VACIO);
		cabecera.setCarrierAccountNbr(Constants.VACIO);
		cabecera.setPaymentMethod(Constants.VACIO);
		cabecera.setHostAllocationNbr(Constants.VACIO);
		cabecera.setCustomerPoNbr(Constants.VACIO);
		cabecera.setSalesChannel(Constants.VACIO);
		cabecera.setDestDeptNbr(Constants.VACIO);
		cabecera.setStartShipDate(Constants.VACIO);
		cabecera.setStopShipDate(Constants.VACIO);
		cabecera.setSplInstr(Constants.VACIO);
		cabecera.setVasGroupCode(Constants.VACIO);
		cabecera.setCurrencyCode(Constants.VACIO);
		cabecera.setStageLocationBarcode(Constants.VACIO);
		cabecera.setCustField1(Constants.VACIO);
		cabecera.setCustField2("ABCDin");
		cabecera.setCustField3(Constants.VACIO);
		cabecera.setCustField4(Constants.VACIO);
		cabecera.setCustField5(Constants.VACIO);
		cabecera.setObLpnType(Constants.VACIO);
		cabecera.setGiftMsg(Constants.VACIO);
		cabecera.setSchedShipDate(Constants.VACIO);
		cabecera.setCustomerPoType(Constants.VACIO);
		cabecera.setCustomerVendorCode(Constants.VACIO);
		cabecera.setCustDate1(Constants.VACIO);
		cabecera.setCustDate2(Constants.VACIO);
		cabecera.setCustDate3(Constants.VACIO);
		cabecera.setCustDate4(Constants.VACIO);
		cabecera.setCustDate5(Constants.VACIO);
		cabecera.setCustNumerico1(Constants.VACIO);
		cabecera.setCustNumerico2(Constants.VACIO);
		cabecera.setCustNumerico3(Constants.VACIO);
		cabecera.setCustNumerico4(Constants.VACIO);
		cabecera.setCustNumerico5(Constants.VACIO);
		cabecera.setCustDecimal1(Constants.VACIO);
		cabecera.setCustDecimal2(Constants.VACIO);
		cabecera.setCustDecimal3(Constants.VACIO);
		cabecera.setCustDecimal4(Constants.VACIO);
		cabecera.setCustDecimal5(Constants.VACIO);
		cabecera.setCustShortText1(Constants.VACIO);
		cabecera.setCustShortText2(Constants.VACIO);
		cabecera.setCustShortText3(Constants.VACIO);
		cabecera.setCustShortText4(Constants.VACIO);
		cabecera.setCustShortText5(Constants.VACIO);
		cabecera.setCustShortText6(Constants.VACIO);
		cabecera.setCustShortText7(Constants.VACIO);
		cabecera.setCustShortText8(Constants.VACIO);
		cabecera.setCustShortText9(Constants.VACIO);
		cabecera.setCustShortText10(Constants.CUST_SHORT_TEXT_10_CAB_ORR);
		cabecera.setCustShortText11(Constants.VACIO);
		cabecera.setCustShortText12(Constants.VACIO);
		cabecera.setCustLongText1(Constants.VACIO);
		cabecera.setCustLongText2(Constants.VACIO);
		cabecera.setCustLongText3(Constants.VACIO);
		cabecera.setOrderNbrToReplace(Constants.VACIO);
		return cabecera;
	}


	private String getNombreArchivoOrden() {
		Prts comienzaCon = SSTTParametros.getInstancia().getParametroPorCodigo(Constants.STARWITH_ORDEN);
		Prts terminaCon = SSTTParametros.getInstancia().getParametroPorCodigo(Constants.ENDSWITH_ORDEN);
		Prts sistemaOrg =SSTTParametros.getInstancia().getParametroPorCodigo(Constants.SYSTEM_ORIGEN_ORR);
		Prts sistemaDest =SSTTParametros.getInstancia().getParametroPorCodigo(Constants.SYSTEM_DESTINO_ORR);
		Long seqPMM = utilDAO.getSeqInterfazORR();
		String fechaArchivo = null;
		try {
			fechaArchivo = Utils.formatDate(Calendar.getInstance().getTime(),
					Constants.FORMATO_FECHA_YYYYMMDDHHMMSS);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}
		StringBuilder nombreArchivo = new StringBuilder();
		nombreArchivo.append(comienzaCon.getValor() != null ? comienzaCon.getValor() : "")
				.append(sistemaOrg.getValor() != null ? sistemaOrg.getValor() : "").append(sistemaDest.getValor() != null ? sistemaDest.getValor() : "")
				.append(fechaArchivo)
				.append(seqPMM.toString()).append(terminaCon.getValor() != null ? terminaCon.getValor() : "");
		return nombreArchivo.toString();
	}


	public void saveOtTracking(OrdenTrabajo oT, long idOtInterfaz)
			throws Exception {
		OtTracking otTracking = null;
		try {
			otTracking = setOtTracking(oT, idOtInterfaz);
			otTrackingDAO.save(otTracking);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}

	}

	private OtTracking setOtTracking(OrdenTrabajo oT, long idOtInterfaz) {
		OtTracking otTracking = new OtTracking();
		otTracking.setSidOt(oT.getId());
		otTracking.setSidOtInterfaz(idOtInterfaz);
		return otTracking;
	}

	public void setOtTrackingDAO(OtTrackingDAO otTrackingDAO) {
		this.otTrackingDAO = otTrackingDAO;
	}
	public void setUtilDAO(UtilDAO utilDAO) {
		this.utilDAO = utilDAO;
	}
	public void setBitacoraDAO(BitacoraDAO bitacoraDAO) {
		this.bitacoraDAO = bitacoraDAO;
	}

	public void createInterfazAsn(Cambio cambio) throws Exception{
		
		PrintWriter salida = null;
		String nombreArchivo = null;
		AsnDTO registro = null;
		Long idUbicacion = bitacoraDAO.getUbicacionInicialOT(cambio.getOt().getId());
		String nombre = null;
		try {
			// ADJ+ST+PMM+ORG_LVL_NUMBER+yyyymmddhhss+batch_number.TXT
			nombreArchivo = getNombreArchivoASN(cambio.getUbicacion());
			
			//TODO descomentar la primera salida y comentar la segunda para pruebas local (plarrain)
			nombre = SSTTParametros.getInstancia()
					.getParametroPorCodigo(Constants.PATH_ASN).getValor()
					.concat("temp_" + nombreArchivo);
			
			salida = new PrintWriter(nombre);
			
			//------------------------------------------------------------------------
			//salida = new PrintWriter("C:/borrarDataTestABCDIN/".concat(nombre));
			//------------------------------------------------------------------------
			
			registro = getAdjRegistroASN(cambio.getOt(), idUbicacion);

			salida.print(registro.getCabeceraDTO().getCabeceraASN() + "\r\n");
			salida.print(registro.getDetalleAsnDTO().getDetalleASN() + "\r\n");

			salida.flush();
		} catch (FileNotFoundException e) {
			log.error(e.getMessage(), e);
			throw e;
		} finally {
			try {
				if (null != salida)
					salida.close();
				
				String[] nombre2 = nombre.split("temp_");
				boolean renombro = renombrarArchivo(nombre, SSTTParametros.getInstancia().getParametroPorCodigo(Constants.PATH_ASN).getValor().concat(nombre2[1]));
				//TODO ruta local (plarrain)
				//boolean renombro = renombrarArchivo("C:/borrarDataTestABCDIN".concat(nombre), "C:/borrarDataTestABCDIN".concat(SSTTParametros.getInstancia().getParametroPorCodigo(Constants.PATH_ASN).getValor().concat(nombre2[1])));
				if (!renombro) {
					System.out.println("-----> Error intentando cambiar el nombre de fichero.");
			    }
				
			} catch (Exception e2) {
				log.error(e2.getMessage(), e2);
				throw e2;
			}
		}
	}
	
	private String getNombreArchivoASN(Ubicacion ubicacion) {
		Prts comienzaCon = SSTTParametros.getInstancia().getParametroPorCodigo(Constants.STARWITH_ASN);
		Prts sistemaOrg = SSTTParametros.getInstancia().getParametroPorCodigo(Constants.SYSTEM_ORIGEN_ASN);
		Prts sistemaDest = SSTTParametros.getInstancia().getParametroPorCodigo(Constants.SYSTEM_DESTINO_ASN);
		Prts terminaCon = SSTTParametros.getInstancia().getParametroPorCodigo(Constants.ENDSWITH_ASN);
		Long seqASN = utilDAO.getSeqInterfazASN();
		String fechaArchivo = null;
		try {
			fechaArchivo = Utils.formatDate(Calendar.getInstance().getTime(),
					Constants.FORMATO_FECHA_YYYYMMDDHHMMSS);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}
		StringBuilder nombreArchivo = new StringBuilder();
		nombreArchivo.append(comienzaCon.getValor() != null ? comienzaCon.getValor() : "")
				.append(sistemaOrg.getValor() != null ? sistemaOrg.getValor() : "").append(sistemaDest.getValor() != null ? sistemaDest.getValor() : "")
				.append(fechaArchivo)
				.append(seqASN.toString()).append(terminaCon.getValor() != null ? terminaCon.getValor() : "");
		return nombreArchivo.toString();
	}
	
	private AsnDTO getAdjRegistroASN(OrdenTrabajo oT, Long ubicacion) {
		AsnDTO asnDTO = new AsnDTO();
		CabeceraAsnDTO cabecera = getCabeceraAsn(oT, ubicacion);
		DetalleAsnDTO detalle = getDetalleAsn(oT);
		asnDTO.setCabeceraDTO(cabecera);
		asnDTO.setDetalleAsnDTO(detalle);
		return asnDTO;
	}
	
	private CabeceraAsnDTO getCabeceraAsn(final OrdenTrabajo oT,
			final Long ubicacion) {
		CabeceraAsnDTO cabecera = new CabeceraAsnDTO();
		String fechaAsn = null;
		try {
			fechaAsn = Utils.formatDate(Calendar.getInstance().getTime(),
					Constants.FORMATO_FECHA_YYYYMMDD);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}
		cabecera.setHdrGroupNbr(Constants.HDR_GROUP_NBR_CAB_ASN);
		cabecera.setShipmentNbr(Constants.SHIPMENT_NBR.concat(oT.getId().toString().concat(fechaAsn.toString())));
		cabecera.setFacilityCode(SSTTParametros.getInstancia().getParametroPorCodigo(Constants.FACILITY_CODE).getValor());
		cabecera.setCompanyCode(Constants.COMPANY_CODE_ASN);
		cabecera.setTrailerNbr(Constants.VACIO);
		cabecera.setActionCode(Constants.ACTION_CODE_ASN);
		cabecera.setRefNbr(Constants.VACIO);
		cabecera.setShipmentType(Constants.SHIPMENT_TYPE);
		cabecera.setLoadNbr(Constants.VACIO);
		cabecera.setManifestNbr(Constants.VACIO);
		cabecera.setTrailerType(Constants.VACIO);
		cabecera.setVendorInfo(Constants.VACIO);
		cabecera.setOriginInfo(ubicacion.toString());
		cabecera.setOriginCode(Constants.VACIO);
		cabecera.setOrigShippedUnits(Constants.VACIO);
		cabecera.setLockCode(Constants.VACIO);
		cabecera.setShippedDate(fechaAsn);
		cabecera.setOrigShippedLpns(Constants.VACIO);
		cabecera.setCustField1(Constants.CUST_FIELD1);
		cabecera.setCustField2(Constants.VACIO);
		cabecera.setCustField3(Constants.VACIO);
		cabecera.setCustField4(Constants.VACIO);
		cabecera.setCustField5(Constants.VACIO);
		return cabecera;
	}
	
	private DetalleAsnDTO getDetalleAsn(OrdenTrabajo oT) {
		
		Prts putawayType = SSTTParametros.getInstancia().getParametroPorCodigo(Constants.PUT_AWAY_TYPE);
		DetalleAsnDTO detalle = new DetalleAsnDTO();
		Long seqRegIntAsn = new Long(1); //utilDAO.getSeqRegInterfazASN();
		detalle.setHdrGroupNbr(Constants.HDR_GROUP_NBR_DET_ASN);
		detalle.setSeqNbr(seqRegIntAsn);
		detalle.setActionCode(Constants.ACTION_CODE_ASN);
		detalle.setLpnNbr(Constants.VACIO);
		detalle.setLpnWeight(Constants.VACIO);
		detalle.setLpnVolume(Constants.VACIO);
		detalle.setItemAlternateCode(Constants.VACIO);
		detalle.setItemPartA(Constants.ItemPartA.concat(oT.getProducto().getId().toString()));
		detalle.setItemPartB(Constants.VACIO);
		detalle.setItemPartC(Constants.VACIO);
		detalle.setItemPartD(Constants.VACIO);
		detalle.setItemPartE(Constants.VACIO);
		detalle.setItemPartF(Constants.VACIO);
		detalle.setPrePackCode(Constants.VACIO);
		detalle.setPrePackRatio(Constants.VACIO);
		detalle.setPrePackTotalUnits(Constants.VACIO);
		detalle.setInvnAttrA(Constants.VACIO);
		detalle.setInvnAttrB(Constants.VACIO);
		detalle.setInvnAttrC(Constants.VACIO);
		detalle.setShippedQty(Long.parseLong(Constants.SHIPPED_QTY));
		detalle.setPriorityDate(Constants.VACIO);
		detalle.setPoNbr(Constants.VACIO);
		detalle.setPalletNbr(Constants.VACIO);
		detalle.setPutawayType(putawayType.getValor());
		detalle.setExpiryDate(Constants.VACIO);
		detalle.setBatchNbr(Constants.VACIO);
		detalle.setRecvXdockFacilityCode(Constants.VACIO);
		detalle.setCustField1(Constants.VACIO);
		detalle.setCustField2(Constants.VACIO);
		detalle.setCustField3(Constants.VACIO);
		detalle.setCustField4(Constants.VACIO);
		detalle.setCustField5(Constants.VACIO);
		detalle.setLpnIsPhysicalPalletFlg(Constants.VACIO);
		detalle.setPoSeqNbr(Constants.VACIO);
		detalle.setPrePackRatioSeq(Constants.VACIO);
		detalle.setSerialNbr(Constants.VACIO);
		detalle.setLpnLockCode(Constants.VACIO);
		detalle.setItemBarcode(Constants.VACIO);
		detalle.setUom(Constants.VACIO);
		detalle.setLpnLength(Constants.VACIO);
		detalle.setLpnWidth(Constants.VACIO);
		detalle.setLpnHeight(Constants.VACIO);
		detalle.setDtlRcvFlg(Constants.VACIO);
		detalle.setInvnAttrD(Constants.VACIO);
		detalle.setInvnAttrE(Constants.VACIO);
		detalle.setInvnAttrF(Constants.VACIO);
		detalle.setInvnAttrG(Constants.VACIO);
		return detalle;
	}
}