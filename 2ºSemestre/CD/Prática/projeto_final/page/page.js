const allowedMimeTypes = ['audio/mp3', 'audio/wav', 'audio/ogg', 'audio/mpeg'];

const soundArchiveInput = document.getElementById('sound-archive');

let currentMusicId = null;

const resetButton = document.getElementById('reset-button');
resetButton.addEventListener('click', reset);

const instrumentData = [
    { "track_id": 1, "name": "drums" },
    { "track_id": 2, "name": "bass" },
    { "track_id": 3, "name": "vocals" },
    { "track_id": 4, "name": "other" }
];

soundArchiveInput.addEventListener('change', (event) => {
    const file = event.target.files[0];
    const fileType = file.type;

    // Validate the file type
    if (allowedMimeTypes.includes(fileType)) {
        // Process the selected file, e.g., upload to a server
        callAPI();
    } else {
        console.error('Invalid file type:', fileType);
    }
});

function callAPI() {
    const fileInput = document.getElementById('sound-archive');
    const file = fileInput.files[0];
    const apiUrl = 'http://localhost:5000/music';
    const formData = new FormData();
    formData.append('file', file);

    for (const pair of formData.entries()) {
        console.log(pair[0] + ', ' + pair[1]);
    }

    fetch(apiUrl, {
        method: 'POST',
        body: formData,
    })
        .then((response) => {
            if (!response.ok) {
                throw new Error('Network response was not ok');
            }
            return response.json();
        })
        .then((data) => {
            console.log('API response:', data);

            // Process and display the music data
            displayMusicData(data);
        })
        .catch((error) => {
            console.error('There was a problem with the fetch operation:', error);
        })
}

async function fetchIndividualTrack(instrumentName) {
    const trackUrl = `http://localhost:5000/music/${currentMusicId}_processed_music_final/${instrumentName}.wav`;
    console.log('Instrument track URL:', trackUrl);

    const response = await fetch(trackUrl);

    if (!response.ok) {
        throw new Error('Network response was not ok');
    }

    const blob = await response.blob();
    const audioUrl = URL.createObjectURL(blob);

    // Create an audio element with the fetched instrument track data
    const audio = new Audio();
    audio.src = audioUrl;
    audio.controls = true;

    const container = document.createElement('div');
    const trackText = document.createElement('p');
    trackText.textContent = `Instrument ${instrumentName}`;
    container.appendChild(trackText); // Append the paragraph to the container
    container.appendChild(audio);

    return container;
}


function processMusic() {
    if (currentMusicId === null) {
        console.error('No music_id is set. Please upload a sound archive first.');
        return;
    }

    const selectedInstruments = getSelectedInstruments();
    const apiUrl = `http://localhost:5000/music/${currentMusicId}`;

    fetch(apiUrl, {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify({ instruments: selectedInstruments })
    })

    const musicUrl = `http://localhost:5000/music/${currentMusicId}_processed_music_final/final_output.wav`;
    console.log('Music URL:', musicUrl);
    const fetchFinalMusic = async () => {
        const finalMusicUrl = `http://localhost:5000/music/${currentMusicId}_processed_music_final/final_output.wav`;
        console.log('Final Music URL:', finalMusicUrl);

        while (true) {
            const response = await fetch(finalMusicUrl);

            if (response.status === 404) {
                console.log('Retrying...');
                await new Promise((resolve) => setTimeout(resolve, 10000)); // Wait for 1 second before retrying
            } else if (!response.ok) {
                throw new Error('Network response was not ok');
            } else {
                const blob = await response.blob();
                const audioUrl = URL.createObjectURL(blob);

                // Create an audio element with the fetched final music data
                const audio = new Audio();
                audio.src = audioUrl;
                audio.controls = true;

                const container = document.createElement('div');
                const finalMusicText = document.createElement('p');
                finalMusicText.textContent = 'Final Music';
                container.appendChild(finalMusicText); // Append the paragraph to the container
                container.appendChild(audio);

                return container;
            }
        }
    };

    const fetchMusic = async () => {
        const musicList = document.getElementById('music-list');
        musicList.innerHTML = ''; // Clear the previous music list

        try {
            const finalMusicContainer = await fetchFinalMusic();
            musicList.appendChild(finalMusicContainer);

            // Add await statement here to ensure individual instrument tracks are fetched after final music
            await new Promise((resolve) => setTimeout(resolve, 100));

            for (const instrumentName of selectedInstruments) {
                const trackContainer = await fetchIndividualTrack(instrumentName);
                musicList.appendChild(trackContainer);
            }
        } catch (error) {
            console.error('Error fetching final music:', error);
        }
    };
    fetchMusic(); // Start fetching the final music and individual instrument tracks
}



function reset() {
    const apiUrl = 'http://localhost:5000/reset';

    fetch(apiUrl, {
        method: 'POST',
    })
    console.log('Reset successful');
    location.reload(); // Refresh the page
}


