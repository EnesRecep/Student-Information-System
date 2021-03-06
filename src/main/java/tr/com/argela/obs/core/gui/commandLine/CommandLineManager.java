/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tr.com.argela.obs.core.gui.commandLine;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import tr.com.argela.obs.core.app.Application;
import tr.com.argela.obs.core.gui.GuiManager;
import tr.com.argela.obs.core.models.Lecture;
import tr.com.argela.obs.core.models.ExamTaken;
import tr.com.argela.obs.core.models.Exam;

import tr.com.argela.obs.core.models.Student;
import tr.com.argela.obs.core.models.TakenLecture;
import tr.com.argela.obs.core.models.Teacher;
import tr.com.argela.obs.core.remote.MainService;

/**
 *
 * @author aybuke
 */
public class CommandLineManager implements GuiManager {

    ResourceBundle label;
    BufferedReader in;

    private MainService getMainService() {
        return Application.getApp().getMainService();
    }

    @Override
    public void start() throws Exception {
        label = ResourceBundle.getBundle("label.label", new Locale("tr", "TR"));
        in = new BufferedReader(new InputStreamReader(System.in));
        showLoginScreen();

    }

    private void studentScreen(String id) throws IOException, Exception {
        
        System.out.println(label.getString("STUDENT_INFORMATION_SYSTEM"));
        hr();
        System.out.println("1 ) " + label.getString("EXIT"));
        System.out.println("2 ) " + label.getString("STUDENT_INFO"));
        System.out.println("3 ) " + label.getString("STUDENT_TAKEN_LECTURE"));
        System.out.println("4 ) " + label.getString("STUDENT_SELECT_LECTURE"));
        System.out.println("5 ) " + label.getString("STUDENT_EXAM_INFO"));
        System.out.println("6 ) " + label.getString("STUDENT_GRADE"));

        System.out.println("7 ) " + label.getString("STUDENT_SEE_LECTURES"));

        Integer selection = toInt(read(label.getString("SELECT_ACTION")));

        if (selection == null) {
            System.out.println(label.getString("CHOICE_ERR"));

        } else {
            switch (selection) {
                case 1: {

                    System.exit(0);

                }
                case 2: {
                    showSelfInfo(id);
                    break;
                }
                case 3: {
                    showTakenLectures();
                    break;
                }
                case 4: {
                    selectLectures();
                    break;
                }
                case 5: {
                    showExamInfo(id);
                    break;
                }
                case 6: {
                    System.out.println("1 ) " + label.getString("EXAM_GRADE"));
                    System.out.println("2 ) " + label.getString("LETTER_GRADE"));

                    Integer gradeType = toInt(read(label.getString("SELECT_ACTION")));

                    if (gradeType == null) {
                        System.out.println(label.getString("CHOICE_ERR"));

                    } else {

                        switch (gradeType) {
                            case 1: {

                                showExamGrades(id);
                                break;
                            }
                            case 2: {
                                showLetterGrades(id);
                                break;
                            }
                            

                        }
                        /*
                    case 3: {
                        addStudent();
                        break;
                    }
                    case 4: {
                        deleteStudent();
                    }*/
                    }
                    break;
                }

                case 7: {
                    
                    
                   System.out.println("1 ) " + label.getString("ALL_COURSES"));
                    System.out.println("2 ) " + label.getString("OWN_COURSES"));

                    Integer gradeType = toInt(read(label.getString("SELECT_ACTION")));

                    if (gradeType == null) {
                        System.out.println(label.getString("CHOICE_ERR"));

                    } else {

                        switch (gradeType) {
                            case 1: {

                                showLectures();
                                break;
                            }
                            case 2: {
                                
                                showTeacherLectures(id);
                                break;
                            }

                        }
                    
                    }
                    break;
                }
                
            

            }
        }

        studentScreen(id);

    }

    private void teacherScreen(String id) throws IOException, Exception {

        System.out.println("Ogretmen Bilgi Sistemi");
        hr();
        System.out.println("1 ) Cikis");
        System.out.println("2 ) Ogretmen Goster");
        System.out.println("3 ) Ogretmen Ekle");
        System.out.println("4 ) Ogretmen Sil");
        System.out.println("5 ) Ogrenci Ara");
        System.out.println("6 ) Not Girisi");
        System.out.println("7 ) Dersleri Goruntule");
        
        //TODO Add here 8) Sınav Ekle
        //Currently it is in the menu 7-3
        
       
        
 
        Integer selection = toInt(read("Seciminiz"));
        if (selection == null) {
            System.out.println("Hatali secim ");

        } else {
            switch (selection) {
                case 1: {
                    System.exit(0);
                }
                case 2: {
                    showSelfTeacher(id);
                    break;
                }
                case 3: {
                    addTeacher();
                    break;
                }
                case 4: {
                    deleteTeacher();
                }
                case 5: {
                    searchStudent();

                }
                case 6: {
                    setGrade(id);

                }
                case 7: {
                    
                    
                   System.out.println("1 ) " + label.getString("ALL_COURSES"));
                    System.out.println("2 ) " + label.getString("OWN_COURSES"));

                    Integer gradeType = toInt(read(label.getString("SELECT_ACTION")));

                    if (gradeType == null) {
                        System.out.println(label.getString("CHOICE_ERR"));

                    } else {

                        switch (gradeType) {
                            case 1: {

                                showLectures();
                                break;
                            }
                            case 2: {
                                
                                showTeacherLectures(id);
                                break;
                            }
                            case 3: {
                                addExam(id);
                                break;
                            }

                        }
                    
                    }
                    break;
                }

            }
        }
        teacherScreen(id);
    }

