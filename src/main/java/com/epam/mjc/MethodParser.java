package com.epam.mjc;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class MethodParser {

    /**
     * Parses string that represents a method signature and stores all it's members into a {@link MethodSignature} object.
     * signatureString is a java-like method signature with following parts:
     *      1. access modifier - optional, followed by space: ' '
     *      2. return type - followed by space: ' '
     *      3. method name
     *      4. arguments - surrounded with braces: '()' and separated by commas: ','
     * Each argument consists of argument type and argument name, separated by space: ' '.
     * Examples:
     *      accessModifier returnType methodName(argumentType1 argumentName1, argumentType2 argumentName2)
     *      private void log(String value)
     *      Vector3 distort(int x, int y, int z, float magnitude)
     *      public DateTime getCurrentDateTime()
     *
     * @param signatureString source string to parse
     * @return {@link MethodSignature} object filled with parsed values from source string
     */
    public MethodSignature parseFunction(String signatureString) {

        StringTokenizer stz = new StringTokenizer(signatureString, "()");
        List<String> substrings = new ArrayList<>();
        while (stz.hasMoreElements()) substrings.add(stz.nextToken());

        List<String> nameTypeAccess = new ArrayList<>();
        StringTokenizer stzNameTypeAccess = new StringTokenizer(substrings.get(0), " ");
        while (stzNameTypeAccess.hasMoreElements()) nameTypeAccess.add(0, stzNameTypeAccess.nextToken());

        ArrayList<MethodSignature.Argument> arguments = new ArrayList<>();
        if (substrings.size() > 1) {
            List<String> listOfArguments = new ArrayList<>();
            StringTokenizer stzArguments = new StringTokenizer(substrings.get(1), ",");
            while (stzArguments.hasMoreElements()) listOfArguments.add(stzArguments.nextToken().trim());

            for (String element : listOfArguments) {

                String[] argument = element.split(" ");
                arguments.add(new MethodSignature.Argument(argument[0], argument[1]));
            }
        }

        MethodSignature signature = new MethodSignature(nameTypeAccess.get(0), arguments);

        signature.setReturnType(nameTypeAccess.get(1));
        if(nameTypeAccess.size() > 2) signature.setAccessModifier(nameTypeAccess.get(2));

        return signature;
    }
}
