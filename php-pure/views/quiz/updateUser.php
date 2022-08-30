<?php 






//El objeto quiz es obtenido en el controlador
include_once("./views/templates/document-start.php"); 
?>
<!-- Modal -->
<div class="modal fade" id="deleteModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="exampleModalLabel">Delete</h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <div class="modal-body">
                <p>¿Estas seguro que quieres borrar ? / Are you sure you want to delete?</p>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
                <a href="<?php echo PATHSERVER; ?>Quiz/delete/<?php echo $idGame; ?>" class="btn btn-primary">   SI   </a>
            </div>
        </div>
    </div>
</div>


<h3>Update Quiz </h3>
<?php
    $quiz=$this->quiz;
    echo "<h4>".$quiz->getId()."</h4>";
?>


<!----------------------------------------------------------------------------------------------------------------------->
<!------------------------------------------------ FORMULARIO ACTUALIZAR------------------------------------------------->
<!----------------------------------------------------------------------------------------------------------------------->
<!-- Patrones: para campos con números: pattern='[0-9]{1,10000}'-->
<form method=post action='<?php echo PATHSERVER."Quiz/update"?>' class='form-horizontal background-pink' enctype='multipart/form-data'>
    <div class='form-group m-4' >
        <label for='question' class='control-label col-md-2'>question:</label>
        <div class='col'>
            <textarea type='text' class='form-control' name='question' id='question' rows=3 cols=60 title='Enter question' ><?php echo $quiz->getQuestion();?></textarea>
        </div>
    </div>








    <!--Cover -->  
    <div class='text-center' > 
        <div class='col'>
            <!--Obtener foto --> 
            <?php
            $image=MultimediaRepository::getImage($quiz->getImage());

            if($image==null){
                if(PRODUCTION==1){
                    echo "<img src='".PATHSERVERSININDEX."media/sinImagen.jpg' class='max-height300' /><br>";
                }else{
                    echo "<img src='".PATHSERVER."media/sinImagen.jpg' class='max-height300' /><br>";
                }
            }else{
                if(PRODUCTION==1){
                    echo "<img src=".PATHSERVERSININDEX.$image->getPath()." class='max-height300' /><br>";
                }else{
                    echo "<img src=".PATHSERVER.$image->getPath()." class='max-height300' /><br>";
                }
            }
           ?>  
            <a href='<?php echo PATHSERVER;?>Media/showAll' target='_blanck' >Management</a><br>      
           <!--Fin de obtener foto -->         
            <?php $images=MultimediaRepository::getAllImagesByUser($_SESSION['idusuario']);?>
            <select class="m-4" name='image' id='image' > ";
                <?php
               
                if ($image==null){
                    echo "<option value='1' >Sin imagen</option>";
                }else{
                    echo "<option value='".$image->getId()."' >".$image->getName()."</option>";
                    
                }
                foreach ($images as $posicion=>$image){
                    echo "<option value='".$image->getId()."' >".$image->getName()."</option>";
                }

                ?>
            </select>       
        </div>
    </div>
    <!-- Fin cover -->  















    <div class='form-group m-4' >  
        <label for='answer1' class='control-label '>answer1:</label> 
        <div class='col'>
            <input type='text' class='form-control' name='answer1' id='answer1' title='answer1' value='<?php echo $quiz->getAnswer1(); ?>' required />
        </div>
    </div> 
    <div class='form-group m-4' >  
        <label for='answer2' class='control-label '>answer2:</label> 
        <div class='col'>
            <input type='text' class='form-control' name='answer2' id='answer2' title='answer2' value='<?php echo $quiz->getAnswer2(); ?>' required />
        </div>
    </div> 
    <div class='form-group m-4' >
        <label for='answer3' class='control-label '>answer3:</label> 
        <div class='col'>
            <input type='text' class='form-control' name='answer3' id='answer3' title='answer3' value='<?php echo $quiz->getAnswer3(); ?>' required />
        </div>
    </div> 
    <div class='form-group m-4' >  
        <label for='correctAnswer' class='control-label '>correctAnswer: </label> 
        <div class='col'>
            <input type='text' class='form-control' name='correctAnswer' id='correctAnswer' title='correctAnswer' value='<?php echo $quiz->getCorrectAnswer(); ?>' required />
        </div>
    </div> 
    <!--<div class='form-group m-4' > 
        <label for='image' class='control-label '>image:</label> 
        <div class='col'>
            <input type='text' class='form-control' name='image' id='image' size=80 value='<?php echo $quiz->getImage(); ?>' />
        </div>
    </div> -->
    <div class='form-group m-4' >  
        <label for='level' class='control-label '>level: </label> 
        <div class='col'>
            <input type='number' class='form-control' name='level' id='level' title='level' value='<?php echo $quiz->getLevel(); ?>'  />
        </div>
    </div> 
    <div class='form-group m-4' >  
        <label for='theme' class='control-label '>theme:</label> 
        <div class='col'>
            <input type='text' class='form-control' name='theme' id='theme' title='theme' value='<?php echo $quiz->getTheme(); ?>'  />
        </div>
    </div> 
    <div class='form-group m-4' >  
        <label for='category' class='control-label '>category:</label> 
        <div class='col'>
            <input type='text' class='form-control' name='category' id='category' title='category' value='<?php echo $quiz->getCategory(); ?>'  />
        </div>
    </div> 

    <div class='form-group m-4' >  
        <label for='viewed' class='control-label '>viewed:</label> 
        <div class='col'>
            <input type='text' class='form-control' name='viewed' id='viewed' title='viewed' value='<?php echo $quiz->getViewed(); ?>'  />
        </div>
    </div> 
    <div class='form-group m-4' >  
        <label for='date' class='control-label '>date: </label> 
        <div class='col'>
            <input type='text' class='form-control' name='date' id='date' title='date' value='<?php echo $quiz->getDate(); ?>' />
        </div>
    </div> 
    <div class='form-group m-4' >  
        <label for='creator' class='control-label '>control:</label> 
        <div class='col'>
            <input type='text' class='form-control' name='creator' id='creator' title='creator' value='<?php echo $quiz->getCreator(); ?>' />
        </div>
    </div> 
    


    <div class='form-group m-4' > 
        <div class='col col-md-offset-2' >
            <input type="hidden" name="id" id="id" value='<?php echo $quiz->getId() ?>' />
            <input type='submit' name="submit" id="submit" value='Update' class='btn btn-primary' ></input> 
            <!--<input type='button' name="remove" id="remove" value='Remove' class='btn btn-danger' data-toggle="modal" data-target="#deleteModal" ></input> -->
            <a href="<?php echo PATHSERVER; ?>Quiz/delete/<?php echo $quiz->getId(); ?>" class="btn btn-danger">   Delete   </a>
        </div>
    </div> 
</form>
<!----------------------------------------------------------------------------------------------------------------------->
<!---------------------------------------FINAL DEL FORMULARIO ACTUALIZAR------------------------------------------------->
<!----------------------------------------------------------------------------------------------------------------------->











<?php include_once("./views/templates/document-end.php"); ?>