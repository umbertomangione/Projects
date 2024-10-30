<?php 
    require_once 'auth.php';
    if (!$userid = checkAuth()) {
        $var = "Login/Registrati";
        $var2 = "login.php";
    }else{
        $var = "Logout";
        $var2 = "logout.php";
    }

    if(comprato()){
        echo '<script language="javascript">';
        echo 'alert("Grazie per aver acquistato da noi")';
        echo '</script>';
        unset($_SESSION['comprato']);
    }

    if(isset($_POST['submit'])){
        $_SESSION['artista'] = $_POST['artista'];
        header('Location: biglietti.php');
    }
?>

<html>
    <head>
        <link rel="stylesheet" href="home.css">
        <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Roboto+Slab">
        <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Source+Sans+Pro">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <script src="contents.js" defer="true"></script>
        <script src="script.js" defer="true"></script>
    </head>

    <body>
        <header>
            <h1>Find a Concert</h1>
            <div class="overlay"></div>
            <nav>
                <div id="link">
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
        
        <div id="ricerca">
            <input type="submit" id="sub" value="Cerca">
            <input type="search" id="input" placeholder="Find Concert" onkeyup="Ricerca()">
        </div>

        <article>
            <h1>Tutti i Concerti</h1>
        </article>

        <footer>Umberto Mangione &nbsp Matricola: 100010249</footer>
    </body>
</html>