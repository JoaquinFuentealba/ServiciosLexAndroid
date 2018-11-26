/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lexAbogado.persistence.mapper;

import com.lexAbogado.domain.UserDataLoggin;
import java.sql.SQLDataException;
import java.util.Map;

/**
 *
 * @author jfuentealba
 */
public interface PersistenceLexAbogadoMapper {
    public UserDataLoggin getUserLoggin(Map<String, Object> parm) throws SQLDataException;
}
