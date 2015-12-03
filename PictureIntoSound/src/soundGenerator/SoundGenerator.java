package soundGenerator;

	import jm.JMC;
	import jm.music.data.*;
	import jm.util.Play;

	public final class SoundGenerator implements JMC {
	    
		SoundGenerator(){}
		
	    public void playSound(){
	        Play.midi(new Note(C4, QN));
	    }
	}

