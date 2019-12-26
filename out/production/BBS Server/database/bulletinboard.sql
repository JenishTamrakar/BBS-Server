-- phpMyAdmin SQL Dump
-- version 4.9.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Dec 25, 2019 at 07:26 AM
-- Server version: 10.4.8-MariaDB
-- PHP Version: 7.3.10

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `bulletinboard`
--

-- --------------------------------------------------------

--
-- Table structure for table `assignment`
--

CREATE TABLE `assignment` (
  `Assignment_id` int(10) NOT NULL,
  `Assignment_title` varchar(255) DEFAULT NULL,
  `Assignment_level` varchar(10) DEFAULT NULL,
  `Assignment_course` varchar(255) DEFAULT NULL,
  `Assignment_unit` varchar(255) DEFAULT NULL,
  `Assignment_deadline_date` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `assignment`
--

INSERT INTO `assignment` (`Assignment_id`, `Assignment_title`, `Assignment_level`, `Assignment_course`, `Assignment_unit`, `Assignment_deadline_date`) VALUES
(1, 'Project', '6', 'BSc', 'CIS', '2019-12-27');

-- --------------------------------------------------------

--
-- Table structure for table `event`
--

CREATE TABLE `event` (
  `event_id` int(10) NOT NULL,
  `event_title` varchar(255) DEFAULT NULL,
  `event_description` varchar(255) DEFAULT NULL,
  `event_date` varchar(255) DEFAULT NULL,
  `event_time` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `event`
--

INSERT INTO `event` (`event_id`, `event_title`, `event_description`, `event_date`, `event_time`) VALUES
(12, 'Sports', 'Sports Tournament', '2019-12-25', '12:30:00'),
(13, 'Calligraphy', 'Calligraphy Tournament', '2019-12-25', '12:55:00'),
(14, 'Art ', 'Art Tournament', '2019-12-28', '21:04:00'),
(15, 'Beer', 'Beer Drinking', '2019-12-04', '12:53:00'),
(16, 'Hackathon', 'Hackathon Competition', '2019-12-24', '07:00');

-- --------------------------------------------------------

--
-- Table structure for table `faculty`
--

CREATE TABLE `faculty` (
  `faculty_sn` int(11) NOT NULL,
  `faculty_id` int(10) NOT NULL,
  `faculty_name` varchar(255) NOT NULL,
  `faculty_course` varchar(55) NOT NULL,
  `faculty_email` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `faculty`
--

INSERT INTO `faculty` (`faculty_sn`, `faculty_id`, `faculty_name`, `faculty_course`, `faculty_email`) VALUES
(1, 123456, 'Rashim Joshi', 'BSc', 'joc.racm@gmail.com');

-- --------------------------------------------------------

--
-- Table structure for table `feedback`
--

CREATE TABLE `feedback` (
  `feedback_id` int(255) NOT NULL,
  `feedback_title` varchar(255) NOT NULL,
  `feedback_description` varchar(255) NOT NULL,
  `feedback_date` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `feedback`
--

INSERT INTO `feedback` (`feedback_id`, `feedback_title`, `feedback_description`, `feedback_date`) VALUES
(1, 'exam', 'exam ', '2019-12-25');

-- --------------------------------------------------------

--
-- Table structure for table `feedetails`
--

CREATE TABLE `feedetails` (
  `fee_id` int(10) NOT NULL,
  `fee_amount` varchar(50) DEFAULT NULL,
  `fee_deadline_date` varchar(255) DEFAULT NULL,
  `fee_details` varchar(255) DEFAULT NULL,
  `student_level` varchar(5) DEFAULT NULL,
  `student_course` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `feedetails`
--

INSERT INTO `feedetails` (`fee_id`, `fee_amount`, `fee_deadline_date`, `fee_details`, `student_level`, `student_course`) VALUES
(12, '50000', '2019-12-31', 'Yearly', '6', 'BSc'),
(13, '50500', '2019-12-02', 'Yearly', '6', 'BSc'),
(14, '40000', '2019-12-25', 'Yearly', '5', 'BSc');

-- --------------------------------------------------------

--
-- Table structure for table `notice`
--

CREATE TABLE `notice` (
  `notice_id` int(10) NOT NULL,
  `notice_title` varchar(255) DEFAULT NULL,
  `notice_description` varchar(1000) DEFAULT NULL,
  `notice_date` varchar(55) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `student`
--

CREATE TABLE `student` (
  `student_sn` int(11) NOT NULL,
  `student_id` int(10) NOT NULL,
  `student_name` varchar(255) NOT NULL,
  `student_email` varchar(255) NOT NULL,
  `student_level` int(5) NOT NULL,
  `student_course` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `student`
--

INSERT INTO `student` (`student_sn`, `student_id`, `student_name`, `student_email`, `student_level`, `student_course`) VALUES
(0, 1720846, 'Rashim Joshi', 'joc.racm@gmail.com', 6, 'BSc');

-- --------------------------------------------------------

--
-- Table structure for table `user`
--

CREATE TABLE `user` (
  `user_id` varchar(255) NOT NULL,
  `user_password` varchar(255) NOT NULL,
  `user_role` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `user`
--

INSERT INTO `user` (`user_id`, `user_password`, `user_role`) VALUES
('1234', 'admin', 'admin'),
('1111', 'faculty', 'faculty'),
('2222', 'student', 'student');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `assignment`
--
ALTER TABLE `assignment`
  ADD PRIMARY KEY (`Assignment_id`);

--
-- Indexes for table `event`
--
ALTER TABLE `event`
  ADD PRIMARY KEY (`event_id`);

--
-- Indexes for table `faculty`
--
ALTER TABLE `faculty`
  ADD PRIMARY KEY (`faculty_sn`);

--
-- Indexes for table `feedback`
--
ALTER TABLE `feedback`
  ADD PRIMARY KEY (`feedback_id`);

--
-- Indexes for table `feedetails`
--
ALTER TABLE `feedetails`
  ADD PRIMARY KEY (`fee_id`);

--
-- Indexes for table `notice`
--
ALTER TABLE `notice`
  ADD PRIMARY KEY (`notice_id`);

--
-- Indexes for table `student`
--
ALTER TABLE `student`
  ADD PRIMARY KEY (`student_sn`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `assignment`
--
ALTER TABLE `assignment`
  MODIFY `Assignment_id` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT for table `event`
--
ALTER TABLE `event`
  MODIFY `event_id` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=17;

--
-- AUTO_INCREMENT for table `faculty`
--
ALTER TABLE `faculty`
  MODIFY `faculty_sn` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT for table `feedback`
--
ALTER TABLE `feedback`
  MODIFY `feedback_id` int(255) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT for table `feedetails`
--
ALTER TABLE `feedetails`
  MODIFY `fee_id` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=15;

--
-- AUTO_INCREMENT for table `notice`
--
ALTER TABLE `notice`
  MODIFY `notice_id` int(10) NOT NULL AUTO_INCREMENT;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
