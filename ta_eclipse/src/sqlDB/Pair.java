package sqlDB;

public class Pair<L, R> {
	L left;
	R right;

	public Pair(L l, R r) {
		this.left = l;
		this.right = r;
	}

	public L getLeft() {
		return left;
	}

	public R getRight() {
		return right;
	}
}