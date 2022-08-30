<?php
//Obtenemos todas las preguntas
class ApiController{
    public function index(){

    }
    public function getAllQuiz(){
        $quizs=QuizRepository::getAllApi(0,2000);
        header('Content-Type: application/json');
        print_r(json_encode($quizs)); 
    }
    public function getPerson(){
       header('Location: http://quiz.tipolisto.es/api/index.php');
    
    }
    public function getDogList(){
        $request=array("message"=>array("https://images.dog.ceo/breeds/hound-afghan/n02088094_1003.jpg","https://images.dog.ceo/breeds/hound-afghan/n02088094_1007.jpg", "https://images.dog.ceo/breeds/hound-afghan/n02088094_1023.jpg"),
              "status"=> "success");
            
        echo json_encode($request);
    }

    


}

?>