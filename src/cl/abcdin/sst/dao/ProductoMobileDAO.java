package cl.abcdin.sst.dao;

import java.util.List;

import cl.abcdin.sst.model.mobile.ProductoMobile;

public class ProductoMobileDAO extends BaseDAO{
	public List<ProductoMobile> listProductosByIdIventarioUbicacion(Long idInventarioUbicacion) throws Exception {
		return getSqlSessionTemplate().selectList("productoMobile.listProductosByIdIventarioUbicacion",idInventarioUbicacion);
	}
	
	public ProductoMobile getProductoByIdOT(Long idOT) throws Exception {
		return getSqlSessionTemplate().selectOne("productoMobile.getProductoByIdOT",idOT);
	}
}
