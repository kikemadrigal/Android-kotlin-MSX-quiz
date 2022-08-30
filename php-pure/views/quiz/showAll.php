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
	<a href="<?php echo PATHSERVER;?>home/number" class="btn btn-secondary">Number</a>
	<a href="<?php echo PATHSERVER;?>home/a" class="btn btn-secondary">A</a>
	<a href="<?php echo PATHSERVER;?>home/b" class="btn btn-secondary">B</a>
	<a href="<?php echo PATHSERVER;?>home/c" class="btn btn-secondary">C</a>
	<a href="<?php echo PATHSERVER;?>home/d" class="btn btn-secondary">D</a>
	<a href="<?php echo PATHSERVER;?>home/e" class="btn btn-secondary">E</a>
	<a href="<?php echo PATHSERVER;?>home/f" class="btn btn-secondary">F</a>
	<a href="<?php echo PATHSERVER;?>home/g" class="btn btn-secondary">G</a>
	<a href="<?php echo PATHSERVER;?>home/h" class="btn btn-secondary">H</a>
	<a href="<?php echo PATHSERVER;?>home/i" class="btn btn-secondary">I</a>
	<a href="<?php echo PATHSERVER;?>home/j" class="btn btn-secondary">J</a>
	<a href="<?php echo PATHSERVER;?>home/k" class="btn btn-secondary">K</a>
	<a href="<?php echo PATHSERVER;?>home/l" class="btn btn-secondary">L</a>
	<a href="<?php echo PATHSERVER;?>home/m" class="btn btn-secondary">M</a>
	<a href="<?php echo PATHSERVER;?>home/n" class="btn btn-secondary">N</a>
	<a href="<?php echo PATHSERVER;?>home/ñ" class="btn btn-secondary">Ñ</a>
	<a href="<?php echo PATHSERVER;?>home/o" class="btn btn-secondary">O</a>
	<a href="<?php echo PATHSERVER;?>home/p" class="btn btn-secondary">P</a>
	<a href="<?php echo PATHSERVER;?>home/q" class="btn btn-secondary">Q</a>
	<a href="<?php echo PATHSERVER;?>home/r" class="btn btn-secondary">R</a>
	<a href="<?php echo PATHSERVER;?>home/s" class="btn btn-secondary">S</a>
	<a href="<?php echo PATHSERVER;?>home/t" class="btn btn-secondary">T</a>
	<a href="<?php echo PATHSERVER;?>home/u" class="btn btn-secondary">U</a>
	<a href="<?php echo PATHSERVER;?>home/v" class="btn btn-secondary">V</a>
	<a href="<?php echo PATHSERVER;?>home/w" class="btn btn-secondary">W</a>
	<a href="<?php echo PATHSERVER;?>home/x" class="btn btn-secondary">X</a>
	<a href="<?php echo PATHSERVER;?>home/y" class="btn btn-secondary">Y</a>
	<a href="<?php echo PATHSERVER;?>home/z" class="btn btn-secondary">Z</a>
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
						echo "<td><a href='".PATHSERVER."Quiz/show/".$quiz->getId()."'>".$quiz->getQuestion()."</a></td>";
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

		

