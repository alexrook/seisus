package seisus.li.parser;

import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;
import seisus.model.DirVector;

/**
 * @author moroz
 * @param <E>
 */
public abstract class AbstractDirVectorParser<E extends DirVector> implements Iterable<E>, Iterator<E> {

    private final Class<E> clazz;
    
    private long projectId=0;
    
    public  class VectorRecord {
       long projectId;
       long frecuencyId;
       E vector;
    }
    
    public AbstractDirVectorParser(Class<E> clazz) {
        this.clazz=clazz;
    }
    
    public void setProjectId(long id){
        this.projectId=id;
    }
    
    
    public long getProjectId(){
        return projectId;
    }
    
    public E newDirVector() throws InstantiationException, IllegalAccessException{
        return clazz.newInstance();
    }
    
    
    private InputStream stream;

    public void setDataStream(InputStream stream) throws IOException {
        close();
        this.stream = stream;
    }

    public InputStream getDataStream() {
        return stream;
    }

    public void close() throws IOException {
        if (stream != null) {
            stream.close();
        }

    }

    @Override
    public Iterator<E> iterator() {
        return this;
    }

    @Override
    public boolean hasNext() {
        try {
            return hasNextVector();
        } catch (IOException ex) {
            return false;
        }
    }

    @Override
    public E next() {
        try {
           return nextVector();
        } catch (IOException e) {
            return null;
        }
    }

    public abstract boolean hasNextVector() throws IOException;

    public abstract E nextVector() throws IOException;

}
