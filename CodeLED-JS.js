var PIN = 10; //for now the pin has to be 10
var NUM_LEDS = 240;
var BRIGHTNESS = 255;
var temps = 0;

var strip = new Adafruit_NeoPixel(NUM_LEDS, PIN, NEO_GRB + NEO_KHZ800);

var rouge = strip.Color(255, 0, 0);
var bleu = strip.Color(0, 0, 255);
var noir = strip.Color(0, 0, 0);
var vert = strip.Color(0, 255, 0);
var jaune = strip.Color(255, 255, 0);
var blanc = strip.Color(255, 255, 255);


function setup() {
  strip.setBrightness(BRIGHTNESS);
  strip.begin();
  strip.setPixelColor(0, 0x00FF00);
  strip.show();
}

function loop() {
  //signal(temps);
  monter(temps);
  temps++;
}


function debutMatch(temps) {
	var longueurCycle = 60;
	var y;
	
	for (var i = 0; i < NUM_LEDS; i++) {
		
		y = 0.5 * Math.sin (2 * Math.PI / longueurCycle * (i + temps)) + 0.5;
		
		strip.setPixelColor (i, interpolate(rouge, bleu, y)); //interpolate(rouge, bleu, y));
		
	}
	strip.show();
	delay(50);
}


function endGame(temps) {
	var longueurCycle = 60;
	var y;
	
	for (var i = 0; i < NUM_LEDS; i++) {
		
		y = 1 - ((i + temps) % longueurCycle) / longueurCycle
		
		strip.setPixelColor (i, interpolate(noir, rouge, y)); //interpolate(rouge, bleu, y));
		
	}
	strip.show();
	delay(50);
}

function signal1(temps) {
	var longueurCycle = 60;
	
	var y = -2/longueurCycle * Math.abs((temps % longueurCycle) - longueurCycle/2) + 1;
	
	for (var i = 0; i < NUM_LEDS; i++) {
		
		strip.setPixelColor(i, interpolate(noir, vert, y)); //interpolate(rouge, bleu, y));
		
	}
	strip.show();
	delay(10);
}

function signal2(temps) {
	var longueurCycle = 60;
	
	var y = -2/longueurCycle * Math.abs((temps % longueurCycle) - longueurCycle/2) + 1;
	
	for (var i = 0; i < NUM_LEDS; i++) {
		
		strip.setPixelColor(i, interpolate(noir, jaune, y)); //interpolate(rouge, bleu, y));
		
	}
	strip.show();
	delay(10);
}

function autonome(temps) {
	var longueurCycle = 20;
	var y;
	var distance = 1;
	
	for (var i = 0; i < NUM_LEDS; i++) {
		y = distance * (Math.sin(2 * Math.PI / longueurCycle * (i + temps)) - 1) + 1;
		
		strip.setPixelColor(i, interpolate(bleu, blanc, y));
	}
	strip.show();
	delay(50);
}

function monter(temps) {
	var longueurCycle = 15;
	var y;
	var distance = 1;
	
	temps = temps % (2 * longueurCycle);
	
	for (var i = 0; i < NUM_LEDS; i++) {
		y = (i % longueurCycle) - longueurCycle + Math.abs(temps % longueurCycle - longueurCycle);
		
		
		if(temps < longueurCycle) {
			if(y < 0)
				strip.setPixelColor (i, blanc);
			else
				strip.setPixelColor (i, bleu);
		}
		else {
			if(y < 0)
				strip.setPixelColor (i, bleu);
			else
				strip.setPixelColor (i, blanc);
		}
		
	}
	strip.show();
	delay(50);
}


function teleop(temps) {
	var longueurCycle = 60;
	var y;
	
	temps %= longueurCycle;
	
	for (var i = 0; i < NUM_LEDS; i++) {
		
		y = Math.sin(2 * Math.PI / (longueurCycle / 5) * (i + temps)) + 2 * (-1 - 2.25) / longueurCycle * Math.abs(temps - (longueurCycle / 2.0)) + 2.25;
		
		if(y > 0)
			strip.setPixelColor (i, interpolate(bleu, blanc, y)); //interpolate(rouge, bleu, y));
		else
			strip.setPixelColor (i, bleu);
		
	}
	strip.show();
	delay(50);
}

function cube(temps) {
	var longueurCycle = 60;
	var y;
	
	temps %= longueurCycle;
	
	for (var i = 0; i < NUM_LEDS; i++) {
		
		y = Math.sin(2 * Math.PI / (longueurCycle / 5) * (i + temps)) + 2 * (-1 - 2.25) / longueurCycle * Math.abs(temps - (longueurCycle / 2.0)) + 2.25;
		
		if(y > 0)
			strip.setPixelColor (i, interpolate(bleu, jaune, y)); //interpolate(rouge, bleu, y));
		else
			strip.setPixelColor (i, bleu);
		
	}
	strip.show();
	delay(50);
}

function interpolate(a, b, t) {
	t = t > 1 ? 1 : t < 0 ? 0 : t;
	
	return (((a & 0xff0000) + ((b & 0xff0000) - (a & 0xff0000)) * t) & 0xff0000) | (((a & 0xff00) + ((b & 0xff00) - (a & 0xff00)) * t) & 0xff00) | (((a & 0xff) + ((b & 0xff) - (a & 0xff)) * t)  & 0xff)
}