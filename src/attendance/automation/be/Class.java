/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package attendance.automation.be;

import javafx.beans.property.SimpleIntegerProperty;


/**
 *
 * @author SKRUMM
 */
public class Class
{

    private final String teacherUsrName;
    private final SimpleIntegerProperty classID;
    private final String studentUsrName;
    private final String[] classes;
    
        public Class(int classID, String teacherUsrName, String studentUsrName, String[] classes)
    {
        this.classID = new SimpleIntegerProperty(classID);
        this.teacherUsrName = teacherUsrName;
        this.studentUsrName = studentUsrName;
        this.classes = classes;
    }
    
}
