<?php
header('Content-Type: application/json');
include_once("../app/database/MysqliClient.php");
require_once("../app/config/env.php");

Class MultimediaApi {}

$componentesUrl=parse_url($_SERVER["REQUEST_URI"]);
//sacamos solo la ruta del final, toda la dirección menos la parte del servivor
$ruta=$componentesUrl["path"];
//Obtenemos los partes de la ruta
$partesRuta=explode("/", $ruta);
//echo var_dump($partesRuta);
$nparams=sizeof($partesRuta);
//echo "nparams: ".$nparams."<br>";
if($partesRuta[2]=="getImage.php"){
    $image=null;
    $basededatos= new MysqliClient();
    $basededatos->conectar_mysql();
    //tYPES=1 images, audios, videos
    $consulta  = "SELECT * FROM multimedia WHERE id='".$partesRuta[3]."'";
    $resultado=$basededatos->ejecutar_sql($consulta);
    while ($linea = mysqli_fetch_array($resultado)) 
    {
        $image=new MultimediaApi();
        $image->id=$linea['id'];
        $image->name=$linea['name'];
        $image->date=$linea['date'];
        $image->path=$linea['path'];
        $image->user=$linea['user'];
        $image->idQuiz=$linea['idQuiz'];
    }
    $basededatos->desconectar();
}


      
echo json_encode($image);
?>