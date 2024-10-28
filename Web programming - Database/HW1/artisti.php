<?php
    require_once 'auth.php';
    header('Content-Type: application/json');

    $conn = mysqli_connect($dbconfig['host'], $dbconfig['user'], $dbconfig['password'], $dbconfig['name']) or die(mysqli_error($conn));
    $query = "SELECT NomeArtista, Genere FROM artisti";
    $res = mysqli_query($conn, $query) or die(mysqli_error($conn));

    $artisti = array();
    while($entry = mysqli_fetch_assoc($res)){
        $artisti[] = array('Nome' => $entry['NomeArtista'], 'Genere' => $entry['Genere']);
    }
    
    echo json_encode($artisti);
    exit;
?>