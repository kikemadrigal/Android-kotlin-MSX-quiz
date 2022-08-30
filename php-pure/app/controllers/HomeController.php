<?php

class HomeController extends BaseController{
    function __construct()
    {
        parent::__construct();
    }

    public function index(){
        $this->view->render("home");
    }

    public function getPrivacidad(){
        $this->view->render("privacidad");
    }

}