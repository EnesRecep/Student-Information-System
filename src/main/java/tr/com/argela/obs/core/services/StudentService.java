/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tr.com.argela.obs.core.services;

import java.util.List;
import javax.persistence.EntityManager;
import tr.com.argela.obs.core.dao.StudentDao;
import tr.com.argela.obs.core.models.Exam;
import tr.com.argela.obs.core.models.Lecture;
import tr.com.argela.obs.core.models.Lecture;
import tr.com.argela.obs.core.models.ExamTaken;
import tr.com.argela.obs.core.models.Student;
import tr.com.argela.obs.core.models.TakenLecture;


public class StudentService {

    StudentDao dao = new StudentDao();
    

    public List<Student> getStudents(EntityManager em) {
        return dao.getStudents(em);
    }

    public Student createStudent(EntityManager em, Student student) {
        return dao.save(em, student);
    }

    public void deleteStudent(EntityManager em, String id) {
        dao.delete(em, id);
    }

    public Student searchStudent(EntityManager em, String sid) {
        return dao.searchStudent(em, sid);
    }

   

    public boolean login(EntityManager em, String id, String pass) {
        return dao.login(em,id, pass);
    }

    public Student showSelfInfo(EntityManager em , String id) {
        return dao.showSelfInfo(em,id);
    }
    

    public List<ExamTaken> getExamGrades(EntityManager em, String id) {
        return dao.getExamGrades(em, id);
    }
    
    

    public List<Lecture> getLectures(EntityManager em) {
        return dao.getLectures(em);
    }

    public List<Lecture> showLectures(EntityManager em) {
        return (List<Lecture>) dao.showLectures(em);

    }
    
    public List<TakenLecture> getLetterGrades(EntityManager em, String id) {
        return dao.getLetterGrades(em, id);
    }


    public List<Exam> showExamInfo(EntityManager em, String id) {
        return dao.showExamInfo(em,id);
    }

   
}
