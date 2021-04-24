package ru.nazarenko.se.project;

import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SharedSessionContractImplementor;

import java.sql.*;

public class PostgreSQLEnumType extends org.hibernate.type.EnumType {

	public void nullSafeSet(
		PreparedStatement st,
		Object value,
		int index,
		SharedSessionContractImplementor session)
		throws HibernateException, SQLException {
		if(value == null) {
			st.setNull( index, Types.OTHER );
		}
		else {
			st.setObject(
				index,
				value.toString(),
				Types.OTHER
			);
		}
	}
}
