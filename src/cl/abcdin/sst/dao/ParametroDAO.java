package cl.abcdin.sst.dao;

import java.util.List;

import cl.abcdin.sst.model.Parametro;
import cl.abcdin.sst.model.TipoOT;
import cl.abcdin.sst.model.vo.CondicionRecepcion;

public class ParametroDAO extends BaseDAO {
	
	public List<Parametro> listParametrosByTipo(String tipo) throws Exception {
		return getSqlSessionTemplate().selectList("parametro.listParametrosByTipo", tipo);
	}
	
	public List<Parametro> listParametrosByShowTipo(String tipo) throws Exception {
		return getSqlSessionTemplate().selectList("parametro.listParametrosByShowTipo", tipo);
	}
	
	public Integer updateParametros(Parametro parametro) throws Exception {
		return getSqlSessionTemplate().update("parametro.updateParametros", parametro);
	}
	
	public Integer insertCondicionRecepcion(CondicionRecepcion condicion) throws Exception{
		return getSqlSessionTemplate().insert("parametro.insertCondicionRecepcion", condicion);		
	}
	
	public Integer saveParametros(Parametro parametro)throws Exception{
		return getSqlSessionTemplate().insert("parametro.saveParametros");
	}
	
	public CondicionRecepcion getCondicionRecepcion() throws Exception{
		return getSqlSessionTemplate().selectOne("parametro.getCondicionRecepcion");		
	}
	
	public Integer updateCondicionRecepcion(CondicionRecepcion condicion) throws Exception{
		return getSqlSessionTemplate().update("parametro.updateCondicionRecepcion", condicion);		
	}
	
	public TipoOT getTipoOTbyCodigo(String codigo) throws Exception {
		return getSqlSessionTemplate().selectOne("parametro.getTipoOTbyCodigo",codigo);
	}
	
	public Parametro getRangoParametroById(Long id) throws Exception {
		return getSqlSessionTemplate().selectOne("parametro.getRangoParametroById",id);
	}
	public List<Parametro> listProveedorRemateLiquidacion() throws Exception {
		return getSqlSessionTemplate().selectList("parametro.listProveedorRemateLiquidacion");
	}
}
