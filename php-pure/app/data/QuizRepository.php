<?php

class QuizRepository{ 



	/**********************************************************************************************
	* 										SELECT
	**********************************************************************************************/

	public static function getById($id){
		$quiz=null;
		$basededatos= new MysqliClient();
		$basededatos->conectar_mysql();
		$consulta  = "SELECT * FROM quiz WHERE id='".$id."' ";
		$resultado=$basededatos->ejecutar_sql($consulta);
		while ($linea = mysqli_fetch_array($resultado)) 
		{
			$quiz=new Quiz($linea['id']);
			$quiz->setQuestion($linea['question']);
			$quiz->setAnswer1($linea['answer1']);
			$quiz->setAnswer2($linea['answer2']);
			$quiz->setAnswer3($linea['answer3']);
			$quiz->setCorrectAnswer($linea['correctAnswer']);
			$quiz->setImage($linea['image']);
			$quiz->setLevel($linea['level']);
			$quiz->setTheme($linea['theme']);
			$quiz->setCategory($linea['category']);
			$quiz->setViewed($linea['viewed']);
			$quiz->setDate($linea['date']);
			$quiz->setCreator($linea['creator']);
		}
		$basededatos->desconectar();
		return $quiz;
	}
		/**
	 * Esta función es utilizada en updateUser
	 */
	public static function getTitleById($id){
		$basededatos= new MysqliClient();
		$basededatos->conectar_mysql();
		$consulta  = "SELECT title FROM quiz WHERE id='".$id."' ";
		$resultado=$basededatos->ejecutar_sql($consulta);
		while ($linea = mysqli_fetch_array($resultado)) 
		{
			return $linea['title'];
		}
		$basededatos->desconectar();
	}
	public static function getAuthorById($id){
		$basededatos= new MysqliClient();
		$basededatos->conectar_mysql();
		$consulta  = "SELECT author FROM games WHERE id='$id' ";
		$resultado=$basededatos->ejecutar_sql($consulta);
		while ($linea = mysqli_fetch_array($resultado)) 
		{
			return $linea['author'];
		}
		$basededatos->desconectar();
	}




	/**
	 * Parametros:
	 * start registro de inicio
	 * end: maximos registros a buscar
	 */
	
	public static function getAll($start, $end){
		$quizs=array();
		$basededatos= new MysqliClient();
		$basededatos->conectar_mysql();
		$consulta  = "SELECT * FROM quiz limit ".$start .", ".$end."";
		$resultado=$basededatos->ejecutar_sql($consulta);
		while ($linea = mysqli_fetch_array($resultado)) 
		{
			$quiz=new Quiz($linea['id']);
			$quiz->setQuestion($linea['question']);
			$quiz->setAnswer1($linea['answer1']);
			$quiz->setAnswer2($linea['answer2']);
			$quiz->setAnswer3($linea['answer3']);
			$quiz->setCorrectAnswer($linea['correctAnswer']);
			$quiz->setImage($linea['image']);
			$quiz->setLevel($linea['level']);
			$quiz->setTheme($linea['theme']);
			$quiz->setCategory($linea['category']);
			$quiz->setViewed($linea['viewed']);
			$quiz->setDate($linea['date']);
			$quiz->setCreator($linea['creator']);
			$quizs[]=$quiz;
		}
		$basededatos->desconectar();
		return $quizs;
	}
	public static function obtenerTodos(){
		$quizs=array();
		$basededatos= new MysqliClient();
		$basededatos->conectar_mysql();
		$consulta  = "SELECT * FROM quiz limit 1";
		$resultado=$basededatos->ejecutar_sql($consulta);
		while ($linea = mysqli_fetch_array($resultado)) 
		{
			$quiz=new QuizApi();
			$quiz->question=$linea['question'];
			$quiz->answer1=$linea['answer1'];
			$quiz->answer2=$linea['answer2'];
			$quiz->answer3=$linea['answer3'];
			$quiz->correctAnswer=$linea['correctAnswer'];
			
			$quizs[]=$quiz;
		}
		$basededatos->desconectar();
		return $quizs;
	}
	public static function getAllApi($start, $end){
		$quizs=array();
		$basededatos= new MysqliClient();
		$basededatos->conectar_mysql();
		$consulta  = "SELECT * FROM quiz limit ".$start .", ".$end."";
		$resultado=$basededatos->ejecutar_sql($consulta);
		$linea = mysqli_fetch_array($resultado);
		
		$basededatos->desconectar();
		return $linea;
	}

