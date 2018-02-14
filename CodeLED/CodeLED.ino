#include <Adafruit_NeoPixel.h>

final int PIN = 10; //for now the pin has to be 10
final int NUM_LEDS = 240;
final int BRIGHTNESS = 255;

int temps = 0;
String message = "";
char alliance = '0';
boolean signal1, signal2;
unsigned long tempsSignal;

Adafruit_NeoPixel strip = new Adafruit_NeoPixel(NUM_LEDS, PIN, NEO_GRB + NEO_KHZ800);

uint32_t rouge = strip.Color(255, 0, 0);
uint32_t bleu = strip.Color(0, 0, 255);
uint32_t noir = strip.Color(0, 0, 0);
uint32_t vert = strip.Color(0, 255, 0);
uint32_t jaune = strip.Color(255, 255, 0);
uint32_t blanc = strip.Color(255, 255, 255);
uint32_t allianceLed;


void setup() {
  strip.setBrightness(BRIGHTNESS);
  strip.begin();
  strip.setPixelColor(0, 0x00FF00);
  strip.show();
  Serial.begin(9600);
}



void loop() {

  temps++;

  if(signal1){
    signal1();
    if(millis() - tempsSignal >= 3000)
    signal1 = false;
  }
  
  if(signal2){
    signal2();
    if(millis() - tempsSignal >= 3000)
    signal2 = false;
  }
 
  
  if(message == "rouge" || message == "bleu"){
    
    alliance = message[0];
    message = "autonome";
    
    if (alliance == 'r'){
      allianceLed = rouge;   
    }
    else if(alliance == 'b'){
      allianceLed = bleu;
    }
    
  }
 else if(message == "cube"){
    cube();
  }
 else if(message == "monter"){
    monter();
  }
 else if(message == "autonome"){
    autonome();
  }
 else if(message == "endGame"){
    endGame();
  }
 else if(message == "teleop"){
    teleop();
  }
 else if(message == ""){
    debutMatch();
  }
}

void serialEvent(){

  String messageRecu = Serial.readStringUntil('\n');

  if(messageRecu == "signal1")
    signal1 = true;
    tempsSignal = millis();
  else if(messageRecu == "signal2") 
    signal2 = true;
    tempsSignal = millis();
  else
    message = messageRecu;
    
}

void debutMatch() {
	final int longueurCycle = 60;
	double y;
	
	for (int i = 0; i < NUM_LEDS; i++) {
		
		y = 0.5 * Math.sin (2 * Math.PI / longueurCycle * (i + temps)) + 0.5;
		
		strip.setPixelColor (i, interpolate(rouge, bleu, y)); //interpolate(rouge, bleu, y));
		
	}
	strip.show();
	delay(50);
}


void endGame() {
	final int longueurCycle = 60;
	double y;
	
	for (int i = 0; i < NUM_LEDS; i++) {
		
		y = 1 - ((i + temps) % longueurCycle) / longueurCycle
		
		strip.setPixelColor (i, interpolate(noir, allianceLed, y)); //interpolate(rouge, bleu, y));
		
	}
	strip.show();
	delay(50);
}

void signal1() {
	final int longueurCycle = 60;
	
	double y = -2/longueurCycle * Math.abs((temps % longueurCycle) - longueurCycle/2) + 1;
	
	for (int i = 0; i < NUM_LEDS; i++) {
		
		strip.setPixelColor(i, interpolate(noir, vert, y)); //interpolate(rouge, bleu, y));
		
	}
	strip.show();
	delay(10);
}

void signal2() {
	final double longueurCycle = 60;
	
  double y = -2/longueurCycle * Math.abs((temps % longueurCycle) - longueurCycle/2) + 1;
	
	for (int i = 0; i < NUM_LEDS; i++) {
		
		strip.setPixelColor(i, interpolate(noir, jaune, y)); //interpolate(rouge, bleu, y));
		
	}
	strip.show();
	delay(10);
}

void autonome() {
	final int longueurCycle = 20;
  double y;
	final int distance = 1;
	
	for (int i = 0; i < NUM_LEDS; i++) {
		y = distance * (Math.sin(2 * Math.PI / longueurCycle * (i + temps)) - 1) + 1;
		
		strip.setPixelColor(i, interpolate(allianceLed, blanc, y));
	}
	strip.show();
	delay(50);
}

void monter() {
  final int longueurCycle = 15;
	double y;
  final int distance = 1;
	
	temps = temps % (2 * longueurCycle);
	
	for (int i = 0; i < NUM_LEDS; i++) {
		y = (i % longueurCycle) - longueurCycle + Math.abs(temps % longueurCycle - longueurCycle);
		
		
		if(temps < longueurCycle) {
			if(y < 0)
				strip.setPixelColor (i, blanc);
			else
				strip.setPixelColor (i, allianceLed);
		}
		else {
			if(y < 0)
				strip.setPixelColor (i, allianceLed);
			else
				strip.setPixelColor (i, blanc);
		}
		
	}
	strip.show();
	delay(50);
}

void teleop() {
	final int longueurCycle = 60;
	double y;
	
	temps %= longueurCycle;
	
	for (int i = 0; i < NUM_LEDS; i++) {
		
		y = Math.sin(2 * Math.PI / (longueurCycle / 5) * (i + temps)) + 2 * (-1 - 2.25) / longueurCycle * Math.abs(temps - (longueurCycle / 2.0)) + 2.25;
		
		if(y > 0)
			strip.setPixelColor (i, interpolate(allianceLed, blanc, y)); //interpolate(rouge, bleu, y));
		else
			strip.setPixelColor (i, allianceLed);
		
	}
	strip.show();
	delay(50);
}

void cube() {
	final int longueurCycle = 60;
	double y;
	
	temps %= longueurCycle;
	
	for (int i = 0; i < NUM_LEDS; i++) {
		
		y = Math.sin(2 * Math.PI / (longueurCycle / 5) * (i + temps)) + 2 * (-1 - 2.25) / longueurCycle * Math.abs(temps - (longueurCycle / 2.0)) + 2.25;
		
		if(y > 0)
			strip.setPixelColor (i, interpolate(allianceLed, jaune, y)); //interpolate(rouge, bleu, y));
		else
			strip.setPixelColor (i, allianceLed);
		
	}
	strip.show();
	delay(50);
}

void interpolate(a, b, t) {
  
  if(t > 1)
     t = 1;
  else if(t < 0)
     t = 0;
  
	return (((a & 0xff0000) + ((b & 0xff0000) - (a & 0xff0000)) * t) & 0xff0000) | (((a & 0xff00) + ((b & 0xff00) - (a & 0xff00)) * t) & 0xff00) | (((a & 0xff) + ((b & 0xff) - (a & 0xff)) * t)  & 0xff)
}





