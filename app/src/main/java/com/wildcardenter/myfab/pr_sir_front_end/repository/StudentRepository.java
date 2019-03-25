package com.wildcardenter.myfab.pr_sir_front_end.repository;

import android.app.Application;
import android.os.AsyncTask;

import com.wildcardenter.myfab.pr_sir_front_end.dao.Book_Adapt_Dao;
import com.wildcardenter.myfab.pr_sir_front_end.dao.CourseDao;
import com.wildcardenter.myfab.pr_sir_front_end.dao.EnrollDao;
import com.wildcardenter.myfab.pr_sir_front_end.dao.StudentDao;
import com.wildcardenter.myfab.pr_sir_front_end.dao.TextDao;
import com.wildcardenter.myfab.pr_sir_front_end.databases.StudentDatabase;
import com.wildcardenter.myfab.pr_sir_front_end.models.Book_Adaptation;
import com.wildcardenter.myfab.pr_sir_front_end.models.Course;
import com.wildcardenter.myfab.pr_sir_front_end.models.Enroll;
import com.wildcardenter.myfab.pr_sir_front_end.models.Student;
import com.wildcardenter.myfab.pr_sir_front_end.models.Text;
import com.wildcardenter.myfab.pr_sir_front_end.models.TextByCs;

import java.util.List;

import androidx.lifecycle.LiveData;

public class StudentRepository {
    private StudentDao studentDao;
    private CourseDao courseDao;
    private EnrollDao enrollDao;
    private Book_Adapt_Dao book_adapt_dao;
    private TextDao textDao;
    private LiveData<List<Student>> allStudentList;
    private LiveData<List<Course>> allCourseList;
    private LiveData<List<Text>> allTextList;
    private LiveData<List<Enroll>> allEnrollList;
    private LiveData<List<Book_Adaptation>> allAdaptList;
    private LiveData<List<TextByCs>> allTextListByCs;



    public StudentRepository(Application application){
        StudentDatabase database=StudentDatabase.getDatabase(application);
        studentDao=database.getStudentDao();
        courseDao=database.getCourseDao();
        enrollDao=database.getEnrollDao();
        book_adapt_dao=database.getAdaptDao();
        textDao=database.getTextDao();
        allStudentList=studentDao.getAllStudents();
        allCourseList=courseDao.getAllCourses();
        allTextList=textDao.getAllText();
        allEnrollList=enrollDao.getAllEnrolls();
        allAdaptList=book_adapt_dao.getAllAdaptation();
        allTextListByCs=textDao.getAllBookOfferedByCs();

    }
    public LiveData<List<String>> getDeptByPublisher(String publisher){
        return  courseDao.getDeptBySpecificPub(publisher);
    }
    public void insertStudent(Student student){
        new insertStudentAsync(studentDao).execute(student);

    }
    public void insertCourse(Course course){
        new insertCourseAsync(courseDao).execute(course);
    }
    public void insertText(Text text){
        new insertTextAsync(textDao).execute(text);

    }
    public void insertEnroll(Enroll enroll){new insertEnrollAsync(enrollDao).execute(enroll);}
    public void insertAdapt(Book_Adaptation adaptation){
        new insertAdaptAsync(book_adapt_dao).execute(adaptation);
    }

    public void deleteStudent(Student student){ new deleteStudentAsync(studentDao).execute(student);}
    public void deleteCourse(Course course){new deleteCourseAsync(courseDao).execute(course);}
    public void deleteEnroll(Enroll enroll){new deleteEnrollAsync(enrollDao).execute(enroll);}
    public void deleteAdaptation(Book_Adaptation book_adaptation){new deleteAdaptAsync(book_adapt_dao).execute(book_adaptation);}
    public void deleteText(Text text){new deleteTextAsync(textDao).execute(text);}



    //All insertion asynctask is performed here
    private static class insertStudentAsync extends AsyncTask<Student,Void,Void>{

        private StudentDao dao;

