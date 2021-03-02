package com.duolingo.app.util;

import com.duolingo.app.model.Category;
import com.duolingo.app.model.Course;
import com.duolingo.app.model.Language;
import java.util.List;

public interface IServerRMI {

    public List<Course> getAllCoursesByID(int idOriginLang);

    public List<Language> getAllLanguages();

    public List<Category> getAllCategoriesByID(int idCourse);

}
