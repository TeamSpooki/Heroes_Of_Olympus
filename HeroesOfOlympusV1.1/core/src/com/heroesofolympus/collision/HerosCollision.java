package com.heroesofolympus.collision;

import com.heroesofolympus.game.Hero;

public class HerosCollision {
	
 private Hero achille;
 private Hero helen;
 private Hero hercules;
 private Hero hypolyta;
 private Hero thesius;
 
 public HerosCollision() {
	 
 }

public Hero getAchille() {
	return achille;
}

public void setAchille(Hero achille) {
	this.achille = achille;
}

public Hero getHelen() {
	return helen;
}

public void setHelen(Hero helen) {
	this.helen = helen;
}

public Hero getHercules() {
	return hercules;
}

public void setHercules(Hero hercules) {
	this.hercules = hercules;
}

public Hero getHypolyta() {
	return hypolyta;
}

public void setHypolyta(Hero hypolyta) {
	this.hypolyta = hypolyta;
}

public Hero getThesius() {
	return thesius;
}

public void setThesius(Hero thesius) {
	this.thesius = thesius;
}


///////////helen collision detection////////////////////
public boolean helenForward() {
	float helx = getHelen().getX() +64;
    float hely = getHelen().getY();
	
	float achilx = getAchille().getX();
	float achily = getAchille().getY();
	
	float herx = getHercules().getX();
	float hery = getHercules().getY();
	
	float hypx = getHypolyta().getX();
	float hypy = getHypolyta().getY();
	
	float thesx = getThesius().getX();
	float thesy = getThesius().getY();
	if(helx== achilx && hely ==achily || helx==herx && hely ==hery || helx==hypx && hely ==hypy || helx  ==thesx && hely ==thesy )
		return false;
	System.out.println(" helen location: "+ (helx) + "and" + "a chille:" + achilx);
	return true;
}

public boolean helenBackward() {
	float helx = getHelen().getX() - 64;
    float hely = getHelen().getY();
	
	float achilx = getAchille().getX();
	float achily = getAchille().getY();
	
	float herx = getHercules().getX();
	float hery = getHercules().getY();
	
	float hypx = getHypolyta().getX();
	float hypy = getHypolyta().getY();
	
	float thesx = getThesius().getX();
	float thesy = getThesius().getY();
	if(helx== achilx && hely ==achily || helx==herx && hely ==hery || helx==hypx && hely ==hypy || helx  ==thesx && hely ==thesy )
		return false;
	System.out.println(" helen location: "+ (helx) + "and" + "a chille:" + achilx);
	return true;
}

public boolean helenUpward() {
	float helx = getHelen().getX();
    float hely = getHelen().getY() - 64;
	
	float achilx = getAchille().getX();
	float achily = getAchille().getY();
	
	float herx = getHercules().getX();
	float hery = getHercules().getY();
	
	float hypx = getHypolyta().getX();
	float hypy = getHypolyta().getY();
	
	float thesx = getThesius().getX();
	float thesy = getThesius().getY();
	if(hely== achily && helx ==achilx || hely==hery && helx ==herx || hely==hypy && helx ==hypx || hely  ==thesy && helx ==thesx )
		return false;
	System.out.println(" helen location: "+ (helx) + "and" + "a chille:" + achilx);
	return true;
}

public boolean helenDownward() {
	float helx = getHelen().getX();
    float hely = getHelen().getY() + 64;
	
	float achilx = getAchille().getX();
	float achily = getAchille().getY();
	
	float herx = getHercules().getX();
	float hery = getHercules().getY();
	
	float hypx = getHypolyta().getX();
	float hypy = getHypolyta().getY();
	
	float thesx = getThesius().getX();
	float thesy = getThesius().getY();
	if(hely== achily && helx ==achilx || hely==hery && helx ==herx || hely==hypy && helx ==hypx || hely  ==thesy && helx ==thesx )
		return false;
	System.out.println(" helen location: "+ (helx) + "and" + "a chille:" + achilx);
	return true;
}

///////////achille collision detection////////////////////
public boolean achilleForward() {
float helx = getHelen().getX() ;
float hely = getHelen().getY();

float achilx = getAchille().getX() + 64;
float achily = getAchille().getY();

float herx = getHercules().getX();
float hery = getHercules().getY();

float hypx = getHypolyta().getX();
float hypy = getHypolyta().getY();

float thesx = getThesius().getX();
float thesy = getThesius().getY();
if(achilx== helx && achily==hely  || achilx==herx && achily ==hery || achilx==hypx && achily ==hypy || achilx  ==thesx && achily ==thesy )
return false;
System.out.println(" helen location: "+ (helx) + "and" + "a chille:" + achilx);
return true;
}

public boolean achilleBackward() {
float helx = getHelen().getX() ;
float hely = getHelen().getY();

float achilx = getAchille().getX()- 64;
float achily = getAchille().getY();

float herx = getHercules().getX();
float hery = getHercules().getY();

float hypx = getHypolyta().getX();
float hypy = getHypolyta().getY();

float thesx = getThesius().getX();
float thesy = getThesius().getY();
if(achilx== helx && achily==hely  || achilx==herx && achily ==hery || achilx==hypx && achily ==hypy || achilx  ==thesx && achily ==thesy )
return false;
System.out.println(" helen location: "+ (helx) + "and" + "a chille:" + achilx);
return true;
}

public boolean achilleUpward() {
float helx = getHelen().getX();
float hely = getHelen().getY();

float achilx = getAchille().getX();
float achily = getAchille().getY()  - 64 ;

float herx = getHercules().getX();
float hery = getHercules().getY();

float hypx = getHypolyta().getX();
float hypy = getHypolyta().getY();

float thesx = getThesius().getX();
float thesy = getThesius().getY();
if(achilx== helx && achily==hely  || achilx==herx && achily ==hery || achilx==hypx && achily ==hypy || achilx  ==thesx && achily ==thesy )
return false;
System.out.println(" helen location: "+ (helx) + "and" + "a chille:" + achilx);
return true;
}

public boolean achilleDownward() {
float helx = getHelen().getX();
float hely = getHelen().getY() ;

float achilx = getAchille().getX();
float achily = getAchille().getY() + 64;

float herx = getHercules().getX();
float hery = getHercules().getY();

float hypx = getHypolyta().getX();
float hypy = getHypolyta().getY();

float thesx = getThesius().getX();
float thesy = getThesius().getY();
if(achilx== helx && achily==hely  || achilx==herx && achily ==hery || achilx==hypx && achily ==hypy || achilx  ==thesx && achily ==thesy )
return false;
System.out.println(" helen location: "+ (helx) + "and" + "a chille:" + achilx);
return true;
}

///////////hercules collision detection////////////////////
public boolean herculesForward() {
float helx = getHelen().getX() ;
float hely = getHelen().getY();

float achilx = getAchille().getX() ;
float achily = getAchille().getY();

float herx = getHercules().getX() + 64;
float hery = getHercules().getY();

float hypx = getHypolyta().getX();
float hypy = getHypolyta().getY();

float thesx = getThesius().getX();
float thesy = getThesius().getY();
if(herx== helx && hery==hely  || herx==achilx && hery==achily || herx==hypx && hery ==hypy || herx  ==thesx && hery ==thesy )
return false;
System.out.println(" helen location: "+ (helx) + "and" + "a chille:" + achilx);
return true;
}

public boolean herculesBackward() {
float helx = getHelen().getX() ;
float hely = getHelen().getY();

float achilx = getAchille().getX();
float achily = getAchille().getY();

float herx = getHercules().getX() - 64;
float hery = getHercules().getY();

float hypx = getHypolyta().getX();
float hypy = getHypolyta().getY();

float thesx = getThesius().getX();
float thesy = getThesius().getY();
if(herx== helx && hery==hely  || herx==achilx && hery==achily || herx==hypx && hery ==hypy || herx  ==thesx && hery ==thesy)
return false;
System.out.println(" helen location: "+ (helx) + "and" + "a chille:" + achilx);
return true;
}

public boolean herculesUpward() {
float helx = getHelen().getX();
float hely = getHelen().getY();

float achilx = getAchille().getX();
float achily = getAchille().getY() ;

float herx = getHercules().getX();
float hery = getHercules().getY() - 64;

float hypx = getHypolyta().getX();
float hypy = getHypolyta().getY();

float thesx = getThesius().getX();
float thesy = getThesius().getY();
if(herx== helx && hery==hely  || herx==achilx && hery==achily || herx==hypx && hery ==hypy || herx  ==thesx && hery ==thesy)
return false;
System.out.println(" helen location: "+ (helx) + "and" + "a chille:" + achilx);
return true;
}

public boolean herculesDownward() {
float helx = getHelen().getX();
float hely = getHelen().getY() ;

float achilx = getAchille().getX();
float achily = getAchille().getY() ;

float herx = getHercules().getX();
float hery = getHercules().getY() + 64;

float hypx = getHypolyta().getX();
float hypy = getHypolyta().getY();

float thesx = getThesius().getX();
float thesy = getThesius().getY();
if(herx== helx && hery==hely  || herx==achilx && hery==achily || herx==hypx && hery ==hypy || herx  ==thesx && hery ==thesy)
return false;
System.out.println(" helen location: "+ (helx) + "and" + "a chille:" + achilx);
return true;
}

///////////hypolyta collision detection////////////////////
public boolean hypolytaForward() {
float helx = getHelen().getX() ;
float hely = getHelen().getY();

float achilx = getAchille().getX() ;
float achily = getAchille().getY();

float herx = getHercules().getX() ;
float hery = getHercules().getY();

float hypx = getHypolyta().getX() + 64;
float hypy = getHypolyta().getY();

float thesx = getThesius().getX();
float thesy = getThesius().getY();
if(hypx== helx && hypy==hely  || hypx==achilx && hypy==achily || hypx==herx && hypy ==hery || hypx  ==thesx && hypy ==thesy )
return false;
System.out.println(" helen location: "+ (helx) + "and" + "a chille:" + achilx);
return true;
}

public boolean hypolytaBackward() {
float helx = getHelen().getX() ;
float hely = getHelen().getY();

float achilx = getAchille().getX();
float achily = getAchille().getY();

float herx = getHercules().getX();
float hery = getHercules().getY();

float hypx = getHypolyta().getX() - 64;
float hypy = getHypolyta().getY();

float thesx = getThesius().getX();
float thesy = getThesius().getY();
if(hypx== helx && hypy==hely  || hypx==achilx && hypy==achily || hypx==herx && hypy ==hery || hypx  ==thesx && hypy ==thesy )
return false;
System.out.println(" helen location: "+ (helx) + "and" + "a chille:" + achilx);
return true;
}

public boolean hypolytaUpward() {
float helx = getHelen().getX();
float hely = getHelen().getY();

float achilx = getAchille().getX();
float achily = getAchille().getY() ;

float herx = getHercules().getX();
float hery = getHercules().getY() ;

float hypx = getHypolyta().getX();
float hypy = getHypolyta().getY() - 64;

float thesx = getThesius().getX();
float thesy = getThesius().getY();
if(hypx== helx && hypy==hely  || hypx==achilx && hypy==achily || hypx==herx && hypy ==hery || hypx  ==thesx && hypy ==thesy )
return false;
System.out.println(" helen location: "+ (helx) + "and" + "a chille:" + achilx);
return true;
}

public boolean hypolytaDownward() {
float helx = getHelen().getX();
float hely = getHelen().getY() ;

float achilx = getAchille().getX();
float achily = getAchille().getY() ;

float herx = getHercules().getX();
float hery = getHercules().getY() ;

float hypx = getHypolyta().getX();
float hypy = getHypolyta().getY() + 64;

float thesx = getThesius().getX();
float thesy = getThesius().getY();
if(hypx== helx && hypy==hely  || hypx==achilx && hypy==achily || hypx==herx && hypy ==hery || hypx  ==thesx && hypy ==thesy )
return false;
System.out.println(" helen location: "+ (helx) + "and" + "a chille:" + achilx);
return true;
}

///////////thesius collision detection////////////////////
public boolean thesiusForward() {
float helx = getHelen().getX() ;
float hely = getHelen().getY();

float achilx = getAchille().getX() ;
float achily = getAchille().getY();

float herx = getHercules().getX() ;
float hery = getHercules().getY();

float hypx = getHypolyta().getX();
float hypy = getHypolyta().getY();

float thesx = getThesius().getX() + 64;
float thesy = getThesius().getY();
if(thesx== helx && thesy==hely  || thesx==achilx && thesy==achily || thesx==herx && thesy ==hery || thesx ==hypx && thesy ==hypy )
return false;
System.out.println(" helen location: "+ (helx) + "and" + "a chille:" + achilx);
return true;
}

public boolean thesiusBackward() {
float helx = getHelen().getX() ;
float hely = getHelen().getY();

float achilx = getAchille().getX();
float achily = getAchille().getY();

float herx = getHercules().getX();
float hery = getHercules().getY();

float hypx = getHypolyta().getX();
float hypy = getHypolyta().getY();

float thesx = getThesius().getX() - 64;
float thesy = getThesius().getY();
if(thesx== helx && thesy==hely  ||thesx==achilx && thesy==achily || thesx==herx && thesy ==hery || thesx  ==hypx && thesy ==hypy )
return false;
System.out.println(" helen location: "+ (helx) + "and" + "a chille:" + achilx);
return true;
}

public boolean thesiusUpward() {
float helx = getHelen().getX();
float hely = getHelen().getY();

float achilx = getAchille().getX();
float achily = getAchille().getY() ;

float herx = getHercules().getX();
float hery = getHercules().getY() ;

float hypx = getHypolyta().getX();
float hypy = getHypolyta().getY();

float thesx = getThesius().getX();
float thesy = getThesius().getY() - 64;
if(thesx== helx && thesy==hely  || thesx==achilx && thesy==achily || thesx==herx && thesy ==hery || thesx  ==hypx && thesy ==hypy )
return false;
System.out.println(" helen location: "+ (helx) + "and" + "a chille:" + achilx);
return true;
}

public boolean thesiusDownward() {
float helx = getHelen().getX();
float hely = getHelen().getY() ;

float achilx = getAchille().getX();
float achily = getAchille().getY() ;

float herx = getHercules().getX();
float hery = getHercules().getY() ;

float hypx = getHypolyta().getX();
float hypy = getHypolyta().getY();

float thesx = getThesius().getX();
float thesy = getThesius().getY() + 64;
if(thesx== helx && thesy==hely  || thesx==achilx && thesy==achily || thesx==herx && thesy ==hery ||thesx  ==hypx && thesy ==hypy )
return false;
System.out.println(" helen location: "+ (helx) + "and" + "a chille:" + achilx);
return true;
}



}
