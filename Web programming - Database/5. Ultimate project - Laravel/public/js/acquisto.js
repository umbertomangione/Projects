const artista = document.querySelectorAll("h1");

const key = 'djuvmmIJbXmyEIjSTqZB'
const secret = 'fCKfgUlFqbLiEzjmidRajHRgJqhxfLKI'
const img_end_point = 'https://api.discogs.com/database/search'

function InserisciImmagine(NomeArtista){

    const img_request = img_end_point + "?q=" + NomeArtista + "&key=" + key + "&secret=" + secret + "&per_page=1";
    fetch(img_request).then(onResponse).then(getImmagini)

    const immagine = document.createElement("img");
    return immagine;
}

function getImmagini(json)
{
    const risultati = json.results;
    let url;

    const artista = document.querySelector(".Artista");

    for(let result of risultati)
    {
        url = result.thumb;
        
        artista.lastChild.setAttribute("src", url)
    }
}

function onResponse(response) {
    return response.json();
}

function fetchConcerti(){
    fetch("DWconcerts").then(fetchResponse).then(fetchConcertiJson);
}

function fetchResponse(response){
    return response.json();
}

function fetchConcertiJson(json){
    const section = document.querySelector("section");

    for(let concerti of json)
    {
        const form = document.createElement("form");
        form.setAttribute("class", "Acquisto");
        form.setAttribute("name", "Acquisto");
        form.setAttribute("method", "post");
        section.appendChild(form);

        const concerto = document.createElement("div");
        concerto.setAttribute("class", "Biglietto");
        form.appendChild(concerto);

        const token = document.createElement("input");
        token.setAttribute("type", "hidden");
        token.setAttribute("name", "_token");
        token.setAttribute("value", document.querySelector("#token").value);
        concerto.appendChild(token);

        const input2 = document.createElement("input");
        input2.setAttribute("type", "hidden");
        input2.setAttribute("name", "ID");
        input2.setAttribute("value", concerti.idConcerto);
        concerto.appendChild(input2);

        const titolo = document.createElement("label");
        titolo.setAttribute("class","TitoloConcerto");
        titolo.textContent = concerti.NomeConcerto;
        concerto.appendChild(titolo);

        const prezzo = document.createElement("label");
        prezzo.setAttribute("class","Prezzo");
        prezzo.textContent = "Prezzo: "+concerti.Prezzo+".00 \u20AC";
        concerto.appendChild(prezzo);

        const input3 = document.createElement("input");
        input3.setAttribute("type", "hidden");
        input3.setAttribute("name", "Posti");
        input3.setAttribute("value", concerti.Posti);
        concerto.appendChild(input3);

        const posti = document.createElement("label");
        posti.setAttribute("class","Posti");
        posti.textContent = "Posti: "+concerti.Posti;
        concerto.appendChild(posti);

        const data = document.createElement("label");
        data.setAttribute("name", "Data");
        data.setAttribute("class","Data");
        const date = new Date(concerti.DataConcerto); 
        if((date.getMonth() +1) < 10){
            data.textContent = date.getDate()+"/"+"0"+(date.getMonth() + 1 )+"/"+date.getFullYear();
        }else{
            data.textContent = date.getDate()+"/"+(date.getMonth() + 1 )+"/"+date.getFullYear();
        }
        concerto.appendChild(data);

        const localita = document.createElement("label");
        localita.setAttribute("class","Localita");
        localita.textContent = concerti.location.NomeLocalita;
        concerto.appendChild(localita);

        const ind = document.createElement("div");
        ind.setAttribute("class", "ind");
        concerto.appendChild(ind);
        
        const cap = document.createElement("label");
        cap.setAttribute("class","CAP");
        cap.textContent = concerti.location.CAP;
        ind.appendChild(cap);

        const nazione = document.createElement("label");
        nazione.setAttribute("class", "Sigla");
        nazione.textContent = concerti.location.nation.NomeNazione;
        ind.appendChild(nazione);

        const input = document.createElement("input");
        input.setAttribute("id", "sub");
        input.setAttribute("type", "submit");
        input.setAttribute("name", "Acquisto");
        input.setAttribute("value", "Buy now");
        form.appendChild(input);
    }

    const h1 = document.createElement("h1");
    h1.setAttribute("class", "TitleTracks");
    h1.textContent = "Tracks";
    section.appendChild(h1);

    const div = document.createElement("div");
    div.setAttribute("class", "Tracks");
    section.appendChild(div);

    for(let tracks of json[0].artist.track){
        const tr = document.createElement("input");
        tr.setAttribute("class", "Track");
        tr.setAttribute("type", "hidden");
        tr.setAttribute("value", tracks.NomeBrano);
        div.appendChild(tr);

        Inseriscitrack(tracks.NomeBrano);
    }
}

function fetchCommenti(){
    fetch("DWcomments").then(fetchResponseComm).then(fetchCommentiJson);
}

function fetchResponseComm(response){
    return response.json();
}

