package com.eres.waiter.waiter.viewpager.helper;


import java.util.ArrayList;
import java.util.Collection;
import java.util.function.Predicate;

public class ObservableCollection<T> extends ArrayList<T> {
    public enum NotifyCollectionChangedAction
    {
        Add,Remove,Replace,Move,Reset,Clear
    }
    public interface CollectionChangeListener
    {
        void onCollectionChange(NotifyCollectionChangedAction action, Object obj, long position);
    }
    private  CollectionChangeListener collectionChangeListener;

    public void setCollectionChangeListener(CollectionChangeListener collectionChangeListener) {
        this.collectionChangeListener = collectionChangeListener;
    }
    private void  onCollectionChange(NotifyCollectionChangedAction action,Object obj,long position)
    {
        if(collectionChangeListener!=null)
            collectionChangeListener.onCollectionChange(action,obj,position);
    }

    @Override
    public boolean add(T t) {
         boolean ret=super.add(t);
         onCollectionChange(NotifyCollectionChangedAction.Add,t,-1);
         return ret;
    }

    @Override
    public boolean addAll(Collection<? extends T> c) {
        boolean ret= super.addAll(c);
        onCollectionChange(NotifyCollectionChangedAction.Add,c,-1);
        return ret;
    }

    @Override
    public boolean addAll(int index, Collection<? extends T> c) {
        boolean ret= super.addAll(index, c);
        onCollectionChange(NotifyCollectionChangedAction.Add,c,index);
        return ret;
    }

    @Override
    public void add(int index, T element) {
        super.add(index, element);
        onCollectionChange(NotifyCollectionChangedAction.Add,element,index);
    }

    @Override
    public T remove(int index) {
        T ret= super.remove(index);
        onCollectionChange(NotifyCollectionChangedAction.Remove,null,index);
        return ret;
    }

    @Override
    public boolean remove(Object o) {
        boolean ret= super.remove(o);
        onCollectionChange(NotifyCollectionChangedAction.Remove,o,-1);
        return ret;

    }

    @Override
    public boolean removeAll(Collection<?> c) {
        boolean ret= super.removeAll(c);
        onCollectionChange(NotifyCollectionChangedAction.Remove,c,-1);
        return ret;

    }

    @Override
    public boolean removeIf(Predicate<? super T> filter) {
        boolean ret= false;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
            ret = super.removeIf(filter);
            onCollectionChange(NotifyCollectionChangedAction.Remove,null,-1);
        }

        return ret;

    }

    @Override
    public void clear() {
        super.clear();
        onCollectionChange(NotifyCollectionChangedAction.Clear,null,-1);
    }

    @Override
    public T set(int index, T element) {
        T ret= super.set(index, element);
        onCollectionChange(NotifyCollectionChangedAction.Replace,element,index);
        return ret;
    }
}
