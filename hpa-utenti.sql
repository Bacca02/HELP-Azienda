-- phpMyAdmin SQL Dump
-- version 5.1.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Creato il: Apr 03, 2022 alle 14:13
-- Versione del server: 10.4.21-MariaDB
-- Versione PHP: 8.0.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
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
  `Posizione` varchar(110) NOT NULL,
  `Path` varchar(255) NOT NULL
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
-- Struttura della tabella `richieste`
--

CREATE TABLE `richieste` (
  `iD` int(11) NOT NULL,
  `Mittente` int(11) NOT NULL,
  `Destinatario` int(11) NOT NULL,
  `Testo` text NOT NULL,
  `Attiva` tinyint(1) NOT NULL DEFAULT 0
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

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
(2, 'Christian', 'Baccaglini', 'bacca02@gmail.com', 'bacca02', '1234', 'admin', '0310230331'),
(14, 'Luca', 'Bondesan', 'luca.bondesan@gmail.com', 'luca_bondesan', '1234', 'Utente', '0315466587');

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
-- Indici per le tabelle `richieste`
--
ALTER TABLE `richieste`
  ADD PRIMARY KEY (`iD`);

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
-- AUTO_INCREMENT per la tabella `richieste`
--
ALTER TABLE `richieste`
  MODIFY `iD` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT per la tabella `utenti`
--
ALTER TABLE `utenti`
  MODIFY `iD` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=15;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
