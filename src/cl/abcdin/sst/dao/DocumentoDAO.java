package cl.abcdin.sst.dao;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cl.abcdin.sst.model.Documento;

public class DocumentoDAO extends BaseDAO{
	
	@SuppressWarnings("unchecked")
	public Documento getDocumentoByIdAndTipo(Documento documento) throws Exception {

	    Map<String, Object> params = new HashMap<String, Object>(); 
	    ResultSet rs = null;
	    params.put("id", documento.getId());
	    params.put("tipo", documento.getTipo());
	    params.put("cursor", rs);
	    
	    getSqlSessionTemplate().selectOne("documento.getDocumentoByIdAndTipo", params);
	    
	    
	    List<Documento> documentos = (ArrayList<Documento>)params.get("cursor");
	    
	    if (documentos.size() == 0) {
	    	return null;
	    }
	    
//		Date fecha = new Date();
//		String strFecha = "03082014";
//		SimpleDateFormat format = new SimpleDateFormat("ddMMyyyy");
//		fecha = format.parse(strFecha);
//		documentos.get(0).setFechaEmision(fecha);
	    
	    return documentos.get(0);
	    
	}
}
