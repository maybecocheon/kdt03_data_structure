package 자료구조_03장_검색알고리즘;

import java.util.*;
import java.io.*;

class Term {
    int coef; // 계수
    int exp;  // 지수
    Term(int c, int e) { this.coef = c; this.exp = e; }
}

class Polynomial {
	static Term[] terms;
	static int capacity; // 총 용량
	static int free; // top과 같은 포인터
	int start; // 첫 번째 인덱스
	int finish; // 마지막 인덱스

	public void Polynominal() {
		terms = new Term[capacity];
	}
	
    @Override
    public String toString() {
    	//terms 배열 비어 있으면 0 출력
        if (terms.isEmpty()) return "0\n";
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < terms.size(); i++) {
        	//t 인스턴스 = terms의 인덱스가 내포하나 값
            Term t = terms.get(i);
            sb.append(t.coef).append("x^").append(t.exp);
            if (i != terms.size() - 1) sb.append(" + ");
        }
        sb.append('\n');
        return sb.toString();
    }
    
    
    private boolean isEmpty() {
    	if (free == 0) {
    		return true;
    	} else {
    		return false;
    	}
    }

    private int size() {
    	return capacity;
    }
    
    Term get(int num) {
    	Term t = new Term();
    	return t;
    }

    public void newTerm(int c, int e) {
        addTerm(c, e);
    }

    public void getData(int numTerms, Random rand) {
        terms.clear();
        if (numTerms <= 0) return;

        int degree = 2 * numTerms; // C++ 코드 동일
        int expo = degree;

        for (int i = degree; i > 0; i--) {
            int coef;
            do { coef = rand.nextInt(numTerms); } while (coef == 0);

            int newExpo;
            do { newExpo = rand.nextInt(degree); } while (newExpo >= expo);
            expo = newExpo;

            newTerm(coef, expo);
            if (expo == 0) break;
        }
    }


}
public class train_실습03_11다항식객체연산_상급자 {
    private static final Random RAND = new Random(49);
    
    //add 메서드 생성
    //P3 = P1.add(P2);
    private Polynomial add(Polynomial p) {
    	Polynomial c = new Polynomial();
    	
    	return c;
    }
    

    private static void printMenu() {
        System.out.println("1.INPUT");
        System.out.println("2.ADD");
        System.out.println("3.SUBTRACT");
        System.out.println("4.MULTIPLY");
        System.out.println("5.EVAL");
        System.out.println("6.ADDTERM");
        System.out.println("7.EXIT");
        System.out.print("선택> ");
    }

    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);
        Polynomial P1 = new Polynomial();
        Polynomial P2 = new Polynomial();
        Polynomial P3 = new Polynomial();
        Polynomial P4 = new Polynomial();
        Polynomial P5 = new Polynomial();

        while (true) {
            printMenu();
            if (!sc.hasNextInt()) break;
            int choice = sc.nextInt();

            switch (choice) {
                case 1: // INPUT
                    P1.getData(5, RAND);
                    P2.getData(9, RAND);
                    System.out.print(P1);
                    System.out.print(P2);
                    break;

                case 2: // ADD
                    P3 = P1.add(P2);
                    System.out.print(P3);
                    break;

                case 3: // SUBTRACT
                    P4 = P1.subtract(P2);
                    System.out.print(P4);
                    break;

                case 4: // MULTIPLY
                    P5 = P1.multiply(P2);
                    System.out.print(P5);
                    break;

                case 5: // EVAL
                    int result = P5.eval(3);
                    System.out.println(result);
                    break;

                case 6: // ADDTERM
                    P1.addTerm(9, 9);
                    System.out.print(P1);
                    break;

                case 7: // EXIT
                    System.out.println("프로그램을 종료합니다.");
                    sc.close();
                    return;

                default:
                    System.out.println("잘못된 선택입니다.");
            }
        }

        System.out.println("프로그램을 종료합니다.");
        sc.close();
    }
}

