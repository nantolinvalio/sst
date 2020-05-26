package cl.abcdin.sst.dao.mappers.typeHadlers;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;

public class VarcharToBooleanTypeHandler extends BaseTypeHandler<Boolean> {

	@Override
	public Boolean getNullableResult(ResultSet rs, String columnName) throws SQLException {
		return "S".equals(rs.getString(columnName));
	}

	@Override
	public Boolean getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
		return "S".equals(rs.getString(columnIndex));
	}

	@Override
	public Boolean getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
		return "S".equals(cs.getString(columnIndex));
	}

	@Override
	public void setNonNullParameter(PreparedStatement ps, int columnIndex, Boolean parameter, JdbcType jdbcType) throws SQLException {
		ps.setString(columnIndex, parameter != null && parameter ? "S" : "N");
	}


}
