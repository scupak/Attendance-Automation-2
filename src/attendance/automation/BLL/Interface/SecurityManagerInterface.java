/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package attendance.automation.BLL.Interface;

/**
 *
 * @author anton
 */
public interface SecurityManagerInterface {
    
    
    public String hashPassword(String password) throws attendance.automation.BLL.Security.SecurityException;
    
}
