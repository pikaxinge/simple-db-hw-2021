package simpledb.storage;

import simpledb.common.DbException;
import simpledb.common.Permissions;
import simpledb.transaction.TransactionAbortedException;
import simpledb.transaction.TransactionId;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class HeapFileIterator implements DbFileIterator{
    HeapFile heapFile;
    TransactionId tid;
    Iterator<Tuple> i = null;
    Iterable<Tuple> tuples = null;
    int offset = 0;int tableId;
    public HeapFileIterator(HeapFile heapFile, TransactionId tid) {
        this.tableId = heapFile.getId();
        this.heapFile = heapFile;
        this.tid = tid;
    }
    HeapPageId id0 = new HeapPageId(tableId,offset);
    HeapPage begin;
    @Override
    public void open() throws DbException, TransactionAbortedException {
        begin= (HeapPage) BufferPool.getPage(tid,id0, Permissions.READ_ONLY);
        //check if exits
        if(begin==null){
            //move pages to memory
            begin = (HeapPage) heapFile.readPage(id0);
        }
        i = begin.iterator();
    }

    @Override
    public boolean hasNext() throws DbException, TransactionAbortedException {
        if(i==null) return false;
//                if(!i.hasNext()){ //try to locate the next page
//                    id0 = new HeapPageId(tableId,++offset);
//                    open();
//                    if(begin==null) return false;
//                }
//                return true;
        return i.hasNext();
    }

    @Override
    public Tuple next() throws DbException, TransactionAbortedException, NoSuchElementException {
        if(hasNext()) return i.next();
        throw new NoSuchElementException();
    }

    @Override
    public void rewind() throws DbException, TransactionAbortedException {
        offset = 0;
        id0 = new HeapPageId(tableId,offset);
        begin = (HeapPage) BufferPool.getPage(tid,id0, Permissions.READ_ONLY);
        i = begin.iterator();
    }

    @Override
    public void close() {
        i = null;
    }

}
