-- phpMyAdmin SQL Dump
-- version 4.4.12
-- http://www.phpmyadmin.net
--
-- Host: 127.0.0.1
-- Generation Time: Dec 05, 2015 at 05:58 PM
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
  `CourseTeacher` char(255) NOT NULL,
  `CourseId` char(255) NOT NULL,
  `Level` tinyint(4) NOT NULL,
  `Term` tinyint(4) NOT NULL,
  `Section` char(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COMMENT='This table will store the running courses and the teachers';

--
-- Dumping data for table `coursesbyteacher`
--

INSERT INTO `coursesbyteacher` (`CourseTeacher`, `CourseId`, `Level`, `Term`, `Section`) VALUES
('Sukarna', 'CSE203', 2, 1, 'A'),
('Tavir', 'CSE202', 2, 1, 'A2');

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
  `email` varchar(400) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `currentstudents`
--

INSERT INTO `currentstudents` (`studentId`, `studentName`, `password`, `hallName`, `level`, `term`, `Section`, `email`) VALUES
('1305097', 'Muktadir Rahman', '150250', 'NazrulIslamHall', 2, 1, 'A2', 'muktadir.rhmn@gmail.com'),
('1305099', 'Rafeen Hossain', '150250', 'NazrulIslam', 2, 1, 'A1', '');

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
('head', 'Mahfuzul Islam', '150250', 'Proffessor', 'CSE201', '', ''),
('sakibbai', 'Skib the vondo', '150250', 'lecturer', 'CSE202 CSE303', 'sakibbai@gmail.com', 'www.sakibbai.com'),
('tanvir', 'Tanvir Ahmed Khan', '150250', 'Lecturer', 'CSE202,CSE101', 'tak@cse.buet.ac.bd', '');

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
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `notifications`
--

INSERT INTO `notifications` (`id`, `Time`, `To`, `From`, `Seen`, `Title`, `Body`) VALUES
(1, '2015-11-22 16:46:40', '1305097', 'azad', 1, 'Testing the notificaiton', 'My database has successfully Ran'),
(2, '2015-11-22 16:46:40', '1305001', 'Ashiq', 0, 'TitleOf the notification', 'Fuck You alll'),
(3, '2015-11-22 16:46:40', '1305001', 'Ashiq', 0, 'TitleOf the notification', 'Fuck You alll'),
(4, '2015-11-22 16:46:40', '1305097', 'Sukarna', 1, 'Welcome to DataStructure[CSE203]', 'I am sukarana from CSE, BUET. I will teach you all the datastruce techniques'),
(5, '2015-11-22 16:46:40', '1305099', 'Sukarna', 0, 'Welcome to DataStructure[CSE203]', 'I am sukarana from CSE, BUET. I will teach you all the datastruce techniques'),
(6, '2015-11-22 16:46:40', '1305097', 'Sukarna', 1, 'Welcome to DataStructure[CSE203]', 'I am sukarana from CSE, BUET. I will teach you all the datastruce techniques'),
(7, '2015-11-22 16:46:40', '1305099', 'Sukarna', 0, 'Welcome to DataStructure[CSE203]', 'I am sukarana from CSE, BUET. I will teach you all the datastruce techniques'),
(8, '2015-11-22 16:46:40', '1305097', 'Tavir', 1, 'Class Hobe na[CSE202]', 'Bacchara ajke amar mejaj kharap. ajke class nibo na.'),
(9, '2015-11-29 12:31:29', 'HEAD', 'fuck', 0, 'CT[CSE202] Result requires approval', 'CT Result from the course CSE202 requires your approval to be published'),
(10, '2015-12-02 10:10:07', '1305101', 'tanvir', 0, 'CT[CSE202]result has been published', 'CT result of the course CSE202 by tanvir has been pulished. \nYou have got 19 marks in the exam.'),
(11, '2015-12-02 10:10:07', '1305061', 'tanvir', 0, 'CT[CSE202]result has been published', 'CT result of the course CSE202 by tanvir has been pulished. \nYou have got 15 marks in the exam.'),
(12, '2015-12-02 10:10:07', '1305010', 'tanvir', 0, 'CT[CSE202]result has been published', 'CT result of the course CSE202 by tanvir has been pulished. \nYou have got 14 marks in the exam.'),
(13, '2015-12-02 10:10:07', '1305101', 'tanvir', 0, 'CT[CSE202]result has been published', 'CT result of the course CSE202 by tanvir has been pulished. \nYou have got 19 marks in the exam.'),
(14, '2015-12-02 10:10:07', '1305061', 'tanvir', 0, 'CT[CSE202]result has been published', 'CT result of the course CSE202 by tanvir has been pulished. \nYou have got 15 marks in the exam.'),
(15, '2015-12-02 10:10:07', '1305010', 'tanvir', 0, 'CT[CSE202]result has been published', 'CT result of the course CSE202 by tanvir has been pulished. \nYou have got 14 marks in the exam.');

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
) ENGINE=InnoDB AUTO_INCREMENT=34 DEFAULT CHARSET=latin1;

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
(16, '2015-11-29 11:32:37', '1305101', 'tanvir', 'CSE202', 'CT', 19, 1),
(17, '2015-11-29 11:32:38', '1305061', 'tanvir', 'CSE202', 'CT', 15, 1),
(18, '2015-11-29 11:32:38', '1305010', 'tanvir', 'CSE202', 'CT', 14, 1),
(19, '2015-11-29 11:34:07', '1305101', 'tanvir', 'CSE202', 'CT', 19, 1),
(20, '2015-11-29 11:34:07', '1305061', 'tanvir', 'CSE202', 'CT', 15, 1),
(21, '2015-11-29 11:34:07', '1305010', 'tanvir', 'CSE202', 'CT', 14, 1),
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
(33, '2015-11-29 12:31:29', '1305010', 'fuck', 'CSE202', 'CT', 14, 0);

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
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=16;
--
-- AUTO_INCREMENT for table `results`
--
ALTER TABLE `results`
  MODIFY `Id` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=34;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
