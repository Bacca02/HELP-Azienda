<?php
require_once 'config.php';
$name=$pass="";
    if($_SERVER["REQUEST_METHOD"]=="POST"){
        
        if(empty(trim($_POST["name"]))||empty(trim($_POST["pass"]))){
            $age = array("Esito"=>"N", "Motivo"=>"Parametri mancanti");
            header('Content-Type: application/json; charset=utf-8');
			echo json_encode($age);
            die();
        }else{
            $name=trim($_POST["name"]);
            $pass=trim($_POST["pass"]);
        }
            $sql = "SELECT * FROM `utenti` WHERE `Nome`='".$name."';";
            
            if($result = mysqli_query($link,$sql)){
                if(mysqli_num_rows($result) > 0){
                    $row = mysqli_fetch_array($result);
                    if($row['Password']==$pass&&($row['Tipo']=="Admin"||$row['Tipo']=="SAdmin")){                            
                        $age = array("Esito"=>"V","Tipo"=>"A", "iD"=>$row['iD']);
						echo json_encode($age);                        
                    exit();
                    }
                    else if($row['Password']==$pass&&$row['Tipo']=="Utente"){
                    	$age = array("Esito"=>"V","Tipo"=>"U","iD"=>$row['iD']);
                        header('Content-Type: application/json; charset=utf-8');
						echo json_encode($age);                        
                    	exit();
                    }
                    else{
                        $age = array("Esito"=>"N", "Motivo"=>"Password errata");
                        /*array_push($age, (object)[
        					"Consiglio" => "Metti quella giusta"
							]);*/
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
