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
    private  SimpleStringProperty classname;
    private int classid;
    private  String teacherUsrName;
    private  String studentUsrName;
    private  String[] classes;
    
        public Class(String classname,int classid, String teacherUsrName, String studentUsrName, String[] classes)
    {
        this.classname = new SimpleStringProperty(classname);
        this.classid = classid;
        this.teacherUsrName = teacherUsrName;
        this.studentUsrName = studentUsrName;
        this.classes = classes;
        
    }

    public Class(String classname, int classid) {
        this.classname.set(classname);
        this.classid = classid;
    }

    public String getClassName() {
        return classname.get();
    }

    public void setClassName(String classname) {
        this.classname.set(classname);
    }

    public int getClassid() {
        return classid;
    }

    public void setClassid(int classid) {
        this.classid = classid;
    }
    
    
    
        
       
    
}
