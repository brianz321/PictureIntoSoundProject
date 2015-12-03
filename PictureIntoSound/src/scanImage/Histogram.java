package scanImage;

public class Histogram {
	int[] histogram;
	int[][] histogramValues;
	
	public Histogram(){
		
	}
	public Histogram(int[] histogram){
		this.histogram = histogram;
	}
	public Histogram(int[] histogram, int[][] histogramValues){
		this.histogram = histogram;
		this.histogramValues = histogramValues;
	}
	public int[] getHistogram() {
		return histogram;
	}
	public int[][] getHistogramValues() {
		return histogramValues;
	}
	public void setHistogramValues(int[][] histogramValues) {
		this.histogramValues = histogramValues;
	}
	public void setHistogram(int[] histogram) {
		this.histogram = histogram;
	}
	public Histogram createHistogram(int[][] histogramValues){
		Histogram hist = new Histogram();
		int[] histValues = new int[256];
		for(int i = 0; i < histogramValues.length; i++){
			for(int j = 0; j < histogramValues[0].length; j++){
				histValues[histogramValues[i][j]]++;
			}
		}
		hist.setHistogram(histValues);
		return hist;
	}
}
