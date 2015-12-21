package se.kth.ict.oodbook.design.uml;

public class ClassB {
    private ClassC objC;
    
    public void metA(int aParam) {
        objC.metB();
        ClassD objD = new ClassD();
    }

}
