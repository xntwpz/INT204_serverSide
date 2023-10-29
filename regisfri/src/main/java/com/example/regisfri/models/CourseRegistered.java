/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.regisfri.models;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Khaitong Lim
 */
public class CourseRegistered { // ไม่ให้ข้อมูลหาย=stored in SESSION
    private Map<Integer, List<Subject>> registeredSubjects = new HashMap<>();
    // add in ArrayList<>
    public void registerSubject(int semester, Subject subject) {
        if (registeredSubjects.get(semester)==null) {
            registeredSubjects.put(semester, new ArrayList<>());
        }
        registeredSubjects.get(semester).add(subject);
    }
    public void removeAllRegisteredCourse(int semester) {
        registeredSubjects.remove(semester);
    }

    public void removeAllRegisteredCourse() {
        registeredSubjects.clear();
    }

    public List<Subject> getRegisteredCourse(int semester) {
        return registeredSubjects.get(semester);
    }
    // ยกลิสใส่เข้ามาแล้วบอกเทอม
    public Map<Integer, List<Subject>> getHistory() {
        return registeredSubjects;
    }
}
