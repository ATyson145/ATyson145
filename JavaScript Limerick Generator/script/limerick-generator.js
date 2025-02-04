const limericks = [
  {
    rhymes: [
      "There once was a {noun} so {adjective},",
      "Who {verb} on a {noun} so {adjective}.",
      "It {verb} with glee,",
      "For all to see,",
      "That {noun} so {adjective} and {adjective}.",
    ],
  },
  {
    rhymes: [
      "How funny was the {noun} indeed,",
      "That was {adjective} and {verb} with speed.",
      "That {noun} had it all,",
      "Till the season turned fall,",
      "And the {adjective} {noun} was pleased.",
    ],
  },
];

// Match HTML input fields
const nounInput = document.getElementById("line1");
const verbInput = document.getElementById("line2");
const adjectiveInput = document.getElementById("line3");
const generateButton = document.getElementById("generate-btn");
const limerickOutput = document.getElementById("limerick-output");

// Event listener for button click
generateButton.addEventListener("click", generateLimerick);

function replacePlaceholders(limerick, noun, verb, adjective) {
  const rhymes = limerick.rhymes
    .map((line) =>
      line
        .replace(/{noun}/g, noun)
        .replace(/{verb}/g, verb)
        .replace(/{adjective}/g, adjective)
    )
    .join("<br>"); // Preserve line breaks in HTML

  return rhymes;
}

function getRandomLimerick() {
  return limericks[Math.floor(Math.random() * limericks.length)];
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
  const generatedLimerick = replacePlaceholders(
    randomLimerick,
    noun,
    verb,
    adjective
  );

  limerickOutput.innerHTML = generatedLimerick; // Fix: Use innerHTML for formatting
}
