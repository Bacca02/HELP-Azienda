<?php
require_once 'config.php';
$name=$pass="";
    if($_SERVER["REQUEST_METHOD"]=="GET"){
        $input_name =trim($_GET["name"]);
        if(empty(trim($_GET["name"]))||empty(trim($_GET["pass"]))){
            $age = array("Esito"=>"N", "Motivo"=>"Parametri mancanti");
            header('Content-Type: application/json; charset=utf-8');
			echo json_encode($age);
            die();
        }else{
            $name=trim($_GET["name"]);
            $pass=trim($_GET["pass"]);
        }
            $sql = "SELECT * FROM `utenti` WHERE `Nome`='".$name."';";
            
            if($result = mysqli_query($link,$sql)){
                if(mysqli_num_rows($result) > 0){
                    $row = mysqli_fetch_array($result);
                    if($row['Password']==$pass&&$row['Tipo']=="admin"){                            
                        $age = array("Esito"=>"V","Tipo"=>"A");
						echo json_encode($age);                        
                    exit();
                    }
                    else if($row['Password']==$pass&&$row['Tipo']=="Utente"){
                    	$age = array("Esito"=>"V","Tipo"=>"U");
                        header('Content-Type: application/json; charset=utf-8');
						echo json_encode($age);                        
                    	exit();
                    }
                    else{
                        $age = [];
                        array_push($age, (object)["Esito"=>"N", "Motivo"=>"Password errata"]);
                        array_push($age, (object)[
        					"Consiglio" => "Metti quella giusta"
							]);
                        header('Content-Type: application/json; charset=utf-8');
						echo json_encode($age);
                    }
                }else{
                    $age = array("Esito"=>"N", "Motivo"=>"Utente insesitente");
                    header('Content-Type: application/json; charset=utf-8');
					echo json_encode($age);
                }
            }

            mysqli_free_result($result);
        mysqli_close($link);
    }