-- phpMyAdmin SQL Dump
-- version 4.9.0.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Creato il: Mar 18, 2022 alle 10:04
-- Versione del server: 10.4.6-MariaDB
-- Versione PHP: 7.3.8

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `hpa-utenti`
--

-- --------------------------------------------------------

--
-- Struttura della tabella `fornitore`
--

CREATE TABLE `fornitore` (
  `iD` int(11) NOT NULL,
  `Nome` varchar(30) NOT NULL,
  `Telefono` varchar(10) NOT NULL,
  `Indirizzo` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Struttura della tabella `materiale`
--

CREATE TABLE `materiale` (
  `iD` int(11) NOT NULL,
  `Materiale` varchar(30) NOT NULL,
  `Marca` varchar(40) NOT NULL,
  `Posizione` varchar(110) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Struttura della tabella `ordinazione`
--

CREATE TABLE `ordinazione` (
  `idOrdine` int(11) NOT NULL,
  `idFornitore` int(11) NOT NULL,
  `idMateriale` int(11) NOT NULL,
  `Quantita` int(11) NOT NULL,
  `DatOrdine` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Struttura della tabella `prelevamento`
--

CREATE TABLE `prelevamento` (
  `idMateriale` int(11) NOT NULL,
  `idUtente` int(11) NOT NULL,
  `Quantita` int(11) NOT NULL,
  `DataPrelievo` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Struttura della tabella `rifornimento`
--

CREATE TABLE `rifornimento` (
  `idMateriale` int(11) NOT NULL,
  `Quantità` int(11) NOT NULL,
  `idFornitore` int(11) NOT NULL,
  `DataRifornimento` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Struttura della tabella `utenti`
--

CREATE TABLE `utenti` (
  `iD` int(11) NOT NULL,
  `Nome` varchar(30) NOT NULL,
  `Cognome` varchar(30) NOT NULL,
  `e-mail` varchar(30) NOT NULL,
  `nome_utente` varchar(30) NOT NULL,
  `Password` varchar(16) NOT NULL,
  `Tipo` varchar(15) NOT NULL,
  `Telefono` varchar(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dump dei dati per la tabella `utenti`
--

INSERT INTO `utenti` (`iD`, `Nome`, `Cognome`, `e-mail`, `nome_utente`, `Password`, `Tipo`, `Telefono`) VALUES
(1, 'Marco', 'Soggiu', 'marco.soggiu@miodiominio.it', 'MSoggiu', '1234', 'admin', '0362522211'),
(4, 'Christian', 'Baccaglini', 'bacca1302@gmail.com', 'bacca', '12345678', 'admin', '33332333'),
(5, 'Christian', 'Baccaglini', 'bacca1302@gmail.com', 'bacca', '12345678', 'admin', '33332333'),
(6, 'Christian', 'Baccaglini', 'bacca1302@gmail.com', 'bacca', '12345678', 'admin', '33332333'),
(7, 'Christian', 'Baccaglini', 'bacca1302@gmail.com', 'bacca', '12345678', 'admin', '33332333');

--
-- Indici per le tabelle scaricate
--

--
-- Indici per le tabelle `fornitore`
--
ALTER TABLE `fornitore`
  ADD PRIMARY KEY (`iD`);

--
-- Indici per le tabelle `materiale`
--
ALTER TABLE `materiale`
  ADD PRIMARY KEY (`iD`);

--
-- Indici per le tabelle `ordinazione`
--
ALTER TABLE `ordinazione`
  ADD PRIMARY KEY (`idOrdine`);

--
-- Indici per le tabelle `prelevamento`
--
ALTER TABLE `prelevamento`
  ADD PRIMARY KEY (`idUtente`);

--
-- Indici per le tabelle `rifornimento`
--
ALTER TABLE `rifornimento`
  ADD PRIMARY KEY (`idFornitore`,`idMateriale`);

--
-- Indici per le tabelle `utenti`
--
ALTER TABLE `utenti`
  ADD PRIMARY KEY (`iD`);

--
-- AUTO_INCREMENT per le tabelle scaricate
--

--
-- AUTO_INCREMENT per la tabella `fornitore`
--
ALTER TABLE `fornitore`
  MODIFY `iD` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT per la tabella `materiale`
--
ALTER TABLE `materiale`
  MODIFY `iD` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT per la tabella `utenti`
--
ALTER TABLE `utenti`
  MODIFY `iD` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
