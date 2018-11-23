/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lexAbogado.persistence.config;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import java.io.Reader;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

/**
 *
 * @author jfuentealba
 */
public class BaitsConfiguration {
    
    //private static final String DEFAULT_CONFIG_MAITENIX =	"com/latam/arq/commons/persistence/config/PersistenceConfig.xml";
    private static final String DEFAULT_CONFIG = 		"com/lexAbogado/arq/persistence/config/PersistenceLexAbogado.xml";
    private static Map<String, SqlSessionFactory> factories = new HashMap<String, SqlSessionFactory>();
    private static SqlSessionFactory factory = null;
    private SqlSession sqlSession = null;
    
	
	public static SqlSession getSqlSession(final String environment,final Properties props) throws IBatisConfiguratorException {
		return getSqlSession(DEFAULT_CONFIG, environment, props);
	}
    public static SqlSession getSqlSession(final String resourceName,final String environment, final Properties props)
			throws IBatisConfiguratorException {
    	//Se tiene que reiniciar las factory
    	factories = new HashMap<String, SqlSessionFactory>();
		try {
			if (!factories.containsKey(environment)) {
				final Reader reader = Resources.getResourceAsReader(resourceName);
				if (reader == null) {
					throw new IBatisConfiguratorException(
							"No puede abrir archivo configuracion MyBatis. ("
									+ resourceName + ")");
				}
				final SqlSessionFactoryBuilder factoryBuild = new SqlSessionFactoryBuilder();
				factories.put(environment,factoryBuild.build(reader, environment, props));
			}
			return factories.get(environment).openSession();
		} catch (final IOException e) {
			throw new IBatisConfiguratorException(e);
		} catch (final Exception e) {
			throw new IBatisConfiguratorException(e);
		}
	}
    
}
