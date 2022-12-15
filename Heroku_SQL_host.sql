-- phpMyAdmin SQL Dump
-- version 4.7.1
-- https://www.phpmyadmin.net/
--
-- Host: sql4.freesqldatabase.com
-- Generation Time: Apr 13, 2022 at 10:04 AM
-- Server version: 5.5.54-0ubuntu0.12.04.1
-- PHP Version: 7.0.33-0ubuntu0.16.04.16

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `sql4484367`
--

-- --------------------------------------------------------

--
-- Table structure for table `added_answer`
--

CREATE TABLE `jnymjcp0uyvpzyu9`.`added_answer` (
  `id` int(11) NOT NULL,
  `courseName` varchar(50) DEFAULT NULL,
  `courseID` int(11) NOT NULL,
  `tutorName` varchar(25) DEFAULT NULL,
  `givenMarks` varchar(15) DEFAULT NULL,
  `question` varchar(20) DEFAULT NULL,
  `studentName` varchar(20) DEFAULT NULL,
  `regNo` varchar(20) DEFAULT NULL,
  `answer` varchar(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `added_answer`
--

INSERT INTO `jnymjcp0uyvpzyu9`.`added_answer` (`id`, `courseName`, `courseID`, `tutorName`, `givenMarks`, `question`, `studentName`, `regNo`, `answer`) VALUES
(3, 'EE', 101, 'Ross', '55', 'Second thought.', 'Rachel', '2022', 'Will try.');

-- --------------------------------------------------------

--
-- Table structure for table `add_assessment`
--

CREATE TABLE `jnymjcp0uyvpzyu9`.`add_assessment` (
  `id` int(11) NOT NULL,
  `courseName` varchar(50) DEFAULT NULL,
  `courseID` int(11) NOT NULL,
  `tutorName` varchar(25) DEFAULT NULL,
  `givenMarks` varchar(15) DEFAULT NULL,
  `question` varchar(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `add_assessment`
--

INSERT INTO `jnymjcp0uyvpzyu9`.`add_assessment` (`id`, `courseName`, `courseID`, `tutorName`, `givenMarks`, `question`) VALUES
(3, 'EE', 101, 'Ross', '55', 'Second thought.');

-- --------------------------------------------------------

--
-- Table structure for table `add_course`
--

CREATE TABLE `jnymjcp0uyvpzyu9`.`add_course` (
  `id` int(11) NOT NULL,
  `courseName` varchar(50) DEFAULT NULL,
  `courseID` int(11) NOT NULL,
  `tutorName` varchar(25) DEFAULT NULL,
  `assignedMarks` varchar(15) DEFAULT NULL,
  `courseContent` varchar(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `add_course`
--

INSERT INTO `jnymjcp0uyvpzyu9`.`add_course` (`id`, `courseName`, `courseID`, `tutorName`, `assignedMarks`, `courseContent`) VALUES
(2, 'CS', 101, 'John', '100', 'What is cs?'),
(3, 'EE', 101, 'Ross', '100', 'What is EE?');

-- --------------------------------------------------------

--
-- Table structure for table `communication`
--

CREATE TABLE `jnymjcp0uyvpzyu9`.`communication` (
  `id` int(11) NOT NULL,
  `courseName` varchar(15) DEFAULT NULL,
  `tutorName` varchar(15) DEFAULT NULL,
  `studentName` varchar(50) DEFAULT NULL,
  `fatherName` varchar(15) DEFAULT NULL,
  `parentMessage` varchar(50) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `communication`
--

INSERT INTO `jnymjcp0uyvpzyu9`.`communication` (`id`, `courseName`, `tutorName`, `studentName`, `fatherName`, `parentMessage`) VALUES
(1, 'Ross', 'John', 'CS', 'Emma', 'How is he doing?');

-- --------------------------------------------------------

--
-- Table structure for table `employee_fee`
--

CREATE TABLE `jnymjcp0uyvpzyu9`.`employee_fee` (
  `employeeid` int(11) NOT NULL,
  `year` varchar(50) DEFAULT NULL,
  `salary` int(11) NOT NULL,
  `month` varchar(50) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `enroll_course`
--

CREATE TABLE `jnymjcp0uyvpzyu9`.`enroll_course` (
  `id` int(11) NOT NULL,
  `courseName` varchar(50) DEFAULT NULL,
  `courseID` int(11) NOT NULL,
  `tutorName` varchar(25) DEFAULT NULL,
  `studentName` varchar(15) DEFAULT NULL,
  `regNo` varchar(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `enroll_course`
--

INSERT INTO `jnymjcp0uyvpzyu9`.`enroll_course` (`id`, `courseName`, `courseID`, `tutorName`, `studentName`, `regNo`) VALUES
(1, 'CS', 101, 'John', 'Joey', '2017'),
(2, 'CS', 101, 'John', 'Rachel', '2022'),
(3, 'EE', 101, 'Ross', 'Rachel', '2022'),
(4, 'CS', 101, 'John', 'Emma', '2021');

-- --------------------------------------------------------

--
-- Table structure for table `leaved_employee`
--

CREATE TABLE `jnymjcp0uyvpzyu9`.`leaved_employee` (
  `id` int(11) NOT NULL,
  `name` varchar(50) DEFAULT NULL,
  `hostel_name` varchar(20) DEFAULT NULL,
  `tel` varchar(15) DEFAULT NULL,
  `emg_tel` varchar(15) DEFAULT NULL,
  `leave_date` varchar(50) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `leaved_students`
--

CREATE TABLE `jnymjcp0uyvpzyu9`.`leaved_students` (
  `id` int(11) NOT NULL,
  `name` varchar(50) DEFAULT NULL,
  `reg_no` int(11) NOT NULL,
  `email` varchar(25) DEFAULT NULL,
  `phonenumber` varchar(15) DEFAULT NULL,
  `hostel_name` varchar(20) DEFAULT NULL,
  `address` varchar(50) DEFAULT NULL,
  `guardname` varchar(50) DEFAULT NULL,
  `guardtel` varchar(15) DEFAULT NULL,
  `leave_date` varchar(50) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `parent_student`
--

CREATE TABLE `jnymjcp0uyvpzyu9`.`parent_student` (
  `id` int(11) NOT NULL,
  `fatherName` varchar(50) DEFAULT NULL,
  `studentName` varchar(15) DEFAULT NULL,
  `tutorName` varchar(15) DEFAULT NULL,
  `time` varchar(50) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `parent_student`
--

INSERT INTO `jnymjcp0uyvpzyu9`.`parent_student` (`id`, `fatherName`, `studentName`, `tutorName`, `time`) VALUES
(1, 'Ross', 'Emma', 'John', '04/12/22, 10 AM');

-- --------------------------------------------------------

--
-- Table structure for table `payment`
--

CREATE TABLE `jnymjcp0uyvpzyu9`.`payment` (
  `id` int(11) NOT NULL,
  `courseName` varchar(50) DEFAULT NULL,
  `courseID` int(11) NOT NULL,
  `payment` varchar(25) DEFAULT NULL,
  `studentName` varchar(15) DEFAULT NULL,
  `regNo` varchar(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `payment`
--

INSERT INTO `jnymjcp0uyvpzyu9`.`payment` (`id`, `courseName`, `courseID`, `payment`, `studentName`, `regNo`) VALUES
(1, 'CS', 101, '1000', 'Joey', '2017'),
(2, 'CS', 101, NULL, 'Rachel', '2022'),
(3, 'EE', 101, NULL, 'Rachel', '2022'),
(4, 'CS', 101, NULL, 'Emma', '2021');

-- --------------------------------------------------------

--
-- Table structure for table `register_Employee`
--

CREATE TABLE `jnymjcp0uyvpzyu9`.`register_Employee` (
  `id` int(11) NOT NULL,
  `name` varchar(50) DEFAULT NULL,
  `hostel_name` varchar(20) DEFAULT NULL,
  `tel` varchar(15) DEFAULT NULL,
  `emg_tel` varchar(15) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `register_Students`
--

CREATE TABLE `jnymjcp0uyvpzyu9`.`register_Students` (
  `id` int(11) NOT NULL,
  `name` varchar(50) DEFAULT NULL,
  `reg_no` int(11) NOT NULL,
  `email` varchar(25) DEFAULT NULL,
  `phoneNumber` varchar(15) DEFAULT NULL,
  `hostel_name` varchar(20) DEFAULT NULL,
  `address` varchar(50) DEFAULT NULL,
  `guardName` varchar(50) DEFAULT NULL,
  `guardTel` varchar(15) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `register_Students`
--

INSERT INTO `jnymjcp0uyvpzyu9`.`register_Students` (`id`, `name`, `reg_no`, `email`, `phoneNumber`, `hostel_name`, `address`, `guardName`, `guardTel`) VALUES
(1, 'aa', 5665665, 'abc@gmail.com', '016556666', 'ded', 'dede', 'ded', '01515');

-- --------------------------------------------------------

--
-- Table structure for table `student_fee`
--

CREATE TABLE `jnymjcp0uyvpzyu9`.`student_fee` (
  `studentid` int(11) NOT NULL,
  `year` varchar(50) DEFAULT NULL,
  `fee` varchar(50) DEFAULT NULL,
  `month` varchar(50) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `timetable`
--

CREATE TABLE `jnymjcp0uyvpzyu9`.`timetable` (
  `id` int(11) NOT NULL,
  `courseName` varchar(50) DEFAULT NULL,
  `courseID` varchar(15) DEFAULT NULL,
  `tutorName` varchar(15) DEFAULT NULL,
  `courseDuration` varchar(15) DEFAULT NULL,
  `assignmentDuration` varchar(15) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `timetable`
--

INSERT INTO `jnymjcp0uyvpzyu9`.`timetable` (`id`, `courseName`, `courseID`, `tutorName`, `courseDuration`, `assignmentDuration`) VALUES
(2, 'CS', '101', 'John', '1 Month', '60 Hours'),
(3, 'EE', '101', 'Ross', '1 Month', '3 Hours');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `add_assessment`
--
ALTER TABLE `jnymjcp0uyvpzyu9`.`add_assessment`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `add_course`
--
ALTER TABLE `jnymjcp0uyvpzyu9`.`add_course`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `enroll_course`
--
ALTER TABLE `jnymjcp0uyvpzyu9`.`enroll_course`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `parent_student`
--
ALTER TABLE `jnymjcp0uyvpzyu9`.`parent_student`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `payment`
--
ALTER TABLE `jnymjcp0uyvpzyu9`.`payment`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `register_Employee`
--
ALTER TABLE `jnymjcp0uyvpzyu9`.`register_Employee`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `register_Students`
--
ALTER TABLE `jnymjcp0uyvpzyu9`.`register_Students`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `add_assessment`
--
ALTER TABLE `jnymjcp0uyvpzyu9`.`add_assessment`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;
--
-- AUTO_INCREMENT for table `add_course`
--
ALTER TABLE `jnymjcp0uyvpzyu9`.`add_course`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;
--
-- AUTO_INCREMENT for table `enroll_course`
--
ALTER TABLE `jnymjcp0uyvpzyu9`.`enroll_course`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;
--
-- AUTO_INCREMENT for table `parent_student`
--
ALTER TABLE `jnymjcp0uyvpzyu9`.`parent_student`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;
--
-- AUTO_INCREMENT for table `payment`
--
ALTER TABLE `jnymjcp0uyvpzyu9`.`payment`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;
--
-- AUTO_INCREMENT for table `register_Employee`
--
ALTER TABLE `jnymjcp0uyvpzyu9`.`register_Employee`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `register_Students`
--
ALTER TABLE `jnymjcp0uyvpzyu9`.`register_Students`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
