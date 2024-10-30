<html>
    <head>
        <link rel="stylesheet" href='{{ url("css/login.css") }}'>
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
                    <a href='{{ url("home") }}'>Home</a>
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
                <h1>Welcome</h1>
                @if (!empty(Session::get('error')))
                    <span class='error'>{{Session::get('error')}}</span>
                @endif
                <form class="login" name="login" method='post'>
                    <input type='hidden' name='_token' value='{{ csrf_token() }}'>
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
                    <input id='sub' type='submit' value="Signin">  
                </form>
                <div class="signup">Do you have an account? <a href='{{ url("login") }}'>Login</a>
            </section>
        </article>

        <footer>Umberto Mangione &nbsp Matricola: 100010249</footer>
    </body>
</html>