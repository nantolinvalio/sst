package cl.abcdin.sst.dao;

import org.mybatis.spring.SqlSessionTemplate;


public class BaseDAO {
	private SqlSessionTemplate sqlSessionTemplate;

	protected SqlSessionTemplate getSqlSessionTemplate() {
		return sqlSessionTemplate;
	}

	public void setSqlSessionTemplate(SqlSessionTemplate sqlSessionTemplate) {
		this.sqlSessionTemplate = sqlSessionTemplate;
	}
}
