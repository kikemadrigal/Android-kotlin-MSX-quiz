<?php
QuizRepository::delete($id);
header("location: ".PATHSERVER."Game/showByCategoriesUsers");
if ( PRODUCTION==1 ) echo "<script type='text/javascript'>location.href='".PATHSERVER."Game/showByCategoriesUsers';</script>";
?>