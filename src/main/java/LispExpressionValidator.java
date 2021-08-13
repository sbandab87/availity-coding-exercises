// Java program for checking
// balanced brackets

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class LispExpressionValidator {

  public static void main(String[] args) {
    final List<String> exps = new ArrayList<>();
    exps.add("(let x 2 (mult x (let x 3 y 4 (add x y)))");
    exps.add("(add x y)");
    exps.add("((mult x (let x 3 y 4 (add x y))");
    exps.add("(60 * 9 / 5) + 32");
    exps.add("(write(+ (* (/ 9 5) 60) 32))");

    exps.stream()
        .forEach(
            exp -> {
              System.out.println(
                  isValidLispExpression(exp) ? exp + ": is Balanced" : exp + ": is Not Balanced");
            });
  }

  private static boolean isValidLispExpression(String expr) {
    final Stack<Character> stack = new Stack<Character>();

    for (int i = 0; i < expr.length(); i++) {
      char x = expr.charAt(i);

      if (x == '(') {
        stack.push(x);
        continue;
      }

      if (stack.isEmpty()) return false;

      if (x == ')') {
        stack.pop();
      }
    }

    return stack.isEmpty();
  }
}
