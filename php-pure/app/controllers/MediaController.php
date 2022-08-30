<?php
class MediaController extends BaseController{
    function __construct()
    {
        parent::__construct(); 
    }

    public function index(){
        $this->view->render("media/showAll");    
    }
    public function show($image=null){
        $image=MultimediaRepository::getImage($image[0]);
        $this->view->image=$image;
        $this->view->render("media/show");
    }
    public function showAll(){
        
        if(isset($_SESSION['idusuario']) && $_SESSION['nivelaccesousuario']==3){
            $images=MultimediaRepository::getAllImagesByUser($_SESSION['idusuario']);
            $this->view->images=$images;
            $this->view->render("media/showAllUser");   
        }else if(isset($_SESSION['idusuario']) && $_SESSION['nivelaccesousuario']==1){ 
            $this->view->render("media/showAll"); 
        }
    }
    public function insert(){
        $this->view->render("media/insert");    
    }
    public function update(){
        if(isset($_POST['id'])) {
            $idMedia=$_POST['id'];
            $this->view->idMedia=$idMedia;
            $this->view->render("media/update");  
        }else{
            echo "idMedia not exists";
        }
          
    }
    public function delete(){
        if(isset($_POST['id'])) {
            $idMedia=$_POST['id'];
            $this->view->idMedia=$idMedia;
            $this->view->render("media/delete");    
        }else{
            echo "idMedia not exists";
        }

    }
}