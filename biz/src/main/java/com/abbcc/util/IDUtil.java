/**
 * Copyright (c) 2010 Abbcc Corp.
 * No 225,Wen Yi RD, Hang Zhou, Zhe Jiang, China.
 * All rights reserved.
 *
 * "IDUtil.java is the copyrighted,
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

package com.abbcc.util;
import org.hibernate.id.Configurable;
import org.hibernate.id.IdentifierGenerator;
import java.io.Serializable; 
import java.sql.SQLException; 
import java.util.Properties;
import java.util.UUID;

import org.hibernate.HibernateException; 
import org.hibernate.MappingNotFoundException;
import org.hibernate.dialect.Dialect; 
import org.hibernate.engine.SessionImplementor; 
import org.hibernate.type.Type;
import org.hibernate.util.PropertiesHelper;

import com.abbcc.helper.IdentifierGeneratorHelper;

public class IDUtil  implements IdentifierGenerator, Configurable {
    //private static final String SEQ_NAME = "seq_id";
    public static final String SEQUENCE = "sequence";
    public static final String PREFIX = "prefix";
    public static final String SCHEMA = "schema";
    public static final String PARAMETERS = "parameters";
    public static final String className = "com.abbcc.util.IDUtil";
    //public static final String NUMBERS = "numbers";

    private String parameters;
    private Type type;
    private String sequenceName;
    private String prefix;
    private String schemaName;
    //private int numbers;

    /**
     * 配置函数<br>
     <P><DL>
     <DT><B>说明：从配置文件中得到参数等信息</B><DD>
     <pre>
            无
     </pre>
     </DL>
     * @param	type		hibernate类型
     * @param	params		参数
     * @param	dialect		数据库方言
     * @return	null
     */
    public void configure(Type type, Properties params, Dialect dialect) throws
            MappingNotFoundException {
        this.sequenceName = PropertiesHelper.getString(SEQUENCE, params,
                "hibernate_sequence");
        this.prefix = PropertiesHelper.getString(PREFIX, params, "");
        this.parameters = params.getProperty(PARAMETERS);
        String schemaName = params.getProperty(SCHEMA);
        //if ( schemaName!=null && sequenceName.indexOf(StringHelper.DOT)<0 )
        //        sequenceName = schemaName + '.' + sequenceName;
        this.type = type;
        //sql = dialect.getSequenceNextValString(sequenceName);
        //sequenceName = schemaName + parameters;
    }

    /**
     * 序列号生成函数<br>
     <P><DL>
     <DT><B>说明：从数据库sequence中生成序列号</B><DD>
     <pre>
            无
     </pre>
     </DL>
     * @param	session
     * @param	obj
     * @return	Serializable
     */
    public Serializable generate(SessionImplementor session, Object obj) throws
            HibernateException {
        obj.getClass();
        //beans.isInstanceOf(obj,obj.getClass());
        Integer value = new Integer(0);
		try {
			value = IdentifierGeneratorHelper.getInstance().
			                generateBySequence(session, obj, sequenceName);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        if (prefix == "") 
        {
            int number2 = 32;
            return prefix +
                     StringUtil.fillString(value.toString(), '0', number2);
        } 
        else 
        {
            int number1 = 32 - 1 - prefix.length();
            return prefix + "_" +
                    StringUtil.fillString(value.toString(), '0', number1);
        }
    }
    
    public static String getUUID(){
    	UUID uuid  =  UUID.randomUUID(); 
    	String s = UUID.randomUUID().toString();
    	return s;
    }
}
