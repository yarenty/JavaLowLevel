package com.yarenty.tree;

import org.junit.Before;
import org.junit.Test;

public class BinaryTreeTest {


    BinaryTree<Integer, String> bt = new BinaryTree<Integer, String>();

    @Before
    public void setUp() throws Exception {
        bt.addNode(50, "Boss");
        bt.addNode(25, "Left25");
        bt.addNode(10, "Lef");
        bt.addNode(5, "Bdf");
        bt.addNode(60, "sdfsa");
        bt.addNode(70, "fds");
        bt.addNode(90, "fgfv");
        bt.addNode(30, "rte");
        bt.addNode(22, "vxcv");
    }


    @Test
    public final void test() {
        System.out.println("IN -------------");
        bt.inOrderTraverseTree(bt.root);

        System.out.println("PRE -----------");
        bt.preOrderTraverseTree(bt.root);

        System.out.println("POST ---------");
        bt.postOrderTraverseTree(bt.root);
    }

    @Test
    public final void find() {
        System.out.println(" 70 is ::" + bt.find(70));
    }

}
