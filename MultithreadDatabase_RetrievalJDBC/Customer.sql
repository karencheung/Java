-- phpMyAdmin SQL Dump
-- version 4.7.4
-- https://www.phpmyadmin.net/
--
-- Host: localhost
-- Generation Time: Nov 09, 2017 at 07:21 AM
-- Server version: 10.1.26-MariaDB
-- PHP Version: 7.1.9

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `Customer`
--

-- --------------------------------------------------------

--
-- Table structure for table `Customer`
--

CREATE TABLE `Customer` (
  `Cust_ID` int(11) NOT NULL,
  `Name` varchar(100) NOT NULL,
  `Class` varchar(20) NOT NULL,
  `Address` varchar(100) NOT NULL,
  `CreditLimit` double NOT NULL,
  `Balance` double NOT NULL,
  `Comment` varchar(300) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf16;

--
-- Dumping data for table `Customer`
--

INSERT INTO `Customer` (`Cust_ID`, `Name`, `Class`, `Address`, `CreditLimit`, `Balance`, `Comment`) VALUES
(1, 'John Doe', 'Premier MasterCard', 'Marco Polo Mansion, Causeway Bay, Hong Kong', 1000000, 3000000, 'Good benefits and rewards program.'),
(2, 'Katherine Iu', 'Advance Platinum', 'Marco Polo Mansion, Causeway Bay, Hong Kong', 200000, 400000, 'Great rewards program.'),
(3, 'Nora Cheung', 'Premier MasterCard', 'Haywood Mansion, Causeway Bay, Hong Kong', 1000000, 8000000, 'Good rewards program.'),
(4, 'Echo Doe', 'Visa SignatureCard', 'Haywood Mansion, Causeway Bay, Hong Kong', 480000, 600000, 'N/A');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `Customer`
--
ALTER TABLE `Customer`
  ADD PRIMARY KEY (`Cust_ID`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `Customer`
--
ALTER TABLE `Customer`
  MODIFY `Cust_ID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
