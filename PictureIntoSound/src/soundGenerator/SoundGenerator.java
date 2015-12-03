package soundGenerator;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import scanImage.ColorList;
import jm.JMC;
import jm.music.data.*;
import jm.util.Play;
import jm.util.Write;

public final class SoundGenerator implements JMC {

	public SoundGenerator() {
	}

	public void playSound() {
		Score modeScore = new Score("Picture Melody");

		// INSTRUMENT
		Part inst = new Part("Guitar", SGUITAR, 0);

		// SCALE
		// PITCH
		// NUMBER OF NOTES
		// NOTE LENGTH
		// NOTE AMPLITUDE

		// create a middle C minim (half note)
		Note n = new Note(C4, MINIM);

		// create a phrase
		Phrase phr = new Phrase();

		// put the note inside the phrase
		phr.addNote(n);

		// pack the phrase into a part
		Part p = new Part();
		p.addPhrase(phr);

		// pack the part into a score titled 'Bing'
		Score s = new Score("Bing");
		s.addPart(p);

		// write the score as a MIDI file to disk
		// Write.midi(s,"Bing.mid");

		Play.midi(s);
	}

	public Part getInstrument(int determiningNumber) {
		ArrayList<String> musicalInstruments = new ArrayList<String>();
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

	public void getPitches() {
		// COLOR
		ArrayList<String> colors = new ArrayList<String>();
		ColorList cl = new ColorList();
		cl.initColorList();
		colors = cl.getColorsArray();

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
