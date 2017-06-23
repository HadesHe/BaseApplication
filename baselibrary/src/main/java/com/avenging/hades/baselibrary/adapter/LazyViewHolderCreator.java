package com.avenging.hades.baselibrary.adapter;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Modifier;

/**
 * __   __    _
 * \ \ / /_ _| |_   _ _ __
 * \ V / _` | | | | | '_ \
 * | | (_| | | |_| | | | |
 * |_|\__,_|_|\__,_|_| |_|
 * Created by Hades on 2017/6/23.
 */

public class LazyViewHolderCreator<T> implements ViewHolderCreator<T>{

    private final Constructor<?> mConstrucor;
    private Object[] mInstanceObjects;

    private LazyViewHolderCreator(Constructor<?> constructor,Object[] instanceObjects){
        this.mConstrucor=constructor;
        this.mInstanceObjects=instanceObjects;
    }

    public static <ItemDataType> ViewHolderCreator<ItemDataType> create(final Object enclosingInstance,
                                                                        final Class<?> cls,
                                                                        final Object... args){
        if(cls==null){
            throw new IllegalArgumentException("ViewHolder class is null");
        }

        boolean isEnclosingInstanceClass=false;
        if(cls.getEnclosingClass() != null&& !Modifier.isStatic(cls.getModifiers())){
            isEnclosingInstanceClass=true;
        }

        int argsLen=isEnclosingInstanceClass?args.length+1:args.length;
        final Object[] instanceObjects=new Object[argsLen];
        int copyStart=0;
        if(isEnclosingInstanceClass){
            instanceObjects[0]=enclosingInstance;
            copyStart=1;
        }
        if(args.length>0){
            System.arraycopy(args,0,instanceObjects,copyStart,args.length);
        }
        final Class[] parameterTypes=new Class[argsLen];
        for (int i = 0; i < instanceObjects.length; i++) {
            parameterTypes[i]=instanceObjects[i].getClass();
        }

        Constructor<?> constructor=null;
        try {
            constructor=cls.getDeclaredConstructor(parameterTypes);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }

        if(constructor==null){
            throw  new IllegalArgumentException("ViewHolderClass can not be initiated");
        }

        ViewHolderCreator lazyCreator=new LazyViewHolderCreator(constructor,instanceObjects);
        return lazyCreator;


    }
    @Override
    public ViewHolderBase<T> createViewHolder(int position) {
        Object object=null;


        try {
            boolean isAccessible=mConstrucor.isAccessible();
            if (!isAccessible) {
                mConstrucor.setAccessible(true);
            }
            object=mConstrucor.newInstance(mInstanceObjects);
            if (!isAccessible) {
                mConstrucor.setAccessible(false);
            }
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }

        if(object==null|| !(object instanceof ViewHolderBase)){
            throw new IllegalArgumentException("ViewHolderClass can not be initiated");
        }
        return (ViewHolderBase<T>) object;
    }
}
