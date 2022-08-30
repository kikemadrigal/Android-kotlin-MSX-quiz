<?php

Class Quiz {
	private $id;
	private $question;
	private $answer1;
	private $answer2;
	private $answer3;
	private $correctAnswer;
	private $image;
	//Nivel de dificultad:0,1,2
	private $level;
	//theme: MSX, spectrum, amstrad
	private $theme;
	//group: hardware, programación, etc
	private $category;
	private $viewed;
	//programming: MSX Basic, assambler
	private $date;
	private $creator;
	

	
	/**
	 * CONSTRUCTOR
	 */
	function __construct($id){
		$this->id=$id;
	}
	

	/**
	 * SETTERS
	 */
	function setQuestion($question){
		$this->question=$question;
	}
	function setAnswer1($answer1){
		$this->answer1=$answer1;
	}
	function setAnswer2($answer2){
		$this->answer2=$answer2;
	}
	function setAnswer3($answer3){
		$this->answer3=$answer3;
	}
	function setCorrectAnswer($correctAnswer){
		$this->correctAnswer=$correctAnswer;
	}
	function setImage($image){
		$this->image=$image;
	}
	function setLevel($level){
		$this->level=$level;
	}
	function setTheme($theme){
		$this->theme=$theme;
	}
	function setCategory($category){
		$this->category=$category;
	}
	function setViewed($viewed){
		$this->viewed=$viewed;
	}
	function setDate($date){
		$this->date=$date;
	}
	function setCreator($creator){
		$this->creator=$creator;
	}
	

	








	/**
	 * GETTERS
	 */
	function getId(){
		return $this->id;	
	}
	function getQuestion(){
		return $this->question;
	}
	function getAnswer1(){
		return $this->answer1;
	}
	function getAnswer2(){
		return $this->answer2;
	}
	function getAnswer3(){
		return $this->answer3;
	}
	function getCorrectAnswer(){
		return $this->correctAnswer;
	}
	function getImage(){
		return $this->image;
	}
	function getLevel(){
		return $this->level;
	}
	function getTheme(){
		return $this->theme;
	}
	function getCategory(){
		return $this->category;
	}
	function getViewed(){
		return $this->viewed;
	}
	function getDate(){
		return $this->date;
	}
	function getCreator(){
		return $this->creator;
	}
	
	
	
	
	/**
	 * TODTRING
	 */
	function toString(){
		return"<br>Quiz id: ".$this->id.", question: ".$this->question.", answer1: ".$this->answer1.", answer2: ".$this->answer2.", answer3: ".$this->answer3.", correctAnswer: ".$this->correctAnswer.", image: ".$this->image.", level: ".$this->level.", theme: ".$this->theme.", category: ".$this->category.", viewed: ".$this->viewed.", date: ".$this->date.", creator: ".$this->creator;	
	}

	
	
}

?>