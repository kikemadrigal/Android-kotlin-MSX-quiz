<?php 
//showByCategoriesUsers muestra en una vistas elegidas por el usuario los juegos de la tabla usersgames
//Si recibimos un formulario es que hemos solicitado mostrar otra vista
if (isset($_POST['submit'])){
	$view=$_POST['view'];
	$user=UserRepository::getUser($_SESSION['idusuario']);
	$user->setView($view);
	UserRepository::updateUser($user);
}
include_once("./views/templates/document-start.php"); 
?>

<div class="m-4">
	<a href="<?php echo PATHSERVER;?>Quiz/number" class="btn btn-secondary">Number</a>
	<a href="<?php echo PATHSERVER;?>Quiz/a" class="btn btn-secondary">A</a>
	<a href="<?php echo PATHSERVER;?>Quiz/b" class="btn btn-secondary">B</a>
	<a href="<?php echo PATHSERVER;?>Quiz/c" class="btn btn-secondary">C</a>
	<a href="<?php echo PATHSERVER;?>Quiz/d" class="btn btn-secondary">D</a>
	<a href="<?php echo PATHSERVER;?>Quiz/e" class="btn btn-secondary">E</a>
	<a href="<?php echo PATHSERVER;?>Quiz/f" class="btn btn-secondary">F</a>
	<a href="<?php echo PATHSERVER;?>Quiz/g" class="btn btn-secondary">G</a>
	<a href="<?php echo PATHSERVER;?>Quiz/h" class="btn btn-secondary">H</a>
	<a href="<?php echo PATHSERVER;?>Quiz/i" class="btn btn-secondary">I</a>
	<a href="<?php echo PATHSERVER;?>Quiz/j" class="btn btn-secondary">J</a>
	<a href="<?php echo PATHSERVER;?>Quiz/k" class="btn btn-secondary">K</a>
	<a href="<?php echo PATHSERVER;?>Quiz/l" class="btn btn-secondary">L</a>
	<a href="<?php echo PATHSERVER;?>Quiz/m" class="btn btn-secondary">M</a>
	<a href="<?php echo PATHSERVER;?>Quiz/n" class="btn btn-secondary">N</a>
	<a href="<?php echo PATHSERVER;?>Quiz/ñ" class="btn btn-secondary">Ñ</a>
	<a href="<?php echo PATHSERVER;?>Quiz/o" class="btn btn-secondary">O</a>
	<a href="<?php echo PATHSERVER;?>Quiz/p" class="btn btn-secondary">P</a>
	<a href="<?php echo PATHSERVER;?>Quiz/q" class="btn btn-secondary">Q</a>
	<a href="<?php echo PATHSERVER;?>Quiz/r" class="btn btn-secondary">R</a>
	<a href="<?php echo PATHSERVER;?>Quiz/s" class="btn btn-secondary">S</a>
	<a href="<?php echo PATHSERVER;?>Quiz/t" class="btn btn-secondary">T</a>
	<a href="<?php echo PATHSERVER;?>Quiz/u" class="btn btn-secondary">U</a>
	<a href="<?php echo PATHSERVER;?>Quiz/v" class="btn btn-secondary">V</a>
	<a href="<?php echo PATHSERVER;?>Quiz/w" class="btn btn-secondary">W</a>
	<a href="<?php echo PATHSERVER;?>Quiz/x" class="btn btn-secondary">X</a>
	<a href="<?php echo PATHSERVER;?>Quiz/y" class="btn btn-secondary">Y</a>
	<a href="<?php echo PATHSERVER;?>Quiz/z" class="btn btn-secondary">Z</a>
</div>
<table class="table">
  <thead>
    <tr>
      <th scope="col">id</th>
      <th scope="col">Question</th>
	  <th scope="col">answer1</th>
      <th scope="col">answer1</th>
      <th scope="col">answer1</th>
      <th scope="col">correctAnswer</th>
    </tr>
  </thead>
	<?php
	if($this->quizs==NULL) die();
	else{
		foreach ($this->quizs as $posicion=>$quiz){
			if ($quiz->getId()!=NULL){
				echo "<tbody>";
					echo "<tr>";
						echo "<th scope='row'>".$quiz->getId()."</th>";
						echo "<td><a href='".PATHSERVER."Quiz/update/".$quiz->getId()."'>".$quiz->getQuestion()."</a></td>";
						echo "<td>".$quiz->getAnswer1()."</td>";
						echo "<td>".$quiz->getAnswer2()."</td>";
						echo "<td>".$quiz->getAnswer3()."</td>";
						echo "<td>".$quiz->getCorrectAnswer()."</td>";
					echo "</tr>";
				echo "</tbody>";
			}
		}	
	}
	?>
</table>

<?php include_once("./views/templates/document-end.php");?>

		

