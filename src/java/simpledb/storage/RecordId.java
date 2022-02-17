package simpledb.storage;

import java.io.Serializable;
import java.util.Objects;

/**
 * A RecordId is a reference to a specific tuple on a specific page of a
 * specific table.
 */
public class RecordId implements Serializable {

    private static final long serialVersionUID = 1L;
    private PageId pid;
    private int tupleno;
    /**
     * Creates a new RecordId referring to the specified PageId and tuple
     * number.
     * 
     * @param pid
     *            the pageid of the page on which the tuple resides
     * @param tupleno
     *            the tuple number within the page.
     */
    public RecordId(PageId pid, int tupleno) {
        this.pid = pid;
        this.tupleno = tupleno;
    }

    /**
     * @return the tuple number this RecordId references.
     */
    public int getTupleNumber() {
        return this.tupleno;
    }

    /**
     * @return the page id this RecordId references.
     */
    public PageId getPageId() {
        return this.pid;
    }

    /**
     * Two RecordId objects are considered equal if they represent the same
     * tuple.
     * 
     * @return True if this and o represent the same tuple
     */
    @Override
    public boolean equals(Object o) {
        //先判断是不是自己,提高运行效率
        if (this == o)
            return true;

        //再判断是不是Person类,提高代码的健壮性
        if (o instanceof RecordId) {

            //向下转型,父类无法调用子类的成员和方法
            RecordId oPerson = (RecordId) o;

            //最后判断类的所有属性是否相等，其中String类型和Object类型可以用相应的equals()来判断
            if ((this.getTupleNumber()==oPerson.getTupleNumber()) && (this.getPageId().equals(oPerson.getPageId())) )
                return true;
        }
        else {
            return false;
        }

        return false;
    }

    /**
     * You should implement the hashCode() so that two equal RecordId instances
     * (with respect to equals()) have the same hashCode().
     * 
     * @return An int that is the same for equal RecordId objects.
     */
    @Override
    public int hashCode() {
        return  Objects.hash(pid,tupleno);
    }

}
