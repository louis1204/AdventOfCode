import java.lang.Integer;
import java.io.*;
import java.util.*;
import javafx.util.Pair;

// I've spent 3 hours on this, I'm done.
public class Day18 {
    private static class Node {
        Node left;
        Node right;
        Integer value = null;
        
        public String dfsToString() {
            if (value != null) {
                return "" + value;
            }
            return "[" + (left == null ? "not set yet" : left.dfsToString())
                 + "," + (right == null ? "not set yet" : right.dfsToString()) + "]";
        }

        @Override
        public String toString() {
            return "" + value;
        }
    }

    public static class NodeBuilder {
        Node node = new Node();

        public NodeBuilder left(Node node) {
            this.node.left = node;
            return this;
        }

        public NodeBuilder right(Node node) {
            this.node.right = node;
            return this;
        }

        public NodeBuilder value(Integer value) {
            this.node.value = value;
            return this;
        }

        public Node build() {
            return node;
        }
    }

    public static void doDay18() throws Exception {
        File file = new File("input18-test.txt");    //creates a new file instance  
        FileReader fr = new FileReader(file);   //reads the file  
        BufferedReader br = new BufferedReader(fr);  //creates a buffering character input stream  

        // How to represent this?
        // Keep as string? Messy
        // Can think of a rescursive solution
        // Need to create a data structure that holds the pair, number. Also it's depth?
        // We can calculate depth recursively, then it won't be so messy as well
        // When we do an add, we create a new head and set the left to be the old head and the right
        // to be the adding head
        // To do the actual add, we can do a recursion and if we hit the depth of 4, we let the parent
        // add the number to the left and right?
        // Or we can look at the string. Maintain the order of the numbers, then when we hit a depth of
        // 4 we add the left to the left number if there is any, and to the right number if there is any
        // Then we set the node's value to be 0 and set the left right values to null
        // Replace the pair in the orderings list

        // This assumes that depths will not exceed 4, and initial input does not have numbers over 9
        // 1. Create a DS that represends a node
        // class Node { Node left; Node right; Integer value (nullable if actually a pair); }
        // 2. Create a HashMap of depth to node (a node that explodes will no longer be 4, and a node that
        // has depth 4 will not be affected by other nodes exploding)
        // 3. We'd also need to create a set of pairs to explode that exceed the value of 9
        // 4. After exploding we don't need to delete anything. We just set the node's left and right to null
        // and set the node's value to be 0.
        String line;
        Node head = null;
        List<Node> order = new ArrayList<>();
        while ((line = br.readLine()) != null) {
            if (head == null) {
                // We're not doing an add, so just construct our data
                head = construct(line.toCharArray());
                constructOrder(head, order);
                reduceDepth(head, 0, order);
                System.out.println(head.dfsToString());
            } else {
                // Reduce new string
                Node adder = construct(line.toCharArray());
                List<Node> newOrder = new ArrayList<>();
                constructOrder(adder, newOrder);
                reduceDepth(adder, 1, newOrder);

                // Add to old and reduce
                Node newHead = new Node();
                newHead.left = head;
                newHead.right = adder;
                head = newHead;
                newOrder = new ArrayList<>();
                constructOrder(head, newOrder);
                reduceDepth(head, 1, newOrder);
                System.out.println(head.dfsToString());
                return;
            }
        }
    }

    private static void constructOrder(Node node, List<Node> order) {
        if (node.value != null) {
            order.add(node);
            return;
        }
        constructOrder(node.left, order);
        constructOrder(node.right, order);
    }

    private static void reduceDepth(Node node, int depth, List<Node> order) {
        if (depth == 4) {
            // Assume the max depth at any given point is 4
            // Go through the order and add to values nodes to the left and right if any
            for (int i = 0; i < order.size(); i++) {
                Node curr = order.get(i);
                if (curr == node.left) {
                    if (i > 1) {
                        order.get(i - 1).value += node.left.value;
                    }
                    continue;
                }
                if (curr == node.right) {
                    if (i < order.size() - 1) {
                        order.get(i + 1).value += node.right.value;
                    }
                    continue;
                }
            }
            node.left = null;
            node.right = null;
            node.value = 0;
            return;
        }
        if (node.value == null) {
            // We are at a parent, so go down the depth chart
            reduceDepth(node.left, depth + 1, order);
            reduceDepth(node.right, depth + 1, order);
        }
    }

    private static Node construct(char[] chars) {
        // If we see a [ then we create a new node, if we see a number then we set that to be the node's value
        // If we see a ] or a , then we pop from the stack
        Stack<Node> stack = new Stack<>();
        Node root = null;
        for (int i = 0; i < chars.length; i++) {
            char ch = chars[i];
            if (ch == '[' && (root == null || stack.isEmpty())) {
                Node left = new Node();
                Node right = new Node();
                // Now we have the parent, right and left in the stack
                Node newRoot = new NodeBuilder().left(left).right(right).build();
                if (root == null) {
                    root = newRoot;
                }
                stack.push(newRoot);
                stack.push(right);
                stack.push(left);
                continue;
            }
            if (ch == '[') {
                // unbox the previous insert
                Node left = new Node();
                stack.peek().left = left;
                Node right = new Node();
                stack.peek().right = right;
                stack.push(right);
                stack.push(left);
            }
            if (ch - '0' >= 0 && ch - '0' <= 9) {
                stack.peek().value = Integer.parseInt("" + ch);
                stack.pop(); // we can pop stack since < 10
            }
            if (ch == ',') {
                // ignore these
                continue;
            }
            if (ch == ']') {
                // Otherwise, we have ended this run
                stack.pop(); // pop the parent
            }
        }
        return root;
    }
}