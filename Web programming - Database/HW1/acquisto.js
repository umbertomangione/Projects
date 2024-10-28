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
    fetch("concerti.php").then(fetchResponse).then(fetchConcertiJson);
}

function fetchResponse(response){
    return response.json();
}

function fetchConcertiJson(json){
    const section = document.querySelector("section");
    const artista = document.querySelectorAll("h1");
    for(let concerti of json)
    {
        NomeArtista = concerti.NomeArtista;
        if(NomeArtista == artista[1].innerText){
            
            const form = document.createElement("form");
            form.setAttribute("class", "Acquisto");
            form.setAttribute("name", "Acquisto");
            form.setAttribute("method", "post");
            section.appendChild(form);

            const concerto = document.createElement("div");
            concerto.setAttribute("class", "Biglietto");
            form.appendChild(concerto);

            const input2 = document.createElement("input");
            input2.setAttribute("type", "hidden");
            input2.setAttribute("name", "ID");
            input2.setAttribute("value", concerti.idConcerto);
            concerto.appendChild(input2);

            const titolo = document.createElement("label");
            titolo.setAttribute("class","TitoloConcerto");
            titolo.textContent = concerti.NomeCon;
            concerto.appendChild(titolo);

            const prezzo = document.createElement("label");
            prezzo.setAttribute("class","Prezzo");
            prezzo.textContent = "Prezzo: "+concerti.Prezzo+" \u20AC";
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
            data.textContent = concerti.Data;
            concerto.appendChild(data);

            const localita = document.createElement("label");
            localita.setAttribute("class","Localita");
            localita.textContent = concerti.Localita;
            concerto.appendChild(localita);

            const cap = document.createElement("label");
            cap.setAttribute("class","CAP");
            cap.textContent = concerti.CAP;
            concerto.appendChild(cap);

            const input = document.createElement("input");
            input.setAttribute("id", "sub");
            input.setAttribute("type", "submit");
            input.setAttribute("name", "Acquisto");
            input.setAttribute("value", "Acquista Ora");
            form.appendChild(input);
        }
    }
}

function fetchCommenti(){
    fetch("commenti.php").then(fetchResponseComm).then(fetchCommentiJson);
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
    sezionecommenti.textContent = "Sezione Commenti";
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
    input.setAttribute("placeholder", "Inserisci qui il tuo commento");
    inseriscicommento.appendChild(input);

    const input2 = document.createElement("input");
    input2.setAttribute("id", "Voto");
    input2.setAttribute("type", "text");
    input2.setAttribute("name", "Voto");
    input2.setAttribute("placeholder", "Voto (0.5 a 5.0)");
    inseriscicommento.appendChild(input2);

    const input3 = document.createElement("input");
    input3.setAttribute("type", "hidden");
    input3.setAttribute("name", "Data");
    input3.setAttribute("value", d.getFullYear()+"/"+(d.getMonth()+1)+"/"+d.getDate());
    inseriscicommento.appendChild(input3);

    const invio = document.createElement("input");
    invio.setAttribute("id", "inviocommento");
    invio.setAttribute("type", "submit");
    invio.setAttribute("name", "inviocommento");
    invio.setAttribute("value", "Inserisci Commento");
    inseriscicommento.appendChild(invio);

    for(let commenti of json)
    {
        NomeArtista = commenti.Artista;
        if(NomeArtista == artista[1].innerText){

            const stelle = document.createElement("div");
            stelle.setAttribute("class", "Stelle");
            div.appendChild(stelle);

            for(i=0; i<commenti.Voto; i++){
                if((commenti.Voto - i) == 0.5){
                    const stella = document.createElement("img");
                    stella.setAttribute("class", "Stella2");
                    stella.setAttribute("src", "star2.png");
                    stelle.appendChild(stella);
                }else {
                    const stella = document.createElement("img");
                    stella.setAttribute("src", "star.png");
                    stelle.appendChild(stella);
                }
            }
            const commento = document.createElement("label");
            commento.setAttribute("class","Commento");
            commento.textContent = commenti.Commento;
            div.appendChild(commento);

            const data = document.createElement("label");
            data.setAttribute("class","Data");
            data.textContent = commenti.Data;
            div.appendChild(data);
        }
    }
}

const div = document.querySelector(".Artista");
const immagine = InserisciImmagine(artista[1].innerText);
div.appendChild(immagine);

function inserisci(event){
    const formData = new FormData(document.querySelector(".inco"));
    fetch("inserirecomm.php", {method: 'post', body: formData});
    
    fetch("commenti.php").then(fetchResponseComm).then(fetchCommJson);
    
    event.preventDefault();
}

function fetchCommJson(json){
    console.log(json[ Object.keys(json).sort().pop() ]);
    const div = document.querySelector(".Comm");
    for(let commenti of json)
    {
        NomeArtista = commenti.Artista;
        if((NomeArtista == artista[1].innerText) && (commenti == (json[ Object.keys(json).sort().pop() ]))){

            const stelle = document.createElement("div");
            stelle.setAttribute("class", "Stelle");
            div.appendChild(stelle);

            for(i=0; i<commenti.Voto; i++){
                if((commenti.Voto - i) == 0.5){
                    const stella = document.createElement("img");
                    stella.setAttribute("class", "Stella2");
                    stella.setAttribute("src", "star2.png");
                    stelle.appendChild(stella);
                }else {
                    const stella = document.createElement("img");
                    stella.setAttribute("src", "star.png");
                    stelle.appendChild(stella);
                }
            }
            const commento = document.createElement("label");
            commento.setAttribute("class","Commento");
            commento.textContent = commenti.Commento;
            div.appendChild(commento);

            const data = document.createElement("label");
            data.setAttribute("class","Data");
            data.textContent = commenti.Data;
            div.appendChild(data);
        }
    }
}

fetchConcerti();
fetchCommenti();