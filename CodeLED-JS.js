var PIN = 10; //for now the pin has to be 10
var NUM_LEDS = 240;
var BRIGHTNESS = 255;
var temps = 0;

var strip = new Adafruit_NeoPixel(NUM_LEDS, PIN, NEO_GRB + NEO_KHZ800);

var rouge = strip.Color(255, 0, 0);
var bleu = strip.Color(0, 0, 255);

function setup() {
  strip.setBrightness(BRIGHTNESS);
  strip.begin();
  strip.setPixelColor(0, 0x00FF00);
    strip.show();
    
}

function loop() {
    debutMatch(temps);
    temps++;

}


function debutMatch(temps) {
	var longueurCycle = 60;
	var y;
	
	for (var i = 0; i < NUM_LEDS; i++) {
		
		y = 0.5 * Math.sin (2 * Math.PI / longueurCycle * (i + temps)) + 0.5;
	//	y = ((i + temps) % 60) / 60; 
		
		strip.setPixelColor (i, interpolate(rouge, bleu, y)); //interpolate(rouge, bleu, y));
		
	}
	strip.show();
	delay(50);
}

function interpolate(a, b, t) {
	
	return (((a & 0xff0000) + ((b & 0xff0000) - (a & 0xff0000)) * t) & 0xff0000) | (((a & 0x00ff00) + ((b & 0x00ff00) - (a & 0x00ff00)) * t) & 0x00ff00) | (((a & 0x0000FF) + ((b & 0x0000FF) - (a & 0x0000FF)) * t)  & 0x0000ff)
}





