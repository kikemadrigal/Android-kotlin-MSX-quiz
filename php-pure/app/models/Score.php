<?php
class Score{
    private $id;
    private $name;
	private $score;
    private $date;

    	/**
	 * CONSTRUCTOR
	 */
	function __construct($id){
		$this->id=$id;
	}
	

	/**
	 * SETTERS
	 */

    function setName($name){
		$this->name=$name;
	}
	function setScore($score){
		$this->score=$score;
	}
    function setDate($date){
		$this->date=$date;
	}

    
	/**
	 * GETTERS
	 */
	function getId(){
		return $this->id;	
	}
	function getName(){
		return $this->name;
	}
	function getScore(){
		return $this->score;
	}
    function getDate(){
		return $this->date;
	}
}




?>