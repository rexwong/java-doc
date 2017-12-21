package com.rexwong.jvm.escape;
/**

 * 逃逸分析测试
 */
public class FinalizedEscape {
    public static void main(String[] args) throws InterruptedException {
        System.out.println(FinalizedEscapeTestCase.caseForEscape);
        FinalizedEscapeTestCase.caseForEscape = new FinalizedEscapeTestCase();
        System.out.println(FinalizedEscapeTestCase.caseForEscape);
        FinalizedEscapeTestCase.caseForEscape=null;
        System.gc();//call gc
        Thread.sleep(100);
        System.out.println(FinalizedEscapeTestCase.caseForEscape);

    }
}
