<?php

if (isset($_POST['submit'])){
    $quiz=new Quiz(0);
    $quiz->setQuestion($_POST['question']);
    $quiz->setAnswer1($_POST['answer1']);
    $quiz->setAnswer2($_POST['answer2']);
    $quiz->setAnswer3($_POST['answer3']);
    $quiz->setCorrectAnswer($_POST['correctAnswer']);
    $quiz->setImage($_POST['image']);
    $quiz->setLevel($_POST['level']);
    $quiz->setTheme($_POST['theme']);
    $quiz->setCategory($_POST['category']);
    $quiz->setViewed($_POST['viewed']);
    $quiz->setDate($_POST['date']);
  
    $quiz->setCreator($_SESSION['idusuario']);
    
    if ($quiz!=null){
        $success=QuizRepository::insert($quiz);
        if($success>0){
            header("location: ".PATHSERVER."Game/showByCategoriesUsers");
            if ( PRODUCTION==1 ) echo "<script type='text/javascript'>location.href='".PATHSERVER."Game/showByCategoriesUsers';</script>";       
        }else{
            echo "Insert could not be completed"; 
        }
    }else{
        echo "Insert could not be completed";  
    }
    die();
}
include_once("./views/templates/document-start.php");
?>

<h3 class="">Insert quiz</h3>
<form method='post' action='<?php echo PATHSERVER."Quiz/insert"?>' class='form-horizontal banckground-yellow' enctype='multipart/form-data'>
    <div class='form-group m-4' >
        <label for='question' class='control-label col-md-2'>question:</label>
        <div class='col'>
            <textarea type='text' class='form-control' name='question' id='question' rows=3 cols=60 title='Enter question' ></textarea>
        </div>
    </div>
 





    <!-- Rollo imagen -->
    <div class='form-group m-4 text-center' > 
        <div class='col'>
            <!--Obtener foto --> 
            <?php
                if(PRODUCTION==1){
                    echo "<img src='".PATHSERVERSININDEX."media/sinImagen.jpg' class='max-height300' /><br>";
                }else{
                    echo "<img src='".PATHSERVER."media/sinImagen.jpg' class='max-height300' /><br>";
                }
            
           ?>  
            <a href='<?php echo PATHSERVER;?>Media/showall' target='_blanck' >Management</a><br>         
            <?php 
                $images=MultimediaRepository::getAllImagesByUser($_SESSION['idusuario']);?>
                <!--image -->  
                <select class="m-4" name='image' id='image' required > ";
                <?php
                    echo "<option value='0' >Sin image</option>";
                    foreach ($images as $posicion=>$image){
                        echo "<option value='".$image->getId()."' >".$image->getName()."</option>";
                    }
                ?>
            </select>       
        </div>
    </div>
    <!-- Fin image -->  















    <div class='form-group m-4' >  
        <label for='answer1' class='control-label'>Answer1:</label> 
        <div class='col'>
            <input type='text' class='form-control' name='answer1' id='answer1' title='answer1' required/>
        </div>
    </div> 

    <div class='form-group m-4' >
        <label for='answer2' class='control-label'>Answer2:</label> 
        <div class='col'>
            <input type='text' class='form-control' name='answer2' id='answer2' title='answer2' required/>
        </div>
    </div> 
    <div class='form-group m-4' >  
        <label for='answer3' class='control-label'>Answer3: </label> 
        <div class='col'>
            <input type='text' class='form-control' name='answer3' id='answer3' title='answer3' required/>
        </div>
    </div> 

    <div class='form-group m-4' > 
        <label for='correctAnswer' class='control-label'>CorrectAnswer</label> 
        <div class='col'>
            <input type='text' class='form-control' name='correctAnswer' id='correctAnswer' required>
        </div>
    </div> 
    <div class='form-group m-4' >  
        <label for='level' class='control-label'>level: 0 without difficulty, 3 extreme difficulty</label> 
        <div class='col'>
            <input type='number' class='form-control' name='level' id='level' title='level' />
        </div>
    </div> 
    <div class='form-group m-4' >  
        <label for='theme' class='control-label'>theme: 8 bits, MSX, computers, etc</label> 
        <div class='col'>
            <input type='text' class='form-control' name='theme' id='theme' title='theme' />
        </div>
    </div> 
    <div class='form-group m-4' >  
        <label for='category' class='control-label'>category: Hardware, music, programming, games, etc</label> 
        <div class='col'>
            <input type='text' class='form-control' name='category' id='category' title='category' />
        </div>
    </div> 
    <div class='form-group m-4' >  
        <label for='viewed' class='control-label'>viewed (visto): 0 not, 1 yes</label> 
        <div class='col'>
            <input type='text' class='form-control' name='viewed' id='viewed' title='viewed' />
        </div>
    </div> 
    <div class='form-group m-4' >  
        <label for='date' class='control-label'>date: empty to current time</label> 
        <div class='col'>
            <input type='text' class='form-control' name='date' id='date' title='date' />
        </div>
    </div> 
   



    <div class='form-group m-4' > 
        <div class='col col-md-offset-2' >
            <input type='submit' name='submit' value='Insert' class='btn btn-primary' />
        </div>
    </div> 
        
</form>

<?php

include_once("./views/templates/document-end.php");
?>