	public static function getAllByUser($idUser, $start, $end){
		$quizs=array();
		$basededatos= new MysqliClient();
		$basededatos->conectar_mysql();
		$consulta  = "SELECT * FROM quiz WHERE creator='".$idUser."' limit ".$start .", ".$end."";
		$resultado=$basededatos->ejecutar_sql($consulta);
		while ($linea = mysqli_fetch_array($resultado)) 
		{
			$quiz=new Quiz($linea['id']);
			$quiz->setQuestion($linea['question']);
			$quiz->setAnswer1($linea['answer1']);
			$quiz->setAnswer2($linea['answer2']);
			$quiz->setAnswer3($linea['answer3']);
			$quiz->setCorrectAnswer($linea['correctAnswer']);
			$quiz->setImage($linea['image']);
			$quiz->setLevel($linea['level']);
			$quiz->setTheme($linea['theme']);
			$quiz->setCategory($linea['category']);
			$quiz->setViewed($linea['viewed']);
			$quiz->setDate($linea['date']);
			$quiz->setCreator($linea['creator']);
			$quizs[]=$quiz;
		}
		$basededatos->desconectar();
		return $quizs;
	}



	/*
	INSERT INTO gamesUsers VALUES ( '', 'title', 'Cove', 'Instructions', 'Country()', 'Publisher', 'Developer', 'Year', 'Format', 'Genre', 'System', 'Programming', 'Sound', 'Control', '1', 'Languages', '1', '1', '1', '1', '1', '0' , 'Observations', '186');
	*/
	public static function insert($quiz){
		$bd= new MysqliClient();
		$bd->conectar_mysql();
		if (!$bd->checkExitsQuizTable()){
			echo "quiz table not exists";
			die();
		}
		//$sql="INSERT INTO quiz VALUES ( '', '".$quiz->getQuestion()."', '".$quiz->getAnswer1()."', '".$quiz->getAnswer2()."', '".$quiz->getAnswer3()."', '".$quiz->getCorrectAnswer()."', '".$quiz->getImage()."', '".$quiz->getLevel()."', '".$quiz->getTheme()."', '".$quiz->getCategory()."', '".$quiz->getViewed()."', '".$quiz->getDate()."', '".$quiz->getCreator()."') ";
		$sql="INSERT INTO quiz VALUES ( '', '".$quiz->getQuestion()."', '".$quiz->getAnswer1()."', '".$quiz->getAnswer2()."', '".$quiz->getAnswer3()."', '".$quiz->getCorrectAnswer()."', '".$quiz->getImage()."', '".$quiz->getLevel()."', '".$quiz->getTheme()."', '".$quiz->getCategory()."', '".$quiz->getViewed()."', '".$quiz->getDate()."', '186') ";

		$success=$bd->ejecutar_sql($sql);
		$bd->desconectar();
		return $success;
	}


	public static function update($quiz){
        $bd= new MysqliClient();
        $bd->conectar_mysql();
        if (!$bd->checkExitsQuizTable()){
            echo "Quiz table not exists";
            die();
        }
		$sql="update quiz set question='".$quiz->getQuestion()."', answer1='".$quiz->getAnswer1()."', answer2='".$quiz->getAnswer2()."', answer3='".$quiz->getAnswer3()."', correctAnswer='".$quiz->getCorrectAnswer()."', image='".$quiz->getImage()."', level='".$quiz->getLevel()."', theme='".$quiz->getTheme()."', category='".$quiz->getCategory()."', viewed='".$quiz->getViewed()."', date='".$quiz->getDate()."', creator='".$quiz->getCreator()."' where id='".$quiz->getId()."'";
        $bd->ejecutar_sql($sql);
        $bd->desconectar();
    }

	public static function delete($id){
        $bd= new MysqliClient();
        $bd->conectar_mysql();
        if (!$bd->checkExitsQuizTable()){
            echo "Quiz table not exists";
            die();
        }
        $sql="DELETE FROM quiz WHERE id='".$id."' LIMIT 1";
        $bd->ejecutar_sql($sql);
        $bd->desconectar();
    }
}
?>