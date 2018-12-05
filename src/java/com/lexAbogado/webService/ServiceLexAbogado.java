/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lexAbogado.webService;

import com.lexAbogado.domain.UserDataLoggin;
import com.lexAbogado.domain.LoginData;
import com.lexAbogado.domain.Presupuestos;
import com.lexAbogado.persistence.config.BaitsConfiguration;
import com.lexAbogado.persistence.config.IBatisConfiguratorException;
import com.lexAbogado.persistence.mapper.PersistenceLexAbogadoMapper;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import javax.jws.WebParam;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.PathParam;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import org.apache.ibatis.session.SqlSession;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

/**
 *
 * @author jfuentealba
 */
@Path("/ServiceLexAbogado")
public class ServiceLexAbogado {
    private final static Logger LOGGER = Logger.getLogger(ServiceLexAbogado.class);
    private static Properties configProps = new Properties();
    private static Properties logProps    = new Properties();
    private static Properties logPropsMail= new Properties();
    private static SqlSession sessionLexAbogado = null;
    
        
    @Context
    private UriInfo context;

    /**
     * Creates a new instance of NotariaProduct
     */
    public ServiceLexAbogado() throws FileNotFoundException, IOException {
        System.out.println("com.lexAbogado.webService.ServiceLexAbogado.init()");
        final String LOG_PROPS = "config/log4j.properties";
        final String CONF_PROPS = "config/config.properties";
        logProps.load(new FileInputStream(LOG_PROPS));
        PropertyConfigurator.configure(logProps);
        LOGGER.info("LOG SERVICE INITIALIZED SUCCESSFULLY");
        configProps.load(new FileInputStream(CONF_PROPS));
        PropertyConfigurator.configure(configProps);
    }
    
    /*public void init() throws FileNotFoundException, IOException{
        System.out.println("com.lexAbogado.webService.ServiceLexAbogado.init()");
        final String LOG_PROPS = "config/log4j.properties";
        final String CONF_PROPS = "config/config.properties";
        logProps.load(new FileInputStream(LOG_PROPS));
        PropertyConfigurator.configure(logProps);
        LOGGER.info("LOG SERVICE INITIALIZED SUCCESSFULLY");
        configProps.load(new FileInputStream(CONF_PROPS));
        PropertyConfigurator.configure(configProps);
    }*/
    @POST
    @Produces(javax.ws.rs.core.MediaType.APPLICATION_JSON)
    @Consumes(javax.ws.rs.core.MediaType.APPLICATION_JSON)
    //@Path("obtenerClientByRut")
    @Path("/loggin")
    public UserDataLoggin Loggin(LoginData login) throws IBatisConfiguratorException{//@WebParam(name = "user") String user, @WebParam(name = "Pass") String pass) throws IBatisConfiguratorException{
        
        
        UserDataLoggin userLoggin = new UserDataLoggin();
        try{
            final BaitsConfiguration ic = new BaitsConfiguration();
            LOGGER.info("Comienza el servicio: "+ new Date());
            sessionLexAbogado = ic.getSqlSession("development", configProps);
            final PersistenceLexAbogadoMapper mapperLexAbogado = sessionLexAbogado.getMapper(PersistenceLexAbogadoMapper.class);  
            Map<String, Object> parm = new HashMap<>();
            parm.put("user", login.getUser());
            parm.put("pass", login.getPass());
            userLoggin = mapperLexAbogado.getUserLoggin(parm);
            LOGGER.info(userLoggin.toString());
            userLoggin.setCodigo(0);
            userLoggin.setMensaje("success");
            return userLoggin;
        }
        catch(NullPointerException e){
            userLoggin = new UserDataLoggin();
            LOGGER.error("No trajo data: " +e);
            userLoggin.setCodigo(-1);
            userLoggin.setMensaje(e.toString());
            return userLoggin;
        }
        catch(IBatisConfiguratorException e){
            userLoggin = new UserDataLoggin();
            LOGGER.error("IBatisConfiguratorException: " +e);
            userLoggin.setCodigo(-2);
            userLoggin.setMensaje(e.toString());
            return userLoggin;
        }
        catch(Exception e){
            userLoggin = new UserDataLoggin();
            LOGGER.error("Exception: " +e);
            userLoggin.setCodigo(-3);
            userLoggin.setMensaje(e.toString());
            return userLoggin;
        }
    }
    
    @POST
    @Produces(javax.ws.rs.core.MediaType.APPLICATION_JSON)
    @Consumes(javax.ws.rs.core.MediaType.APPLICATION_JSON)
    //@Path("obtenerClientByRut")
    @Path("/getAllPresupuesto")
    public List<Presupuestos> getAllPresupuesto(UserDataLoggin userDataLoggin) throws IBatisConfiguratorException{
        final BaitsConfiguration ic = new BaitsConfiguration();
        LOGGER.info("Comienza el servicio: "+ new Date());
        sessionLexAbogado = ic.getSqlSession("development", configProps);
        final PersistenceLexAbogadoMapper mapperLexAbogado = sessionLexAbogado.getMapper(PersistenceLexAbogadoMapper.class);  
        Map<String, Object> parm = new HashMap<>();
        parm.put("idUsuario", userDataLoggin.getIdUser());
        return mapperLexAbogado.getPresupuestoByUserId(parm);
    }
    
}
