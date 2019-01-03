package com.company;

public class BinaryTree {

    Node root;

    //public void  addNode(Node node)
    public void  addNode(int key, String name){
        Node newNode = new Node(key, name);

        if (root == null){
            root = newNode;
        } else {
            Node focusNode = root;
            Node parent;

            while(true){
                parent = focusNode;
                if(key < focusNode.key){
                    focusNode = focusNode.leftChild;

                    if (focusNode == null){
                        parent.leftChild = newNode;
                        return;
                    }
                } else {
                    focusNode = focusNode.rightChild;
                    if(focusNode == null) {
                        parent.rightChild = newNode;
                        return;
                    }
                }
            }
        }
    }

    public Node findNode(int key){
        Node focusNode = root;
        while (focusNode.key != key){
            if(key < focusNode.key){
                focusNode = focusNode.leftChild;
            } else {
                focusNode = focusNode.rightChild;
            }

            if (focusNode == null){
                return null;
            }
        }

        return focusNode;
    }

    public boolean remove(int key){
        Node focusNode = root;
        Node parent = root;

        boolean isLeft = true;

        while (focusNode.key != key){
            parent = focusNode;
            if (key <focusNode.key){
                isLeft = true;
                focusNode = focusNode.leftChild;
            } else {
                isLeft = false;
                focusNode = focusNode.rightChild;
            }

            if (focusNode == null) {
                return false;
            }
        }

        if( focusNode.leftChild == null && focusNode.rightChild == null){
            if(focusNode == root){
                root = null;
            } else {
                if (isLeft){
                    parent.leftChild = null;
                } else {
                    parent.rightChild = null;
                }
            }
        } else {
            if(focusNode.rightChild == null){
                if(focusNode == root){
                    root = focusNode.leftChild;
                } else {
                    if(isLeft){
                        parent.leftChild = focusNode.leftChild;
                    }
                }
            }
            else {
                if (focusNode.leftChild == null){
                    if (focusNode == root){
                        root = focusNode.rightChild;
                    } else {
                        if (isLeft){
                            parent.leftChild = focusNode.rightChild;
                        } else {
                            Node replacement = getReplacementNode(focusNode);

                            if(focusNode == root){
                                root = replacement;
                            } else{
                                if(isLeft){
                                    parent.leftChild = replacement;

                                } else {
                                    parent.rightChild = replacement;
                                }
                            }
                            replacement.leftChild = focusNode.leftChild;
                        }

                    }

                }

            }

        }

        return true;
    }

    public Node getReplacementNode(Node replacementNode){
        Node replacementParent = replacementNode;
        Node replacement = replacementNode;
        Node focusNode = replacementNode.rightChild;

        while (focusNode != null){
            replacementParent = replacement;
            replacement = focusNode;
            focusNode = focusNode.leftChild;
        }

        if (replacement != replacementNode.rightChild){
            replacementParent.leftChild = replacement.rightChild;
            replacement.rightChild = replacementNode.rightChild;
        }

        return replacement;
    }

    public void print(Node focusNode){
        if(focusNode !=null){
            print(focusNode.leftChild);
            System.out.println(focusNode);
            print(focusNode.rightChild);
        }
    }

    public static void main(String[] args) {
	 BinaryTree tree =  new BinaryTree();
	 tree.addNode(50, "Генеральный директор");
        tree.addNode(25, "Исполнительный директор");
        tree.addNode(15, "Оффис менеджер");
        tree.addNode(30, "Секретарь");
        tree.addNode(70, "Начальник отдела");
        tree.addNode(85, "Специалист");
        tree.print(tree.root);
        System.out.println("-------------------------------------");
        System.out.println(tree.findNode(30));
        System.out.println("-------------------------------------");
        tree.remove(30);
        tree.print(tree.root);
    }
}