        private insertStudentAsync(StudentDao dao) {
            this.dao = dao;
        }

        @Override
        protected Void doInBackground(Student... students) {
            dao.insertStudent(students[0]);
            return null;
        }
    }
    private static class insertCourseAsync extends AsyncTask<Course,Void,Void>{

        private CourseDao dao;

        private insertCourseAsync(CourseDao dao) {
            this.dao = dao;
        }

        @Override
        protected Void doInBackground(Course... courses) {
            dao.insertCourse(courses[0]);
            return null;
        }
    }
    private static class insertTextAsync extends AsyncTask<Text,Void,Void>{
        TextDao dao;

        private insertTextAsync(TextDao dao) {
            this.dao = dao;
        }

        @Override
        protected Void doInBackground(Text... texts) {
            dao.insertText(texts[0]);
            return null;
        }
    }
    private static class insertEnrollAsync extends AsyncTask<Enroll,Void,Void>{
        private EnrollDao dao;

        private insertEnrollAsync(EnrollDao dao) {
            this.dao = dao;
        }

        @Override
        protected Void doInBackground(Enroll... enrolls) {
            dao.insertEnoll(enrolls[0]);
            return null;
        }
    }
    private static class insertAdaptAsync extends AsyncTask<Book_Adaptation,Void,Void>{
        private Book_Adapt_Dao dao;

        private insertAdaptAsync(Book_Adapt_Dao dao) {
            this.dao = dao;
        }

        @Override
        protected Void doInBackground(Book_Adaptation... book_adaptations) {
            dao.insertAdaptation(book_adaptations[0]);
            return null;
        }
    }


    //All deletion asynctask is performed here

    private static class deleteStudentAsync extends AsyncTask<Student,Void,Void>{

        private StudentDao dao;

        public deleteStudentAsync(StudentDao dao) {
            this.dao = dao;
        }

        @Override
        protected Void doInBackground(Student... students) {
            dao.deleteStudent(students[0]);
            return null;
        }
    }

    private static class deleteCourseAsync extends AsyncTask<Course,Void,Void>{

        private CourseDao dao;

        public deleteCourseAsync(CourseDao dao) {
            this.dao = dao;
        }

        @Override
        protected Void doInBackground(Course... courses) {
            dao.deleteCourse(courses[0]);
            return null;
        }
    }

    private static class deleteEnrollAsync extends AsyncTask<Enroll,Void,Void>{

        private EnrollDao dao;

        public deleteEnrollAsync(EnrollDao dao) {
            this.dao = dao;
        }

        @Override
        protected Void doInBackground(Enroll... enrolls) {
            dao.deleteEnroll(enrolls[0]);
            return null;
        }
    }

    private static class deleteAdaptAsync extends AsyncTask<Book_Adaptation,Void,Void>{

        private Book_Adapt_Dao dao;

        public deleteAdaptAsync(Book_Adapt_Dao dao) {
            this.dao = dao;
        }

        @Override
        protected Void doInBackground(Book_Adaptation... book_adaptations) {
            dao.deleteAdaptation(book_adaptations[0]);
            return null;
        }
    }

    private static class deleteTextAsync extends AsyncTask<Text,Void,Void>{

        private TextDao dao;

        public deleteTextAsync(TextDao dao) {
            this.dao = dao;
        }

        @Override
        protected Void doInBackground(Text... texts) {
            dao.deleteText(texts[0]);
            return null;
        }
    }



    public LiveData<List<Student>> getAllstudent(){
        return allStudentList;
    }
    public LiveData<List<Course>> getAllCourse(){return allCourseList; }
    public LiveData<List<Text>> getAllText(){return allTextList;}
    public LiveData<List<Enroll>> getAllEnroll(){return allEnrollList;}
    public LiveData<List<Book_Adaptation >> getAllAdapt(){ return allAdaptList;}
    public LiveData<List<TextByCs>> getAllTextListByCs(){return allTextListByCs;}



}
