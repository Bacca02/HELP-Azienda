<?php
require_once 'config.php';
$tipo = "";
if ($_SERVER["REQUEST_METHOD"] == "GET") {
    if (empty(trim($_GET["tipo"]))) {
        $age = array("Esito" => "N", "Motivo" => "Parametri mancanti");
        header('Content-Type: application/json; charset=utf-8');
        echo json_encode($age);
        die();
    } else {
        $tipo = trim($_GET["tipo"]);
    }


    if ($tipo == "R") { //RICHIESTE

        $sql = "SELECT * FROM `richieste`;";

        if ($result = mysqli_query($link, $sql)) {

            if (mysqli_num_rows($result) > 0) {

                $age = [];
                array_push($age, (object)["Esito" => "V"]);
                while ($row = mysqli_fetch_array($result)) {

                    array_push($age, (object)["iD" => $row['iD'], "Mittente" => $row['Mittente'], "Destinatario" => $row['Destinatario'], "Testo" => $row['Testo'], "Attiva" => $row['Attiva']]);
                }
                header('Content-Type: application/json; charset=utf-8');
                echo json_encode($age);
                mysqli_free_result($result);
            } else {
                $age = [];
                array_push($age, (object)["Esito" => "N", "Motivo" => "Nessun risultato"]);
                header('Content-Type: application/json; charset=utf-8');
                echo json_encode($age);
            }
        }
    } else if ($tipo == "M") {
        $sql = "SELECT * FROM `materiale`;";

        if ($result = mysqli_query($link, $sql)) {

            if (mysqli_num_rows($result) > 0) {

                $age = [];
                array_push($age, (object)["Esito" => "V"]);
                while ($row = mysqli_fetch_array($result)) {

                    array_push($age, (object)["iD" => $row['iD'], "Materiale" => $row['Materiale'], "Marca" => $row['Marca'], "Posizione" => $row['Posizione'], "Path" => $row['Path'], "quantita" => $row['quantita']]);
                }
                header('Content-Type: application/json; charset=utf-8');
                echo json_encode($age);
                mysqli_free_result($result);
            } else {
                $age = [];
                array_push($age, (object)["Esito" => "N", "Motivo" => "Nessun risultato"]);
                header('Content-Type: application/json; charset=utf-8');
                echo json_encode($age);
            }
        }
    } else if ($tipo == "U") {
        $sql = "SELECT * FROM `utenti`;";

        if ($result = mysqli_query($link, $sql)) {

            if (mysqli_num_rows($result) > 0) {

                $age = [];
                array_push($age, (object)["Esito" => "V"]);
                while ($row = mysqli_fetch_array($result)) {

                    array_push($age, (object)["iD" => $row['iD'], "Nome" => $row['Nome'], "Cognome" => $row['Cognome'], "e-mail" => $row['e-mail'], "nome_utente" => $row['nome_utente'], "Tipo" => $row['Tipo'], "Telefono" => $row['Telefono']]);
                }
                header('Content-Type: application/json; charset=utf-8');
                echo json_encode($age);
                mysqli_free_result($result);
            } else {
                $age = [];
                array_push($age, (object)["Esito" => "N", "Motivo" => "Nessun risultato"]);
                header('Content-Type: application/json; charset=utf-8');
                echo json_encode($age);
            }
        }
    } else if ($tipo == "O") {
        $sql = "SELECT * FROM `ordinazione`;";

        if ($result = mysqli_query($link, $sql)) {

            if (mysqli_num_rows($result) > 0) {

                $age = [];
                array_push($age, (object)["Esito" => "V"]);
                while ($row = mysqli_fetch_array($result)) {

                    array_push($age, (object)["idOrdine" => $row['idOrdine'], "idFornitore" => $row['idFornitore'], "idMateriale" => $row['idMateriale'], "Quanita" => $row['Quanita'], "DatOrdine" => $row['DatOrdine']]);
                }
                header('Content-Type: application/json; charset=utf-8');
                echo json_encode($age);
                mysqli_free_result($result);
            } else {
                $age = [];
                array_push($age, (object)["Esito" => "N", "Motivo" => "Nessun risultato"]);
                header('Content-Type: application/json; charset=utf-8');
                echo json_encode($age);
            }
        }
    } else if ($tipo == "F") {
        $sql = "SELECT * FROM `fornitore`;";

        if ($result = mysqli_query($link, $sql)) {

            if (mysqli_num_rows($result) > 0) {

                $age = [];
                array_push($age, (object)["Esito" => "V"]);
                while ($row = mysqli_fetch_array($result)) {

                    array_push($age, (object)["iD" => $row['iD'], "Nome" => $row['Nome'], "Telefono" => $row['Telefono'], "Indirizzo" => $row['Indirizzo']]);
                }
                header('Content-Type: application/json; charset=utf-8');
                echo json_encode($age);
                mysqli_free_result($result);
            } else {
                $age = [];
                array_push($age, (object)["Esito" => "N", "Motivo" => "Nessun risultato"]);
                header('Content-Type: application/json; charset=utf-8');
                echo json_encode($age);
            }
        }
    }
}
