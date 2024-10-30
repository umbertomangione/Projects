<html>
    <head>
        <link rel="stylesheet" href='{{ url("css/home.css") }}'>
        <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Roboto+Slab">
        <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Source+Sans+Pro">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <script src='{{ url("js/script.js") }}' defer></script>
    </head>

    <body>
        <header>
            <h1>Find a Concert</h1>
            <div class="overlay"></div>
            <nav>
                <div id="link">
                    @if(empty(Session::get('username')))
                        <a href='{{ url("login") }}'>Login/Signin</a>
                    @else
                        <a href='{{ url("logout") }}'>Logout</a>
                    @endif
                </div>
                <div id="menu">
                    <div></div>
                    <div></div>
                    <div></div>
                  </div>
            </nav>
        </header>
        
        <div id="ricerca">
            <input type="submit" id="sub" value="Search">
            <input type="search" id="input" placeholder="Find Concert" onkeyup="Ricerca()">
        </div>

        <article>
            <h1>All the Concerts</h1>
            <input id='token' type='hidden' value='{{ csrf_token() }}'>
        </article>

        <footer>Umberto Mangione &nbsp Matricola: 100010249</footer>
    </body>
</html>