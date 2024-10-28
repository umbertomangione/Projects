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
//Funzione utilizzata per inserire un concerto tra i preferiti 
function InserisciPreferiti (event){
    const stella = event.currentTarget;
    const sezione = stella.parentElement.parentElement.parentElement;
    const copia = sezione.cloneNode(true);
    const article = sezione.parentElement;
    article.children.preferiti.classList.remove("hide");
    article.children.preferiti.appendChild(copia);

    stella.removeEventListener("click", InserisciPreferiti);
    copia.firstChild.lastChild.lastChild.addEventListener("click", EliminaPreferiti);
}
//Funzione utilizzata per eliminare un concerto dai preferiti
function EliminaPreferiti (event){
    const stella = event.currentTarget;
    const preferiti = stella.parentElement;
    const section = preferiti.parentElement.parentElement;
    const preferitiV = preferiti.parentElement.parentElement.parentElement; 
    const article = preferiti.parentElement.parentElement.parentElement.parentElement;
    
    preferiti.parentElement.parentElement.remove();
    const Tsezioni = article.querySelectorAll("section");
    for(const n of Tsezioni)
    {
        if(n.id == section.id){
            n.firstChild.lastChild.lastChild.addEventListener("click", InserisciPreferiti);
        }
    }
    
    if(preferitiV.firstChild == preferitiV.lastChild){
        preferitiV.classList.add("hide");
    }
    
    stella.removeEventListener("click", EliminaPreferiti);
}
//Funzione utilizzata per far apparire la descrizione, dopo aver premuto su "Dettagli" 
function AggDescrizione (event){
    const PiuDettagli = event.currentTarget;
    PiuDettagli.parentElement.children.Descrizione.classList.remove("hide");
    
    PiuDettagli.textContent = "Meno Dettagli";
    PiuDettagli.removeEventListener("click", AggDescrizione);
    PiuDettagli.addEventListener("click", DelDescrizione);
}
//Funzione utilizzata per far scomparire la descrizione, dopo aver premuto su "Meno Dettagli"
function DelDescrizione (event){
    const MenoDettagli = event.currentTarget;
    MenoDettagli.parentElement.children.Descrizione.classList.add("hide");

    MenoDettagli.textContent = "Dettagli";
    MenoDettagli.removeEventListener("click", DelDescrizione);
    MenoDettagli.addEventListener("click", AggDescrizione);
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
    descrizione.setAttribute("class", "hide");
    descrizione.textContent = Testo;
    return descrizione;
}

const key = 'djuvmmIJbXmyEIjSTqZB'
const secret = 'fCKfgUlFqbLiEzjmidRajHRgJqhxfLKI'
const img_end_point = 'https://api.discogs.com/database/search'

const news_key = '6cdc6582-53cf-4497-b09e-4874c6e19829'
const news_end_point = 'https://content.guardianapis.com/search'


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

function getNews(json)
{
    const risultati = json.response.results;
    let url,title;
    const SpazioNews = document.querySelector("#news")
    
    for(let result of risultati)
    {
        title = result.webTitle;
        url = result.webUrl;
        const news = document.createElement("a");
        news.setAttribute("href", url);
        news.textContent = title;
        SpazioNews.appendChild(news);
    }
}

function InserisciNews(NomeArtista){
    const new_request = news_end_point + '?q=' + NomeArtista + '&section=music&from-date=2014-01-01&page-size=1&api-key=' + news_key;
    fetch(new_request).then(Response).then(getNews);
}

//Tutto questo ciclo FOR serve per inserire tutti i dati che sono all'interno del file "contents.js" all'interno della pagina
const article = document.querySelector("article");
for (let i=0; i<ContenutoPagina.length; i++){
    const NomeArtista = ContenutoPagina[i].titolo;              //Si prende il nome dell'artista dal file "contents.js"
    //const srcimmagine = ContenutoPagina[i].immagine;            //Si prende l'src dell'immagine dal file "contents.js"
    const descrizione = ContenutoPagina[i].descrizione;         //Si prende la descrizione del concerto dal file "contents.js"

    const sezione = document.createElement("section");          //Si crea la sezione al cui interno andrà un altro div 
    sezione.setAttribute("id", NomeArtista)
    article.appendChild(sezione);                               

    const contenuto = document.createElement("div");            //Si crea il div al cui interno andranno tutti i concerti
    contenuto.setAttribute("class", "contenuto");
    sezione.appendChild(contenuto);
    
    const immagine = InserisciImmagine(NomeArtista);            
    contenuto.appendChild(immagine);

    const div = document.createElement("div");                  //Si crea un div riguardante la descrizione
    div.setAttribute("class", "Descrizione");
    contenuto.appendChild(div);

    const artista = InserisciNomeArtista(NomeArtista);
    div.appendChild(artista);
    
    const desc = InserisciDescrizione(descrizione);
    div.appendChild(desc);
    
    const dettagli = document.createElement("div");             //Si crea un div riguardante i dettagli
    dettagli.setAttribute("id", "Dettagli");
    dettagli.textContent = "Dettagli";
    dettagli.addEventListener("click", AggDescrizione);         //al quale si aggiunge un event listener
    div.appendChild(dettagli);

    const biglietti = document.createElement("a");              //Si crea un elemento che serve per l'acquisto del biglietto
    biglietti.setAttribute("class", "Biglietti");
    biglietti.textContent = "Biglietti";
    div.appendChild(biglietti);

    const preferiti = document.createElement("img");            //Si crea un elemento, ovvero la stella che servirà per aggiungere o togliere un concerto nei/dai preferiti
    preferiti.src = "preferiti.png";
    preferiti.setAttribute("id", "Stella");
    preferiti.addEventListener("click", InserisciPreferiti);    //al quale si aggiunge un event listener
    div.appendChild(preferiti);
}
for (let i=0; i<ContenutoPagina.length; i++){
    InserisciNews(ContenutoPagina[i].titolo);
}