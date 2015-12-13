package soundGenerator;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import scanImage.ColorList;
import scanImage.Shape;
import scanImage.ColorList.ColorName;
import jm.JMC;
import jm.music.data.*;
import jm.util.Play;
import jm.util.Write;

public final class SoundGenerator implements JMC {

	public SoundGenerator() {
	}

	public void playSound(int[] histogram, String histogramMeansValue, String sizeMeansValue, String colorMeansValue, ArrayList<Shape> shapes) {
		int[] scaleToUse = { 0, 1, 3, 7, 8 };
		ArrayList<Integer> pitchesToUse = getPitches();
		ArrayList<Double> rhythms = new ArrayList<Double>();
		Part inst = new Part();
		double[] rhythmArray; 
		 // COLOR
		ArrayList<String> colors = new ArrayList<String>();
		ColorList cl = new ColorList();
		cl.initColorList();
		colors = cl.getColorsArray();
		int numColors = 0;
		
		ArrayList<String> allColors = new ArrayList<String>();
		for(Shape s : shapes){
	    	allColors.add(s.getColor());
	    }
		Collections.sort(allColors);
		int maxColor = 1;
		int currentValue =0;
		String previousColor = "n";
		String mode = "Red";
		for(int x=0; x<allColors.size(); x++){
	    	if(allColors.equals(previousColor)){
	    		currentValue++;
	    		if(currentValue>maxColor){
	    			mode = allColors.get(x);
	    		}
	    	}else{
	    		currentValue = 0;
	    	}
	    	previousColor = allColors.get(x);
	    }
		int valueToMap = 0;
		for(int d = 0; d<colors.size(); d++){
			if(mode.equals(colors.get(d))){
				valueToMap = d;
			}
		}
		
		
		for(int f= 0; f<colors.size(); f++){
			numColors++;
		}
		
		
		boolean sizeMeansNoteLength = false;
		boolean sizeMeansNoteValue = false;
		
		Score modeScore = new Score("Picture Melody");
		int averageSize = 1;
		
		if(sizeMeansValue.equals("Instrument")){
	    for(Shape s : shapes){
	    	averageSize += s.getSize();
	    }
	    averageSize = averageSize/shapes.size();
	    
		inst = getInstrument(averageSize,true);
		}else if(sizeMeansValue.equals("Note Length")){
			sizeMeansNoteLength = true;
		}else if(sizeMeansValue.equals("Note Amplitude")){
			sizeMeansNoteValue = true;
		}
		
		if(colorMeansValue.equals("Scale")){

			getScale(valueToMap*10/colors.size());
		}else if(colorMeansValue.equals("Instrument")){
		inst = getInstrument(valueToMap*30/colors.size(),false);
		}
		
		
		
		//"Note Amplitude"
		//"Note Length"
		//"Instrument"
		//"Scale"
				
		// create a middle C minim (half note)
		ArrayList<Note> notes = new ArrayList<Note>();
	
		for(int r = 0; r<shapes.size(); r++){
			
			
			switch(shapes.get(r).getColor()){
			case "Black": notes.add(new Note( pitchesToUse.get(1%pitchesToUse.size()), ((shapes.get(r).getSize()/2)*100)));
				break;
			case   "Blue": notes.add(new Note( pitchesToUse.get(2%pitchesToUse.size()), ((shapes.get(r).getSize()/2)*100)));
				break;
			case   "Brown": notes.add(new Note( pitchesToUse.get(3%pitchesToUse.size()), ((shapes.get(r).getSize()/2)*100)));
				break;
			case   "Cyan": notes.add(new Note( pitchesToUse.get(4%pitchesToUse.size()), ((shapes.get(r).getSize()/2)*100)));
				break;
			case   "DarkBlue": notes.add(new Note( pitchesToUse.get(5%pitchesToUse.size()), ((shapes.get(r).getSize()/2)*100)));
				break;
			case   "DarkGreen": notes.add(new Note( pitchesToUse.get(6%pitchesToUse.size()), ((shapes.get(r).getSize()/2)*100)));
				break;
			case   "DarkOrange": notes.add(new Note( pitchesToUse.get(7%pitchesToUse.size()), ((shapes.get(r).getSize()/2)*100)));
				break;
			case   "DarkRed": notes.add(new Note( pitchesToUse.get(8%pitchesToUse.size()), ((shapes.get(r).getSize()/2)*100)));
				break;
			case   "DarkViolet":  notes.add(new Note( pitchesToUse.get(9%pitchesToUse.size()), ((shapes.get(r).getSize()/2)*100)));
				break;
			case   "Gray": notes.add(new Note( pitchesToUse.get(10%pitchesToUse.size()), ((shapes.get(r).getSize()/2)*100)));
				break;
			case  "Green":  notes.add(new Note( pitchesToUse.get(11%pitchesToUse.size()), ((shapes.get(r).getSize()/2)*100)));
				break;
			case   "Khaki":  notes.add(new Note( pitchesToUse.get(12%pitchesToUse.size()), ((shapes.get(r).getSize()/2)*100)));
				break;
			case   "LightBlue":  notes.add(new Note( pitchesToUse.get(13%pitchesToUse.size()), ((shapes.get(r).getSize()/2)*100)));
				break;
			case   "LightGreen":  notes.add(new Note( pitchesToUse.get(14%pitchesToUse.size()), ((shapes.get(r).getSize()/2)*100)));
				break;
			case  "LightYellow":  notes.add(new Note( pitchesToUse.get(15%pitchesToUse.size()), ((shapes.get(r).getSize()/2)*100)));
				break;
			case  "Magenta":  notes.add(new Note( pitchesToUse.get(16%pitchesToUse.size()), ((shapes.get(r).getSize()/2)*100)));
				break;
			case   "Orange":  notes.add(new Note( pitchesToUse.get(17%pitchesToUse.size()), ((shapes.get(r).getSize()/2)*100)));
				break;
			case   "Pink":  notes.add(new Note( pitchesToUse.get(18%pitchesToUse.size()), ((shapes.get(r).getSize()/2)*100)));
				break;
			case   "Purple":  notes.add(new Note( pitchesToUse.get(19%pitchesToUse.size()), ((shapes.get(r).getSize()/2)*100)));
				break;
			case   "Red":  notes.add(new Note( pitchesToUse.get(20%pitchesToUse.size()), ((shapes.get(r).getSize()/2)*100)));
				break;
			case   "Salmon":  notes.add(new Note( pitchesToUse.get(21%pitchesToUse.size()), ((shapes.get(r).getSize()/2)*100)));
				break;
			case   "Turquoise":  notes.add(new Note( pitchesToUse.get(22%pitchesToUse.size()), ((shapes.get(r).getSize()/2)*100)));
				break;
			case   "Violet":  notes.add(new Note( pitchesToUse.get(23%pitchesToUse.size()), ((shapes.get(r).getSize()/2)*100)));
				break;
			case   "White":  notes.add(new Note( pitchesToUse.get(24%pitchesToUse.size()), ((shapes.get(r).getSize()/2)*100)));
				break;
			case   "Yellow":  notes.add(new Note( pitchesToUse.get(25%pitchesToUse.size()), ((shapes.get(r).getSize()/2)*100)));
				break;
			default:  notes.add(new Note( pitchesToUse.get(1%pitchesToUse.size()), ((shapes.get(r).getSize()/2)*100)));
				break;
			}
		}
		

		// create a phrase
		Phrase phr = new Phrase();

		// put the note inside the phrase
		for(int p = 0; p<notes.size(); p++){
			phr.addNote(notes.get(p));
		}
		 
		// pack the phrase into a part
		//Part p = new Part();
		inst.addPhrase(phr);

		// pack the part into a score titled 'Bing'
		Score s = new Score("Bing");
		s.addPart(inst);

		// write the score as a MIDI file to disk
		// Write.midi(s,"Bing.mid");

		Play.midi(s);
	}

