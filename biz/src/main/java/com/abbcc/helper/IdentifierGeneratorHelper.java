/**
 * Copyright (c) 2010 Abbcc Corp.
 * No 225,Wen Yi RD, Hang Zhou, Zhe Jiang, China.
 * All rights reserved.
 *
 * "IdentifierGeneratorHelper.java is the copyrighted,
 * proprietary property of Abbcc Company and its
 * subsidiaries and affiliates which retain all right, title and interest
 * therein."
 * 
 * Revision History
 *
 * Date              Programmer                   Notes
 * ---------    ---------------------  --------------------------------------------
 * 2009-12-3           yixiugg                      initial
 **/

package com.abbcc.helper;

import java.io.Serializable;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.HibernateException;
import org.hibernate.engine.SessionImplementor;
import org.hibernate.type.IntegerType;
import org.hibernate.type.Type;

public class IdentifierGeneratorHelper {

	private static final Log log = LogFactory
			.getLog(IdentifierGeneratorHelper.class);

	private IdentifierGeneratorHelper() {

	}

	public static IdentifierGeneratorHelper getInstance() {
		return new IdentifierGeneratorHelper();
	}

	public Integer generateBySequence(SessionImplementor session, Object obj,
			String sequenceName) throws SQLException, HibernateException {
		String sql = "select " + sequenceName + ".nextval from dual";
		PreparedStatement st = session.getBatcher().prepareStatement(sql);
		Type type = new IntegerType();
		try {
			ResultSet rs = st.executeQuery();
			Serializable tempResult = null;
			try {
				rs.next();
				//tempResult = IdentifierGeneratorFactory.get(rs, type);
				Class clazz = type.getReturnedClass();
				if ( clazz == Long.class ) {
					tempResult= new Long( rs.getLong( 1 ) );
				}
				else if ( clazz == Integer.class ) {
					tempResult= new Integer( rs.getInt( 1 ) );
				}
				else if ( clazz == Short.class ) {
					tempResult= new Short( rs.getShort( 1 ) );
				}
				else if ( clazz == String.class ) {
					tempResult= rs.getString( 1 );
				}
			} finally {
				rs.close();
			}
			if (log.isDebugEnabled()) {
				log.info("Sequence generated: " + tempResult);
			}
			return (Integer) tempResult;
		}

		finally {
			session.getBatcher().closeStatement(st);
		}
	}
}
