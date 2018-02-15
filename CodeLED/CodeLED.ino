#include <Adafruit_NeoPixel.h>

int PIN = 6; //for now the pin has to be 10
int NUM_LEDS = 240;
int BRIGHTNESS = 255;

int temps = 0;
String message = "";
char alliance = '0';
boolean signal1 = false, signal2 = false;
unsigned long tempsSignal;

Adafruit_NeoPixel strip = new Adafruit_NeoPixel(NUM_LEDS, 6, NEO_GRB + NEO_KHZ800);

uint32_t rouge = strip.Color(255, 0, 0);
uint32_t bleu = strip.Color(0, 0, 255);
uint32_t noir = strip.Color(0, 0, 0);
uint32_t vert = strip.Color(0, 255, 0);
uint32_t jaune = strip.Color(255, 255, 0);
uint32_t blanc = strip.Color(255, 255, 255);
uint32_t allianceLed;



void setup() {
  //strip.setBrightness(BRIGHTNESS);
  strip.begin();
  //strip.setPixelColor(0, 0x00FF00);
  for(int i = 0; i < NUM_LEDS; i++) {
    strip.setPixelColor(i, 0, 255, 0);
  }
  strip.show();
  //Serial.begin(9600);
}



void loop() {

  temps++;
  /*temps = temps % NUM_LEDS;
  strip.setPixelColor(temps, 255, 255, 0);
  strip.show();
  delay(20);*/
  debutMatch(temps);
/*
  if(signal1){
    funcSignal1();
    if(millis() - tempsSignal >= 3000)
    signal1 = false;
  }
  
  else if(signal2){
    funcSignal2();
    if(millis() - tempsSignal >= 3000)
    signal2 = false;
  }
 
  
  else if(message == "rouge" || message == "bleu"){
    
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
 else {
    debutMatch();
  }
  */
}

/*
void serialEvent(){

  String messageRecu = Serial.readStringUntil('\n');

  if(messageRecu == "signal1") {
    signal1 = true;
    tempsSignal = millis();
  }
  else if(messageRecu == "signal2") {
    signal2 = true;
    tempsSignal = millis();
  }
  else {
    message = messageRecu;
  }
}
*/
void debutMatch(int temps) {
	  int longueurCycle = 20;
	double y;
	
	for (int i = 0; i < NUM_LEDS; i++) {
		
		y = 0.5 * sin (2 * PI/ longueurCycle * (i + temps)) + 0.5;
		
		strip.setPixelColor (i, interpolate(rouge, bleu, y)); //interpolate(rouge, bleu, y));
		
	}
	strip.show();
	delay(50);
}


void endGame() {
	  int longueurCycle = 60;
	double y;
	
	for (int i = 0; i < NUM_LEDS; i++) {
		
		y = 1 - ((i + temps) % longueurCycle) / longueurCycle;
		
		strip.setPixelColor (i, interpolate(noir, allianceLed, y)); //interpolate(rouge, bleu, y));
		
	}
	strip.show();
	delay(50);
}

void funcSignal1() {
	  int longueurCycle = 60;
	
	double y = -2/longueurCycle * abs((temps % longueurCycle) - longueurCycle/2) + 1;
	
	for (int i = 0; i < NUM_LEDS; i++) {
		
		strip.setPixelColor(i, interpolate(noir, vert, y)); //interpolate(rouge, bleu, y));
		
	}
	strip.show();
	delay(10);
}

void funcSignal2() {
	int longueurCycle = 60;
	
  double y = -2/longueurCycle *  abs((temps % longueurCycle) - longueurCycle/2) + 1;
	
	for (int i = 0; i < NUM_LEDS; i++) {
		
		strip.setPixelColor(i, interpolate(noir, jaune, y)); //interpolate(rouge, bleu, y));
		
	}
	strip.show();
	delay(10);
}

void autonome() {
	  int longueurCycle = 20;
  double y;
	  int distance = 1;
	
	for (int i = 0; i < NUM_LEDS; i++) {
		y = distance * ( sin(2 *  PI / longueurCycle * (i + temps)) - 1) + 1;
		
		strip.setPixelColor(i, interpolate(allianceLed, blanc, y));
	}
	strip.show();
	delay(50);
}

void monter() {
    int longueurCycle = 15;
	double y;
    int distance = 1;
	
	temps = temps % (2 * longueurCycle);
	
	for (int i = 0; i < NUM_LEDS; i++) {
		y = (i % longueurCycle) - longueurCycle +  abs(temps % longueurCycle - longueurCycle);
		
		
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
	  int longueurCycle = 60;
	double y;
	
	temps %= longueurCycle;
	
	for (int i = 0; i < NUM_LEDS; i++) {
		
		y =  sin(2 *  PI / (longueurCycle / 5) * (i + temps)) + 2 * (-1 - 2.25) / longueurCycle *  abs(temps - (longueurCycle / 2.0)) + 2.25;
		
		if(y > 0)
			strip.setPixelColor (i, interpolate(allianceLed, blanc, y)); //interpolate(rouge, bleu, y));
		else
			strip.setPixelColor (i, allianceLed);
		
	}
	strip.show();
	delay(50);
}

void cube() {
	  int longueurCycle = 60;
	double y;
	
	temps %= longueurCycle;
	
	for (int i = 0; i < NUM_LEDS; i++) {
		
		y = sin(2 *  PI / (longueurCycle / 5) * (i + temps)) + 2 * (-1 - 2.25) / longueurCycle * abs(temps - (longueurCycle / 2.0)) + 2.25;
		
		if(y > 0)
			strip.setPixelColor (i, interpolate(allianceLed, jaune, y));
		else
			strip.setPixelColor (i, allianceLed);
		
	}
	strip.show();
	delay(50);
}


uint32_t interpolate( uint32_t fromColor, uint32_t toColor, double t ){
  // fromColor
  uint8_t r = ( fromColor & ((uint32_t)0xFF << 16) ) >> 16;
  uint8_t g = ( fromColor & ((uint32_t)0xFF << 8) ) >> 8;
  uint8_t b = ( fromColor & ((uint32_t)0xFF) );
  // toColor
  uint8_t r2 = ( toColor & ((uint32_t)0xFF << 16) ) >> 16;
  uint8_t g2 = ( toColor & ((uint32_t)0xFF << 8) ) >> 8;
  uint8_t b2 = ( toColor & ((uint32_t)0xFF) );
  
  // Interpolate
  float ri = r - ( ((float)(r-r2)) *t );
  float gi = g - ( ((float)(g-g2)) * t);
  float bi = b - ( ((float)(b-b2)) * t);

  return strip.Color( (int)ri, (int)gi, (int)bi );
}


/*
uint32_t interpolate(uint32_t a, uint32_t b, double t) {
  
  if(t > 1)
     t = 1;
  else if(t < 0)
     t = 0;
  
	return (((uint32_t)((a & 0xff0000) + ((b & 0xff0000) - (a & 0xff0000)) * t)) & 0xff0000) | (((uint32_t)((a & 0x00ff00) + ((b & 0x00ff00) - (a & 0xff00)) * t)) & 0xff00) | (((uint32_t)((a & 0xff) + ((b & 0xff) - (a & 0xff)) * t))  & 0xff);
}
*/




