public class Triplet<L, M, R> {
    private L left;
    private M middle;
    private R right;

    public Triplet(L left, M middle, R right) {
        assert left != null;
        assert right != null;

        this.left = left;
        this.middle = middle;
        this.right = right;
    }

    public L getLeft() { return left; }
    public M getMiddle() {
        return middle;
    }
    public R getRight() { return right; }

    public void setLeft(L left) {
        this.left = left;
    }
    public void setMiddle(M middle) {
        this.middle = middle;
    }
    public void setRight(R right) {
        this.right = right;
    }

    @Override
    public int hashCode() {
        return left.hashCode() ^ middle.hashCode() ^ right.hashCode();
    }

    @Override
    public boolean equals(Object object) { //
        if (!(object instanceof Triplet)) return false;
        Triplet union = (Triplet) object;
        return this.left.equals(union.getLeft()) && this.middle.equals(union.getMiddle()) &&
                this.right.equals(union.getRight());
    }

}
