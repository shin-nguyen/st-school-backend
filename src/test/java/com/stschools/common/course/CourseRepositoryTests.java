//package com.stschools.common.course;
//
//import com.stschools.entity.Course;
//import com.stschools.repository.CourseRepository;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
//import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
//import org.springframework.test.annotation.Rollback;
//
//import java.util.Arrays;
//import java.util.List;
//
//import static org.assertj.core.api.Assertions.assertThat;
//import static org.junit.jupiter.api.Assertions.assertNotNull;
//
//@DataJpaTest
//@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
////@Rollback(false)
//public class CourseRepositoryTests {
//    @Autowired
//    CourseRepository courseRepository;
//
//    @Test
//    public void testAddCourse() {
//
//        List<Course> courseList = Arrays.asList(
//                new Course("The Complete 2020 Fullstack Web Developer Course",
//                        "Start learning HTML with the ST-schools fundamentals course. HTML is the standard markup language for creating Web pages.",
//                        "AWS Cloud: Start with AWS Certified Solutions Architect Associate, then move on to AWS Certified Developer Associate and then AWS Certified SysOps Administrator. Afterwards you can either do AWS Certified Solutions Architect Professional or AWS Certified DevOps",
//                        "John Purcell",
//                        "C# Git",
//                        "All Levels",
//                        "English",
//                        "Finance & Accounting",
//                        5,
//                        "https://res.cloudinary.com/qscloud/image/upload/v1635049180/st-school/images/html.png.png"
//                ),
//                new Course("Backbone Tutorial: Learn Backbonejs from Scratch",
//                        "A collection of hands-on, step-by-step, bite-sized Backbone tutorials covering fundamentals of Backbone.",
//                        "Want to learn how to write clean, maintainable, testable Javascript code?\n" +
//                                "There are many libraries and frameworks to help you structure your Javascript code. BackboneJS is one of them",
//                        "Dr.Angrela Y",
//                        "C# Git",
//                        "All Levels",
//                        "English",
//                        "IT & Software",
//                        15,
//                        "https://res.cloudinary.com/qscloud/image/upload/v1635049180/st-school/images/html.png.png"
//                ),
//                new Course("The Complete 2022 Web Development Bootcamp",
//                        "Become a Full-Stack Web Developer with just ONE course. HTML, CSS, Javascript, Node, React, MongoDB, Web3 and DApps",
//                        "Welcome to the Complete Web Development Bootcamp, the only course you need to learn to code and become a full-stack web developer. With 150,000+ ratings and a 4.8 average, my Web Development course is one of the HIGHEST RATED courses in the history of Udemy! ",
//                        "Cheng",
//                        "Data Visualization",
//                        "Beginner",
//                        "English",
//                        "IT & Software",
//                        10,
//                        "https://res.cloudinary.com/qscloud/image/upload/v1635049169/st-school/images/css.png.png"
//                ),
//                new Course("Learn HTML",
//                        "Start learning HTML with the ST-schools fundamentals course. HTML is the standard markup language for creating Web pages.",
//                        "We'll take you step-by-step through engaging video tutorials and teach you everything you need to know to succeed as a web developer.\n" +
//                                "\n" +
//                                "The course includes over 65 hours of HD video tutorials and builds your programming knowledge while making real-world websites and web apps.",
//                        "AI Sweigart",
//                        "C# Git",
//                        "Expert",
//                        "English",
//                        "Development",
//                        12,
//                        "https://res.cloudinary.com/qscloud/image/upload/v1632236549/st-school/images/sql.png.png"
//                ),
//                new Course("Python Django - The Practical GuideLearn how to build web applications and websites with Python and the Django framework",
//                        "Learn how to build web applications and websites with Python and the Django framework",
//                        "Angular is an amazing frontend JavaScript & TypeScript framework with which you can build powerful web applications.\n" +
//                                "\n" +
//                                "There are a lot of courses that dive deeply into Angular but sometimes you just want to build an entire app and see how it all works in practice. And you want to use all these great third-party packages that can add a lot of awesome functionalities to your Angular app!",
//                        "Jose Portilla",
//                        "JAVA SQL",
//                        "Beginner",
//                        "English",
//                        "Software",
//                        43,
//                        "https://res.cloudinary.com/qscloud/image/upload/v1632104647/st-school/images/java.png.png"
//                ),
//                new Course("Flutter 1.2 with Firebase&Stripe Build shop app from scratch",
//                        "Master Angular 13 (formerly \"Angular 2\") and build awesome, reactive web apps with the successor of Angular.js",
//                        "The course is exclusively targeted at people who have not programmed before. Therefore, you will learn every programming concept in the context of real-life programming examples by building real-world programs. That way, you will learn the syntax of the language but also understand the logic behind the programming process so you can create your own Python programs. In addition, you will learn both how to write programs but also how to deploy them to live servers and create executable versions that run independently on any computer. The course covers everything from A to Z.",
//                        "Hadi Kachmar",
//                        "Dart",
//                        "Beginner",
//                        "English",
//                        "Software",
//                        23,
//                        "https://res.cloudinary.com/qscloud/image/upload/v1638543961/st-school/images/js.png.png"
//                )
//        );
//
//        courseRepository.saveAll(courseList);
//
////        Iterable<Course> iterable = courseRepository.findAll();
////        assertThat(iterable).size().isEqualTo(2);
//    }
//
//    @Test
//    public void findCoursesByUserId() {
//        System.out.println(courseRepository.findCoursesByUserId(2L));
//        Iterable<Course> iterable = courseRepository.findCoursesByUserId(2L);
//        assertThat(iterable).size().isEqualTo(2);
//    }
//
//    @Test
//
//    public void testFindCourseByName(){
//        String name = "Learn HTML";
//        List<Course> courseList = courseRepository.findCourseByName(name);
//        System.out.println(courseList.get(0));
//        assertNotNull(courseList.get(0));
//    }
//
//    @Test
//    public void findCoursesByUserAndOrder() {
//        List<Course> list = courseRepository.findCoursesByNotInOrder(1L);
//        System.out.println(list);
//
//        //Nay tu chinh tham so nha
//        assertThat(list).size().isEqualTo(0);
//    }
//
//}