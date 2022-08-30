<?php
header('Content-Type: application/json');
include_once("../app/database/MysqliClient.php");
require_once("../app/config/env.php");
Class QuizApi {}


$basededatos= new MysqliClient();
$basededatos->conectar_mysql();
//$consulta  = "SELECT * FROM quiz ";
$consulta="Select q.id,q.question,q.answer1,q.answer2,q.answer3,q.image,q.correctAnswer, u.name from quiz q LEFT OUTER JOIN users u ON q.creator=u.id";

$resultado=$basededatos->ejecutar_sql($consulta);
$quizs=array();
while ($linea = mysqli_fetch_array($resultado)) 
{
    $quiz=new QuizApi();
    $quiz->id=$linea['id'];
    $quiz->question=$linea['question'];
    $quiz->answer1=$linea['answer1'];
    $quiz->answer2=$linea['answer2'];
    $quiz->answer3=$linea['answer3'];
    $quiz->image=$linea['image'];
    $quiz->correctAnswer=$linea['correctAnswer'];
    $quiz->nameUser=$linea['name'];
    $quizs[]=$quiz;
}
$basededatos->desconectar();


$data= json_encode($quizs);  
echo $data."\n";

?>