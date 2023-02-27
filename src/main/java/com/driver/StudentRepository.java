package com.driver;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Repository
public class StudentRepository {
    HashMap<String,Student> studentHashMap=new HashMap<>();
    HashMap<String,Teacher> teacherHashMap=new HashMap<>();
    HashMap<String, List<String>> teacherStudentHashMap=new HashMap<>();


    public  void addStudent(Student student){
        studentHashMap.put(student.getName(),student);
    }

    public  void addTeacher(Teacher teacher){
        teacherHashMap.put(teacher.getName(),teacher);
    }

    public void addStudentTeacherPair(String student,String teacher){
        if(studentHashMap.containsKey(student)&&teacherHashMap.containsKey(teacher)){
            List<String> listOfStudents=new ArrayList<>();
            if(teacherStudentHashMap.containsKey(teacher)){
                listOfStudents=teacherStudentHashMap.get(teacher);
            }
            listOfStudents.add(student);
            teacherStudentHashMap.put(teacher,listOfStudents);
        }
    }

    public Student getStudentByName(String student){
          return studentHashMap.get(student);
    }

    public  Teacher getTeacherByName(String teacher){
        return  teacherHashMap.get(teacher);
    }

    public List<String> getStudentsByTeacherName(String teacher){
        List<String> listOfStudents=new ArrayList<>();
        listOfStudents=teacherStudentHashMap.get(teacher);
        return  listOfStudents;
    }

    public List<String> getAllStudents(){
        List<String> students=new ArrayList<>();
        for (String x: studentHashMap.keySet()){
            students.add(x);
        }
        return students;
    }

    public void  deleteTeacherByName(String teacher){

        teacherHashMap.remove(teacher);
        List<String> students=new ArrayList<>();
        students=teacherStudentHashMap.get(teacher);
        teacherStudentHashMap.remove(teacher);
        for(String x: students){
            if(studentHashMap.containsKey(x)){
                studentHashMap.remove(x);
            }
        }
    }

    public void deleteAllTeachers(){
        teacherHashMap.clear();
        studentHashMap.clear();
        teacherStudentHashMap.clear();
    }


}