	public Part getInstrument(int determiningNumber, boolean bySize) {
		ArrayList<String> musicalInstruments = new ArrayList<String>();
		if(bySize){
			musicalInstruments.add("BASS");
			musicalInstruments.add("ACOUSTIC_BASS");
			musicalInstruments.add("ELECTRIC_BASS");
			musicalInstruments.add("TROMBONE");
			musicalInstruments.add("BARITONE_SAX");
			musicalInstruments.add("BASSOON");
			musicalInstruments.add("TUBA");
			musicalInstruments.add("BRASS");
			musicalInstruments.add("VIOLA");
			musicalInstruments.add("FRENCH_HORN");
			musicalInstruments.add("CELLO");
			musicalInstruments.add("HARMONICA");
			musicalInstruments.add("FIDDLE");
			musicalInstruments.add("HARPSICHORD");
			musicalInstruments.add("ACCORDION");
			musicalInstruments.add("DIST_GUITAR");
			musicalInstruments.add("ALTO_SAX");	
			musicalInstruments.add("CHURCH_ORGAN");
			musicalInstruments.add("EL_GUITAR");
			musicalInstruments.add("TRUMPET");
			musicalInstruments.add("VIOLIN");
			musicalInstruments.add("OBOE");
			musicalInstruments.add("CLARINET");
			musicalInstruments.add("CHOIR");
			musicalInstruments.add("STRINGS");
			musicalInstruments.add("ACOUSTIC_GUITAR");
			musicalInstruments.add("ACOUSTIC_GRAND");
			musicalInstruments.add("FLUTE");
			musicalInstruments.add("FANTASIA");
			musicalInstruments.add("PAN_FLUTE");
			
		}else{
		musicalInstruments.add("DIST_GUITAR");
		musicalInstruments.add("BRASS");
		musicalInstruments.add("TRUMPET");
		musicalInstruments.add("VIOLIN");
		musicalInstruments.add("OBOE");
		musicalInstruments.add("BASSOON");
		musicalInstruments.add("HARMONICA");
		musicalInstruments.add("FIDDLE");
		musicalInstruments.add("HARPSICHORD");
		musicalInstruments.add("ACCORDION");
		musicalInstruments.add("TROMBONE");
		musicalInstruments.add("VIOLA");
		musicalInstruments.add("ALTO_SAX");
		musicalInstruments.add("BARITONE_SAX");
		musicalInstruments.add("CHURCH_ORGAN");
		musicalInstruments.add("EL_GUITAR");
		musicalInstruments.add("TUBA");
		musicalInstruments.add("FRENCH_HORN");
		musicalInstruments.add("ACOUSTIC_BASS");
		musicalInstruments.add("ELECTRIC_BASS");
		musicalInstruments.add("CLARINET");
		musicalInstruments.add("CHOIR");
		musicalInstruments.add("CELLO");
		musicalInstruments.add("STRINGS");
		musicalInstruments.add("BASS");
		musicalInstruments.add("ACOUSTIC_GUITAR");
		musicalInstruments.add("ACOUSTIC_GRAND");
		musicalInstruments.add("FLUTE");
		musicalInstruments.add("FANTASIA");
		musicalInstruments.add("PAN_FLUTE");
		}
		List<Integer> instrumentNumbers = new ArrayList<Integer>();

		for (int x = 0; x < musicalInstruments.size(); x++) {
			instrumentNumbers.add(x);
		}

		int listSize = musicalInstruments.size();
		int instrumentInt = closest(determiningNumber, instrumentNumbers);
		int selectedInstrument = 99999;
		try {
			Field inNum = jm.constants.ProgramChanges.class
					.getDeclaredField(musicalInstruments.get(instrumentInt));
			selectedInstrument = inNum.getInt(null);

		} catch (NoSuchFieldException | SecurityException
				| IllegalArgumentException | IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		if (selectedInstrument == 99999) {
			selectedInstrument = 8;
		}

		Part inst = new Part(musicalInstruments.get(instrumentInt),
				selectedInstrument, 0);
		System.out.println(musicalInstruments.get(instrumentInt) + " : "
				+ selectedInstrument);
		return inst;
	}

	public int[] getScale(int determiningNumber) {
		ArrayList<String> scales = new ArrayList<String>();

		scales.add("LYDIAN_SCALE");
		scales.add("MIXOLYDIAN_SCALE");
		scales.add("BLUES_SCALE");
		scales.add("MAJOR_SCALE");
		scales.add("PENTATONIC_SCALE");
		scales.add("DORIAN_SCALE");
		scales.add("MELODIC_MINOR_SCALE");
		scales.add("AEOLIAN_SCALE");
		scales.add("TURKISH_SCALE");
		scales.add("HARMONIC_MINOR_SCALE");
		List<Integer> scalesNumbers = new ArrayList<Integer>();
		for (int x = 0; x < scales.size(); x++) {
			scalesNumbers.add(x);
		}

		int listSize = scales.size();
		int scaleInt = closest(determiningNumber, scalesNumbers);
		int[] selectedScale = { 0, 1, 3, 7, 8 };
		int[] selectedScale2 = { 0, 1, 3, 7, 8 };

		try {
			Field inNum = jm.constants.Scales.class.getDeclaredField(scales
					.get(scaleInt));
			selectedScale = (int[]) inNum.get(null);

		} catch (NoSuchFieldException | SecurityException
				| IllegalArgumentException | IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		System.out.println(scales.get(scaleInt) + " "
				+ Arrays.toString(selectedScale));
		return selectedScale;
	}

	public ArrayList<Integer> getPitches() {
	

		int[] scaleToUse = getScale(83);
		ArrayList<String> pitchArray = new ArrayList<String>();
		ArrayList<Integer> scaleToUseArrayList = new ArrayList<Integer>();
		for (int x = 0; x < scaleToUse.length; x++) {
			scaleToUseArrayList.add(scaleToUse[x]);
		}
		pitchArray = getPitchArray();
		int var;
		ArrayList<String> useThisPitchArray = new ArrayList<String>();

		for (int x = 0; x < pitchArray.size(); x++) {
			int num = 12 - scaleToUse[scaleToUse.length - 1];
			for (int w = 0; w < scaleToUse.length; w++) {
				if (scaleToUseArrayList.contains(x % 12)) {
					if (!useThisPitchArray.contains(pitchArray.get(x))) {
						useThisPitchArray.add(pitchArray.get(x));
					}
				}
			}
		}
		System.out.println(useThisPitchArray.toString());
		 
		ArrayList<Integer> useThisPitchArrayInt = new ArrayList<Integer>();
		for(int h = 0; h<useThisPitchArray.size(); h++){
		try {
			Field inNum = jm.constants.ProgramChanges.class
					.getDeclaredField(useThisPitchArray.get(h));
			useThisPitchArrayInt.add(inNum.getInt(null));

		} catch (NoSuchFieldException | SecurityException
				| IllegalArgumentException | IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		}
		return useThisPitchArrayInt;
		
		// SHAPE

		// SIZE
		
		
	}

	public void getNumberOfNotes() {

	}

	public void getNoteLengths() {
	}

	public void getNoteAmplitudes() {
	}

	public void getChordProgression() {
	}

	public int closest(int of, List<Integer> in) {
		int min = Integer.MAX_VALUE;
		int closest = of;

		for (int v : in) {
			final int diff = Math.abs(v - of);

			if (diff < min) {
				min = diff;
				closest = v;
			}
		}

		return closest;
	}

	public ArrayList<String> getPitchArray() {
		ArrayList<String> pitches = new ArrayList<String>();
		pitches.add("C4");
		pitches.add("CS4");
		pitches.add("D4");
		pitches.add("DS4");
		pitches.add("E4");
		pitches.add("F4");
		pitches.add("FS4");
		pitches.add("G4");
		pitches.add("GS4");
		pitches.add("A4");
		pitches.add("AS4");
		pitches.add("B4");
		pitches.add("C5");
		pitches.add("CS5");
		pitches.add("D5");
		pitches.add("DS5");
		pitches.add("E5");
		pitches.add("F5");
		pitches.add("FS5");
		pitches.add("G5");
		pitches.add("GS5");
		pitches.add("A5");
		pitches.add("AS5");
		pitches.add("B5");
		pitches.add("C6");

		return pitches;
	}

}
