/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lexAbogado.webService;

import com.lexAbogado.domain.UserDataLoggin;
import com.lexAbogado.persistence.config.BaitsConfiguration;
import com.lexAbogado.persistence.config.IBatisConfiguratorException;
import com.lexAbogado.persistence.mapper.PersistenceLexAbogadoMapper;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Date;
import java.util.Properties;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import org.apache.ibatis.session.SqlSession;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

/**
 *
 * @author jfuentealba
 */
@Path("ServiceLexAbogado")
public class ServiceLexAbogado {
        private final static Logger LOGGER = Logger.getLogger(ServiceLexAbogado.class);
    private static Properties configProps = new Properties();
    private static Properties logProps    = new Properties();
    private static Properties logPropsMail= new Properties();
    private static SqlSession sessionLexAbogado = null;
    
    public void init() throws FileNotFoundException, IOException{
        System.out.println("com.lexAbogado.webService.ServiceLexAbogado.init()");
        final String LOG_PROPS = "config/log4j.properties";
        final String CONF_PROPS = "config/config.properties";
        logProps.load(new FileInputStream(LOG_PROPS));
        PropertyConfigurator.configure(logProps);
        LOGGER.info("LOG SERVICE INITIALIZED SUCCESSFULLY");
        configProps.load(new FileInputStream(CONF_PROPS));
        PropertyConfigurator.configure(configProps);
    }
    @GET
    @Produces(javax.ws.rs.core.MediaType.APPLICATION_JSON)
    //@Consumes(javax.ws.rs.core.MediaType.APPLICATION_JSON)
    //@Path("obtenerClientByRut")
    @Path("loggin")
    public String Loggin() throws IBatisConfiguratorException{//@WebParam(name = "user") String user, @WebParam(name = "Pass") String pass) throws IBatisConfiguratorException{
        System.out.println("com.lexAbogado.webService.ServiceLexAbogado.Loggin()");
        final BaitsConfiguration ic = new BaitsConfiguration();
        LOGGER.info("Comienza el servicio: "+ new Date());
        sessionLexAbogado = ic.getSqlSession("development", configProps);
        final PersistenceLexAbogadoMapper mapperLexAbogado = sessionLexAbogado.getMapper(PersistenceLexAbogadoMapper.class);  
        UserDataLoggin userLoggin = mapperLexAbogado.getUserLoggin();
        LOGGER.info(userLoggin.toString());
        return "Hola Mundo";
    }
    
}
