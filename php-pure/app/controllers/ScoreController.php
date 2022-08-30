<?php
class ScoreController extends BaseController{
    function __construct()
    {
        parent::__construct(); 
    }

    public function index(){
        $scores=ScoreRepositoty::getAll(0,2000);
        $this->view->scores=$scores;
        $this->view->render("score/showAll");   
    }
    
    public function showAll(){
        $scores=ScoreRepositoty::getAll(0,2000);
        $this->view->scores=$scores;
        $this->view->render("score/showAll");   
    }
    public function insert(){
        $this->view->render("score/insert");    
    }
    public function update(){
        if(isset($_POST['id'])) {
            $idScore=$_POST['id'];
            $this->view->idScore=$idScore;
            $this->view->render("score/update");  
        }else{
            echo "idScore not exists";
        }
          
    }
    public function delete(){
        if(isset($_POST['id'])) {
            $idScore=$_POST['id'];
            $this->view->idScore=$idScore;
            $this->view->render("score/delete");    
        }else{
            echo "idScore not exists";
        }

    }
}