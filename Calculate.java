import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Calculate {
  public String expression() throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    String exp = br.readLine();
    
    if (exp != null && exp.equals("quit")) {
      return exp;
    }
    if (exp != null && exp.length() > 2) {
      String str = exp.substring(0,2);
      //Run the function of simplify
      if (str.equals("!s")) {
        return exp;
      }
      //Run the function of derivative
      if (str.equals("!d")) {
        return exp;
      }
    }

    //Judge the illegal char by java.util.regex
    String reg1 = "[^0-9a-zA-Z(\\*)(\\+)]";
    if (Match(reg1,exp)) {
      exp = "Error, wrong expression";
      return exp;
    }

    //filter the combination of digit and character
    String reg2 = "([a-zA-Z]+)(\\d)+";
    if (Match(reg2,exp)) {
      exp = "Error, wrong expression";
      return exp;
    }

    //filter the combination of digit and character
    String reg3 = "(\\d)+([a-zA-Z]+)";
    if (Match(reg3,exp)) {
      exp = "Error, wrong expression";
      return exp;
    }

    //filter String of multiple charater
    String reg4 = "([a-zA-Z]+){2,}";
    if (Match(reg4,exp)) {
      exp = "Error, wrong expression";
      return exp;
    }

    return exp;
  }
  
  public static boolean Match(String re, String exp) {
    Pattern pattern = Pattern.compile(re);
    Matcher matcher = pattern.matcher(exp);
    if (matcher.find()) {
      return true;
    } else {
      return false;
    }
  }

  public static boolean IsDigit(String str) {
    //Judge string is a digit number or not, using regular expression
    Pattern pattern = Pattern.compile("(\\d)+");
    Matcher matcher = pattern.matcher(str);
    return matcher.matches();
  }

  public static String Multiple(String exp) {
    int num = 1;
    StringBuilder sbvar = new StringBuilder();//StringBuilder object for combination of variable
    StringBuilder mulexp = new StringBuilder();//StringBuilder object for combination of factor
    String[] mulPart = exp.split("\\*");
    for (int j = 0; j < mulPart.length; j++) {
      if (IsDigit(mulPart[j])) {
        num *= Integer.parseInt(mulPart[j]);//multiple numers
      } else { 
        sbvar.append("*").append(mulPart[j]);//concat the variable
      }
    }
    //combination of number and variable
    mulexp.append(Integer.toString(num)).append(sbvar.toString());
    return mulexp.toString();
  }

  public static String CalculateExp(String exp) {
    int num;
    String smidExp = "";//the string of expression after multiple
    String mulexp = "";
    //only do the multiple when there is no addition
    if (!Match("\\+",exp)) {
      exp = Multiple(exp);
      return exp;
    }

    //first step,make the mutiple calculate
    String[] AddPart = exp.split("\\+");
    StringBuilder midExp = new StringBuilder();//using StringBuilder to concat String
    for (int i = 0; i < AddPart.length; i++) {
      mulexp = Multiple(AddPart[i]);
      //addition of these factor
      midExp.append("+").append(mulexp);
      //get the expression of the middle part of simplify()
      smidExp = midExp.toString().substring(1);
    }

    //second step,make the addition calculate
    String[] addSeg = smidExp.split("\\+");
    StringBuilder addexp = new StringBuilder();//
    StringBuilder aidAddexp = new StringBuilder();//StringBuilder object help for final combination
    num = 0;
    for (int k = 0; k < addSeg.length; k++) {
      if (IsDigit(addSeg[k])) {
        num += Integer.parseInt(addSeg[k]);//addition of these numbers
      } else {
        aidAddexp.append("+").append(addSeg[k]);//hold these non-digit factor
      }
    }
    addexp.append(Integer.toString(num)).append(aidAddexp.toString());
    String finalExp = "";//the final expression after simplify
    finalExp = addexp.toString();

    return finalExp;
  }

  public String simplify(String exp,String SimpExp) {
    char var;
    int val;
    //Get the variable in the expression
    var = SimpExp.substring(10).charAt(0);
    //Get the value of the variable
    val = Integer.parseInt(SimpExp.substring(12));
    //character or digit to String
    String svar = String.valueOf(var);
    String sval = Integer.toString(val);

    if (!Match(svar,exp)) {
      return exp;
    }
    //replace all variable by the value
    String str = exp.replace(svar,sval);
    str = Calculate.CalculateExp(str);
    return str;
  }

  public String derivative(String exp,String DervativeExp) {
    //get the variable from this expression of dervative
    char var;
    var = DervativeExp.charAt(DervativeExp.length() - 1);

    //Judge the variable is in the expression or not
    String svar = Character.toString(var);
    if (!Match(svar,exp)) {
      System.out.println("Error, no variable");
      return exp;
    }

    String[] addSeg = exp.split("\\+");
    int varCount;//count the number of variable of each additional part
    StringBuilder sbAddSeg = new StringBuilder();
    for (int i = 0; i < addSeg.length; i++) {
      String addPart = "";
      //Judge the variable is in the AddSeg or not
      if (Match(svar, addSeg[i])) {
        if (addSeg[i].length() == 1) {
          addPart = "1";
        } else if (addSeg[i].length() == 3) {
          addPart = addSeg[i].substring(0,1);
        } else {
          varCount = 0;
          StringBuilder factors = new StringBuilder();
          String[] vars = addSeg[i].split("\\*");
          for (int j = 0; j < vars.length; j++) {
            if (vars[j].equals(svar)) {
              varCount += 1;
            } else {
              factors.append("*").append(vars[j]);
            }
          }

          StringBuilder varFactors = new StringBuilder(Integer.toString(varCount));
          for (int k = 1; k < varCount; k++) { 
            varFactors.append("*").append(var);
          }
          varFactors.append(factors.toString());
          addPart = varFactors.toString();
        }
        sbAddSeg.append("+").append(addPart);
      }
    }
    String finalExp = sbAddSeg.toString().substring(1);
    finalExp = Calculate.CalculateExp(finalExp);
    return finalExp;
  }

  public static void main(String[] args) throws IOException {
    Calculate calobject = new Calculate();
    String exp;
    String strSimplify;
    String simpExp;
    String strDerivative;
    String derExp;

    //Get the Expression
    exp = calobject.expression();
    while (!exp.equals("quit")) {
      System.out.println(exp);

      //input the simplify expression
      String simplifyExp = calobject.expression();
      // If the string "simplify" in it,then run simplify()
      final long simstartTime = System.nanoTime();
      strSimplify = simplifyExp.substring(0,9);
      //Simplify the expression
      if (strSimplify.equals("!simplify")) {
        simpExp = calobject.simplify(exp, simplifyExp);
        System.out.println(simpExp);
      }
      final long simendTime = System.nanoTime();

      //input the dervative expression
      String dervativeExp = calobject.expression();
      //If the string "!d/dx" in it,then run dervative()
      final long derstartTime = System.nanoTime();
      strDerivative = dervativeExp.substring(0,2);
      if (strDerivative.equals("!d")) {
        derExp = calobject.derivative(exp,dervativeExp);
        System.out.println(derExp);
      }
      long derendTime = System.nanoTime();

      //Output the time of Simplify and Derivative
      System.out.println("Simplify and Derivative time:");
      System.out.println((simendTime - simstartTime + derendTime - derstartTime) / 1000 + "us");

      exp = calobject.expression();
    }
  }
}