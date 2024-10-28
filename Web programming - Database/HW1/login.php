<?php
    include 'auth.php';
    if (checkAuth()) {
        header('Location: home.php');
        exit;
    }

    if (!empty($_POST["username"]) && !empty($_POST["password"]) )
    {
        $conn = mysqli_connect($dbconfig['host'], $dbconfig['user'], $dbconfig['password'], $dbconfig['name']) or die(mysqli_error($conn));

        $username = mysqli_real_escape_string($conn, $_POST['username']);
        $pass = mysqli_real_escape_string($conn, $_POST['password']);
        $password = base64_encode(hash("sha256", $pass, True)); 
        
        $searchField = filter_var($username, FILTER_VALIDATE_EMAIL) ? "email" : "username";
        
        $query = "SELECT username, password FROM utenti WHERE $searchField = '$username'";
        
        $res = mysqli_query($conn, $query) or die(mysqli_error($conn));;
        if (mysqli_num_rows($res) > 0) {
            
            $entry = mysqli_fetch_assoc($res);
            if ($password == $entry['password']) {
                $_SESSION["username"] = $entry['username'];
                header("Location: home.php");
                mysqli_free_result($res);
                mysqli_close($conn);
                exit;
            }
        }
        $error = "Username e/o password errati.";
    }
    else if (isset($_POST["username"]) || isset($_POST["password"])) {
        $error = "Inserisci username e password.";
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
                <h1>Benvenuto</h1>
                <?php
                    if (isset($error)) {
                        echo "<span class='error'>$error</span>";
                    }
                ?>
                <form class="login" name="login" method='post'>
                    <div class='username'>
                        <div><label for='username'>Nome utente o email</label></div>
                        <div><input type='text' name='username'></div>
                    </div>
                    <div class='password'>
                        <div><label for='password'>Password</label></div>
                        <div><input type='password' name='password'></div>
                    </div>
                    <input id='sub' type='submit' value="Accedi">  
                </form>
                <div class="signup">Non hai un account? <a href="registrazione.php">Iscriviti</a>
            </section>
        </article>

        <footer>Umberto Mangione &nbsp Matricola: 100010249</footer>
    </body>
</html>