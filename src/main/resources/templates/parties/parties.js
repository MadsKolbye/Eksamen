const partyDiv = document.getElementById("party-div");

fetch("http://localhost:8080/parties")
    .then(response => response.json())
    .then(parties => {
        console.log(parties);
        parties.map(createPartyCard);
    });

function createPartyCard(party){
    const partyElement = document.createElement("div");
    partyElement.innerHTML = `
        <div style="text-align: center">
            <a id = "party-name" style="font-size: 50px; left: 50%; top: 50%; ">${party.percentageOfVotes} </a>
            <br>
            <a href="party.html?partyId=${party.partyId}">
                <img class="party-logo"src="${party.partyImage}">
            </a>
            <br>
            <br>
            <br>
            <br>
            <br>
        </div>
    `

    partyDiv.appendChild(partyElement);
}