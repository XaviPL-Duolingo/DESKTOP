package com.duolingo.app.util;

import com.duolingo.app.model.Course;

import java.util.ArrayList;
import java.util.List;

public interface IServerRMI {

    public List<Course> getAllCoursesByID(int idOriginLang);


}
