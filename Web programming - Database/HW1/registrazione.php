<?php
    include 'auth.php';
    if (checkAuth()) {
        header('Location: home.php');
        exit;
    }else{
        echo '<script language="javascript">';
        echo 'alert("Per favore registrati per poter acquistare i biglietti")';
        echo '</script>';
    }

    if (!empty($_POST["username"]) && !empty($_POST["email"]) && !empty($_POST["password"]) )
    {
        $conn = mysqli_connect($dbconfig['host'], $dbconfig['user'], $dbconfig['password'], $dbconfig['name']) or die(mysqli_error($conn));
        
        $username = mysqli_real_escape_string($conn, $_POST['username']);
        $email = mysqli_real_escape_string($conn, $_POST['email']);
        $pass = mysqli_real_escape_string($conn, $_POST['password']);
        $password = base64_encode(hash("sha256", $pass, True));
        
        $query = "SELECT username, email FROM utenti WHERE username = '$username' OR email = '$email'";
        $res = mysqli_query($conn, $query) or die(mysqli_error($conn));;
        if (mysqli_num_rows($res) > 0) {
            $error = "Username o Email già esistenti.";
        }else  if (strlen($_POST["password"]) < 6) {
            $error = "Lunghezza password insufficiente almeno di 6 caratteri";
        } else if (mysqli_num_rows($res) == 0){
            $query = "INSERT INTO `utenti`(`username`, `password`, `email`) VALUES ('$username','$password','$email')";
            $res = mysqli_query($conn, $query) or die(mysqli_error($conn));
            header("Location: login.php");
            mysqli_free_result($res);
            mysqli_close($conn);
            exit;
        }
    }else if (isset($_POST["username"]) || isset($_POST["email"]) || isset($_POST["password"])) {
        $error = "Campo/i non riempito/i.";
    }
?>

<html>
    <head>
        <link rel="stylesheet" href="login.css">
        <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Roboto+Slab">
        <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Source+Sans+Pro">
        <meta name="viewport" content="width=device-width, initial-scale=1">
    </head>

    <body>
        <header>
            <h1>Find a Concert</h1>
            <div class="overlay"></div>
            <nav>
                <div id="link">
                    <a href="home.php">Home</a>
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
                <h1>Bentornato</h1>
                <?php
                    if (isset($error)) {
                        echo "<span class='error'>$error</span>";
                    }
                ?>
                <form class="login" name="login" method='post'>
                    <div class='username'>
                        <div><label for='username'>Username</label></div>
                        <div><input type='text' name='username' ></div>
                    </div>
                    <div class='email'>
                        <div><label for='email'>Email</label></div>
                        <div><input type='text' name='email' ></div>
                    </div>
                    <div class='password'>
                        <div><label for='password'>Password</label></div>
                        <div><input type='password' name='password' ></div>
                    </div>
                    <input id='sub' type='submit' value="Iscriviti">  
                </form>
                <div class="signup">Sei già registrato? <a href="login.php">Fai il Login</a>
            </section>
        </article>

        <footer>Umberto Mangione &nbsp Matricola: 100010249</footer>
    </body>
</html>