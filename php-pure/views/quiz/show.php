<?php
//Show.php permite visualizar los datos de un juego alamcenado en la tabla games
//La variable param se obtiene en el GameController.php

$quiz=$this->quiz;
include_once("./views/templates/document-start.php"); 
?>
<!-- Patrones: para campos con números: pattern='[0-9]{1,10000}'-->
<!--<h3>Show user quiz </h3>-->


<div class="row text-center">
    <div class="col">
        <!--Obtener foto --> 
        <?php
        $image=MultimediaRepository::getImage($quiz->getImage());
        if($image!=null) $path=$image->getPath();
        else $path="";
        if(PRODUCTION==1){
            if (file_exists($path)){
                echo "<img class='img-fluid mx-4' src='".PATHSERVERSININDEX.$path."' class='max-height300' />&nbsp;&nbsp;";
            }else{
                echo "<img class='img-fluid mx-4' src='".PATHSERVERSININDEX."media/sinImagen.jpg' class='max-height300' />&nbsp;&nbsp;";
            }
        }else{
            if (file_exists($path)){
                echo "<img class='img-fluid img-fluid mx-4'  src='".PATHSERVER.$path."' class='max-height300' />";
            }else{
                echo "<img class='img-fluid mx-4' src='".PATHSERVER."media/sinImagen.jpg' class='max-height300' />&nbsp;&nbsp;";
            }
        }
        echo "<h4>".$quiz->getImage()."</h4>";
        ?>  
    </div>
</div>


<nav class="m-4">
  <div class="nav nav-tabs" id="nav-tab" role="tablist">
    <button class="nav-link active" id="nav-home-tab" data-bs-toggle="tab" data-bs-target="#nav-home" type="button" role="tab" aria-controls="nav-home" aria-selected="true">Information</button>
   </div>
</nav>
<div class="tab-content" id="nav-tabContent">
  <div class="tab-pane fade show active" id="nav-home" role="tabpanel" aria-labelledby="nav-home-tab">
      <p>Question: <?php echo $quiz->getQuestion(); ?></p>
      <p>Answer1: <?php echo $quiz->getAnswer1(); ?></p>
      <p>Answer2: <?php echo $quiz->getAnswer2(); ?></p>
      <p>Answer3: <?php echo $quiz->getAnswer3(); ?></p>
      <p>Correct answer: <?php echo $quiz->getCorrectAnswer(); ?></p>
      <p>Image: <?php echo $quiz->getImage(); ?></p>
      <p>Level: <?php echo $quiz->getLevel(); ?></p>
      <p>Theme: <?php echo $quiz->getTheme(); ?></p>
      <p>Category: <?php echo $quiz->getCategory(); ?></p>
      <p>Viewed: <?php echo $quiz->getViewed(); ?></p>
      <p>Date: <?php echo $quiz->getDate(); ?></p>
      <p>Creator: <?php echo $quiz->getCreator(); ?></p>
      <p></p>
  </div>
  
</div>





<?php include_once("./views/templates/document-end.php"); ?>