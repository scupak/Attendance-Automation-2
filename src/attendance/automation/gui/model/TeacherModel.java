/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package attendance.automation.gui.model;

import attendance.automation.BLL.BLLFacade;
import attendance.automation.gui.model.Interface.TeacherModelInterface;
import java.io.IOException;

/**
 *
 * @author kacpe
 */
public class TeacherModel implements TeacherModelInterface
{
    private BLLFacade bllfacade;
    
    public TeacherModel() throws IOException
    {
        bllfacade = new BLLFacade();
    }
            
}
