// Pure CSS dance animation (no graphics included)

// Designed by Gustavo Viselner
// https://dribbble.com/shots/3979515-It-s-not-unusual

// Thanks for Una Kravets for Sass Pixel Art technique
// https://una.im/sass-pixel-art/

// Making time ~ 7 hours

// Some Js for audio toggle

var music = document.getElementById("music");
var isPlaying = false;
music.volume = 0.2;
function togglePlay() {
  if (isPlaying) {
    music.pause()
  } else {
    music.play();
  }
};
music.onplaying = function() {
  isPlaying = true;
  document.getElementById("music-animation").classList.add('on')
};
music.onpause = function() {
  isPlaying = false;
  document.getElementById("music-animation").classList.remove('on')
};

var button = document.getElementById("toggle");
button.addEventListener('click', function() {
  if (button.getAttribute("data-text-swap") == button.innerHTML) {
    button.innerHTML = button.getAttribute("data-text-original");
  } else {
    button.setAttribute("data-text-original", button.innerHTML);
    button.innerHTML = button.getAttribute("data-text-swap");
  }
}, false);