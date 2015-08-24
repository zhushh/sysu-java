public class SparseGridNode
{
    private Object occupant;
    private int col;
    private SparseGridNode next;

    public SparseGridNode()
    {
        occupant = null;
        col = -1;
        next = null;
    }

    public SparseGridNode(Object obj, int c, SparseGridNode n)
    {
        occupant = obj;
        col = c;
        next = n;
    }

    public SparseGridNode getNext()
    {
        return next;
    }

    public void setNext(SparseGridNode obj)
    {
        next = obj;
    }

    public int getCol()
    {
        return col;
    }

    public void setCol(int c)
    {
        col = c;
    }

    public Object getOccupied()
    {
        return occupant;
    }

    public void setOccupied(Object obj)
    {
        occupant = obj;
    }
}