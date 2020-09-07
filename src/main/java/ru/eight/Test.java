package ru.eight;

public class Test {
    public static void main(String[] args) {
        Inner inner = new Inner();
        inner.func("testStringOne", new StringWriter());
    }

    public static class Inner {
        public void func (String s, StringWriter stringWriter) {
            for (char c : s.toCharArray()) {
                print(stringWriter, c);
            }
        }

        private void print(StringWriter stringWriter, char c) {
            stringWriter.printPartOfString(c);
        }
    }

    public static class StringWriter {
        public void printPartOfString(char s) {
            System.out.println(s);
        }
    }
}
