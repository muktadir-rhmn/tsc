-- phpMyAdmin SQL Dump
-- version 4.4.12
-- http://www.phpmyadmin.net
--
-- Host: 127.0.0.1
-- Generation Time: Dec 23, 2015 at 07:16 AM
-- Server version: 5.6.25
-- PHP Version: 5.6.11

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `project`
--

-- --------------------------------------------------------

--
-- Table structure for table `courses`
--

CREATE TABLE IF NOT EXISTS `courses` (
  `courseId` char(255) NOT NULL,
  `courseTitle` char(255) NOT NULL,
  `syllabus` text NOT NULL,
  `ReferenceBook` varchar(500) NOT NULL,
  `creditHour` float NOT NULL,
  `level` tinyint(4) NOT NULL,
  `term` tinyint(4) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `courses`
--

INSERT INTO `courses` (`courseId`, `courseTitle`, `syllabus`, `ReferenceBook`, `creditHour`, `level`, `term`) VALUES
('CSE105', 'Structured Programming', 'C programming laguage', '', 3, 1, 2),
('CSE201', 'Object Oriented Programming', 'C++ and Java programming language.', '', 3, 2, 1);

-- --------------------------------------------------------

--
-- Table structure for table `coursesbyteacher`
--

CREATE TABLE IF NOT EXISTS `coursesbyteacher` (
  `TeacherId` char(255) NOT NULL,
  `CourseId` char(255) NOT NULL,
  `Level` tinyint(4) NOT NULL,
  `Term` tinyint(4) NOT NULL,
  `Section` char(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COMMENT='This table will store the running courses and the teachers';

--
-- Dumping data for table `coursesbyteacher`
--

INSERT INTO `coursesbyteacher` (`TeacherId`, `CourseId`, `Level`, `Term`, `Section`) VALUES
('ashikur', 'CSE409', 4, 2, 'All'),
('ashikur', 'CSE410', 4, 2, 'A1'),
('azad', 'CSE308', 3, 1, 'A'),
('azad', 'CSE305', 3, 1, 'A2'),
('himel', 'CSE207', 2, 2, 'B'),
('himel', 'CSE208', 2, 2, 'B1'),
('kashem', 'CSE201', 2, 1, 'All'),
('kashem', 'CSE202', 2, 1, 'B2'),
('mahfuz', 'CSE403', 4, 1, 'A'),
('mahfuz', 'CSE404', 4, 1, 'A1'),
('nazneen', 'CSE313', 3, 2, 'B'),
('nazneen', 'CSE314', 3, 2, 'A2'),
('sakib', 'CSE105', 1, 2, 'All'),
('sakib', 'CSE106', 1, 2, 'B1'),
('sukarna', 'CSE305', 3, 1, 'A'),
('sukarna', 'CSE317', 3, 2, 'B'),
('tanvir', 'CSE211', 2, 2, 'All'),
('tanvir', 'CSE214', 2, 2, 'B'),
('tanzima', 'CSE411', 4, 1, 'A'),
('tanzima', 'CSE421', 4, 1, 'B2'),
('wasef', 'CSE100', 1, 1, 'All');

-- --------------------------------------------------------

--
-- Table structure for table `currentstudents`
--

CREATE TABLE IF NOT EXISTS `currentstudents` (
  `studentId` varchar(255) NOT NULL,
  `studentName` char(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `hallName` varchar(255) NOT NULL,
  `level` tinyint(4) NOT NULL,
  `term` tinyint(4) NOT NULL,
  `Section` char(5) NOT NULL,
  `email` varchar(400) NOT NULL,
  `phone` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `currentstudents`
--

INSERT INTO `currentstudents` (`studentId`, `studentName`, `password`, `hallName`, `level`, `term`, `Section`, `email`, `phone`) VALUES
('0705101', 'Md. Zakaria', '150250', 'Titumir Hall', 4, 2, 'B2', 'zakaria@gmail.com', '01832629006'),
('0805117', 'Partho Shuvo', '150250', 'Ahsan Ullah North', 4, 1, 'B1', 'partho@gmail.com', '01832629006'),
('0905077', 'Mohaiminul Islam Emon', '150250', 'Suhrawardy Hall', 3, 2, 'A2', 'emon@gmail.com', '01814787980'),
('1005071', 'Rafeed Ul Islam', '150250', 'Ahsan Ullah West', 3, 1, 'A1', 'rafid@gmail.com', '01814787980'),
('1105039', 'Rafeed Rahman', '150250', 'Suhrawardy Hall', 2, 2, 'B2', 'rafeed@gmail.com', '01832629006'),
('1105081', 'Tanveer Hannan Pavel', '150250', 'Nazrul Islam Hall', 2, 2, 'B2', 'tanveer@gmail.com', '01832629006'),
('1205119', 'Anwar Hossain Zahid', '150250', 'Titumir Hall', 2, 1, 'B1', 'zahid@yahoo.com', '01814787980'),
('1305025', 'Ejaz Chowdhury', '150250', 'Ahsan Ullah West', 1, 2, 'A2', 'ejaz@gmail.com', '01814787980'),
('1305057', 'Md. Saquib Hasan', '150250', 'Suhrawardy ', 1, 2, 'A2', 'saquib@gmail.com', '01832629006'),
('1305097', 'Muktadir Rahman', '150250', 'NazrulIslamHall', 2, 1, 'A2', 'muktadir.rhmn@gmail.com', '01814787980'),
('1405001', 'Preetom Saha Arko', '150250', 'Ahsan Ullah North', 1, 1, 'A1', 'preetom@gmail.com', '01832629006'),
('1405075', 'Dipto Barua', '150250', 'Nazrul Islam Hall', 1, 1, 'A1', 'dipto@gmail.com', '01814787980');

-- --------------------------------------------------------

--
-- Table structure for table `currentteachers`
--

CREATE TABLE IF NOT EXISTS `currentteachers` (
  `teacherId` char(255) NOT NULL,
  `teacherName` char(255) NOT NULL,
  `password` char(255) NOT NULL,
  `designation` char(255) NOT NULL,
  `takenCourses` text NOT NULL,
  `e-mail` varchar(400) NOT NULL,
  `web` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `currentteachers`
--

INSERT INTO `currentteachers` (`teacherId`, `teacherName`, `password`, `designation`, `takenCourses`, `e-mail`, `web`) VALUES
('ashikur', 'Md. Ashikur Rahman', '150250', 'Associate Professor', 'CSE409, CSE410', 'ashikur@gmail.com', 'www.buet.ac.bd/cse/ashikur'),
('azad', 'Abdus Salam Azad', '150250', 'Lecturer', 'CSE308, CSE305', 'azad@gmail.com', 'www.buet.ac.bd/cse/azadsalam'),
('head', 'Mahfuzul Islam', '150250', 'Professor', '', '', ''),
('himel', 'Himel Dev', '150250', 'Lecturer', 'CSE207, CSE208', 'himel@gmail.com', 'www.buet.ac.bd/cse/himel'),
('kashem', 'Md. Abul Kashem Mia', '150250', 'Professor', 'CSE201, CSE202', 'kashem@gmail.com', 'www.buet.ac.bd/cse/kashem'),
('mahfuz', 'Md. Mahfuzul Islam', '150250', 'Professor', 'CSE403, CSE404', 'mahfuz@gmail.com', 'www.buet.ac.bd.cse/mahfuz'),
('nazneen', 'Md. Mahmuda Nazneen', '150250', 'Associate Professor', 'CSE313, CSE314', 'nazneen@gmail.com', 'www.buet.ac.bd/cse/nazneen'),
('sakib', 'Md. Iftekharul Islam Sakib', '150250', 'Lecturer', 'CSE105, CSE106', 'iisakib@gmail.com', 'www.buet.ac.bd/cse/iisakib'),
('sukarna', 'Sukarna Barua', '150250', 'Assistant Professor', 'CSE305, CSE317', 'sukarna@gmail.com', 'www.buet.ac.bd/cse/sukarna'),
('tanvir', 'Tanvir Ahmed Khan', '150250', 'Lecturer', 'CSE211, CSE214', 'tak@cse.buet.ac.bd', 'www.buet.ac.bd/cse/tanvir'),
('tanzima', 'Tanzima Hashem', '150250', 'Assistant Professor', 'CSE411, CSE421', 'tanzima@gmail.com', 'www.buet.ac.bd/cse/tanzima'),
('wasef', 'Md. Abu Wasef', '150250', 'Assistant Professor', 'CSE100', 'wasef@gmail.com', 'www.buet.ac.bd/cse/wasef');

-- --------------------------------------------------------

--
-- Table structure for table `notifications`
--

CREATE TABLE IF NOT EXISTS `notifications` (
  `id` int(11) NOT NULL,
  `Time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `To` varchar(255) NOT NULL,
  `From` char(255) NOT NULL,
  `Seen` tinyint(1) DEFAULT '0',
  `Title` text NOT NULL,
  `Body` text NOT NULL
) ENGINE=InnoDB AUTO_INCREMENT=99 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `notifications`
--

INSERT INTO `notifications` (`id`, `Time`, `To`, `From`, `Seen`, `Title`, `Body`) VALUES
(1, '2015-11-22 16:46:40', '1305097', 'azad', 1, 'Testing the notificaiton', 'My database has successfully Ran'),
(2, '2015-11-22 16:46:40', '1305001', 'Ashiq', 0, 'TitleOf the notification', 'dsafsdf'),
(3, '2015-11-22 16:46:40', '1305001', 'Ashiq', 0, 'TitleOf the notification', 'asdfasd\\'),
(4, '2015-11-22 16:46:40', '1305097', 'Sukarna', 1, 'Welcome to DataStructure[CSE203]', 'I am sukarana from CSE, BUET. I will teach you all the datastruce techniques'),
(5, '2015-11-22 16:46:40', '1305099', 'Sukarna', 0, 'Welcome to DataStructure[CSE203]', 'I am sukarana from CSE, BUET. I will teach you all the datastruce techniques'),
(6, '2015-11-22 16:46:40', '1305097', 'Sukarna', 1, 'Welcome to DataStructure[CSE203]', 'I am sukarana from CSE, BUET. I will teach you all the datastruce techniques'),
(7, '2015-11-22 16:46:40', '1305099', 'Sukarna', 0, 'Welcome to DataStructure[CSE203]', 'I am sukarana from CSE, BUET. I will teach you all the datastruce techniques'),
(8, '2015-11-22 16:46:40', '1305097', 'Tavir', 1, 'Class Hobe na[CSE202]', 'Bacchara ajke amar mejaj kharap. ajke class nibo na.'),
(9, '2015-11-29 12:31:29', 'head', 'adsf', 1, 'CT[CSE202] Result requires approval', 'CT Result from the course CSE202 requires your approval to be published'),
(10, '2015-12-02 10:10:07', '1305101', 'tanvir', 0, 'CT[CSE202]result has been published', 'CT result of the course CSE202 by tanvir has been pulished. \nYou have got 19 marks in the exam.'),
(11, '2015-12-02 10:10:07', '1305061', 'tanvir', 0, 'CT[CSE202]result has been published', 'CT result of the course CSE202 by tanvir has been pulished. \nYou have got 15 marks in the exam.'),
(12, '2015-12-02 10:10:07', '1305010', 'tanvir', 0, 'CT[CSE202]result has been published', 'CT result of the course CSE202 by tanvir has been pulished. \nYou have got 14 marks in the exam.'),
(13, '2015-12-02 10:10:07', '1305101', 'tanvir', 0, 'CT[CSE202]result has been published', 'CT result of the course CSE202 by tanvir has been pulished. \nYou have got 19 marks in the exam.'),
(14, '2015-12-02 10:10:07', '1305061', 'tanvir', 0, 'CT[CSE202]result has been published', 'CT result of the course CSE202 by tanvir has been pulished. \nYou have got 15 marks in the exam.'),
(15, '2015-12-02 10:10:07', '1305010', 'tanvir', 0, 'CT[CSE202]result has been published', 'CT result of the course CSE202 by tanvir has been pulished. \nYou have got 14 marks in the exam.'),
(20, '2015-12-06 12:41:02', '1305097', 'Head Of the Department', 1, 'Welcome to CSE[Head]', 'students welcome to our deparrment'),
(21, '2015-12-06 12:41:03', '1305099', 'Head Of the Department', 0, 'Welcome to CSE[Head]', 'students welcome to our deparrment'),
(22, '2015-12-22 19:00:24', 'head', 'azad', 0, 'result needs approval', 'result'),
(23, '2015-12-23 07:08:37', '0705101', 'null', 0, 'hjgyg[null]', 'hbhbbjhbjbhbhjbhbhj'),
(24, '2015-12-23 07:08:37', '0805117', 'null', 0, 'hjgyg[null]', 'hbhbbjhbjbhbhjbhbhj'),
(25, '2015-12-23 07:08:37', '0905077', 'null', 0, 'hjgyg[null]', 'hbhbbjhbjbhbhjbhbhj'),
(26, '2015-12-23 07:08:37', '1005071', 'null', 0, 'hjgyg[null]', 'hbhbbjhbjbhbhjbhbhj'),
(27, '2015-12-23 07:08:38', '1105039', 'null', 0, 'hjgyg[null]', 'hbhbbjhbjbhbhjbhbhj'),
(28, '2015-12-23 07:08:38', '1105081', 'null', 0, 'hjgyg[null]', 'hbhbbjhbjbhbhjbhbhj'),
(29, '2015-12-23 07:08:38', '1205119', 'null', 0, 'hjgyg[null]', 'hbhbbjhbjbhbhjbhbhj'),
(30, '2015-12-23 07:08:38', '1305025', 'null', 0, 'hjgyg[null]', 'hbhbbjhbjbhbhjbhbhj'),
(31, '2015-12-23 07:08:38', '1305057', 'null', 0, 'hjgyg[null]', 'hbhbbjhbjbhbhjbhbhj'),
(32, '2015-12-23 07:08:38', '1305097', 'null', 1, 'hjgyg[null]', 'hbhbbjhbjbhbhjbhbhj'),
(33, '2015-12-23 07:08:38', '1405001', 'null', 0, 'hjgyg[null]', 'hbhbbjhbjbhbhjbhbhj'),
(34, '2015-12-23 07:08:38', '1405075', 'null', 0, 'hjgyg[null]', 'hbhbbjhbjbhbhjbhbhj'),
(35, '2015-12-23 07:09:31', '0705101', 'null', 0, 'uhuhu[null]', 'hgygjgbkbhuhbuh'),
(36, '2015-12-23 07:09:31', '0805117', 'null', 0, 'uhuhu[null]', 'hgygjgbkbhuhbuh'),
(37, '2015-12-23 07:09:31', '0905077', 'null', 0, 'uhuhu[null]', 'hgygjgbkbhuhbuh'),
(38, '2015-12-23 07:09:32', '1005071', 'null', 0, 'uhuhu[null]', 'hgygjgbkbhuhbuh'),
(39, '2015-12-23 07:09:32', '1105039', 'null', 0, 'uhuhu[null]', 'hgygjgbkbhuhbuh'),
(40, '2015-12-23 07:09:32', '1105081', 'null', 0, 'uhuhu[null]', 'hgygjgbkbhuhbuh'),
(41, '2015-12-23 07:09:32', '1205119', 'null', 0, 'uhuhu[null]', 'hgygjgbkbhuhbuh'),
(42, '2015-12-23 07:09:32', '1305025', 'null', 0, 'uhuhu[null]', 'hgygjgbkbhuhbuh'),
(43, '2015-12-23 07:09:32', '1305057', 'null', 0, 'uhuhu[null]', 'hgygjgbkbhuhbuh'),
(44, '2015-12-23 07:09:32', '1305097', 'null', 1, 'uhuhu[null]', 'hgygjgbkbhuhbuh'),
(45, '2015-12-23 07:09:32', '1405001', 'null', 0, 'uhuhu[null]', 'hgygjgbkbhuhbuh'),
(46, '2015-12-23 07:09:32', '1405075', 'null', 0, 'uhuhu[null]', 'hgygjgbkbhuhbuh'),
(47, '2015-12-23 07:12:03', '1205119', 'kashem', 0, 'hghjg[kashem]', 'bhbhjbjhbbhbhjbj'),
(48, '2015-12-23 07:12:03', '1305097', 'kashem', 1, 'hghjg[kashem]', 'bhbhjbjhbbhbhjbj'),
(49, '2015-12-23 07:15:34', 'HEAD', 'kashem', 0, 'CT-1[CSE201] Result requires approval', 'CT-1 Result from the course CSE201 requires your approval to be published'),
(50, '2015-12-23 12:05:22', '0705101', 'null', 0, 'Hello[null]', 'Welcome to CSE'),
(51, '2015-12-23 12:05:22', '0805117', 'null', 0, 'Hello[null]', 'Welcome to CSE'),
(52, '2015-12-23 12:05:22', '0905077', 'null', 0, 'Hello[null]', 'Welcome to CSE'),
(53, '2015-12-23 12:05:22', '1005071', 'null', 0, 'Hello[null]', 'Welcome to CSE'),
(54, '2015-12-23 12:05:22', '1105039', 'null', 0, 'Hello[null]', 'Welcome to CSE'),
(55, '2015-12-23 12:05:22', '1105081', 'null', 0, 'Hello[null]', 'Welcome to CSE'),
(56, '2015-12-23 12:05:23', '1205119', 'null', 0, 'Hello[null]', 'Welcome to CSE'),
(57, '2015-12-23 12:05:23', '1305025', 'null', 0, 'Hello[null]', 'Welcome to CSE'),
(58, '2015-12-23 12:05:23', '1305057', 'null', 0, 'Hello[null]', 'Welcome to CSE'),
(59, '2015-12-23 12:05:23', '1305097', 'null', 0, 'Hello[null]', 'Welcome to CSE'),
(60, '2015-12-23 12:05:23', '1405001', 'null', 0, 'Hello[null]', 'Welcome to CSE'),
(61, '2015-12-23 12:05:23', '1405075', 'null', 0, 'Hello[null]', 'Welcome to CSE'),
(62, '2015-12-23 12:08:00', '0705101', 'null', 0, 'Helloooooooo[null]', 'Welcome to CSEEEEEEEEEEEEEEEEE'),
(63, '2015-12-23 12:08:00', '0805117', 'null', 0, 'Helloooooooo[null]', 'Welcome to CSEEEEEEEEEEEEEEEEE'),
(64, '2015-12-23 12:08:00', '0905077', 'null', 0, 'Helloooooooo[null]', 'Welcome to CSEEEEEEEEEEEEEEEEE'),
(65, '2015-12-23 12:08:00', '1005071', 'null', 0, 'Helloooooooo[null]', 'Welcome to CSEEEEEEEEEEEEEEEEE'),
(66, '2015-12-23 12:08:00', '1105039', 'null', 0, 'Helloooooooo[null]', 'Welcome to CSEEEEEEEEEEEEEEEEE'),
(67, '2015-12-23 12:08:00', '1105081', 'null', 0, 'Helloooooooo[null]', 'Welcome to CSEEEEEEEEEEEEEEEEE'),
(68, '2015-12-23 12:08:00', '1205119', 'null', 0, 'Helloooooooo[null]', 'Welcome to CSEEEEEEEEEEEEEEEEE'),
(69, '2015-12-23 12:08:00', '1305025', 'null', 0, 'Helloooooooo[null]', 'Welcome to CSEEEEEEEEEEEEEEEEE'),
(70, '2015-12-23 12:08:00', '1305057', 'null', 0, 'Helloooooooo[null]', 'Welcome to CSEEEEEEEEEEEEEEEEE'),
(71, '2015-12-23 12:08:01', '1305097', 'null', 0, 'Helloooooooo[null]', 'Welcome to CSEEEEEEEEEEEEEEEEE'),
(72, '2015-12-23 12:08:01', '1405001', 'null', 0, 'Helloooooooo[null]', 'Welcome to CSEEEEEEEEEEEEEEEEE'),
(73, '2015-12-23 12:08:01', '1405075', 'null', 0, 'Helloooooooo[null]', 'Welcome to CSEEEEEEEEEEEEEEEEE'),
(74, '2015-12-23 12:09:57', '0705101', 'null', 0, 'HellooooooW[null]', 'wWLCOME TO CSEEEEEEEEEEEE'),
(75, '2015-12-23 12:09:57', '0805117', 'null', 0, 'HellooooooW[null]', 'wWLCOME TO CSEEEEEEEEEEEE'),
(76, '2015-12-23 12:09:57', '0905077', 'null', 0, 'HellooooooW[null]', 'wWLCOME TO CSEEEEEEEEEEEE'),
(77, '2015-12-23 12:09:57', '1005071', 'null', 0, 'HellooooooW[null]', 'wWLCOME TO CSEEEEEEEEEEEE'),
(78, '2015-12-23 12:09:57', '1105039', 'null', 0, 'HellooooooW[null]', 'wWLCOME TO CSEEEEEEEEEEEE'),
(79, '2015-12-23 12:09:57', '1105081', 'null', 0, 'HellooooooW[null]', 'wWLCOME TO CSEEEEEEEEEEEE'),
(80, '2015-12-23 12:09:57', '1205119', 'null', 0, 'HellooooooW[null]', 'wWLCOME TO CSEEEEEEEEEEEE'),
(81, '2015-12-23 12:09:58', '1305025', 'null', 0, 'HellooooooW[null]', 'wWLCOME TO CSEEEEEEEEEEEE'),
(82, '2015-12-23 12:09:58', '1305057', 'null', 0, 'HellooooooW[null]', 'wWLCOME TO CSEEEEEEEEEEEE'),
(83, '2015-12-23 12:09:58', '1305097', 'null', 0, 'HellooooooW[null]', 'wWLCOME TO CSEEEEEEEEEEEE'),
(84, '2015-12-23 12:09:58', '1405001', 'null', 0, 'HellooooooW[null]', 'wWLCOME TO CSEEEEEEEEEEEE'),
(85, '2015-12-23 12:09:58', '1405075', 'null', 0, 'HellooooooW[null]', 'wWLCOME TO CSEEEEEEEEEEEE'),
(86, '2015-12-23 12:12:56', '0705101', 'null', 0, 'hello[null]', 'welcm to CSE'),
(87, '2015-12-23 12:12:56', '0805117', 'null', 0, 'hello[null]', 'welcm to CSE'),
(88, '2015-12-23 12:12:56', '0905077', 'null', 0, 'hello[null]', 'welcm to CSE'),
(89, '2015-12-23 12:12:56', '1005071', 'null', 0, 'hello[null]', 'welcm to CSE'),
(90, '2015-12-23 12:12:56', '1105039', 'null', 0, 'hello[null]', 'welcm to CSE'),
(91, '2015-12-23 12:12:56', '1105081', 'null', 0, 'hello[null]', 'welcm to CSE'),
(92, '2015-12-23 12:12:56', '1205119', 'null', 0, 'hello[null]', 'welcm to CSE'),
(93, '2015-12-23 12:12:56', '1305025', 'null', 0, 'hello[null]', 'welcm to CSE'),
(94, '2015-12-23 12:12:56', '1305057', 'null', 0, 'hello[null]', 'welcm to CSE'),
(95, '2015-12-23 12:12:57', '1305097', 'null', 0, 'hello[null]', 'welcm to CSE'),
(96, '2015-12-23 12:12:57', '1405001', 'null', 0, 'hello[null]', 'welcm to CSE'),
(97, '2015-12-23 12:12:57', '1405075', 'null', 0, 'hello[null]', 'welcm to CSE'),
(98, '2015-12-23 12:14:10', '1005071', 'azad', 0, 'hello[azad]', 'welcome to CSE 308');

-- --------------------------------------------------------

--
-- Table structure for table `results`
--

CREATE TABLE IF NOT EXISTS `results` (
  `Id` int(11) NOT NULL,
  `Time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `StudentId` varchar(255) NOT NULL,
  `CourseTeacher` varchar(255) NOT NULL,
  `CourseId` varchar(50) NOT NULL,
  `ResultType` varchar(50) NOT NULL,
  `Marks` int(11) NOT NULL,
  `Approved` tinyint(4) NOT NULL DEFAULT '0'
) ENGINE=InnoDB AUTO_INCREMENT=36 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `results`
--

INSERT INTO `results` (`Id`, `Time`, `StudentId`, `CourseTeacher`, `CourseId`, `ResultType`, `Marks`, `Approved`) VALUES
(10, '2015-11-28 01:25:57', '1305097', 'Sakib al hasan', 'CSE202', 'CT', 20, 0),
(11, '2015-11-28 01:25:57', '1305001', 'Sakib al hasan', 'CSE202', 'CT', 20, 0),
(12, '2015-11-28 01:25:57', '1305061', 'Sakib al hasan', 'CSE202', 'CT', 15, 0),
(13, '2015-11-28 01:31:25', '1305101', 'Sakib al hasan', 'CSE202', 'CT', 19, 0),
(14, '2015-11-28 01:31:25', '1305061', 'Sakib al hasan', 'CSE202', 'CT', 15, 0),
(15, '2015-11-28 01:31:25', '1305010', 'Sakib al hasan', 'CSE202', 'CT', 14, 0),
(16, '2015-11-29 11:32:37', '1305101', 'tanvir', 'CSE202', 'CT', 19, 0),
(17, '2015-11-29 11:32:38', '1305061', 'tanvir', 'CSE202', 'CT', 15, 0),
(18, '2015-11-29 11:32:38', '1305010', 'tanvir', 'CSE202', 'CT', 14, 0),
(19, '2015-11-29 11:34:07', '1305101', 'tanvir', 'CSE202', 'CT', 19, 0),
(20, '2015-11-29 11:34:07', '1305061', 'tanvir', 'CSE202', 'CT', 15, 0),
(21, '2015-11-29 11:34:07', '1305010', 'tanvir', 'CSE202', 'CT', 14, 0),
(22, '2015-11-29 12:13:05', '1305101', 'fuck', 'CSE202', 'CT', 19, 0),
(23, '2015-11-29 12:13:05', '1305061', 'fuck', 'CSE202', 'CT', 15, 0),
(24, '2015-11-29 12:13:05', '1305010', 'fuck', 'CSE202', 'CT', 14, 0),
(25, '2015-11-29 12:30:06', '1305101', 'fuck', 'CSE202', 'CT', 19, 0),
(26, '2015-11-29 12:30:06', '1305061', 'fuck', 'CSE202', 'CT', 15, 0),
(27, '2015-11-29 12:30:06', '1305010', 'fuck', 'CSE202', 'CT', 14, 0),
(28, '2015-11-29 12:30:44', '1305101', 'fuck', 'CSE202', 'CT', 19, 0),
(29, '2015-11-29 12:30:44', '1305061', 'fuck', 'CSE202', 'CT', 15, 0),
(30, '2015-11-29 12:30:44', '1305010', 'fuck', 'CSE202', 'CT', 14, 0),
(31, '2015-11-29 12:31:29', '1305101', 'fuck', 'CSE202', 'CT', 19, 0),
(32, '2015-11-29 12:31:29', '1305061', 'fuck', 'CSE202', 'CT', 15, 0),
(33, '2015-11-29 12:31:29', '1305010', 'fuck', 'CSE202', 'CT', 14, 0),
(34, '2015-12-23 07:15:34', '1325', 'kashem', 'CSE201', 'CT-1', 25, 0),
(35, '2015-12-23 07:15:34', '1324', 'kashem', 'CSE201', 'CT-1', 25, 0);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `courses`
--
ALTER TABLE `courses`
  ADD PRIMARY KEY (`courseId`);

--
-- Indexes for table `currentstudents`
--
ALTER TABLE `currentstudents`
  ADD PRIMARY KEY (`studentId`);

--
-- Indexes for table `currentteachers`
--
ALTER TABLE `currentteachers`
  ADD PRIMARY KEY (`teacherId`);

--
-- Indexes for table `notifications`
--
ALTER TABLE `notifications`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `results`
--
ALTER TABLE `results`
  ADD PRIMARY KEY (`Id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `notifications`
--
ALTER TABLE `notifications`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=99;
--
-- AUTO_INCREMENT for table `results`
--
ALTER TABLE `results`
  MODIFY `Id` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=36;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
