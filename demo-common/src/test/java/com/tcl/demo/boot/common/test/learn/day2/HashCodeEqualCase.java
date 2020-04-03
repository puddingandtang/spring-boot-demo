package com.tcl.demo.boot.common.test.learn.day2;

import com.tcl.demo.boot.common.test.learn.AClass;
import org.junit.Test;

import java.util.Objects;

public class HashCodeEqualCase {

    @Test
    public void defaultHashCodeEquals() {

        EntryDefault entryDefault1 = new EntryDefault();
        entryDefault1.setId(1L);
        entryDefault1.setName("1");

        System.out.println(entryDefault1.hashCode());

        EntryDefault entryDefault2 = new EntryDefault();
        entryDefault2.setId(1L);
        entryDefault2.setName("1");

        System.out.println(entryDefault2.hashCode());

        // 比较内存地址
        System.out.println(entryDefault1 == entryDefault2);
        System.out.println(entryDefault1.equals(entryDefault2));

    }

    @Test
    public void case1HashCodeEquals(){

        Entry entry1 = new Entry();
        entry1.setId(1L);
        entry1.setName("1");

        System.out.println(entry1.hashCode());

        Entry entry2 = new Entry();
        entry2.setId(1L);
        entry2.setName("1");

        System.out.println(entry2.hashCode());

        // 比较内存地址
        System.out.println(entry1 == entry2);
        // 比较内容
        System.out.println(entry1.equals(entry2));
    }

    @Test
    public void case2HashCodeEquals(){

        BClass x = new BClass(2L);
        BClass y = new BClass(2L);

        System.out.println(x.hashCode());
        System.out.println(y.hashCode());

        System.out.println(x == y);
        System.out.println(x.equals(y));


        /*
        BClass aClass1 = new BClass(1L);
        BClass aClass2 = new BClass(2L);
        BClass aClass3 = new BClass(2L);

        System.out.println(aClass2.hashCode());
        System.out.println(aClass3.hashCode());

        System.out.println(aClass2 == aClass3);

        EntryClass entryClass1 = new EntryClass();
        entryClass1.setName("name");
        entryClass1.setaClass(aClass1);

        System.out.println(entryClass1.hashCode());

        EntryClass entryClass2 = new EntryClass();
        entryClass2.setName("name");
        entryClass2.setaClass(aClass2);

        System.out.println(entryClass2.hashCode());

        EntryClass entryClass3 = new EntryClass();
        entryClass3.setName("name");
        entryClass3.setaClass(aClass3);

        System.out.println(entryClass3.hashCode());

        System.out.println(entryClass1.equals(entryClass2));
        System.out.println(entryClass1.equals(entryClass3));
        System.out.println(entryClass2.equals(entryClass3));
         */

    }
}


// 自定义hashCode，equals方法
class Entry {

    private Long id;

    private String name;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Entry)) return false;
        Entry entry = (Entry) o;
        return Objects.equals(id, entry.id) &&
                Objects.equals(name, entry.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }
}

// 默认hashCode，equals方法
class EntryDefault {

    private Long id;

    private String name;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

class EntryClass{

    private String name;

    private BClass aClass;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BClass getaClass() {
        return aClass;
    }

    public void setaClass(BClass aClass) {
        this.aClass = aClass;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof EntryClass)) return false;
        EntryClass that = (EntryClass) o;
        return Objects.equals(getName(), that.getName()) &&
                Objects.equals(getaClass(), that.getaClass());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName(), getaClass());
    }
}

class BClass{

    private Long seq;


    public BClass(Long seq) {
        this.seq = seq;
    }

    public Long getSeq() {
        return seq;
    }

    public void setSeq(Long seq) {
        this.seq = seq;
    }
}