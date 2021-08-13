import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/** Validator for validating the parentheses for LISP code. */
public class LispExpressionValidator {

  public static void main(final String[] args) {
    final List<String> exps = new ArrayList<>();
    exps.add("(let x 2 (mult x (let x 3 y 4 (add x y)))");
    exps.add("(add x y)");
    exps.add("((mult x (let x 3 y 4 (add x y))");
    exps.add("(60 * 9 / 5) + 32");
    exps.add("60 * 9 / 5) + 32");
    exps.add("  ");
    exps.add(")num()");
    exps.add("(write(+ (* (/ 9 5) 60) 32))");

    exps.forEach(
        exp -> {
          System.out.println(
              isValidLispExpression(exp) ? exp + ": is Balanced" : exp + ": is Not Balanced");
        });
  }

  private static boolean isValidLispExpression(final String expr) {
    final Stack<Character> stack = new Stack<>();

    for (int i = 0; i < expr.length(); i++) {
      char c = expr.charAt(i);

      if (c == '(') {
        stack.push(c);
        continue;
      }

      if (stack.isEmpty()) {
        return false;
      }

      if (c == ')') {
        stack.pop();
      }
    }

    return stack.isEmpty();
  }
}
