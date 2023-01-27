class AVLNode {
    String key; //ISBN
    BookObject book; // Title <space> Author’s last name, any other attribute 
    int height;
    AVLNode leftPtr;
    AVLNode rightPtr;

    AVLNode(String isbn, BookObject book)   {
        key = isbn;
        leftPtr = null;
        rightPtr = null;
        height = 0;
    }
}

