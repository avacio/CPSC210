package ca.ubc.cpsc210.courseRecommender;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by lexigee on 2017-03-06.
 */
public class Main {
    public static void main(String[] args) {
        Course cs110 = new Course("CPSC", 110);
        Course cs210 = new Course("CPSC", 210);

        Course crs = cs110;
        Course cs110_dup = new Course("CPSC", 110);

        System.out.println(crs == cs110);
        System.out.println(crs.equals(cs110));

        System.out.println(cs110_dup == cs110);
        System.out.println(cs110_dup.equals(cs110));

        Set<Course> courses = new HashSet<>();
        courses.add(cs110);
        courses.add(cs210);

        System.out.println(courses.contains(cs110));
        System.out.println(courses.contains(cs110_dup));


    }
}
