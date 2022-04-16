<?php
require_once "config.php";
$nome = $cognome = $email = $nome_utente = $password = $tipo = $telefono = "";
$nome_err = $cognome_err = $email_err = $nome_utente_err = $password_err = $tipo_err = $telefono_err = "";

if ($_SERVER["REQUEST_METHOD"] == "GET") {    
    $input_nome = trim($_GET["Nome"]);
    if (empty($input_nome)) {
        $nome_err = "inserire un nome.";
    } else {
        $nome = $input_nome;
    }

    $input_cognome = trim($_GET["Cognome"]);
    if (empty($input_cognome)) {
        $cognome_err = "inserire un cognome.";
    } else {
        $cognome = $input_cognome;
    }

    $input_email = trim($_GET["e-mail"]);
    if (empty($input_email)) {
        $email_err = "inserire una e-mail.";
    } else {
        $email = $input_email;
    }

    $input_nome_utente = trim($_GET["nome_utente"]);
    if (empty($input_nome_utente)) {
        $nome_utente_err = "inserire un nome utente.";
    } else {
        $nome_utente = $input_nome_utente;
    }

    $input_password = trim($_GET["Password"]);
    if (empty($input_password)) {
        $password_err = "inserire una password.";
    } else {
        $password = $input_password;
    }

    $input_tipo = trim($_GET["Tipo"]);
    if (empty($input_tipo)) {
        $tipo_err = "inserire un tipo di account.";
    } else {
        $tipo = $input_tipo;
    }

    $input_telefono = trim($_GET["Telefono"]);
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
                $esito = array("Esito"=>true);

				echo json_encode($esito);
            } else {
                $esito = array("Esito"=>false);

				echo json_encode($esito);
            }
        } else {
            $esito = array("Esito"=>false);

			echo json_encode($esito);
        }
    } else {
    //PARAMETRI VUOTI
        $esito = array("Esito"=>false, "Motivo"=>"Parametri mancanti");

		echo json_encode($esito);
    }
} else {
	//NO GET (?)
    $esito = array("Esito"=>false);

	echo json_encode($esito);
}
