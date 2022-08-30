<?php
//El idMedia lo obtenemos en el app/routes.php y en el mediaController.php
if(empty($this->idMedia)) echo "idMedia not exits";
else{
    MultimediaRepository::delete($this->idMedia);
    header('Location: '.PATHSERVER."Media/showAll");
    if ( PRODUCTION==1 ) echo "<script type='text/javascript'>location.href='".PATHSERVER."media/showAll';</script>";
}

?>
