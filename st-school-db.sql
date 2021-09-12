-- drop database st_school;
-- create database st_school;
use st_school;

insert tbl_role value (1, 'ROLE_ADMIN');
insert tbl_role value (2, 'ROLE_CUSTOMER');

insert tbl_user value (1, 'HCM', 'default.jpg', '2000-02-27', 'admin@gmail.com', 0, 'Admin', '1234', 0123456789, true, 1);
insert tbl_user value (2, 'HN', 'default.jpg', '2000-02-08', 'user@gmail.com', 0, 'User', '1234', 9876543210, true, 2);

insert tbl_topic value (1, 'Html & Css', 'HTML/CSS');
insert tbl_topic value (2, 'Java Script', 'JAVA SCRIPT');
insert tbl_topic value (3, 'Sql', 'SQL');
insert tbl_topic value (4, 'Java', 'JAVA');
insert tbl_topic value (5, 'CSharp/C#', 'C#');

insert tbl_course value (1, 'Start learning HTML with the w3schools fundamentals course. HTML is the standard markup language for creating Web pages.', 'html.png', 'Learn HTML', 96);
insert tbl_course value (2, 'Start learning CSS with the w3schools fundamentals course. CSS is the language we use to style an HTML document.', 'css.png', 'Learn CSS', 90);
insert tbl_course value (3, 'Start learning JavaScript with the w3schools fundamentals course. JavaScript is the programming language of the Web.', 'js.png', 'Learn JavaScript', 100);
insert tbl_course value (4, 'Start learning SQL with the w3schools fundamentals course. SQL is a standard language for storing, manipulating and retrieving data in databases.', 'sql.png', 'Learn SQL', 96);
