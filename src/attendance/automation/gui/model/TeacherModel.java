/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package attendance.automation.gui.model;

import attendance.automation.BLL.BLLFacade;
import attendance.automation.BLL.Interface.BLLFacadeInterface;
import attendance.automation.be.Teacher;
import attendance.automation.dal.AttendanceAutomationDalException;
import attendance.automation.gui.model.Interface.TeacherModelInterface;
import java.io.IOException;
import javafx.collections.ObservableList;
import attendance.automation.be.Class;
import attendance.automation.be.Student;
import javafx.collections.FXCollections;

/**
 *
 * @author kacpe
 */
public class TeacherModel implements TeacherModelInterface
{
    private BLLFacadeInterface bllfacade;
    private Teacher currentTeacher;
    private Class currentClass;
    private  ObservableList<Student> Students;
    public TeacherModel() throws IOException, Exception
    {
        bllfacade = new BLLFacade();
        Students = FXCollections.observableArrayList();
        
    }

    @Override
    public Teacher getCurrentTeacher()
    {
        return currentTeacher;
    }

    @Override
    public void setCurrentTeacher(Teacher currentTeacher) throws AttendanceAutomationDalException
    {
        this.currentTeacher = bllfacade.getTeacher(currentTeacher);
    }
    
    @Override
    public boolean checkCredTeacher(Teacher t) throws AttendanceAutomationDalException{
        return bllfacade.checkCredTeacher(t);
    }
            
    @Override
    public ObservableList<Class> classList(String username)
    {
        return bllfacade.getTeacherClasses(username);
    }

    @Override
    public ObservableList<Student> teacherStudentList(int classid) throws AttendanceAutomationDalException {
        
        
        Students.clear();
        Students.addAll( bllfacade.getTeacherStudentList(classid));
         
        return Students;
    }

    @Override
    public Class getCurrentClass() {
        return currentClass;
    }

    @Override
    public void setCurrentClass(Class currentClass) {
        this.currentClass = currentClass;
    }
    
    
    
            
}