function displayMusicData(data) {
    const musicDataDiv = document.getElementById('music-data');
    musicDataDiv.innerHTML = ''; // Clear any previous content

    // Create a card for the music item
    const card = document.createElement('div');
    card.classList.add('card', 'mb-3');
    card.dataset.musicId = data.music_id;

    const cardBody = document.createElement('div');
    cardBody.classList.add('card-body');

    const cardTitle = document.createElement('h5');
    cardTitle.classList.add('card-title');
    cardTitle.textContent = `${data.band} - ${data.name}`;
    cardBody.appendChild(cardTitle);

    const tracksList = document.createElement('ul');
    data.tracks.forEach((track) => {
        const trackItem = document.createElement('li');
        trackItem.textContent = `${track.track_id}: ${track.name}`;
        tracksList.appendChild(trackItem);
    });
    cardBody.appendChild(tracksList);

    // Crie o elemento div de loading
    const loadingDiv = document.createElement('div');
    loadingDiv.classList.add('loading');
    loadingDiv.textContent = 'Processing Music';
    loadingDiv.style.display = 'none';
    cardBody.appendChild(loadingDiv);


    const instrumentsCheckboxesDiv = document.createElement('div');
    instrumentData.forEach((instrument) => {
        const checkboxWrapper = document.createElement('div');
        checkboxWrapper.classList.add('form-check', 'form-check-inline');

        const checkbox = document.createElement('input');
        checkbox.classList.add('form-check-input');
        checkbox.type = 'checkbox';
        checkbox.id = `instrument-${instrument.track_id}`;
        checkbox.value = instrument.track_id;
        checkboxWrapper.appendChild(checkbox);

        const label = document.createElement('label');
        label.classList.add('form-check-label');
        label.htmlFor = `instrument-${instrument.track_id}`;
        label.textContent = instrument.name;
        checkboxWrapper.appendChild(label);

        instrumentsCheckboxesDiv.appendChild(checkboxWrapper);
    });
    cardBody.appendChild(instrumentsCheckboxesDiv);
    const processingContainer = document.createElement('div');
    processingContainer.classList.add('d-flex', 'justify-content-between', 'align-items-center', 'mt-3');
    cardBody.appendChild(processingContainer);


    // Adicione o botão "Process Music"
    const processMusicButton = document.createElement('button');
    processMusicButton.classList.add('btn', 'btn-primary');
    processMusicButton.textContent = 'Process Music';
    processMusicButton.onclick = () => {
        processMusic();
    };
    processMusicButton.dataset.musicId = data.music_id;
    cardBody.appendChild(processMusicButton);

    // Add the music-list div inside the card-body div
    const musicList = document.createElement('div');
    musicList.id = 'music-list';
    musicList.classList.add('mt-3');
    cardBody.appendChild(musicList);

    // Create a loading element
    const loadingElement = document.createElement('div');
    loadingElement.id = 'loading-element';
    loadingElement.textContent = 'Loading...';
    loadingElement.style.display = 'none';
    cardBody.appendChild(loadingElement);

    card.appendChild(cardBody);
    musicDataDiv.appendChild(card);
    currentMusicId = data.music_id;
}


function getSelectedInstruments() {
    const selectedInstruments = [];
    instrumentData.forEach((instrument) => {
        const checkbox = document.getElementById(`instrument-${instrument.track_id}`);
        if (checkbox.checked) {
            selectedInstruments.push(instrument.name);
        }
    });
    return selectedInstruments;
}

document.getElementById("listJobsButton").addEventListener("click", async () => {
    const response = await fetch("http://localhost:5000/jobs");
    const data = await response.json();
    const jobsIds = data.job_ids;
  
    const jobsListElement = document.getElementById("jobsList");
    jobsListElement.innerHTML = "";
  
    jobsIds.forEach((jobId) => {
        const jobContainer = document.createElement("div");
      
        const jobIdElement = document.createElement("p");
        jobIdElement.textContent = `Job ID: ${jobId}`;
        jobContainer.appendChild(jobIdElement);
      
        const getInfoButton = document.createElement("button");
        getInfoButton.textContent = "Get Job Information";
        getInfoButton.classList.add("btn", "btn-info");
        getInfoButton.addEventListener("click", async () => {
            const jobInfoResponse = await fetch(`http://localhost:5000/jobs/${jobId}`);
            const jobInfo = await jobInfoResponse.json();
            displayJobInfo(jobInfo);
        });
        jobContainer.appendChild(getInfoButton);

        jobsListElement.appendChild(jobContainer);
    });
});

function displayJobInfo(jobInfo) {
    const jobInfoElement = document.getElementById("jobInfo");
    jobInfoElement.innerHTML = `
        <p>Job ID: ${jobInfo.job_id}</p>
        <p>Size: ${jobInfo.size} bytes</p>
        <p>Time: ${jobInfo.time} seconds</p>
        <p>Music ID: ${jobInfo.music_id}</p>
        <p>Track ID: ${jobInfo.track_id}</p>
    `;
}