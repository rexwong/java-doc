package com.rexwong.algorithm;

import java.util.Comparator;

/**
 * 红黑树的特性:
 * <ol>每个节点或者是黑色，或者是红色。</ol>
 * <ol>根节点是黑色。</ol>
 * <ol>每个叶子节点（NIL）是黑色。 [注意：这里叶子节点，是指为空(NIL或NULL)的叶子节点！</ol>
 * <ol>如果一个节点是红色的，则它的子节点必须是黑色的。</ol>
 * <ol>从一个节点到该节点的子孙节点的所有路径上包含相同数目的黑节点。</ol>
 *
 * http://www.cs.usfca.edu/~galles/visualization/RedBlack.html
 *
 * @param <V>
 */
public class RBtree<V> {

    private static final boolean RED   = false;
    private static final boolean BLACK = true;

    private transient Node<V> root;//根节点

    private final Comparator<? super V> comparator;

    public RBtree() {
        comparator = null;
    }

    static final class Node<V>{
        V value;
        Node<V> left;
        Node<V> right;
        Node<V> parent;
        boolean color = BLACK;

        Node(V value, Node<V> parent) {
            this.value = value;
            this.parent = parent;
        }
        public V getValue() {
            return value;
        }

        public V setValue(V value) {
            V oldValue = this.value;
            this.value = value;
            return oldValue;
        }
    }

    public V putNode(V value) {
        Node<V> t = root;
        if (t == null) {
            root = new Node(value, null);
            return null;
        }
        int cmp;
        Node<V> parent;
        Comparator<? super V> cpr = comparator;
        if (cpr != null) {
            do {
                parent = t;
                cmp = cpr.compare(value, t.value);
                if (cmp < 0)
                    t = t.left;
                else if (cmp > 0)
                    t = t.right;
                else
                    return t.setValue(value);
            } while (t != null);
        }
        else {
            if (value == null)
                throw new NullPointerException();
            Comparable<? super V> k = (Comparable<? super V>) value;
            do {
                parent = t;
                cmp = k.compareTo(t.value);
                if (cmp < 0)
                    t = t.left;
                else if (cmp > 0)
                    t = t.right;
                else
                    return t.setValue(value);
            } while (t != null);
        }
        Node<V> e = new Node(value, parent);
        if (cmp < 0)
            parent.left = e;
        else
            parent.right = e;
        fixAfterInsertion(e);
        return null;
    }

    /**
     * 参完后修复Node颜色
     *
     * 当前节点的父节点是红色，且当前节点的祖父节点的另一个子节点（叔叔节点）也是红色。
     *    (01) 将“父节点”设为黑色。
     *    (02) 将“叔叔节点”设为黑色。
     *    (03) 将“祖父节点”设为“红色”。
     *    (04) 将“祖父节点”设为“当前节点”(红色节点)；即，之后继续对“当前节点”进行操作。
     *
     * 当前节点的父节点是红色，叔叔节点是黑色，且当前节点是其父节点的右孩子
     *    (01) 将“父节点”作为“新的当前节点”。
     *    (02) 以“新的当前节点”为支点进行左旋。
     *
     * 当前节点的父节点是红色，叔叔节点是黑色，且当前节点是其父节点的左孩子
     *    (01) 将“父节点”设为“黑色”。
     *    (02) 将“祖父节点”设为“红色”。
     *    (03) 以“祖父节点”为支点进行右旋。
     * @param x
     */
    private void fixAfterInsertion(Node<V> x) {
        //插入节点默认红色
        x.color = RED;
        
        //关键点：当前节点的父节点是红色，且不是根节点
        while (x != null && x != root && x.parent.color == RED) {

            //如果父亲节点是左节点
            if (parentOf(x) == leftOf(parentOf(parentOf(x)))) {
                Node<V> rUncle = rightOf(parentOf(parentOf(x)));
                //右叔叔节点是红色
                if (colorOf(rUncle) == RED) {
                    setColor(parentOf(x), BLACK);//将“父节点”设为黑色。
                    setColor(rUncle, BLACK);//将“叔叔节点”设为黑色。
                    setColor(parentOf(parentOf(x)), RED);//将“祖父节点”设为“红色”。
                    x = parentOf(parentOf(x));//爷爷节点复制继续循环操作
                }
                //右叔叔节点是黑色
                else {
                    if (x == rightOf(parentOf(x))) {
                        x = parentOf(x);
                        rotateLeft(x);
                    }
                    setColor(parentOf(x), BLACK);
                    setColor(parentOf(parentOf(x)), RED);
                    rotateRight(parentOf(parentOf(x)));
                }
            }
            //如果父亲节点是右节点的情况
            else {
                Node<V> lUncle = leftOf(parentOf(parentOf(x)));
                if (colorOf(lUncle) == RED) {
                    setColor(parentOf(x), BLACK);
                    setColor(lUncle, BLACK);
                    setColor(parentOf(parentOf(x)), RED);
                    x = parentOf(parentOf(x));
                }
                else {
                    if (x == leftOf(parentOf(x))) {
                        x = parentOf(x);
                        rotateRight(x);
                    }
                    setColor(parentOf(x), BLACK);
                    setColor(parentOf(parentOf(x)), RED);
                    rotateLeft(parentOf(parentOf(x)));
                }
            }
        }
        root.color = BLACK;
    }

    private static <V> Node<V> parentOf(Node<V> p) {
        return (p == null ? null: p.parent);
    }
    private static <V> boolean colorOf(Node<V> node) {
        return (node == null ? BLACK : node.color);
    }
    private static <V> Node<V> leftOf(Node<V> p) {
        return (p == null) ? null: p.left;
    }

    private static <V> Node<V> rightOf(Node<V> p) {
        return (p == null) ? null: p.right;
    }

    private static void setColor(Node p, boolean color) {
        if (p != null)
            p.color = color;
    }
    /** From CLR */
    private void rotateLeft(Node<V> p) {
        if (p != null) {
            Node<V> r = p.right;
            p.right = r.left;
            if (r.left != null)
                r.left.parent = p;
            r.parent = p.parent;
            if (p.parent == null)
                root = r;
            else if (p.parent.left == p)
                p.parent.left = r;
            else
                p.parent.right = r;
            r.left = p;
            p.parent = r;
        }
    }

    /** From CLR */
    private void rotateRight(Node<V> p) {
        if (p != null) {
            Node<V> l = p.left;
            p.left = l.right;
            if (l.right != null) l.right.parent = p;
            l.parent = p.parent;
            if (p.parent == null)
                root = l;
            else if (p.parent.right == p)
                p.parent.right = l;
            else p.parent.left = l;
            l.right = p;
            p.parent = l;
        }
    }

    public static void main(String[] args) {

    }
}
