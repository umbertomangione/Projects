-- phpMyAdmin SQL Dump
-- version 5.1.0
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Creato il: Giu 02, 2021 alle 10:58
-- Versione del server: 10.4.18-MariaDB
-- Versione PHP: 8.0.3

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `findaconcert`
--

-- --------------------------------------------------------

--
-- Struttura della tabella `artisti`
--

CREATE TABLE `artisti` (
  `NomeArtista` varchar(30) NOT NULL,
  `Genere` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dump dei dati per la tabella `artisti`
--

INSERT INTO `artisti` (`NomeArtista`, `Genere`) VALUES
('Caparezza', 'Hip-hop/Rap'),
('Daft Punk', 'EDM/Elettr'),
('Francesco Gabbani', 'Pop'),
('Levante', 'Rock/Pop'),
('The Weeknd', 'R&B/Soul');

-- --------------------------------------------------------

--
-- Struttura della tabella `biglietti`
--

CREATE TABLE `biglietti` (
  `idBiglietto` int(11) NOT NULL,
  `NumeroBiglietto` int(11) NOT NULL,
  `email` varchar(30) NOT NULL,
  `IC` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Struttura della tabella `brani`
--

CREATE TABLE `brani` (
  `idBrani` int(11) NOT NULL,
  `NomeBrano` varchar(100) NOT NULL,
  `Autore` varchar(30) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dump dei dati per la tabella `brani`
--

INSERT INTO `brani` (`idBrani`, `NomeBrano`, `Autore`) VALUES
(1, 'Exuvia', 'Caparezza'),
(2, 'La scelta', 'Caparezza'),
(3, 'Goodbye Malinconia', 'Caparezza'),
(4, 'One More Time', 'Daft Punk'),
(5, 'Giorgio by Moroder', 'Daft Punk'),
(6, 'Aerodynamic', 'Daft Punk'),
(7, 'Occidentali\'s Karma', 'Francesco Gabbani'),
(8, 'Viceversa', 'Francesco Gabbani'),
(9, 'Tra le granite e le granate', 'Francesco Gabbani'),
(10, 'Tikibombom', 'Levante'),
(11, 'Lo stretto necessario', 'Levante'),
(12, 'Pezzo di me', 'Levante'),
(13, 'Blinding Lights', 'The Weeknd'),
(14, 'Save your tears', 'The Weeknd'),
(15, 'After Hours', 'The Weeknd');

-- --------------------------------------------------------

--
-- Struttura della tabella `commenti`
--

CREATE TABLE `commenti` (
  `idCommento` int(11) NOT NULL,
  `Commento` varchar(500) NOT NULL,
  `DataCommento` date NOT NULL,
  `Voto` decimal(2,1) NOT NULL,
  `Art` varchar(30) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Struttura della tabella `concerto`
--

CREATE TABLE `concerto` (
  `idConcerto` int(11) NOT NULL,
  `NomeConcerto` varchar(250) NOT NULL,
  `Descrizione` varchar(500) NOT NULL,
  `Prezzo` double(4,2) NOT NULL,
  `Posti` int(11) NOT NULL,
  `DataConcerto` date NOT NULL,
  `iL` int(11) NOT NULL,
  `Artista` varchar(30) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dump dei dati per la tabella `concerto`
--

INSERT INTO `concerto` (`idConcerto`, `NomeConcerto`, `Descrizione`, `Prezzo`, `Posti`, `DataConcerto`, `iL`, `Artista`) VALUES
(1, 'Exuvia', 'Caparezza torna nei principali teatri italiani con l\'Exuvia Tour!', 22.00, 400, '2022-02-19', 1, 'Caparezza'),
(2, 'Exuvia', 'Caparezza arriva per la prima volta al teatro di Taormina con l\'Exuvia Tour!', 18.00, 1500, '2022-03-29', 2, 'Caparezza'),
(3, 'The Weeknd: The After Hours Tour\r\n', 'Annunciata la prima data del The After Hours World Tour!', 32.00, 2000, '2022-10-31', 7, 'The Weeknd'),
(4, 'The Weeknd: The After Hours Tour\r\n', 'Annunciata la seconda data del The After Hours World Tour!', 34.00, 5000, '2022-11-10', 5, 'The Weeknd'),
(5, 'Occidentali\'s Karma', 'La prima data del concerto della stella di Sanremo 2017!', 25.00, 1500, '2022-07-12', 2, 'Francesco Gabbani'),
(6, 'Occidentali\'s Karma', 'La seconda data del concerto della stella di Sanremo 2017!', 23.00, 500, '2022-08-24', 9, 'Francesco Gabbani'),
(7, 'HomeWork', 'La penultima data dell\'ultimo concerto ufficiale del gruppo di musica elettronica più famoso al mondo!', 32.00, 0, '2022-05-25', 4, 'Daft Punk'),
(8, 'HomeWork', 'L\'ultima data dell\'ultimo concerto ufficiale del gruppo di musica elettronica più famoso al mondo!', 35.00, 8000, '2022-06-30', 10, 'Daft Punk'),
(9, 'MAGMAMEMORIA', 'MAGMAMEMORIA MMXX TOUR', 30.00, 500, '2022-05-18', 3, 'Levante'),
(10, 'MAGMAMEMORIA', 'Il tour di Levante arriva a Taormina!', 22.00, 1500, '2022-11-24', 2, 'Levante');

-- --------------------------------------------------------

--
-- Struttura della tabella `localita`
--

CREATE TABLE `localita` (
  `idLocalita` int(11) NOT NULL,
  `NomeLocalita` varchar(250) NOT NULL,
  `CAP` varchar(10) NOT NULL,
  `S` varchar(2) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dump dei dati per la tabella `localita`
--

INSERT INTO `localita` (`idLocalita`, `NomeLocalita`, `CAP`, `S`) VALUES
(1, 'Teatro Verde - Venezia', '30133', 'IT'),
(2, 'Teatro Antico di Taormina', '98039', 'IT'),
(3, 'Palazzo dei Festival e dei Congressi di Cannes', '06400 ', 'FR'),
(4, 'Palazzo dello Sport di Parigi-Bercy', '75012', 'FR'),
(5, 'Finsbury Park - Londra', 'N4', 'GB'),
(6, 'Hammersmith Apollo - Londra', 'W6', 'GB'),
(7, 'Boulevard Pool - Nevada', '89109', 'US'),
(8, 'Fox Theater - Oakland', '94612', 'US'),
(9, 'Villa Medici - Roma', '00187', 'IT'),
(10, 'Velodrom - Berlino', '10407', 'DE');

-- --------------------------------------------------------

--
-- Struttura della tabella `nazioni`
--

CREATE TABLE `nazioni` (
  `Sigla` varchar(2) NOT NULL,
  `NomeNazione` varchar(15) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dump dei dati per la tabella `nazioni`
--

INSERT INTO `nazioni` (`Sigla`, `NomeNazione`) VALUES
('DE', 'Germania'),
('FR', 'Francia'),
('GB', 'Gran Bretagna'),
('IT', 'Italia'),
('US', 'Stati Uniti');

-- --------------------------------------------------------

--
-- Struttura della tabella `utenti`
--

CREATE TABLE `utenti` (
  `username` varchar(30) NOT NULL,
  `email` varchar(30) NOT NULL,
  `password` varchar(256) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dump dei dati per la tabella `utenti`
--

INSERT INTO `utenti` (`username`, `email`, `password`) VALUES
('giovanni', 'giovanni@gmail.com', '4E3GQFFU4b5Ih+s06KpiN2YaFal14sE6XnjgVtFhCZ8=');

--
-- Indici per le tabelle scaricate
--

--
-- Indici per le tabelle `artisti`
--
ALTER TABLE `artisti`
  ADD PRIMARY KEY (`NomeArtista`);

--
-- Indici per le tabelle `biglietti`
--
ALTER TABLE `biglietti`
  ADD PRIMARY KEY (`idBiglietto`),
  ADD KEY `fk_Biglietti_Concerto1_idx` (`IC`),
  ADD KEY `fk_Biglietti_Utenti1_idx` (`email`);

--
-- Indici per le tabelle `brani`
--
ALTER TABLE `brani`
  ADD PRIMARY KEY (`idBrani`),
  ADD KEY `fk_Brani_Artisti1_idx` (`Autore`);

--
-- Indici per le tabelle `commenti`
--
ALTER TABLE `commenti`
  ADD PRIMARY KEY (`idCommento`),
  ADD KEY `fk_Commenti_Artisti1_idx` (`Art`);

--
-- Indici per le tabelle `concerto`
--
ALTER TABLE `concerto`
  ADD PRIMARY KEY (`idConcerto`),
  ADD KEY `fk_Concerto_Artisti1_idx` (`Artista`),
  ADD KEY `fk_Concerto_Localita1_idx` (`iL`);

--
-- Indici per le tabelle `localita`
--
ALTER TABLE `localita`
  ADD PRIMARY KEY (`idLocalita`),
  ADD KEY `fk_Localita_Nazioni1_idx` (`S`) USING BTREE;

--
-- Indici per le tabelle `nazioni`
--
ALTER TABLE `nazioni`
  ADD PRIMARY KEY (`Sigla`);

--
-- Indici per le tabelle `utenti`
--
ALTER TABLE `utenti`
  ADD PRIMARY KEY (`email`) USING BTREE;

--
-- AUTO_INCREMENT per le tabelle scaricate
--

--
-- AUTO_INCREMENT per la tabella `biglietti`
--
ALTER TABLE `biglietti`
  MODIFY `idBiglietto` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT per la tabella `brani`
--
ALTER TABLE `brani`
  MODIFY `idBrani` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=16;

--
-- AUTO_INCREMENT per la tabella `commenti`
--
ALTER TABLE `commenti`
  MODIFY `idCommento` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT per la tabella `concerto`
--
ALTER TABLE `concerto`
  MODIFY `idConcerto` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;

--
-- AUTO_INCREMENT per la tabella `localita`
--
ALTER TABLE `localita`
  MODIFY `idLocalita` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;

--
-- Limiti per le tabelle scaricate
--

--
-- Limiti per la tabella `biglietti`
--
ALTER TABLE `biglietti`
  ADD CONSTRAINT `fk_Biglietti_Concerto1_idx` FOREIGN KEY (`IC`) REFERENCES `concerto` (`idConcerto`),
  ADD CONSTRAINT `fk_Biglietti_Utenti1_idx` FOREIGN KEY (`email`) REFERENCES `utenti` (`email`);

--
-- Limiti per la tabella `brani`
--
ALTER TABLE `brani`
  ADD CONSTRAINT `fk_Brani_Artisti1_idx` FOREIGN KEY (`Autore`) REFERENCES `artisti` (`NomeArtista`);

--
-- Limiti per la tabella `commenti`
--
ALTER TABLE `commenti`
  ADD CONSTRAINT `fk_Commenti_Artisti1_idx` FOREIGN KEY (`Art`) REFERENCES `artisti` (`NomeArtista`);

--
-- Limiti per la tabella `concerto`
--
ALTER TABLE `concerto`
  ADD CONSTRAINT `fk_Concerto_Artisti1_idx` FOREIGN KEY (`Artista`) REFERENCES `artisti` (`NomeArtista`),
  ADD CONSTRAINT `fk_Concerto_Localita1_idx` FOREIGN KEY (`iL`) REFERENCES `localita` (`idLocalita`);

--
-- Limiti per la tabella `localita`
--
ALTER TABLE `localita`
  ADD CONSTRAINT `fk_Localita_Nazioni1_idx` FOREIGN KEY (`S`) REFERENCES `nazioni` (`Sigla`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