    private void hr() {
        System.out.println("-----------------------------------------------------------------");
    }

    private String read(String msg) throws IOException {
        System.out.print(msg + " : ");
        return in.readLine();
    }

    private Integer toInt(String text) {
        try {
            return Integer.parseInt(text);
        } catch (Exception e) {
            return null;
        }
    }
    
    private Float toFloat(String text) {
        try {
            return Float.parseFloat(text);
        } catch (Exception e) {
            return null;
        }
    }

    private void showLoginScreen() throws Exception {

        System.out.println(label.getString("STUDENT_INFORMATION_SYSTEM"));
        System.out.println(label.getString("LOGIN"));
        String id = read(label.getString("USER_NAME"));
        String pass = read(label.getString("PASSWORD"));

        if (null != getMainService().login(id, pass)) {
            switch (getMainService().login(id, pass)) {
                case STUDENT:
                    studentScreen(id);
                    break;
                case TEACHER:
                    teacherScreen(id);
                    break;
                case INVALID:
                    System.out.println(label.getString("WRONG_TYPE"));
                    break;
                default:
                    break;
            }
        }

    }

    private void showStudents() throws Exception {
        List<Student> students = getMainService().getStudents();
        hr();
        System.out.print(String.format("%5s", "ID"));
        System.out.print(String.format("%25s", "Name"));
        System.out.print(String.format("%50s", "Email"));
        System.out.println("");
        for (Student student : students) {
            System.out.print(String.format("%5d", student.getId()));
            System.out.print(String.format("%25s", student.getName()));
            System.out.print(String.format("%50s", student.getEmail()));
            System.out.println("");
        }
    }

   
    private void showTeacher() throws Exception {
        List<Teacher> teachers = getMainService().getTeacher();
        hr();
        System.out.print(String.format("%5s", "ID"));
        System.out.print(String.format("%50s", "Name"));
        System.out.print(String.format("%50s", "Lesson"));
        System.out.println("");
        for (Teacher teacher : teachers) {
            System.out.print(String.format("%5d", teacher.getId()));
            System.out.print(String.format("%50s", teacher.getName()));

            System.out.println("");
        }
    }

    private void addTeacher() throws IOException {
        System.out.println("Öğretmen Ekleme");
        Teacher teacher = new Teacher();
        teacher.setName(read("Adi"));
        teacher.setId(toInt(read("id")));

        getMainService().saveTeacher(teacher);
    }

    private void deleteTeacher() throws IOException {
        System.out.println("öğretmen Silme");
        String id;

        id = (read("Silinecek öğretmenin id no"));

        getMainService().deleteTeacher(id);
    }

    private void searchStudent() throws IOException {
        String sid;
        sid = (read("aranacak öğrencinin id no"));
        System.out.println(getMainService().searchStudent(sid).toString());

    }

    private void setGrade(String pass) throws IOException {
        int sid;
        int not;

        System.out.println("Not giris :");
        sid = (toInt(read("öğrencinin id no")));
        System.out.println("Notunu gir :");
        not = (toInt(read("öğrencinin notu")));
        getMainService().setGrade(pass, sid, not);

    }

    private void showSelfInfo(String id) {
        System.out.println("Öğrenci bilgisi görüntüleniyor:");
        Student student = new Student();
        student = getMainService().showSelfInfo(id);
        System.out.println(student.toString());

    }

    private void showTakenLectures() {
        System.out.println("Öğrencinin aldığı dersler görüntüleniyor:");

    }

    private void selectLectures() {
        System.out.println("Ders Seçimi:");

    }

    private void showExamInfo(String id) {
        hr();
        System.out.println("Öğrenciye ait sınav bilgileri görüntüleniyor:");
        List<Exam> exams = getMainService().showExamInfo(id);
        /*System.out.print(String.format("%5s", "ID"));
        System.out.print(String.format("%50s", "LECTURE ID"));
        System.out.print(String.format("%50s", "NAME"));
        System.out.println("");
        for (Exam exam : exams) {
            System.out.print(String.format("%5s", exam.getId()));
            System.out.print(String.format("%50s", exam.getLectureId()));
            System.out.print(String.format("%50s", exam.getName()));

            System.out.println("");
        }
        */
    }