function fetchCommentiJson(json){
    const article = document.querySelector("article");
    const artista = document.querySelectorAll("h1");

    const d = new Date(); 

    const div = document.createElement("div");
    div.setAttribute("class", "Comm");
    article.appendChild(div);

    const sezionecommenti = document.createElement("h1");
    sezionecommenti.textContent = "Comments Section";
    div.appendChild(sezionecommenti);
    
    const form = document.createElement("form");
    form.setAttribute("class", "inco");
    form.setAttribute("method", "post");
    form.addEventListener("submit", inserisci);
    div.appendChild(form);

    const inseriscicommento = document.createElement("div");
    inseriscicommento.setAttribute("class", "Inserisci");
    form.appendChild(inseriscicommento);

    const input = document.createElement("input");
    input.setAttribute("id", "inputC");
    input.setAttribute("type", "text");
    input.setAttribute("name", "cdi");
    input.setAttribute("placeholder", "Insert here your comment");
    inseriscicommento.appendChild(input);

    const input2 = document.createElement("input");
    input2.setAttribute("id", "Voto");
    input2.setAttribute("type", "text");
    input2.setAttribute("name", "Voto");
    input2.setAttribute("placeholder", "Vote (0.5 to 5.0)");
    inseriscicommento.appendChild(input2);

    const input3 = document.createElement("input");
    input3.setAttribute("type", "hidden");
    input3.setAttribute("name", "Data");
    input3.setAttribute("value", d.getFullYear()+"/"+(d.getMonth()+1)+"/"+d.getDate());
    inseriscicommento.appendChild(input3);

    const input4 = document.createElement("input");
    input4.setAttribute("type", "hidden");
    input4.setAttribute("name", "NomeA");
    input4.setAttribute("value", artista[1].innerText);
    inseriscicommento.appendChild(input4);

    const token = document.createElement("input");
    token.setAttribute("type", "hidden");
    token.setAttribute("name", "_token");
    token.setAttribute("value", document.querySelector("#token").value);
    inseriscicommento.appendChild(token);

    const invio = document.createElement("input");
    invio.setAttribute("id", "inviocommento");
    invio.setAttribute("type", "submit");
    invio.setAttribute("name", "inviocommento");
    invio.setAttribute("value", "Send Comment");
    inseriscicommento.appendChild(invio);

    for(let commenti of json)
    {
        NomeArtista = commenti.Art;
        if(NomeArtista == artista[1].innerText){

            const stelle = document.createElement("div");
            stelle.setAttribute("class", "Stelle");
            div.appendChild(stelle);

            for(i=0; i<commenti.Voto; i++){
                if((commenti.Voto - i) == 0.5){
                    const stella = document.createElement("img");
                    stella.setAttribute("class", "Stella2");
                    stella.setAttribute("src", "js/star2.png");
                    stelle.appendChild(stella);
                }else {
                    const stella = document.createElement("img");
                    stella.setAttribute("src", "js/star.png");
                    stelle.appendChild(stella);
                }
            }
            const commento = document.createElement("label");
            commento.setAttribute("class","Commento");
            commento.textContent = commenti.Commento;
            div.appendChild(commento);

            const data = document.createElement("label");
            data.setAttribute("class","Data");
            const date = new Date(commenti.DataCommento);
            if((date.getMonth() +1) < 10){
                data.textContent = date.getDate()+"/"+"0"+(date.getMonth() + 1 )+"/"+date.getFullYear();
            }else{
                data.textContent = date.getDate()+"/"+(date.getMonth() + 1 )+"/"+date.getFullYear();
            }
            div.appendChild(data);
        }
    }
}

const div = document.querySelector(".Artista");
const immagine = InserisciImmagine(artista[1].innerText);
div.appendChild(immagine);

async function now(){
    const formData = new FormData(document.querySelector(".inco"));
    await fetch("Upcomments", {method: 'post', body: formData});
    fetch("DWcomments").then(fetchResponsePU).then(fetchCommJson);
}

function inserisci(event){
    now();
    event.preventDefault();
}

function fetchResponsePU(response){
    return response.json();
}

function fetchCommJson(json){
    console.log(json);
    const div = document.querySelector(".Comm");
    for(let commenti of json)
    {
        NomeArtista = commenti.Art;
        if((NomeArtista == artista[1].innerText) && (commenti == (json[ Object.keys(json).sort().pop() ]))){
            const stelle = document.createElement("div");
            stelle.setAttribute("class", "Stelle");
            div.appendChild(stelle);

            for(i=0; i<commenti.Voto; i++){
                if((commenti.Voto - i) == 0.5){
                    const stella = document.createElement("img");
                    stella.setAttribute("class", "Stella2");
                    stella.setAttribute("src", "js/star2.png");
                    stelle.appendChild(stella);
                }else {
                    const stella = document.createElement("img");
                    stella.setAttribute("src", "js/star.png");
                    stelle.appendChild(stella);
                }
            }
            const commento = document.createElement("label");
            commento.setAttribute("class","Commento");
            commento.textContent = commenti.Commento;
            div.appendChild(commento);

            const data = document.createElement("label");
            data.setAttribute("class","Data");
            const date = new Date(commenti.DataCommento);
            if((date.getMonth() +1) < 10){
                data.textContent = date.getDate()+"/"+"0"+(date.getMonth() + 1 )+"/"+date.getFullYear();
            }else{
                data.textContent = date.getDate()+"/"+(date.getMonth() + 1 )+"/"+date.getFullYear();
            }
            div.appendChild(data);
        }
    }
}

const deezer_half = "https://boiling-fortress-65474.herokuapp.com/http://api.deezer.com/search?q=";

function Inseriscitrack(NomeBrano){
    const deezer = deezer_half + NomeBrano; 
    fetch(deezer).then(onResponseDeezer).then(getTrack);
}

function onResponseDeezer(response) {
    return response.json();
}

function getTrack(json)
{
    id = json.data[0].id;
    title = json.data[0].title_short;
    const tracks = document.querySelectorAll(".Track");
    const div = document.querySelector(".Tracks"); 
    for(let input of tracks){
        if(title.toLowerCase() == input.value.toLowerCase()){
            const div2 = document.createElement("div");
            div2.innerHTML = '<iframe title="deezer-widget" src="https://widget.deezer.com/widget/dark/track/'+id+'" width="150" height="150" frameborder="0" allowtransparency="true" allow="encrypted-media; clipboard-write"></iframe>';
            div.appendChild(div2);
        }
    }
}

fetchConcerti();
fetchCommenti();