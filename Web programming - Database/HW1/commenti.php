<?php
    require_once 'auth.php';
    header('Content-Type: application/json');

    $conn = mysqli_connect($dbconfig['host'], $dbconfig['user'], $dbconfig['password'], $dbconfig['name']) or die(mysqli_error($conn));
    $query = "SELECT Commento, DATE_FORMAT(DataCommento, '%d/%m/%Y') AS DataCommento, Voto, Art FROM commenti";
    $res = mysqli_query($conn, $query) or die(mysqli_error($conn));

    $commenti = array();
    while($entry = mysqli_fetch_assoc($res)){
        $commenti[] = array('Commento' => $entry['Commento'], 
                            'Data' => $entry['DataCommento'],
                            'Voto' => $entry['Voto'],
                            'Artista' => $entry['Art']);
    }
    
    echo json_encode($commenti);
    exit;
?>