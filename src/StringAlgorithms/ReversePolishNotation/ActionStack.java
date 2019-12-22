package StringAlgorithms.ReversePolishNotation;

class ActionStack {
    private String[] stackArr = new String[50];
    private int top = 0;

    void push(String a) {
        ++this.top;
        this.stackArr[this.top] = a;
    }

    String pop() {
        String a = this.stackArr[this.top];
        --this.top;
        return a;
    }

    boolean isClear() {
        return this.top == 0;
    }

    String readTop() {
        return this.top > 0 ? this.stackArr[this.top] : "null";
    }
}