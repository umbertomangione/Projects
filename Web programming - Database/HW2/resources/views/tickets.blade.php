<html>
    <head>
        <link rel="stylesheet" href='{{ url("css/biglietti.css") }}'>
        <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Roboto+Slab">
        <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Source+Sans+Pro">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <script src='{{ url("js/acquisto.js") }}' defer="true"></script>
    </head>

    <body>
        <header>
            <h1>Find a Concert</h1>
            <div class="overlay"></div>
            <nav>
                <div id="link">
                    <a href='{{ url("home") }}'>Home</a>
                    <a href='{{ url("logout") }}'>Logout</a>
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
                @if (!empty(Session::get('error')))
                    <span class='error'>{{Session::get('error')}}</span>
                @endif
                <div class="Artista">
                    <input id='token' type='hidden' value='{{ csrf_token() }}'>
                    <h1 value='{{Session::get("NomeArtista")}}'> {{Session::get('NomeArtista')}}</h1>
                </div>
            </section>
        </article>


        <footer>Umberto Mangione &nbsp Matricola: 100010249</footer>
    </body>
</html>