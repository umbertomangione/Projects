<?php 
    include 'auth.php';
    if (!$user = checkAuth()) {
        header('Location: registrazione.php');
    }else{
        $var = "Logout";
        $var2 = "logout.php";
    }

    if(artista()){
        $NomeArtista = $_SESSION['artista'];
    }

    if(isset($_POST['Acquisto']) && ($_POST['Posti']>0)){
        $conn = mysqli_connect($dbconfig['host'], $dbconfig['user'], $dbconfig['password'], $dbconfig['name']) or die(mysqli_error($conn));

        $username = mysqli_real_escape_string($conn, $_SESSION['username']);
        $id = mysqli_real_escape_string($conn, $_POST['ID']);
        $posti = mysqli_real_escape_string($conn, $_POST['Posti']);

        $query = "SELECT email FROM utenti WHERE username = '$username' OR email= '$username' ";
        $res = mysqli_query($conn, $query) or die(mysqli_error($conn));;

        $entry = mysqli_fetch_assoc($res);
        $query = "INSERT INTO `biglietti`(`idBiglietto`, `NumeroBiglietto`, `email`, `IC`) VALUES ('[value-1]','1','".$entry['email']."','$id')";
        $res = mysqli_query($conn, $query) or die(mysqli_error($conn));
        
        $posti = $posti - 1;
        $query = "UPDATE `concerto` SET `Posti` = '$posti' WHERE `concerto`.`idConcerto` = $id";
        $res = mysqli_query($conn, $query) or die(mysqli_error($conn));

        $_SESSION['comprato']=1; 
        header('Location: home.php');
        mysqli_free_result($res);
        mysqli_close($conn);
        exit;
    }else if (isset($_POST['Acquisto']) && ($_POST['Posti'] == 0)){
        $error = "Mi dispiace, per il concerto selezionato non ci sono posti disponibili";
    }
    
?>

<html>
    <head>
        <link rel="stylesheet" href="biglietti.css">
        <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Roboto+Slab">
        <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Source+Sans+Pro">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <script src="acquisto.js" defer="true"></script>
    </head>

    <body>
        <header>
            <h1>Find a Concert</h1>
            <div class="overlay"></div>
            <nav>
                <div id="link">
                    <a href="home.php">Home</a>
                    <?php
                        echo "<a href='$var2'>$var</a>";
                    ?>
                </div>
                <div id="menu">
                    <div></div>
                    <div></div>
                    <div></div>
                  </div>
            </nav>
        </header>

        <article>
            <section>
                <?php
                    if (isset($error)) {
                        echo "<span class='error'>$error</span>";
                    }
                ?>
                <div class="Artista">
                    <?php
                        echo "<h1 value='$NomeArtista'>$NomeArtista</h1>";
                    ?>
                </div>
            </section>
        </article>


        <footer>Umberto Mangione &nbsp Matricola: 100010249</footer>
    </body>
</html>