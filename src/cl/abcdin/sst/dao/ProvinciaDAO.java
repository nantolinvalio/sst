package cl.abcdin.sst.dao;

import java.util.List;

import cl.abcdin.sst.model.Provincia;

public class ProvinciaDAO extends BaseDAO{
    public List<Provincia> listProvinciaByIdRegion(Integer idRegion) throws Exception {
        return getSqlSessionTemplate().selectList("provincia.listProvinciaByIdRegion",idRegion);
    }

}
