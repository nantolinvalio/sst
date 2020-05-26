package cl.abcdin.sst.dao.mappers.typeHadlers;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;

import org.apache.commons.lang.StringUtils;
import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedJdbcTypes;

import cl.abcdin.sst.utils.Constants;

@MappedJdbcTypes(JdbcType.NUMERIC)
public class CodigoBarrasTypeHandler extends BaseTypeHandler<String>{

	@Override
	public String getNullableResult(ResultSet rs, String columnName) throws SQLException {
		try {
			if (rs.getString(columnName) == null) {
				return null;
			}
			return StringUtils.leftPad(rs.getString(columnName),Constants.LARGO_CODIGO_BARRA,Constants.LEFT_PAD_CODIGO_BARRA);
		} catch (Exception e) {
			throw new SQLException(e);
		}
	}

	@Override
	public String getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
		try {
			if (rs.getString(columnIndex) == null) {
				return null;
			}
			return StringUtils.leftPad(rs.getString(columnIndex),Constants.LARGO_CODIGO_BARRA,Constants.LEFT_PAD_CODIGO_BARRA);
		} catch (Exception e) {
			throw new SQLException(e);
		}
	}

	@Override
	public String getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
		try {
			if (cs.getString(columnIndex) == null) {
				return null;
			}
			return StringUtils.leftPad(cs.getString(columnIndex),Constants.LARGO_CODIGO_BARRA,Constants.LEFT_PAD_CODIGO_BARRA);
		} catch (Exception e) {
			throw new SQLException(e);
		}
	}

	@Override
	public void setNonNullParameter(PreparedStatement ps, int columnIndex, String parameter, JdbcType jdbcType) throws SQLException {
		try {
			if (parameter == null || parameter.equals("")) {
				ps.setNull(columnIndex, Types.NUMERIC);
			} else {
				parameter = parameter.replaceFirst("^0+(?!$)", "");
				Long numero;
				try {
					numero = Long.parseLong(parameter);
				} catch (Exception e) {
					numero = 0L;
				}
				ps.setLong(columnIndex, numero);				
			}
		} catch (Exception e) {
			throw new SQLException(e);
		}
	}
}
