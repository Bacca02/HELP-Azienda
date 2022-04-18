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

    if ($tipo == "U") {
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
                    $esito = array("Esito" => true);
                    header('Content-Type: application/json; charset=utf-8');
                    echo json_encode($esito);
                } else {
                    $esito = array("Esito" => false, "Motivo" => "Impossibile eseguire la ricerca, errore interno");
                    header('Content-Type: application/json; charset=utf-8');
                    echo json_encode($esito);
                }
            } else {
                $esito = array("Esito" => false, "Motivo" => "Impossibile eseguire la ricerca, errore interno");
                header('Content-Type: application/json; charset=utf-8');
                echo json_encode($esito);
            }
        } else {
            //PARAMETRI VUOTI
            $esito = array("Esito" => false, "Motivo" => "Parametri mancanti");
            header('Content-Type: application/json; charset=utf-8');
            echo json_encode($esito);
        }







        //-------------------------------------------------MATERIALE-------------------------------------------
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



        $sql = "INSERT INTO `materiale`(`iD`, `Materiale`, `Marca`, `Posizione`, `Path`, `quantita`) VALUES (NULL,?,?,?,?,?)";


        if ($stmt = mysqli_prepare($link, $sql)) {
            mysqli_stmt_bind_param($stmt, "sssss", $param_mat, $param_marca, $param_pos, $param_path, $param_qnt);
            $param_mat = $mat;
            $param_marca = $marca;
            $param_pos = $pos;
            $param_path = $path;
            $param_qnt = $qnt;
            if (mysqli_stmt_execute($stmt)) {
                $esito = array("Esito" => true);
                header('Content-Type: application/json; charset=utf-8');
                echo json_encode($esito);
            } else {
                $esito = array("Esito" => false, "Motivo" => "Impossibile eseguire la ricerca, errore interno1M");
                header('Content-Type: application/json; charset=utf-8');
                echo json_encode($esito);
            }
        } else {
            $esito = array("Esito" => false, "Motivo" => "Impossibile eseguire la ricerca, errore interno2M");
            header('Content-Type: application/json; charset=utf-8');
            echo json_encode($esito);
        }
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

            $esito = array("Esito" => true);
            header('Content-Type: application/json; charset=utf-8');
            echo json_encode($esito);
        } else {
            $esito = array("Esito" => false, "Motivo" => "Impossibile eseguire la ricerca, errore interno1MM");
            header('Content-Type: application/json; charset=utf-8');
            echo json_encode($esito);
        }
    } else {
        //NO GET (?)
        $esito = array("Esito" => false, "Motivo" => "Tipo irriconoscibile, inserito: " . $tipoI);

        echo json_encode($esito);
    }
}
