<?php

class MultimediaRepository{
    public static function getAll(){
		$imagenes=array();
		$basededatos= new MysqliClient();
		$basededatos->conectar_mysql();
		$consulta  = 'SELECT * FROM multimedia';
		$resultado=$basededatos->ejecutar_sql($consulta);
		while ($linea = mysqli_fetch_array($resultado)) 
		{
			if($linea['nombreimagen'] != "." || $linea['nombreimagen'] != ".."){
				$image=new Multimedia($linea['id']);
				$image->setName($linea['name']);
				$image->setDate($linea['date']);
				$image->setPath($linea['path']);
				$image->setUser($linea['user']);
				$image->setIdQuiz($linea['idQuiz']);
				$images[]=$image;
			}
		}
		$basededatos->desconectar();
		return $imagenes;
	}
	/**
	 * Esta función aparece en media/showAll
	 * también aparece en views/Quiz/updateUser.php
	 */
	public static function getAllImagesByUser($idUser){
		$images=array();
		$basededatos= new MysqliClient();
		$basededatos->conectar_mysql();
		//Rypes=1 images, audios, videos
		$consulta  = "SELECT * FROM multimedia WHERE user='".$idUser."' ORDER BY name";
		$resultado=$basededatos->ejecutar_sql($consulta);
		if($resultado==null) return null;
		while ($linea = mysqli_fetch_array($resultado)) 
		{
			$image=new Multimedia($linea['id']);
			$image->setName($linea['name']);
			$image->setDate($linea['date']);
			$image->setPath($linea['path']);
			$image->setUser($linea['user']);
			$image->setIdQuiz($linea['idQuiz']);
			$images[]=$image;
		}
		$basededatos->desconectar();
		return $images;
	}
	/**
	* Esta función es llamaa en views/Quiz/updateUser.php
	*/
	public static function getImage($id){
		$image=null;
		$basededatos= new MysqliClient();
		$basededatos->conectar_mysql();
		//tYPES=1 images, audios, videos
		$consulta  = "SELECT * FROM multimedia WHERE id='".$id."'";
		$resultado=$basededatos->ejecutar_sql($consulta);
		while ($linea = mysqli_fetch_array($resultado)) 
		{
			$image=new Multimedia($linea['id']);
			$image->setName($linea['name']);
			$image->setDate($linea['date']);
			$image->setPath($linea['path']);
			$image->setUser($linea['user']);
			$image->setIdQuiz($linea['idQuiz']);
		}
		$basededatos->desconectar();
		return $image;
	}
	public static function insert($name, $path, $type, $isUser, $idQuiz){
		$basededatos= new MysqliClient();
        $basededatos->conectar_mysql();
        $sql="INSERT INTO `multimedia` (`id`, `name`, `path`, `date`, `user`, `idQuiz`) VALUES 
        ( '', '$name', '$path', '', '$isUser', '$idQuiz') ";
        $basededatos->ejecutar_sql($sql);
        $basededatos->desconectar();
		if(!$basededatos) return false;	
        else return true;
	}
	/**
 	* Esta dunción es llamada en views/media/delete.php 
 	*/
	public static function delete($id){
        $bd= new MysqliClient();
        $bd->conectar_mysql();
        $sql="DELETE FROM multimedia WHERE id='".$id."' LIMIT 1";
        $bd->ejecutar_sql($sql);
        $bd->desconectar();
    }
}

?>