const candidateListHtmlElement = document.getElementById("candidate-list-div");
let filteredCandidates;

fetch("http://localhost:8080/candidates")
    .then(response => response.json())
    .then(candidates => {
        console.log(candidates);
        candidates.map(addCandidateToList);
        filteredCandidates = candidates;
    })

function addCandidateToList(candidate){
    console.log(candidate);
    const candidateTableRow = document.createElement("tr");
    candidateTableRow.id = "candidate-tablerow-element-" + candidate.candidateId;
    candidateListHtmlElement.appendChild(candidateTableRow);
    constructCandidateRow(candidateTableRow, candidate);
}

function constructCandidateRow(candidateTableRow, candidate){
    candidateTableRow.innerHTML = `
        <tr style="text-align: center">
            <td>
                <p>Navn: ${escapeHTML(candidate.candidateName)}</p>
                <p>Parti: ${escapeHTML(candidate.candidateParty)}</p>    
                <p>Antal stemmer: ${candidate.amountOfVotes}</p> 
                &nbsp;
                &nbsp;
                       
            </td>   
            <td>
                <button id="update-candidate-button-${candidate.candidateId}">🔄</button>
                <button onclick="deleteCandidate(${candidate.candidateId})">❌</button>
            </td>
        </tr>
    `
    document.getElementById("update-candidate-button-"+candidate.candidateId)
        .addEventListener("click", () => editCandidate(candidate));
}

function editCandidate(candidate){
    console.log("Edit clicked with id: "+candidate.candidateId)
    console.log(document.getElementById("candidate-tablerow-element-" + candidate.candidateId))
    const candidateTableRowToUpdate = document.getElementById("candidate-tablerow-element-" + candidate.candidateId);
    console.log(candidateTableRowToUpdate)
    candidateTableRowToUpdate.innerHTML = `
        <tr>
                <td>
                    <input id="update-candidate-name-${candidate.candidateId}" value="${candidate.candidateName}">
                    <button onclick="updateCandidateInBackend(${candidate.candidateId})">✔</button>
                </td>
        </tr>
    `;
}

function updateCandidateInBackend(candidateId){

    const candidateTableRowToUpdate = document.getElementById("candidate-tablerow-element-" + candidateId);

    const candidateToUpdate = {
        candidateId: candidateId,
        candidateName: document.getElementById(`update-candidate-name-` + candidateId).value
    }

    fetch("http://localhost:8080/candidates/" + candidateId,  {
        method:"PATCH",
        headers: {"Content-type": "application/json; charset=UTF-8"},
        body: JSON.stringify(candidateToUpdate)
    }).then(response => {
        if(response.status === 200){
            addCandidateToList(candidateTableRowToUpdate, candidateToUpdate);
        }
    })
}

function createNewCandidate(){

    const name = document.getElementById("create-candidate-name").value;
    const party = document.getElementById("create-candidate-party").value;
    const votes = document.getElementById("create-candidate-votes").value;

    const newCandidate ={
        candidateName: name,
        candidateParty: party,
        amountOfVotes: votes
    };

    fetch("http://localhost:8080/candidates", {
        method: "POST",
        headers: {
            "Content-type": "application/json; charset=UTF-8"
        },
        body: JSON.stringify(newCandidate)
    }).then(response => {
        if (response.status === 200) {
            addCandidateToList(newCandidate);
        } else {
            console.log("Candidate not created", response.status)
        }
    })
        .catch(error => console.log("Netværksfejl...", error))
}

function deleteCandidate(candidateId){
    fetch("http://localhost:8080/candidates/" + candidateId, {
        method: "DELETE"
    }).then(response => {
        if (response.status === 200) {
            document.getElementById("candidate-tablerow-element-" + candidateId).remove();
        } else {
            console.log(response.status);
        }
    })
}

function searchCandidateParty(){
    candidateListHtmlElement.innerHTML = "";
    const searchTerm = event.target.value.toLowerCase();
    filteredCandidates.filter(candidate => candidate.candidateParty.toLowerCase().includes(searchTerm)).map(addCandidateToList);
}
document.getElementById("create-candidate-button").addEventListener("click", createNewCandidate);
document.getElementById("candidate-search").addEventListener("input", searchCandidateParty);