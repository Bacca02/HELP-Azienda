<?php
require_once "config.php";

$tipoI = "";




if ($_SERVER["REQUEST_METHOD"] == "POST") {
    if (empty(trim($_POST["TipoI"]))) {
        $age = array("Esito" => "N", "Motivo" => "Parametri mancanti");
        header('Content-Type: application/json; charset=utf-8');
        echo json_encode($age);
        die();
    } else {
        $tipoI = trim($_POST["TipoI"]);
    }





    //----------------------------------------UTENTE---------------------------------------------- (BONDESAN-SOGGIU)
    if ($tipoI == "U") {
        $nome = $cognome = $email = $nome_utente = $password = $tipo = $telefono = "";
        $nome_err = $cognome_err = $email_err = $nome_utente_err = $password_err = $tipo_err = $telefono_err = "";

        $input_name = trim($_POST["Nome"]);
        if (empty($input_name)) {
            $nome_err = "inserire un nome.";
        } else {
            $nome = $input_name;
        }
        $input_cognome = trim($_POST["Cognome"]);
        if (empty($input_cognome)) {
            $cognome_err = "inserire un cognome.";
        } else {
            $cognome = $input_cognome;
        }

        $input_email = trim($_POST["Email"]);
        if (empty($input_email)) {
            $email_err = "inserire una e-mail.";
        } else {
            $email = $input_email;
        }

        $input_nome_utente = trim($_POST["nomeUtente"]);
        if (empty($input_nome_utente)) {
            $nome_utente_err = "inserire un nome utente.";
        } else {
            $nome_utente = $input_nome_utente;
        }

        $input_password = trim($_POST["Password"]);
        if (empty($input_password)) {
            $password_err = "inserire una password.";
        } else {
            $password = $input_password;
        }

        $input_tipo = trim($_POST["Tipo"]);
        if (empty($input_tipo)) {
            $tipo_err = "inserire un tipo di account.";
        } else {
            $tipo = $input_tipo;
        }

        $input_telefono = trim($_POST["Telefono"]);
        if (empty($input_telefono)) {
            $telefono_err = "inserire un numero di telefono.";
        } else {
            $telefono = $input_telefono;
        }

        if (!empty($nome) && !empty($cognome) && !empty($email) && !empty($nome_utente) && !empty($password) && !empty($tipo) && !empty($telefono)) {
            $sql = "INSERT INTO `utenti` (`iD`, `Nome`, `Cognome`, `e-mail`, `nome_utente`, `Password`, `Tipo`, `Telefono`) VALUES (NULL,?,?,?,?,?,?,?)";

            if ($stmt = mysqli_prepare($link, $sql)) {
                mysqli_stmt_bind_param($stmt, "sssssss", $param_nome, $param_cognome, $param_email, $param_nome_utente, $param_password, $param_tipo, $param_telefono);
                $param_nome = $nome;
                $param_cognome = $cognome;
                $param_email = $email;
                $param_nome_utente = $nome_utente;
                $param_password = $password;
                $param_tipo = $tipo;
                $param_telefono = $telefono;
                if (mysqli_stmt_execute($stmt)) {
                    $esito = array("Esito" => "V");
                    header('Content-Type: application/json; charset=utf-8');
                    echo json_encode($esito);
                } else {
                    $esito = array("Esito" => false, "Motivo" => "Impossibile eseguire la ricerca, errore interno1U");
                    header('Content-Type: application/json; charset=utf-8');
                    echo json_encode($esito);
                }
            } else {
                $esito = array("Esito" => false, "Motivo" => "Impossibile eseguire la ricerca, errore interno2U");
                header('Content-Type: application/json; charset=utf-8');
                echo json_encode($esito);
            }
        } else {
            //PARAMETRI VUOTI
            $esito = array("Esito" => false, "Motivo" => "Parametri mancanti");
            header('Content-Type: application/json; charset=utf-8');
            echo json_encode($esito);
        }







        //---------------------------------------------MATERIALE--------------------------------------(SOGGIU)
    } else if ($tipoI == "M") {
        $mat = $marca = $pos = $path = $qnt = "";

        if (empty(trim($_POST["Materiale"]))) {
            $esito = array("Esito" => false, "Motivo" => "Parametri mancanti");
            header('Content-Type: application/json; charset=utf-8');
            echo json_encode($esito);
            die();
        } else {
            $mat = trim($_POST["Materiale"]);
        }

        if (empty(trim($_POST["Marca"]))) {
            $esito = array("Esito" => false, "Motivo" => "Parametri mancanti");
            header('Content-Type: application/json; charset=utf-8');
            echo json_encode($esito);
            die();
        } else {
            $marca = trim($_POST["Marca"]);
        }

        if (empty(trim($_POST["Posizione"]))) {
            $esito = array("Esito" => false, "Motivo" => "Parametri mancanti");
            header('Content-Type: application/json; charset=utf-8');
            echo json_encode($esito);
            die();
        } else {
            $pos = trim($_POST["Posizione"]);
        }

        if (empty(trim($_POST["Path"]))) {
            $esito = array("Esito" => false, "Motivo" => "Parametri mancanti");
            header('Content-Type: application/json; charset=utf-8');
            echo json_encode($esito);
            die();
        } else {
            $path = trim($_POST["Path"]);
        }

        if (empty(trim($_POST["quantita"]))) {
            $esito = array("Esito" => false, "Motivo" => "Parametri mancanti");
            header('Content-Type: application/json; charset=utf-8');
            echo json_encode($esito);
            die();
        } else {
            $qnt = trim($_POST["quantita"]);
        }



        $sql = "INSERT INTO `materiale`(`iD`, `Materiale`, `Marca`, `Posizione`, `Path`, `quantita`) VALUES (NULL,'" . $mat . "','" . $marca . "','" . $pos . "','" . $path . "','" . $qnt . "');";



        if ($link->query($sql) === TRUE) {

            $esito = array("Esito" => "V");
            header('Content-Type: application/json; charset=utf-8');
            echo json_encode($esito);
        } else {
            $esito = array("Esito" => false, "Motivo" => "Errore: " . $link->error . ". Impossibile eseguire la ricerca, errore interno1M");
            header('Content-Type: application/json; charset=utf-8');
            echo json_encode($esito);
        }














        //----------------------------------------MODIFICAQUANTITÀMATERIALE----------------------------------------------(SOGGIU)
    } else if ($tipoI == "MM") {
        $iD = $qnt = "";

        if (empty(trim($_POST["iD"]))) {
            $esito = array("Esito" => false, "Motivo" => "Parametri mancanti");
            header('Content-Type: application/json; charset=utf-8');
            echo json_encode($esito);
            die();
        } else {
            $iD = trim($_POST["iD"]);
        }

        if (empty(trim($_POST["qnt"]))) {
            $esito = array("Esito" => false, "Motivo" => "Parametri mancanti");
            header('Content-Type: application/json; charset=utf-8');
            echo json_encode($esito);
            die();
        } else {
            $qnt = trim($_POST["qnt"]);
        }

        $sql = "UPDATE `materiale` SET `quantita`=`quantita`+ " . $qnt . " WHERE `iD`=" . $iD;


        if ($link->query($sql) === TRUE) {

            $esito = array("Esito" => "V");
            header('Content-Type: application/json; charset=utf-8');
            echo json_encode($esito);
        } else {
            $esito = array("Esito" => false, "Motivo" => "Impossibile eseguire la ricerca, errore interno1MM");
            header('Content-Type: application/json; charset=utf-8');
            echo json_encode($esito);
        }












        //---------------------------------------------RICHIESTE--------------------------------------(SOGGIU)
    } else if ($tipoI == "R") {
        $mit = $des = $tes = "";

        if (empty(trim($_POST["Mittente"]))) {
            $esito = array("Esito" => false, "Motivo" => "Parametri mancanti");
            header('Content-Type: application/json; charset=utf-8');
            echo json_encode($esito);
            die();
        } else {
            $mit = trim($_POST["Mittente"]);
        }

        if (empty(trim($_POST["Destinatario"]))) {
            $esito = array("Esito" => false, "Motivo" => "Parametri mancanti");
            header('Content-Type: application/json; charset=utf-8');
            echo json_encode($esito);
            die();
        } else {
            $des = trim($_POST["Destinatario"]);
        }

        if (empty(trim($_POST["Testo"]))) {
            $esito = array("Esito" => false, "Motivo" => "Parametri mancanti");
            header('Content-Type: application/json; charset=utf-8');
            echo json_encode($esito);
            die();
        } else {
            $tes = trim($_POST["Testo"]);
        }



        $sql = "INSERT INTO `richieste`(`iD`, `Mittente`, `Destinatario`, `Testo`, `Attiva`) VALUES (NULL,?,?,?,?)";


        if ($stmt = mysqli_prepare($link, $sql)) {
            mysqli_stmt_bind_param($stmt, "sss", $param_mit, $param_des, $param_tes);
            $param_mit = $mit;
            $param_des = $des;
            $param_tes = $tes;
            if (mysqli_stmt_execute($stmt)) {
                $esito = array("Esito" => "V");
                header('Content-Type: application/json; charset=utf-8');
                echo json_encode($esito);
            } else {
                $esito = array("Esito" => false, "Motivo" => "Impossibile eseguire la ricerca, errore interno1R");
                header('Content-Type: application/json; charset=utf-8');
                echo json_encode($esito);
            }
        } else {
            $esito = array("Esito" => false, "Motivo" => "Impossibile eseguire la ricerca, errore interno2R");
            header('Content-Type: application/json; charset=utf-8');
            echo json_encode($esito);
        }










        //-------------------------------------------ATTIVA/DISATTIVARICHIESTA---------------------------------------(SOGGIU)
    } else if ($tipoI == "AR") {

        $iD = $att = -1;





        if (empty(trim($_POST["iD"]))) {
            $esito = array("Esito" => false, "Motivo" => "Parametri mancanti");
            header('Content-Type: application/json; charset=utf-8');
            echo json_encode($esito);
            die();
        } else {
            $iD = trim($_POST["iD"]);
        }



        if (empty(trim($_POST["att"]))) {
            $esito = array("Esito" => false, "Motivo" => "Parametri mancanti");
            header('Content-Type: application/json; charset=utf-8');
            echo json_encode($esito);
            die();
        } else {
            $att = trim($_POST["att"]);
        }

        $sql = "UPDATE `richieste` SET `Attiva`= " . $att . " WHERE `iD`=" . $iD;
        if ($link->query($sql) === TRUE) {

            $esito = array("Esito" => "V");
            header('Content-Type: application/json; charset=utf-8');
            echo json_encode($esito);
        } else {
            $esito = array("Esito" => false, "Motivo" => "Impossibile eseguire la ricerca, errore interno1AR");
            header('Content-Type: application/json; charset=utf-8');
            echo json_encode($esito);
        }







        //-------------------------------------------RIMUOVIRICHIESTA-------------------------------------(SOGGIU)
    } else if ($tipoI == "RR") {

        $iD = -1;





        if (empty(trim($_POST["iD"]))) {
            $esito = array("Esito" => false, "Motivo" => "Parametri mancanti");
            header('Content-Type: application/json; charset=utf-8');
            echo json_encode($esito);
            die();
        } else {
            $iD = trim($_POST["iD"]);
        }
        $sql = "DELETE FROM `richieste` WHERE `iD` = " . $iD;

        if ($link->query($sql) === TRUE) {

            $esito = array("Esito" => "V");
            header('Content-Type: application/json; charset=utf-8');
            echo json_encode($esito);
        } else {
            $esito = array("Esito" => false, "Motivo" => "Impossibile eseguire la ricerca, errore interno1RR");
            header('Content-Type: application/json; charset=utf-8');
            echo json_encode($esito);
        }


        //-----------------------------------------ORDINAZIONE---------------------------------------------(SOGGIU)
    } else if ($tipoI == "O") {
        $idF = $idM = $qnt = $data = "";

        if (empty(trim($_POST["idFornitore"]))) {
            $esito = array("Esito" => false, "Motivo" => "Parametri mancanti");
            header('Content-Type: application/json; charset=utf-8');
            echo json_encode($esito);
            die();
        } else {
            $idF = trim($_POST["idFornitore"]);
        }

        if (empty(trim($_POST["idMateriale"]))) {
            $esito = array("Esito" => false, "Motivo" => "Parametri mancanti");
            header('Content-Type: application/json; charset=utf-8');
            echo json_encode($esito);
            die();
        } else {
            $idM = trim($_POST["idMateriale"]);
        }

        if (empty(trim($_POST["Quantita"]))) {
            $esito = array("Esito" => false, "Motivo" => "Parametri mancanti");
            header('Content-Type: application/json; charset=utf-8');
            echo json_encode($esito);
            die();
        } else {
            $qnt = trim($_POST["Quantita"]);
        }

        if (empty(trim($_POST["Data"]))) {
            $esito = array("Esito" => false, "Motivo" => "Parametri mancanti");
            header('Content-Type: application/json; charset=utf-8');
            echo json_encode($esito);
            die();
        } else {
            $data = trim($_POST["Data"]);
        }




        $sql = "INSERT INTO `ordinazione` (`idOrdine`, `idFornitore`, `idMateriale`, `Quantita`, `DatOrdine`) VALUES (NULL,'" . $idF . "','" . $idM . "','" . $qnt . "','" . $data . "');";



        if ($link->query($sql) === TRUE) {

            $esito = array("Esito" => "V");
            header('Content-Type: application/json; charset=utf-8');
            echo json_encode($esito);
        } else {
            $esito = array("Esito" => false, "Motivo" => "Errore: " . $link->error . ". Impossibile eseguire la ricerca, errore internoO");
            header('Content-Type: application/json; charset=utf-8');
            echo json_encode($esito);
        }
    } else if ($tipoI == "RO") {
        $iD = -1;





        if (empty(trim($_POST["iD"]))) {
            $esito = array("Esito" => false, "Motivo" => "Parametri mancanti");
            header('Content-Type: application/json; charset=utf-8');
            echo json_encode($esito);
            die();
        } else {
            $iD = trim($_POST["iD"]);
        }
        $sql = "DELETE FROM `ordinazione` WHERE `idOrdine` = " . $iD;

        if ($link->query($sql) === TRUE) {

            $esito = array("Esito" => "V");
            header('Content-Type: application/json; charset=utf-8');
            echo json_encode($esito);
        } else {
            $esito = array("Esito" => false, "Motivo" => "Impossibile eseguire la ricerca, errore interno1RR");
            header('Content-Type: application/json; charset=utf-8');
            echo json_encode($esito);
        }

        //---------------------------------------------------RIMUOVIUTENTE-----------------------------------------------
    } else if ($tipoI == "RU") {
        $iD = -1;
        $iDCh = -1;





        if (empty(trim($_POST["iD"]))) {
            $esito = array("Esito" => false, "Motivo" => "Parametri mancanti");
            header('Content-Type: application/json; charset=utf-8');
            echo json_encode($esito);
            die();
        } else {
            $iD = trim($_POST["iD"]);
        }

        



            
        

        $sql = "DELETE FROM `utenti` WHERE `iD` = " . $iD . ";";
        if ($link->query($sql) === TRUE) {

            $esito = array("Esito" => "V");
            header('Content-Type: application/json; charset=utf-8');
            echo json_encode($esito);
        } else {
            $esito = array("Esito" => false, "Motivo" => "Impossibile eseguire la ricerca, errore interno1RU");
            header('Content-Type: application/json; charset=utf-8');
            echo json_encode($esito);
        }

        //----------------------------------RIMUOVI MATERIALE--------------------------------------------
    } else if ($tipoI == "RM") {
        $iD = -1;





        if (empty(trim($_POST["iD"]))) {
            $esito = array("Esito" => false, "Motivo" => "Parametri mancanti");
            header('Content-Type: application/json; charset=utf-8');
            echo json_encode($esito);
            die();
        } else {
            $iD = trim($_POST["iD"]);
        }

        $sql = "DELETE FROM `materiale` WHERE `iD` = " . $iD;
        if ($link->query($sql) === TRUE) {

            $esito = array("Esito" => "V");
            header('Content-Type: application/json; charset=utf-8');
            echo json_encode($esito);
        } else {
            $esito = array("Esito" => false, "Motivo" => "Impossibile eseguire la ricerca, errore interno1RR");
            header('Content-Type: application/json; charset=utf-8');
            echo json_encode($esito);
        }

        //----------------------------------RESETTA PASSWORD UTENTE--------------------------------------------
    } else if ($tipoI == "RPU") {

        $iD = -1;

        if (empty(trim($_POST["iD"]))) {
            $esito = array("Esito" => false, "Motivo" => "Parametri mancanti");
            header('Content-Type: application/json; charset=utf-8');
            echo json_encode($esito);
            die();
        } else {
            $iD = trim($_POST["iD"]);
        }

        $sql = "UPDATE `utenti` SET `Password`='" . md5("Password") . "' WHERE `iD`= " . $iD;

        if ($link->query($sql) === TRUE) {

            $esito = array("Esito" => "V");
            header('Content-Type: application/json; charset=utf-8');
            echo json_encode($esito);
        } else {
            $esito = array("Esito" => false, "Motivo" => "Impossibile eseguire la ricerca, errore interno1AR");
            header('Content-Type: application/json; charset=utf-8');
            echo json_encode($esito);
        }

        //------------------------------------------------MODIFICA PASSWORD UTENTE
    } else if ($tipoI == "MPU") {

        $iD = -1;
        $password="";

        if (empty(trim($_POST["iD"]))) {
            $esito = array("Esito" => false, "Motivo" => "Parametri mancanti");
            header('Content-Type: application/json; charset=utf-8');
            echo json_encode($esito);
            die();
        } else {
            $iD = trim($_POST["iD"]);
        }
        if (empty(trim($_POST["Pass"]))) {
            $esito = array("Esito" => false, "Motivo" => "Parametri mancanti");
            header('Content-Type: application/json; charset=utf-8');
            echo json_encode($esito);
            die();
        } else {
            $password = trim($_POST["Pass"]);
        }

        $sql = "UPDATE `utenti` SET `Password`='" . $password . "' WHERE `iD`= " . $iD;

        if ($link->query($sql) === TRUE) {

            $esito = array("Esito" => "V");
            header('Content-Type: application/json; charset=utf-8');
            echo json_encode($esito);
        } else {
            $esito = array("Esito" => false, "Motivo" => "Impossibile eseguire la ricerca, errore interno1MPU");
            header('Content-Type: application/json; charset=utf-8');
            echo json_encode($esito);
        }
        //---------------------------------------------MODIFICA NOME UTENTE----------------------------------------------
    }else if ($tipoI == "MNU") {

        $iD = -1;
        $nome="";

        if (empty(trim($_POST["iD"]))) {
            $esito = array("Esito" => false, "Motivo" => "Parametri mancanti");
            header('Content-Type: application/json; charset=utf-8');
            echo json_encode($esito);
            die();
        } else {
            $iD = trim($_POST["iD"]);
        }
        if (empty(trim($_POST["Nome"]))) {
            $esito = array("Esito" => false, "Motivo" => "Parametri mancanti");
            header('Content-Type: application/json; charset=utf-8');
            echo json_encode($esito);
            die();
        } else {
            $nome = trim($_POST["Nome"]);
        }

        $sql = "UPDATE `utenti` SET `Nome`='" . $nome . "' WHERE `iD`= " . $iD;

        if ($link->query($sql) === TRUE) {

            $esito = array("Esito" => "V");
            header('Content-Type: application/json; charset=utf-8');
            echo json_encode($esito);
        } else {
            $esito = array("Esito" => false, "Motivo" => "Impossibile eseguire la ricerca, errore interno1MNU");
            header('Content-Type: application/json; charset=utf-8');
            echo json_encode($esito);
        }
    }else {

        $esito = array("Esito" => false, "Motivo" => "Tipo irriconoscibile, inserito: " . $tipoI);
        header('Content-Type: application/json; charset=utf-8');
        echo json_encode($esito);
    }
}
