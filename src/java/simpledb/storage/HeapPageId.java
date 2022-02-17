package simpledb.storage;

/** Unique identifier for HeapPage objects. */
public class HeapPageId implements PageId {
    private int tableId, pgNo;

    /**
     * Constructor. Create a page id structure for a specific page of a
     * specific table.
     *
     * @param tableId The table that is being referenced
     * @param pgNo The page number in that table.
     */
    public HeapPageId(int tableId, int pgNo) {
        this.tableId = tableId;
        this.pgNo = pgNo;
    }

    /** @return the table associated with this PageId */
    public int getTableId() {
        return tableId;
    }

    /**
     * @return the page number in the table getTableId() associated with
     *   this PageId
     */
    public int getPageNumber() {
        return this.pgNo;
    }

    /**
     * @return a hash code for this page, represented by a combination of
     *   the table number and the page number (needed if a PageId is used as a
     *   key in a hash table in the BufferPool, for example.)
     * @see BufferPool
     */
    public int hashCode() {
        int result =12;
        result = 31*result + this.pgNo;
        return 31*result +this.tableId;
    }

    /**
     * Compares one PageId to o.
     *
     * @param o The object to compare against (must be a PageId)
     * @return true if the objects are equal (e.g., page numbers and table
     *   ids are the same)
     */
    public boolean equals(Object o) {
        //先判断是不是自己,提高运行效率
        if (this == o)
            return true;

        //再判断是不是Person类,提高代码的健壮性
        if (o instanceof HeapPageId) {

            //向下转型,父类无法调用子类的成员和方法
            HeapPageId oPerson = (HeapPageId) o;

            //最后判断类的所有属性是否相等，其中String类型和Object类型可以用相应的equals()来判断
            if ((this.getTableId()==oPerson.getTableId()) && (this.getPageNumber() == oPerson.getPageNumber()))
                return true;
            }
        else {
            return false;
        }

        return false;
    }

    /**
     *  Return a representation of this object as an array of
     *  integers, for writing to disk.  Size of returned array must contain
     *  number of integers that corresponds to number of args to one of the
     *  constructors.
     */
    public int[] serialize() {
        int[] data = new int[2];

        data[0] = getTableId();
        data[1] = getPageNumber();

        return data;
    }

}
