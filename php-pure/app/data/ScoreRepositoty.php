<?php
class ScoreRepositoty{
    public static function getAll($start, $end){
		$scores=array();
		$basededatos= new MysqliClient();
		$basededatos->conectar_mysql();
		$consulta  = "SELECT * FROM scores ORDER BY score DESC LIMIT ".$start .", ".$end." ";
		$resultado=$basededatos->ejecutar_sql($consulta);
		while ($linea = mysqli_fetch_array($resultado)) 
		{
			$score=new Score($linea['id']);
			$score->setName($linea['name']);
			$score->setScore($linea['score']);
			$score->setDate($linea['date']);
			$scores[]=$score;
		}
		$basededatos->desconectar();
		return $scores;
	}
}



?>