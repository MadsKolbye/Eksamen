const queryString = window.location.search;
const URLParams = new URLSearchParams(queryString);
const partyId = URLParams.get("partyId");

fetch("http://localhost:8080/parties/" + partyId)
    .then(response => response.json())
    .then(party => {
        console.log(party);
        document.getElementById("party-name").innerText = party.partyName;
        document.getElementById("party-chairman").innerText = party.chairmanName;
        document.getElementById("party-percentage").innerText = party.percentageOfVotes;

    })