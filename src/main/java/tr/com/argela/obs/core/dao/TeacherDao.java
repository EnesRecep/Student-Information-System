/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tr.com.argela.obs.core.dao;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import tr.com.argela.obs.core.models.Exam;
import tr.com.argela.obs.core.models.Lecture;
import tr.com.argela.obs.core.models.Student;
import tr.com.argela.obs.core.models.Teacher;

/**
 *
 * @author omer
 */
public class TeacherDao {

    public List<Teacher> getTeacher(EntityManager em) {
        Query query = em.createQuery("select s from Teacher s");
        return query.getResultList();
    }

    public Teacher saveTeacher(EntityManager em, Teacher teacher) {
        Teacher saved = em.merge(teacher);
        em.flush();
        return saved;
    }

    public void delete(EntityManager em, String id) {
        Query query = em.createQuery("Delete from Teacher s where s.id = :id");
        int int_id = Integer.parseInt(id);
        query.setParameter("id", int_id);
        query.executeUpdate();

    }

    public boolean login(EntityManager em, String id, String pass) {
        Query query = em.createQuery("select t from Teacher t where t.id = :id and t.password= :password");
        int int_id = Integer.parseInt(id);
        query.setParameter("id", int_id);
        query.setParameter("password", pass);
        return query.getResultList().size() == 1;
    }

    public void setGrade(EntityManager em, String pass, int not, int sid) {
        Query query = em.createQuery("update Grade set grade= :not where t_id = :pass and s_id= :sid");
        int t_id = Integer.parseInt(pass);
        query.setParameter("pass", t_id);
        query.setParameter("not", not);
        query.setParameter("sid", sid);

    }

    public Teacher showSelfTeachers(EntityManager em, String id) {
        Query query = em.createQuery("select s from Teacher s where id=:id");
        int t_id = Integer.parseInt(id);
        query.setParameter("id", id);
        return (Teacher) query.getSingleResult();
    }

    public Exam showExamInfo(EntityManager em, String id) {
        Query query = em.createQuery("select s from Teacher s where id=:id");
        int t_id = Integer.parseInt(id);
        query.setParameter("id", id);
        return (Exam) query.getSingleResult();
    }

    public List<Lecture> getLectures(EntityManager em) {
        Query query = em.createQuery("select s from Lecture s");
        return query.getResultList();
    }

    public List<Lecture> showLectures(EntityManager em) {
        Query query = em.createQuery("SELECT s FROM Lecture s");

        return (List<Lecture>) query.getResultList();
    }

    public List<Lecture> getTeacherLectures(EntityManager em, String id) {
        Query query = em.createQuery("select l from Lecture l,Teacher t where  l.tId=t.id and t.id=:id");
        int t_id = Integer.parseInt(id);
        query.setParameter("id", t_id);

        return query.getResultList();
    }

    public List<Lecture> showTeacherLectures(EntityManager em, String id) {
        Query query = em.createQuery("select l from Lecture l,Teacher t where  l.tId=t.id and t.id=:id");

        query.setParameter("id", Integer.parseInt(id));
        return (List<Lecture>) query.getResultList();
    }

    public void addExam(EntityManager em, String id, Exam exam) {


        em.persist(exam);

        em.getTransaction().commit();
        em.close();
        
        /*
        Query query = em.createQuery("INSERT INTO Exam (lectureId , name ,semester, sHour, eHour, percentage) values"
                + "(:lId, :name, :semester, :sHour, :eHour, :percentage) ");

        query.setParameter("lId", exam.getLectureId().getId());
        query.setParameter("name", exam.getName());
        query.setParameter("semester", exam.getSemester());
        query.setParameter("sHour", exam.getSHour());
        query.setParameter("eHour", exam.getEHour());
        query.setParameter("percentage", exam.getPercentage());

        */

    }

    
    public List<Exam> showGivenExams(EntityManager em , String id){
        Query query = em.createQuery("SELECT e from Exam e , Lecture l , Teacher t where"
                + " t.id = l.tId and l.id = e.lectureId.id and t.id = :id");
        query.setParameter("id", Integer.parseInt(id));
        
        System.out.println(query.getResultList().get(0).toString());
        return (List<Exam>) query.getResultList();
        
    }

}
