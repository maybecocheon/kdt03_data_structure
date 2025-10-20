package 자료구조_과제;

//Merge정렬 다항식 때 필요한 클래스

class Term implements Comparable<Term>{
    double coef;           // 계수
    int    exp;            // 지수

    //--- 생성자(constructor) ---//
    Term() {
    	
    }
    
    Term(double coef, int exp) {
        this.coef = coef;  this.exp = exp; 
    }
    
    @Override
    public String toString() {
    	return coef + "x^" + exp + " ";
    }
    
    //지수를 기준으로 비교
    @Override
    public int compareTo(Term b) {
    	//지수가 다르면 계수로 비교
    	if (this.exp != b.exp) {
    		return b.exp - this.exp; //내림차순
    	}
    	return Double.compare(b.coef, this.coef);
    }
}
