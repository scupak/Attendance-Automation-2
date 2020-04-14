/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package attendance.automation.BLL.Interface;

/**
 *
 * @author SKRUMM
 */
public interface BLLFacadeInterface extends StudentManagerInterface, TeacherManagerInterface
{

    /**
     * encrypts a string using hashing
     * @param password
     * @return hexString
     */
    public String hashPassword(String password);

}
