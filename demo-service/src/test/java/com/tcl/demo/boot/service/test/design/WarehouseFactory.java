package com.tcl.demo.boot.service.test.design;

public class WarehouseFactory {


    public Warehouse acquireWarehouse(Integer warehouseType) {

        if (warehouseType == 1) {


            return new DefaultWarehouse(1);

        } else {


            throw new RuntimeException("未匹配对应仓库类");
        }


    }


    public static void main(String[] args) {

        WarehouseFactory factory = new WarehouseFactory();

        // 拿到具体实现
        Warehouse warehouse = factory.acquireWarehouse(1);

        // 拿
        Entry entry = warehouse.out("1");

        //放
        warehouse.in(entry);
    }
}
