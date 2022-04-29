<?php
require_once 'config.php';
$iD = "";
if ($_SERVER["REQUEST_METHOD"] == "GET") {
    $input_name = trim($_GET["iD"]);
    if (empty(trim($_GET["iD"]))) {
        $age = array("Esito" => "N", "Motivo" => "Parametri mancanti");
        header('Content-Type: application/json; charset=utf-8');
        echo json_encode($age);
        die();
    } else {
        $iD = trim($_GET["iD"]);
    }
    $sql = "SELECT * FROM `utenti` WHERE `iD`=" . $iD . ";";

    if ($result = mysqli_query($link, $sql)) {
        
        
        if (mysqli_num_rows($result) > 0) {

            $row = mysqli_fetch_array($result);
            if ($row['iD'] == $iD) {
                
                $age = array("Esito" => "V", "iD" => $row['iD'], "Nome" => $row['Nome'], "Cognome" => $row['Cognome'], "e-mail" => $row['e-mail'], "nome_utente" => $row['nome_utente'], "Tipo" => $row['Tipo'], "Telefono" => $row['Telefono']);
                header('Content-Type: application/json; charset=utf-8');
                print_r(json_encode($age));
                //echo ;
                exit();
            } else {

                $age = array("Esito" => "F", "iD" => -1);
                header('Content-Type: application/json; charset=utf-8');
                echo json_encode($age);
            }
        } else {
            $age = array("Esito" => "N", "Motivo" => "Utente insesitente");
            header('Content-Type: application/json; charset=utf-8');
            echo json_encode($age);
        }
    }

    mysqli_free_result($result);
    mysqli_close($link);
}
