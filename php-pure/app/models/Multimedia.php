<?php
Class Multimedia {
	private $id;
	private $name;
    private $path;
    private $date;
    private $user;
	private $idQuiz;

	function __construct($id){
		$this->id=$id;
	}

	function setName($name){
		$this->name=$name;
	}
	function setDate($date){
		$this->date=$date;
	}
    function setPath($path){
		$this->path=$path;
	}
    function setUser($user){
		$this->user=$user;
	}
    function setIdQuiz($idQuiz){
		$this->isQuiz=$idQuiz;
	}
	

	
	function getId(){
		return $this->id;	
	}
	function getName(){
		return $this->name;
	}
    function getDate(){
		return $this->date;
	}
    function getPath(){
		return $this->path;
	}
    function getUser(){
		return $this->user;
	}
    function getIdQuiz(){
		return $this->idQuiz;
	}


	
	function toString(){
		return "Multimedia: Id: ".$this->id.", name: ".$this->name;	
	}
}