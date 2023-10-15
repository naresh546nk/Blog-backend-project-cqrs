-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Sep 22, 2023 at 08:28 AM
-- Server version: 10.4.28-MariaDB
-- PHP Version: 8.2.4

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `blog`
--

-- --------------------------------------------------------

--
-- Table structure for table `blog`
--

CREATE TABLE `blog` (
  `id` bigint(20) NOT NULL,
  `article` longtext DEFAULT NULL,
  `author_name` varchar(255) DEFAULT NULL,
  `blog_name` varchar(255) DEFAULT NULL,
  `category` varchar(255) DEFAULT NULL,
  `created_on` date DEFAULT NULL,
  `blog_user_id` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `blog`
--

INSERT INTO `blog` (`id`, `article`, `author_name`, `blog_name`, `category`, `created_on`, `blog_user_id`) VALUES
(1, 'Say Yes is an award-winning blog created by Liz Stanley in 2006. Although it could be classified as a mom blog as well, since Liz is a mother of three, it goes beyond that, offering useful advice about other topics, including food, and travel.\nSay Yes is an award-winning blog created by Liz Stanley in 2006. Although it could be classified as a mom blog as well, since Liz is a mother of three, it goes beyond that, offering useful advice about other topics, including food, and travel.\nSay Yes is an award-winning blog created by Liz Stanley in 2006. Although it could be classified as a mom blog as well, since Liz is a mother of three, it goes beyond that, offering useful advice about other topics, including food, and travel.\nSay Yes is an award-winning blog created by Liz Stanley in 2006. Although it could be classified as a mom blog as well, since Liz is a mother of three, it goes beyond that, offering useful advice about other topics, including food, and travel.\nSay Yes is an award-winning blog created by Liz Stanley in 2006. Although it could be classified as a mom blog as well, since Liz is a mother of three, it goes beyond that, offering useful advice about other topics, including food, and travel.\nSay Yes is an award-winning blog created by Liz Stanley in 2006. Although it could be classified as a mom blog as well, since Liz is a mother of three, it goes beyond that, offering useful advice about other topics, including food, and travel.\nSay Yes is an award-winning blog created by Liz Stanley in 2006. Although it could be classified as a mom blog as well, since Liz is a mother of three, it goes beyond that, offering useful advice about other topics, including food, and travel.\nSay Yes is an award-winning blog created by Liz Stanley in 2006. Although it could be classified as a mom blog as well, since Liz is a mother of three, it goes beyond that, offering useful advice about other topics, including food, and travel.', 'Devid Brown', 'Sey Yes', 'Education', '2023-09-22', NULL),
(2, 'This site targets “nerds, misfits and mutants,” and helps them to get in shape through home workouts and private coaching. Nerd Fitness has 25 team members led by Jim Bathurst, an award-winning personal trainer. The site also comes with an educational blog where you can learn more about working out.\nThis site targets “nerds, misfits and mutants,” and helps them to get in shape through home workouts and private coaching. Nerd Fitness has 25 team members led by Jim Bathurst, an award-winning personal trainer. The site also comes with an educational blog where you can learn more about working out.\nThis site targets “nerds, misfits and mutants,” and helps them to get in shape through home workouts and private coaching. Nerd Fitness has 25 team members led by Jim Bathurst, an award-winning personal trainer. The site also comes with an educational blog where you can learn more about working out.\nThis site targets “nerds, misfits and mutants,” and helps them to get in shape through home workouts and private coaching. Nerd Fitness has 25 team members led by Jim Bathurst, an award-winning personal trainer. The site also comes with an educational blog where you can learn more about working out.\nThis site targets “nerds, misfits and mutants,” and helps them to get in shape through home workouts and private coaching. Nerd Fitness has 25 team members led by Jim Bathurst, an award-winning personal trainer. The site also comes with an educational blog where you can learn more about working out.\nThis site targets “nerds, misfits and mutants,” and helps them to get in shape through home workouts and private coaching. Nerd Fitness has 25 team members led by Jim Bathurst, an award-winning personal trainer. The site also comes with an educational blog where you can learn more about working out.', 'Darwin', ' Nerd Fitness', 'Awarness', '2023-09-22', NULL),
(3, 'Created in 2014 by Katie Dunlop, LSF focuses on providing fitness services to women. The site also features an app with fitness plans, workout plans, and more. There’s also a blog section where you can read more about fitness, nutrition, and participate in the community of LSF members.\nCreated in 2014 by Katie Dunlop, LSF focuses on providing fitness services to women. The site also features an app with fitness plans, workout plans, and more. There’s also a blog section where you can read more about fitness, nutrition, and participate in the community of LSF members.\nCreated in 2014 by Katie Dunlop, LSF focuses on providing fitness services to women. The site also features an app with fitness plans, workout plans, and more. There’s also a blog section where you can read more about fitness, nutrition, and participate in the community of LSF members.\nCreated in 2014 by Katie Dunlop, LSF focuses on providing fitness services to women. The site also features an app with fitness plans, workout plans, and more. There’s also a blog section where you can read more about fitness, nutrition, and participate in the community of LSF members.', 'Brown Lara', 'Love Sweat Fitness', 'Love', '2023-09-22', NULL);

-- --------------------------------------------------------

--
-- Table structure for table `blog_user`
--

CREATE TABLE `blog_user` (
  `id` bigint(20) NOT NULL,
  `authority` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `username` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `blog_user`
--

INSERT INTO `blog_user` (`id`, `authority`, `name`, `username`) VALUES
(100, 'ADMIN', 'Naresh Kumar', 'naresh546.nk@gmail.com'),
(101, 'USER', 'Naresh', 'nareshkumar@546.nk@gmail.com');

-- --------------------------------------------------------

--
-- Table structure for table `hibernate_sequence`
--

CREATE TABLE `hibernate_sequence` (
  `next_val` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `hibernate_sequence`
--

INSERT INTO `hibernate_sequence` (`next_val`) VALUES
(1);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `blog`
--
ALTER TABLE `blog`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKm581ndqvn4647yxcve49nuvg4` (`blog_user_id`);

--
-- Indexes for table `blog_user`
--
ALTER TABLE `blog_user`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `blog`
--
ALTER TABLE `blog`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `blog`
--
ALTER TABLE `blog`
  ADD CONSTRAINT `FKm581ndqvn4647yxcve49nuvg4` FOREIGN KEY (`blog_user_id`) REFERENCES `blog_user` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
