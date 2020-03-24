/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package attendance.automation.be;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;


/**
 *
 * @author SKRUMM
 */
public class Class
{

    private  String teacherUsrName;
    private  SimpleStringProperty classID;
    private  String studentUsrName;
    private  String[] classes;
    
        public Class(String classID, String teacherUsrName, String studentUsrName, String[] classes)
    {
        this.classID = new SimpleStringProperty(classID);
        this.teacherUsrName = teacherUsrName;
        this.studentUsrName = studentUsrName;
        this.classes = classes;
    }

    public Class(SimpleStringProperty classID) {
        this.classID = classID;
    }

    public String getClassID() {
        return classID.get();
    }

    public void setClassID(String classID) {
        this.classID.set(teacherUsrName);
    }
    
    
        
       
    
}
