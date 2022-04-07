<?php
require_once 'config.php';
if ($_SERVER["REQUEST_METHOD"] == "GET") {
    $nomeUtente = trim($_GET["nomeutente"]);
    $password = trim($_GET["password"]);

    if (isset($nomeUtente) and isset($password)) {

        $sql = "SELECT `nome_utente`,`Password` FROM `utenti` WHERE `nome_utente`=? and `Password`=?";

        if ($stmt = mysqli_prepare($link, $sql)) {
            mysqli_stmt_bind_param($stmt, "ss", $param_uname, $param_pass);

            $param_uname = $nomeUtente;
            $param_pass = $password;
            if (mysqli_stmt_execute($stmt)) {
                $result = mysqli_stmt_get_result($stmt);

                if (mysqli_num_rows($result) == 1) {
                    $row = mysqli_fetch_array($result, MYSQLI_ASSOC);
                    $nomeUtente = $row["nome_utente"];
                    $password = $row["Password"];
                    echo $nomeUtente;
                    echo $password;
                } else {
                    echo "righe maggiori diverse da 1";
                }
            } else {
                echo "non ha eseguito";
            }
        } else {
            echo " non ha preparato";
        }
    } else {
        echo "variabili non settate";
    }
} else {
    echo "no get";
}
