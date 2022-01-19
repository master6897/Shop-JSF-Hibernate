-- phpMyAdmin SQL Dump
-- version 5.0.4
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Czas generowania: 18 Sty 2022, 18:09
-- Wersja serwera: 10.4.17-MariaDB
-- Wersja PHP: 8.0.2

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Baza danych: `shop`
--

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `brand`
--

CREATE TABLE `brand` (
  `id` int(11) NOT NULL,
  `name` varchar(60) COLLATE utf8_polish_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_polish_ci;

--
-- Zrzut danych tabeli `brand`
--

INSERT INTO `brand` (`id`, `name`) VALUES
(1, 'Nike'),
(2, 'Adidas'),
(3, 'Reebok'),
(4, 'Calvin Klein'),
(5, 'Mata'),
(6, 'Lacoste');

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `category`
--

CREATE TABLE `category` (
  `id` int(11) NOT NULL,
  `cat_name` varchar(60) COLLATE utf8_polish_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_polish_ci;

--
-- Zrzut danych tabeli `category`
--

INSERT INTO `category` (`id`, `cat_name`) VALUES
(1, 'sneakersy'),
(2, 'casual'),
(3, 'Zimowe'),
(4, 'Eleganckie'),
(5, 'Letnie'),
(6, 'Półbuty');

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `delivery_adress`
--

CREATE TABLE `delivery_adress` (
  `id` int(11) NOT NULL,
  `name` varchar(60) COLLATE utf8_polish_ci NOT NULL,
  `city` varchar(60) COLLATE utf8_polish_ci NOT NULL,
  `post_code` varchar(6) COLLATE utf8_polish_ci NOT NULL,
  `street` varchar(60) COLLATE utf8_polish_ci NOT NULL,
  `adress` int(11) NOT NULL,
  `adress2` int(11) DEFAULT NULL,
  `user_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_polish_ci;

--
-- Zrzut danych tabeli `delivery_adress`
--

INSERT INTO `delivery_adress` (`id`, `name`, `city`, `post_code`, `street`, `adress`, `adress2`, `user_id`) VALUES
(1, 'Katowice', 'Katowice', '40-004', 'Kurpiowska', 19, 19, 2),
(2, 'Morze', 'Szczecin', '33-333', 'Wojska Polskiego', 33, 22, 2),
(4, 'Góry', 'Zabrze', '23-567', 'Zielona', 78, 0, 2),
(5, 'Domek Letniskowy', 'Białka Tatrzańska', '45-500', 'Leśna', 3, 45, 4),
(17, 'Wschód', 'Białystok', '45-500', 'Leśna', 4, 0, 2);

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `orders`
--

CREATE TABLE `orders` (
  `id` bigint(20) NOT NULL,
  `user_id` int(11) NOT NULL,
  `adres` varchar(60) COLLATE utf8_polish_ci NOT NULL DEFAULT 'default'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_polish_ci;

--
-- Zrzut danych tabeli `orders`
--

INSERT INTO `orders` (`id`, `user_id`, `adres`) VALUES
(22010402270515, 2, 'Morze'),
(22010604210717, 2, 'Katowice'),
(22010605000818, 4, 'default'),
(220104015022122, 2, 'default'),
(220104015110110, 2, 'Morze'),
(220104022749149, 2, 'Góry'),
(220106041417117, 2, 'Góry'),
(220106042527127, 2, 'default'),
(220113075028128, 2, 'Wschód'),
(220113075214114, 2, 'Wschód'),
(220113075519119, 2, 'Katowice'),
(220113080113113, 2, 'Morze'),
(220113080753153, 4, 'Domek Letniskowy'),
(220118041552152, 2, 'default'),
(2112011014521252, 2, 'Morze'),
(2112020318131213, 2, 'default'),
(2112040346261226, 2, 'Katowice'),
(2112040348101210, 2, 'Góry');

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `pictures`
--

CREATE TABLE `pictures` (
  `id` int(11) NOT NULL,
  `picture` longblob NOT NULL,
  `shoe_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_polish_ci;

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `quantity_size`
--

CREATE TABLE `quantity_size` (
  `id` int(11) NOT NULL,
  `quantity` int(11) NOT NULL,
  `shoe_id` int(11) NOT NULL,
  `size_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_polish_ci;

--
-- Zrzut danych tabeli `quantity_size`
--

INSERT INTO `quantity_size` (`id`, `quantity`, `shoe_id`, `size_id`) VALUES
(1, 0, 3, 15),
(2, 0, 3, 8),
(11, 0, 10, 11),
(12, 0, 10, 11),
(14, 0, 11, 7),
(17, 57, 3, 15),
(18, 68, 9, 12),
(19, 0, 10, 5);

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `shoe`
--

CREATE TABLE `shoe` (
  `id` int(11) NOT NULL,
  `name` varchar(60) COLLATE utf8_polish_ci NOT NULL,
  `price` int(60) NOT NULL,
  `descr` text COLLATE utf8_polish_ci NOT NULL,
  `color` varchar(60) COLLATE utf8_polish_ci NOT NULL,
  `picture` varchar(1000) COLLATE utf8_polish_ci NOT NULL,
  `brand_id` int(11) NOT NULL,
  `category_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_polish_ci;

--
-- Zrzut danych tabeli `shoe`
--

INSERT INTO `shoe` (`id`, `name`, `price`, `descr`, `color`, `picture`, `brand_id`, `category_id`) VALUES
(3, 'Air Max 97', 300, 'genialny obuw', 'czerwony', 'https://www.eobuwie.com.pl/media/catalog/product/cache/image/650x650/0/0/0000208766845_01_ph_1.jpg', 1, 1),
(9, 'ZX Flux', 400, 'czarny obuw', 'czarny', 'https://www.eobuwie.com.pl/media/catalog/product/cache/image/650x650/0/0/0000197532742_01_mf.jpg', 1, 2),
(10, 'Zx 2K Flux', 350, 'ciekawy obuw', 'zielony', 'https://www.eobuwie.com.pl/media/catalog/product/cache/image/650x650/0/0/0000206873897_03_ki.jpg', 2, 1),
(11, 'Continental 80 G27706', 279, 'Ten model butów posiada wnętrze wyścielone wytrzymałym i jednocześnie miękkim materiałem, który gwarantuje wygodę oraz odpowiednią wentylację stóp.', 'biały', 'https://www.eobuwie.com.pl/media/catalog/product/cache/image/650x650/0/0/0000200855530_01_mn_11.jpg', 2, 1),
(12, 'Stan Smith FX5502', 289, 'Powyższe buty zostały wyposażone w twardą wyściółkę, która nie dość, że jest odporniejsza na zdzieranie, to jeszcze utrzymuje stopę we właściwym położeniu. W sam raz do codziennej gonitwy!', 'zielony', 'https://www.eobuwie.com.pl/media/catalog/product/cache/image/650x650/0/0/0000207773493_01_ki_1.jpg', 2, 1),
(13, 'Air Max 270', 400, 'Podeszwa powy?szych butów zosta?a wykonana z innowacyjnego tworzywa, co nadaje im jeszcze bardziej sportowy i wspó?czesny charakter, a przy tym zapewnia komfort codziennego u?ytkowania.', 'bia?y', 'https://www.eobuwie.com.pl/media/catalog/product/cache/image/650x650/0/0/0000209252927_01_ki.jpg', 1, 1);

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `size`
--

CREATE TABLE `size` (
  `id` int(11) NOT NULL,
  `value` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_polish_ci;

--
-- Zrzut danych tabeli `size`
--

INSERT INTO `size` (`id`, `value`) VALUES
(2, 35),
(3, 36),
(4, 37),
(5, 38),
(6, 39),
(7, 40),
(8, 41),
(9, 42),
(10, 43),
(11, 44),
(12, 45),
(13, 46),
(14, 47),
(15, 48);

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `temp_order`
--

CREATE TABLE `temp_order` (
  `id` int(11) NOT NULL,
  `date` datetime NOT NULL DEFAULT current_timestamp(),
  `order_quantity` int(11) NOT NULL DEFAULT 1,
  `status` int(1) NOT NULL DEFAULT 0,
  `shoe_id` int(11) NOT NULL,
  `quantity_size_id` int(11) NOT NULL,
  `user_id` int(11) NOT NULL,
  `order_id` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_polish_ci;

--
-- Zrzut danych tabeli `temp_order`
--

INSERT INTO `temp_order` (`id`, `date`, `order_quantity`, `status`, `shoe_id`, `quantity_size_id`, `user_id`, `order_id`) VALUES
(22, '2021-12-01 10:14:43', 3, 1, 3, 2, 2, 2112011014521252),
(23, '2021-12-02 15:17:48', 2, 1, 3, 2, 2, 2112020318131213),
(46, '2021-12-04 15:46:18', 10, 1, 3, 1, 2, 2112040346261226),
(48, '2021-12-04 15:48:02', 25, 1, 3, 2, 2, 2112040348101210),
(54, '2022-01-04 13:50:06', 1, 1, 10, 12, 2, 220104015022122),
(55, '2022-01-04 13:51:01', 3, 1, 10, 11, 2, 220104015110110),
(56, '2022-01-04 14:26:55', 1, 1, 10, 12, 2, 22010402270515),
(59, '2022-01-04 14:27:43', 1, 1, 10, 12, 2, 220104022749149),
(70, '2022-01-06 16:12:20', 7, 1, 10, 12, 2, 220106041417117),
(71, '2022-01-06 16:15:46', 1, 1, 3, 17, 2, 22010604210717),
(72, '2022-01-06 16:25:13', 2, 1, 3, 17, 2, 220106042527127),
(73, '2022-01-06 16:56:39', 1, 1, 10, 12, 4, 22010605000818),
(75, '2022-01-13 19:45:50', 3, 1, 9, 18, 2, 220113075028128),
(76, '2022-01-13 19:52:07', 1, 1, 9, 18, 2, 220113075214114),
(77, '2022-01-13 19:54:48', 2, 1, 9, 18, 2, 220113075519119),
(78, '2022-01-13 20:01:03', 4, 1, 9, 18, 2, 220113080113113),
(79, '2022-01-13 20:07:26', 2, 1, 9, 18, 4, 220113080753153),
(80, '2022-01-13 20:07:39', 1, 1, 10, 11, 4, 220113080753153),
(84, '2022-01-18 16:14:13', 2, 1, 10, 19, 2, 220118041552152);

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `user`
--

CREATE TABLE `user` (
  `id` int(11) NOT NULL,
  `email` varchar(60) COLLATE utf8_polish_ci NOT NULL,
  `pass` varchar(60) COLLATE utf8_polish_ci NOT NULL,
  `role` varchar(60) COLLATE utf8_polish_ci NOT NULL DEFAULT 'user',
  `city` varchar(60) COLLATE utf8_polish_ci NOT NULL,
  `post_code` varchar(6) COLLATE utf8_polish_ci NOT NULL,
  `street` varchar(60) COLLATE utf8_polish_ci NOT NULL,
  `adress` int(11) NOT NULL,
  `adress2` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_polish_ci;

--
-- Zrzut danych tabeli `user`
--

INSERT INTO `user` (`id`, `email`, `pass`, `role`, `city`, `post_code`, `street`, `adress`, `adress2`) VALUES
(2, 'pucmarcin@gmail.com', 'zaq12wsx', 'admin', 'Katowice', '44-004', 'Korfantego', 26, 16),
(3, 'marta1455@gmail.com', 'zaq12wsx', 'user', 'Katowice', '44-004', 'Zielona', 20, 3),
(4, 'maciek@gmail.com', 'cdcd', 'user', 'cdcd', '44-444', 'cdcd', 1, 0);

--
-- Indeksy dla zrzutów tabel
--

--
-- Indeksy dla tabeli `brand`
--
ALTER TABLE `brand`
  ADD PRIMARY KEY (`id`);

--
-- Indeksy dla tabeli `category`
--
ALTER TABLE `category`
  ADD PRIMARY KEY (`id`);

--
-- Indeksy dla tabeli `delivery_adress`
--
ALTER TABLE `delivery_adress`
  ADD PRIMARY KEY (`id`),
  ADD KEY `delivery_adress_user_fk` (`user_id`);

--
-- Indeksy dla tabeli `orders`
--
ALTER TABLE `orders`
  ADD PRIMARY KEY (`id`),
  ADD KEY `order_user_fk` (`user_id`);

--
-- Indeksy dla tabeli `pictures`
--
ALTER TABLE `pictures`
  ADD PRIMARY KEY (`id`),
  ADD KEY `pictures_shoe_fk` (`shoe_id`);

--
-- Indeksy dla tabeli `quantity_size`
--
ALTER TABLE `quantity_size`
  ADD PRIMARY KEY (`id`) USING BTREE,
  ADD KEY `quantity_size_shoe_fk` (`shoe_id`),
  ADD KEY `quantity_size_size_fk` (`size_id`);

--
-- Indeksy dla tabeli `shoe`
--
ALTER TABLE `shoe`
  ADD PRIMARY KEY (`id`),
  ADD KEY `shoe__idx` (`brand_id`) USING BTREE,
  ADD KEY `shoe__idxv1` (`category_id`) USING BTREE;

--
-- Indeksy dla tabeli `size`
--
ALTER TABLE `size`
  ADD PRIMARY KEY (`id`);

--
-- Indeksy dla tabeli `temp_order`
--
ALTER TABLE `temp_order`
  ADD PRIMARY KEY (`id`) USING BTREE,
  ADD KEY `temp_order_shoe_fk` (`shoe_id`),
  ADD KEY `temp_order_quantity_size_fk` (`quantity_size_id`),
  ADD KEY `temp_order_user_id_fk` (`user_id`) USING BTREE,
  ADD KEY `temp_orders_order_fk` (`order_id`) USING BTREE;

--
-- Indeksy dla tabeli `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT dla zrzuconych tabel
--

--
-- AUTO_INCREMENT dla tabeli `brand`
--
ALTER TABLE `brand`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- AUTO_INCREMENT dla tabeli `category`
--
ALTER TABLE `category`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- AUTO_INCREMENT dla tabeli `delivery_adress`
--
ALTER TABLE `delivery_adress`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=18;

--
-- AUTO_INCREMENT dla tabeli `pictures`
--
ALTER TABLE `pictures`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT dla tabeli `quantity_size`
--
ALTER TABLE `quantity_size`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=20;

--
-- AUTO_INCREMENT dla tabeli `shoe`
--
ALTER TABLE `shoe`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=14;

--
-- AUTO_INCREMENT dla tabeli `size`
--
ALTER TABLE `size`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=31;

--
-- AUTO_INCREMENT dla tabeli `temp_order`
--
ALTER TABLE `temp_order`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=85;

--
-- AUTO_INCREMENT dla tabeli `user`
--
ALTER TABLE `user`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- Ograniczenia dla zrzutów tabel
--

--
-- Ograniczenia dla tabeli `delivery_adress`
--
ALTER TABLE `delivery_adress`
  ADD CONSTRAINT `delivery_adress_user_fk` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`);

--
-- Ograniczenia dla tabeli `orders`
--
ALTER TABLE `orders`
  ADD CONSTRAINT `order_user_fk` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`);

--
-- Ograniczenia dla tabeli `pictures`
--
ALTER TABLE `pictures`
  ADD CONSTRAINT `pictures_shoe_fk` FOREIGN KEY (`shoe_id`) REFERENCES `shoe` (`id`);

--
-- Ograniczenia dla tabeli `quantity_size`
--
ALTER TABLE `quantity_size`
  ADD CONSTRAINT `quantity_size_shoe_fk` FOREIGN KEY (`shoe_id`) REFERENCES `shoe` (`id`),
  ADD CONSTRAINT `quantity_size_size_fk` FOREIGN KEY (`size_id`) REFERENCES `size` (`id`);

--
-- Ograniczenia dla tabeli `shoe`
--
ALTER TABLE `shoe`
  ADD CONSTRAINT `shoe_brand_fk` FOREIGN KEY (`brand_id`) REFERENCES `brand` (`id`),
  ADD CONSTRAINT `shoe_category_fk` FOREIGN KEY (`category_id`) REFERENCES `category` (`id`);

--
-- Ograniczenia dla tabeli `temp_order`
--
ALTER TABLE `temp_order`
  ADD CONSTRAINT `temp_order_quantity_size_fk` FOREIGN KEY (`quantity_size_id`) REFERENCES `quantity_size` (`id`),
  ADD CONSTRAINT `temp_order_shoe_fk` FOREIGN KEY (`shoe_id`) REFERENCES `shoe` (`id`),
  ADD CONSTRAINT `temp_order_user_id` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`),
  ADD CONSTRAINT `temp_orders_order_fk` FOREIGN KEY (`order_id`) REFERENCES `orders` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
