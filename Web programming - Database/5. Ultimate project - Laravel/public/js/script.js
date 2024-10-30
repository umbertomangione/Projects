//Funzione utilizzata per filtrare i risultati, dopo aver scritto qualcosa nella barra di ricerca
function Ricerca(){
    const input = document.getElementById("input");
    const maiuscolo = input.value.toUpperCase();
    const article = document.querySelector("article");
    const Nsezioni = article.getElementsByTagName("section");
    for (let i=0; i<Nsezioni.length; i++){
        const nome = Nsezioni[i].id;
        if (nome.toUpperCase().indexOf(maiuscolo) > -1){
            Nsezioni[i].style.display = "";
        } else {
            Nsezioni[i].style.display = "none";
        }
    }
}

//Funzione utilizzata per la creazione di un elemento riguardante l'immagine
function InserisciImmagine(NomeArtista){

    const img_request = img_end_point + "?q=" + NomeArtista + "&key=" + key + "&secret=" + secret + "&per_page=1";
    fetch(img_request).then(onResponse).then(getImmagini)

    const immagine = document.createElement("img");
    return immagine;
}

//Funzione utilizzata per la creazione di un elemento riguardante il nome dell'artista
function InserisciNomeArtista(Nome){
    const NomeArtista = document.createElement("div");
    NomeArtista.setAttribute("id", "Artista");
    NomeArtista.textContent = Nome;
    return NomeArtista;
}

//Funzione utilizzata per la creazione di un elemento riguardante la descrizione 
function InserisciDescrizione(Testo){
    const descrizione = document.createElement("div");
    descrizione.setAttribute("id", "Descrizione");
    descrizione.textContent = Testo;
    return descrizione;
}

const key = 'djuvmmIJbXmyEIjSTqZB'
const secret = 'fCKfgUlFqbLiEzjmidRajHRgJqhxfLKI'
const img_end_point = 'https://api.discogs.com/database/search'

function getImmagini(json)
{
    const risultati = json.results;
    let url,title;

    for(let result of risultati)
    {
        title = result.title;
        url = result.thumb;
        const Tartisti = document.querySelectorAll("section")
        for(art of Tartisti){
            if(title == art.id)
            art.firstChild.firstChild.setAttribute("src", url)
        }
    }
}

function onResponse(response) {
    return response.json();
}

function Response(response) {
    return response.json();
}

function fetchArtisti(){
    fetch("artists").then(fetchResponse).then(fetchArtistiJson);
}

function fetchResponse(response){
    return response.json();
}

function fetchArtistiJson(json){
    const article = document.querySelector("article");
    for(let artisti of json)
    {
        NomeArtista = artisti.NomeArtista;
        Genere = artisti.Genere;

        const form = document.createElement("form");
        form.setAttribute("method", "post");
        article.appendChild(form);

        const token = document.createElement("input");
        token.setAttribute("type", "hidden");
        token.setAttribute("name", "_token");
        token.setAttribute("value", document.querySelector("#token").value);
        form.appendChild(token);

        const sezione = document.createElement("section");          
        sezione.setAttribute("id", NomeArtista);
        form.appendChild(sezione);                               

        const contenuto = document.createElement("div");            
        contenuto.setAttribute("class", "contenuto");
        sezione.appendChild(contenuto);
        
        const immagine = InserisciImmagine(NomeArtista);            
        contenuto.appendChild(immagine);

        const div = document.createElement("div");                  
        div.setAttribute("class", "Descrizione");
        contenuto.appendChild(div);

        const artista = InserisciNomeArtista(NomeArtista);
        div.appendChild(artista);
        
        const desc = InserisciDescrizione(Genere);
        div.appendChild(desc);

        const input = document.createElement("input");
        input.setAttribute("type", "hidden");
        input.setAttribute("name", "artista");
        input.setAttribute("value", NomeArtista);
        div.appendChild(input);
        
        const biglietti = document.createElement("input");               
        biglietti.setAttribute("class", "Biglietti");
        biglietti.setAttribute("type", "submit");
        biglietti.setAttribute("value", "Tickets");
        biglietti.setAttribute("name", "submit");
        div.appendChild(biglietti);
    }
}

fetchArtisti();