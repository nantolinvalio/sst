package cl.abcdin.sst.dao.mappers.typeHadlers;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedJdbcTypes;

import cl.abcdin.sst.utils.Utils;

@MappedJdbcTypes(JdbcType.NUMERIC)
public class RunTypeHandler extends BaseTypeHandler<String>{

	@Override
	public String getNullableResult(ResultSet rs, String columnName) throws SQLException {
		try {
			return Utils.getRunFromId(rs.getLong(columnName));
		} catch (Exception e) {
			throw new SQLException(e);
		}
	}

	@Override
	public String getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
		try {
			return Utils.getRunFromId(rs.getLong(columnIndex));
		} catch (Exception e) {
			throw new SQLException(e);
		}
	}

	@Override
	public String getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
		try {
			return Utils.getRunFromId(cs.getLong(columnIndex));
		} catch (Exception e) {
			throw new SQLException(e);
		}
	}

	@Override
	public void setNonNullParameter(PreparedStatement ps, int columnIndex, String parameter, JdbcType jdbcType) throws SQLException {
		try {
			ps.setLong(columnIndex, Utils.getIdFromRun(parameter));
		} catch (Exception e) {
			throw new SQLException(e);
		}
	}


}
