package com.yarenty.tree;


public class BinaryTree <K extends Comparable,V>{

    Node root;

    public void addNode(K key, V name) {
        Node newNode = new Node(key, name);


        if (root== null) {
            root = newNode;
        } else {
            Node focus = root;
            Node parent;

            while(true) {
                parent = focus;

                if (key.compareTo(focus.key)<0) {
                    focus = focus.leftChild;
                    if (focus == null) {
                        parent.leftChild = newNode;
                        return;
                    }
                }  else {
                    focus = focus.rightChild;

                    if (focus==null) {
                        parent.rightChild = newNode;
                        return;
                    }
                }
            }
        }
    }



    public V find(K key){
        Node focus = root;

        while(!focus.key.equals(key)){
            if (key.compareTo(focus.key)<0) {
                focus = focus.leftChild;
            } else {
                focus = focus.rightChild;
            }

            if (focus == null )
                return null;
        }


        return (V) focus.name;
    }

    public void inOrderTraverseTree(Node focus){
        if (focus != null) {
            inOrderTraverseTree(focus.leftChild);
            System.out.println(focus);
            inOrderTraverseTree(focus.rightChild);
        }
    }

    public void preOrderTraverseTree(Node focus){
        if (focus != null) {
            System.out.println(focus);
            preOrderTraverseTree(focus.leftChild);
            preOrderTraverseTree(focus.rightChild);
        }
    }

    public void postOrderTraverseTree(Node focus){
        if (focus != null) {
            postOrderTraverseTree(focus.leftChild);
            postOrderTraverseTree(focus.rightChild);
            System.out.println(focus);
        }

    }




    class Node<K,V> {
        K key;
        V name;

        Node leftChild;
        Node rightChild;


        public Node (K key, V name) {
            this.key=key;
            this.name= name;
        }


        @Override
        public String toString() {
            return "Node [key=" + key + ", name=" + name + "]";
        }


    }

}
