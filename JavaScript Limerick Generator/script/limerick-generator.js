const limerick = [
    {
        rhymes: [
            "There once was a {noun} so {adjective},",
            "Who {verb} on a {noun} so {adjective}.",
            "It {verb} with glee,",
             "For all to see,",
            "That {noun} so {adjective} and {adjective}."
        ],
    },
    {
        rhymes: [
            "How funny was the {noun} indeed,",
            "That was {adjective} and {verb} with speed.",
            "That {noun} had it all,",
            "Till the season turned fall,",
            "And the {adjective} {noun} was pleased."
        ],
    },
];

const nounInput = document.getElementById("noun-input");
const verbInput = document.getElementById("verb-input");
const adjectiveInput = document.getElementById("adjective-input");
const generateButton = document.getElementById("generate-btn");
const limerickContainer = document.getElementById("limerick-container");
  
generateButton.addEventListener("click", generateLimerick);
  
function replacePlaceholders(limerick, noun, verb, adjective) {
    const rhymes =  limerick.rhymes.map(line => line
      .replace(/{noun}/g, noun)
      .replace(/{verb}/g, verb)
      .replace(/{adjective}/g, adjective)
    ).join("\n");

    return {
        rhymes,
        structure: limerick.rhymes
    };
}

function getRandomLimerick() {
    return limerick[Math.floor(Math.random() * limerick.length)];
}

function generateLimerick() {
    const noun = nounInput.value.trim();
    const verb = verbInput.value.trim();
    const adjective = adjectiveInput.value.trim();
  
    if (noun === "" || verb === "" || adjective === "") {
        alert("Please enter a noun, verb, and an adjective.");
        return;
    }
  
    const randomLimerick = getRandomLimerick();
    const generatedLimerick = replacePlaceholders(randomLimerick, noun, verb, adjective);
    
    limerickContainer.textContent = generatedLimerick.rhymes;
}
  