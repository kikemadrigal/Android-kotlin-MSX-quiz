<?php
class QuizController extends BaseController{
    
    function __construct()
    {
        parent::__construct();
    }


    public function index(){
        $quizs=QuizRepository::getAll(0,2000);
        $this->view->quizs=$quizs;
        $this->view->render("quiz/showAll"); 
    }
    public function showAllByUser(){
        $quizs=QuizRepository::getAllByUser($_SESSION['idusuario'],0,2000);
        $this->view->quizs=$quizs;
        $this->view->render("quiz/showAllByUser");
    }
    public function show($param=null){
        $quiz=QuizRepository::getById($param[0]);
        $this->view->quiz=$quiz;
        $this->view->render("quiz/show");
    }
    public function showAll($param=null){
        $quizs=QuizRepository::getAll(0,2000);
        $this->view->quizs=$quizs;
        $this->view->render("quiz/showAll");
    }


    public function showUser($param = null){
        $this->view->param=$param;
        $this->view->render("quiz/showUser");
    }
    public function search($search = null){
        $this->view->param=$search[0];
        $this->view->render("quiz/search");
    }
    public function searchUser($search = null){
        $this->view->param=$search[0];
        $this->view->render("quiz/search");
    }



    public function insert(){
       $this->view->render("quiz/insert");   
    }
    public function update($id=null){
        if (isset($_POST['submit'])){
            $quiz=new Quiz($_POST['id']);
            $quiz->setQuestion($_POST['question']);
            $quiz->setAnswer1($_POST['answer1']);
            $quiz->setAnswer2($_POST['answer2']);
            $quiz->setAnswer3($_POST['answer3']);
            $quiz->setCorrectAnswer($_POST['correctAnswer']);
            $quiz->setImage($_POST['image']);
            $quiz->setLevel($_POST['level']);
            $quiz->setTheme($_POST['theme']);
            $quiz->setCategory($_POST['category']);
            $quiz->setViewed($_POST['viewed']);
            $quiz->setDate($_POST['date']);
            $quiz->setCreator($_POST['creator']);
            if ($quiz!=null){
                QuizRepository::update($quiz);
                header("location: ".PATHSERVER."Quiz/update/".$_POST['id']."");
                if ( PRODUCTION==1 ) echo "<script type='text/javascript'>location.href='".PATHSERVER."Quiz/update/".$_POST['id']."';</script>";
            }else{
                echo "Update could not be completed";  
            }
            echo " la imagen es ".$quiz->getImage();
            die();
        }else{
            if(isset($_SESSION['idusuario']) && $_SESSION['nivelaccesousuario']==3){
                $quiz=QuizRepository::getById($id[0]);
                $this->view->quiz=$quiz;
                $this->view->render("quiz/updateUser");   
            }else if(isset($_SESSION['idusuario']) && $_SESSION['nivelaccesousuario']==1){ 
                $this->view->render("quiz/update"); 
            }
        }
    }
    public function delete($id=null){
        QuizRepository::delete($id[0]);
        header("location: ".PATHSERVER."Game/showByCategoriesUsers");
        if ( PRODUCTION==1 ) echo "<script type='text/javascript'>location.href='".PATHSERVER."Game/showByCategoriesUsers';</script>";
    }
}