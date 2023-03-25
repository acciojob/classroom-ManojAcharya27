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
    } // to add student

    public  void addTeacher(Teacher teacher){
        teacherHashMap.put(teacher.getName(),teacher);
    }  // to add teacher

    public void addStudentTeacherPair(String student,String teacher){  // to assign teacher to student
        if(studentHashMap.containsKey(student)&&teacherHashMap.containsKey(teacher)){ // check student present   or not and check for teacher also
            List<String> listOfStudents=new ArrayList<>();
            if(teacherStudentHashMap.containsKey(teacher)){
                listOfStudents=teacherStudentHashMap.get(teacher);
            }
            listOfStudents.add(student);
            teacherStudentHashMap.put(teacher,listOfStudents);
        }
    }

    // return the student
    public Student getStudentByName(String student){
          return studentHashMap.get(student);
    }



    // method to return teacher
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


        if(teacherStudentHashMap.containsKey(teacher)&&teacherHashMap.containsKey(teacher)){
            for(String student: teacherStudentHashMap.get(teacher)){
                studentHashMap.remove(student);
            }
            teacherStudentHashMap.remove(teacher);
        }
    }

    public void deleteAllTeachers(){
        /*teacherHashMap.clear();
        studentHashMap.clear();
        teacherStudentHashMap.clear();*/

        for(String teacher: teacherHashMap.keySet()){
            if(teacherStudentHashMap.containsKey(teacher)){
                for(String student: teacherStudentHashMap.get(teacher)){
                    studentHashMap.remove(student);
                }
                teacherStudentHashMap.remove(teacher);
            }else{
                teacherHashMap.remove(teacher);
            }
        }
    }


}