    private void showGradeInfo() {
        System.out.println("Öğrenciye ait not bilgileri görüntüleniyor:");
    }

    private void showSelfTeacher(String id) {
        System.out.println("Öğretmen Bilgisi Görüntüleniyor..");
        Teacher teacher = new Teacher();
        teacher = getMainService().showSelfTeacher(id);

    }

    private void showExamGrades(String id) {
        List<ExamTaken> exams = getMainService().getExamGrades(id);

        hr();
        System.out.print(String.format("%5s", "ID"));
        System.out.print(String.format("%5s", id));
        System.out.println("");
        System.out.print(String.format("%5s", "Exam Name"));
        System.out.print(String.format("%50s", "Grade"));
        System.out.println("");
        hr();
        System.out.println("");

        for (ExamTaken grade : exams) {
            System.out.print(String.format("%5s", grade.getExam().getName()));
            System.out.print(String.format("%50s", grade.getGrade()));
           
            System.out.println("");
            hr();
        }

    }

    private void showLectures() {
        System.out.println("Tüm Derslerin Listesi");
        List<Lecture> lectures = getMainService().showLectures();
        hr();
        System.out.print(String.format("%5s", "ID"));
        System.out.print(String.format("%25s", "Name"));
        System.out.print(String.format("%50s", "Credits"));
        System.out.println("");
        for (Lecture lecture : lectures) {
            System.out.print(String.format("%5d", lecture.getId()));
            System.out.print(String.format("%25s", lecture.getLectureName()));
            System.out.print(String.format("%50s", lecture.getCredit()));

        }

    }

    
    private void showLetterGrades(String id) {
        List<TakenLecture> courses = getMainService().getLetterGrades(id);
        

        hr();
        System.out.print(String.format("%5s", "ID"));
        System.out.print(String.format("%5s", id));
        System.out.println("");
        System.out.print(String.format("%5s", "Course Name"));
        System.out.print(String.format("%50s", "Grade"));
        System.out.println("");
        hr();
        System.out.println("");

        for (TakenLecture course : courses) {
            System.out.print(String.format("%5s", course.getLecture().getLectureName()));
            System.out.print(String.format("%50s", course.getGrade()));
            
            System.out.println("");
            hr();
        }


    }
    private void showTeacherLectures(String id) {
        System.out.println("Alınan Derslerin Listesi");
        List<Lecture> lectures = getMainService().showLectures();
        hr();
        System.out.print(String.format("%5s", "ID"));
        System.out.print(String.format("%25s", "Name"));
        System.out.print(String.format("%50s", "Credits"));
        System.out.println("");
        for (Lecture lecture : lectures) {
            System.out.print(String.format("%5d", lecture.getId()));
            System.out.print(String.format("%25s", lecture.getLectureName()));
            System.out.print(String.format("%50s", lecture.getCredit()));
            System.out.println("");
        }
    }
    
    private void addExam(String id){
        
        showTeacherLectures(id);
        
        Exam exam = new Exam();
        Lecture lecture = new Lecture();
        
        try {
            Integer lectureId = toInt(read(label.getString("LECTURE_ID")));
            lecture.setId(lectureId);
            exam.setLectureId(lecture);
        } catch (IOException ex) {
            Logger.getLogger(CommandLineManager.class.getName()).log(Level.SEVERE, null, ex);
        }
               
        
        try {
            String examName = read(label.getString("EXAM_NAME"));
            exam.setName(examName);
        } catch (IOException ex) {
            Logger.getLogger(CommandLineManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        try {
            String semester = read(label.getString("SEMESTER"));
            exam.setSemester(semester);
        } catch (IOException ex) {
            Logger.getLogger(CommandLineManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        try {
            Integer sHour = toInt(read(label.getString("START_HOUR")));
            exam.setSHour(sHour);        
        } catch (IOException ex) {
            Logger.getLogger(CommandLineManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        try {
            Integer eHour = toInt(read(label.getString("END_HOUR")));
            exam.setEHour(eHour);
        } catch (IOException ex) {
            Logger.getLogger(CommandLineManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        try {
            Float percentage = toFloat(read(label.getString("PERCENTAGE")));
            exam.setPercentage(percentage);
        } catch (IOException ex) {
            Logger.getLogger(CommandLineManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        getMainService().addExam(id, exam);
                
    }
    private void showGivenExams(String id) {
        hr();
        System.out.println("Öğretmenin verdiği derslere ait sınav bilgileri görüntüleniyor:");
        List<Exam> exams = getMainService().showGivenExams(id);
        System.out.print(String.format("%5s", "ID"));
        System.out.print(String.format("%50s", "LECTURE ID"));
        System.out.print(String.format("%50s", "NAME"));
        System.out.println("");
        for (Exam exam : exams) {
            System.out.print(String.format("%5s", exam.getId()));
            System.out.print(String.format("%50s", exam.getLectureId()));
            System.out.print(String.format("%50s", exam.getName()));

            System.out.println("");
        }
    
    
    }
}