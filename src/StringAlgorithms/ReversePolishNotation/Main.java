package StringAlgorithms.ReversePolishNotation;

import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws ScriptException {
        String string = new Scanner(System.in).next();
        System.out.printf("Expression:\n%s\nReverse Polish Notation:\n%s\nResult:\n%s",
                string, new Main().KURWA_RPN(string), new Main().KURWA(string));
    }

    private Object KURWA(String string) throws ScriptException {
        return new ScriptEngineManager().getEngineByName("js").eval(string);
    }

    private StringBuilder KURWA_RPN(String string) {
        String[] output = new String[string.length()];
        ActionStack actionStack = new ActionStack();
        int outI = 0;
        String x;
        for(int i = 0; i < string.length(); ++i) {
            if (string.charAt(i) == '/' || string.charAt(i) == '+' || string.charAt(i) == '*' || string.charAt(i) == '-' || string.charAt(i) == '(') {
                if (actionStack.isClear()) {
                    actionStack.push(String.valueOf(string.charAt(i)));
                } else if (string.charAt(i) == '(') {
                    actionStack.push(String.valueOf(string.charAt(i)));
                } else if (string.charAt(i) != '+' && string.charAt(i) != '-') {
                    if (string.charAt(i) == '*' || string.charAt(i) == '/') {
                        if (!actionStack.readTop().equals("(") && !actionStack.readTop().equals("+") && !actionStack.readTop().equals("-")) {
                            while(true) {
                                if (!actionStack.readTop().equals("*") && !actionStack.readTop().equals("/")) {
                                    actionStack.push(String.valueOf(string.charAt(i)));
                                    break;
                                }

                                x = actionStack.pop();
                                output[outI] = x;
                                ++outI;
                            }
                        } else {
                            actionStack.push(String.valueOf(string.charAt(i)));
                        }
                    }
                } else if (actionStack.readTop().equals("(")) {
                    actionStack.push(String.valueOf(string.charAt(i)));
                } else {
                    while(true) {
                        if (!actionStack.readTop().equals("+") && !actionStack.readTop().equals("-") && !actionStack.readTop().equals("*") && !actionStack.readTop().equals("/")) {
                            actionStack.push(String.valueOf(string.charAt(i)));
                            break;
                        }

                        x = actionStack.pop();
                        output[outI] = x;
                        ++outI;
                    }
                }
            }

            if (string.charAt(i) == ')') {
                while(!actionStack.readTop().equals("(")) {
                    x = actionStack.pop();
                    output[outI] = x;
                    ++outI;
                }

                actionStack.pop();
            }

            if (Character.isDigit(string.charAt(i))) {
                for(x = String.valueOf(string.charAt(i)); i + 1 < string.length() && Character.isDigit(string.charAt(i + 1)); x = x.concat(String.valueOf(string.charAt(i)))) {
                    ++i;
                }

                output[outI] = x;
                ++outI;
            }
        }

        while(!actionStack.isClear()) {
            x = actionStack.pop();
            output[outI] = x;
            ++outI;
        }

        StringBuilder returnString = new StringBuilder();

        for(int j = 0; j < outI; ++j) {
            returnString.append(output[j]).append(" ");
        }

        return returnString;
    }

}

