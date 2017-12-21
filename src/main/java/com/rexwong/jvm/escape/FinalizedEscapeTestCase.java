package com.rexwong.jvm.escape;
/**
 * 逃逸分析测试用例
 */
public class FinalizedEscapeTestCase {

    public static FinalizedEscapeTestCase caseForEscape = null;

    @Override
    protected void finalize() throws Throwable {

        //相当于C++中的析构方法,在GC回收之前，
        //会先调用一次这个方法,而这个方法又将this指针指向他自己，因此得以成功逃逸
        super.finalize();

        System.out.println("哈哈，我已逃逸！");

        caseForEscape = this;

    }
}
