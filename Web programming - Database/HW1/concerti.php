<?php
    require_once 'auth.php';
    header('Content-Type: application/json');

    $conn = mysqli_connect($dbconfig['host'], $dbconfig['user'], $dbconfig['password'], $dbconfig['name']) or die(mysqli_error($conn));
    $query = "SELECT idConcerto, NomeConcerto, Prezzo, DATE_FORMAT(DataConcerto, '%d/%m/%Y') AS DataConcerto, Artista, NomeLocalita, Posti, CAP FROM concerto,localita WHERE idLocalita = iL";
    $res = mysqli_query($conn, $query) or die(mysqli_error($conn));

    $concerti = array();
    while($entry = mysqli_fetch_assoc($res)){
        $concerti[] = array('idConcerto' => $entry['idConcerto'], 'NomeCon' => $entry['NomeConcerto'], 'Prezzo' => $entry['Prezzo'],
                            'Data' => $entry['DataConcerto'], 'NomeArtista' => $entry['Artista'],
                            'Localita' => $entry['NomeLocalita'], 'Posti' => $entry['Posti'], 'CAP' => $entry['CAP']);
    }
    
    echo json_encode($concerti);
    exit;
?>