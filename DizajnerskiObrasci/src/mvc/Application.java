package mvc;

public class Application {

	public static void main(String[] args) {
		System.out.println("Dobrodošli na vežbe iz predmeta Dizajnerski obrasci.");

		DrawingModel model = new DrawingModel();
		DrawingFrame frame = new DrawingFrame();

		frame.getView().setModel(model);
		DrawingController controller = new DrawingController(model, frame);
		frame.setController(controller);

		/*
		 * Drawing drawingPanel = new Drawing();
		 * frame.getContentPane().add(drawingPanel); frame.setVisible(true);
		 */

	}

}
