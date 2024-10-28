<?php 
    include 'auth.php';
    
    if(artista()){
        $NomeArtista = $_SESSION['artista'];
    }

    $conn = mysqli_connect($dbconfig['host'], $dbconfig['user'], $dbconfig['password'], $dbconfig['name']) or die(mysqli_error($conn));

    $commento = mysqli_real_escape_string($conn, $_POST['cdi']);
    $data = mysqli_real_escape_string($conn, $_POST['Data']);
    $voto = mysqli_real_escape_string($conn, $_POST['Voto']);
    $artista = mysqli_real_escape_string($conn, $_SESSION['artista']);

    $query = "INSERT INTO `commenti`(`idCommento`, `Commento`, `DataCommento`, `Voto`, `Art`) VALUES ('[value-1]','$commento','$data','$voto','$artista')";
    $res = mysqli_query($conn, $query) or die(mysqli_error($conn));;

    mysqli_free_result($res);
    mysqli_close($conn);
?>