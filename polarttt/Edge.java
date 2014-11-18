package polarttt;

public class Edge {
	private static final int RADIAL = 0;
	private static final int ARC = 1;
	private static final int SPIRAL = 2;

	int type;
	int color; // shows what player the edge belongs to
	Node start;
	Node end;
	// order is arbitrary as to which is start and end

	public Edge(Node start, Node end, int color) {
		this.start = start;
		this.end = end;
		this.color = color;

		setType();
	}

	/** Determines and assigns the type of this Edge (radial, arc, spiral) */
	private void setType() {
		if (((start.getX() == end.getX() + 1) || (start.getX() == end.getX() - 1))
				&& (start.getY() == end.getY())) {
			type = RADIAL;
		} else if (((start.getY() == end.getY() + 1) || (start.getY() == end
				.getY() - 1)) && (start.getX() == end.getX())) {
			type = ARC;
		} else {
			type = SPIRAL;
		}

	}

	public boolean equals(Object o) {
		if (o == null) {
			return false;
		} else if (this.getClass() != o.getClass()) {
			return false;
		} else if ((this.start != ((Edge) o).getStart())
				|| (this.end != ((Edge) o).getEnd())) {
			return false;
		}

		return true;
	}

	public String toString() {
		return ("[" + start.toString() + "->" + end.toString() + ": " + type + "]");
	}

	public int getType() {
		return type;
	}

	public int getColor() {
		return color;
	}

	public Node getStart() {
		return start;
	}

	public Node getEnd() {
		return end;
	}

}
