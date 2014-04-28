package com.abbcc.dao;


import java.io.File;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;


/**
 * @author Hades Guan
 * 
 */
public class HibernateUtil
{
    private static SessionFactory factory = null;

    private static ThreadLocal<Session> local = new ThreadLocal<Session>();

    /**
     * ����Singletonģʽ��ʼ��SessionFactoryʵ��
     * 
     * @return SessionFactoryʵ��
     */
    private static SessionFactory getSessionFactory()
    {
        if (factory == null)
        {
            factory = new AnnotationConfiguration().configure().buildSessionFactory();
        }
        return factory;
    }

    /**
     * ��ȡSessionʵ��
     * 
     * @return Sessionʵ��
     */
    public static Session getSession()
    {
        Session s = local.get();
        if (s == null)
        {
            s = HibernateUtil.getSessionFactory().openSession();
            local.set(s);
        }
        return s;
    }

    public static void closeSession()
    {
        Session s = local.get();
        if (s != null)
            s.close();
        local.set(null);
    }
}
