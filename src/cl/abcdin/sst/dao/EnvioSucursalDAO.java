package cl.abcdin.sst.dao;

import java.util.List;

import cl.abcdin.sst.model.EnvioSucursal;
import cl.abcdin.sst.model.filters.FilterEnvioSucursal;
import cl.abcdin.sst.model.filters.GridControl;

public class EnvioSucursalDAO extends BaseDAO{
	public List<EnvioSucursal> listEnviosToSucursal(FilterEnvioSucursal filterEnvioSucursal,GridControl gridControl){
		return getSqlSessionTemplate().selectList("envioSucursal.listEnviosToSucursal",filterEnvioSucursal,gridControl.getRowBounds());
	}
	public List<EnvioSucursal> listEnviosToSucursal(FilterEnvioSucursal filterEnvioSucursal){
		return getSqlSessionTemplate().selectList("envioSucursal.listEnviosToSucursal",filterEnvioSucursal);
	}
	
	public Integer getTotalEnviosToSucursal() throws Exception{
		return getSqlSessionTemplate().selectOne("envioSucursal.getTotalEnviosToSucursal");
	}
	
	public Integer save(EnvioSucursal envioSucursal) throws Exception{
		return getSqlSessionTemplate().insert("envioSucursal.save",envioSucursal);
	}
	
	public EnvioSucursal getEnvioToSucursalByIdSucursal(Integer idSucursal) throws Exception {
		return getSqlSessionTemplate().selectOne("envioSucursal.getEnvioToSucursalByIdSucursal",idSucursal);
	}
	
	public Integer cerrarEnvioSucursalByEnvioSucursa(EnvioSucursal envioSucursal) throws Exception {
		return getSqlSessionTemplate().update("envioSucursal.cerrarEnvioSucursalByEnvioSucursal",envioSucursal);
	}
	
}
